package DB;

import POJOs.Station;
import Util.Constants;
import Util.LatLng;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class DBMongo implements DB {

  private final MongoDatabase database;
  private final MongoClient mongoClient;
  private final Client client;
  private final MongoCollection<Document> busStopCollection;
  private final ObjectMapper objectMapper;


  public DBMongo(Client client, MongoClient mongoClient) {
    this.client = client;
    this.mongoClient = mongoClient;
    database = mongoClient.getDatabase(Constants.DATABASE_NAME);
    busStopCollection = database.getCollection(Constants.BUS_STOPS_COLLECTION);
    objectMapper = new ObjectMapper();
  }

  @Override
  public List<Station> getListOfStations() {
    if(busStopCollection.count() == 0){
      DBUtil.populateBusStopCollection(client);
    }

    List<Station> stations = new ArrayList<>();

    busStopCollection.find().forEach((Block<Document>) document -> {
      try {
        Station station = objectMapper.readValue(document.toJson(), Station.class);
        stations.add(station);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    return stations;
  }

  @Override
  public List<Station> getStationsWithinRadius(LatLng position, int radius) {

    ArrayList<Station> result = new ArrayList<>();

    busStopCollection.find(geoWithinCenterSphere("loc",
        position.getLng(),
        position.getLat(),
        ((double) radius)/(LatLng.EARTH_RADIUS*1000.0))).forEach((Block<Document>) document -> {
      try {
        result.add(objectMapper.readValue(document.toJson(), Station.class));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    return result;
  }

  @Override
  public Station getStationById(String stationId) {
    Document document = busStopCollection.find(eq("id", stationId)).first();
    Station result = null;
    try {
      result = objectMapper.readValue(document.toJson(), Station.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean busCollectionIsInitialized() {
    return busStopCollection.count() > 0;
  }
}
