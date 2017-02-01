package jp.utokyo.shibalab.googletakeoutparser.query;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Query object class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** time-stamp */
	@JsonProperty("query_text")
	private String _queryText;
		
	/* ==============================================================
	 * instance fields
	 * ============================================================== */	
	/** activity array */
	@JsonProperty("id")
	private List<TimeStamp> _timeStamp;
	
	/**
	 * get query text
	 * @return query text
	 */
	public String getQueryText() {
		return _queryText;
	}
	
	/**
	 * get time stamps 
	 * @return time stamp list
	 */
	public List<TimeStamp> listTimeStamp() {
		return _timeStamp;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() { 
		return _queryText;
	}
}
