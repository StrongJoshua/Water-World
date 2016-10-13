package edu.gatech.scrumbags.model;

/**
 * Describes a location for a water source or quality report
 * designed for compatibility with Google Maps' Java API
 * see:
 * https://github.com/googlemaps/google-maps-services-java/blob/master/src/main/java/com/google/maps/model/LatLng.java
 * for more info
 * Created by Beau on 10/9/2016.
 */
public class WaterLocation {

	private double lat;
	private double lng;

	/**
	 * Creates a new Water report location from a latitude and longitude.
	 *
	 * @param lat Latitude of water report location
	 * @param lng Longitude of water report location
	 */
	public WaterLocation (double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	/**
	 * Returns the latitude of this water location
	 *
	 * @return latitude
	 */
	public double getLat () {
		return lat;
	}

	/**
	 * Returns the longitude of this water location
	 *
	 * @return longitude
	 */
	public double getLng () {
		return lng;
	}

	/**
	 * Returns a string representation of this latitude, longitude pair
	 *
	 * @return String representation of the form "#,#"
	 */
	@Override public String toString () {
		return String.format("%.8f, %.8f", lat, lng);
	}
}
