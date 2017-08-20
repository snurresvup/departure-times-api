package Resources;

import POJOs.Station;
import Util.LatLng;
import DB.DB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
public class StationsResource {
  private DB db;

  public StationsResource(DB db) {
    this.db = db;
  }

  /**
   * Get a list of stations known to the system. If lat, lng and radius is provided, the endpoint will return a list of stations with a distance smaller than radius to the position (lat, lng).
   * Otherwise the endpoint will return a list of all known stations.
   * @param lat The latitude of the point to calculate from.
   * @param lng The longitude of the point to calculate from.
   * @param radius The radius of the circle to get stations within.
   */
  @GET
  public List<Station> getStations(@QueryParam("lat") Double lat, @QueryParam("lng") Double lng, @QueryParam("radius") int radius){
    if(lat != null && lng != null){
      return db.getStationsWithinRadius(new LatLng(lat, lng), radius == 0 ? 200 : radius);
    }
    return db.getListOfStations();
  }
}
