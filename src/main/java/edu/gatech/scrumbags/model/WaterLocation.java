
package edu.gatech.scrumbags.model;

/** Describes a location for a water source or quality report designed for compatibility with Google Maps' Java API see:
 * https://github.com/googlemaps/google-maps-services-java/blob/master/src/main/java/com/google/maps/model/LatLng.java for more
 * info Created by Beau on 10/9/2016. */
public class WaterLocation {

    private double latitude;
    private double longitude;

    /** Creates a new Water report location from a latitude and longitude.
     *
     * @param latitude Latitude of water report location
     * @param longitude Longitude of water report location */
    public WaterLocation (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /** Returns the latitude of this water location
     *
     * @return latitude */
    public double getLatitude () {
        return latitude;
    }

    /** Returns the latitude string of this water location
     *
     * @return latitude */
    public String getLatitudeString () {
        return Double.toString(latitude);
    }

    /** Returns the longitude of this water location
     *
     * @return longitude */
    public double getLongitude () {
        return longitude;
    }

    /** Returns the latitude string of this water location
     *
     * @return latitude */
    public String getLongitudeString () {
        return Double.toString(longitude);
    }

    public String getContinent () {
        // checking for errors
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("latitude cannot be greater than 90 or less than -90 degrees");
        } else if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("longitude cannot be greater than 180 or less than -180 degrees");
        }
        // categorizing the world map
        if (latitude > 15.0 && latitude < 75.0 && longitude > -165.0 && longitude < -45.0) {
            return "North America";
        } else if (latitude > -60.0 && latitude <= 15.0 && longitude > -105.0 && longitude < -30.0) {
            return "South America";
        } else if (latitude > -40.0 && latitude <= 35.0 && longitude > -25.0 && longitude < 45.0) {
            return "Africa";
        } else if (latitude > 35.0 && latitude < 70.0 && longitude > -15.0 && longitude < 45.0) {
            return "Europe";
        } else if (latitude > -10.0 && latitude < 75.0 && longitude >= 45.0 && longitude < 180.0) {
            return "Asia";
        } else if (latitude > -45.0 && latitude < -10.0 && longitude >= 105.0 && longitude < 180.0) {
            return "Oceania";
        } else if (latitude < -60.0) {
            return "Antarctica";
        } else {
            return "undefined";
        }
    }

    /** Returns a string representation of this latitude, longitude pair
     *
     * @return String representation of the form "#,#" */
    @Override
    public String toString () {
        return String.format("%.8f, %.8f", latitude, longitude);
    }
}
