package Resources;

import POJOs.Station;
import Util.LatLng;
import DB.DB;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.List;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
public class StationsResource {
  private DB db;

  public StationsResource(DB db) {
    this.db = db;
  }

  @GET
  public List<Station> getStations(@QueryParam("lat") Double lat, @QueryParam("lng") Double lng, @QueryParam("radius") int radius){
    if(lat != null && lng != null){
      return db.getStationsWithinRadius(new LatLng(lat, lng), radius == 0 ? 200 : radius);
    }
    return db.getListOfStations();
  }
}
