package jp.utokyo.shibalab.googletakeoutparser.locationlog;

/**
 *
 */
public interface JsonKeys {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** timestampMs 			*/	static final String TIMESTAMP     = "timestampMs";
	/** latitudeE7				*/	static final String LATITUDE      = "latitudeE7";
	/** longitudeE7				*/	static final String LONGITUDE     = "longitudeE7";
	/** accuracy				*/	static final String ACCURACY      = "accuracy";
	/** velocity 				*/	static final String VELOCITY      = "velocity";
	/** heading 				*/	static final String HEADING       = "heading";
	/** altitude	 			*/	static final String ALTITUDE      = "altitude";
	/** verticalAccuracy		*/	static final String VERTICAL_ACCURACY = "verticalAccuracy";
	/** activity 				*/	static final String ACTIVITY      = "activity";
	/** @deprecated 			*/	static final String ACTIVITYS     = "activitys";
	/** activities 				*/	static final String ACTIVITIES    = "activities";
	/** type 					*/	static final String TYPE          = "type";
	/** confidence 				*/	static final String CONFIDENCE    = "confidence";
	
	/** 'activityType'			*/	static final String ACTIVITY_TYPE             = "activityType";
	/** 'latE7'		 			*/	static final String LAT                       = "latE7";
	/** 'lngE7' 				*/	static final String LNG                       = "lngE7";
	/** 'probability' 			*/	static final String PROBABILITY               = "probability";
	/** 'waypointPath'			*/	static final String WAYPOINT_PATH             = "waypointPath";
	/** 'waypoints'		 		*/	static final String WAYPOINTS            	  = "waypoints";
	/** 'duration'				*/	static final String DURATION                  = "duration";
	/** 'startTimestampMs'		*/	static final String START_TIMESTAMP_MS        = "startTimestampMs";
	/** 'endtimestampMs'		*/	static final String END_TIMESTAMP_MS          = "endTimestampMs";
	/** 'startLocation'			*/	static final String START_LOCATION            = "startLocation";
	/** 'endLocatoin'			*/	static final String END_LOCATION              = "endLocation";
	/** 'distance' 				*/	static final String DISTANCE                  = "distance";
	/** 'placeId'				*/	static final String PLACE_ID                  = "placeId";
	/** 'name' 					*/	static final String NAME                      = "name";
	/** 'address'				*/	static final String ADDRESS                   = "address";
	/** 'sourceInfo'			*/	static final String SOURCE_INFO               = "sourceInfo";
	/** 'deviceTag' 			*/	static final String DEVICE_TAG                = "deviceTag";
	/** 'locationConfidence'	*/	static final String LOCATION_CONFIDENCE       = "locationConfidence";
	/** 'placeConfidence'		*/	static final String PLACE_CONFIDENCE          = "placeConfidence";
	/** 'centerLatE7' 			*/	static final String CENTER_LAT                = "centerLatE7";
	/** 'centerLngE7'			*/	static final String CENTER_LNG                = "centerLngE7";
	/** 'visitConfidence'		*/	static final String VISIT_CONFIDENCE          = "visitConfidence";
	/** 'otherCandidateLocations'*/	static final String OTHER_CANDIDATE_LOCATIONS = "otherCandidateLocations";
	/** 'editConfirmationStatus'*/	static final String EDIT_CONFIRMATION_STATUS  = "editConfirmationStatus";
	/** 'location' 				*/	static final String LOCATION                  = "location";
	/** 'activitySegment'		*/	static final String ACTIVITY_SEGMENT          = "activitySegment";
	/** 'placeVisit'			*/	static final String PLACE_VISIT               = "placeVisit";
	/** 'timelineObjects'		*/	static final String TIMELINE_OBJECTS          = "timelineObjects";
	/** 'simplifiedRawPath'		*/	static final String SIMPLIFIED_RAW_PATH       = "simplifiedRawPath";
	/** 'points'				*/	static final String POINTS                    = "points";
	/** 'accuracyMeters'		*/	static final String ACCURACY_METERS           = "accuracyMeters";
	/** 'transitPath'			*/	static final String TRANSIT_PATH              = "transitPath";
	/** 'transitStops'			*/	static final String TRANSIT_STOPS             = "transitStops";
	/** 'hexRgbColor'			*/	static final String HEX_RGB_COLOR             = "hexRgbColor";
	/** 'childVisits'			*/	static final String CHILD_VISITS              = "childVisits";
}
