package jp.utokyo.shibalab.googletakeoutparser.query;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ID object class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ID {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** time-stamp */
	@JsonProperty("timestamp_usec")
	private Long _timestampNs;
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 */
	protected ID() {
		this(null);
	}
	
	/**
	 * initialization 
	 * @param timestampNs time-stamp in nano second
	 */
	protected ID(Long timestampNs) { 
		_timestampNs = timestampNs;
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get query time-stamp. [caution] date class limits time resolution in msec. 
	 * @return time-stamp
	 */
	public Date getTime() { 
		return new Date(_timestampNs/1000);
	}
	
	/**
	 * get raw time stamp value in Nano second
	 * @return timestamp (nano second)
	 */
	public Long getTimestampNano() {
		return _timestampNs;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return String.format("%d",_timestampNs);
	}
}
