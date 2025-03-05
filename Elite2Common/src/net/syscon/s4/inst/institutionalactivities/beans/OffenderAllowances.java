package net.syscon.s4.inst.institutionalactivities.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderAllowances extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String allowanceType;
	
	private String unit;
	
	private Integer maxUnit;
	
	private Integer rate;
	
	private Integer versionNo;
	
	private Date allowanceModifiedDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private BigDecimal offenderBookId;
	
	private BigDecimal offAllowanceId;
	
	private Integer returnedOutput;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("paidFlag")
	private Integer paidFlag;
	

	@JsonProperty("lastPaidDate")
	private Date lastPaidDate;
	

	public String getAllowanceType() {
		return allowanceType;
	}

	public String getUnit() {
		return unit;
	}

	public Integer getMaxUnit() {
		return maxUnit;
	}

	public Integer getRate() {
		return rate;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public Date getAllowanceModifiedDate() {
		return allowanceModifiedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}


	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setMaxUnit(Integer maxUnit) {
		this.maxUnit = maxUnit;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	public void setAllowanceModifiedDate(Date allowanceModifiedDate) {
		this.allowanceModifiedDate = allowanceModifiedDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}


	public Date getCreateDatetime() {
		return createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Integer getReturnedOutput() {
		return returnedOutput;
	}

	public void setReturnedOutput(Integer returnedOutput) {
		this.returnedOutput = returnedOutput;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public BigDecimal getOffAllowanceId() {
		return offAllowanceId;
	}

	public void setOffAllowanceId(BigDecimal offAllowanceId) {
		this.offAllowanceId = offAllowanceId;
	}
	
	public Integer getPaidFlag() {
		return paidFlag;
	}

	public void setPaidFlag(Integer paidFlag) {
		this.paidFlag = paidFlag;
	}

	public Date getLastPaidDate() {
		return lastPaidDate;
	}

	public void setLastPaidDate(Date lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}
}
