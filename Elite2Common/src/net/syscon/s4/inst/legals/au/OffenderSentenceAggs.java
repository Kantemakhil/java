package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentenceAggs extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("calcPosRelDate")
	private Date calcPosRelDate;

	@JsonProperty("nbtParoleReviewDate")
	private String nbtParoleReviewDate;

	@JsonProperty("mostSeriousSentenceSeq")
	private long mostSeriousSentenceSeq;

	@JsonProperty("nbtHearingDate")
	private Date nbtHearingDate;

	@JsonProperty("hearingDate")
	private Date hearingDate;

	@JsonProperty("pbPosRelDate")
	private Date pbPosRelDate;

	@JsonProperty("nbtLatestPbRelease")
	private Date nbtLatestPbRelease;

	@JsonProperty("nbtEprhdDate")
	private Date nbtEprhdDate;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("finalSentExpDate")
	private Date finalSentExpDate;

	@JsonProperty("ovrPosRelDate")
	private Date ovrPosRelDate;

	@JsonProperty("calcPosProbDate")
	private Date calcPosProbDate;

	@JsonProperty("ovrPosProbDate")
	private Date ovrPosProbDate;

	@JsonProperty("calcProbEffectDate")
	private Date calcProbEffectDate;

	@JsonProperty("ovrProbEffectDate")
	private Date ovrProbEffectDate;

	@JsonProperty("totalLengthHours")
	private long totalLengthHours;

	@JsonProperty("totalLengthDays")
	private long totalLengthDays;

	@JsonProperty("totalLengthWeeks")
	private long totalLengthWeeks;

	@JsonProperty("totalLengthYears")
	private long totalLengthYears;

	@JsonProperty("totalLengthMonths")
	private long totalLengthMonths;

	@JsonProperty("calcProbExpDate")
	private Date calcProbExpDate;

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

	@JsonProperty("ovrSentExpDate")
	private Date ovrSentExpDate;

	@JsonProperty("totalSatisfiedTime")
	private long totalSatisfiedTime;

	@JsonProperty("earliestSentStartDate")
	private Date earliestSentStartDate;

	@JsonProperty("paroleReviewDate")
	private Date paroleReviewDate;

	@JsonProperty("paroleDate")
	private Date paroleDate;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("code")
	private String code;

	@JsonProperty("dspCalcPosRelDate")
	private Date dspCalcPosRelDate;

	@JsonProperty("dspOverrideFlag")
	private String dspOverrideFlag;

	@JsonProperty("nbtSentenceLengthText")
	private String nbtSentenceLengthText;

	public String getNbtSentenceLengthText() {
		return nbtSentenceLengthText;
	}

	public void setNbtSentenceLengthText(String nbtSentenceLengthText) {
		this.nbtSentenceLengthText = nbtSentenceLengthText;
	}

	public Date getDspCalcPosRelDate() {
		return dspCalcPosRelDate;
	}

	public void setDspCalcPosRelDate(Date dspCalcPosRelDate) {
		this.dspCalcPosRelDate = dspCalcPosRelDate;
	}

	public String getDspOverrideFlag() {
		return dspOverrideFlag;
	}

	public void setDspOverrideFlag(String dspOverrideFlag) {
		this.dspOverrideFlag = dspOverrideFlag;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCalcPosRelDate() {
		return calcPosRelDate;
	}

	public void setCalcPosRelDate(Date calcPosRelDate) {
		this.calcPosRelDate = calcPosRelDate;
	}

	public String getNbtParoleReviewDate() {
		return nbtParoleReviewDate;
	}

	public void setNbtParoleReviewDate(String nbtParoleReviewDate) {
		this.nbtParoleReviewDate = nbtParoleReviewDate;
	}

	public long getMostSeriousSentenceSeq() {
		return mostSeriousSentenceSeq;
	}

	public void setMostSeriousSentenceSeq(long mostSeriousSentenceSeq) {
		this.mostSeriousSentenceSeq = mostSeriousSentenceSeq;
	}

	public Date getNbtHearingDate() {
		return nbtHearingDate;
	}

	public void setNbtHearingDate(Date nbtHearingDate) {
		this.nbtHearingDate = nbtHearingDate;
	}

	public Date getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(Date hearingDate) {
		this.hearingDate = hearingDate;
	}

	public Date getPbPosRelDate() {
		return pbPosRelDate;
	}

	public void setPbPosRelDate(Date pbPosRelDate) {
		this.pbPosRelDate = pbPosRelDate;
	}

	public Date getNbtLatestPbRelease() {
		return nbtLatestPbRelease;
	}

	public void setNbtLatestPbRelease(Date nbtLatestPbRelease) {
		this.nbtLatestPbRelease = nbtLatestPbRelease;
	}

	public Date getNbtEprhdDate() {
		return nbtEprhdDate;
	}

	public void setNbtEprhdDate(Date nbtEprhdDate) {
		this.nbtEprhdDate = nbtEprhdDate;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getFinalSentExpDate() {
		return finalSentExpDate;
	}

	public void setFinalSentExpDate(Date finalSentExpDate) {
		this.finalSentExpDate = finalSentExpDate;
	}

	public Date getOvrPosRelDate() {
		return ovrPosRelDate;
	}

	public void setOvrPosRelDate(Date ovrPosRelDate) {
		this.ovrPosRelDate = ovrPosRelDate;
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

	public long getTotalLengthHours() {
		return totalLengthHours;
	}

	public void setTotalLengthHours(long totalLengthHours) {
		this.totalLengthHours = totalLengthHours;
	}

	public long getTotalLengthDays() {
		return totalLengthDays;
	}

	public void setTotalLengthDays(long totalLengthDays) {
		this.totalLengthDays = totalLengthDays;
	}

	public long getTotalLengthWeeks() {
		return totalLengthWeeks;
	}

	public void setTotalLengthWeeks(long totalLengthWeeks) {
		this.totalLengthWeeks = totalLengthWeeks;
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

	public Date getCalcProbExpDate() {
		return calcProbExpDate;
	}

	public void setCalcProbExpDate(Date calcProbExpDate) {
		this.calcProbExpDate = calcProbExpDate;
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

	public Date getOvrSentExpDate() {
		return ovrSentExpDate;
	}

	public void setOvrSentExpDate(Date ovrSentExpDate) {
		this.ovrSentExpDate = ovrSentExpDate;
	}

	public long getTotalSatisfiedTime() {
		return totalSatisfiedTime;
	}

	public void setTotalSatisfiedTime(long totalSatisfiedTime) {
		this.totalSatisfiedTime = totalSatisfiedTime;
	}

	public Date getEarliestSentStartDate() {
		return earliestSentStartDate;
	}

	public void setEarliestSentStartDate(Date earliestSentStartDate) {
		this.earliestSentStartDate = earliestSentStartDate;
	}

	public Date getParoleReviewDate() {
		return paroleReviewDate;
	}

	public void setParoleReviewDate(Date paroleReviewDate) {
		this.paroleReviewDate = paroleReviewDate;
	}

	public Date getParoleDate() {
		return paroleDate;
	}

	public void setParoleDate(Date paroleDate) {
		this.paroleDate = paroleDate;
	}

	@Override
	public String toString() {
		return "OffenderSentenceAggs [calcPosRelDate=" + calcPosRelDate + ", nbtParoleReviewDate=" + nbtParoleReviewDate
				+ ", mostSeriousSentenceSeq=" + mostSeriousSentenceSeq + ", nbtHearingDate=" + nbtHearingDate
				+ ", pbPosRelDate=" + pbPosRelDate + ", nbtLatestPbRelease=" + nbtLatestPbRelease + ", nbtEprhdDate="
				+ nbtEprhdDate + ", offenderBookId=" + offenderBookId + ", finalSentExpDate=" + finalSentExpDate
				+ ", ovrPosRelDate=" + ovrPosRelDate + ", calcPosProbDate=" + calcPosProbDate + ", ovrPosProbDate="
				+ ovrPosProbDate + ", calcProbEffectDate=" + calcProbEffectDate + ", ovrProbEffectDate="
				+ ovrProbEffectDate + ", totalLengthHours=" + totalLengthHours + ", totalLengthDays=" + totalLengthDays
				+ ", totalLengthWeeks=" + totalLengthWeeks + ", totalLengthYears=" + totalLengthYears
				+ ", totalLengthMonths=" + totalLengthMonths + ", calcProbExpDate=" + calcProbExpDate
				+ ", ovrProbExpDate=" + ovrProbExpDate + ", ovrPrlExpDate=" + ovrPrlExpDate + ", sentCalcNeededFlag="
				+ sentCalcNeededFlag + ", totalAwolDays=" + totalAwolDays + ", eligibleGoodtimeDays="
				+ eligibleGoodtimeDays + ", totalServedTime=" + totalServedTime + ", totalWorkTime=" + totalWorkTime
				+ ", goodtimeRevokedDays=" + goodtimeRevokedDays + ", revokedParoleDays=" + revokedParoleDays
				+ ", ovrSentExpDate=" + ovrSentExpDate + ", totalSatisfiedTime=" + totalSatisfiedTime
				+ ", earliestSentStartDate=" + earliestSentStartDate + ", paroleReviewDate=" + paroleReviewDate
				+ ", paroleDate=" + paroleDate + ", createUserId=" + createUserId + ", createDateTime=" + createDateTime
				+ ", modifyDateTime=" + modifyDateTime + ", modifyUserId=" + modifyUserId + ", description="
				+ description + ", code=" + code + "]";
	}

}
