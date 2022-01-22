package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for locations
 */
public class Locations {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** location list */
	@JsonProperty("locations")
	private List<Location> _locations;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get location list
	 * @return location list
	 */
	public List<Location> listLocations() {
		return _locations;
	}
	
	@Override
	public String toString() { 
		return _locations.toString();
	}
}
