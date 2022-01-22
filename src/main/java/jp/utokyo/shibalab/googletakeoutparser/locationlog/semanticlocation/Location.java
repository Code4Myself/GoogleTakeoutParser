package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

/**
 * class for location 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Location extends LonLat {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** place ID */
	private String _placeId;
	
	/** address */
	private String _address;
	
	/** place name */
	private String _name;
	
	/** source info */
	private SourceInfo _sourceInfo;
	
	/** location confidence */
	private double _locationConfidence;
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param latE7 latitude 
	 * @param lngE7 longitude 
	 * @param placeId place ID
	 * @param address address 
	 * @param name place name
	 * @param sourceInfo source info
	 * @param locationConfidence location confidence 
	 */
	@JsonCreator
	private Location( @JsonProperty(JsonKeys.LATITUDE)    Long latE7,
			 		  @JsonProperty(JsonKeys.LONGITUDE)   Long lngE7, 
			 		  @JsonProperty(JsonKeys.PLACE_ID)    String placeId, 
			 		  @JsonProperty(JsonKeys.ADDRESS)     String address, 
			 		  @JsonProperty(JsonKeys.NAME)        String name,
			 		  @JsonProperty(JsonKeys.SOURCE_INFO) SourceInfo sourceInfo, 
			 		  @JsonProperty(JsonKeys.LOCATION_CONFIDENCE) Double locationConfidence)
	{
		super(
			lngE7 != null ? lngE7 / 1.0e7 : Double.NaN, 
			latE7 != null ? latE7 / 1.0e7 : Double.NaN
		);

		_placeId    = placeId;
		_address    = address;
		_name       = name;
		_sourceInfo = sourceInfo;
		_locationConfidence = locationConfidence == null ? Double.NaN : locationConfidence;
	}

	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get place Id
	 * @return place id
	 */
	public String getPlaceId() {
		return _placeId;
	}
	
	/**
	 * get address 
	 * @return address 
	 */
	public String getAddress() {
		return _address;
	}
	
	/**
	 * get place name
	 * @return place name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * get source info  
	 * @return source info
	 */
	public SourceInfo getSourceInfo() {
		return _sourceInfo;
	}
	
	/**
	 * get location confidence
	 * @return location confidence 
	 */
	public double getLocationConfidence() {
		return _locationConfidence;
	}
	
	@Override
	public String toString() {
		String[] strs = new String[] {
			getPlaceId(), 
			getAddress(), 
			getName(), 
			String.valueOf(getLng()), 
			String.valueOf(getLat())
		};
		return StringUtils.join(strs, "\t");
	}

	/* ==============================================================
	 * inner classes
	 * ============================================================== */
	/**
	 * class for source info
	 */
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class SourceInfo {
		/* ==============================================================
		 * instance fields
		 * ============================================================== */
		/** device tag */
		private long _deviceTag;
		
		
		/* ==============================================================
		 * constructors
		 * ============================================================== */
		/**
		 * initialization 
		 * @param deviceTag device tag
		 */
		@JsonCreator
		private SourceInfo(@JsonProperty("deviceTag") Long deviceTag) {
			_deviceTag = deviceTag;
		}
		
		
		/* ==============================================================
		 * instance methods
		 * ============================================================== */
		/**
		 * get device tag
		 * @return device tag
		 */
		public long getDeviceTag() {
			return _deviceTag;
		}
	}
}
