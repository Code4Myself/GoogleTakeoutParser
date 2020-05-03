package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

public class TimelineObjectsParser {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * main 
	 * @param args parameters
	 * @throws IOException exception 
	 */
	public static void main(String ...args) throws IOException {
		File inputFile = new File(args[0]);
		
		new TimelineObjectsParser().parseJson(inputFile);
	}
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization 
	 */
	public TimelineObjectsParser() { 
		// nothing to do 
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */	
	/**
	 * parse JSON object from the indicated JSON File
	 * @param inputJsonFile JSON file of time-line data obtained from Google take out 
	 * @throws IOException exception in parsing
	 */
	public void parseJson(File inputJsonFile) throws IOException {
		// conduct JSON parsing ////////////////////////////
		parseJson(inputJsonFile, null, null);
	}
	
	/**
	 * parse JSON object from the indicated JSON File
	 * @param inputJsonFile JSON file of time-line data obtained from Google take out 
	 * @param fromDate start time of time range. null allowable
	 * @param toDate end time of time range. null allowable
	 * @throws IOException exception in parsing
	 */
	public void parseJson(File inputJsonFile, Date fromDate, Date toDate) throws IOException {
		// load JSON file and get node tree ////////////////
		ObjectMapper mapper   = new ObjectMapper();
		JsonNode     jsonNode = mapper.readTree(inputJsonFile);
		
		// conduct JSON parsing ////////////////////////////
		JsonNode root = jsonNode.path(JsonKeys.TIMELINE_OBJECTS);
		if( root.isArray() ) {
			Iterator<JsonNode> itr = root.iterator();
			while( itr.hasNext() ) {
				JsonNode item = itr.next();
				// process activity segment ::::::::::::::::
				if( item.has(JsonKeys.ACTIVITY_SEGMENT) ) {
					String          json    = item.path(JsonKeys.ACTIVITY_SEGMENT).toPrettyString(); 
					ActivitySegment segment = mapper.readValue(json, ActivitySegment.class);
					if( intersects(segment.getDuration(), fromDate, toDate) ) {
						processActivitySegment(segment);
					}
				}
				// process place visit :::::::::::::::::::::
				else if( item.has(JsonKeys.PLACE_VISIT) ) {
					String     json  = item.path(JsonKeys.PLACE_VISIT).toPrettyString(); 
					PlaceVisit visit = mapper.readValue(json, PlaceVisit.class);
					if( intersects(visit.getDuration(), fromDate, toDate) ) {
						processPlaceVisit(visit);
					}
				}
				// other cases
				else {
					// invalid values
				}
			}
		}
	}

	/**
	 * check time intersection 
	 * @param duration time duration of instance
	 * @param fromDate from time restriction
	 * @param toDate to time restriction
	 * @return result
	 */
	private boolean intersects(Duration duration, Date fromDate, Date toDate) {
		long tsUnixtime0 = duration.getStartTimestamp().getTime();
		long teUnixtime0 = duration.getEndTimestamp().getTime();
		long tsUnixtime1 = fromDate != null ? fromDate.getTime() : 0L;
		long teUnixtime1 = toDate != null   ? toDate.getTime()   : 0L;
		
		if( fromDate != null && toDate != null ) { 
			return (tsUnixtime0 <= tsUnixtime1 && tsUnixtime1 <= teUnixtime0) ||
				   (tsUnixtime0 <= teUnixtime1 && teUnixtime1 <= teUnixtime0);
		}
		else if( fromDate != null ) {
			return tsUnixtime1 <= teUnixtime0;
		}
		else if( toDate != null ) { 
			return tsUnixtime0 <= teUnixtime1;
		}
		else {
			return true;
		}
	}

	/**
	 * main process of activity segment
	 * @param segment activity segment
	 */
	protected void processActivitySegment(ActivitySegment segment) {
		System.out.println("activity----");
		System.out.println("\t" + segment.getStartLocation());
		System.out.println("\t" + segment.getEndLocation());
		System.out.println("\t" + segment.getActivityType());
	}
	
	/**
	 * main process of place visit 
	 * @param visit place visit 
	 */
	protected void processPlaceVisit(PlaceVisit visit) {
		System.out.println("visit----");
		System.out.println("\t" + visit.getLocation());
	}
}
