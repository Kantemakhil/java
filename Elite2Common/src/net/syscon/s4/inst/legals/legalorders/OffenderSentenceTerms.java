package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentenceTerms extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("sentenceSeq")
	private long sentenceSeq;
	@JsonProperty("termSeq")
	private long termSeq;
	@JsonProperty("sentenceTermCode")
	private String sentenceTermCode;
	@JsonProperty("years")
	private int years;
	@JsonProperty("months")
	private int months;
	@JsonProperty("weeks")
	private int weeks;
	@JsonProperty("days")
	private int days;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("lifeSentenceFlag")
	private String lifeSentenceFlag;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("hours")
	private int hours;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("paroleSupervision")
	private String paroleSupervision;

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public long getTermSeq() {
		return termSeq;
	}

	public void setTermSeq(long termSeq) {
		this.termSeq = termSeq;
	}

	public String getSentenceTermCode() {
		return sentenceTermCode;
	}

	public void setSentenceTermCode(String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLifeSentenceFlag() {
		return lifeSentenceFlag;
	}

	public void setLifeSentenceFlag(String lifeSentenceFlag) {
		this.lifeSentenceFlag = lifeSentenceFlag;
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

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getParoleSupervision() {
		return paroleSupervision;
	}

	public void setParoleSupervision(String paroleSupervision) {
		this.paroleSupervision = paroleSupervision;
	}

	@Override
	public String toString() {
		return "OffenderSentenceTerms [offenderBookId=" + offenderBookId + ", sentenceSeq=" + sentenceSeq + ", termSeq="
				+ termSeq + ", sentenceTermCode=" + sentenceTermCode + ", years=" + years + ", months=" + months
				+ ", weeks=" + weeks + ", days=" + days + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", lifeSentenceFlag=" + lifeSentenceFlag + ", modifyDatetime=" + modifyDatetime + ", modifyUserId="
				+ modifyUserId + ", createDatetime=" + createDatetime + ", createUserId=" + createUserId + ", hours="
				+ hours + ", sealFlag=" + sealFlag + "]";
	}

}
