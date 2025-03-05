package net.syscon.s4.common.beans;

import java.io.Serializable;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderBillingProfiles extends BaseModel implements Serializable  {
	
	@JsonProperty("offenderBookingId")
	private long offenderBookingId ;
	
	@JsonProperty("agencyId")
	private long agencyId;
	
	@JsonProperty("caseloadId") 
	private String caseloadId ;
	
	@JsonProperty("billingType")
	private String billingType;
	
	@JsonProperty("effectiveDateStart") 
	private Date effectiveDateStart;
	
	@JsonProperty("effectiveDateEnd")
	private Date effectiveDateEnd;
	
	@JsonProperty("createDate")
	private Date createDate;
	
	@JsonProperty("rate")
	private long rate;
	
	@JsonProperty("comments")
	private String comments ;
	
	@JsonProperty("impAgyBillingDetailId")
	private long impAgyBillingDetailId;
	
	@JsonProperty("effectiveStartTime")
	private Date effectiveStartTime;
	
	@JsonProperty("effectiveEndTime")
	private Date effectiveEndTime;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("caseId")
	private long caseId ;
	
	@JsonProperty("imprisonmentStatus")
	private String imprisonmentStatus ;
	
	@JsonProperty("eligibleDate")
	private Date eligibleDate; 
	
	@JsonProperty("caseNumber")
	private String caseNumber;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("delayDays")
	private Integer delayDays;

	public long getOffenderBookingId() {
		return offenderBookingId;
	}

	public void setOffenderBookingId(long offenderBookingId) {
		this.offenderBookingId = offenderBookingId;
	}

	public long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public Date getEffectiveDateStart() {
		return effectiveDateStart;
	}

	public void setEffectiveDateStart(Date effectiveDateStart) {
		this.effectiveDateStart = effectiveDateStart;
	}

	public Date getEffectiveDateEnd() {
		return effectiveDateEnd;
	}

	public void setEffectiveDateEnd(Date effectiveDateEnd) {
		this.effectiveDateEnd = effectiveDateEnd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public long getImpAgyBillingDetailId() {
		return impAgyBillingDetailId;
	}

	public void setImpAgyBillingDetailId(long impAgyBillingDetailId) {
		this.impAgyBillingDetailId = impAgyBillingDetailId;
	}

	public Date getEffectiveStartTime() {
		return effectiveStartTime;
	}

	public void setEffectiveStartTime(Date effectiveStartTime) {
		this.effectiveStartTime = effectiveStartTime;
	}

	public Date getEffectiveEndTime() {
		return effectiveEndTime;
	}

	public void setEffectiveEndTime(Date effectiveEndTime) {
		this.effectiveEndTime = effectiveEndTime;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getImprisonmentStatus() {
		return imprisonmentStatus;
	}

	public void setImprisonmentStatus(String imprisonmentStatus) {
		this.imprisonmentStatus = imprisonmentStatus;
	}

	public Date getEligibleDate() {
		return eligibleDate;
	}

	public void setEligibleDate(Date eligibleDate) {
		this.eligibleDate = eligibleDate;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the delayDays
	 */
	public Integer getDelayDays() {
		return delayDays;
	}

	/**
	 * @param delayDays the delayDays to set
	 */
	public void setDelayDays(final Integer delayDays) {
		this.delayDays = delayDays;
	}

	
}
