package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VSentenceTermAggs extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("sentenceSeq")
	private long sentenceSeq;
	@JsonProperty("ver")
	private long ver;
	@JsonProperty("sentenceCategory")
	private String sentenceCategory;
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	@JsonProperty("sentenceTermCode")
	private String sentenceTermCode;
	@JsonProperty("dspTermPeriod")
	private String dspTermPeriod;
	@JsonProperty("dspOverrideFlag")
	private String dspOverrideFlag;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("dspStartDate")
	private Date dspStartDate;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("dspOverrideFlag1")
	private String dspOverrideFlag1;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("dspEndDate")
	private Date dspEndDate;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("ovrStartDate")
	private Date ovrStartDate;
	@JsonProperty("ovrStartTime")
	private Date ovrStartTime;
	@JsonProperty("ovrEndDate")
	private Date ovrEndDate;
	@JsonProperty("ovrEndTime")
	private Date ovrEndTime;
	@JsonProperty("termPeriod")
	private String termPeriod;
	@JsonProperty("aggsDisplayOrder")
	private long aggsDisplayOrder;
	@JsonProperty("paroleSupervision")
	private String paroleSupervision;
	@JsonProperty("nbtParoleSupervision")
	private String nbtParoleSupervision;

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

	@JsonProperty("nbtParoleSuper")
	private boolean nbtParoleSuper;

	@JsonProperty("termSeq")
	private long termSeq;

	@JsonProperty("sentenceTermSeq")
	private long sentenceTermSeq;

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

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
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

	public String getDspOverrideFlag() {
		return dspOverrideFlag;
	}

	public void setDspOverrideFlag(String dspOverrideFlag) {
		this.dspOverrideFlag = dspOverrideFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDspStartDate() {
		return dspStartDate;
	}

	public void setDspStartDate(Date dspStartDate) {
		this.dspStartDate = dspStartDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getDspOverrideFlag1() {
		return dspOverrideFlag1;
	}

	public void setDspOverrideFlag1(String dspOverrideFlag1) {
		this.dspOverrideFlag1 = dspOverrideFlag1;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDspEndDate() {
		return dspEndDate;
	}

	public void setDspEndDate(Date dspEndDate) {
		this.dspEndDate = dspEndDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getOvrStartDate() {
		return ovrStartDate;
	}

	public void setOvrStartDate(Date ovrStartDate) {
		this.ovrStartDate = ovrStartDate;
	}

	public Date getOvrStartTime() {
		return ovrStartTime;
	}

	public void setOvrStartTime(Date ovrStartTime) {
		this.ovrStartTime = ovrStartTime;
	}

	public Date getOvrEndDate() {
		return ovrEndDate;
	}

	public void setOvrEndDate(Date ovrEndDate) {
		this.ovrEndDate = ovrEndDate;
	}

	public Date getOvrEndTime() {
		return ovrEndTime;
	}

	public void setOvrEndTime(Date ovrEndTime) {
		this.ovrEndTime = ovrEndTime;
	}

	public String getTermPeriod() {
		return termPeriod;
	}

	public void setTermPeriod(String termPeriod) {
		this.termPeriod = termPeriod;
	}

	public long getAggsDisplayOrder() {
		return aggsDisplayOrder;
	}

	public void setAggsDisplayOrder(long aggsDisplayOrder) {
		this.aggsDisplayOrder = aggsDisplayOrder;
	}

	public String getParoleSupervision() {
		return paroleSupervision;
	}

	public void setParoleSupervision(String paroleSupervision) {
		this.paroleSupervision = paroleSupervision;
	}

	public String getNbtParoleSupervision() {
		return nbtParoleSupervision;
	}

	public void setNbtParoleSupervision(String nbtParoleSupervision) {
		this.nbtParoleSupervision = nbtParoleSupervision;
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

	public boolean isNbtParoleSuper() {
		return nbtParoleSuper;
	}

	public void setNbtParoleSuper(boolean nbtParoleSuper) {
		this.nbtParoleSuper = nbtParoleSuper;
	}

	public long getSentenceTermSeq() {
		return sentenceTermSeq;
	}

	public void setSentenceTermSeq(long sentenceTermSeq) {
		this.sentenceTermSeq = sentenceTermSeq;
	}

	@Override
	public String toString() {
		return "VSentenceTermAggs [sentenceSeq=" + sentenceSeq + ", ver=" + ver + ", sentenceCategory="
				+ sentenceCategory + ", sentenceCalcType=" + sentenceCalcType + ", sentenceTermCode=" + sentenceTermCode
				+ ", dspTermPeriod=" + dspTermPeriod + ", dspOverrideFlag=" + dspOverrideFlag + ", startDate="
				+ startDate + ", dspStartDate=" + dspStartDate + ", startTime=" + startTime + ", dspOverrideFlag1="
				+ dspOverrideFlag1 + ", endDate=" + endDate + ", dspEndDate=" + dspEndDate + ", endTime=" + endTime
				+ ", offenderBookId=" + offenderBookId + ", ovrStartDate=" + ovrStartDate + ", ovrStartTime="
				+ ovrStartTime + ", ovrEndDate=" + ovrEndDate + ", ovrEndTime=" + ovrEndTime + ", termPeriod="
				+ termPeriod + ", aggsDisplayOrder=" + aggsDisplayOrder + ", paroleSupervision=" + paroleSupervision
				+ ", nbtParoleSupervision=" + nbtParoleSupervision + ", createUserId=" + createUserId
				+ ", createDateTime=" + createDateTime + ", modifyDateTime=" + modifyDateTime + ", modifyUserId="
				+ modifyUserId + ", description=" + description + ", code=" + code + "]";
	}

}
