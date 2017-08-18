package DB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoClientSingleton {
  private static MongoClient instance = null;

  private MongoClientSingleton(){
    instance = new MongoClient(new MongoClientURI("mongodb://mongo:27017"));
  }

  public static MongoClient getInstance(){
    if(instance == null){
      instance = new MongoClient();
    }
    return instance;
  }
}
