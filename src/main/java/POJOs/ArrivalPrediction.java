package POJOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArrivalPrediction {
  @JsonProperty("$type")
  private String type;
  @JsonProperty
  private String id;
  @JsonProperty
  private int operationType;
  @JsonProperty
  private String vehicleId;
  @JsonProperty
  private String naptanId;
  @JsonProperty
  private String stationName;
  @JsonProperty
  private String lineId;
  @JsonProperty
  private String lineName;
  @JsonProperty
  private String platformName;
  @JsonProperty
  private String direction;
  @JsonProperty
  private String bearing;
  @JsonProperty
  private String destinationNaptanId;
  @JsonProperty
  private String destinationName;
  @JsonProperty
  private String timestamp;
  @JsonProperty
  private int timeToStation;
  @JsonProperty
  private String currentLocation;
  @JsonProperty
  private String towards;
  @JsonProperty
  private String expectedArrival;
  @JsonProperty
  private String timeToLive;
  @JsonProperty
  private String modeName;
  @JsonProperty
  private Timing timing;

  public ArrivalPrediction(String stationName, String lineName, int timeToStation){
    this.stationName = stationName;
    this.lineName = lineName;
    this. timeToStation = timeToStation;
  }

  @JsonCreator
  public ArrivalPrediction(){

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getOperationType() {
    return operationType;
  }

  public void setOperationType(int operationType) {
    this.operationType = operationType;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getNaptanId() {
    return naptanId;
  }

  public void setNaptanId(String naptanId) {
    this.naptanId = naptanId;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public String getLineId() {
    return lineId;
  }

  public void setLineId(String lineId) {
    this.lineId = lineId;
  }

  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getBearing() {
    return bearing;
  }

  public void setBearing(String bearing) {
    this.bearing = bearing;
  }

  public String getDestinationNaptanId() {
    return destinationNaptanId;
  }

  public void setDestinationNaptanId(String destinationNaptanId) {
    this.destinationNaptanId = destinationNaptanId;
  }

  public String getDestinationName() {
    return destinationName;
  }

  public void setDestinationName(String destinationName) {
    this.destinationName = destinationName;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public int getTimeToStation() {
    return timeToStation;
  }

  public void setTimeToStation(int timeToStation) {
    this.timeToStation = timeToStation;
  }

  public String getCurrentLocation() {
    return currentLocation;
  }

  public void setCurrentLocation(String currentLocation) {
    this.currentLocation = currentLocation;
  }

  public String getTowards() {
    return towards;
  }

  public void setTowards(String towards) {
    this.towards = towards;
  }

  public String getExpectedArrival() {
    return expectedArrival;
  }

  public void setExpectedArrival(String expectedArrival) {
    this.expectedArrival = expectedArrival;
  }

  public String getTimeToLive() {
    return timeToLive;
  }

  public void setTimeToLive(String timeToLive) {
    this.timeToLive = timeToLive;
  }

  public String getModeName() {
    return modeName;
  }

  public void setModeName(String modeName) {
    this.modeName = modeName;
  }

  public Timing getTiming() {
    return timing;
  }

  public void setTiming(Timing timing) {
    this.timing = timing;
  }
}
