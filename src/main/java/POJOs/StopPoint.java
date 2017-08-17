package POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StopPoint {
  @JsonProperty("$type")
  private String type;
  @JsonProperty("icsId")
  private String icsId;
  @JsonProperty
  private String topMostParentId;
  @JsonProperty
  List<String> modes;
  @JsonProperty
  private String id;
  @JsonProperty
  private String url;
  @JsonProperty
  private String name;
  @JsonProperty
  private double lat;
  @JsonProperty
  private double lon;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }
}
