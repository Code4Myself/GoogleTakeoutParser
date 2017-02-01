package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * convert JSON data of Google Location Logs into CSV data
 */
public class LocationLogParser {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * entry point
	 * @param args 0:input JSON file, 1:output csv file
	 */
	public static void main(String[] args) {
		File inputFile  = new File(args[0]);
		File outputFile = new File(args[1]);
		
		try (BufferedWriter bw=new BufferedWriter(new FileWriter(outputFile))) {
			// parse JSON data ////////////////////////////
			ObjectMapper   jsonMapper = new ObjectMapper();
			Locations      locs       = jsonMapper.readValue(inputFile,Locations.class);
			List<Location> list       = locs.listLocations();
			Collections.sort(list);
			
			// export results /////////////////////////////
			bw.write("timestamp,lon,lat,acc,activities");
			bw.newLine();
			for(Location loc:locs.listLocations()) { 
				bw.write(loc.toCsvString());
				bw.newLine();
			}
		}
		catch(JsonMappingException|JsonParseException exp) {
			exp.printStackTrace();
		}
		catch(IOException exp) { 
			exp.printStackTrace();
		}
	}
	
}
