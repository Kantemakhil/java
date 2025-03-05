package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderIndSchedule extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("eventId")
	private long eventId;

	@JsonProperty("agreedTravelHour")
	private BigDecimal agreedTravelHour;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("applicationDate")
	private Date applicationDate;

	@JsonProperty("applicationTime")
	private Date applicationTime;

	@JsonProperty("checkBox1")
	private String checkBox1;

	@JsonProperty("checkBox2")
	private String checkBox2;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("confirmFlag")
	private String confirmFlag;

	@JsonProperty("contactPersonName")
	private String contactPersonName;

	@JsonProperty("courtEventResult")
	private String courtEventResult;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creditedHours")
	private BigDecimal creditedHours;

	@JsonProperty("creditedWorkHour")
	private BigDecimal creditedWorkHour;

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

	@JsonProperty("escortCode")
	private String escortCode;

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

	@JsonProperty("fromCity")
	private String fromCity;

	@JsonProperty("fromCityCode")
	private String fromCityCode;

	@JsonProperty("hiddenCommentText")
	private String hiddenCommentText;

	@JsonProperty("inChargeStaffId")
	private BigDecimal inChargeStaffId;

	@JsonProperty("inTime")
	private Date inTime;

	@JsonProperty("judgeName")
	private String judgeName;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offPrgrefId")
	private BigDecimal offPrgrefId;

	@JsonProperty("offenderPrgObligationId")
	private BigDecimal offenderPrgObligationId;

	@JsonProperty("orderId")
	private BigDecimal orderId;

	@JsonProperty("outTime")
	private Date outTime;

	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;

	@JsonProperty("parentEventId")
	private BigDecimal parentEventId;

	@JsonProperty("performanceCode")
	private String performanceCode;

	@JsonProperty("pieceWork")
	private BigDecimal pieceWork;

	@JsonProperty("provStateCode")
	private String provStateCode;

	@JsonProperty("referenceId")
	private BigDecimal referenceId;

	@JsonProperty("reportInDate")
	private Date reportInDate;

	@JsonProperty("returnDate")
	private Date returnDate;

	@JsonProperty("returnTime")
	private Date returnTime;

	@JsonProperty("scheduledTripId")
	private BigDecimal scheduledTripId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sentenceSeq")
	private BigDecimal sentenceSeq;

	@JsonProperty("sickNoteExpiryDate")
	private Date sickNoteExpiryDate;

	@JsonProperty("sickNoteReceivedDate")
	private Date sickNoteReceivedDate;

	@JsonProperty("startTime")
	private Date startTime;

	@JsonProperty("taId")
	private BigDecimal taId;

	@JsonProperty("tempAbsSchId")
	private BigDecimal tempAbsSchId;

	@JsonProperty("toAddressId")
	private BigDecimal toAddressId;

	@JsonProperty("toAddressOwnerClass")
	private String toAddressOwnerClass;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("toCity")
	private String toCity;

	@JsonProperty("toCityCode")
	private String toCityCode;

	@JsonProperty("toCorporateId")
	private BigDecimal toCorporateId;

	@JsonProperty("toInternalLocationId")
	private BigDecimal toInternalLocationId;

	@JsonProperty("transportCode")
	private String transportCode;

	@JsonProperty("understandingCode")
	private String understandingCode;

	@JsonProperty("unexcusedAbsenceFlag")
	private String unexcusedAbsenceFlag;

	@JsonProperty("unpaidWorkAction")
	private String unpaidWorkAction;

	@JsonProperty("unpaidWorkBehaviour")
	private String unpaidWorkBehaviour;

	@JsonProperty("unpaidWorkSupervisor")
	private String unpaidWorkSupervisor;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderIndSchSents")
	private List<OffenderIndSchSent> offenderIndSchSents;

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
	public Date getApplicationDate() {
		return applicationDate;
	}

	/**
	 *
	 * @param applicationDate
	 */
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getApplicationTime() {
		return applicationTime;
	}

	/**
	 *
	 * @param applicationTime
	 */
	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox1() {
		return checkBox1;
	}

	/**
	 *
	 * @param checkBox1
	 */
	public void setCheckBox1(String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox2() {
		return checkBox2;
	}

	/**
	 *
	 * @param checkBox2
	 */
	public void setCheckBox2(String checkBox2) {
		this.checkBox2 = checkBox2;
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
	public String getConfirmFlag() {
		return confirmFlag;
	}

	/**
	 *
	 * @param confirmFlag
	 */
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getContactPersonName() {
		return contactPersonName;
	}

	/**
	 *
	 * @param contactPersonName
	 */
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	/**
	 *
	 * @return
	 */
	public String getCourtEventResult() {
		return courtEventResult;
	}

	/**
	 *
	 * @param courtEventResult
	 */
	public void setCourtEventResult(String courtEventResult) {
		this.courtEventResult = courtEventResult;
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
	public BigDecimal getCreditedWorkHour() {
		return creditedWorkHour;
	}

	/**
	 *
	 * @param creditedWorkHour
	 */
	public void setCreditedWorkHour(BigDecimal creditedWorkHour) {
		this.creditedWorkHour = creditedWorkHour;
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
	public String getEscortCode() {
		return escortCode;
	}

	/**
	 *
	 * @param escortCode
	 */
	public void setEscortCode(String escortCode) {
		this.escortCode = escortCode;
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
	public String getFromCity() {
		return fromCity;
	}

	/**
	 *
	 * @param fromCity
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	/**
	 *
	 * @return
	 */
	public String getFromCityCode() {
		return fromCityCode;
	}

	/**
	 *
	 * @param fromCityCode
	 */
	public void setFromCityCode(String fromCityCode) {
		this.fromCityCode = fromCityCode;
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
	public BigDecimal getInChargeStaffId() {
		return inChargeStaffId;
	}

	/**
	 *
	 * @param inChargeStaffId
	 */
	public void setInChargeStaffId(BigDecimal inChargeStaffId) {
		this.inChargeStaffId = inChargeStaffId;
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
	public String getJudgeName() {
		return judgeName;
	}

	/**
	 *
	 * @param judgeName
	 */
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
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
	public BigDecimal getOrderId() {
		return orderId;
	}

	/**
	 *
	 * @param orderId
	 */
	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
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
	public BigDecimal getParentEventId() {
		return parentEventId;
	}

	/**
	 *
	 * @param parentEventId
	 */
	public void setParentEventId(BigDecimal parentEventId) {
		this.parentEventId = parentEventId;
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
	public String getProvStateCode() {
		return provStateCode;
	}

	/**
	 *
	 * @param provStateCode
	 */
	public void setProvStateCode(String provStateCode) {
		this.provStateCode = provStateCode;
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
	public Date getReportInDate() {
		return reportInDate;
	}

	/**
	 *
	 * @param reportInDate
	 */
	public void setReportInDate(Date reportInDate) {
		this.reportInDate = reportInDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 *
	 * @param returnDate
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getReturnTime() {
		return returnTime;
	}

	/**
	 *
	 * @param returnTime
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getScheduledTripId() {
		return scheduledTripId;
	}

	/**
	 *
	 * @param scheduledTripId
	 */
	public void setScheduledTripId(BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
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
	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 *
	 * @param sentenceSeq
	 */
	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
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
	public BigDecimal getTaId() {
		return taId;
	}

	/**
	 *
	 * @param taId
	 */
	public void setTaId(BigDecimal taId) {
		this.taId = taId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getTempAbsSchId() {
		return tempAbsSchId;
	}

	/**
	 *
	 * @param tempAbsSchId
	 */
	public void setTempAbsSchId(BigDecimal tempAbsSchId) {
		this.tempAbsSchId = tempAbsSchId;
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
	public String getToCity() {
		return toCity;
	}

	/**
	 *
	 * @param toCity
	 */
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	/**
	 *
	 * @return
	 */
	public String getToCityCode() {
		return toCityCode;
	}

	/**
	 *
	 * @param toCityCode
	 */
	public void setToCityCode(String toCityCode) {
		this.toCityCode = toCityCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getToCorporateId() {
		return toCorporateId;
	}

	/**
	 *
	 * @param toCorporateId
	 */
	public void setToCorporateId(BigDecimal toCorporateId) {
		this.toCorporateId = toCorporateId;
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
	public String getTransportCode() {
		return transportCode;
	}

	/**
	 *
	 * @param transportCode
	 */
	public void setTransportCode(String transportCode) {
		this.transportCode = transportCode;
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
	public String getUnpaidWorkAction() {
		return unpaidWorkAction;
	}

	/**
	 *
	 * @param unpaidWorkAction
	 */
	public void setUnpaidWorkAction(String unpaidWorkAction) {
		this.unpaidWorkAction = unpaidWorkAction;
	}

	/**
	 *
	 * @return
	 */
	public String getUnpaidWorkBehaviour() {
		return unpaidWorkBehaviour;
	}

	/**
	 *
	 * @param unpaidWorkBehaviour
	 */
	public void setUnpaidWorkBehaviour(String unpaidWorkBehaviour) {
		this.unpaidWorkBehaviour = unpaidWorkBehaviour;
	}

	/**
	 *
	 * @return
	 */
	public String getUnpaidWorkSupervisor() {
		return unpaidWorkSupervisor;
	}

	/**
	 *
	 * @param unpaidWorkSupervisor
	 */
	public void setUnpaidWorkSupervisor(String unpaidWorkSupervisor) {
		this.unpaidWorkSupervisor = unpaidWorkSupervisor;
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
	public List<OffenderIndSchSent> getOffenderIndSchSents() {
		return offenderIndSchSents;
	}

	/**
	 *
	 * @param offenderIndSchSents
	 */
	public void setOffenderIndSchSents(List<OffenderIndSchSent> offenderIndSchSents) {
		this.offenderIndSchSents = offenderIndSchSents;
	}

}