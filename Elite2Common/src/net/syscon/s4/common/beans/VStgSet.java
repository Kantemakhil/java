package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the V_STG_SET database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VStgSet extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("stgDesc")
	private String stgDesc;
	@JsonProperty("stgId")
	private BigDecimal stgId;

	public VStgSet() {
		// VStgSet
	}

	public String getStgDesc() {
		return this.stgDesc;
	}

	public void setStgDesc(final String stgDesc) {
		this.stgDesc = stgDesc;
	}

	public BigDecimal getStgId() {
		return this.stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

}
