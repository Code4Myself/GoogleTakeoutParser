package jp.utokyo.shibalab.googletakeoutparser.query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * convert JSON data of Google Location Logs into CSV data
 */
public class QueryLogParser {
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
			Event          event      = jsonMapper.readValue(inputFile,Event.class);
			List<Query>    list       = event.listQueries();
			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// export results /////////////////////////////
			bw.write("timestamp\tquery");
			bw.newLine();
			for(Query q:list) {
				System.out.println(q);
				for(TimeStamp time:q.listTimeStamp()) {
					bw.write(sdf.format(time.getTime()) + "\t" + q.getQueryText());
					bw.newLine();
				}
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
