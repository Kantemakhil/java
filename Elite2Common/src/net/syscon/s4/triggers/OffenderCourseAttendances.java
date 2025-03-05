package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCourseAttendances extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long eventId;
	private Long offenderBookId;
	private Date eventDate;
	private Date startTime;
	private Date endTime;
	private String eventSubType;
	private String eventStatus;
	private String commentText;
	private String hiddenCommentText;
	private Long toInternalLocationId;
	private Long crsSchId;
	private String outcomeReasonCode;
	private BigDecimal pieceWork;
	private String engagementCode;
	private String understandingCode;
	private String details;
	private BigDecimal creditedHours;
	private BigDecimal agreedTravelHour;
	private String supervisorName;
	private String behaviourCode;
	private String actionCode;
	private Date sickNoteReceivedDate;
	private Date sickNoteExpiryDate;
	private Long offPrgrefId;
	private Date inTime;
	private Date outTime;
	private String performanceCode;
	private BigDecimal referenceId;
	private String toAddressOwnerClass;
	private Long toAddressId;
	private String eventOutcome;
	private BigDecimal offCrsSchRefId;
	private Long supervisorStaffId;
	private BigDecimal crsApptId;
	private Long offenderCourseApptRuleId;
	private Long crsActyId;
	private String eventType;
	private String agyLocId;
	private String eventClass;
	private String unexcusedAbsenceFlag;
	private String toAgyLocId;
	private Long sessionNo;
	private Long offenderPrgObligationId;
	private Long programId;
	private BigDecimal bonusPay;
	private Long txnId;
	private Long txnEntrySeq;
	private String payFlag;
	private String authorisedAbsenceFlag;
	private String sealFlag;
	private String directionCode;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getHiddenCommentText() {
		return hiddenCommentText;
	}

	public void setHiddenCommentText(String hiddenCommentText) {
		this.hiddenCommentText = hiddenCommentText;
	}

	public Long getToInternalLocationId() {
		return toInternalLocationId;
	}

	public void setToInternalLocationId(Long toInternalLocationId) {
		this.toInternalLocationId = toInternalLocationId;
	}

	public Long getCrsSchId() {
		return crsSchId;
	}

	public void setCrsSchId(Long crsSchId) {
		this.crsSchId = crsSchId;
	}

	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public BigDecimal getPieceWork() {
		return pieceWork;
	}

	public void setPieceWork(BigDecimal pieceWork) {
		this.pieceWork = pieceWork;
	}

	public String getEngagementCode() {
		return engagementCode;
	}

	public void setEngagementCode(String engagementCode) {
		this.engagementCode = engagementCode;
	}

	public String getUnderstandingCode() {
		return understandingCode;
	}

	public void setUnderstandingCode(String understandingCode) {
		this.understandingCode = understandingCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getCreditedHours() {
		return creditedHours;
	}

	public void setCreditedHours(BigDecimal creditedHours) {
		this.creditedHours = creditedHours;
	}

	public BigDecimal getAgreedTravelHour() {
		return agreedTravelHour;
	}

	public void setAgreedTravelHour(BigDecimal agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getBehaviourCode() {
		return behaviourCode;
	}

	public void setBehaviourCode(String behaviourCode) {
		this.behaviourCode = behaviourCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public Date getSickNoteReceivedDate() {
		return sickNoteReceivedDate;
	}

	public void setSickNoteReceivedDate(Date sickNoteReceivedDate) {
		this.sickNoteReceivedDate = sickNoteReceivedDate;
	}

	public Date getSickNoteExpiryDate() {
		return sickNoteExpiryDate;
	}

	public void setSickNoteExpiryDate(Date sickNoteExpiryDate) {
		this.sickNoteExpiryDate = sickNoteExpiryDate;
	}

	public Long getOffPrgrefId() {
		return offPrgrefId;
	}

	public void setOffPrgrefId(Long offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getPerformanceCode() {
		return performanceCode;
	}

	public void setPerformanceCode(String performanceCode) {
		this.performanceCode = performanceCode;
	}

	public BigDecimal getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(BigDecimal referenceId) {
		this.referenceId = referenceId;
	}

	public String getToAddressOwnerClass() {
		return toAddressOwnerClass;
	}

	public void setToAddressOwnerClass(String toAddressOwnerClass) {
		this.toAddressOwnerClass = toAddressOwnerClass;
	}

	public Long getToAddressId() {
		return toAddressId;
	}

	public void setToAddressId(Long toAddressId) {
		this.toAddressId = toAddressId;
	}

	public String getEventOutcome() {
		return eventOutcome;
	}

	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public BigDecimal getOffCrsSchRefId() {
		return offCrsSchRefId;
	}

	public void setOffCrsSchRefId(BigDecimal offCrsSchRefId) {
		this.offCrsSchRefId = offCrsSchRefId;
	}

	public Long getSupervisorStaffId() {
		return supervisorStaffId;
	}

	public void setSupervisorStaffId(Long supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	public BigDecimal getCrsApptId() {
		return crsApptId;
	}

	public void setCrsApptId(BigDecimal crsApptId) {
		this.crsApptId = crsApptId;
	}

	public Long getOffenderCourseApptRuleId() {
		return offenderCourseApptRuleId;
	}

	public void setOffenderCourseApptRuleId(Long offenderCourseApptRuleId) {
		this.offenderCourseApptRuleId = offenderCourseApptRuleId;
	}

	public Long getCrsActyId() {
		return crsActyId;
	}

	public void setCrsActyId(Long crsActyId) {
		this.crsActyId = crsActyId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getEventClass() {
		return eventClass;
	}

	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}

	public String getUnexcusedAbsenceFlag() {
		return unexcusedAbsenceFlag;
	}

	public void setUnexcusedAbsenceFlag(String unexcusedAbsenceFlag) {
		this.unexcusedAbsenceFlag = unexcusedAbsenceFlag;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public Long getSessionNo() {
		return sessionNo;
	}

	public void setSessionNo(Long sessionNo) {
		this.sessionNo = sessionNo;
	}

	public Long getOffenderPrgObligationId() {
		return offenderPrgObligationId;
	}

	public void setOffenderPrgObligationId(Long offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public BigDecimal getBonusPay() {
		return bonusPay;
	}

	public void setBonusPay(BigDecimal bonusPay) {
		this.bonusPay = bonusPay;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public Long getTxnEntrySeq() {
		return txnEntrySeq;
	}

	public void setTxnEntrySeq(Long txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getAuthorisedAbsenceFlag() {
		return authorisedAbsenceFlag;
	}

	public void setAuthorisedAbsenceFlag(String authorisedAbsenceFlag) {
		this.authorisedAbsenceFlag = authorisedAbsenceFlag;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
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

}
