package DB;

import javax.ws.rs.client.Client;

public interface DBFactory {
  DB createDBInstance(Client client);
}
