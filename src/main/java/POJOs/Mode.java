package POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mode {
  private String type;
  private boolean tflService;
  private boolean farePaying;
  private boolean scheduledService;
  private String modeName;

  @JsonProperty("$type")
  public String get$type() {
    return type;
  }

  @JsonProperty("$type")
  public void settype(String $type) {
    this.type = type;
  }

  @JsonProperty("isTflService")
  public boolean isTflService() {
    return tflService;
  }

  @JsonProperty("isTflService")
  public void setTflService(boolean tflService) {
    this.tflService = tflService;
  }

  @JsonProperty("isFarePaying")
  public boolean isFarePaying() {
    return farePaying;
  }

  @JsonProperty("isFarePaying")
  public void setFarePaying(boolean farePaying) {
    this.farePaying = farePaying;
  }

  @JsonProperty("isScheduledService")
  public boolean isScheduledService() {
    return scheduledService;
  }

  @JsonProperty("isScheduledService")
  public void setScheduledService(boolean scheduledService) {
    this.scheduledService = scheduledService;
  }

  @JsonProperty("modeName")
  public String getModeName() {
    return modeName;
  }

  @JsonProperty("modeName")
  public void setModeName(String modeName) {
    this.modeName = modeName;
  }
}
