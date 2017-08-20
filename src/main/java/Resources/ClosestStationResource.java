package Resources;

import POJOs.Station;
import Util.LatLng;
import DB.DB;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Path("/closest-station")
@Produces(MediaType.APPLICATION_JSON)
public class ClosestStationResource {
  private DB db;

  public ClosestStationResource(DB db) {
    this.db = db;
  }

  /**
   * Gets a list of all stations from the database and calculates the closest station to the provided latitude and longitude
   * @param lat Latitude
   * @param lng Longitude
   * @return The closest station to the provided position
   */
  @GET
  public Station getClosestStation(@QueryParam("lat") @NotNull Double lat, @QueryParam("lng") @NotNull Double lng){
    LatLng currentPos = new LatLng(lat, lng);

    List<Station> stations = db.getListOfStations();

    Optional<Station> res = stations.stream().min(Comparator.comparingDouble(station -> currentPos.distanceTo(new LatLng(station.getLatitude(), station.getLongitude()))));

    return res.orElse(null);
  }
}
