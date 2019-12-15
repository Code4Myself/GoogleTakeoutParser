package jp.utokyo.shibalab.googletakeoutparser.photo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for JSON data of Google Photo 
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class Photo {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** tag list */
	@JsonProperty("tags")
	private List<String> _tags;
	/** description */
	@JsonProperty("description")
	private String       _description;
	/** title */
	@JsonProperty("title")
	private String       _title;
	/** people */
	@JsonProperty("people")
	private List<String> _people;
	/** creation time */
	@JsonProperty("creationTime")
	private Long         _creationTime;
	/** modification time */
	@JsonProperty("modificationTime")
	private Long         _modificationTime;
	/** comment */
	@JsonProperty("comments")
	private List<String> _comments;
	/** URL */
	@JsonProperty("url")
	private String       _url;
	/** geo information */
	@JsonProperty("geoInfo")
	private GeoInfo      _geoInfo;
	/** Geo information of EXIF */
	@JsonProperty("geoInfoExif")
	private GeoInfo      _geoInfoExif;
	/** image views */
	@JsonProperty("imageViews")
	private Integer      _imageViews;
	/** license */
	@JsonProperty("license")
	private Object       _license;
	/** locale */
	@JsonProperty("locale")
	private String       _locale;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get tag list
	 * @return tag list
	 */
	public List<String> listTags() {
		return _tags;
	}
	
	/**
	 * get description 
	 * @return description
	 */
	public String getDescription() {
		return _description;
	}
	
	/**
	 * get title 
	 * @return title
	 */
	public String getTitle() {
		 return _title;
	}
	
	/**
	 * get people list. TODO check detail
	 * @return people list
	 */
	public List<String> listPeople() {
		return _people;
	}
	
	/**
	 * get creation time 
	 * @return creation time
	 */
	public Date getCreationTime() {
		return _creationTime != null ? new Date(_creationTime * 1000) : null;
	}
	
	/**
	 * get modification time
	 * @return modification time. 
	 */
	public Date getModificatoinTime() {
		return _modificationTime != null ? new Date(_modificationTime * 1000) : null;
	}
	
	/**
	 * get comment list
	 * @return comment list
	 */
	public List<String> listComments() {
		return _comments;
	}
	
	/**
	 * get image URL 
	 * @return image URL.
	 */
	public URL getUrl() {
		URL url = null;
		try {
			url = _url != null ? new URL(_url) : null;
		}
		catch(MalformedURLException exp) { 
			exp.printStackTrace();
		}
		return url;
	}
	
	/**
	 * get Geo Information
	 * @return Geo Information
	 */
	public GeoInfo getGeoInfo() {
		return _geoInfo;
	}
	/**
	 * get Geo information from Exif
	 * @return geo information from Exif
	 */
	public GeoInfo getGeoInfoExif() {
		return _geoInfoExif;
	}
	
	/**
	 * get image views TODO check detail
	 * @return image views
	 */
	public int getImageViews() {
		return _imageViews;
	}
	
	/**
	 * get license. TODO check detail
	 * @return license
	 */
	public Object getLicense() {
		return _license;
	}
	
	/**
	 * get locale (e.g. ja)
	 * @return locale
	 */
	public String getLocale() {
		return _locale;
	}
}
