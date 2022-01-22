package jp.utokyo.shibalab.googletakeoutparser.locationlog.semanticlocation;

/**
 * class for longitude/latitude
 */
public class LonLat {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** latitude */
	private double _lat;
	
	/** longitude */
	private double _lng;

	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 * @param lon
	 * @param lat
	 */
	public LonLat(double lon, double lat) {
		_lat = lat;
		_lng = lon;
	}
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get longitude
	 * @return longitude in degree
	 */
	public double getLng() {
		return _lng;
	}
	
	/**
	 * get latitude
	 * @return latitude in degree
	 */
	public double getLat() {
		return _lat;
	}

}
