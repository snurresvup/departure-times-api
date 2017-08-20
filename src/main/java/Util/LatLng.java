package Util;

/**
 * A utility class for representing a position by latitude and longitude
 */
public class LatLng {
  public static final double EARTH_RADIUS = 6371;
  private double latitude;
  private double longitude;

  public LatLng(double latitude, double longitude) {
    if(!isValidLatitude(latitude) || !isValidLongitude(longitude)) throw new IllegalArgumentException("Latitude and/or longitude is not valid");
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * Calculates the distance to other
   * @param other The LatLng position to which to calculate the distance
   * @return The distance to other in meters
   */
  public double distanceTo(LatLng other){
    return distance(this, other);
  }

  /**
   * Calculates the distance to (lat, lng) from this
   * @param lat Latitude
   * @param lng Longitude
   * @return The distance in meters
   */
  public double distanceTo(double lat, double lng) {
    return distance(latitude, longitude, lat, lng);
  }

  private static boolean isValidLongitude(double longitude) {
    return -180.00000 <= longitude && longitude <= 180.00000;
  }

  private static boolean isValidLatitude(double latitude) {
    return -90.00000 <= latitude && latitude <= 90.00000;
  }

  /**
   * Calculates the distance between ll1 and ll2 in meters
   * @param ll1 position 1
   * @param ll2 position 2
   * @return The distance between position 1 and position 2 in meters
   */
  public static double distance(LatLng ll1, LatLng ll2){
    return distance(ll1.latitude, ll1.longitude, ll2.latitude, ll2.longitude);
  }


  /**
   * Uses the 'haversine' formula to calculate the great-circle distance between (latx, lngx) and (laty, lngy) in meters.
   * Source: http://www.movable-type.co.uk/scripts/latlong.html
   * @param latx
   * @param lngx
   * @param laty
   * @param lngy
   * @return the distnace between the two points in meters.
   */
  public static double distance(double latx, double lngx, double laty, double lngy){
    if(!isValidLatitude(latx) || !isValidLatitude(laty)
        || !isValidLongitude(lngx) || !isValidLongitude(lngy)) {
      throw new IllegalArgumentException("Latitude and/or longitude is not valid");
    }

    double latxRadians = Math.toRadians(latx);
    double latyRadians = Math.toRadians(laty);
    double deltaLatRadians = Math.toRadians(latx - laty);
    double deltaLngRadians = Math.toRadians(lngx - lngy);

    double a = Math.pow(Math.sin(deltaLatRadians/2), 2)
        + Math.cos(latxRadians) * Math.cos(latyRadians) * Math.pow(Math.sin(deltaLngRadians/2), 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    return EARTH_RADIUS * c * 1000;

    //return Math.abs(Math.sqrt(Math.pow(latx, 2) + Math.pow(lngx, 2))
      //  - Math.sqrt(Math.pow(laty, 2) + Math.pow(lngy, 2)));
  }

  public double getLat() {
    return latitude;
  }

  public double getLng() {
    return longitude;
  }
}
