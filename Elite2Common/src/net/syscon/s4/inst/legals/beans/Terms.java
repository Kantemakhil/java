package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Terms  extends BaseModel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@JsonProperty("termSeq")
	private Integer termSeq;
	
	@JsonProperty("sentenceTermCodeDescription")
	private String sentenceTermCodeDescription;
		
	@JsonProperty("sentenceTermCode")
	private String sentenceTermCode;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;
	
	@JsonProperty("lifeSentenceFlag")
	private String lifeSentenceFlag;
		
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("years")
	private Integer years;
	
	@JsonProperty("months")
	private Integer months;
	
	@JsonProperty("weeks")
	private Integer weeks;
	
	@JsonProperty("days")
	private Integer days;
		
	@JsonProperty("hours")
	private Integer hours;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("commitFlag")
	private String commitFlag;
	
	@JsonProperty("sentenceCalculation")
	private SentenceCalculation sentenceCalculation;
	
	@JsonProperty("endDate")
	private Date endDate;
	
	@JsonProperty("startTime")
	private Date startTime;
	
	@JsonProperty("ovrStartDate")
	private Date ovrStartDate;
	
	@JsonProperty("ovrStartTime")
	private Date ovrStartTime;
	
	@JsonProperty("endTime")
	private Date endTime;
	
	@JsonProperty("ovrEndDate")
	private Date ovrEndDate;
	
	@JsonProperty("ovrEndTime")
	private Date ovrEndTime;
	
	@JsonProperty("workHours")
	private Integer workHours;
	
	@JsonProperty("attendenceHours")
	private Integer attendenceHours;
	
	@JsonProperty("gapSeq")
	private Integer gapSeq;
	
	@JsonProperty("paroleSupervision")
	private String paroleSupervision;
	
	@JsonProperty("parentSentenceData")
	private Sentences parentSentenceData;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("teamCode")
	private String teamCode;
	@JsonProperty("description")
	private String description;
	
	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("dummySentenceId")
	private Integer dummySentenceId;
	
	public Integer getDummySentenceId() {
		return dummySentenceId;
	}

	public void setDummySentenceId(final Integer dummySentenceId) {
		this.dummySentenceId = dummySentenceId;
	}
	
	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public Sentences getParentSentenceData() {
		return parentSentenceData;
	}

	public void setParentSentenceData(final Sentences parentSentenceData) {
		this.parentSentenceData = parentSentenceData;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Integer getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}

	public Integer getAttendenceHours() {
		return attendenceHours;
	}

	public void setAttendenceHours(Integer attendenceHours) {
		this.attendenceHours = attendenceHours;
	}

	public Integer getGapSeq() {
		return gapSeq;
	}

	public void setGapSeq(Integer gapSeq) {
		this.gapSeq = gapSeq;
	}

	public String getParoleSupervision() {
		return paroleSupervision;
	}

	public void setParoleSupervision(String paroleSupervision) {
		this.paroleSupervision = paroleSupervision;
	}

	public Integer getTermSeq() {
		return termSeq;
	}

	public void setTermSeq(Integer termSeq) {
		this.termSeq = termSeq;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSentenceTermCodeDescription() {
		return sentenceTermCodeDescription;
	}

	public void setSentenceTermCodeDescription(String sentenceTermCodeDescription) {
		this.sentenceTermCodeDescription = sentenceTermCodeDescription;
	}

	public String getSentenceTermCode() {
		return sentenceTermCode;
	}

	public void setSentenceTermCode(String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

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

	public String getLifeSentenceFlag() {
		return lifeSentenceFlag;
	}

	public void setLifeSentenceFlag(String lifeSentenceFlag) {
		this.lifeSentenceFlag = lifeSentenceFlag;
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

	public String getCommitFlag() {
		return commitFlag;
	}

	public void setCommitFlag(String commitFlag) {
		this.commitFlag = commitFlag;
	}

	public SentenceCalculation getSentenceCalculation() {
		return sentenceCalculation;
	}

	public void setSentenceCalculation(SentenceCalculation sentenceCalculation) {
		this.sentenceCalculation = sentenceCalculation;
	}
	
	
	
	
}
