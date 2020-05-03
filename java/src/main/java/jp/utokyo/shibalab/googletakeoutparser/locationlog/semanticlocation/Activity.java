package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

/**
 * class for activity
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Activity {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** transportation mode */
	private String  _activityType;
	
	/** probability */
	private Double  _probability;

	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization 
	 * @param activityType activity type(transportation mode)
	 * @param probability probability
	 */
	private Activity(@JsonProperty(JsonKeys.ACTIVITY_TYPE) String activityType,
					 @JsonProperty(JsonKeys.PROBABILITY)   Double probability)
	{ 
		_activityType = activityType;
		_probability  = probability;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get activity type
	 * @return activity type
	 */
	public String getActivityType() { 
		return _activityType;
	}
	
	/**
	 * get probability
	 * @return probability
	 */
	public double getProbability() { 
		return _probability;
	}
}
