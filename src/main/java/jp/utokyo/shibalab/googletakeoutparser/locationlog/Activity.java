package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for activity data
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Activity {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** mode(?) */
	@JsonProperty("type")
	private String _type;
	
	/** confidence value(%?) */
	@JsonProperty("confidence")
	private int    _confidence;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get mode(?)
	 * @return mode
	 */
	public String getType() { 
		return _type;
	}
	/**
	 * get confidence value
	 * @return confidence value(%?)
	 */
	public int getConfidence() { 
		return _confidence;
	}		
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() { 
		return String.format("%s=%d",_type,_confidence);
	}
}
