package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for Activitys object (caution: ambiguous spell) 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Activitys {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** time-stamp property */
	@JsonProperty("timestampMs")
	private long _timestampMs;
	
	/** for activity array */
	@JsonProperty("activities")
	private List<Activity> _activities;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get time-stamp of this activity
	 * @return time-stamp
	 */
	protected Date getTimestamp() {
		return new Date(_timestampMs);
	}
	
	/**
	 * list activity contents
	 * @return activity contents. returns null if not exists
	 */
	protected List<Activity> listActivities() {
		return _activities;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() {
		// date time format /////////////////////
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// prepare data contents ////////////////
		String[] tokens = new String[] {
			sdf.format(getTimestamp()),
			StringUtils.join(_activities,"|")
		};

		// compose string ///////////////////////
		return StringUtils.join(tokens,"!");
	}
}