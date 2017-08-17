package POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StopPointSearchResult {
  @JsonProperty("$type")
  private String type;

  @JsonProperty
  private String query;
  @JsonProperty
  private double from;
  @JsonProperty
  private double page;
  @JsonProperty
  private double pageSize;
  @JsonProperty
  private String provider;
  @JsonProperty
  private double total;
  @JsonProperty
  private List<StopPoint> matches;
  @JsonProperty
  private double maxScore;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public double getFrom() {
    return from;
  }

  public void setFrom(double from) {
    this.from = from;
  }

  public double getPage() {
    return page;
  }

  public void setPage(double page) {
    this.page = page;
  }

  public double getPageSize() {
    return pageSize;
  }

  public void setPageSize(double pageSize) {
    this.pageSize = pageSize;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public List<StopPoint> getMatches() {
    return matches;
  }

  public void setMatches(List<StopPoint> matches) {
    this.matches = matches;
  }

  public double getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(double maxScore) {
    this.maxScore = maxScore;
  }
}
