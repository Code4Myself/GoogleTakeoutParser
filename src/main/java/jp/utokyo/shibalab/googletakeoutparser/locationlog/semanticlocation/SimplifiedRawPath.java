package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.utokyo.shibalab.googletakeoutparser.locationlog.JsonKeys;

/**
 * class for simplified Raw path 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SimplifiedRawPath {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** simplified raw path points */
	@JsonProperty(JsonKeys.POINTS)
	private List<Point> _points;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get 'points'
	 * @return point list
	 */
	public List<Point> getPoints( ) {
		return _points;
	}
	
	

	/* ==============================================================
	 * inner classes
	 * ============================================================== */
	/**
	 * class for point (in simplified raw path)
	 */
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class Point extends LonLat {
		/* ==============================================================
		 * instance fields
		 * ============================================================== */
		/** time-stamp  */
		private Date _timestamp;
		
		/** accuracy in meters */
		private int  _accuracyMeters;
		
		
		/* ==============================================================
		 * constructors
		 * ============================================================== */
		/**
		 * initialization 
		 * @param latE7 latitude 
		 * @param lngE7 longitude
		 * @param timestampMs time-stamp in milliseconds
		 * @param accuracyMeters accuracy in meters
		 */
		@JsonCreator
		private Point(	@JsonProperty(JsonKeys.LAT) Long latE7, 
						@JsonProperty(JsonKeys.LNG) Long lngE7, 
						@JsonProperty(JsonKeys.TIMESTAMP) Long timestampMs, 
						@JsonProperty(JsonKeys.ACCURACY_METERS) Integer accuracyMeters)
		{
			super(
					lngE7 != null ? lngE7 / 1.0e7 : Double.NaN, 
					latE7 != null ? latE7 / 1.0e7 : Double.NaN
				);
			
			_timestamp      = timestampMs != null ? new Date(timestampMs) : null;
			_accuracyMeters = accuracyMeters != null ? accuracyMeters : 0;
		}
		
		
		/* ==============================================================
		 * instance methods
		 * ============================================================== */
		/**
		 * get time-stamp 
		 * @return time-stamp 
		 */
		public Date getTimestamp() {
			return _timestamp;
		}
		
		/**
		 * get accuracy in meters
		 * @return accuracy in meters
		 */
		public int getAccuracyMeters() {
			return _accuracyMeters;
		}
	}
}
