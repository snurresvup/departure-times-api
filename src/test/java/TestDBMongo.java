import Util.Constants;
import Util.LatLng;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.mongodb.client.model.Filters.geoWithinCenterSphere;

public class TestDBMongo {
  private MongoClient client;
  private MongoCollection<Document> busStopCollection;
  private final LatLng londonCenter = new LatLng(51.507351, -0.127758);

  @Before
  public void setup(){
    client = new MongoClient();
    busStopCollection = client.getDatabase(Constants.DATABASE_NAME).getCollection(Constants.BUS_STOPS_COLLECTION);
  }

  @Ignore
  @Test
  public void shouldFindEntryWithGeoIndex(){
    FindIterable<Document> findIterable = busStopCollection.find(geoWithinCenterSphere("loc",
        londonCenter.getLat(),
        londonCenter.getLng(),
        1.1));

    findIterable.forEach((Block<Document>) document -> System.out.println(document.toJson()));
  }
}
