package jp.utokyo.shibalab.googletakeoutparser.photo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class AlbumData {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** title */
	@JsonProperty("title")
	private String _title;
	/** date */
	@JsonProperty("date")
	private String _date;
	/** access */
	@JsonProperty("access")
	private String _access;
	/** description */
	@JsonProperty("description")
	private String _description;
	/** location */
	@JsonProperty("location")
	private String _location;
	/** Geo Information */
	@JsonProperty("geoInfo")
	private GeoInfo _geoInfo;

	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get title
	 * @return title
	 */
	public String getTitle() {
		return _title;
	}
	
	/**
	 * get date
	 * @return date
	 */
	public Date getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");	// TODO modify timestamp format
		Date date = null;
		try{
			date = sdf.parse(_date);
		}
		catch(ParseException exp) { 
			exp.printStackTrace();
		}
		return date;
	}
	
	/**
	 * get access (e.g. protected)
	 * @return access 
	 */
	public String getAccess() {
		return _access;
	}
	
	/**
	 * get description 
	 * @return description 
	 */
	public String getDescription() {
		return _description; 
	}
	
	/**
	 * get location 
	 * @return location 
	 */
	public String getLocation() {
		return _location;
	}
	
	/**
	 * get geo information of this album
	 * @return geo information
	 */
	public GeoInfo getGeoInfo() {
		return _geoInfo;
	}
}
