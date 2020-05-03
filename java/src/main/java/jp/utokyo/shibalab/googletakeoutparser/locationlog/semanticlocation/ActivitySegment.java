package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;


/**
 * class for activity segment
 */
public class ActivitySegment {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** start location */
	private Location _startLocation;
	
	/** end location */
	private Location _endLocation;
	
	/** time duration */
	private Duration _duration;
	
	/** distance */
	private Double   _distance;
	
	/** activity type */
	private String   _activityType;
	
	/** confidence level in string */
	private String   _confidence;
	
	/** activity candidate list */
	private List<Activity> _activities;
	
	/** way points */
	private List<WaypointPath.Waypoint> _waypoints;
	
	/** simplified raw path */
	private List<SimplifiedRawPath.Point> _simplifiedRawPath;
	
	/** transit stops */
	private List<TransitPath.TransitStop> _transitStops;
	

	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param startLocation start location 
	 * @param endLocation end location 
	 * @param duration duration 
	 * @param distance distance 
	 * @param activityType activity type
	 * @param confidence confidence level in string
	 * @param activities activity candidate list
	 * @param waypoints way points
	 * @param simplifiedRawPath simplified raw path
	 * @param transitPath transit path
	 */
	private ActivitySegment(@JsonProperty(JsonKeys.START_LOCATION) Location startLocation, 
							@JsonProperty(JsonKeys.END_LOCATION)   Location endLocation, 
							@JsonProperty(JsonKeys.DURATION)       Duration duration, 
							@JsonProperty(JsonKeys.DISTANCE)       Double   distance, 
							@JsonProperty(JsonKeys.ACTIVITY_TYPE)  String   activityType, 
							@JsonProperty(JsonKeys.CONFIDENCE)     String   confidence, 
							@JsonProperty(JsonKeys.ACTIVITIES)     List<Activity> activities, 
							@JsonProperty(JsonKeys.WAYPOINT_PATH)  WaypointPath   waypointPath, 
							@JsonProperty(JsonKeys.SIMPLIFIED_RAW_PATH) SimplifiedRawPath simplifiedRawPath, 
							@JsonProperty(JsonKeys.TRANSIT_PATH)        TransitPath       transitPath)
	{
		_startLocation     = startLocation;
		_endLocation       = endLocation;
		_duration          = duration;
		_distance          = distance != null ? distance : Double.NaN;
		_activityType      = activityType;
		_confidence        = confidence;
		_activities        = activities;
		_waypoints         = waypointPath != null      ? waypointPath.getWaypoints() : null;
		_simplifiedRawPath = simplifiedRawPath != null ? simplifiedRawPath.getPoints() : null;
		_transitStops      = transitPath != null       ? transitPath.getTransitStops() : null;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get start location 
	 * @return start location 
	 */
	public Location getStartLocation() {
		return _startLocation;
	}
	
	/**
	 * get end location 
	 * @return end location
	 */
	public Location getEndLocation() {
		return _endLocation;
	}
	
	/**
	 * get duration 
	 * @return duration 
	 */
	public Duration getDuration() {
		return _duration;
	}
	
	/**
	 * get distance (meter?)
	 * @return distance
	 */
	public double getDistance() {
		return _distance;
	}
	
	/**
	 * get activity type
	 * @return activity type
	 */
	public String getActivityType() {
		return _activityType;
	}
	
	/**
	 * get confidence string for activity
	 * @return confidence
	 */
	public String getConfidence() {
		return _confidence;
	}
	
	/**
	 * get activities
	 * @return activities
	 */
	public List<Activity> getActivities() {
		return _activities;
	}
	
	/**
	 * get way points
	 * @return way points
	 */
	public List<WaypointPath.Waypoint> getWaypoints() {
		return _waypoints;
	}
	
	/**
	 * get simplified raw path 
	 * @return simplified raw path
	 */
	public List<SimplifiedRawPath.Point> getSimplifiedRawPath() {
		return _simplifiedRawPath;
	}
	
	/**
	 * get transit stops
	 * @return transit stops
	 */
	public List<TransitPath.TransitStop> getTransitStops() {
		return _transitStops;
	}
}
