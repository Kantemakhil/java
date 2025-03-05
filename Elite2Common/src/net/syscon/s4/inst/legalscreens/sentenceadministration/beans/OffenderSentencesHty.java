package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentencesHty extends BaseModel implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;
	
	@JsonProperty("sentenceEventId")
	private Long sentenceEventId;
	
	@JsonProperty("chargeSeq")
	private Long chargeSeq;
	
	@JsonProperty("counts")
	private Long counts;
	
	@JsonProperty("jurisdictionCode")
	private String jurisdictionCode;
	
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	
	@JsonProperty("sentenceStatus")
	private String sentenceStatus;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("termChangedFlag")
	private String termChangedFlag;
	
	@JsonProperty("sentCalcNeededFlag")
	private String sentCalcNeededFlag;
	
	@JsonProperty("fineAmount")
	private Long fineAmount;
	
	@JsonProperty("consecutiveCountFlag")
	private String consecutiveCountFlag;
	
	@JsonProperty("fineCommentText")
	private String fineCommentText;
	
	@JsonProperty("consecToSentenceSeq")
	private Long consecToSentenceSeq;
	
	@JsonProperty("aggregateSentenceSeq")
	private Long aggregateSentenceSeq;
	
	@JsonProperty("sentenceDate")
	private Date sentenceDate;
	
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	
	@JsonProperty("goodConductDays")
	private Long goodConductDays;
	
	@JsonProperty("paymentDueDate")
	private Date paymentDueDate;
	
	@JsonProperty("defaultDays")
	private Long defaultDays;
	
	@JsonProperty("paidDate")
	private Date paidDate;
	
	@JsonProperty("revocationDate")
	private Date revocationDate;
	
	@JsonProperty("asGoodtimeType")
	private String asGoodtimeType;
	
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("startTime")
	private Date startTime;
	
	@JsonProperty("nonProbationStartDate")
	private Date nonProbationStartDate;
	
	@JsonProperty("probableReleaseDate")
	private Date probableReleaseDate;
	
	@JsonProperty("probableReleaseTime")
	private Date probableReleaseTime;
	
	@JsonProperty("sentenceExpiryDate")
	private Date sentenceExpiryDate;
	
	@JsonProperty("sentenceExpiryTime")
	private Date sentenceExpiryTime;
	
	@JsonProperty("sentClosedDate")
	private Date sentClosedDate;
	
	@JsonProperty("sentClosReasonCode")
	private String sentClosReasonCode;
	
	@JsonProperty("sentClosCommentText")
	private String sentClosCommentText;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("creationDate")
	private Date creationDate;
	
	@JsonProperty("creationUser")
	private String creationUser;
	
	@JsonProperty("dischargeAuthority")
	private Long dischargeAuthority;
	
	@JsonProperty("dischargeComment")
	private String dischargeComment;
	
	@JsonProperty("dischargeDate")
	private Date dischargeDate;
	
	@JsonProperty("dischargeReason")
	private String dischargeReason;
	
	@JsonProperty("dtbfFlag")
	private String dtbfFlag;
	
	@JsonProperty("extendedDate")
	private Date extendedDate;
	
	@JsonProperty("extendedFlag")
	private String extendedFlag;
	
	@JsonProperty("extendingAuthority")
	private String extendingAuthority;
	
	@JsonProperty("gapFlag")
	private String gapFlag;
	
	@JsonProperty("installmentDetails")
	private String installmentDetails;
	
	@JsonProperty("orderCode")
	private String orderCode;
	
	@JsonProperty("orderType")
	private String orderType;
	
	@JsonProperty("registrationDate")
	private Date registrationDate;
	
	@JsonProperty("reportDueDate")
	private Date reportDueDate;
	
	@JsonProperty("supervisingAgyLocId")
	private String supervisingAgyLocId;
	
	@JsonProperty("supervisionExpiryDate")
	private Date supervisionExpiryDate;
	
	@JsonProperty("totalCompensation")
	private Long totalCompensation;
	
	@JsonProperty("totalFine")
	private Long totalFine;
	
	@JsonProperty("eksSentence")
	private String eksSentence;
	
	@JsonProperty("applicableAdjustCode")
	private String applicableAdjustCode;
	
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

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Long getSentenceEventId() {
		return sentenceEventId;
	}

	public void setSentenceEventId(Long sentenceEventId) {
		this.sentenceEventId = sentenceEventId;
	}

	public Long getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(Long chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public Long getCounts() {
		return counts;
	}

	public void setCounts(Long counts) {
		this.counts = counts;
	}

	public String getJurisdictionCode() {
		return jurisdictionCode;
	}

	public void setJurisdictionCode(String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceStatus() {
		return sentenceStatus;
	}

	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTermChangedFlag() {
		return termChangedFlag;
	}

	public void setTermChangedFlag(String termChangedFlag) {
		this.termChangedFlag = termChangedFlag;
	}

	public String getSentCalcNeededFlag() {
		return sentCalcNeededFlag;
	}

	public void setSentCalcNeededFlag(String sentCalcNeededFlag) {
		this.sentCalcNeededFlag = sentCalcNeededFlag;
	}

	public Long getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Long fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getConsecutiveCountFlag() {
		return consecutiveCountFlag;
	}

	public void setConsecutiveCountFlag(String consecutiveCountFlag) {
		this.consecutiveCountFlag = consecutiveCountFlag;
	}

	public String getFineCommentText() {
		return fineCommentText;
	}

	public void setFineCommentText(String fineCommentText) {
		this.fineCommentText = fineCommentText;
	}

	public Long getConsecToSentenceSeq() {
		return consecToSentenceSeq;
	}

	public void setConsecToSentenceSeq(Long consecToSentenceSeq) {
		this.consecToSentenceSeq = consecToSentenceSeq;
	}

	public Long getAggregateSentenceSeq() {
		return aggregateSentenceSeq;
	}

	public void setAggregateSentenceSeq(Long aggregateSentenceSeq) {
		this.aggregateSentenceSeq = aggregateSentenceSeq;
	}

	public Date getSentenceDate() {
		return sentenceDate;
	}

	public void setSentenceDate(Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Long getGoodConductDays() {
		return goodConductDays;
	}

	public void setGoodConductDays(Long goodConductDays) {
		this.goodConductDays = goodConductDays;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public Long getDefaultDays() {
		return defaultDays;
	}

	public void setDefaultDays(Long defaultDays) {
		this.defaultDays = defaultDays;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Date getRevocationDate() {
		return revocationDate;
	}

	public void setRevocationDate(Date revocationDate) {
		this.revocationDate = revocationDate;
	}

	public String getAsGoodtimeType() {
		return asGoodtimeType;
	}

	public void setAsGoodtimeType(String asGoodtimeType) {
		this.asGoodtimeType = asGoodtimeType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getNonProbationStartDate() {
		return nonProbationStartDate;
	}

	public void setNonProbationStartDate(Date nonProbationStartDate) {
		this.nonProbationStartDate = nonProbationStartDate;
	}

	public Date getProbableReleaseDate() {
		return probableReleaseDate;
	}

	public void setProbableReleaseDate(Date probableReleaseDate) {
		this.probableReleaseDate = probableReleaseDate;
	}

	public Date getProbableReleaseTime() {
		return probableReleaseTime;
	}

	public void setProbableReleaseTime(Date probableReleaseTime) {
		this.probableReleaseTime = probableReleaseTime;
	}

	public Date getSentenceExpiryDate() {
		return sentenceExpiryDate;
	}

	public void setSentenceExpiryDate(Date sentenceExpiryDate) {
		this.sentenceExpiryDate = sentenceExpiryDate;
	}

	public Date getSentenceExpiryTime() {
		return sentenceExpiryTime;
	}

	public void setSentenceExpiryTime(Date sentenceExpiryTime) {
		this.sentenceExpiryTime = sentenceExpiryTime;
	}

	public Date getSentClosedDate() {
		return sentClosedDate;
	}

	public void setSentClosedDate(Date sentClosedDate) {
		this.sentClosedDate = sentClosedDate;
	}

	public String getSentClosReasonCode() {
		return sentClosReasonCode;
	}

	public void setSentClosReasonCode(String sentClosReasonCode) {
		this.sentClosReasonCode = sentClosReasonCode;
	}

	public String getSentClosCommentText() {
		return sentClosCommentText;
	}

	public void setSentClosCommentText(String sentClosCommentText) {
		this.sentClosCommentText = sentClosCommentText;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Long getDischargeAuthority() {
		return dischargeAuthority;
	}

	public void setDischargeAuthority(Long dischargeAuthority) {
		this.dischargeAuthority = dischargeAuthority;
	}

	public String getDischargeComment() {
		return dischargeComment;
	}

	public void setDischargeComment(String dischargeComment) {
		this.dischargeComment = dischargeComment;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getDischargeReason() {
		return dischargeReason;
	}

	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}

	public String getDtbfFlag() {
		return dtbfFlag;
	}

	public void setDtbfFlag(String dtbfFlag) {
		this.dtbfFlag = dtbfFlag;
	}

	public Date getExtendedDate() {
		return extendedDate;
	}

	public void setExtendedDate(Date extendedDate) {
		this.extendedDate = extendedDate;
	}

	public String getExtendedFlag() {
		return extendedFlag;
	}

	public void setExtendedFlag(String extendedFlag) {
		this.extendedFlag = extendedFlag;
	}

	public String getExtendingAuthority() {
		return extendingAuthority;
	}

	public void setExtendingAuthority(String extendingAuthority) {
		this.extendingAuthority = extendingAuthority;
	}

	public String getGapFlag() {
		return gapFlag;
	}

	public void setGapFlag(String gapFlag) {
		this.gapFlag = gapFlag;
	}

	public String getInstallmentDetails() {
		return installmentDetails;
	}

	public void setInstallmentDetails(String installmentDetails) {
		this.installmentDetails = installmentDetails;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getReportDueDate() {
		return reportDueDate;
	}

	public void setReportDueDate(Date reportDueDate) {
		this.reportDueDate = reportDueDate;
	}

	public String getSupervisingAgyLocId() {
		return supervisingAgyLocId;
	}

	public void setSupervisingAgyLocId(String supervisingAgyLocId) {
		this.supervisingAgyLocId = supervisingAgyLocId;
	}

	public Date getSupervisionExpiryDate() {
		return supervisionExpiryDate;
	}

	public void setSupervisionExpiryDate(Date supervisionExpiryDate) {
		this.supervisionExpiryDate = supervisionExpiryDate;
	}

	public Long getTotalCompensation() {
		return totalCompensation;
	}

	public void setTotalCompensation(Long totalCompensation) {
		this.totalCompensation = totalCompensation;
	}

	public Long getTotalFine() {
		return totalFine;
	}

	public void setTotalFine(Long totalFine) {
		this.totalFine = totalFine;
	}

	public String getEksSentence() {
		return eksSentence;
	}

	public void setEksSentence(String eksSentence) {
		this.eksSentence = eksSentence;
	}

	public String getApplicableAdjustCode() {
		return applicableAdjustCode;
	}

	public void setApplicableAdjustCode(String applicableAdjustCode) {
		this.applicableAdjustCode = applicableAdjustCode;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	
}
