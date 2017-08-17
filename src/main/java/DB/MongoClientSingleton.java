package DB;

import com.mongodb.MongoClient;

public class MongoClientSingleton {
  private static MongoClient instance = null;

  private MongoClientSingleton(){
    instance = new MongoClient();
  }

  public static MongoClient getInstance(){
    if(instance == null){
      instance = new MongoClient();
    }
    return instance;
  }
}
