package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the V_STG_GROUP database table.
 * 
 */
public class VStgGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stgDesc;

	private BigDecimal stgId;

	public VStgGroup() {
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
