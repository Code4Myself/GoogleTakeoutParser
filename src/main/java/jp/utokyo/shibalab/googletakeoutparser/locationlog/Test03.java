package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


/**
 * convert JSON data of Google Location Logs into CSV data(stream input)
 */
public class Test03 {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * entry point
	 * @param args 0:input JSON file, 1:output csv file, 2:from date, 3:to date
	 */
	public static void main(String[] args) {
		Path inputPath  = Paths.get(args[0]);
		Path outputPath = Paths.get(args[1]);
		
		Date fromDate = args.length >= 3 ? parseDate(args[2]) : null;
		Date toDate   = args.length >= 4 ? parseDate(args[3]) : null;
		
		long t0 = System.currentTimeMillis();
		try (BufferedWriter bw=Files.newBufferedWriter(outputPath)) {
			// export header ///////////////////////////////
			bw.write("timestamp,longitudde,latitude,accuracy,velocity,heading,altitude,activities");
			bw.newLine();

			// parse JSON data ////////////////////////////
			LocationParser parser = new LocationParser() {
				public void doPostProcess(Location location) { 
					try {
						bw.write(location.toCsvString());
						bw.newLine();
					}
					catch(IOException exp) { 
						exp.printStackTrace();
					}
				}
			};
			parser.parseJson(inputPath.toFile(),fromDate,toDate);
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
	
	/**
	 * parse date string into date instance
	 * @param string date string
	 * @return date instance, if successfully parsed
	 */
	private static Date parseDate(String string) {
		Date date   = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = string != null ? sdf.parse(string) : null; 
		}
		catch(ParseException exp) { 
			exp.printStackTrace();
			date = null;
		}
		
		return date;
	}
}
