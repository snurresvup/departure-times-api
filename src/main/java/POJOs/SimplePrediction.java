package POJOs;

public class SimplePrediction {
  private String staionName;
  private String line;
  private double arrivalTime;

  public SimplePrediction(String staionName, String line, double arrivalTime) {
    this.staionName = staionName;
    this.line = line;
    this.arrivalTime = arrivalTime;
  }

  public String getStaionName() {
    return staionName;
  }

  public void setStaionName(String staionName) {
    this.staionName = staionName;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public double getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(double arrivalTime) {
    this.arrivalTime = arrivalTime;
  }
}
