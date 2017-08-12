package jp.utokyo.shibalab.googletakeoutparser.query;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * JSON Parser class for query history of Google take out. 
 */
public class QueryParser {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** JSON key: event          */
	public static final String KEY_EVENT      = "event";
	/** JSON key: query          */
	public static final String KEY_QUERY      = "query";
	/** JSON key: id             */
	public static final String KEY_ID         = "id";
	/** JSON key: timestamp_usec */
	public static final String KEY_TIMESTAMP  = "timestamp_usec";
	/** JSON key: query_tet      */
	public static final String KEY_QUERY_TEXT = "query_text";

	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization 
	 */
	public QueryParser() {
		// nothing to do 
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * destination method after parsing individual query data. <br>
	 * should be override this method to conduct original process. 
	 * @param query extracted query instance
	 */
	protected void doPostProcess(Query query) {
		System.out.println(query);
	}
	
	/**
	 * parse JSON object from the indicated JSON File
	 * @param inputJsonFile JSON file of query history obtained from Google take out 
	 * @throws IOException exception in parsing
	 */
	public void parseJson(File inputJsonFile) throws IOException {
		// prepare JSON parser instance ////////////////////
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser  jsonParser  = jsonFactory.createParser(inputJsonFile);

		// conduct JSON parsing ////////////////////////////
		parseQuery(jsonParser);
	}
	
	/**
	 * parse JSON object from the indicated JSON stream
	 * @param jsonStream JSON input stream of query history obtained from Google take out 
	 * @throws IOException exception in parsing
	 */
	public void parseJson(InputStream jsonStream) throws IOException {
		// prepare JSON parser instance ////////////////////
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser  jsonParser  = jsonFactory.createParser(jsonStream);

		// conduct JSON parsing ////////////////////////////
		parseQuery(jsonParser);
	}
	
	/**
	 * parse JSON object of take out data
	 * @param jsonParser JSON parser instance starting at root. 
	 * @throws IOException exception in parsing
	 */
	private void parseQuery(JsonParser jsonParser) throws IOException {
		// root object 
		if( jsonParser.nextToken() == JsonToken.START_OBJECT ) {
			// loop until the end of root object
			while( jsonParser.nextToken() != JsonToken.END_OBJECT ) {
				// event: object array
				if( jsonParser.nextToken() == JsonToken.START_ARRAY ) {
					// loop until the end of object array
					while( jsonParser.nextToken() != JsonToken.END_ARRAY ) {
						// parse individual query object 
						if( jsonParser.getCurrentToken() == JsonToken.START_OBJECT ) {
							// get JSON key ++++++++++++++++++++++++
							List<ID> ids       = null;
							// move to JSON value ++++++++++++++++++
							String   queryText = null;
							while( jsonParser.nextToken() != JsonToken.END_OBJECT ) {
								String key = jsonParser.getCurrentName(); 
								jsonParser.nextToken(); 
								// case "id"
								if( key.equals(KEY_ID) ) { 
									ids = parseIDs(jsonParser);
								}
								// case "query_text"
								else if ( key.equals(KEY_QUERY_TEXT) ) {
									queryText = jsonParser.getValueAsString(); 
								}
							}
							Query query = new Query(ids,queryText);
							doPostProcess(query);
						}	
					}
				}
			}
		}
	}
	

	/**
	 * parse JSON array in individual query object 
	 * @param jsonParser JSON parser starting at "id" array token 
	 * @return parsed id list {@link ID}
	 * @throws IOException exception in parsing
	 */
	private List<ID> parseIDs(JsonParser jsonParser) throws IOException { 
		// prepare return value ////////////////////////////
		List<ID> idList = null;
		
		// beginning token type must be start-array //////// 
		if( jsonParser.getCurrentToken() != JsonToken.START_ARRAY ) {
			return idList;
		}
		idList = new ArrayList<ID>();
		// loop until the end to array /////////////////////
		while( jsonParser.nextToken() != JsonToken.END_ARRAY ) {
			// beginning of object :::::::::::::::::::::::::
			if( jsonParser.getCurrentToken() == JsonToken.START_OBJECT ) {
				// prepare object valuables ::::::::::::::::
				Long  timestamp  = null;
				while( jsonParser.nextToken() != JsonToken.END_OBJECT ) {
					// get JSON key ++++++++++++++++++++++++
					String idkey = jsonParser.getCurrentName();
					// move to JSON value ++++++++++++++++++
					jsonParser.nextToken(); 
					
					// case "timestamp_usec"
					if( idkey.equals(KEY_TIMESTAMP) ) {
						timestamp = jsonParser.getValueAsLong();
					}
				}
				// create activity instance 
				idList.add(new ID(timestamp));
			}
		}
		// return result ///////////////////////////////////
		return idList;
	}
}
