package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_PRISON_TOTAL database table.
 * 
 */
public class VPrisonTotal extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agyLocId;

	private BigDecimal femaleCount;

	private BigDecimal maleCount;

	private BigDecimal totalCount;

	public VPrisonTotal() {
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getFemaleCount() {
		return this.femaleCount;
	}

	public void setFemaleCount(BigDecimal femaleCount) {
		this.femaleCount = femaleCount;
	}

	public BigDecimal getMaleCount() {
		return this.maleCount;
	}

	public void setMaleCount(BigDecimal maleCount) {
		this.maleCount = maleCount;
	}

	public BigDecimal getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}

}
