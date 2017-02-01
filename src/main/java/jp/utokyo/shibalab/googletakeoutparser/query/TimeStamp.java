package jp.utokyo.shibalab.googletakeoutparser.query;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * time stamp object class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeStamp {	
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** time-stamp */
	@JsonProperty("timestamp_usec")
	private double _timestampMs;
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get location time-stamp
	 * @return time-stamp
	 */
	public Date getTime() { 
		return new Date((long)(_timestampMs/1000));
	}
}
