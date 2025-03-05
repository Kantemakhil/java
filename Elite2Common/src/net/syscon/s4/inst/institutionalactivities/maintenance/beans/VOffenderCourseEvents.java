package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the V_OFFENDER_COURSE_EVENTS database table.
 * 
 */
public class VOffenderCourseEvents implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer rowId;
	
	private String actionCode;

	private BigDecimal agreedTravelHour;

	private String agyLocId;

	private String behaviourCode;

	private BigDecimal checkSum;

	private String commentText;

	private String courseCode;

	private BigDecimal creditedHours;

	private BigDecimal crsActyId;

	private Long crsApptId;

	private BigDecimal crsSchId;

	private String description;

	private String directionCode;

	private Date endTime;

	private String engagementCode;

	private String eventClass;

	private Date eventDate;

	private BigDecimal eventId;

	private String eventOutcome;

	private String eventOutcomeDesc;

	private String eventStatus;

	private String eventSubType;

	private String eventType;

	private Date extMoveInTime;

	private Date extMoveOutTime;

	private Date inTime;

	private BigDecimal offPrgrefId;

	private BigDecimal offenderBookId;

	private Date outTime;

	private String outcomeReasonCode;

	private String performanceCode;

	private String performanceDesc;

	private BigDecimal pieceWork;

	private BigDecimal programId;

	private String recordSource;

	private Long referenceId;

	private Date scheduleMovementTime;

	private BigDecimal sessionNo;

	private Date sickNoteExpiryDate;

	private Date sickNoteReceivedDate;

	private Date startTime;

	private BigDecimal supervisorStaffId;

	private BigDecimal toAddressId;

	private String toAgyLocId;

	private BigDecimal toInternalLocationId;

	private String understandingCode;

	private String unexcusedAbsenceFlag;

	private String weekday;

	private Date dbStartTime;

	private Date dbEndTime;

	private Integer teamId;

	private String pOffenderIdDisplay;

	private String pName;

	private String pAttendance;

	private String pBehaviour;

	private String pWorkQuality;

	private String pSupervisorName;

	private String pCode;

	private String pActivityDesc;

	private Date penalty;

	private Date nbtPenalty;

	private Date nbtHours;

	private Date nbtTravel;

	private Date nbtCreditedHours;

	private Long nbtRecordOffPrgrefId;

	private Long nbtRecordCrsActyId;

	private String eventOutcomeDbVal;

	private boolean pOldUa;

	private boolean pNewUa;

	private boolean pMultipleFailure;
	private String view;
	private Long nbtCrsActyId;
	@JsonProperty("offenderCourseApptGrpId")
	private long offenderCourseApptGrpId;
	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("courseScheduleRuleId")
	private long courseScheduleRuleId;
	@JsonProperty("programCategory")
	private String programCategory;
	private boolean recordStatus;

	private Long nbtRefCrsActyId;

	private BigDecimal nbtProjectRefStrId;

	private String userName;
	
	@JsonProperty("activity")
	private String activity;

	private String code;

	private String projectCode;
	private String projectDescription;
	private String teamDescription;

	@JsonProperty("agreedTravelFare")
	private Long agreedTravelFare;	
       
       @JsonProperty("endDate")
	private Date endDate;
	
	@JsonProperty("agreedTravelFare1")
	private Float agreedTravelFare1;

	@JsonProperty("completionDate")
	private Date completionDate;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("creditOtherHours")
	private Long creditOtherHours;

	@JsonProperty("creditWorkHours")
	private Long creditWorkHours;

	@JsonProperty("earlyEndReason")
	private String earlyEndReason;

	@JsonProperty("holidayFlag")
	private String holidayFlag;

	@JsonProperty("medicalRecordSeq")
	private Long medicalRecordSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("neededFlag")
	private String neededFlag;

	@JsonProperty("offenderEndCommentText")
	private String offenderEndCommentText;

	@JsonProperty("offenderEndDate")
	private Date offenderEndDate;

	@JsonProperty("offenderEndReason")
	private String offenderEndReason;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("offenderPrgObligationId")
	@NotNull
	private Long offenderPrgObligationId;

	@JsonProperty("offenderProgramStatus")
	@NotNull
	@Size(max = 12)
	private String offenderProgramStatus;

	@JsonProperty("offenderSentConditionId")
	private Long offenderSentConditionId;

	@JsonProperty("offenderStartDate")
	private Date offenderStartDate;

	@JsonProperty("parameter1")
	private String parameter1;

	@JsonProperty("parentOffPrgrefId")
	private Long parentOffPrgrefId;

	@JsonProperty("profileClass")
	private String profileClass;

	private BigDecimal pProgramId;

	@JsonProperty("programOffPrgrefId")
	private Long programOffPrgrefId;

	@JsonProperty("providerName")
	private String providerName;

	@JsonProperty("programDescription")
	private String programDescription;

	@JsonProperty("referralCommentText")
	private String referralCommentText;

	@JsonProperty("referralDate")
	private Date referralDate;

	@JsonProperty("referralPriority")
	private String referralPriority;

	@JsonProperty("referralStaffId")
	private Long referralStaffId;

	@JsonProperty("rejectDate")
	private Date rejectDate;

	@JsonProperty("rejectReasonCode")
	private String rejectReasonCode;

	@JsonProperty("reviewedBy")
	private String reviewedBy;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;

	@JsonProperty("startSessionNo")
	private Long startSessionNo;

	@JsonProperty("suspendedFlag")
	private String suspendedFlag;

	@JsonProperty("waitlistDecisionCode")
	private String waitlistDecisionCode;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("activityDescription")
	private String activityDescription;

	@JsonProperty("vacancy")
	private String vacancy;

	@JsonProperty("decision")
	private String decision;

	@JsonProperty("rejectReason")
	private String rejectReason;

	@JsonProperty("decisionDate")
	private Date decisionDate;

	@JsonProperty("facilityDescription")
	private String facilityDescription;

	@JsonProperty("internalLocationId")
	private Integer internalLocationId;

	@JsonProperty("offEndReasonVal")
	private String offEndReasonVal;

	@JsonProperty("offEndCommentVal")
	private String offEndCommentVal;

	@JsonProperty("refCommentVal")
	private String refCommentVal;

	@JsonProperty("rejReason")
	private String rejReason;

	@JsonProperty("rejDate")
	private Date rejDate;

	@JsonProperty("scheduleEndDate")
	private Date scheduleEndDate;

	@JsonProperty("scheduleStartDate")
	private Date scheduleStartDate;

	@JsonProperty("allocate")
	private String allocate;

	@JsonProperty("chkActiveIaAllocation")
	private Integer chkActiveIaAllocation;

	@JsonProperty("offEndDate")
	private Date offEndDate;

	@JsonProperty("warningMsg")
	private String warningMsg;
	@JsonProperty("warningPrompt")
	private String warningPrompt;

	private BigDecimal maxCapacity;


	private String phaseDesc;

	private String moduleFlag;

	private String occuranceCode;
	
	private String nbtteamAreaCode;
	
	private String nbtAgyLocId;
	
	private Integer moduleFrom;
	
	private Integer moduleTo;
	
	private String nbtStatus;
	
	@JsonProperty("offPrgStatusDbVal")
	private String offPrgStatusDbVal;
	
	@JsonProperty("offendDateDbVal")
	private Date offendDateDbVal;
	
	@JsonProperty("eventIdTemp")
	private BigDecimal eventIdTemp;
	
	@JsonProperty("payFlag")
	private String payFlag;
	
	@JsonProperty("payBatchId")
	private Integer payBatchId;
	
	@JsonProperty("paySystemRate")
	private Integer paySystemRate;
	
	@JsonProperty("paySystemAmount")
	private BigDecimal paySystemAmount;
	
	@JsonProperty("paySystemUnit")
	private String paySystemUnit;

	@JsonProperty("line")
	private String line;
	
	@JsonProperty("emailFlag")
	private String emailFlag;
	
	@JsonProperty("smsFlag")
	private String smsFlag;
	
	@JsonProperty("smsScheduleHoursBefore")
	private Integer smsScheduleHoursBefore;
	
	@JsonProperty("emailScheduleHoursBefore")
	private Integer emailScheduleHoursBefore;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(boolean recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getProgramCategory() {
		return programCategory;
	}

	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}

	public long getOffenderCourseApptGrpId() {
		return offenderCourseApptGrpId;
	}

	public void setOffenderCourseApptGrpId(long offenderCourseApptGrpId) {
		this.offenderCourseApptGrpId = offenderCourseApptGrpId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public long getCourseScheduleRuleId() {
		return courseScheduleRuleId;
	}

	public void setCourseScheduleRuleId(long courseScheduleRuleId) {
		this.courseScheduleRuleId = courseScheduleRuleId;
	}

	public Long getNbtCrsActyId() {
		return nbtCrsActyId;
	}

	public void setNbtCrsActyId(Long nbtCrsActyId) {
		this.nbtCrsActyId = nbtCrsActyId;
	}

	public String getView() {
		return view;
	}

	public void setView(final String view) {
		this.view = view;
	}

	public boolean getpOldUa() {
		return pOldUa;
	}

	public void setpOldUa(final boolean pOldUa) {
		this.pOldUa = pOldUa;
	}

	public boolean getpNewUa() {
		return pNewUa;
	}

	public void setpNewUa(final boolean pNewUa) {
		this.pNewUa = pNewUa;
	}

	public boolean getpMultipleFailure() {
		return pMultipleFailure;
	}

	public void setpMultipleFailure(final boolean pMultipleFailure) {
		this.pMultipleFailure = pMultipleFailure;
	}

	public String getEventOutcomeDbVal() {
		return eventOutcomeDbVal;
	}

	public void setEventOutcomeDbVal(final String eventOutcomeDbVal) {
		this.eventOutcomeDbVal = eventOutcomeDbVal;
	}

	public Date getNbtPenalty() {
		return nbtPenalty;
	}

	public void setNbtPenalty(final Date nbtPenalty) {
		this.nbtPenalty = nbtPenalty;
	}

	public Date getNbtHours() {
		return nbtHours;
	}

	public void setNbtHours(final Date nbtHours) {
		this.nbtHours = nbtHours;
	}

	public Date getNbtTravel() {
		return nbtTravel;
	}

	public void setNbtTravel(final Date nbtTravel) {
		this.nbtTravel = nbtTravel;
	}

	public Date getNbtCreditedHours() {
		return nbtCreditedHours;
	}

	public void setNbtCreditedHours(final Date nbtCreditedHours) {
		this.nbtCreditedHours = nbtCreditedHours;
	}

	public Date getPenalty() {
		return penalty;
	}

	public void setPenalty(final Date penalty) {
		this.penalty = penalty;
	}

	public String getpOffenderIdDisplay() {
		return pOffenderIdDisplay;
	}

	public void setpOffenderIdDisplay(final String pOffenderIdDisplay) {
		this.pOffenderIdDisplay = pOffenderIdDisplay;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(final String pName) {
		this.pName = pName;
	}

	public String getpAttendance() {
		return pAttendance;
	}

	public void setpAttendance(final String pAttendance) {
		this.pAttendance = pAttendance;
	}

	public String getpBehaviour() {
		return pBehaviour;
	}

	public void setpBehaviour(final String pBehaviour) {
		this.pBehaviour = pBehaviour;
	}

	public String getpWorkQuality() {
		return pWorkQuality;
	}

	public void setpWorkQuality(final String pWorkQuality) {
		this.pWorkQuality = pWorkQuality;
	}

	public String getpSupervisorName() {
		return pSupervisorName;
	}

	public void setpSupervisorName(final String pSupervisorName) {
		this.pSupervisorName = pSupervisorName;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(final String pCode) {
		this.pCode = pCode;
	}

	public String getpActivityDesc() {
		return pActivityDesc;
	}

	public void setpActivityDesc(final String pActivityDesc) {
		this.pActivityDesc = pActivityDesc;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(final Integer teamId) {
		this.teamId = teamId;
	}

	public VOffenderCourseEvents() {
		// VOffenderCourseEvents
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(final String actionCode) {
		this.actionCode = actionCode;
	}

	public BigDecimal getAgreedTravelHour() {
		return this.agreedTravelHour;
	}

	public void setAgreedTravelHour(final BigDecimal agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getBehaviourCode() {
		return this.behaviourCode;
	}

	public void setBehaviourCode(final String behaviourCode) {
		this.behaviourCode = behaviourCode;
	}

	public BigDecimal getCheckSum() {
		return this.checkSum;
	}

	public void setCheckSum(final BigDecimal checkSum) {
		this.checkSum = checkSum;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseCode(final String courseCode) {
		this.courseCode = courseCode;
	}

	public BigDecimal getCreditedHours() {
		return this.creditedHours;
	}

	public void setCreditedHours(final BigDecimal creditedHours) {
		this.creditedHours = creditedHours;
	}

	public BigDecimal getCrsActyId() {
		return this.crsActyId;
	}

	public void setCrsActyId(final BigDecimal crsActyId) {
		this.crsActyId = crsActyId;
	}

	public Long getCrsApptId() {
		return this.crsApptId;
	}

	public void setCrsApptId(final Long crsApptId) {
		this.crsApptId = crsApptId;
	}

	public BigDecimal getCrsSchId() {
		return this.crsSchId;
	}

	public void setCrsSchId(final BigDecimal crsSchId) {
		this.crsSchId = crsSchId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDirectionCode() {
		return this.directionCode;
	}

	public void setDirectionCode(final String directionCode) {
		this.directionCode = directionCode;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public String getEngagementCode() {
		return this.engagementCode;
	}

	public void setEngagementCode(final String engagementCode) {
		this.engagementCode = engagementCode;
	}

	public String getEventClass() {
		return this.eventClass;
	}

	public void setEventClass(final String eventClass) {
		this.eventClass = eventClass;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getEventId() {
		return this.eventId;
	}

	public void setEventId(final BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventOutcome() {
		return this.eventOutcome;
	}

	public void setEventOutcome(final String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public String getEventOutcomeDesc() {
		return this.eventOutcomeDesc;
	}

	public void setEventOutcomeDesc(final String eventOutcomeDesc) {
		this.eventOutcomeDesc = eventOutcomeDesc;
	}

	public String getEventStatus() {
		return this.eventStatus;
	}

	public void setEventStatus(final String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventSubType() {
		return this.eventSubType;
	}

	public void setEventSubType(final String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	public Date getExtMoveInTime() {
		return this.extMoveInTime;
	}

	public void setExtMoveInTime(final Date extMoveInTime) {
		this.extMoveInTime = extMoveInTime;
	}

	public Date getExtMoveOutTime() {
		return this.extMoveOutTime;
	}

	public void setExtMoveOutTime(final Date extMoveOutTime) {
		this.extMoveOutTime = extMoveOutTime;
	}

	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(final Date inTime) {
		this.inTime = inTime;
	}

	public BigDecimal getOffPrgrefId() {
		return this.offPrgrefId;
	}

	public void setOffPrgrefId(final BigDecimal offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(final Date outTime) {
		this.outTime = outTime;
	}

	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	public void setOutcomeReasonCode(final String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public String getPerformanceCode() {
		return this.performanceCode;
	}

	public void setPerformanceCode(final String performanceCode) {
		this.performanceCode = performanceCode;
	}

	public String getPerformanceDesc() {
		return this.performanceDesc;
	}

	public void setPerformanceDesc(final String performanceDesc) {
		this.performanceDesc = performanceDesc;
	}

	public BigDecimal getPieceWork() {
		return this.pieceWork;
	}

	public void setPieceWork(final BigDecimal pieceWork) {
		this.pieceWork = pieceWork;
	}

	public BigDecimal getProgramId() {
		return this.programId;
	}

	public void setProgramId(final BigDecimal programId) {
		this.programId = programId;
	}

	public String getRecordSource() {
		return this.recordSource;
	}

	public void setRecordSource(final String recordSource) {
		this.recordSource = recordSource;
	}

	public Long getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(final Long referenceId) {
		this.referenceId = referenceId;
	}

	public Date getScheduleMovementTime() {
		return this.scheduleMovementTime;
	}

	public void setScheduleMovementTime(final Date scheduleMovementTime) {
		this.scheduleMovementTime = scheduleMovementTime;
	}

	public BigDecimal getSessionNo() {
		return this.sessionNo;
	}

	public void setSessionNo(final BigDecimal sessionNo) {
		this.sessionNo = sessionNo;
	}

	public Date getSickNoteExpiryDate() {
		return this.sickNoteExpiryDate;
	}

	public void setSickNoteExpiryDate(final Date sickNoteExpiryDate) {
		this.sickNoteExpiryDate = sickNoteExpiryDate;
	}

	public Date getSickNoteReceivedDate() {
		return this.sickNoteReceivedDate;
	}

	public void setSickNoteReceivedDate(final Date sickNoteReceivedDate) {
		this.sickNoteReceivedDate = sickNoteReceivedDate;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getSupervisorStaffId() {
		return this.supervisorStaffId;
	}

	public void setSupervisorStaffId(final BigDecimal supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	public BigDecimal getToAddressId() {
		return this.toAddressId;
	}

	public void setToAddressId(final BigDecimal toAddressId) {
		this.toAddressId = toAddressId;
	}

	public String getToAgyLocId() {
		return this.toAgyLocId;
	}

	public void setToAgyLocId(final String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public BigDecimal getToInternalLocationId() {
		return this.toInternalLocationId;
	}

	public void setToInternalLocationId(final BigDecimal toInternalLocationId) {
		this.toInternalLocationId = toInternalLocationId;
	}

	public String getUnderstandingCode() {
		return this.understandingCode;
	}

	public void setUnderstandingCode(final String understandingCode) {
		this.understandingCode = understandingCode;
	}

	public String getUnexcusedAbsenceFlag() {
		return this.unexcusedAbsenceFlag;
	}

	public void setUnexcusedAbsenceFlag(final String unexcusedAbsenceFlag) {
		this.unexcusedAbsenceFlag = unexcusedAbsenceFlag;
	}

	public String getWeekday() {
		return this.weekday;
	}

	public void setWeekday(final String weekday) {
		this.weekday = weekday;
	}

	public Date getDbStartTime() {
		return dbStartTime;
	}

	public void setDbStartTime(final Date dbStartTime) {
		this.dbStartTime = dbStartTime;
	}

	public Date getDbEndTime() {
		return dbEndTime;
	}

	public void setDbEndTime(final Date dbEndTime) {
		this.dbEndTime = dbEndTime;
	}

	public Long getNbtRecordOffPrgrefId() {
		return nbtRecordOffPrgrefId;
	}

	public void setNbtRecordOffPrgrefId(final Long nbtRecordOffPrgrefId) {
		this.nbtRecordOffPrgrefId = nbtRecordOffPrgrefId;
	}

	public Long getNbtRecordCrsActyId() {
		return nbtRecordCrsActyId;
	}

	public void setNbtRecordCrsActyId(final Long nbtRecordCrsActyId) {
		this.nbtRecordCrsActyId = nbtRecordCrsActyId;
	}

	public Long getNbtRefCrsActyId() {
		return nbtRefCrsActyId;
	}

	public void setNbtRefCrsActyId(Long nbtRefCrsActyId) {
		this.nbtRefCrsActyId = nbtRefCrsActyId;
	}

	public BigDecimal getNbtProjectRefStrId() {
		return nbtProjectRefStrId;
	}

	public void setNbtProjectRefStrId(BigDecimal nbtProjectRefStrId) {
		this.nbtProjectRefStrId = nbtProjectRefStrId;
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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getTeamDescription() {
		return teamDescription;
	}

	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	public Long getAgreedTravelFare() {
		return agreedTravelFare;
	}

	public void setAgreedTravelFare(Long agreedTravelFare) {
		this.agreedTravelFare = agreedTravelFare;
	}

	public Float getAgreedTravelFare1() {
		return agreedTravelFare1;
	}

	public void setAgreedTravelFare1(Float agreedTravelFare1) {
		this.agreedTravelFare1 = agreedTravelFare1;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Long getCreditOtherHours() {
		return creditOtherHours;
	}

	public void setCreditOtherHours(Long creditOtherHours) {
		this.creditOtherHours = creditOtherHours;
	}

	public Long getCreditWorkHours() {
		return creditWorkHours;
	}

	public void setCreditWorkHours(Long creditWorkHours) {
		this.creditWorkHours = creditWorkHours;
	}

	public String getEarlyEndReason() {
		return earlyEndReason;
	}

	public void setEarlyEndReason(String earlyEndReason) {
		this.earlyEndReason = earlyEndReason;
	}

	public String getHolidayFlag() {
		return holidayFlag;
	}

	public void setHolidayFlag(String holidayFlag) {
		this.holidayFlag = holidayFlag;
	}

	public Long getMedicalRecordSeq() {
		return medicalRecordSeq;
	}

	public void setMedicalRecordSeq(Long medicalRecordSeq) {
		this.medicalRecordSeq = medicalRecordSeq;
	}

	public String getNeededFlag() {
		return neededFlag;
	}

	public void setNeededFlag(String neededFlag) {
		this.neededFlag = neededFlag;
	}

	public String getOffenderEndCommentText() {
		return offenderEndCommentText;
	}

	public void setOffenderEndCommentText(String offenderEndCommentText) {
		this.offenderEndCommentText = offenderEndCommentText;
	}

	public Date getOffenderEndDate() {
		return offenderEndDate;
	}

	public void setOffenderEndDate(Date offenderEndDate) {
		this.offenderEndDate = offenderEndDate;
	}

	public String getOffenderEndReason() {
		return offenderEndReason;
	}

	public void setOffenderEndReason(String offenderEndReason) {
		this.offenderEndReason = offenderEndReason;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public Long getOffenderPrgObligationId() {
		return offenderPrgObligationId;
	}

	public void setOffenderPrgObligationId(Long offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	public String getOffenderProgramStatus() {
		return offenderProgramStatus;
	}

	public void setOffenderProgramStatus(String offenderProgramStatus) {
		this.offenderProgramStatus = offenderProgramStatus;
	}

	public Long getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	public void setOffenderSentConditionId(Long offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public Date getOffenderStartDate() {
		return offenderStartDate;
	}

	public void setOffenderStartDate(Date offenderStartDate) {
		this.offenderStartDate = offenderStartDate;
	}

	public String getParameter1() {
		return parameter1;
	}

	public void setParameter1(String parameter1) {
		this.parameter1 = parameter1;
	}

	public Long getParentOffPrgrefId() {
		return parentOffPrgrefId;
	}

	public void setParentOffPrgrefId(Long parentOffPrgrefId) {
		this.parentOffPrgrefId = parentOffPrgrefId;
	}

	public String getProfileClass() {
		return profileClass;
	}

	public void setProfileClass(String profileClass) {
		this.profileClass = profileClass;
	}

	public BigDecimal getpProgramId() {
		return pProgramId;
	}

	public void setpProgramId(BigDecimal pProgramId) {
		this.pProgramId = pProgramId;
	}

	public Long getProgramOffPrgrefId() {
		return programOffPrgrefId;
	}

	public void setProgramOffPrgrefId(Long programOffPrgrefId) {
		this.programOffPrgrefId = programOffPrgrefId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProgramDescription() {
		return programDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public String getReferralCommentText() {
		return referralCommentText;
	}

	public void setReferralCommentText(String referralCommentText) {
		this.referralCommentText = referralCommentText;
	}

	public Date getReferralDate() {
		return referralDate;
	}

	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	public String getReferralPriority() {
		return referralPriority;
	}

	public void setReferralPriority(String referralPriority) {
		this.referralPriority = referralPriority;
	}

	public Long getReferralStaffId() {
		return referralStaffId;
	}

	public void setReferralStaffId(Long referralStaffId) {
		this.referralStaffId = referralStaffId;
	}

	public Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}

	public String getRejectReasonCode() {
		return rejectReasonCode;
	}

	public void setRejectReasonCode(String rejectReasonCode) {
		this.rejectReasonCode = rejectReasonCode;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Long getStartSessionNo() {
		return startSessionNo;
	}

	public void setStartSessionNo(Long startSessionNo) {
		this.startSessionNo = startSessionNo;
	}

	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	public void setSuspendedFlag(String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	public String getWaitlistDecisionCode() {
		return waitlistDecisionCode;
	}

	public void setWaitlistDecisionCode(String waitlistDecisionCode) {
		this.waitlistDecisionCode = waitlistDecisionCode;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getVacancy() {
		return vacancy;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	public String getFacilityDescription() {
		return facilityDescription;
	}

	public void setFacilityDescription(String facilityDescription) {
		this.facilityDescription = facilityDescription;
	}

	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getOffEndReasonVal() {
		return offEndReasonVal;
	}

	public void setOffEndReasonVal(String offEndReasonVal) {
		this.offEndReasonVal = offEndReasonVal;
	}

	public String getOffEndCommentVal() {
		return offEndCommentVal;
	}

	public void setOffEndCommentVal(String offEndCommentVal) {
		this.offEndCommentVal = offEndCommentVal;
	}

	public String getRefCommentVal() {
		return refCommentVal;
	}

	public void setRefCommentVal(String refCommentVal) {
		this.refCommentVal = refCommentVal;
	}

	public String getRejReason() {
		return rejReason;
	}

	public void setRejReason(String rejReason) {
		this.rejReason = rejReason;
	}

	public Date getRejDate() {
		return rejDate;
	}

	public void setRejDate(Date rejDate) {
		this.rejDate = rejDate;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getAllocate() {
		return allocate;
	}

	public void setAllocate(String allocate) {
		this.allocate = allocate;
	}

	public Integer getChkActiveIaAllocation() {
		return chkActiveIaAllocation;
	}

	public void setChkActiveIaAllocation(Integer chkActiveIaAllocation) {
		this.chkActiveIaAllocation = chkActiveIaAllocation;
	}

	public Date getOffEndDate() {
		return offEndDate;
	}

	public void setOffEndDate(Date offEndDate) {
		this.offEndDate = offEndDate;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getWarningPrompt() {
		return warningPrompt;
	}

	public void setWarningPrompt(String warningPrompt) {
		this.warningPrompt = warningPrompt;
	}

	public BigDecimal getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(BigDecimal maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getPhaseDesc() {
		return phaseDesc;
	}

	public void setPhaseDesc(String phaseDesc) {
		this.phaseDesc = phaseDesc;
	}

	public String getModuleFlag() {
		return moduleFlag;
	}

	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	public String getOccuranceCode() {
		return occuranceCode;
	}

	public void setOccuranceCode(String occuranceCode) {
		this.occuranceCode = occuranceCode;
	}

	public String getNbtteamAreaCode() {
		return nbtteamAreaCode;
	}

	public void setNbtteamAreaCode(String nbtteamAreaCode) {
		this.nbtteamAreaCode = nbtteamAreaCode;
	}

	public String getNbtAgyLocId() {
		return nbtAgyLocId;
	}

	public void setNbtAgyLocId(String nbtAgyLocId) {
		this.nbtAgyLocId = nbtAgyLocId;
	}

	public Integer getModuleFrom() {
		return moduleFrom;
	}

	public void setModuleFrom(Integer moduleFrom) {
		this.moduleFrom = moduleFrom;
	}

	public Integer getModuleTo() {
		return moduleTo;
	}

	public void setModuleTo(Integer moduleTo) {
		this.moduleTo = moduleTo;
	}

	public String getNbtStatus() {
		return nbtStatus;
	}

	public void setNbtStatus(String nbtStatus) {
		this.nbtStatus = nbtStatus;
	}

	public String getOffPrgStatusDbVal() {
		return offPrgStatusDbVal;
	}

	public void setOffPrgStatusDbVal(String offPrgStatusDbVal) {
		this.offPrgStatusDbVal = offPrgStatusDbVal;
	}

	public Date getOffendDateDbVal() {
		return offendDateDbVal;
	}

	public void setOffendDateDbVal(Date offendDateDbVal) {
		this.offendDateDbVal = offendDateDbVal;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getEventIdTemp() {
		return eventIdTemp;
	}

	public void setEventIdTemp(BigDecimal eventIdTemp) {
		this.eventIdTemp = eventIdTemp;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public Integer getPayBatchId() {
		return payBatchId;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public void setPayBatchId(Integer payBatchId) {
		this.payBatchId = payBatchId;
	}

	public Integer getPaySystemRate() {
		return paySystemRate;
	}

	public BigDecimal getPaySystemAmount() {
		return paySystemAmount;
	}

	public String getPaySystemUnit() {
		return paySystemUnit;
	}

	public void setPaySystemRate(Integer paySystemRate) {
		this.paySystemRate = paySystemRate;
	}

	public void setPaySystemAmount(BigDecimal paySystemAmount) {
		this.paySystemAmount = paySystemAmount;
	}

	public void setPaySystemUnit(String paySystemUnit) {
		this.paySystemUnit = paySystemUnit;
	}
		
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public Integer getSmsScheduleHoursBefore() {
		return smsScheduleHoursBefore;
	}

	public void setSmsScheduleHoursBefore(Integer smsScheduleHoursBefore) {
		this.smsScheduleHoursBefore = smsScheduleHoursBefore;
	}

	public Integer getEmailScheduleHoursBefore() {
		return emailScheduleHoursBefore;
	}

	public void setEmailScheduleHoursBefore(Integer emailScheduleHoursBefore) {
		this.emailScheduleHoursBefore = emailScheduleHoursBefore;
	}

}
