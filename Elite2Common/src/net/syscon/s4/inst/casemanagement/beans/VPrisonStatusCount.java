package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_PRISON_STATUS_COUNT database table.
 * 
 */
public class VPrisonStatusCount extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agyLocId;

	private BigDecimal femaleCount;

	private String imprisonmentStatus;

	private BigDecimal maleCount;

	private BigDecimal totalCount;
	
	private Integer other;
	
	private Double percent;

	public VPrisonStatusCount() {
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

	public String getImprisonmentStatus() {
		return this.imprisonmentStatus;
	}

	public void setImprisonmentStatus(String imprisonmentStatus) {
		this.imprisonmentStatus = imprisonmentStatus;
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

	/**
	 * @return the other
	 */
	public Integer getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(Integer other) {
		this.other = other;
	}

	/**
	 * @return the percent
	 */
	public Double getPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(Double percent) {
		this.percent = percent;
	}

}
