package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderSentenceTerms extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("chargeSeq")
	private long chargeSeq;

	@JsonProperty("termSeq")
	private long termSeq;

	@JsonProperty("sentenceSeq")
	private long sentenceSeq;

	@JsonProperty("version")
	private long version;

	@JsonProperty("jurisdictionCode")
	private String jurisdictionCode;

	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;

	@JsonProperty("sentenceTermCode")
	private String sentenceTermCode;

	@JsonProperty("dspTermPeriod")
	private String dspTermPeriod;

	@JsonProperty("nbtSentenceTermCode")
	private String nbtSentenceTermCode;

	@JsonProperty("termPeriod")
	private String termPeriod;

	@JsonProperty("nbtSentenceTermCode2")
	private String nbtSentenceTermCode2;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("nbtSentenceTermCode3")
	private String nbtSentenceTermCode3;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("endTime")
	private Date endTime;

	@JsonProperty("startTime")
	private Date startTime;

	@JsonProperty("ovrStartDate")
	private Date ovrStartDate;

	@JsonProperty("ovrEndDate")
	private Date ovrEndDate;

	@JsonProperty("paroleSupervision")
	private String paroleSupervision;

	@JsonProperty("nbtParoleSup")
	private String nbtParoleSup;

	@JsonProperty("aggregateFlag")
	private String aggregateFlag;

	@JsonProperty("vSentCalcNeededFlag")
	private String vSentCalcNeededFlag;

	@JsonProperty("calcFlag")
	private String calcFlag;

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

	@JsonProperty("nbtParoleSupervision")
	private boolean nbtParoleSupervision;

	@JsonProperty("sentenceStatus")
	private String sentenceStatus;

	@JsonProperty("sentenceCategory")
	private String sentenceCategory;

	@JsonProperty("sentenceStartDate")
	private Date sentenceStartDate;

	@JsonProperty("probableReleaseDate")
	private Date probableReleaseDate;

	@JsonProperty("sentenceExpiryDate")
	private Date sentenceExpiryDate;

	@JsonProperty("convictionDate")
	private Date convictionDate;

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(long chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public long getTermSeq() {
		return termSeq;
	}

	public void setTermSeq(long termSeq) {
		this.termSeq = termSeq;
	}

	public long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
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

	public String getSentenceTermCode() {
		return sentenceTermCode;
	}

	public void setSentenceTermCode(String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

	public String getDspTermPeriod() {
		return dspTermPeriod;
	}

	public void setDspTermPeriod(String dspTermPeriod) {
		this.dspTermPeriod = dspTermPeriod;
	}

	public String getNbtSentenceTermCode() {
		return nbtSentenceTermCode;
	}

	public void setNbtSentenceTermCode(String nbtSentenceTermCode) {
		this.nbtSentenceTermCode = nbtSentenceTermCode;
	}

	public String getTermPeriod() {
		return termPeriod;
	}

	public void setTermPeriod(String termPeriod) {
		this.termPeriod = termPeriod;
	}

	public String getNbtSentenceTermCode2() {
		return nbtSentenceTermCode2;
	}

	public void setNbtSentenceTermCode2(String nbtSentenceTermCode2) {
		this.nbtSentenceTermCode2 = nbtSentenceTermCode2;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getNbtSentenceTermCode3() {
		return nbtSentenceTermCode3;
	}

	public void setNbtSentenceTermCode3(String nbtSentenceTermCode3) {
		this.nbtSentenceTermCode3 = nbtSentenceTermCode3;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getOvrStartDate() {
		return ovrStartDate;
	}

	public void setOvrStartDate(Date ovrStartDate) {
		this.ovrStartDate = ovrStartDate;
	}

	public Date getOvrEndDate() {
		return ovrEndDate;
	}

	public void setOvrEndDate(Date ovrEndDate) {
		this.ovrEndDate = ovrEndDate;
	}

	public String getParoleSupervision() {
		return paroleSupervision;
	}

	public void setParoleSupervision(String paroleSupervision) {
		this.paroleSupervision = paroleSupervision;
	}

	public String getNbtParoleSup() {
		return nbtParoleSup;
	}

	public void setNbtParoleSup(String nbtParoleSup) {
		this.nbtParoleSup = nbtParoleSup;
	}

	public String getAggregateFlag() {
		return aggregateFlag;
	}

	public void setAggregateFlag(String aggregateFlag) {
		this.aggregateFlag = aggregateFlag;
	}

	public String getvSentCalcNeededFlag() {
		return vSentCalcNeededFlag;
	}

	public void setvSentCalcNeededFlag(String vSentCalcNeededFlag) {
		this.vSentCalcNeededFlag = vSentCalcNeededFlag;
	}

	public String getCalcFlag() {
		return calcFlag;
	}

	public void setCalcFlag(String calcFlag) {
		this.calcFlag = calcFlag;
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

	public boolean isNbtParoleSupervision() {
		return nbtParoleSupervision;
	}

	public void setNbtParoleSupervision(boolean nbtParoleSupervision) {
		this.nbtParoleSupervision = nbtParoleSupervision;
	}

	public String getSentenceStatus() {
		return sentenceStatus;
	}

	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public Date getSentenceStartDate() {
		return sentenceStartDate;
	}

	public void setSentenceStartDate(Date sentenceStartDate) {
		this.sentenceStartDate = sentenceStartDate;
	}

	public Date getProbableReleaseDate() {
		return probableReleaseDate;
	}

	public void setProbableReleaseDate(Date probableReleaseDate) {
		this.probableReleaseDate = probableReleaseDate;
	}

	public Date getSentenceExpiryDate() {
		return sentenceExpiryDate;
	}

	public void setSentenceExpiryDate(Date sentenceExpiryDate) {
		this.sentenceExpiryDate = sentenceExpiryDate;
	}

	public Date getConvictionDate() {
		return convictionDate;
	}

	public void setConvictionDate(Date convictionDate) {
		this.convictionDate = convictionDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "VOffenderSentenceTerms [offenderBookId=" + offenderBookId + ", chargeSeq=" + chargeSeq + ", termSeq="
				+ termSeq + ", sentenceSeq=" + sentenceSeq + ", version=" + version + ", jurisdictionCode="
				+ jurisdictionCode + ", sentenceCalcType=" + sentenceCalcType + ", sentenceTermCode=" + sentenceTermCode
				+ ", dspTermPeriod=" + dspTermPeriod + ", nbtSentenceTermCode=" + nbtSentenceTermCode + ", termPeriod="
				+ termPeriod + ", nbtSentenceTermCode2=" + nbtSentenceTermCode2 + ", startDate=" + startDate
				+ ", nbtSentenceTermCode3=" + nbtSentenceTermCode3 + ", endDate=" + endDate + ", endTime=" + endTime
				+ ", ovrStartDate=" + ovrStartDate + ", ovrEndDate=" + ovrEndDate + ", paroleSupervision="
				+ paroleSupervision + ", nbtParoleSup=" + nbtParoleSup + ", aggregateFlag=" + aggregateFlag
				+ ", vSentCalcNeededFlag=" + vSentCalcNeededFlag + ", calcFlag=" + calcFlag + ", createUserId="
				+ createUserId + ", createDateTime=" + createDateTime + ", modifyDateTime=" + modifyDateTime
				+ ", modifyUserId=" + modifyUserId + ", description=" + description + ", code=" + code + "]";
	}

}
