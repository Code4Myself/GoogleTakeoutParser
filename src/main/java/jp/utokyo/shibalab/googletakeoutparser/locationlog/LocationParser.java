package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * JSON Parser class for Location history of Google take out. 
 */
public class LocationParser {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** JSON key: timestampMs */
	public static final String KEY_TIMESTAMP  = "timestampMs";
	/** JSON key: latitudeE7 */
	public static final String KEY_LATITUDE   = "latitudeE7";
	/** JSON key: longitudeE7 */
	public static final String KEY_LONGITUDE  = "longitudeE7";
	/** JSON key: accuracy */
	public static final String KEY_ACCURACY   = "accuracy";
	/** JSON key: velocity */
	public static final String KEY_VELOCITY   = "velocity";
	/** JSON key: heading */
	public static final String KEY_HEADING    = "heading";
	/** JSON key: altitude */
	public static final String KEY_ALTITUDE   = "altitude";
	/** JSON key: activity */
	public static final String KEY_ACTIVITY   = "activity";
	/** JSON key: activitys 
	 * @deprecated */
	public static final String KEY_ACTIVITYS  = "activitys";
	/** JSON key: activities
	 * @deprecated */
	public static final String KEY_ACTIVITIES = "activities";
	/** JSON key: type */
	public static final String KEY_TYPE       = "type";
	/** JSON key: confidence */
	public static final String KEY_CONFIDENCE = "confidence";
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization 
	 */
	public LocationParser() { 
		// nothing to do 
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * destination method after parsing individual location data. <br>
	 * should be override this method to conduct original process. 
	 * @param location extracted location instance
	 */
	protected void doPostProcess(Location location) {
		System.out.println(location);
	}
	
	/**
	 * parse JSON object from the indicated JSON File
	 * @param inputJsonFile JSON file of location history obtained from Google take out 
	 * @throws IOException exception in parsing
	 */
	public void parseJson(File inputJsonFile) throws IOException {
		// conduct JSON parsing ////////////////////////////
		parseJson(inputJsonFile,null,null);
	}
	
	/**
	 * parse JSON object from the indicated JSON File
	 * @param inputJsonFile JSON file of location history obtained from Google take out 
	 * @param fromDate start time of time range. null allowable
	 * @param toDate end time of time range. null allowable
	 * @throws IOException exception in parsing
	 */
	public void parseJson(File inputJsonFile,Date fromDate,Date toDate) throws IOException {
		// prepare JSON parser instance ////////////////////
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser  jsonParser  = jsonFactory.createParser(inputJsonFile);

		// conduct JSON parsing ////////////////////////////
		parseLocations(jsonParser,fromDate,toDate);
	}
	
	/**
	 * parse JSON object from the indicated JSON stream
	 * @param jsonStream JSON input stream of location history obtained from Google take out 
	 * @throws IOException exception in parsing
	 */
	public void parseJson(InputStream jsonStream) throws IOException {
		// conduct JSON parsing ////////////////////////////
		parseJson(jsonStream,null,null);
	}
	
	/**
	 * parse JSON object from the indicated JSON stream
	 * @param jsonStream JSON input stream of location history obtained from Google take out 
	 * @param fromDate start time of time range. null allowable
	 * @param toDate end time of time range. null allowable
	 * @throws IOException exception in parsing
	 */
	public void parseJson(InputStream jsonStream,Date fromDate,Date toDate) throws IOException {
		// prepare JSON parser instance ////////////////////
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser  jsonParser  = jsonFactory.createParser(jsonStream);

		// conduct JSON parsing ////////////////////////////
		parseLocations(jsonParser,fromDate,toDate);
	}
	
	/**
	 * parse JSON object of location log data
	 * @param jsonParser JSON parser instance starting at root. 
	 * @param fromDate start time of time range. null allowable
	 * @param toDate end time of time range. null allowable
	 * @throws IOException exception in parsing
	 */
	private void parseLocations(JsonParser jsonParser,Date fromDate,Date toDate) throws IOException {
		// root object 
		if( jsonParser.nextToken() == JsonToken.START_OBJECT ) {
			// loop until the end of root object
			while( jsonParser.nextToken() != JsonToken.END_OBJECT ) {
				// locations: object array
				if( jsonParser.nextToken() == JsonToken.START_ARRAY ) {
					// loop until the end of object array
					while( jsonParser.nextToken() != JsonToken.END_ARRAY ) {
						// parse individual location object 
						if( jsonParser.getCurrentToken() == JsonToken.START_OBJECT ) {
							Long            timestampMs = null;
							Integer         longitudeE7 = null;
							Integer         latitudeE7  = null;
							Integer         accuracy    = null;
							Integer         velocity    = null;
							Integer         heading     = null;
							Integer         altitude    = null;
							List<Activitys> activitys   = null;
							while( jsonParser.nextToken() != JsonToken.END_OBJECT ) {
								// get JSON key ++++++++++++++++++++++++
								String locationKey = jsonParser.getCurrentName();
								// move to JSON value ++++++++++++++++++
								jsonParser.nextToken(); 
								// case "timestampMs"
								if( locationKey.equals(KEY_TIMESTAMP) ) {      timestampMs = jsonParser.getValueAsLong(); }
								// case "latitudeE7"
								else if ( locationKey.equals(KEY_LATITUDE) ) { latitudeE7  = jsonParser.getValueAsInt();  }
								// case "longitudeE7"
								else if( locationKey.equals(KEY_LONGITUDE) ) { longitudeE7 = jsonParser.getValueAsInt();  }
								// case "accuracy"
								else if( locationKey.equals(KEY_ACCURACY) ) {  accuracy    = jsonParser.getValueAsInt();  }
								// case "velocity"
								else if( locationKey.equals(KEY_VELOCITY) ) {  velocity    = jsonParser.getValueAsInt();  }
								// case "heading"
								else if( locationKey.equals(KEY_HEADING) )  {  heading     = jsonParser.getValueAsInt();  }
								// case "altitude"
								else if( locationKey.equals(KEY_ALTITUDE) ) {  altitude    = jsonParser.getValueAsInt();  }
								// case "activitys" or "activity"
								else if( locationKey.equals(KEY_ACTIVITYS) || locationKey.equals(KEY_ACTIVITY) ) { 
									activitys   = parseActivitys(jsonParser);  
								}
							}
							// check if object is within time range
							Location location = new Location(timestampMs,longitudeE7,latitudeE7,accuracy,velocity,heading,altitude,activitys);
							if( fromDate == null && toDate == null );
							else if( fromDate != null && toDate == null && timestampMs < fromDate.getTime() ) { location = null; }
							else if( fromDate == null && toDate != null && toDate.getTime() < timestampMs ) {   location = null; }
							else if( fromDate != null && toDate != null && (timestampMs < fromDate.getTime() || toDate.getTime() < timestampMs) ) { location = null; }
							
							if( location != null ) { 
								doPostProcess(location);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * parse JSON array in individual location object
	 * @param jsonParser JSON parser staring at array token of activity(<-activitys) object
	 * @return parsed activitys list {@link Activitys}
	 * @throws IOException exception in parsing
	 */
	private List<Activitys> parseActivitys(JsonParser jsonParser) throws IOException {
		// prepare return value ////////////////////////////
		List<Activitys> activitys = null;
		
		// beginning token type must be start-array //////// 
		if( jsonParser.getCurrentToken() != JsonToken.START_ARRAY ) {
			return activitys;
		}
		activitys = new ArrayList<Activitys>();
		// loop until the end to array /////////////////////
		while( jsonParser.nextToken() != JsonToken.END_ARRAY ) {
			// beginning of "activities" object ::::::::::::
			if( jsonParser.getCurrentToken() == JsonToken.START_OBJECT ) {
				// prepare object valuables ::::::::::::::::
				Long           timestampMs  = null;
				List<Activity> activityList = null; 
				// parse individual object :::::::::::::::::
				while( jsonParser.nextToken() != JsonToken.END_OBJECT ) {
					// get JSON key ++++++++++++++++++++++++
					String activitysKey = jsonParser.getCurrentName();
					// move to JSON value ++++++++++++++++++
					jsonParser.nextToken(); 
					// case "timestampMS"
					if( activitysKey.equals(KEY_TIMESTAMP) ) {
						timestampMs = jsonParser.getValueAsLong();
					}
					// case "activities" or "activity"
					if( activitysKey.equals(KEY_ACTIVITIES) || activitysKey.equals(KEY_ACTIVITY) ) {
						activityList = parseActivity(jsonParser);
					}
				}
				// create activitys instance 
				activitys.add(new Activitys(timestampMs,activityList));
			}
		}
		// return result ///////////////////////////////////
		return activitys;
	}
	
	/**
	 * parse JSON array in individual activity object
	 * @param jsonParser JSON parser starting at array token of activity object 
	 * @return parsed activity list {@link Activity}
	 * @throws IOException exception in parsing
	 */
	private List<Activity> parseActivity(JsonParser jsonParser) throws IOException {
		// prepare return value ////////////////////////////
		List<Activity> activityList = null;
		
		// beginning token type must be start-array //////// 
		if( jsonParser.getCurrentToken() != JsonToken.START_ARRAY ) {
			return activityList;
		}
		activityList = new ArrayList<Activity>();
		// loop until the end to array /////////////////////
		while( jsonParser.nextToken() != JsonToken.END_ARRAY ) {
			// beginning of object :::::::::::::::::::::::::
			if( jsonParser.getCurrentToken() == JsonToken.START_OBJECT ) {
				// prepare object valuables ::::::::::::::::
				String  type       = null;
				Integer confidence = null;
				while( jsonParser.nextToken() != JsonToken.END_OBJECT ) {
					// get JSON key ++++++++++++++++++++++++
					String activityKey = jsonParser.getCurrentName();
					// move to JSON value ++++++++++++++++++
					jsonParser.nextToken(); 
					
					// case "type"
					if( activityKey.equals(KEY_TYPE) ) {
						type = jsonParser.getValueAsString();
					}
					// case "confidence"
					else if( activityKey.equals(KEY_CONFIDENCE) ) {
						confidence = jsonParser.getValueAsInt();
					}
				}
				// create activity instance 
				activityList.add(new Activity(type,confidence));
			}
		}
		// return result ///////////////////////////////////
		return activityList;
	}
}
