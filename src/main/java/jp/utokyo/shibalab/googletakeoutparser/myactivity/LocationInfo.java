package jp.utokyo.shibalab.googletakeoutparser.myactivity;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * LocationInfo Object class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationInfo {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** name */
	private String _name;
		
	/** URL */
	private String _url;
	
	/** source */
	private String _source;

	/** sourceUrl */
	private String   _sourceUrl;
		
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	@JsonCreator
	private LocationInfo(
		@JsonProperty("name")      String name,
		@JsonProperty("url")       String url,
		@JsonProperty("source")    String source,
		@JsonProperty("sourceUrl") String sourceUrl
	) 
	{
		_name      = name;
		_url       = url;
		_source    = source;
		_sourceUrl = sourceUrl;
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get name
	 * @return get name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * get URL
	 * @return URL
	 */
	public String getUrl() {
		return _url;
	}
	
	/**
	 * get data source 
	 * @return data source
	 */
	public String getSource() {
		return _source;
	}
	
	/**
	 * get data source URL
	 * @return data source URL
	 */
	public String getSourceUrl() {
		return _sourceUrl;
	}
	
	/**
	 * get CSV string with the indicated delimiter
	 * @param delimiter delimiter string
	 * @return CSV string
	 */
	public String toCsvString(String delimiter) {
		String url = getUrl();
		try {
			// decode percent encoding
			url = new URLCodec().decode(getUrl());
		}
		catch(DecoderException exp) {
			exp.printStackTrace();
		}
		
		String[] tokens = new String[] { 
			getName(),
			url,
			getSource(),
			getSourceUrl()
		};
		
		return StringUtils.join(tokens,delimiter);
	}
	
	@Override
	public String toString() {
		return toCsvString("|");
	}
}
