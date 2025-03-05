package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountPeriods implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountPeriodId")
	private Long accountPeriodId;
	
	@JsonProperty("accountPeriodType")
	private String accountPeriodType;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("endDate")
	private Date endDate;
	
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	
	@JsonProperty("modifyDate")
	private Date modifyDate;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("parentAccountPeriodId")
	private Long parentAccountPeriodId;
	

	public AccountPeriods() {
	}

	public Long getAccountPeriodId() {
		return this.accountPeriodId;
	}

	public void setAccountPeriodId(final Long accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	public String getAccountPeriodType() {
		return this.accountPeriodType;
	}

	public void setAccountPeriodType(final String accountPeriodType) {
		this.accountPeriodType = accountPeriodType;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the parentAccountPeriodId
	 */
	public Long getParentAccountPeriodId() {
		return parentAccountPeriodId;
	}

	/**
	 * @param parentAccountPeriodId the parentAccountPeriodId to set
	 */
	public void setParentAccountPeriodId(final Long parentAccountPeriodId) {
		this.parentAccountPeriodId = parentAccountPeriodId;
	}

}
