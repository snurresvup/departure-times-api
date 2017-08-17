package POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointPageResponse {
  @JsonProperty
  private List<Double> centrePoint;
  @JsonProperty
  private List<BusStopPoint> stopPoints;
  @JsonProperty
  private double pageSize;
  @JsonProperty
  private double total;
  @JsonProperty
  private double page;

  public List<Double> getCentrePoint() {
    return centrePoint;
  }

  public void setCentrePoint(List<Double> centrePoint) {
    this.centrePoint = centrePoint;
  }

  public List<BusStopPoint> getStopPoints() {
    return stopPoints;
  }

  public void setStopPoints(List<BusStopPoint> stopPoints) {
    this.stopPoints = stopPoints;
  }

  public double getPageSize() {
    return pageSize;
  }

  public void setPageSize(double pageSize) {
    this.pageSize = pageSize;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public double getPage() {
    return page;
  }

  public void setPage(double page) {
    this.page = page;
  }
}
