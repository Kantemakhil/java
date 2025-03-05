package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VOffenderVisits implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal agencyVisitSlotId;

	private String agyLocId;

	private BigDecimal checkSum;

	private String commentText;

	private Date endTime;

	private BigDecimal eventId;

	private String eventOutcome;

	private String eventStatus;

	private BigDecimal offenderBookId;

	private String offenderFirstName;

	private BigDecimal offenderId;

	private String offenderIdDisplay;

	private String offenderLastName;

	private BigDecimal offenderVisitId;

	private BigDecimal offenderVisitVisitorId;

	private String outcomeReasonCode;

	private BigDecimal raisedIncidentNumber;

	private String raisedIncidentType;

	private Date startTime;

	private Date visitDate;

	private BigDecimal visitInternalLocationId;

	private BigDecimal visitOffenderBookId;

	private String visitOwnerFlag;

	private String visitStatus;

	private String visitType;

	private String visitorCommentText;
	
	private String code;
	
	private String description;
	
	private Long totalRemainingVisits;
	
	private BigDecimal totalRemainingTime;
	
	private Date cycleEnds;
	
	private Long remainingVisitsType;
	
	private BigDecimal remainingTimeType;

	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;
	
	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("timeSlot")
	private String timeSlot;
	@JsonProperty("originalTimeSlot")
	private String originalTimeSlot;
	@JsonProperty("personId")
	private Integer personId;
	@JsonProperty("groupLeaderFlag")
	private String groupLeaderFlag;
	@JsonProperty("assistedVisitFlag")
	private String assistedVisitFlag;
	
	@JsonProperty("supLevel")
	private String supLevel;
	
	private Integer adultAge;
	private Integer person;
	private Integer adult;
	private Integer group;
	private String weekDay;
	
	private Date createDatetime;
	private Date modifyDatetime;
	
	@JsonProperty("warningMsg")
	private String warningMsg;
	
	@JsonProperty("insertCount")
	private Integer insertCount;
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("visitConfigType")
	private String visitConfigType;
	
	private String remainingTimeTypeTemp;

	private String totalRemainingTimeTemp;
	
	private String stimetemp;
	
	private String recordUserId;
	
	private String modifyUserId;

	public String getTotalRemainingTimeTemp() {
		return totalRemainingTimeTemp;
	}

	public void setTotalRemainingTimeTemp(String totalRemainingTimeTemp) {
		this.totalRemainingTimeTemp = totalRemainingTimeTemp;
	}
	
	public String getRemainingTimeTypeTemp() {
		return remainingTimeTypeTemp;
	}

	public void setRemainingTimeTypeTemp(String remainingTimeTypeTemp) {
		this.remainingTimeTypeTemp = remainingTimeTypeTemp;
	}
	
	public String getVisitConfigType() {
		return visitConfigType;
	}

	public void setVisitConfigType(String visitConfigType) {
		this.visitConfigType = visitConfigType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the warningMsg
	 */
	public String getWarningMsg() {
		return warningMsg;
	}

	/**
	 * @param warningMsg the warningMsg to set
	 */
	public void setWarningMsg(final String warningMsg) {
		this.warningMsg = warningMsg;
	}

	/**
	 * @return the insertCount
	 */
	public Integer getInsertCount() {
		return insertCount;
	}

	/**
	 * @param insertCount the insertCount to set
	 */
	public void setInsertCount(final Integer insertCount) {
		this.insertCount = insertCount;
	}

	public VOffenderVisits() {
		// VOffenderVisits
	}

	public BigDecimal getAgencyVisitSlotId() {
		return this.agencyVisitSlotId;
	}

	public void setAgencyVisitSlotId(BigDecimal agencyVisitSlotId) {
		this.agencyVisitSlotId = agencyVisitSlotId;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getCheckSum() {
		return this.checkSum;
	}

	public void setCheckSum(BigDecimal checkSum) {
		this.checkSum = checkSum;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getEventId() {
		return this.eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventOutcome() {
		return this.eventOutcome;
	}

	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public String getEventStatus() {
		return this.eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	public void setOffenderFirstName(String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	public void setOffenderLastName(String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}

	public BigDecimal getOffenderVisitId() {
		return this.offenderVisitId;
	}

	public void setOffenderVisitId(BigDecimal offenderVisitId) {
		this.offenderVisitId = offenderVisitId;
	}

	public BigDecimal getOffenderVisitVisitorId() {
		return this.offenderVisitVisitorId;
	}

	public void setOffenderVisitVisitorId(BigDecimal offenderVisitVisitorId) {
		this.offenderVisitVisitorId = offenderVisitVisitorId;
	}

	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public BigDecimal getRaisedIncidentNumber() {
		return this.raisedIncidentNumber;
	}

	public void setRaisedIncidentNumber(BigDecimal raisedIncidentNumber) {
		this.raisedIncidentNumber = raisedIncidentNumber;
	}

	public String getRaisedIncidentType() {
		return this.raisedIncidentType;
	}

	public void setRaisedIncidentType(String raisedIncidentType) {
		this.raisedIncidentType = raisedIncidentType;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getVisitDate() {
		return this.visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public BigDecimal getVisitInternalLocationId() {
		return this.visitInternalLocationId;
	}

	public void setVisitInternalLocationId(BigDecimal visitInternalLocationId) {
		this.visitInternalLocationId = visitInternalLocationId;
	}

	public BigDecimal getVisitOffenderBookId() {
		return this.visitOffenderBookId;
	}

	public void setVisitOffenderBookId(BigDecimal visitOffenderBookId) {
		this.visitOffenderBookId = visitOffenderBookId;
	}

	public String getVisitOwnerFlag() {
		return this.visitOwnerFlag;
	}

	public void setVisitOwnerFlag(String visitOwnerFlag) {
		this.visitOwnerFlag = visitOwnerFlag;
	}

	public String getVisitStatus() {
		return this.visitStatus;
	}

	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}

	public String getVisitType() {
		return this.visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getVisitorCommentText() {
		return this.visitorCommentText;
	}

	public void setVisitorCommentText(String visitorCommentText) {
		this.visitorCommentText = visitorCommentText;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the totalRemainingVisits
	 */
	public Long getTotalRemainingVisits() {
		return totalRemainingVisits;
	}

	/**
	 * @param totalRemainingVisits the totalRemainingVisits to set
	 */
	public void setTotalRemainingVisits(Long totalRemainingVisits) {
		this.totalRemainingVisits = totalRemainingVisits;
	}

	/**
	 * @return the totalRemainingTime
	 */
	public BigDecimal getTotalRemainingTime() {
		return totalRemainingTime;
	}

	/**
	 * @param totalRemainingTime the totalRemainingTime to set
	 */
	public void setTotalRemainingTime(BigDecimal totalRemainingTime) {
		this.totalRemainingTime = totalRemainingTime;
	}

	/**
	 * @return the cycleEnds
	 */
	public Date getCycleEnds() {
		return cycleEnds;
	}

	/**
	 * @param cycleEnds the cycleEnds to set
	 */
	public void setCycleEnds(Date cycleEnds) {
		this.cycleEnds = cycleEnds;
	}

	/**
	 * @return the remainingVisitsType
	 */
	public Long getRemainingVisitsType() {
		return remainingVisitsType;
	}

	/**
	 * @param remainingVisitsType the remainingVisitsType to set
	 */
	public void setRemainingVisitsType(Long remainingVisitsType) {
		this.remainingVisitsType = remainingVisitsType;
	}

	/**
	 * @return the remainingTimeType
	 */
	public BigDecimal getRemainingTimeType() {
		return remainingTimeType;
	}

	/**
	 * @param remainingTimeType the remainingTimeType to set
	 */
	public void setRemainingTimeType(BigDecimal remainingTimeType) {
		this.remainingTimeType = remainingTimeType;
	}

	/**
	 * @return the canDisplay
	 */
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay the canDisplay to set
	 */
	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	/**
	 * @return the timeSlot
	 */
	public String getTimeSlot() {
		return timeSlot;
	}

	/**
	 * @param timeSlot the timeSlot to set
	 */
	public void setTimeSlot(final String timeSlot) {
		this.timeSlot = timeSlot;
	}

	/**
	 * @return the originalTimeSlot
	 */
	public String getOriginalTimeSlot() {
		return originalTimeSlot;
	}

	/**
	 * @param originalTimeSlot the originalTimeSlot to set
	 */
	public void setOriginalTimeSlot(final String originalTimeSlot) {
		this.originalTimeSlot = originalTimeSlot;
	}

	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}


	/**
	 * @return the groupLeaderFlag
	 */
	public String getGroupLeaderFlag() {
		return groupLeaderFlag;
	}

	/**
	 * @param groupLeaderFlag the groupLeaderFlag to set
	 */
	public void setGroupLeaderFlag(String groupLeaderFlag) {
		this.groupLeaderFlag = groupLeaderFlag;
	}

	/**
	 * @return the assistedVisitFlag
	 */
	public String getAssistedVisitFlag() {
		return assistedVisitFlag;
	}

	/**
	 * @param assistedVisitFlag the assistedVisitFlag to set
	 */
	public void setAssistedVisitFlag(String assistedVisitFlag) {
		this.assistedVisitFlag = assistedVisitFlag;
	}

	/**
	 * @return the supLevel
	 */
	public String getSupLevel() {
		return supLevel;
	}

	/**
	 * @param supLevel the supLevel to set
	 */
	public void setSupLevel(String supLevel) {
		this.supLevel = supLevel;
	}

	/**
	 * @return the adultAge
	 */
	public Integer getAdultAge() {
		return adultAge;
	}

	/**
	 * @param adultAge the adultAge to set
	 */
	public void setAdultAge(Integer adultAge) {
		this.adultAge = adultAge;
	}

	/**
	 * @return the person
	 */
	public Integer getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Integer person) {
		this.person = person;
	}

	/**
	 * @return the adult
	 */
	public Integer getAdult() {
		return adult;
	}

	/**
	 * @param adult the adult to set
	 */
	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	/**
	 * @return the group
	 */
	public Integer getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Integer group) {
		this.group = group;
	}

	/**
	 * @return the weekDay
	 */
	public String getWeekDay() {
		return weekDay;
	}

	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getstimetemp() {
		return stimetemp;
	}

	public void setstimetemp(String stimetemp) {
		this.stimetemp = stimetemp;
	}

	public String getRecordUserId() {
		return recordUserId;
	}

	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
}
