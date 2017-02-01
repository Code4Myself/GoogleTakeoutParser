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
	private long _timestampMs;
	
	/** longitude */
	@JsonProperty("longitudeE7")
	private int _lonE7;
	
	/** latitude */
	@JsonProperty("latitudeE7")
	private int _latE7;
	
	/** location accuracy */
	@JsonProperty("accuracy")
	private int _accuracy;
	
	/** activity array */
	@JsonProperty("activitys")
	private List<Activitys> _activitys;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get location time-stamp
	 * @return time-stamp
	 */
	public Date getTime() { 
		return new Date(_timestampMs);
	}
	
	/**
	 * get longitude in degree
	 * @return longitude
	 */
	public double getLon() { 
		return _lonE7 / 1.0e7;
	}
	
	/**
	 * get latitude in degree
	 * @return latitude
	 */
	public double getLat() { 
		return _latE7 / 1.0e7;
	}
	
	/**
	 * get location accuracy
	 * @return accuracy
	 */
	public int getAccuracy() { 
		return _accuracy;
	}
	
	/**
	 * get activity count
	 * @return activity count
	 */
	public int getActivityCount() { 
		return _activitys != null ? _activitys.size() : 0;
	}
	
	/**
	 * get time-stamp of activity
	 * @param idx index
	 * @return activity
	 */
	public Date getActivityTimestamp(int idx) {
		return _activitys != null ? _activitys.get(idx).getTimestamp() : null;
	}
	
	/**
	 * get activity list
	 * @param idx index
	 * @return activity list
	 */
	public List<Activity> listActivities(int idx) {
		return  _activitys != null ? _activitys.get(idx).listActivities() : null;
	}
	
	/**
	 * make CSV string 
	 * @return csv string
	 */
	public String toCsvString() {
		// date time format ///////////////////////////////
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// prepare CSV contents ///////////////////////////
		String[] tokens = new String[]{ sdf.format(getTime()),
										String.format("%.08f",getLon()),
										String.format("%.08f",getLat()),
										String.valueOf(getAccuracy())    };
		// compose CSV line 
		String core = StringUtils.join(tokens,",");
		
		// prepare sub contents from activitys ////////////
		String sub = "";
		if( _activitys != null ) {
			StringBuffer buf = new StringBuffer();
			for(Activitys acs:_activitys) {
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
		return String.format("(%d,%d,%d,%d,%s)",
							 _timestampMs,
							 _lonE7,
							 _latE7,
							 _accuracy,
							 _activitys);
	}	
}
