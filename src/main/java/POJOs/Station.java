package POJOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {
  @JsonProperty
  private String name;
  @JsonProperty
  private Geo loc;
  @JsonProperty
  private String description;
  @JsonProperty
  private String id;

  @JsonCreator
  public Station(){

  }

  public Station(String id, String name, double latitude, double longitude, String description) {
    this.id = id;
    this.name = name;
    this.loc = new Geo();
    this.loc.setCoordinates(new double[] {longitude, latitude});
    this.description = description;
  }

  @Override
  public int hashCode() {
    return id.hashCode() * name.hashCode() * 31;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null) return false;
    if(obj.getClass() != Station.class) return false;
    Station other = (Station) obj;
    return id.equals(other.id) && name.equals(other.name);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLatitude() {
    return loc.getLatitude();
  }

  public void setLatitude(double latitude) {
    loc.setLatitude(latitude);
  }

  @JsonSetter("lat")
  public void setLat(double lat){
    setLatitude(lat);
  }

  public double getLongitude() {
    return loc.getLongitude();
  }

  public void setLongitude(double longitude) {
    loc.setLongitude(longitude);
  }

  @JsonSetter("lng")
  public void setLng(double lng){
    setLongitude(lng);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
