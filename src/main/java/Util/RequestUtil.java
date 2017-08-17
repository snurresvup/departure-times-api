package Util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class RequestUtil {
  /**
   * Creates a standard WebTarget object for a request to TFL, with the path provided in the parameters.
   * @param client The jersey client
   * @return The WebTarget constructed for the request
   */
  public static WebTarget createTFLRequest(Client client, String path){
    return client.target(Constants.API_ENDPOINT)
              .path(path)
              .queryParam("app_key", Constants.API_KEY)
              .queryParam("app_id", Constants.APP_ID);

  }
}
