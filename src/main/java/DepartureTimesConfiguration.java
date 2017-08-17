import DB.DBFactory;
import DB.DBFactoryMongo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DepartureTimesConfiguration extends Configuration {
  @Valid
  @NotNull
  @JsonProperty
  private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();

  @NotNull
  @JsonProperty
  private DBFactory dbFactory = new DBFactoryMongo();

  public DBFactory getDbFactory() {
    return dbFactory;
  }

  public JerseyClientConfiguration getJerseyClientConfiguration() {
    return jerseyClientConfiguration;
  }
}
