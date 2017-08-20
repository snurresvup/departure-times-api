import DB.DBUtil;
import Resources.ArrivalPredictionsResource;
import Resources.ClosestStationResource;
import Resources.StationsResource;
import Resources.TransportationModeResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;
import java.util.EnumSet;


public class DepartureTimesApplication extends Application<DepartureTimesConfiguration>{
  public static void main(String[] args) throws Exception {
    new DepartureTimesApplication().run(args);
  }

  @Override
  public String getName() {
    return "departure-times-api";
  }

  public void run(DepartureTimesConfiguration departureTimesConfiguration, Environment environment) throws Exception {
    final FilterRegistration.Dynamic cors =
        environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");


    final Client client = new JerseyClientBuilder(environment).using(departureTimesConfiguration.getJerseyClientConfiguration())
        .build(getName());
    environment.jersey().register(new TransportationModeResource(client));
    environment.jersey().register(new ArrivalPredictionsResource(client, departureTimesConfiguration.getDbFactory().createDBInstance(client)));
    environment.jersey().register(new StationsResource(departureTimesConfiguration.getDbFactory().createDBInstance(client)));
    environment.jersey().register(new ClosestStationResource(departureTimesConfiguration.getDbFactory().createDBInstance(client)));

    //Populate the mongoDB bus-stops collection if empty
    if(!departureTimesConfiguration.getDbFactory().createDBInstance(client).busCollectionIsInitialized()) {
      DBUtil.populateBusStopCollection(client);
    }
  }
}
