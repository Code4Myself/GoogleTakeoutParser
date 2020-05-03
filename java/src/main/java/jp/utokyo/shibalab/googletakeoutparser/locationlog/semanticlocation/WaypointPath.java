package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

/**
 * Way point path 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class WaypointPath {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** way points */
	@JsonProperty(JsonKeys.WAYPOINTS)
	private List<Waypoint> _waypoints;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get 'waypoints'
	 * @return waypoint list
	 */
	public List<Waypoint> getWaypoints( ) {
		return _waypoints;
	}
	
	/**
	 * class for way point
	 */
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Waypoint extends LonLat {
		/* ==============================================================
		 * constructors
		 * ============================================================== */
		/**
		 * initialization
		 * @param latE7 latitude
		 * @param lngE7 longitude
		 */
		private Waypoint(@JsonProperty(JsonKeys.LAT) Long latE7,
						 @JsonProperty(JsonKeys.LNG) Long lngE7)
		{
			super(
				lngE7 != null ? lngE7 / 1.0e7 : Double.NaN, 
				latE7 != null ? latE7 / 1.0e7 : Double.NaN
			);
		}
	}
}
