package DB;

import POJOs.Station;
import Util.LatLng;

import java.util.List;
import java.util.stream.Collectors;

public interface DB {
  /**
   * @return the list of all stations known to the system
   */
  List<Station> getListOfStations();

  /**
   * @return the list of stations within the radius from the provided position
   */
  List<Station> getStationsWithinRadius(LatLng position, int radius);

  /**
   * @param stationId the station id of the station to fetch
   * @return the station with the provided stationId
   */
  Station getStationById(String stationId);

  boolean busCollectionIsInitialized();
}
