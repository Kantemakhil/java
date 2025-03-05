package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the STG_SEARCH_V1 database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StgSearchV1 extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("seq")
	private BigDecimal seq;
	@JsonProperty("source")
	private String source;
	@JsonProperty("stgId")
	private BigDecimal stgId;
	@JsonProperty("identifier")
	private String identifier;
	@JsonProperty("keywordText")
	private String keywordText;
	@JsonProperty("nbtStgDescp")
	private String nbtStgDescp;

	public StgSearchV1() {
		// StgSearchV1
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the nbtStgDescp
	 */
	public String getNbtStgDescp() {
		return nbtStgDescp;
	}

	/**
	 * @param nbtStgDescp the nbtStgDescp to set
	 */
	public void setNbtStgDescp(final String nbtStgDescp) {
		this.nbtStgDescp = nbtStgDescp;
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the keywordText
	 */
	public String getKeywordText() {
		return keywordText;
	}

	/**
	 * @param keywordText
	 *            the keywordText to set
	 */
	public void setKeywordText(final String keywordText) {
		this.keywordText = keywordText;
	}

	
	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public BigDecimal getStgId() {
		return this.stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}
	
	private String modifyUserId;

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	

}
