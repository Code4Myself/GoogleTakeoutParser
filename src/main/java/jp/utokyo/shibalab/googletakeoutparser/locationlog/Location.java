package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.StringUtils.EMPTY;
/**
 * class for location object
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Comparable<Location> {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * get CSV header line with comma delimiter
	 * @return header line
	 */
	public static String getCsvHeader() {
		return getCsvHeader(",");
	}
	
	/**
	 * get CSV header line with the indicated delimiter
	 * @param delim delimiter
	 * @return header line
	 */
	public static String getCsvHeader(String delim) {
		return StringUtils.join(
				new String[]{"timestamp","longitude","latitude","accuracy","velocity","heading","altitude","verticalAccuracy","activity"},
				delim);
	}
	
	
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

	/** vertical accuracy */
	@JsonProperty("verticalAccuracy")
	private Integer _verticalAccuracy;
	
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
		_timestampMs      = null;
		_latitudeE7       = null;
		_longitudeE7      = null;
		_accuracy         = null;
		_velocity         = null;
		_heading          = null;
		_altitude         = null;
		_verticalAccuracy = null;	// added@2018.06.25
		_activities       = null;
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
	 * @param verticalAccuracy vertical accuracy
	 * @param activities activities
	 */
	protected Location(	Long timestampMs,Integer longitudeE7,Integer latitudeE7,Integer accuracy,Integer velocity,
						Integer heading, Integer altitude,
						Integer verticalAccuracy,	 // added@2018.06.25
						List<Activitys> activities) 
	{
		_timestampMs      = timestampMs;
		_latitudeE7       = latitudeE7;
		_longitudeE7      = longitudeE7;
		_accuracy         = accuracy;
		_velocity         = velocity;
		_heading          = heading;
		_altitude         = altitude;
		_verticalAccuracy = verticalAccuracy;	// added@2018.06.25
		_activities       = activities;
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
	 * get vertical accuracy 
	 * @return vertical accuracy
	 */
	public Integer getVerticalAccuracy() {
		return _verticalAccuracy;
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
	 * make CSV string as the following column order(timestamp,longitude,latitude,accuracy,velocity,heading,altitude,verticalAccuracy,activity)
	 * @return CSV string
	 */
	public String toCsvString() {
		return toCsvString(",");
	}
	
	/**
	 * make CSV string as the following column order(timestamp,longitude,latitude,accuracy,velocity,heading,altitude,verticalAccuracy,activity)
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
										_accuracy != null ? String.valueOf(_accuracy) : EMPTY,
										_velocity != null ? String.valueOf(_velocity) : EMPTY,
										_heading != null ?  String.valueOf(_heading)  : EMPTY,
										_altitude != null ? String.valueOf(_altitude) : EMPTY,
										_verticalAccuracy != null ? String.valueOf(_verticalAccuracy) : EMPTY};
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
	
	@Override
	public int compareTo(Location loc) { 
		Long L0 = new Long(_timestampMs);
		Long L1 = new Long(loc._timestampMs);
		
		return L0.compareTo(L1);
	}
	
	@Override
	public String toString() {
		return String.format("ts=%d,lon=%d,lat=%d,accuracy=%d,velocity=%d,altitude=%d,verticalAccuracy=%d,activitys=%s)",
							 _timestampMs,
							 _longitudeE7,
							 _latitudeE7,
							 _accuracy,
							 _velocity,
							 _altitude,
							 _verticalAccuracy,
							 _activities);
	}	
}
