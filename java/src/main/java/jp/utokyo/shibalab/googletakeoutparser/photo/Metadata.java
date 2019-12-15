package jp.utokyo.shibalab.googletakeoutparser.photo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 */
public class Metadata {
	/* ==============================================================
	 * instance fields
	 * ============================================================== */
	/** album data */
	@JsonProperty("albumData")
	private AlbumData _albumData;
	/** media */
	@JsonProperty("media")
	private List<String> _media;
	

	/* ==============================================================
	 * instance methods
	 * ============================================================== */
	/**
	 * get album data 
	 * @return album data
	 */
	public AlbumData getAlbumData() {
		return _albumData;
	}
	
	/**
	 * get media list TODO check detail
	 * @return media list
	 */
	public List<String> listMedia() {
		return _media;
	}
}
