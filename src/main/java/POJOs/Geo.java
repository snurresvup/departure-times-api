package POJOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Geo {
  @JsonProperty
  public String type;
  @JsonProperty @NotEmpty
  public double[] coordinates;

  public Geo() {
    this.type = "Point";
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double[] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(double[] coordinates) {
    this.coordinates = coordinates;
  }

  @JsonIgnore
  public double getLatitude(){
    return coordinates[1];
  }

  @JsonIgnore
  public double getLongitude(){
    return coordinates[0];
  }

  @JsonIgnore
  public void setLatitude(double latitude) {
    this.coordinates[1] = latitude;
  }

  @JsonIgnore
  public void setLongitude(double longitude) {
    this.coordinates[0] = longitude;
  }
}
