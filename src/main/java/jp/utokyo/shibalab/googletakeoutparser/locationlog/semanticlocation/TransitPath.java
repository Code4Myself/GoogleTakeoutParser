package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

/**
 * class for transit path 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransitPath {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** transit stop list */
	@JsonProperty(JsonKeys.TRANSIT_STOPS)
	private List<TransitStop> _transitStops;
	
	/** name */
	@JsonProperty("name")
	private String _name;
	
	/** hex rgb color */	
	@JsonProperty("hexRgbColor")
	private String _color; 
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get 'transitStops'
	 * @return transit stops
	 */
	public List<TransitStop> getTransitStops( ) {
		return _transitStops;
	}

	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * get color 
	 * @return coloring string
	 */
	public String getColor() { 
		return _color;
	}
	

	/* ==============================================================
	 * inner classes
	 * ============================================================== */
	/**
	 * class for transit stop point (in transit path)
	 */
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class TransitStop extends LonLat {
		/* ==============================================================
		 * instance fields
		 * ============================================================== */
		/** name  */
		private String _name;
		
		/** place ID */
		private String _placeId;
		
		
		/* ==============================================================
		 * constructors
		 * ============================================================== */
		/**
		 * initialization 
		 * @param latE7 latitude 
		 * @param lngE7 longitude
		 * @param name name 
		 * @param placeId place ID
		 */
		@JsonCreator
		private TransitStop(@JsonProperty(JsonKeys.LATITUDE)  Long  latE7, 
							@JsonProperty(JsonKeys.LONGITUDE) Long  lngE7, 
							@JsonProperty(JsonKeys.NAME)      String name,  
							@JsonProperty(JsonKeys.PLACE_ID)  String placeId)
		{
			super(
					lngE7 != null ? lngE7 / 1.0e7 : Double.NaN, 
					latE7 != null ? latE7 / 1.0e7 : Double.NaN
				);
			
			_name    = name;
			_placeId = placeId;
		}
		
		
		/* ==============================================================
		 * instance methods
		 * ============================================================== */
		/**
		 * get name 
		 * @return name
		 */
		public String getName() {
			return _name;
		}
		
		/**
		 * get place Id
		 * @return place Id
		 */
		public String getPlaceId() {
			return _placeId;
		}
	}
}
