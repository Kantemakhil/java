package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_OFFENDER_ALL_SCHEDULES_2 database table.
 * 
 */
public class VOffenderAllSchedules2 extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal agreedTravelHour;

	private String agyLocId;

	private Date applicationDate;

	private Date applicationTime;

	private String checkBox1;

	private String checkBox2;

	private BigDecimal checkSum;

	private String commentText;

	private String confirmFlag;

	private String contactPersonName;

	private BigDecimal creditedHours;

	private String details;

	private String directionCode;

	private Date endTime;

	private String engagementCode;

	private String escortCode;

	private String eventClass;

	private Date eventDate;

	private BigDecimal eventId;

	private String eventOutcome;

	private String eventStatus;

	private String eventSubType;

	private String eventType;

	private String fromCityCode;

	private String hiddenCommentText;

	private BigDecimal inChargeStaffId;

	private Date inTime;

	private BigDecimal offPrgrefId;

	private BigDecimal offenderBookId;

	private Date outTime;

	private String outcomeReasonCode;

	private String performanceCode;

	private BigDecimal pieceWork;

	private String provStateCode;

	private String recordSource;

	private BigDecimal referenceId;

	private Date returnDate;

	private Date returnTime;

	private Date scheduleMovementTime;

	private BigDecimal scheduledTripId;

	private Date sickNoteExpiryDate;

	private Date sickNoteReceivedDate;

	private Date startTime;

	private BigDecimal taId;

	private BigDecimal toAddressId;

	private String toAddressOwnerClass;

	private String toAgyLocId;

	private String toCityCode;

	private BigDecimal toCorporateId;

	private BigDecimal toInternalLocationId;

	private String transportCode;

	private String understandingCode;

	private String unexcusedAbsenceFlag;

	private String unpaidWorkAction;

	private String unpaidWorkBehaviour;

	private String unpaidWorkSupervisor;
	
	private String offenderIdDisplay;
	
	private String firstName;
	
	private String lastName;
	
	private String facility;
	
	private String caseloadId;
	
	private String statusCode;
	
	private String reasonCode;
	
	private String modifyUserId;
	
	
	

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public VOffenderAllSchedules2() {
		// VOffenderAllSchedules2
	}

	public BigDecimal getAgreedTravelHour() {
		return this.agreedTravelHour;
	}

	public void setAgreedTravelHour(BigDecimal agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Object getApplicationDate() {
		return this.applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Object getApplicationTime() {
		return this.applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getCheckBox1() {
		return this.checkBox1;
	}

	public void setCheckBox1(String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	public String getCheckBox2() {
		return this.checkBox2;
	}

	public void setCheckBox2(String checkBox2) {
		this.checkBox2 = checkBox2;
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

	public String getConfirmFlag() {
		return this.confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public BigDecimal getCreditedHours() {
		return this.creditedHours;
	}

	public void setCreditedHours(BigDecimal creditedHours) {
		this.creditedHours = creditedHours;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDirectionCode() {
		return this.directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public Object getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEngagementCode() {
		return this.engagementCode;
	}

	public void setEngagementCode(String engagementCode) {
		this.engagementCode = engagementCode;
	}

	public String getEscortCode() {
		return this.escortCode;
	}

	public void setEscortCode(String escortCode) {
		this.escortCode = escortCode;
	}

	public String getEventClass() {
		return this.eventClass;
	}

	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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

	public String getEventSubType() {
		return this.eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getFromCityCode() {
		return this.fromCityCode;
	}

	public void setFromCityCode(String fromCityCode) {
		this.fromCityCode = fromCityCode;
	}

	public String getHiddenCommentText() {
		return this.hiddenCommentText;
	}

	public void setHiddenCommentText(String hiddenCommentText) {
		this.hiddenCommentText = hiddenCommentText;
	}

	public BigDecimal getInChargeStaffId() {
		return this.inChargeStaffId;
	}

	public void setInChargeStaffId(BigDecimal inChargeStaffId) {
		this.inChargeStaffId = inChargeStaffId;
	}

	public Object getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public BigDecimal getOffPrgrefId() {
		return this.offPrgrefId;
	}

	public void setOffPrgrefId(BigDecimal offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Object getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public String getPerformanceCode() {
		return this.performanceCode;
	}

	public void setPerformanceCode(String performanceCode) {
		this.performanceCode = performanceCode;
	}

	public BigDecimal getPieceWork() {
		return this.pieceWork;
	}

	public void setPieceWork(BigDecimal pieceWork) {
		this.pieceWork = pieceWork;
	}

	public String getProvStateCode() {
		return this.provStateCode;
	}

	public void setProvStateCode(String provStateCode) {
		this.provStateCode = provStateCode;
	}

	public String getRecordSource() {
		return this.recordSource;
	}

	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}

	public BigDecimal getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(BigDecimal referenceId) {
		this.referenceId = referenceId;
	}

	public Object getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Object getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Object getScheduleMovementTime() {
		return this.scheduleMovementTime;
	}

	public void setScheduleMovementTime(Date scheduleMovementTime) {
		this.scheduleMovementTime = scheduleMovementTime;
	}

	public BigDecimal getScheduledTripId() {
		return this.scheduledTripId;
	}

	public void setScheduledTripId(BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public Object getSickNoteExpiryDate() {
		return this.sickNoteExpiryDate;
	}

	public void setSickNoteExpiryDate(Date sickNoteExpiryDate) {
		this.sickNoteExpiryDate = sickNoteExpiryDate;
	}

	public Object getSickNoteReceivedDate() {
		return this.sickNoteReceivedDate;
	}

	public void setSickNoteReceivedDate(Date sickNoteReceivedDate) {
		this.sickNoteReceivedDate = sickNoteReceivedDate;
	}

	public Object getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getTaId() {
		return this.taId;
	}

	public void setTaId(BigDecimal taId) {
		this.taId = taId;
	}

	public BigDecimal getToAddressId() {
		return this.toAddressId;
	}

	public void setToAddressId(BigDecimal toAddressId) {
		this.toAddressId = toAddressId;
	}

	public String getToAddressOwnerClass() {
		return this.toAddressOwnerClass;
	}

	public void setToAddressOwnerClass(String toAddressOwnerClass) {
		this.toAddressOwnerClass = toAddressOwnerClass;
	}

	public String getToAgyLocId() {
		return this.toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public String getToCityCode() {
		return this.toCityCode;
	}

	public void setToCityCode(String toCityCode) {
		this.toCityCode = toCityCode;
	}

	public BigDecimal getToCorporateId() {
		return this.toCorporateId;
	}

	public void setToCorporateId(BigDecimal toCorporateId) {
		this.toCorporateId = toCorporateId;
	}

	public BigDecimal getToInternalLocationId() {
		return this.toInternalLocationId;
	}

	public void setToInternalLocationId(BigDecimal toInternalLocationId) {
		this.toInternalLocationId = toInternalLocationId;
	}

	public String getTransportCode() {
		return this.transportCode;
	}

	public void setTransportCode(String transportCode) {
		this.transportCode = transportCode;
	}

	public String getUnderstandingCode() {
		return this.understandingCode;
	}

	public void setUnderstandingCode(String understandingCode) {
		this.understandingCode = understandingCode;
	}

	public String getUnexcusedAbsenceFlag() {
		return this.unexcusedAbsenceFlag;
	}

	public void setUnexcusedAbsenceFlag(String unexcusedAbsenceFlag) {
		this.unexcusedAbsenceFlag = unexcusedAbsenceFlag;
	}

	public String getUnpaidWorkAction() {
		return this.unpaidWorkAction;
	}

	public void setUnpaidWorkAction(String unpaidWorkAction) {
		this.unpaidWorkAction = unpaidWorkAction;
	}

	public String getUnpaidWorkBehaviour() {
		return this.unpaidWorkBehaviour;
	}

	public void setUnpaidWorkBehaviour(String unpaidWorkBehaviour) {
		this.unpaidWorkBehaviour = unpaidWorkBehaviour;
	}

	public String getUnpaidWorkSupervisor() {
		return this.unpaidWorkSupervisor;
	}

	public void setUnpaidWorkSupervisor(String unpaidWorkSupervisor) {
		this.unpaidWorkSupervisor = unpaidWorkSupervisor;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffIdDisplay(String offenderIdDisplay) {
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

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
