package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCourseAttendance extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("eventId")
	private long eventId;

	@JsonProperty("actionCode")
	private String actionCode;

	@JsonProperty("agreedTravelHour")
	private BigDecimal agreedTravelHour;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("authorisedAbsenceFlag")
	private String authorisedAbsenceFlag;

	@JsonProperty("behaviourCode")
	private String behaviourCode;

	@JsonProperty("bonusPay")
	private BigDecimal bonusPay;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creditedHours")
	private BigDecimal creditedHours;

	@JsonProperty("crsActyId")
	private BigDecimal crsActyId;

	@JsonProperty("crsApptId")
	private BigDecimal crsApptId;

	@JsonProperty("crsSchId")
	private BigDecimal crsSchId;

	@JsonProperty("details")
	private String details;

	@JsonProperty("directionCode")
	private String directionCode;

	@JsonProperty("endTime")
	private Date endTime;

	@JsonProperty("engagementCode")
	private String engagementCode;

	@JsonProperty("eventClass")
	private String eventClass;

	@JsonProperty("eventDate")
	private Date eventDate;

	@JsonProperty("eventOutcome")
	private String eventOutcome;

	@JsonProperty("eventStatus")
	private String eventStatus;

	@JsonProperty("eventSubType")
	private String eventSubType;

	@JsonProperty("eventType")
	private String eventType;

	@JsonProperty("hiddenCommentText")
	private String hiddenCommentText;

	@JsonProperty("inTime")
	private Date inTime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offCrsSchRefId")
	private BigDecimal offCrsSchRefId;

	@JsonProperty("offPrgrefId")
	private BigDecimal offPrgrefId;

	@JsonProperty("offenderCourseApptRuleId")
	private BigDecimal offenderCourseApptRuleId;

	@JsonProperty("offenderPrgObligationId")
	private BigDecimal offenderPrgObligationId;

	@JsonProperty("outTime")
	private Date outTime;

	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;

	@JsonProperty("payFlag")
	private String payFlag;

	@JsonProperty("performanceCode")
	private String performanceCode;

	@JsonProperty("pieceWork")
	private BigDecimal pieceWork;

	@JsonProperty("programId")
	private BigDecimal programId;

	@JsonProperty("referenceId")
	private BigDecimal referenceId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sessionNo")
	private BigDecimal sessionNo;

	@JsonProperty("sickNoteExpiryDate")
	private Date sickNoteExpiryDate;

	@JsonProperty("sickNoteReceivedDate")
	private Date sickNoteReceivedDate;

	@JsonProperty("startTime")
	private Date startTime;

	@JsonProperty("supervisorName")
	private String supervisorName;

	@JsonProperty("supervisorStaffId")
	private BigDecimal supervisorStaffId;

	@JsonProperty("toAddressId")
	private BigDecimal toAddressId;

	@JsonProperty("toAddressOwnerClass")
	private String toAddressOwnerClass;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("toInternalLocationId")
	private BigDecimal toInternalLocationId;

	@JsonProperty("txnEntrySeq")
	private BigDecimal txnEntrySeq;

	@JsonProperty("txnId")
	private BigDecimal txnId;

	@JsonProperty("understandingCode")
	private String understandingCode;

	@JsonProperty("unexcusedAbsenceFlag")
	private String unexcusedAbsenceFlag;

	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("payLockFlag")
	private String payLockFlag;
	@JsonProperty("eventOutcomeDbVal")
	private String eventOutcomeDbVal;
	@JsonProperty("phaseId")
	private Integer phaseId;
	@JsonProperty("moduleId")
	private Integer moduleId;
	@JsonProperty("staffName")
	private String staffName;
	@JsonProperty("lvNewUa")
	private Boolean lvNewUa;
	@JsonProperty("lvMultipleFailure")
	private Boolean lvMultipleFailure;
	@JsonProperty("payBatchId")
	private Integer payBatchId;
	@JsonProperty("emailFlag")
	private String emailFlag;
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

	public Integer getEmailScheduleHoursBefore() {
		return emailScheduleHoursBefore;
	}

	public void setEmailScheduleHoursBefore(Integer emailScheduleHoursBefore) {
		this.emailScheduleHoursBefore = emailScheduleHoursBefore;
	}

	public Integer getSmsScheduleHoursBefore() {
		return smsScheduleHoursBefore;
	}

	public void setSmsScheduleHoursBefore(Integer smsScheduleHoursBefore) {
		this.smsScheduleHoursBefore = smsScheduleHoursBefore;
	}

	@JsonProperty("smsFlag")
	private String smsFlag;
	@JsonProperty("emailScheduleHoursBefore")
	private Integer emailScheduleHoursBefore;
	@JsonProperty("smsScheduleHoursBefore")
	private Integer smsScheduleHoursBefore;
	private Integer emailAddressCount;
	private Integer phoneNumberCount;
	
	private String emailFlagConfig;
	private String smsFlagConfig;

	
	
	@JsonProperty("scheduleDate")
	private Date scheduleDate;

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getEventOutcomeDbVal() {
		return eventOutcomeDbVal;
	}

	public void setEventOutcomeDbVal(String eventOutcomeDbVal) {
		this.eventOutcomeDbVal = eventOutcomeDbVal;
	}

	public String getPayLockFlag() {
		return payLockFlag;
	}

	public void setPayLockFlag(String payLockFlag) {
		this.payLockFlag = payLockFlag;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}
	
	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	@JsonProperty("offenderName")
	private String offenderName;


	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	@JsonProperty("offenderCourseSkills")
	private List<OffenderCourseSkill> offenderCourseSkills;
	
	
	
	
	@JsonProperty("confirmAttendance")
	private String confirmAttendance;
	
	@JsonProperty("module")
	private String module;
	
	@JsonProperty("service")
	private String service;
	
	@JsonProperty("programe")
	private String programe;
	
	@JsonProperty("moduleFlag")
	private String moduleFlag;
	
	@JsonProperty("viewCode")
	private String viewCode;
	
	@JsonProperty("catchUpFlag")
	private String catchUpFlag;
	
	@JsonProperty("catchUpCrsSchId")
	private Long catchUpCrsSchId;
	
	
	public Long getCatchUpCrsSchId() {
		return catchUpCrsSchId;
	}

	public void setCatchUpCrsSchId(Long catchUpCrsSchId) {
		this.catchUpCrsSchId = catchUpCrsSchId;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getEventId() {
		return eventId;
	}

	/**
	 *
	 * @param eventId
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	/**
	 *
	 * @return
	 */
	public String getActionCode() {
		return actionCode;
	}

	/**
	 *
	 * @param actionCode
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAgreedTravelHour() {
		return agreedTravelHour;
	}

	/**
	 *
	 * @param agreedTravelHour
	 */
	public void setAgreedTravelHour(BigDecimal agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
	}

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getAuthorisedAbsenceFlag() {
		return authorisedAbsenceFlag;
	}

	/**
	 *
	 * @param authorisedAbsenceFlag
	 */
	public void setAuthorisedAbsenceFlag(String authorisedAbsenceFlag) {
		this.authorisedAbsenceFlag = authorisedAbsenceFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getBehaviourCode() {
		return behaviourCode;
	}

	/**
	 *
	 * @param behaviourCode
	 */
	public void setBehaviourCode(String behaviourCode) {
		this.behaviourCode = behaviourCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getBonusPay() {
		return bonusPay;
	}

	/**
	 *
	 * @param bonusPay
	 */
	public void setBonusPay(BigDecimal bonusPay) {
		this.bonusPay = bonusPay;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCreditedHours() {
		return creditedHours;
	}

	/**
	 *
	 * @param creditedHours
	 */
	public void setCreditedHours(BigDecimal creditedHours) {
		this.creditedHours = creditedHours;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCrsActyId() {
		return crsActyId;
	}

	/**
	 *
	 * @param crsActyId
	 */
	public void setCrsActyId(BigDecimal crsActyId) {
		this.crsActyId = crsActyId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCrsApptId() {
		return crsApptId;
	}

	/**
	 *
	 * @param crsApptId
	 */
	public void setCrsApptId(BigDecimal crsApptId) {
		this.crsApptId = crsApptId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCrsSchId() {
		return crsSchId;
	}

	/**
	 *
	 * @param crsSchId
	 */
	public void setCrsSchId(BigDecimal crsSchId) {
		this.crsSchId = crsSchId;
	}

	/**
	 *
	 * @return
	 */
	public String getDetails() {
		return details;
	}

	/**
	 *
	 * @param details
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 *
	 * @return
	 */
	public String getDirectionCode() {
		return directionCode;
	}

	/**
	 *
	 * @param directionCode
	 */
	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 *
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 *
	 * @return
	 */
	public String getEngagementCode() {
		return engagementCode;
	}

	/**
	 *
	 * @param engagementCode
	 */
	public void setEngagementCode(String engagementCode) {
		this.engagementCode = engagementCode;
	}

	/**
	 *
	 * @return
	 */
	public String getEventClass() {
		return eventClass;
	}

	/**
	 *
	 * @param eventClass
	 */
	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}

	/**
	 *
	 * @return
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 *
	 * @param eventDate
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 *
	 * @return
	 */
	public String getEventOutcome() {
		return eventOutcome;
	}

	/**
	 *
	 * @param eventOutcome
	 */
	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	/**
	 *
	 * @return
	 */
	public String getEventStatus() {
		return eventStatus;
	}

	/**
	 *
	 * @param eventStatus
	 */
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getEventSubType() {
		return eventSubType;
	}

	/**
	 *
	 * @param eventSubType
	 */
	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	/**
	 *
	 * @return
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 *
	 * @param eventType
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 *
	 * @return
	 */
	public String getHiddenCommentText() {
		return hiddenCommentText;
	}

	/**
	 *
	 * @param hiddenCommentText
	 */
	public void setHiddenCommentText(String hiddenCommentText) {
		this.hiddenCommentText = hiddenCommentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getInTime() {
		return inTime;
	}

	/**
	 *
	 * @param inTime
	 */
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffCrsSchRefId() {
		return offCrsSchRefId;
	}

	/**
	 *
	 * @param offCrsSchRefId
	 */
	public void setOffCrsSchRefId(BigDecimal offCrsSchRefId) {
		this.offCrsSchRefId = offCrsSchRefId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffPrgrefId() {
		return offPrgrefId;
	}

	/**
	 *
	 * @param offPrgrefId
	 */
	public void setOffPrgrefId(BigDecimal offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderCourseApptRuleId() {
		return offenderCourseApptRuleId;
	}

	/**
	 *
	 * @param offenderCourseApptRuleId
	 */
	public void setOffenderCourseApptRuleId(BigDecimal offenderCourseApptRuleId) {
		this.offenderCourseApptRuleId = offenderCourseApptRuleId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderPrgObligationId() {
		return offenderPrgObligationId;
	}

	/**
	 *
	 * @param offenderPrgObligationId
	 */
	public void setOffenderPrgObligationId(BigDecimal offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	/**
	 *
	 * @return
	 */
	public Date getOutTime() {
		return outTime;
	}

	/**
	 *
	 * @param outTime
	 */
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	/**
	 *
	 * @return
	 */
	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	/**
	 *
	 * @param outcomeReasonCode
	 */
	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	/**
	 *
	 * @return
	 */
	public String getPayFlag() {
		return payFlag;
	}

	/**
	 *
	 * @param payFlag
	 */
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getPerformanceCode() {
		return performanceCode;
	}

	/**
	 *
	 * @param performanceCode
	 */
	public void setPerformanceCode(String performanceCode) {
		this.performanceCode = performanceCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getPieceWork() {
		return pieceWork;
	}

	/**
	 *
	 * @param pieceWork
	 */
	public void setPieceWork(BigDecimal pieceWork) {
		this.pieceWork = pieceWork;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getProgramId() {
		return programId;
	}

	/**
	 *
	 * @param programId
	 */
	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getReferenceId() {
		return referenceId;
	}

	/**
	 *
	 * @param referenceId
	 */
	public void setReferenceId(BigDecimal referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getSessionNo() {
		return sessionNo;
	}

	/**
	 *
	 * @param sessionNo
	 */
	public void setSessionNo(BigDecimal sessionNo) {
		this.sessionNo = sessionNo;
	}

	/**
	 *
	 * @return
	 */
	public Date getSickNoteExpiryDate() {
		return sickNoteExpiryDate;
	}

	/**
	 *
	 * @param sickNoteExpiryDate
	 */
	public void setSickNoteExpiryDate(Date sickNoteExpiryDate) {
		this.sickNoteExpiryDate = sickNoteExpiryDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getSickNoteReceivedDate() {
		return sickNoteReceivedDate;
	}

	/**
	 *
	 * @param sickNoteReceivedDate
	 */
	public void setSickNoteReceivedDate(Date sickNoteReceivedDate) {
		this.sickNoteReceivedDate = sickNoteReceivedDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 *
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 *
	 * @return
	 */
	public String getSupervisorName() {
		return supervisorName;
	}

	/**
	 *
	 * @param supervisorName
	 */
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getSupervisorStaffId() {
		return supervisorStaffId;
	}

	/**
	 *
	 * @param supervisorStaffId
	 */
	public void setSupervisorStaffId(BigDecimal supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getToAddressId() {
		return toAddressId;
	}

	/**
	 *
	 * @param toAddressId
	 */
	public void setToAddressId(BigDecimal toAddressId) {
		this.toAddressId = toAddressId;
	}

	/**
	 *
	 * @return
	 */
	public String getToAddressOwnerClass() {
		return toAddressOwnerClass;
	}

	/**
	 *
	 * @param toAddressOwnerClass
	 */
	public void setToAddressOwnerClass(String toAddressOwnerClass) {
		this.toAddressOwnerClass = toAddressOwnerClass;
	}

	/**
	 *
	 * @return
	 */
	public String getToAgyLocId() {
		return toAgyLocId;
	}

	/**
	 *
	 * @param toAgyLocId
	 */
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getToInternalLocationId() {
		return toInternalLocationId;
	}

	/**
	 *
	 * @param toInternalLocationId
	 */
	public void setToInternalLocationId(BigDecimal toInternalLocationId) {
		this.toInternalLocationId = toInternalLocationId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getTxnEntrySeq() {
		return txnEntrySeq;
	}

	/**
	 *
	 * @param txnEntrySeq
	 */
	public void setTxnEntrySeq(BigDecimal txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getTxnId() {
		return txnId;
	}

	/**
	 *
	 * @param txnId
	 */
	public void setTxnId(BigDecimal txnId) {
		this.txnId = txnId;
	}

	/**
	 *
	 * @return
	 */
	public String getUnderstandingCode() {
		return understandingCode;
	}

	/**
	 *
	 * @param understandingCode
	 */
	public void setUnderstandingCode(String understandingCode) {
		this.understandingCode = understandingCode;
	}

	/**
	 *
	 * @return
	 */
	public String getUnexcusedAbsenceFlag() {
		return unexcusedAbsenceFlag;
	}

	/**
	 *
	 * @param unexcusedAbsenceFlag
	 */
	public void setUnexcusedAbsenceFlag(String unexcusedAbsenceFlag) {
		this.unexcusedAbsenceFlag = unexcusedAbsenceFlag;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCourseSkill> getOffenderCourseSkills() {
		return offenderCourseSkills;
	}

	/**
	 *
	 * @param offenderCourseSkills
	 */
	public void setOffenderCourseSkills(List<OffenderCourseSkill> offenderCourseSkills) {
		this.offenderCourseSkills = offenderCourseSkills;
	}

	public String getConfirmAttendance() {
		return confirmAttendance;
	}

	public void setConfirmAttendance(String confirmAttendance) {
		this.confirmAttendance = confirmAttendance;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPrograme() {
		return programe;
	}

	public void setPrograme(String programe) {
		this.programe = programe;
	}

	public String getModuleFlag() {
		return moduleFlag;
	}

	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	public String getViewCode() {
		return viewCode;
	}

	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}

	public String getCatchUpFlag() {
		return catchUpFlag;
	}

	public void setCatchUpFlag(String catchUpFlag) {
		this.catchUpFlag = catchUpFlag;
	}

	public Integer getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Boolean getLvNewUa() {
		return lvNewUa;
	}

	public Boolean getLvMultipleFailure() {
		return lvMultipleFailure;
	}

	public void setLvNewUa(Boolean lvNewUa) {
		this.lvNewUa = lvNewUa;
	}

	public void setLvMultipleFailure(Boolean lvMultipleFailure) {
		this.lvMultipleFailure = lvMultipleFailure;
	}

	public Integer getPayBatchId() {
		return payBatchId;
	}

	public void setPayBatchId(Integer payBatchId) {
		this.payBatchId = payBatchId;
	}

	public Integer getEmailAddressCount() {
		return emailAddressCount;
	}

	public void setEmailAddressCount(Integer emailAddressCount) {
		this.emailAddressCount = emailAddressCount;
	}

	public Integer getPhoneNumberCount() {
		return phoneNumberCount;
	}

	public void setPhoneNumberCount(Integer phoneNumberCount) {
		this.phoneNumberCount = phoneNumberCount;
	}

	public String getEmailFlagConfig() {
		return emailFlagConfig;
	}

	public void setEmailFlagConfig(String emailFlagConfig) {
		this.emailFlagConfig = emailFlagConfig;
	}

	public String getSmsFlagConfig() {
		return smsFlagConfig;
	}

	public void setSmsFlagConfig(String smsFlagConfig) {
		this.smsFlagConfig = smsFlagConfig;
	}
	
	

}
