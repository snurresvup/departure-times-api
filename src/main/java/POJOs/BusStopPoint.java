package POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStopPoint {
  @JsonProperty
  public String naptanId;
  @JsonProperty
  public String fullName;
  @JsonProperty
  public String commonName;
  @JsonProperty
  public String stopType;
  @JsonProperty
  public double lat;
  @JsonProperty
  public double lon;

  public String getNaptanId() {
    return naptanId;
  }

  public void setNaptanId(String naptanId) {
    this.naptanId = naptanId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public String getStopType() {
    return stopType;
  }

  public void setStopType(String stopType) {
    this.stopType = stopType;
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
