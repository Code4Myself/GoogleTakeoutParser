package jp.utokyo.shibalab.googletakeoutparser.query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * convert JSON data of Google query logs into CSV (batch)
 * @deprecated
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
		File inputFile  = new File(args[0]);
		File outputFile = new File(args[1]);

		long t0 = System.currentTimeMillis();
		try (BufferedWriter bw=new BufferedWriter(new FileWriter(outputFile))) {
			// parse JSON data ////////////////////////////
			ObjectMapper   jsonMapper = new ObjectMapper();
			Events          event      = jsonMapper.readValue(inputFile,Events.class);
			List<Query>    list       = event.listQueries();
			
			// export results /////////////////////////////
			bw.write("id,querytext");
			bw.newLine();
			for(Query q:list) {
				bw.write(q.toCsvString());
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
