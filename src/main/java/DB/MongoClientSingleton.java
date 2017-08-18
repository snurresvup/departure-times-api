package DB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoClientSingleton {
  private static MongoClient instance = null;

  private MongoClientSingleton(){
  }

  public static MongoClient getInstance(){
    if(instance == null){
      instance = new MongoClient(new MongoClientURI("mongodb://mongodb:27017"));
    }
    return instance;
  }
}
