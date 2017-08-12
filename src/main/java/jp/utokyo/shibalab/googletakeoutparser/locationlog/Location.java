package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for location object
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Comparable<Location> {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** time-stamp */
	@JsonProperty("timestampMs")
	private Long _timestampMs;
	
	/** longitude */
	@JsonProperty("longitudeE7")
	private Integer _longitudeE7;
	
	/** latitude */
	@JsonProperty("latitudeE7")
	private Integer _latitudeE7;
	
	/** location accuracy */
	@JsonProperty("accuracy")
	private Integer _accuracy;
	
	/** velocity */
	@JsonProperty("velocity")
	private Integer _velocity;
	
	/** heading */
	@JsonProperty("heading")
	private Integer _heading;
	
	/** altitude */
	@JsonProperty("altitude")
	private Integer _altitude;
	
	/** activity array */
//	@JsonProperty("activitys")
	@JsonProperty("activity")
	private List<Activitys> _activities;

	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 */
	protected Location() {
		_timestampMs = null;
		_latitudeE7  = null;
		_longitudeE7 = null;
		_accuracy    = null;
		_velocity    = null;
		_heading     = null;
		_altitude    = null;
		_activities  = null;
	}
	
	/**
	 * initialization 
	 * @param timestampMs time-stamp in msec
	 * @param longitudeE7 longitude 
	 * @param latitudeE7 latitude
	 * @param accuracy accuracy
	 * @param velocity velocity
	 * @param heading  heading
	 * @param altitude altitude
	 * @param activitys activities
	 */
	protected Location(Long timestampMs,Integer longitudeE7,Integer latitudeE7,Integer accuracy,Integer velocity,Integer heading,Integer altitude,List<Activitys> activities) {
		_timestampMs = timestampMs;
		_latitudeE7  = latitudeE7;
		_longitudeE7 = longitudeE7;
		_accuracy    = accuracy;
		_velocity    = velocity;
		_heading     = heading;
		_altitude    = altitude;
		_activities  = activities;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get location time-stamp
	 * @return time-stamp
	 */
	public Date getTime() { 
		return _timestampMs != null ? new Date(_timestampMs) : null;
	}
	
	/**
	 * get longitude in degree
	 * @return longitude. return NaN if no value
	 */
	public double getLon() { 
		return _longitudeE7 != null ? _longitudeE7 / 1.0e7 : Double.NaN;
	}
	
	/**
	 * get latitude in degree
	 * @return latitude. return NaN if no value
	 */
	public double getLat() { 
		return _latitudeE7 != null ?  _latitudeE7 / 1.0e7 : Double.NaN;
	}
	
	/**
	 * get location accuracy
	 * @return accuracy. return null if no value
	 */
	public Integer getAccuracy() { 
		return _accuracy;
	}
	
	/**
	 * get velocity 
	 * @return velocity. return null if no value
	 */
	public Integer getVelocity() {
		return _velocity;
	}
	
	/**
	 * get heading 
	 * @return heading. return null if no value
	 */
	public Integer getHeading() {
		return _heading;
	}
	
	/**
	 * get altitude
	 * @return altitude. return null if no value
	 */
	public Integer getAltitude() {
		return _altitude;
	}
	
	/**
	 * get activity count
	 * @return activity count. return null if no value
	 */
	public int getActivityCount() { 
		return _activities == null ? 0 : _activities.size();
	}
	
	/**
	 * get time-stamp of activity
	 * @param idx index
	 * @return activity
	 */
	public Date getActivityTimestamp(int idx) {
		return _activities != null ? _activities.get(idx).getTimestamp() : null;
	}
	
	/**
	 * get activity list
	 * @param idx index
	 * @return activity list
	 */
	public List<Activity> listActivities(int idx) {
		return  _activities != null ? _activities.get(idx).listActivities() : null;
	}
	
	/**
	 * make CSV string as the following column order(timestamp,longitude,latitude,accuracy,velocity,heading,altitude,activity)
	 * @return CSV string
	 */
	public String toCsvString() {
		return toCsvString(",");
	}
	
	/**
	 * make CSV string as the following column order(timestamp,longitude,latitude,accuracy,velocity,heading,altitude,activity)
	 * @param delim column delimiter
	 * @return CSV string
	 */
	public String toCsvString(String delim) {
		// date time format ///////////////////////////////
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// prepare CSV contents ///////////////////////////
		String[] tokens = new String[]{ sdf.format(getTime()),
										String.format("%.08f",getLon()),
										String.format("%.08f",getLat()),
										String.valueOf(_accuracy),
										String.valueOf(_velocity),
										String.valueOf(_heading),
										String.valueOf(_altitude)};
		// compose CSV line 
		String core = StringUtils.join(tokens,delim);
		
		// prepare sub contents from activity /////////////
		String sub = "";
		if( _activities != null ) {
			StringBuffer buf = new StringBuffer();
			for(Activitys acs:_activities) {
				buf.append(";").append(acs.toString());
			}
			sub = buf.substring(1);
		}		
		
		// returns result /////////////////////////////////
		return core + "," + sub;
	}
	
	/* @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Location loc) { 
		Long L0 = new Long(_timestampMs);
		Long L1 = new Long(loc._timestampMs);
		
		return L0.compareTo(L1);
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return String.format("ts=%d,lon=%d,lat=%d,accuracy=%d,velocity=%d,altitude=%d,activitys=%s)",
							 _timestampMs,
							 _longitudeE7,
							 _latitudeE7,
							 _accuracy,
							 _velocity,
							 _altitude,
							 _activities);
	}	
}
