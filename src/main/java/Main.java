import DB.DBSimple;
import POJOs.Station;

public class Main {
  public static void main(String[] args) {
    for(Station s : (new DBSimple()).getListOfStations()){
      System.out.println(s.getName());
    }
  }
}
