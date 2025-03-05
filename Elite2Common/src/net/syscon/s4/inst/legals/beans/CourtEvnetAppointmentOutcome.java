package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CourtEvnetAppointmentOutcome extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("eventClass")
	private String eventClass;
	@JsonProperty("eventDate")
	private Date eventDate;
	@JsonProperty("eventId")
	private BigDecimal eventId;
	@JsonProperty("eventOutcome")
	private String eventOutcome;
	@JsonProperty("eventOutcomeDesc")
	private String eventOutcomeDesc;
	@JsonProperty("eventStatus")
	private String eventStatus;
	@JsonProperty("eventStatusDesc")
	private String eventStatusDesc;
	@JsonProperty("eventSubType")
	private String eventSubType;
	@JsonProperty("eventSubTypeDesc")
	private String eventSubTypeDesc;
	@JsonProperty("eventType")
	private String eventType;
	@JsonProperty("eventTypeDesc")
	private String eventTypeDesc;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("unexcusedAbsenceFlag")
	private String unexcusedAbsenceFlag;
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("courtEventId")
	private BigDecimal courtEventId;

	@JsonProperty("sessionEventId")
	private BigDecimal sessionEventId;

	@JsonProperty("courtEvntSanctDtlId")
	private BigDecimal courtEvntSanctDtlId;
	@JsonProperty("recordSanctionRewardCount")
	private BigDecimal recordSanctionRewardCount;
	@JsonProperty("countType")
	private String countType;
	@JsonProperty("adjournedFlag")
	private String adjournedFlag;
	@JsonProperty("courtEventDate")
	private Date courtEventDate;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("linkFlag")
	private String linkFlag;

	@JsonProperty("keepInlist")
	private Boolean keepInlist;

	@JsonProperty("courtEventAvail")
	private String courtEventAvail;

	@JsonProperty("courtEventLinked")
	private String courtEventLinked;

	@JsonProperty("programDesc")
	private String programDesc;

	@JsonProperty("activityDesc")
	private String activityDesc;

	@JsonProperty("recordType")
	private String recordType;

	@JsonProperty("recordEventId")
	private BigDecimal recordEventId;

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEventClass() {
		return eventClass;
	}

	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventOutcome() {
		return eventOutcome;
	}

	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public String getEventOutcomeDesc() {
		return eventOutcomeDesc;
	}

	public void setEventOutcomeDesc(String eventOutcomeDesc) {
		this.eventOutcomeDesc = eventOutcomeDesc;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventStatusDesc() {
		return eventStatusDesc;
	}

	public void setEventStatusDesc(String eventStatusDesc) {
		this.eventStatusDesc = eventStatusDesc;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getEventSubTypeDesc() {
		return eventSubTypeDesc;
	}

	public void setEventSubTypeDesc(String eventSubTypeDesc) {
		this.eventSubTypeDesc = eventSubTypeDesc;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTypeDesc() {
		return eventTypeDesc;
	}

	public void setEventTypeDesc(String eventTypeDesc) {
		this.eventTypeDesc = eventTypeDesc;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUnexcusedAbsenceFlag() {
		return unexcusedAbsenceFlag;
	}

	public void setUnexcusedAbsenceFlag(String unexcusedAbsenceFlag) {
		this.unexcusedAbsenceFlag = unexcusedAbsenceFlag;
	}

	public String getCaseLoadId() {
		return caseLoadId;
	}

	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
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

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getCourtEventId() {
		return courtEventId;
	}

	public void setCourtEventId(BigDecimal courtEventId) {
		this.courtEventId = courtEventId;
	}

	public BigDecimal getSessionEventId() {
		return sessionEventId;
	}

	public void setSessionEventId(BigDecimal sessionEventId) {
		this.sessionEventId = sessionEventId;
	}

	public BigDecimal getCourtEvntSanctDtlId() {
		return courtEvntSanctDtlId;
	}

	public void setCourtEvntSanctDtlId(BigDecimal courtEvntSanctDtlId) {
		this.courtEvntSanctDtlId = courtEvntSanctDtlId;
	}

	public BigDecimal getRecordSanctionRewardCount() {
		return recordSanctionRewardCount;
	}

	public void setRecordSanctionRewardCount(BigDecimal recordSanctionRewardCount) {
		this.recordSanctionRewardCount = recordSanctionRewardCount;
	}

	public String getCountType() {
		return countType;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public String getAdjournedFlag() {
		return adjournedFlag;
	}

	public void setAdjournedFlag(String adjournedFlag) {
		this.adjournedFlag = adjournedFlag;
	}

	public Date getCourtEventDate() {
		return courtEventDate;
	}

	public void setCourtEventDate(Date courtEventDate) {
		this.courtEventDate = courtEventDate;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public String getLinkFlag() {
		return linkFlag;
	}

	public void setLinkFlag(String linkFlag) {
		this.linkFlag = linkFlag;
	}

	public Boolean getKeepInlist() {
		return keepInlist;
	}

	public void setKeepInlist(Boolean keepInlist) {
		this.keepInlist = keepInlist;
	}

	public String getCourtEventAvail() {
		return courtEventAvail;
	}

	public void setCourtEventAvail(String courtEventAvail) {
		this.courtEventAvail = courtEventAvail;
	}

	public String getCourtEventLinked() {
		return courtEventLinked;
	}

	public void setCourtEventLinked(String courtEventLinked) {
		this.courtEventLinked = courtEventLinked;
	}

	public String getProgramDesc() {
		return programDesc;
	}

	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public BigDecimal getRecordEventId() {
		return recordEventId;
	}

	public void setRecordEventId(BigDecimal recordEventId) {
		this.recordEventId = recordEventId;
	}

}
