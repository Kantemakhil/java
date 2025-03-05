package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.Offenders;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderIndSchedules extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("eventId")
	private Integer eventId;
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("eventDate")
	private Date eventDate;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("eventClass")
	private String eventClass;
	@JsonProperty("eventType")
	private String eventType;
	@JsonProperty("eventSubType")
	private String eventSubType;
	@JsonProperty("eventStatus")
	private String eventStatus;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("hiddenCommentText")
	private String hiddenCommentText;
	@JsonProperty("applicationDate")
	private Date applicationDate;
	@JsonProperty("parentEventId")
	private Integer parentEventId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	@JsonProperty("toInternalLocationId")
	private Integer toInternalLocationId;
	@JsonProperty("fromCity")
	private String fromCity;
	@JsonProperty("toCity")
	private String toCity;
	@JsonProperty("crsSchId")
	private Integer crsSchId;
	@JsonProperty("orderId")
	private Integer orderId;
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;
	@JsonProperty("judgeName")
	private String judgeName;
	@JsonProperty("checkBox1")
	private String checkBox1;
	@JsonProperty("checkBox2")
	private String checkBox2;
	@JsonProperty("inChargeStaffId")
	private Integer inChargeStaffId;
	@JsonProperty("creditedHours")
	private Integer creditedHours;
	@JsonProperty("reportInDate")
	private Date reportInDate;
	@JsonProperty("pieceWork")
	private Integer pieceWork;
	@JsonProperty("engagementCode")
	private String engagementCode;
	@JsonProperty("understandingCode")
	private String understandingCode;
	@JsonProperty("details")
	private String details;
	@JsonProperty("creditedWorkHour")
	private Integer creditedWorkHour;
	@JsonProperty("agreedTravelHour")
	private Integer agreedTravelHour;
	@JsonProperty("unpaidWorkSupervisor")
	private String unpaidWorkSupervisor;
	@JsonProperty("unpaidWorkBehaviour")
	private String unpaidWorkBehaviour;
	@JsonProperty("unpaidWorkAction")
	private String unpaidWorkAction;
	@JsonProperty("sickNoteReceivedDate")
	private Date sickNoteReceivedDate;
	@JsonProperty("sickNoteExpiryDate")
	private Date sickNoteExpiryDate;
	@JsonProperty("courtEventResult")
	private String courtEventResult;
	@JsonProperty("unexcusedAbsenceFlag")
	private String unexcusedAbsenceFlag;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDatetime")
	private Timestamp createDatetime;
	@JsonProperty("modifyDatetime")
	private Timestamp modifyDatetime;
	@JsonProperty("escortCode")
	private String escortCode;
	@JsonProperty("confirmFlag")
	private String confirmFlag;
	@JsonProperty("directionCode")
	private String directionCode;
	@JsonProperty("toCityCode")
	private String toCityCode;
	@JsonProperty("fromCityCode")
	private String fromCityCode;
	@JsonProperty("offPrgrefId")
	private Integer offPrgrefId;
	@JsonProperty("inTime")
	private Date inTime;
	@JsonProperty("outTime")
	private Date outTime;
	@JsonProperty("performanceCode")
	private String performanceCode;
	@JsonProperty("tempAbsSchId")
	private Integer tempAbsSchId;
	@JsonProperty("referenceId")
	private Integer referenceId;
	@JsonProperty("transportCode")
	private String transportCode;
	@JsonProperty("applicationTime")
	private Date applicationTime;
	@JsonProperty("contactPersonName")
	private String contactPersonName;
	@JsonProperty("toAddressOwnerClass")
	private String toAddressOwnerClass;
	@JsonProperty("toAddressId")
	private Integer toAddressId;
	@JsonProperty("returnDate")
	private Date returnDate;
	@JsonProperty("returnTime")
	private Date returnTime;
	@JsonProperty("toCorporateId")
	private Integer toCorporateId;
	@JsonProperty("taId")
	private Integer taId;
	@JsonProperty("eventOutcome")
	private String eventOutcome;
	@JsonProperty("offenderPrgObligationId")
	private Integer offenderPrgObligationId;
	@JsonProperty("provStateCode")
	private String provStateCode;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("scheduledTripId")
	private Integer scheduledTripId;
	private boolean inserted;
	@JsonProperty("conflictFlag")
	private boolean conflictFlag;
	@JsonProperty("movementType")
	private String movementType;
	@JsonProperty("vNotFlag")
	private String vNotFlag;
	@JsonProperty("vNotType")
	private String vNotType;
	@JsonProperty("vCount")
	private Integer vCount;
	@JsonProperty("vProceedFlg")
	private Boolean vProceedFlg;
	private Integer vNotiSeq;
	private Integer vNotiMSeq;
	private String recordSource;
	
	private Date movedOutTime;
	
	private Date movedOutDate;
	
	private String createAgyLocId;
	
	@JsonProperty("emailFlag")
	private String emailFlag;
	@JsonProperty("smsFlag")
    private String smsFlag;
	@JsonProperty("cancelFlag")
    private String cancelFlag;
	@JsonProperty("emailScheduleHoursBefore")
    private Integer emailScheduleHoursBefore;
	@JsonProperty("smsScheduleHoursBefore")
    private Integer smsScheduleHoursBefore;
	@JsonProperty("emailSentFlag")
	private String emailSentFlag;
	@JsonProperty("smsSentFlag")
    private String smsSentFlag;
	@JsonProperty("seriesId")
    private Integer seriesId;
	@JsonProperty("offenderNonAssociationsByGang")
	private List<Offenders> offenderNonAssociationsByGang;
	@JsonProperty("eventIdTemp")
	private BigDecimal eventIdTemp;
	

	@JsonProperty("offenderNonAssociationsByInd")
	private List<Offenders> offenderNonAssociationsByInd;
	
	@JsonProperty("nonAssocationByIngAndGang")
	private String nonAssocationByIngAndGang;
	@JsonProperty("toInternalLocationCode")
	private String toInternalLocationCode;
	
	@JsonProperty("referenceIdTepm")
	private BigDecimal referenceIdTemp;
	
	@JsonProperty("locType")
	private String locType;
	
	@JsonProperty("eventPurpose")
	 private String eventPurpose;
	
	

	public String getEventPurpose() {
		return eventPurpose;
	}

	public void setEventPurpose(String eventPurpose) {
		this.eventPurpose = eventPurpose;
	}

	public BigDecimal getReferenceIdTemp() {
		return referenceIdTemp;
	}

	public void setReferenceIdTemp(BigDecimal referenceIdTemp) {
		this.referenceIdTemp = referenceIdTemp;
	}

	public String getToInternalLocationCode() {
		return toInternalLocationCode;
	}

	public void setToInternalLocationCode(String toInternalLocationCode) {
		this.toInternalLocationCode = toInternalLocationCode;
	}

	public String getNonAssocationByIngAndGang() {
		return nonAssocationByIngAndGang;
	}

	public void setNonAssocationByIngAndGang(String nonAssocationByIngAndGang) {
		this.nonAssocationByIngAndGang = nonAssocationByIngAndGang;
	}

	public List<Offenders> getOffenderNonAssociationsByGang() {
		return offenderNonAssociationsByGang;
	}

	public void setOffenderNonAssociationsByGang(List<Offenders> offenderNonAssociationsByGang) {
		this.offenderNonAssociationsByGang = offenderNonAssociationsByGang;
	}

	public List<Offenders> getOffenderNonAssociationsByInd() {
		return offenderNonAssociationsByInd;
	}

	public void setOffenderNonAssociationsByInd(List<Offenders> offenderNonAssociationsByInd) {
		this.offenderNonAssociationsByInd = offenderNonAssociationsByInd;
	}

	public String getRecordSource() {
		return recordSource;
	}

	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}

	public Integer getvNotiMSeq() {
		return vNotiMSeq;
	}

	public void setvNotiMSeq(Integer vNotiMSeq) {
		this.vNotiMSeq = vNotiMSeq;
	}

	public Integer getvNotiSeq() {
		return vNotiSeq;
	}

	public void setvNotiSeq(Integer vNotiSeq) {
		this.vNotiSeq = vNotiSeq;
	}

	public Boolean getvProceedFlg() {
		return vProceedFlg;
	}

	public void setvProceedFlg(Boolean vProceedFlg) {
		this.vProceedFlg = vProceedFlg;
	}

	public Integer getvCount() {
		return vCount;
	}

	public void setvCount(Integer vCount) {
		this.vCount = vCount;
	}

	public String getvNotFlag() {
		return vNotFlag;
	}

	public void setvNotFlag(String vNotFlag) {
		this.vNotFlag = vNotFlag;
	}

	public String getvNotType() {
		return vNotType;
	}

	public void setvNotType(String vNotType) {
		this.vNotType = vNotType;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	@JsonProperty("movementReasonCode")
	private String movementReasonCode;

	/**
	 * @param eventId
	 *            eventId to set
	 */
	public void setEventId(final Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * return theeventId
	 */
	public Integer getEventId() {
		return this.eventId;
	}

	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public Integer getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * @param eventDate
	 *            eventDate to set
	 */
	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * return theeventDate
	 */
	public Date getEventDate() {
		return this.eventDate;
	}

	/**
	 * @param startTime
	 *            startTime to set
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * return thestartTime
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * @param endTime
	 *            endTime to set
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * return theendTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * @param eventClass
	 *            eventClass to set
	 */
	public void setEventClass(final String eventClass) {
		this.eventClass = eventClass;
	}

	/**
	 * return theeventClass
	 */
	public String getEventClass() {
		return this.eventClass;
	}

	/**
	 * @param eventType
	 *            eventType to set
	 */
	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	/**
	 * return theeventType
	 */
	public String getEventType() {
		return this.eventType;
	}

	/**
	 * @param eventSubType
	 *            eventSubType to set
	 */
	public void setEventSubType(final String eventSubType) {
		this.eventSubType = eventSubType;
	}

	/**
	 * return theeventSubType
	 */
	public String getEventSubType() {
		return this.eventSubType;
	}

	/**
	 * @param eventStatus
	 *            eventStatus to set
	 */
	public void setEventStatus(final String eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * return theeventStatus
	 */
	public String getEventStatus() {
		return this.eventStatus;
	}

	/**
	 * @param commentText
	 *            commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * return thecommentText
	 */
	public String getCommentText() {
		return this.commentText;
	}

	/**
	 * @param hiddenCommentText
	 *            hiddenCommentText to set
	 */
	public void setHiddenCommentText(final String hiddenCommentText) {
		this.hiddenCommentText = hiddenCommentText;
	}

	/**
	 * return thehiddenCommentText
	 */
	public String getHiddenCommentText() {
		return this.hiddenCommentText;
	}

	/**
	 * @param applicationDate
	 *            applicationDate to set
	 */
	public void setApplicationDate(final Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * return theapplicationDate
	 */
	public Date getApplicationDate() {
		return this.applicationDate;
	}

	/**
	 * @param parentEventId
	 *            parentEventId to set
	 */
	public void setParentEventId(final Integer parentEventId) {
		this.parentEventId = parentEventId;
	}

	/**
	 * return theparentEventId
	 */
	public Integer getParentEventId() {
		return this.parentEventId;
	}

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param toAgyLocId
	 *            toAgyLocId to set
	 */
	public void setToAgyLocId(final String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	/**
	 * return thetoAgyLocId
	 */
	public String getToAgyLocId() {
		return this.toAgyLocId;
	}

	/**
	 * @param toInternalLocationId
	 *            toInternalLocationId to set
	 */
	public void setToInternalLocationId(final Integer toInternalLocationId) {
		this.toInternalLocationId = toInternalLocationId;
	}

	/**
	 * return thetoInternalLocationId
	 */
	public Integer getToInternalLocationId() {
		return this.toInternalLocationId;
	}

	/**
	 * @param fromCity
	 *            fromCity to set
	 */
	public void setFromCity(final String fromCity) {
		this.fromCity = fromCity;
	}

	/**
	 * return thefromCity
	 */
	public String getFromCity() {
		return this.fromCity;
	}

	/**
	 * @param toCity
	 *            toCity to set
	 */
	public void setToCity(final String toCity) {
		this.toCity = toCity;
	}

	/**
	 * return thetoCity
	 */
	public String getToCity() {
		return this.toCity;
	}

	/**
	 * @param crsSchId
	 *            crsSchId to set
	 */
	public void setCrsSchId(final Integer crsSchId) {
		this.crsSchId = crsSchId;
	}

	/**
	 * return thecrsSchId
	 */
	public Integer getCrsSchId() {
		return this.crsSchId;
	}

	/**
	 * @param orderId
	 *            orderId to set
	 */
	public void setOrderId(final Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * return theorderId
	 */
	public Integer getOrderId() {
		return this.orderId;
	}

	/**
	 * @param sentenceSeq
	 *            sentenceSeq to set
	 */
	public void setSentenceSeq(final Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 * return thesentenceSeq
	 */
	public Integer getSentenceSeq() {
		return this.sentenceSeq;
	}

	/**
	 * @param outcomeReasonCode
	 *            outcomeReasonCode to set
	 */
	public void setOutcomeReasonCode(final String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	/**
	 * return theoutcomeReasonCode
	 */
	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	/**
	 * @param judgeName
	 *            judgeName to set
	 */
	public void setJudgeName(final String judgeName) {
		this.judgeName = judgeName;
	}

	/**
	 * return thejudgeName
	 */
	public String getJudgeName() {
		return this.judgeName;
	}

	/**
	 * @param checkBox1
	 *            checkBox1 to set
	 */
	public void setCheckBox1(final String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 * return thecheckBox1
	 */
	public String getCheckBox1() {
		return this.checkBox1;
	}

	/**
	 * @param checkBox2
	 *            checkBox2 to set
	 */
	public void setCheckBox2(final String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	/**
	 * return thecheckBox2
	 */
	public String getCheckBox2() {
		return this.checkBox2;
	}

	/**
	 * @param inChargeStaffId
	 *            inChargeStaffId to set
	 */
	public void setInChargeStaffId(final Integer inChargeStaffId) {
		this.inChargeStaffId = inChargeStaffId;
	}

	/**
	 * return theinChargeStaffId
	 */
	public Integer getInChargeStaffId() {
		return this.inChargeStaffId;
	}

	/**
	 * @param creditedHours
	 *            creditedHours to set
	 */
	public void setCreditedHours(final Integer creditedHours) {
		this.creditedHours = creditedHours;
	}

	/**
	 * return thecreditedHours
	 */
	public Integer getCreditedHours() {
		return this.creditedHours;
	}

	/**
	 * @param reportInDate
	 *            reportInDate to set
	 */
	public void setReportInDate(final Date reportInDate) {
		this.reportInDate = reportInDate;
	}

	/**
	 * return thereportInDate
	 */
	public Date getReportInDate() {
		return this.reportInDate;
	}

	/**
	 * @param pieceWork
	 *            pieceWork to set
	 */
	public void setPieceWork(final Integer pieceWork) {
		this.pieceWork = pieceWork;
	}

	/**
	 * return thepieceWork
	 */
	public Integer getPieceWork() {
		return this.pieceWork;
	}

	/**
	 * @param engagementCode
	 *            engagementCode to set
	 */
	public void setEngagementCode(final String engagementCode) {
		this.engagementCode = engagementCode;
	}

	/**
	 * return theengagementCode
	 */
	public String getEngagementCode() {
		return this.engagementCode;
	}

	/**
	 * @param understandingCode
	 *            understandingCode to set
	 */
	public void setUnderstandingCode(final String understandingCode) {
		this.understandingCode = understandingCode;
	}

	/**
	 * return theunderstandingCode
	 */
	public String getUnderstandingCode() {
		return this.understandingCode;
	}

	/**
	 * @param details
	 *            details to set
	 */
	public void setDetails(final String details) {
		this.details = details;
	}

	/**
	 * return thedetails
	 */
	public String getDetails() {
		return this.details;
	}

	/**
	 * @param creditedWorkHour
	 *            creditedWorkHour to set
	 */
	public void setCreditedWorkHour(final Integer creditedWorkHour) {
		this.creditedWorkHour = creditedWorkHour;
	}

	/**
	 * return thecreditedWorkHour
	 */
	public Integer getCreditedWorkHour() {
		return this.creditedWorkHour;
	}

	/**
	 * @param agreedTravelHour
	 *            agreedTravelHour to set
	 */
	public void setAgreedTravelHour(final Integer agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
	}

	/**
	 * return theagreedTravelHour
	 */
	public Integer getAgreedTravelHour() {
		return this.agreedTravelHour;
	}

	/**
	 * @param unpaidWorkSupervisor
	 *            unpaidWorkSupervisor to set
	 */
	public void setUnpaidWorkSupervisor(final String unpaidWorkSupervisor) {
		this.unpaidWorkSupervisor = unpaidWorkSupervisor;
	}

	/**
	 * return theunpaidWorkSupervisor
	 */
	public String getUnpaidWorkSupervisor() {
		return this.unpaidWorkSupervisor;
	}

	/**
	 * @param unpaidWorkBehaviour
	 *            unpaidWorkBehaviour to set
	 */
	public void setUnpaidWorkBehaviour(final String unpaidWorkBehaviour) {
		this.unpaidWorkBehaviour = unpaidWorkBehaviour;
	}

	/**
	 * return theunpaidWorkBehaviour
	 */
	public String getUnpaidWorkBehaviour() {
		return this.unpaidWorkBehaviour;
	}

	/**
	 * @param unpaidWorkAction
	 *            unpaidWorkAction to set
	 */
	public void setUnpaidWorkAction(final String unpaidWorkAction) {
		this.unpaidWorkAction = unpaidWorkAction;
	}

	/**
	 * return theunpaidWorkAction
	 */
	public String getUnpaidWorkAction() {
		return this.unpaidWorkAction;
	}

	/**
	 * @param sickNoteReceivedDate
	 *            sickNoteReceivedDate to set
	 */
	public void setSickNoteReceivedDate(final Date sickNoteReceivedDate) {
		this.sickNoteReceivedDate = sickNoteReceivedDate;
	}

	/**
	 * return thesickNoteReceivedDate
	 */
	public Date getSickNoteReceivedDate() {
		return this.sickNoteReceivedDate;
	}

	/**
	 * @param sickNoteExpiryDate
	 *            sickNoteExpiryDate to set
	 */
	public void setSickNoteExpiryDate(final Date sickNoteExpiryDate) {
		this.sickNoteExpiryDate = sickNoteExpiryDate;
	}

	/**
	 * return thesickNoteExpiryDate
	 */
	public Date getSickNoteExpiryDate() {
		return this.sickNoteExpiryDate;
	}

	/**
	 * @param courtEventResult
	 *            courtEventResult to set
	 */
	public void setCourtEventResult(final String courtEventResult) {
		this.courtEventResult = courtEventResult;
	}

	/**
	 * return thecourtEventResult
	 */
	public String getCourtEventResult() {
		return this.courtEventResult;
	}

	/**
	 * @param unexcusedAbsenceFlag
	 *            unexcusedAbsenceFlag to set
	 */
	public void setUnexcusedAbsenceFlag(final String unexcusedAbsenceFlag) {
		this.unexcusedAbsenceFlag = unexcusedAbsenceFlag;
	}

	/**
	 * return theunexcusedAbsenceFlag
	 */
	public String getUnexcusedAbsenceFlag() {
		return this.unexcusedAbsenceFlag;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Timestamp getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Timestamp modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Timestamp getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param escortCode
	 *            escortCode to set
	 */
	public void setEscortCode(final String escortCode) {
		this.escortCode = escortCode;
	}

	/**
	 * return theescortCode
	 */
	public String getEscortCode() {
		return this.escortCode;
	}

	/**
	 * @param confirmFlag
	 *            confirmFlag to set
	 */
	public void setConfirmFlag(final String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	/**
	 * return theconfirmFlag
	 */
	public String getConfirmFlag() {
		return this.confirmFlag;
	}

	/**
	 * @param directionCode
	 *            directionCode to set
	 */
	public void setDirectionCode(final String directionCode) {
		this.directionCode = directionCode;
	}

	/**
	 * return thedirectionCode
	 */
	public String getDirectionCode() {
		return this.directionCode;
	}

	/**
	 * @param toCityCode
	 *            toCityCode to set
	 */
	public void setToCityCode(final String toCityCode) {
		this.toCityCode = toCityCode;
	}

	/**
	 * return thetoCityCode
	 */
	public String getToCityCode() {
		return this.toCityCode;
	}

	/**
	 * @param fromCityCode
	 *            fromCityCode to set
	 */
	public void setFromCityCode(final String fromCityCode) {
		this.fromCityCode = fromCityCode;
	}

	/**
	 * return thefromCityCode
	 */
	public String getFromCityCode() {
		return this.fromCityCode;
	}

	/**
	 * @param offPrgrefId
	 *            offPrgrefId to set
	 */
	public void setOffPrgrefId(final Integer offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	/**
	 * return theoffPrgrefId
	 */
	public Integer getOffPrgrefId() {
		return this.offPrgrefId;
	}

	/**
	 * @param inTime
	 *            inTime to set
	 */
	public void setInTime(final Date inTime) {
		this.inTime = inTime;
	}

	/**
	 * return theinTime
	 */
	public Date getInTime() {
		return this.inTime;
	}

	/**
	 * @param outTime
	 *            outTime to set
	 */
	public void setOutTime(final Date outTime) {
		this.outTime = outTime;
	}

	/**
	 * return theoutTime
	 */
	public Date getOutTime() {
		return this.outTime;
	}

	/**
	 * @param performanceCode
	 *            performanceCode to set
	 */
	public void setPerformanceCode(final String performanceCode) {
		this.performanceCode = performanceCode;
	}

	/**
	 * return theperformanceCode
	 */
	public String getPerformanceCode() {
		return this.performanceCode;
	}

	/**
	 * @param tempAbsSchId
	 *            tempAbsSchId to set
	 */
	public void setTempAbsSchId(final Integer tempAbsSchId) {
		this.tempAbsSchId = tempAbsSchId;
	}

	/**
	 * return thetempAbsSchId
	 */
	public Integer getTempAbsSchId() {
		return this.tempAbsSchId;
	}

	/**
	 * @param referenceId
	 *            referenceId to set
	 */
	public void setReferenceId(final Integer referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * return thereferenceId
	 */
	public Integer getReferenceId() {
		return this.referenceId;
	}

	/**
	 * @param transportCode
	 *            transportCode to set
	 */
	public void setTransportCode(final String transportCode) {
		this.transportCode = transportCode;
	}

	/**
	 * return thetransportCode
	 */
	public String getTransportCode() {
		return this.transportCode;
	}

	/**
	 * @param applicationTime
	 *            applicationTime to set
	 */
	public void setApplicationTime(final Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	/**
	 * return theapplicationTime
	 */
	public Date getApplicationTime() {
		return this.applicationTime;
	}

	/**
	 * @param contactPersonName
	 *            contactPersonName to set
	 */
	public void setContactPersonName(final String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	/**
	 * return thecontactPersonName
	 */
	public String getContactPersonName() {
		return this.contactPersonName;
	}

	/**
	 * @param toAddressOwnerClass
	 *            toAddressOwnerClass to set
	 */
	public void setToAddressOwnerClass(final String toAddressOwnerClass) {
		this.toAddressOwnerClass = toAddressOwnerClass;
	}

	/**
	 * return thetoAddressOwnerClass
	 */
	public String getToAddressOwnerClass() {
		return this.toAddressOwnerClass;
	}

	/**
	 * @param toAddressId
	 *            toAddressId to set
	 */
	public void setToAddressId(final Integer toAddressId) {
		this.toAddressId = toAddressId;
	}

	/**
	 * return thetoAddressId
	 */
	public Integer getToAddressId() {
		return this.toAddressId;
	}

	/**
	 * @param returnDate
	 *            returnDate to set
	 */
	public void setReturnDate(final Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * return thereturnDate
	 */
	public Date getReturnDate() {
		return this.returnDate;
	}

	/**
	 * @param returnTime
	 *            returnTime to set
	 */
	public void setReturnTime(final Date returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * return thereturnTime
	 */
	public Date getReturnTime() {
		return this.returnTime;
	}

	/**
	 * @param toCorporateId
	 *            toCorporateId to set
	 */
	public void setToCorporateId(final Integer toCorporateId) {
		this.toCorporateId = toCorporateId;
	}

	/**
	 * return thetoCorporateId
	 */
	public Integer getToCorporateId() {
		return this.toCorporateId;
	}

	/**
	 * @param taId
	 *            taId to set
	 */
	public void setTaId(final Integer taId) {
		this.taId = taId;
	}

	/**
	 * return thetaId
	 */
	public Integer getTaId() {
		return this.taId;
	}

	/**
	 * @param eventOutcome
	 *            eventOutcome to set
	 */
	public void setEventOutcome(final String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	/**
	 * return theeventOutcome
	 */
	public String getEventOutcome() {
		return this.eventOutcome;
	}

	/**
	 * @param offenderPrgObligationId
	 *            offenderPrgObligationId to set
	 */
	public void setOffenderPrgObligationId(final Integer offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	/**
	 * return theoffenderPrgObligationId
	 */
	public Integer getOffenderPrgObligationId() {
		return this.offenderPrgObligationId;
	}

	/**
	 * @param provStateCode
	 *            provStateCode to set
	 */
	public void setProvStateCode(final String provStateCode) {
		this.provStateCode = provStateCode;
	}

	/**
	 * return theprovStateCode
	 */
	public String getProvStateCode() {
		return this.provStateCode;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param scheduledTripId
	 *            scheduledTripId to set
	 */
	public void setScheduledTripId(final Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	/**
	 * return thescheduledTripId
	 */
	public Integer getScheduledTripId() {
		return this.scheduledTripId;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	public boolean getConflictFlag() {
		return conflictFlag;
	}

	public void setConflictFlag(boolean conflictFlag) {
		this.conflictFlag = conflictFlag;
	}

	public Date getMovedOutTime() {
		return movedOutTime;
	}

	public void setMovedOutTime(Date movedOutTime) {
		this.movedOutTime = movedOutTime;
	}

	public Date getMovedOutDate() {
		return movedOutDate;
	}

	public String getCreateAgyLocId() {
		return createAgyLocId;
	}

	public void setCreateAgyLocId(String createAgyLocId) {
		this.createAgyLocId = createAgyLocId;
	}

	public void setMovedOutDate(Date movedOutDate) {
		this.movedOutDate = movedOutDate;
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

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
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

	public String getEmailSentFlag() {
		return emailSentFlag;
	}

	public void setEmailSentFlag(String emailSentFlag) {
		this.emailSentFlag = emailSentFlag;
	}

	public String getSmsSentFlag() {
		return smsSentFlag;
	}

	public void setSmsSentFlag(String smsSentFlag) {
		this.smsSentFlag = smsSentFlag;
	}

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public BigDecimal getEventIdTemp() {
		return eventIdTemp;
	}

	public void setEventIdTemp(BigDecimal eventIdTemp) {
		this.eventIdTemp = eventIdTemp;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

}
