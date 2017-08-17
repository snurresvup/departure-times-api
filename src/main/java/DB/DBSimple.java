package DB;

import POJOs.Station;
import Util.Constants;
import Util.LatLng;
import Util.XMLUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdom2.JDOMException;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class DBSimple implements DB {
  private final String stationsJSONFile = "stations.json";
  private final String stationsURL = "https://data.tfl.gov.uk/tfl/syndication/feeds/stations.kml";

  public List<Station> getListOfStations() {
    try{
      URL url = new URL(stationsURL + "?app_id=" + Constants.APP_ID + "app_key=" + Constants.API_KEY);

      List<Station> stations = XMLUtil.getStationsFromKML(url);

      saveToFile(stations, stationsJSONFile);

      return stations;
    } catch (IOException | JDOMException e) {
      e.printStackTrace();
    }

    System.out.println("Fetcing from file");
    return XMLUtil.getStationsFromKML(new File(stationsJSONFile));
  }

  @Override
  public List<Station> getStationsWithinRadius(LatLng position, int radius) {
    return getListOfStations().stream()
        .filter(
            station -> position.distanceTo(station.getLatitude(), station.getLongitude()) <= radius)
        .collect(Collectors.toList());
  }

  @Override
  public Station getStationById(String stationId) {
    return null;
  }

  @Override
  public boolean busCollectionIsInitialized() {
    return true;
  }


  private void saveToFile(List<Station> stations, String stationsKMLFile) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(stationsKMLFile), stations);
  }
}
