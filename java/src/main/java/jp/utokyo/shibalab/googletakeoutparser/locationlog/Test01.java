package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * convert JSON data of Google Location Logs into CSV data(batch process) 
 */
public class Test01 {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * entry point
	 * @param args 0:input JSON file, 1:output csv file
	 */
	public static void main(String[] args) {
		Path inputPath  = Paths.get(args[0]);
		Path outputPath = Paths.get(args[1]); 
		
		long t0 = System.currentTimeMillis();
		try (BufferedWriter bw=Files.newBufferedWriter(outputPath)) {
			// parse JSON data ////////////////////////////
			ObjectMapper   jsonMapper = new ObjectMapper();
			Locations      locs       = jsonMapper.readValue(inputPath.toFile(),Locations.class);
			List<Location> list       = locs.listLocations();
			Collections.sort(list);
			
			// export header ///////////////////////////////
			bw.write(Location.getCsvHeader());
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
		long t1 = System.currentTimeMillis();
		System.out.printf("time duration: %.03f (sec) ", (t1-t0)/1000d);
	}
	
}
