package jp.utokyo.shibalab.googletakeoutparser.photo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * convert JSON data of Google photo data from takeout
 */
public class PhotoParser {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * entry point
	 * @param args 0:input directory containing JSON file, 1:output CSV(TAB separated) file
	 */
	public static void main(String[] args) {
		File inputDir   = new File(args[0]);
		File outputFile = new File(args[1]);
		
		File[] jsonFiles = inputDir.listFiles(new FileFilter() {
			public boolean accept(File f) { 
				return f.getName().endsWith(".json") && !f.getName().equals("メタデータ.json");
			}
		});
		
		try (BufferedWriter bw=new BufferedWriter(new FileWriter(outputFile))) {
			// export results /////////////////////////////
			bw.write("title\tctime\tmtime");
			bw.newLine();
			
			for(File jsonFile:jsonFiles) { 
				// parse JSON data ////////////////////////////
				ObjectMapper   jsonMapper = new ObjectMapper();
				Photo          photo      = jsonMapper.readValue(jsonFile,Photo.class);
				
				bw.write(String.format("%s\t%s\t%s\t%s",photo.getTitle(),photo.getCreationTime(),photo.getModificatoinTime(),photo.getGeoInfo()));
				bw.newLine();
			}
			
			// parse JSON data ////////////////////////////
			ObjectMapper   jsonMapper = new ObjectMapper();
			Metadata       metadata   = jsonMapper.readValue(new File(inputDir,"メタデータ.json"),Metadata.class);
			System.out.println(metadata.getAlbumData().getTitle());
			System.out.println(metadata.getAlbumData().getDate());
			
		}
		catch(JsonMappingException|JsonParseException exp) {
			exp.printStackTrace();
		}
		catch(IOException exp) { 
			exp.printStackTrace();
		}
	}
	
}
