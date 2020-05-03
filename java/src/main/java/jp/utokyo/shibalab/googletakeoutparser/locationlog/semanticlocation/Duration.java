package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

/**
 * class for duration 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Duration {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** start time-stamp */
	private Date _startTimestamp;
	
	/** end time-time */
	private Date _endTimestamp;
	

	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param startTimestampMs start time-stamp in unxtime
	 * @param endTimestampMs end time-stamp in unixtime
	 */
	private Duration(@JsonProperty(JsonKeys.START_TIMESTAMP_MS) Long startTimestampMs, 
				     @JsonProperty(JsonKeys.END_TIMESTAMP_MS)   Long endTimestampMs)
	{
		_startTimestamp = new Date(startTimestampMs);
		_endTimestamp   = new Date(endTimestampMs);
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get start time-stamp 
	 * @return start time-stamp
	 */
	public Date getStartTimestamp() {
		return _startTimestamp;
	}
	
	/**
	 * get end time-stamp
	 * @return end time-stamp
	 */
	public Date getEndTimestamp() {
		return _endTimestamp;
	}
}
