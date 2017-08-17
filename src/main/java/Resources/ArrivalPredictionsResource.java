package Resources;

import DB.DB;
import POJOs.*;
import Util.Constants;
import Util.LatLng;
import Util.RequestUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.net.URL;
import java.util.*;

@Path("/arrival-predictions")
@Produces(MediaType.APPLICATION_JSON)
public class ArrivalPredictionsResource {

  private Client client;
  private DB db;
  private final ObjectMapper objectMapper;


  public ArrivalPredictionsResource(Client client, DB db){
    this.client = client;
    this.db = db;
    objectMapper = new ObjectMapper();
  }

  @GET
  public Response getArrivalPredictions(@QueryParam("station") String stationName, @QueryParam("id") String stationId){
    if((stationName == null || stationName.isEmpty()) &&
        (stationId == null || stationId.isEmpty())){
      return Response.status(Response.Status.BAD_REQUEST).entity("Either station or id parameter must be present.").build();
    }

    if(stationId != null && !stationId.isEmpty()){
      List<ArrivalPrediction> listOfPredictions = requestListOfPredictions(stationId);
      return Response.status(Response.Status.OK).entity(listOfPredictions).build();
    } else {
      //Optional<Station> stopPointStationOpt = db.getListOfStations().stream().filter(s -> s.getName().equals(stationName)).findAny();
      //if (!stopPointStationOpt.isPresent()) return null;

      //Station stopPointStation = stopPointStationOpt.get();

      //String stopPointId = getStopPointId(stopPointStation);

      StreamingOutput stream = getInstantPredictions(stationName);

      //List<ArrivalPrediction> listOfPredictions = requestListOfPredictions(stopPointId); //TODO

      return Response.ok(stream).build();
    }
  }

  private StreamingOutput getInstantPredictions(String stationName) {
    try {
      InputStream inputStream = (new URL(Constants.COUNTDOWN_API_URL)).openStream();

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

      String line = bufferedReader.readLine();

      final String[] data = parseArray(line);
      long timeStamp = data == null ? 0 : Long.parseLong(data[2]);

      return outputStream -> {
        Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        String[] line1 = data;

        while(line1 != null){
          if(line1.length < 4) {
            line1 = parseArray(bufferedReader.readLine());
            continue;
          }
          int arrivalTime = (int) ((Long.parseLong(line1[3]) - timeStamp)/1000);
          writer.write(objectMapper.writeValueAsString(new ArrivalPrediction(line1[1], line1[2], arrivalTime)));
          writer.flush();
          line1 = parseArray(bufferedReader.readLine());
        }
      };
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private String[] parseArray(String arrayString){
    if(arrayString == null) return null;
    //Remove array notation
    String temp = arrayString.replaceAll("\\[", "")
        .replaceAll("\\]", "");
    String[] result = temp.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    //Remove quotes
    for (int i = 0; i < result.length; i++) {
      result[i] = result[i].replaceAll("\"", "");
    }
    return result;
  }

  private List<ArrivalPrediction> requestListOfPredictions(String stationId) {
    ObjectMapper objectMapper = new ObjectMapper();
    String res = client.target(Constants.API_ENDPOINT)
        .path("/StopPoint/" + stationId + "/Arrivals")
        .queryParam("app_key", Constants.API_KEY)
        .queryParam("app_id", Constants.APP_ID)
        .request()
        .get(String.class);

    List<ArrivalPrediction> listOfPredictions = null;

    try {
      listOfPredictions = objectMapper.readValue(res, new TypeReference<List<ArrivalPrediction>>(){});
    } catch (IOException e) {
      e.printStackTrace();
    }
    return listOfPredictions;
  }

  private String getStopPointId(Station stopPointStation) {
    String res = RequestUtil.createTFLRequest(client, "/StopPoint/Search")
        .queryParam("query", stopPointStation.getName())
        .request()
        .get(String.class);

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      StopPointSearchResult searchResult = objectMapper.readValue(res, new TypeReference<StopPointSearchResult>(){});
      if(searchResult.getMatches().size() == 0) return null;
      StopPoint stopPoint = findClosestMatch(searchResult.getMatches(), stopPointStation);
      return stopPoint.getId();
    } catch (IOException e) {
      System.out.println("Did we get it here" + e);
      e.printStackTrace();
    }

    return null;
  }

  /**
   *
   * @param matches List of stoppoints to search among
   * @param station The station to match
   * @return The best matching station based on distance
   * @throws IllegalArgumentException if matches contains no elements or if station null.
   */
  private StopPoint findClosestMatch(List<StopPoint> matches, Station station) {
    if(matches.isEmpty()) throw new IllegalArgumentException("Matches must be non-empty");
    if(station == null) throw new IllegalArgumentException("Station cannot be null");

    Optional<StopPoint> res = matches.stream().min(Comparator.comparing(
        stopPoint -> LatLng.distance(station.getLatitude(), station.getLongitude(), stopPoint.getLat(), stopPoint.getLon())
    ));

    return res.get();
  }
}
