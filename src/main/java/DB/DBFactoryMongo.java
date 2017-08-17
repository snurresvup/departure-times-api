package DB;

import javax.ws.rs.client.Client;

public class DBFactoryMongo implements DBFactory {
  private DBMongo instance;

  @Override
  public DB createDBInstance(Client client) {
    if(instance == null){
      instance = new DBMongo(client, MongoClientSingleton.getInstance());
    }
    return instance;
  }
}
