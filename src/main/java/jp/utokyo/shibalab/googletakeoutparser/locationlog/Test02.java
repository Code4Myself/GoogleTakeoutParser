package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * convert JSON data of Google Location Logs into CSV data(stream input)
 */
public class Test02 {
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

		long t0 = System.currentTimeMillis();
		try (final BufferedWriter bw=new BufferedWriter(new FileWriter(outputFile))) {
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
			parser.parseJson(inputFile);
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
