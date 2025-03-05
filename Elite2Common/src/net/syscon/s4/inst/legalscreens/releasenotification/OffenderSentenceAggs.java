package net.syscon.s4.inst.legalscreens.releasenotification;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderSentenceAggs extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("earliestSentStartDate")
	private Date earliestSentStartDate;	
	
	@JsonProperty("finalSentExpDate")
	private Date finalSentExpDate;
	
	@JsonProperty("ovrSentExpDate")
	private Date ovrSentExpDate;
	
	@JsonProperty("calcPosRelDate")
	private Date calcPosRelDate;
	
	@JsonProperty("ovrPosRelDate") 
	private Date ovrPosRelDate;	
	
	@JsonProperty("paroleDate") 
	private Date paroleDate;
	
	@JsonProperty("paroleReviewDate")
	private Date paroleReviewDate;	
	
	@JsonProperty("calcPosProbDate")
	private Date calcPosProbDate;
	
	@JsonProperty("ovrPosProbDate")
	private Date ovrPosProbDate;
	
	@JsonProperty("calcProbEffectDate")
	private Date calcProbEffectDate;
	
	@JsonProperty("ovrProbEffectDate") 
	private Date ovrProbEffectDate;	
	
	@JsonProperty("totalLengthYears") 
	private long totalLengthYears;
	
	@JsonProperty("totalLengthMonths") 
	private long totalLengthMonths;	
	
	@JsonProperty("totalLengthWeeks") 
	private long totalLengthWeeks;
	
	@JsonProperty("totalLengthDays")
	private long totalLengthDays;
	
	@JsonProperty("totalLengthHours")
	private long totalLengthHours;
	
	@JsonProperty("ovrProbExpDate")
	private Date ovrProbExpDate;
	
	@JsonProperty("ovrPrlExpDate")
	private Date ovrPrlExpDate;	
	
	@JsonProperty("sentCalcNeededFlag")
	private String sentCalcNeededFlag;
	
	@JsonProperty("totalAwolDays")
	private long totalAwolDays;	
	
	@JsonProperty("eligibleGoodtimeDays") 
	private long eligibleGoodtimeDays;	
	
	@JsonProperty("totalServedTime")
	private long totalServedTime;
	
	@JsonProperty("totalWorkTime")
	private long totalWorkTime;		
	
	@JsonProperty("goodtimeRevokedDays") 
	private long goodtimeRevokedDays;
	
	@JsonProperty("revokedParoleDays")
	private long revokedParoleDays;	
	
	@JsonProperty("notificationDate")
	private Date notificationDate;
	
	@JsonProperty("errorCode") 
	private long errorCode;	
	
	@JsonProperty("sentenceLengthText")
	private String sentenceLengthText;	
	
	@JsonProperty("totalSatisfiedTime")
	private long totalSatisfiedTime;
	
	@JsonProperty("mostSeriousSentenceSeq")
	private long mostSeriousSentenceSeq;
	
	@JsonProperty("releaseReason")
	private String releaseReason;	
	
	@JsonProperty("commentText") 
	private String commentText;		
	
	@JsonProperty("minTerm") 
	private String minTerm;		
	
	@JsonProperty("maxTerm")
	private String maxTerm;	
	
	@JsonProperty("pendingSentFlag")
	private String pendingSentFlag;	
	
	@JsonProperty("pendingSentSetDate")
	private Date pendingSentSetDate;
	
	@JsonProperty("verifiedFlag") 
	private String verifiedFlag;
	
	@JsonProperty("flatMaxDate")
	private Date flatMaxDate;	
	
	@JsonProperty("bookMaxDate")
	private Date bookMaxDate;	
	
	@JsonProperty("minExpirationDate")
	private Date minExpirationDate;	
	
	@JsonProperty("actualMaxDate")
	private Date actualMaxDate;
	
	@JsonProperty("daysRemaining")
	private long daysRemaining;
	
	@JsonProperty("totalAttendenceTime")
	private long totalAttendenceTime;
	
	@JsonProperty("caseManagementDate")
	private Date caseManagementDate;	
	
	@JsonProperty("controlOrderId")
	private long controlOrderId;
	
	@JsonProperty("yoDischargeDate")
	private Date yoDischargeDate;
	
	@JsonProperty("ysDischargeDate") 
	private Date ysDischargeDate;
	
	@JsonProperty("dischargeDate")
	private Date dischargeDate;	
	
	@JsonProperty("ovrYoDischargeDate")
	private Date ovrYoDischargeDate;
	
	@JsonProperty("ovrYsDischargeDate")
	private Date ovrYsDischargeDate;
	
	@JsonProperty("ovrDischargeDate")
	private Date ovrDischargeDate;
	
	@JsonProperty("paroleEligiblityDate")
	private Date paroleEligiblityDate;	
	
	@JsonProperty("ovrParoleEligiblityDate")
	private Date ovrParoleEligiblityDate;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("ovrEarliestSentStartDate")
	private Date ovrEarliestSentStartDate;	
	
	@JsonProperty("printedFlag")
	private String printedFlag;
	
	@JsonProperty("updateFlag")
	private String updateFlag;
	
	@JsonProperty("modifyDate") 
	private Date modifyDate;
	
	@JsonProperty("ovrAdultAggDays")
	private long ovrAdultAggDays;
	
	@JsonProperty("ovrYouAggDays")
	private long ovrYouAggDays;	
	
	@JsonProperty("intDischargeDate")
	private Date intDischargeDate;	
	
	@JsonProperty("ovrIntDischargeDate")
	private Date ovrIntDischargeDate;
	
	@JsonProperty("paroleEndDate")
	private Date paroleEndDate;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;	
	
	@JsonProperty("sealFlag") 
	private String sealFlag;

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getEarliestSentStartDate() {
		return earliestSentStartDate;
	}

	public void setEarliestSentStartDate(Date earliestSentStartDate) {
		this.earliestSentStartDate = earliestSentStartDate;
	}

	public Date getFinalSentExpDate() {
		return finalSentExpDate;
	}

	public void setFinalSentExpDate(Date finalSentExpDate) {
		this.finalSentExpDate = finalSentExpDate;
	}

	public Date getOvrSentExpDate() {
		return ovrSentExpDate;
	}

	public void setOvrSentExpDate(Date ovrSentExpDate) {
		this.ovrSentExpDate = ovrSentExpDate;
	}

	public Date getCalcPosRelDate() {
		return calcPosRelDate;
	}

	public void setCalcPosRelDate(Date calcPosRelDate) {
		this.calcPosRelDate = calcPosRelDate;
	}

	public Date getOvrPosRelDate() {
		return ovrPosRelDate;
	}

	public void setOvrPosRelDate(Date ovrPosRelDate) {
		this.ovrPosRelDate = ovrPosRelDate;
	}

	public Date getParoleDate() {
		return paroleDate;
	}

	public void setParoleDate(Date paroleDate) {
		this.paroleDate = paroleDate;
	}

	public Date getParoleReviewDate() {
		return paroleReviewDate;
	}

	public void setParoleReviewDate(Date paroleReviewDate) {
		this.paroleReviewDate = paroleReviewDate;
	}

	public Date getCalcPosProbDate() {
		return calcPosProbDate;
	}

	public void setCalcPosProbDate(Date calcPosProbDate) {
		this.calcPosProbDate = calcPosProbDate;
	}

	public Date getOvrPosProbDate() {
		return ovrPosProbDate;
	}

	public void setOvrPosProbDate(Date ovrPosProbDate) {
		this.ovrPosProbDate = ovrPosProbDate;
	}

	public Date getCalcProbEffectDate() {
		return calcProbEffectDate;
	}

	public void setCalcProbEffectDate(Date calcProbEffectDate) {
		this.calcProbEffectDate = calcProbEffectDate;
	}

	public Date getOvrProbEffectDate() {
		return ovrProbEffectDate;
	}

	public void setOvrProbEffectDate(Date ovrProbEffectDate) {
		this.ovrProbEffectDate = ovrProbEffectDate;
	}

	public long getTotalLengthYears() {
		return totalLengthYears;
	}

	public void setTotalLengthYears(long totalLengthYears) {
		this.totalLengthYears = totalLengthYears;
	}

	public long getTotalLengthMonths() {
		return totalLengthMonths;
	}

	public void setTotalLengthMonths(long totalLengthMonths) {
		this.totalLengthMonths = totalLengthMonths;
	}

	public long getTotalLengthWeeks() {
		return totalLengthWeeks;
	}

	public void setTotalLengthWeeks(long totalLengthWeeks) {
		this.totalLengthWeeks = totalLengthWeeks;
	}

	public long getTotalLengthDays() {
		return totalLengthDays;
	}

	public void setTotalLengthDays(long totalLengthDays) {
		this.totalLengthDays = totalLengthDays;
	}

	public long getTotalLengthHours() {
		return totalLengthHours;
	}

	public void setTotalLengthHours(long totalLengthHours) {
		this.totalLengthHours = totalLengthHours;
	}

	public Date getOvrProbExpDate() {
		return ovrProbExpDate;
	}

	public void setOvrProbExpDate(Date ovrProbExpDate) {
		this.ovrProbExpDate = ovrProbExpDate;
	}

	public Date getOvrPrlExpDate() {
		return ovrPrlExpDate;
	}

	public void setOvrPrlExpDate(Date ovrPrlExpDate) {
		this.ovrPrlExpDate = ovrPrlExpDate;
	}

	public String getSentCalcNeededFlag() {
		return sentCalcNeededFlag;
	}

	public void setSentCalcNeededFlag(String sentCalcNeededFlag) {
		this.sentCalcNeededFlag = sentCalcNeededFlag;
	}

	public long getTotalAwolDays() {
		return totalAwolDays;
	}

	public void setTotalAwolDays(long totalAwolDays) {
		this.totalAwolDays = totalAwolDays;
	}

	public long getEligibleGoodtimeDays() {
		return eligibleGoodtimeDays;
	}

	public void setEligibleGoodtimeDays(long eligibleGoodtimeDays) {
		this.eligibleGoodtimeDays = eligibleGoodtimeDays;
	}

	public long getTotalServedTime() {
		return totalServedTime;
	}

	public void setTotalServedTime(long totalServedTime) {
		this.totalServedTime = totalServedTime;
	}

	public long getTotalWorkTime() {
		return totalWorkTime;
	}

	public void setTotalWorkTime(long totalWorkTime) {
		this.totalWorkTime = totalWorkTime;
	}

	public long getGoodtimeRevokedDays() {
		return goodtimeRevokedDays;
	}

	public void setGoodtimeRevokedDays(long goodtimeRevokedDays) {
		this.goodtimeRevokedDays = goodtimeRevokedDays;
	}

	public long getRevokedParoleDays() {
		return revokedParoleDays;
	}

	public void setRevokedParoleDays(long revokedParoleDays) {
		this.revokedParoleDays = revokedParoleDays;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

	public String getSentenceLengthText() {
		return sentenceLengthText;
	}

	public void setSentenceLengthText(String sentenceLengthText) {
		this.sentenceLengthText = sentenceLengthText;
	}

	public long getTotalSatisfiedTime() {
		return totalSatisfiedTime;
	}

	public void setTotalSatisfiedTime(long totalSatisfiedTime) {
		this.totalSatisfiedTime = totalSatisfiedTime;
	}

	public long getMostSeriousSentenceSeq() {
		return mostSeriousSentenceSeq;
	}

	public void setMostSeriousSentenceSeq(long mostSeriousSentenceSeq) {
		this.mostSeriousSentenceSeq = mostSeriousSentenceSeq;
	}

	public String getReleaseReason() {
		return releaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getMinTerm() {
		return minTerm;
	}

	public void setMinTerm(String minTerm) {
		this.minTerm = minTerm;
	}

	public String getMaxTerm() {
		return maxTerm;
	}

	public void setMaxTerm(String maxTerm) {
		this.maxTerm = maxTerm;
	}

	public String getPendingSentFlag() {
		return pendingSentFlag;
	}

	public void setPendingSentFlag(String pendingSentFlag) {
		this.pendingSentFlag = pendingSentFlag;
	}

	public Date getPendingSentSetDate() {
		return pendingSentSetDate;
	}

	public void setPendingSentSetDate(Date pendingSentSetDate) {
		this.pendingSentSetDate = pendingSentSetDate;
	}

	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	public void setVerifiedFlag(String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	public Date getFlatMaxDate() {
		return flatMaxDate;
	}

	public void setFlatMaxDate(Date flatMaxDate) {
		this.flatMaxDate = flatMaxDate;
	}

	public Date getBookMaxDate() {
		return bookMaxDate;
	}

	public void setBookMaxDate(Date bookMaxDate) {
		this.bookMaxDate = bookMaxDate;
	}

	public Date getMinExpirationDate() {
		return minExpirationDate;
	}

	public void setMinExpirationDate(Date minExpirationDate) {
		this.minExpirationDate = minExpirationDate;
	}

	public Date getActualMaxDate() {
		return actualMaxDate;
	}

	public void setActualMaxDate(Date actualMaxDate) {
		this.actualMaxDate = actualMaxDate;
	}

	public long getDaysRemaining() {
		return daysRemaining;
	}

	public void setDaysRemaining(long daysRemaining) {
		this.daysRemaining = daysRemaining;
	}

	public long getTotalAttendenceTime() {
		return totalAttendenceTime;
	}

	public void setTotalAttendenceTime(long totalAttendenceTime) {
		this.totalAttendenceTime = totalAttendenceTime;
	}

	public Date getCaseManagementDate() {
		return caseManagementDate;
	}

	public void setCaseManagementDate(Date caseManagementDate) {
		this.caseManagementDate = caseManagementDate;
	}

	public long getControlOrderId() {
		return controlOrderId;
	}

	public void setControlOrderId(long controlOrderId) {
		this.controlOrderId = controlOrderId;
	}

	public Date getYoDischargeDate() {
		return yoDischargeDate;
	}

	public void setYoDischargeDate(Date yoDischargeDate) {
		this.yoDischargeDate = yoDischargeDate;
	}

	public Date getYsDischargeDate() {
		return ysDischargeDate;
	}

	public void setYsDischargeDate(Date ysDischargeDate) {
		this.ysDischargeDate = ysDischargeDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getOvrYoDischargeDate() {
		return ovrYoDischargeDate;
	}

	public void setOvrYoDischargeDate(Date ovrYoDischargeDate) {
		this.ovrYoDischargeDate = ovrYoDischargeDate;
	}

	public Date getOvrYsDischargeDate() {
		return ovrYsDischargeDate;
	}

	public void setOvrYsDischargeDate(Date ovrYsDischargeDate) {
		this.ovrYsDischargeDate = ovrYsDischargeDate;
	}

	public Date getOvrDischargeDate() {
		return ovrDischargeDate;
	}

	public void setOvrDischargeDate(Date ovrDischargeDate) {
		this.ovrDischargeDate = ovrDischargeDate;
	}

	public Date getParoleEligiblityDate() {
		return paroleEligiblityDate;
	}

	public void setParoleEligiblityDate(Date paroleEligiblityDate) {
		this.paroleEligiblityDate = paroleEligiblityDate;
	}

	public Date getOvrParoleEligiblityDate() {
		return ovrParoleEligiblityDate;
	}

	public void setOvrParoleEligiblityDate(Date ovrParoleEligiblityDate) {
		this.ovrParoleEligiblityDate = ovrParoleEligiblityDate;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getOvrEarliestSentStartDate() {
		return ovrEarliestSentStartDate;
	}

	public void setOvrEarliestSentStartDate(Date ovrEarliestSentStartDate) {
		this.ovrEarliestSentStartDate = ovrEarliestSentStartDate;
	}

	public String getPrintedFlag() {
		return printedFlag;
	}

	public void setPrintedFlag(String printedFlag) {
		this.printedFlag = printedFlag;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public long getOvrAdultAggDays() {
		return ovrAdultAggDays;
	}

	public void setOvrAdultAggDays(long ovrAdultAggDays) {
		this.ovrAdultAggDays = ovrAdultAggDays;
	}

	public long getOvrYouAggDays() {
		return ovrYouAggDays;
	}

	public void setOvrYouAggDays(long ovrYouAggDays) {
		this.ovrYouAggDays = ovrYouAggDays;
	}

	public Date getIntDischargeDate() {
		return intDischargeDate;
	}

	public void setIntDischargeDate(Date intDischargeDate) {
		this.intDischargeDate = intDischargeDate;
	}

	public Date getOvrIntDischargeDate() {
		return ovrIntDischargeDate;
	}

	public void setOvrIntDischargeDate(Date ovrIntDischargeDate) {
		this.ovrIntDischargeDate = ovrIntDischargeDate;
	}

	public Date getParoleEndDate() {
		return paroleEndDate;
	}

	public void setParoleEndDate(Date paroleEndDate) {
		this.paroleEndDate = paroleEndDate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
