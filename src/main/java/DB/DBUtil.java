package DB;

import POJOs.BusStopPoint;
import POJOs.Station;
import POJOs.StopPointPageResponse;
import Util.Constants;
import Util.RequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DBUtil {

  /**
   * Responsible for populating the bus-stops collection in the mongoDB with data from tfl.
   * @param client the client used for web communication with the tfl API
   */
  public static void populateBusStopCollection(Client client){
    try {
      int requestNumber = 1;
      ObjectMapper objectMapper = new ObjectMapper();
      Set<Station> stations = new HashSet<>();

      String response = RequestUtil.createTFLRequest(client, "/StopPoint/Mode/bus")
          .queryParam("page", requestNumber++)
          .request().get(String.class);

      StopPointPageResponse pageResponse = objectMapper.readValue(response, StopPointPageResponse.class);
      int totalNumberOfRequests = (int) Math.ceil(pageResponse.getTotal()/pageResponse.getPageSize());

      addStopsToStationsList(response, stations);

      //Add the remaining pages to the stations list
      while(requestNumber <= totalNumberOfRequests){
        System.out.println("Fetching page number: " + requestNumber);

        response = RequestUtil.createTFLRequest(client, "/StopPoint/Mode/bus")
            .queryParam("page", requestNumber++)
            .request().get(String.class);

        System.out.println("Page received");

        addStopsToStationsList(response, stations);
      }

      asyncAddStopsToCollection(stations, MongoClientSingleton.getInstance().getDatabase(Constants.DATABASE_NAME).getCollection(Constants.BUS_STOPS_COLLECTION));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static void addStopsToStationsList(String response, Set<Station> stations) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    List<BusStopPoint> busStops = objectMapper.readValue(response, StopPointPageResponse.class).getStopPoints();
    stations.addAll(
        busStops.stream().map(
            busStop -> new Station(busStop.getNaptanId(), busStop.getCommonName(), busStop.getLat(), busStop.getLon(), "N/A")
        ).collect(Collectors.toList())
    );
  }

  private static void asyncAddStopsToCollection(Set<Station> stations, MongoCollection<Document> collection){
    new Thread(() -> {
      List<Document> records = stations.stream().map(
          station -> {
            List<Double> coords = new ArrayList<>();
            coords.add(station.getLongitude());
            coords.add(station.getLatitude());

            Document loc = new Document()
                .append("type", "Point")
                .append("coordinates", coords);
            return new Document()
                .append("id", station.getId())
                .append("name",station.getName())
                .append("description", station.getDescription())
                .append("loc", loc);
          }
      ).collect(Collectors.toList());

      collection.insertMany(records);
    }).start();
  }
}
