package jp.utokyo.shibalab.googletakeoutparser.query;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Query object class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** time-stamp */
	@JsonProperty("query_text")
	private String _queryText;
		
	/** activity array */
	@JsonProperty("id")
	private List<ID> _ids;

	
	/* ==============================================================
	 * constructors
	 * ============================================================== */
	/**
	 * initialization
	 */
	protected Query() {
		this(null,null);
	}
	
	/**
	 * initialization 
	 * @param ids ID object list
	 * @param queryText query text
	 */
	protected Query(List<ID> ids, String queryText) { 
		_ids       = ids;
		_queryText = queryText;
	}
	
	
	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get query text
	 * @return query text
	 */
	public String getQueryText() {
		return _queryText;
	}
	
	/**
	 * get ID (including time stamp) 
	 * @return ID list(time stamp)
	 */
	public List<ID> listIDs() {
		return _ids;
	}
	
	/**
	 * make CSV string as the following column order(id,querytext)
	 * @return CSV string
	 */
	public String toCsvString() {
		return toCsvString(",");
	}
	
	/**
	 * make CSV string as the following column order(id,querytext)
	 * @param delim column delimiter
	 * @return CSV string
	 */
	public String toCsvString(String delim) {
		StringBuffer buf = new StringBuffer();
		// construct token with IDs
		for(ID id:_ids) { 
			buf.append("|").append(id.toString());
		}
		
		// returns result /////////////////////////////////
		return buf.substring(1) + delim + _queryText;
	}
	
	/* @see java.lang.Object#toString() */
	@Override
	public String toString() { 
		return String.format("id=%s,query_text=%s",_ids,_queryText);
	}
}
