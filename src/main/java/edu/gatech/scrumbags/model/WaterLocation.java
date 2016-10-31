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

	private double latitude;
	private double longitude;

	/**
	 * Creates a new Water report location from a latitude and longitude.
	 *
	 * @param latitude Latitude of water report location
	 * @param longitude Longitude of water report location
	 */
	public WaterLocation (double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Returns the latitude of this water location
	 *
	 * @return latitude
	 */
	public double getLatitude () {
		return latitude;
	}

	/**
	 * Returns the latitude string of this water location
	 *
	 * @return latitude
	 */
	public String getLatitudeString () {
		return Double.toString(latitude);
	}

	/**
	 * Returns the longitude of this water location
	 *
	 * @return longitude
	 */
	public double getLongitude () {
		return longitude;
	}

	/**
	 * Returns the latitude string of this water location
	 *
	 * @return latitude
	 */
	public String getLongitudeString () {
		return Double.toString(longitude);
	}

	/**
	 * Returns a string representation of this latitude, longitude pair
	 *
	 * @return String representation of the form "#,#"
	 */
	@Override public String toString () {
		return String.format("%.8f, %.8f", latitude, longitude);
	}
}
