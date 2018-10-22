package jp.utokyo.shibalab.googletakeoutparser.photo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for Geo information
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class GeoInfo {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** latitude */
	@JsonProperty("latitude_")
	private Double  _latitude;
	/** longitude */
	@JsonProperty("longitude_")
	private Double  _longitude;
	/** altitude */
	@JsonProperty("altitude_")
	private Double  _altitude;
	/** latitude span */
	@JsonProperty("latitude_span_")
	private Double  _latitudeSpan;
	/** longitude span */
	@JsonProperty("longitude_span_")
	private Double  _longitudeSpan;
	/** uninterpreted TODO check detail */
	@JsonProperty("uninterpreted")
	private String  _uninterpreted;
	/** optional 0. TODO check detail */
	@JsonProperty("optional_0_")
	private Integer _optional0;
	/** mutable flag */
	@JsonProperty("isMutable")
	private Boolean _isMutable;
	/** cached size flag(?) TODO check detail */
	@JsonProperty("cachedSize")
	private Integer _cachedSize;
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get latitude
	 * @return latitude 
	 */
	public double getLatitude() {
		return _latitude;
	}
	
	/**
	 * get longitude
	 * @return longitude
	 */
	public double getLongitude() {
		return _longitude;
	}
	
	/**
	 * get altitude
	 * @return altitude
	 */
	public double getAltitude() {
		return _altitude;
	}
	
	/**
	 * get latitude span
	 * @return latitude span
	 */
	public double getLatitudeSpan() {
		return _latitudeSpan;
	}
	
	/**
	 * get longitude span 
	 * @return longitude span
	 */
	public double getLongitudeSpan() {
		return _longitudeSpan;
	}
	
	/**
	 * get uninterpreted (?) TODO check detail
	 * @return uninterpreted
	 */
	public String getUninterpreted() {
		return _uninterpreted;
	}
	
	/**
	 * get optional 0. TODO check detail
	 * @return optional 0
	 */
	public int getOptional0() {
		return _optional0 == null ? 0 : _optional0; 
	}
	
	/**
	 * check if mutable
	 * @return result
	 */
	public boolean isMutable() {
		return _isMutable;
	}
	
	/**
	 * get cached size flag (?) TODO check detail
	 * @return cached size 
	 */
	public int getChachedSize() {
		return _cachedSize == null ? 0 : _cachedSize;
	}
}
