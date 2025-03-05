package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSenetenceTerms extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;
	
	@JsonProperty("termSeq")
	private Long termSeq;
	
	@JsonProperty("sentenceTermCode")
	private String sentenceTermCode;
	
	@JsonProperty("years")
	private Long years;
	
	@JsonProperty("months")
	private Long months;
	
	@JsonProperty("weeks")
	private Long weeks;
	
	@JsonProperty("days")
	private Long days;
	
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
	private Long hours;
	
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

	public Long getTermSeq() {
		return termSeq;
	}

	public void setTermSeq(Long termSeq) {
		this.termSeq = termSeq;
	}

	public String getSentenceTermCode() {
		return sentenceTermCode;
	}

	public void setSentenceTermCode(String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

	public Long getYears() {
		return years;
	}

	public void setYears(Long years) {
		this.years = years;
	}

	public Long getMonths() {
		return months;
	}

	public void setMonths(Long months) {
		this.months = months;
	}

	public Long getWeeks() {
		return weeks;
	}

	public void setWeeks(Long weeks) {
		this.weeks = weeks;
	}

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
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

	public Long getHours() {
		return hours;
	}

	public void setHours(Long hours) {
		this.hours = hours;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	
}
