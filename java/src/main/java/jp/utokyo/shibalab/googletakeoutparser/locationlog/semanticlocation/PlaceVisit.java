package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

/**
 * class for place visit 
 */
public class PlaceVisit {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** location */
	private Location _location;
	
	/** time duration */
	private Duration _duration;
	
	/** place confidence string */
	private String   _placeConfidence;
	
	/** center latitude */
	private double   _centerLat;
	
	/** center longitude */
	private double   _centerLng;
	
	/** visit confidence value */
	private double   _visitConfidence;
	
	/** other candidate locations */
	private List<Location> _otherCandidateLocations;
	
	/** edit confirmation status */
	private String         _editConfirmationStatus;
	
	/** child visits */
	private List<PlaceVisit> _childVisits;

	/** simplified raw path */
	private List<SimplifiedRawPath.Point> _simplifiedRawPath;

	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param location location information
	 * @param duration time duration
	 * @param placeConfidence place confidence string
	 * @param latE7 latitude
	 * @param lngE7 longitude
	 * @param visitConfidence visit confidence value
	 * @param otherCandidateLocations other candidate locations
	 * @param editConfirmationStatus edit confirmation status
	 * @param simplifiedRawPath simplified raw path
	 * @param childVisits child visits
	 */
	private PlaceVisit(	@JsonProperty(JsonKeys.LOCATION)         Location location, 
						@JsonProperty(JsonKeys.DURATION)         Duration duration, 
						@JsonProperty(JsonKeys.PLACE_CONFIDENCE) String   placeConfidence, 
						@JsonProperty(JsonKeys.CENTER_LAT)       Long     latE7,
					    @JsonProperty(JsonKeys.CENTER_LNG)       Long     lngE7, 
					    @JsonProperty(JsonKeys.VISIT_CONFIDENCE)          Double            visitConfidence, 
					    @JsonProperty(JsonKeys.OTHER_CANDIDATE_LOCATIONS) List<Location>    otherCandidateLocations, 
					    @JsonProperty(JsonKeys.EDIT_CONFIRMATION_STATUS)  String            editConfirmationStatus,
						@JsonProperty(JsonKeys.SIMPLIFIED_RAW_PATH)       SimplifiedRawPath simplifiedRawPath,  
					    @JsonProperty(JsonKeys.CHILD_VISITS)              List<PlaceVisit>  childVisits)
	{
		_centerLng = lngE7 != null ? lngE7 / 1.0e7 : Double.NaN;
		_centerLat = latE7 != null ? latE7 / 1.0e7 : Double.NaN;
		
		_location = location;
		_duration = duration;
		_placeConfidence         = placeConfidence;
		_visitConfidence         = visitConfidence != null ? visitConfidence : Double.NaN;
		_otherCandidateLocations = otherCandidateLocations;
		_editConfirmationStatus  = editConfirmationStatus;
		_childVisits             = childVisits;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get location
	 * @return location
	 */
	public Location getLocation() {
		return _location;
	}
	
	/**
	 * get time duration 
	 * @return time duration
	 */
	public Duration getDuration() {
		return _duration;
	}
	
	/**
	 * get place confidence string (e.g. MEDIUM_CONFIDENCE)
	 * @return place confidence string
	 */
	public String getPlaceConfidence() {
		return _placeConfidence;
	}
	
	/**
	 * get center latitude
	 * @return center latitude
	 */
	public double getCenterLatitude() {
		return _centerLat;
	}
	
	/**
	 * get center longitude
	 * @return center longitude
	 */
	public double getCenterLongitude() {
		return _centerLng;
	}
	
	/**
	 * get visit confidence value
	 * @return visit confidence value
	 */
	public double getVisitConfidence() {
		return _visitConfidence;
	}
	
	/**
	 * get other candidate locations, only with (lon/lat/placeId/locationConfidence)
	 * @return other candidate locations
	 */
	public List<Location> getOtherCandidateLocations() {
		return _otherCandidateLocations;
	}
	
	/**
	 * get edit confirmation status (e.g. NOT_CONFIRMED) 
	 * @return edit confirmation status
	 */
	public String getEditConfirmationStatus() {
		return _editConfirmationStatus;
	}

	/**
	 * get simplified raw path 
	 * @return simplified raw path
	 */
	public List<SimplifiedRawPath.Point> getSimplifiedRawPath() {
		return _simplifiedRawPath;
	}
	
	/**
	 * get child visit places
	 * @return child visit places
	 */
	public List<PlaceVisit> getChildVisits() {
		return _childVisits;
	}
}
