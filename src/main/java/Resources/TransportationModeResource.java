package Resources;

import POJOs.Mode;
import Util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/mode")
@Produces(MediaType.APPLICATION_JSON)
public class TransportationModeResource {
  private Client client;

  public TransportationModeResource(Client client) {
    this.client = client;
  }

  /**
   * @return The list of possible transportation modes provided by tfl
   */
  @GET
  public List<String> getDepartureTimes(){
    String res = client.target(Constants.API_ENDPOINT)
        .path("/Line/Meta/Modes")
        .queryParam("app_key", Constants.API_KEY)
        .queryParam("app_id", Constants.APP_ID)
        .request()
        .get(String.class);

    ObjectMapper objectMapper = new ObjectMapper();

    List<String> listOfModes = new ArrayList<String>();

    try {
      List<Mode> modes = objectMapper.readValue(res, new TypeReference<List<Mode>>(){});
      for(Mode m : modes){
        listOfModes.add(m.getModeName());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    return listOfModes;
  }
}
