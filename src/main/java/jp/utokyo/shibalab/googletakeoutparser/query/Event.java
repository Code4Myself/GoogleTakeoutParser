package jp.utokyo.shibalab.googletakeoutparser.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Event class
 * @deprecated
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** time-stamp property */
	@JsonProperty("query")
	private Query _query;
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 */
	public Event() {
		// nothing to do 
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get query text
	 * @return query text
	 */
	public Query getQuery() {
		return _query;
	}
}
