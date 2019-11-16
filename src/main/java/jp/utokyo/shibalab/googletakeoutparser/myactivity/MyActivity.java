package jp.utokyo.shibalab.googletakeoutparser.myactivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * My Activity Object class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyActivity implements Comparable<MyActivity> {
	/* ==============================================================
	 * static fields
	 * ============================================================== */
	/** prefix for query */
	protected static final String PREFIX_QUERY   = "「 ";
	/** postfix for access */
	protected static final String POSTFIX_ACCESS = " にアクセスしました";
	/** postfix for query */
	protected static final String POSTFIX_QUERY  = " 」を検索しました";
	

	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * get CSV header line with the indicated delimiter
	 * @param delim delimiter
	 * @return header line
	 */
	public static String getCsvHeader(String delim) {
		return StringUtils.join(
				new String[]{"header","title","queryKey", "titleUrl","time","products","locationInfos"},
				delim);
	}
	
	
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** header */
	private String _header;
		
	/** title */
	private String _title;
	
	/** title URL */
	private String _titleUrl;

	/** time */
	private Date   _time;
	
	/** products */
	private List<String> _products;
	
	/** locationInfos */
	private List<LocationInfo> _locationInfos;
	
	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 */
	@JsonCreator
	private MyActivity(	
		@JsonProperty("header")        String header,
		@JsonProperty("title")         String title,
		@JsonProperty("titleUrl")      String titleUrl,
		@JsonProperty("time")          String time,
		@JsonProperty("products")      List<String> products,
		@JsonProperty("locationInfos") List<LocationInfo> locationInfos
	)
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		_header = header;
		_title  = title;
		_titleUrl = titleUrl;
		try {
			_time = sdf1.parse(time);
		}
		catch(ParseException exp1) {
//			exp1.printStackTrace();
			System.err.println(exp1.getMessage());
			try {
				_time = sdf2.parse(time);
			}
			catch(ParseException exp2) {
//				exp2.printStackTrace();
				System.err.println(exp2.getMessage());
				_time = null;

			}
		}
		
		_products      = products;
		_locationInfos = locationInfos;
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * check if this log is query
	 * @return result
	 */
	public boolean isQuery() {
		return _title.endsWith(POSTFIX_QUERY);
	}
	
	/**
	 * check if this log is access
	 * @return result
	 */
	public boolean isAccess() {
		return _title.endsWith(POSTFIX_ACCESS);
	}
	
	/**
	 * extract query key text
	 * @return query key
	 */
	public String extractQueryKey() {
		if( isQuery() ) {
			return StringUtils.substringBetween(_title, PREFIX_QUERY, POSTFIX_QUERY);
		}
		return null;
	}
	
	/**
	 * get header string 
	 * @return header string
	 */
	public String getHeader() {
		return _header;
	}
	
	/**
	 * get title string 
	 * @return title string
	 */
	public String getTitle() {
		return _title;
	}
	
	/**
	 * get title URL 
	 * @return title URL
	 */
	public String getTitleUrl() {
		return _titleUrl;
	}
	
	/**
	 * get time stamp
	 * @return time stamp
	 */
	public Date getTime() {
		return _time;
	}
	
	/**
	 * get products 
	 * @return products
	 */
	public List<String> getProducts() {
		return _products;
	}
	
	/**
	 * get location infos 
	 * @return location infos
	 */
	public List<LocationInfo> getLocationInfos() {
		return _locationInfos;
	}
	
	/**
	 * get CSV string with the indicated delimiter
	 * @param delimiter delimiter string
	 * @return CSV string
	 */
	public String toCsvString(String delimiter) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		String url = getTitleUrl();
		try {
			url = new URLCodec().decode(getTitleUrl());
		}
		catch(DecoderException exp) {
			exp.printStackTrace();
		}
		String[] tokens = new String[] { 
			getHeader(),
			getTitle(),
			isQuery() ? extractQueryKey() : "",
			url,
			getTime() != null ? sdf.format(getTime()) : "",
			StringUtils.join(getProducts(),";"),
			StringUtils.join(getLocationInfos(),";")
		};
		
		return StringUtils.join(tokens,delimiter);
	}

	@Override
	public int compareTo(MyActivity act) { 
		Long L0 = _time.getTime();
		Long L1 = act.getTime().getTime();
		
		return L0.compareTo(L1);
	}
}
