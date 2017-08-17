package Util;

import POJOs.Station;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XMLUtil {
  private static Namespace KML_NAMESPACE = Namespace.getNamespace("http://www.opengis.net/kml/2.2");
  public static List<Station> getStationsFromKML(File kmlFile) {
    try {
      Document document = new SAXBuilder().build(kmlFile);
      return buildStationsList(document);
    } catch (JDOMException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List<Station> getStationsFromKML(URL kmlURL) throws JDOMException, IOException {
    Document document = new SAXBuilder().build(kmlURL);
    return buildStationsList(document);
  }

  private static List<Station> buildStationsList(Document document) {
    List<Station> stations;
    List<Element> placemarks = document.getRootElement().getChild("Document", KML_NAMESPACE).getChildren("Placemark", KML_NAMESPACE);

    stations = new ArrayList<Station>(placemarks.size());
    for (Element placemark : placemarks) {
      String name = placemark.getChildText("name", KML_NAMESPACE).trim();

      String[] coords = placemark.getChild("Point", KML_NAMESPACE).getChildText("coordinates", KML_NAMESPACE).split(",");
      double lng = Double.parseDouble(coords[0]);
      double lat = Double.parseDouble(coords[1]);

      String description = placemark.getChildText("description", KML_NAMESPACE);

      stations.add(new Station("", name, lat, lng, description));
    }
    return stations;
  }
}
