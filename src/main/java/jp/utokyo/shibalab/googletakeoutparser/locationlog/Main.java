package jp.utokyo.shibalab.googletakeoutparser.locationlog;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * convert JSON data of Google Location Logs into CSV data(stream input)
 */
public class Main {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** command line options */
	private static Options CLI_OPTIONS = new Options();
	static {
		CLI_OPTIONS.addOption("h", "help",          false, "view command line help" );
		CLI_OPTIONS.addOption("i", "in-file",       true,  "input file: location_history.json"   );
		CLI_OPTIONS.addOption("o", "out-file"     , true,  "output file: location_history.csv"  );
		CLI_OPTIONS.addOption("s", "start-date",    true,  "(Optional) start date (yyyyMMdd). convert from the beginning if not specified"  );
		CLI_OPTIONS.addOption("e", "end-date",      true,  "(Optional) end date (yyyyMMdd). convert to the end if not specified");
	}

	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * entry point
	 * @param args
	 */
	public static void main(String ... args) {
		// parameters
		File inputJsonFile  = null; // location_history.json
		File outputCsvFile  = null; // location_history.csv
		Date startDate      = null; // optional
		Date endDate        = null; // optional

		// parse command line options
		DefaultParser optParser = new DefaultParser();
		try {
			CommandLine cli = optParser.parse(CLI_OPTIONS, args);

			// view help
			if( cli.hasOption("h") ) {
				new HelpFormatter().printHelp("Option Tips", CLI_OPTIONS);
				System.exit(1);
			}

			// extract command line options
			String  inputJsonPath = cli.getOptionValue("in-file");
			String  outputCsvPath = cli.getOptionValue("out-file");
			String  startDateStr  = cli.getOptionValue("start-date");
			String  endDateStr    = cli.getOptionValue("end-date");

			// check command line options
			if( StringUtils.isBlank(inputJsonPath) || StringUtils.isBlank(outputCsvPath) ) {
				System.err.println("[ERROR] -i(--in-file) and -m(--out-file) must be specified");
				System.exit(2);
			}

			// extract start date
			try {
				if( StringUtils.isNotBlank( startDateStr) ) {
					startDate = new SimpleDateFormat("yyyyMMdd").parse(startDateStr);
				}
			}
			catch(ParseException | NullPointerException exp ) {
				System.err.println("[ERROR] -s(start-date) must be in 'yyyyMMdd' format");
				System.exit(3);
			}

			// extract end date
			try {
				if( StringUtils.isNotBlank(endDateStr) ) {
					endDate = new SimpleDateFormat("yyyyMMdd").parse(endDateStr);
				}
			}
			catch(ParseException | NullPointerException exp ) {
				System.err.println("[ERROR] -e(end-date) must be in 'yyyyMMdd' format");
				System.exit(4);
			}

			inputJsonFile = new File(inputJsonPath);
			outputCsvFile = new File(outputCsvPath);
		}
		catch(org.apache.commons.cli.ParseException exp) {
			exp.printStackTrace();
		}

		// do main process
		doMain(inputJsonFile, outputCsvFile, startDate, endDate);
	}

	/**
	 * main process
	 * @param inputJsonFile location_history.json, not null
	 * @param outputCsvFile location_history.csv, not null
	 * @param startDate start date, null allowable
	 * @param endDate end date, null allowable
	 */
	private static void doMain(File inputJsonFile, File outputCsvFile, Date startDate, Date endDate) {
		// open output csv file
		try (BufferedWriter bw=Files.newBufferedWriter(outputCsvFile.toPath())) {
			// export header
			bw.write(Location.getCsvHeader());
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
			parser.parseJson(inputJsonFile, startDate, endDate);
		}
		catch(JsonMappingException|JsonParseException exp) {
			exp.printStackTrace();
		}
		catch(IOException exp) { 
			exp.printStackTrace();
		}
	}
}
