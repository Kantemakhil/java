package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class VOffenderAllSchedules
 * 
 */
public class VOffenderAllSchedules implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer rowId;
	
	private String activeFlag;

	private BigDecimal agencyImlId;

	private String agencyImlLevel1Code;

	private String agencyImlLevel2Code;

	private String agencyImlLevel3Code;

	private BigDecimal agreedTravelHour;

	private String agyLocDesc;

	private String agyLocId;

	private Date applicationDate;

	private Date applicationTime;

	private String bookingActiveFlag;

	private String bookingNo;

	private String busyDateFlag;

	private String checkBox1;

	private String checkBox2;

	private BigDecimal checkSum;

	private String commentText;

	private String contactPersonName;

	private BigDecimal creditedHours;

	private String details;

	private String directionCode;

	private Date endTime;

	private String engagementCode;

	private String escortCode;

	private String escortDesc;

	private String eventClass;

	private Date eventDate;

	private BigDecimal eventId;

	private String eventOutcome;

	private String eventOutcomeDesc;

	private String eventStatus;

	private String eventStatusDesc;

	private String eventSubType;

	private String eventSubTypeDesc;

	private String eventType;

	private String eventTypeDesc;

	private String fromCityCode;

	private String fromCityName;

	private String hiddenCommentText;

	private BigDecimal inChargeStaffId;

	private String inChargeStaffName;

	private String inOutStatus;

	private Date inTime;

	private String livingUnitDesc;

	private BigDecimal livingUnitId;

	private String luLevel1Code;

	private String luLevel2Code;

	private String luLevel3Code;

	private String luLevel4Code;

	private BigDecimal offPrgrefId;

	private BigDecimal offenderBookId;

	private String offenderFirstName;

	private BigDecimal offenderId;

	private String offenderIdDisplay;

	private String offenderLastName;

	private Date outTime;

	private String outcomeReasonCode;

	private String performanceCode;

	private BigDecimal pieceWork;

	private String provStateCode;

	private String provStateDesc;

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

	private String toAgyLocDesc;

	private String toAgyLocId;

	private String toCityCode;

	private String toCityName;

	private String toIntLocLevel1Code;

	private String toIntLocLevel2Code;

	private String toIntLocLevel3Code;

	private String toIntLocUserDesc;

	private String toInternalLocationDesc;

	private BigDecimal toInternalLocationId;

	private String toLoc;

	private String toLocDesc;

	private String transportCode;

	private String understandingCode;

	private String unexcusedAbsenceFlag;

	private String unpaidWorkAction;

	private String unpaidWorkBehaviour;

	private String unpaidWorkSupervisor;
	
	private String daysOut;
	
	private String hoursOut;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Date fMTime;
	
	private String caseLoadId;

    private int nbtEventId;
	
	private Date nbtRequestDate;
	
	private String nbtWaitListStatus;
	
	private Date nbtStatusDate;
	
	private String nbtTransferPriority;
	
	private String nbtApprovedFlag;
	
	private Integer nbtApprovedStaffId;
	
	private String nbtOutcomeReasonCode;
	
	private String nbtCommentText1;
	
	private String nbtLastName;
	
    private String nbtFirstName;
    
    private Date nbtCreateDatetime;
    
    private String confirmMove;
    
    private BigDecimal offenderBookIdOne;
    private BigDecimal offenderIdOne;
    private BigDecimal toInternalLocationIdOne;
    private String firstName;
    private String lastName;
    private String lvNaFlag;
    private String offenderIdDisplayStg;
    private String firstNameStg;
    private String lastNameStg;
    private String lvNaFlagOne;
    private Date fromTime;
    private Date toTime;
    private String nbtUpdOutcomeFlag;
    
    private Date ctrlpsFromDate;
    private Date ctrlpsToDate;
    private Date ctrlpsStartTime;
    private Date ctrlpsEndTime;
    private String ctrlpsOutCome;
    private Date eventDateCount;
    
    private String eventOutcomeCount;
    private String threeip;
    
    private String countedFlag;
    
    private Integer adjustment;
    
    private Integer sentenceSeq;
    private String movementType; 
    private String movementReasonCode;
    
    private String createUserId;
    
    private String modifyUserId;
    
	@JsonProperty("movementTime")
	private Date movementTime;
	
	@JsonProperty("location")
    private String location;

	@JsonProperty("appearanceLocation")
	private String appearanceLocation;
	@JsonProperty("appearanceType")
	private String appearanceType;

	@JsonProperty("outcome")
	private String outcome;

	@JsonProperty("tierLevelCode")
	private String tierLevelCode;
	
	@JsonProperty("offenderTierLevelId")
	private BigDecimal offenderTierLevelId;
	
	@JsonProperty("eventIdTemp")
	private BigDecimal eventIdTemp;
	
	private String cancelReason;
	private Boolean cancelEventFalg;
	private String matter;
	private String reasonCode;
	
	@JsonProperty("linkData")
	private Integer linkData;
	@JsonProperty("versionNo")
	private Long versionNo;
	
	@JsonProperty("currentSelectedViewClass")
	private String currentSelectedViewClass;
	
	@JsonProperty("csAddress")
	private String csAddress;
	@JsonProperty("csDescription")
	private String csDescription;
	
	@JsonProperty("departStartTime")
	private Date departStartTime;
	
	@JsonProperty("oicHearingDescription")
	private String oicHearingDescription;
	
	@JsonProperty("visitDescription")
	private String visitDescription;
	
	@JsonProperty("workRelaeseProjectCode")
	private String workRelaeseProjectCode;
	
	@JsonProperty("workRelaeseProvideDesc")
	private String workRelaeseProvideDesc;
	
	private String outComeFlag;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("tapDescription")
	private String tapDescription;
	
	@JsonProperty("scheduleType")
	private String scheduleType;
	
	@JsonProperty("eventPurpose")
	private String eventPurpose;
	
	
	public String getEventPurpose() {
		return eventPurpose;
	}
	public void setEventPurpose(String eventPurpose) {
		this.eventPurpose = eventPurpose;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public Boolean getCancelEventFalg() {
		return cancelEventFalg;
	}

	public void setCancelEventFalg(Boolean cancelEventFalg) {
		this.cancelEventFalg = cancelEventFalg;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getEmailFlagTemp() {
		return emailFlagTemp;
	}

	public void setEmailFlagTemp(String emailFlagTemp) {
		this.emailFlagTemp = emailFlagTemp;
	}

	public String getSmsFlagTemp() {
		return smsFlagTemp;
	}

	public void setSmsFlagTemp(String smsFlagTemp) {
		this.smsFlagTemp = smsFlagTemp;
	}

	public Integer getEmailAddressCount() {
		return emailAddressCount;
	}

	public void setEmailAddressCount(Integer emailAddressCount) {
		this.emailAddressCount = emailAddressCount;
	}

	private String emailFlag;
	private String smsFlag;
	private String cancelFlag;
	private Integer emailScheduleHoursBefore;
	private Integer smsScheduleHoursBefore;
	private String emailSentFlag;
	private String smsSentFlag;
	private String emailFlagTemp;
	private String smsFlagTemp;
	private Integer emailAddressCount;
	private Integer phoneNumberCount;
	private Integer seriesId;
	private Boolean isSeriesDelete;
	@JsonProperty("nonAssociationFlag")
	private String nonAssociationFlag;

	
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

	public String getThreeip() {
		return threeip;
	}

	public void setThreeip(String threeip) {
		this.threeip = threeip;
	}

	public String getEventOutcomeCount() {
		return eventOutcomeCount;
	}

	public void setEventOutcomeCount(String eventOutcomeCount) {
		this.eventOutcomeCount = eventOutcomeCount;
	}

	public String getEventOutcomeDb() {
		return eventOutcomeDb;
	}

	public void setEventOutcomeDb(String eventOutcomeDb) {
		this.eventOutcomeDb = eventOutcomeDb;
	}

	private String eventOutcomeDb;

	public Date getEventDateCount() {
		return eventDateCount;
	}

	public void setEventDateCount(Date eventDateCount) {
		this.eventDateCount = eventDateCount;
	}

	private Long nbtSchCount;

    public Long getNbtSchCount() {
		return nbtSchCount;
	}

	public void setNbtSchCount(Long nbtSchCount) {
		this.nbtSchCount = nbtSchCount;
	}
    public String getCtrlpsOutCome() {
		return ctrlpsOutCome;
	}

	public void setCtrlpsOutCome(String ctrlpsOutCome) {
		this.ctrlpsOutCome = ctrlpsOutCome;
	}

	public Date getCtrlpsFromDate() {
		return ctrlpsFromDate;
	}

	public void setCtrlpsFromDate(Date ctrlpsFromDate) {
		this.ctrlpsFromDate = ctrlpsFromDate;
	}

	public Date getCtrlpsToDate() {
		return ctrlpsToDate;
	}

	public void setCtrlpsToDate(Date ctrlpsToDate) {
		this.ctrlpsToDate = ctrlpsToDate;
	}

	public Date getCtrlpsStartTime() {
		return ctrlpsStartTime;
	}

	public void setCtrlpsStartTime(Date ctrlpsStartTime) {
		this.ctrlpsStartTime = ctrlpsStartTime;
	}

	public Date getCtrlpsEndTime() {
		return ctrlpsEndTime;
	}

	public void setCtrlpsEndTime(Date ctrlpsEndTime) {
		this.ctrlpsEndTime = ctrlpsEndTime;
	}

	public String getNbtUpdOutcomeFlag() {
		return nbtUpdOutcomeFlag;
	}

	public void setNbtUpdOutcomeFlag(String nbtUpdOutcomeFlag) {
		this.nbtUpdOutcomeFlag = nbtUpdOutcomeFlag;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	@JsonProperty("warningMsg")
   	private String warningMsg;
   	public String getCountedFlag() {
		return countedFlag;
	}

	public void setCountedFlag(String countedFlag) {
		this.countedFlag = countedFlag;
	}

	@JsonProperty("warningPrompt")
   	private String warningPrompt;
   	private Integer returnValue;
   	
    public String getConfirmMove() {
		return confirmMove;
	}

	public void setConfirmMove(String confirmMove) {
		this.confirmMove = confirmMove;
	}

	@JsonProperty("conflictFlag")
	private boolean conflictFlag;
    
	@JsonProperty("insertedFlag")
	private boolean insertedFlag;
    
    @JsonProperty("nbtCreateUserId")
	private String nbtCreateUserId;
	
	public VOffenderAllSchedules() {
		// VOffenderAllSchedules
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	private String agencyImlDesc;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getAgencyImlDesc() {
		return agencyImlDesc;
	}

	public void setAgencyImlDesc(final String agencyImlDesc) {
		this.agencyImlDesc = agencyImlDesc;
	}

	public BigDecimal getAgencyImlId() {
		return agencyImlId;
	}

	public void setAgencyImlId(final BigDecimal agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	public String getAgencyImlLevel1Code() {
		return agencyImlLevel1Code;
	}

	public void setAgencyImlLevel1Code(final String agencyImlLevel1Code) {
		this.agencyImlLevel1Code = agencyImlLevel1Code;
	}

	public String getAgencyImlLevel2Code() {
		return agencyImlLevel2Code;
	}

	public void setAgencyImlLevel2Code(final String agencyImlLevel2Code) {
		this.agencyImlLevel2Code = agencyImlLevel2Code;
	}

	public String getAgencyImlLevel3Code() {
		return agencyImlLevel3Code;
	}

	public void setAgencyImlLevel3Code(final String agencyImlLevel3Code) {
		this.agencyImlLevel3Code = agencyImlLevel3Code;
	}

	public BigDecimal getAgreedTravelHour() {
		return agreedTravelHour;
	}

	public void setAgreedTravelHour(final BigDecimal agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
	}

	public String getAgyLocDesc() {
		return agyLocDesc;
	}

	public void setAgyLocDesc(final String agyLocDesc) {
		this.agyLocDesc = agyLocDesc;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(final Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(final Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getBookingActiveFlag() {
		return bookingActiveFlag;
	}

	public void setBookingActiveFlag(final String bookingActiveFlag) {
		this.bookingActiveFlag = bookingActiveFlag;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(final String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getBusyDateFlag() {
		return busyDateFlag;
	}

	public void setBusyDateFlag(final String busyDateFlag) {
		this.busyDateFlag = busyDateFlag;
	}

	public String getCheckBox1() {
		return checkBox1;
	}

	public void setCheckBox1(final String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	public String getCheckBox2() {
		return checkBox2;
	}

	public void setCheckBox2(final String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	public BigDecimal getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(final BigDecimal checkSum) {
		this.checkSum = checkSum;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(final String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public BigDecimal getCreditedHours() {
		return creditedHours;
	}

	public void setCreditedHours(final BigDecimal creditedHours) {
		this.creditedHours = creditedHours;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(final String directionCode) {
		this.directionCode = directionCode;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public String getEngagementCode() {
		return engagementCode;
	}

	public void setEngagementCode(final String engagementCode) {
		this.engagementCode = engagementCode;
	}

	public String getEscortCode() {
		return escortCode;
	}

	public void setEscortCode(final String escortCode) {
		this.escortCode = escortCode;
	}

	public String getEscortDesc() {
		return escortDesc;
	}

	public void setEscortDesc(final String escortDesc) {
		this.escortDesc = escortDesc;
	}

	public String getEventClass() {
		return eventClass;
	}

	public void setEventClass(final String eventClass) {
		this.eventClass = eventClass;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(final BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventOutcome() {
		return eventOutcome;
	}

	public void setEventOutcome(final String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public String getEventOutcomeDesc() {
		return eventOutcomeDesc;
	}

	public void setEventOutcomeDesc(final String eventOutcomeDesc) {
		this.eventOutcomeDesc = eventOutcomeDesc;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(final String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventStatusDesc() {
		return eventStatusDesc;
	}

	public void setEventStatusDesc(final String eventStatusDesc) {
		this.eventStatusDesc = eventStatusDesc;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(final String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getEventSubTypeDesc() {
		return eventSubTypeDesc;
	}

	public void setEventSubTypeDesc(final String eventSubTypeDesc) {
		this.eventSubTypeDesc = eventSubTypeDesc;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	public String getEventTypeDesc() {
		return eventTypeDesc;
	}

	public void setEventTypeDesc(final String eventTypeDesc) {
		this.eventTypeDesc = eventTypeDesc;
	}

	public String getFromCityCode() {
		return fromCityCode;
	}

	public void setFromCityCode(final String fromCityCode) {
		this.fromCityCode = fromCityCode;
	}

	public String getFromCityName() {
		return fromCityName;
	}

	public void setFromCityName(final String fromCityName) {
		this.fromCityName = fromCityName;
	}

	public String getHiddenCommentText() {
		return hiddenCommentText;
	}

	public void setHiddenCommentText(final String hiddenCommentText) {
		this.hiddenCommentText = hiddenCommentText;
	}

	public BigDecimal getInChargeStaffId() {
		return inChargeStaffId;
	}

	public void setInChargeStaffId(final BigDecimal inChargeStaffId) {
		this.inChargeStaffId = inChargeStaffId;
	}

	public String getInChargeStaffName() {
		return inChargeStaffName;
	}

	public void setInChargeStaffName(final String inChargeStaffName) {
		this.inChargeStaffName = inChargeStaffName;
	}

	public String getInOutStatus() {
		return inOutStatus;
	}

	public void setInOutStatus(final String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(final Date inTime) {
		this.inTime = inTime;
	}

	public String getLivingUnitDesc() {
		return livingUnitDesc;
	}

	public void setLivingUnitDesc(final String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	public void setLivingUnitId(final BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public String getLuLevel1Code() {
		return luLevel1Code;
	}

	public void setLuLevel1Code(final String luLevel1Code) {
		this.luLevel1Code = luLevel1Code;
	}

	public String getLuLevel2Code() {
		return luLevel2Code;
	}

	public void setLuLevel2Code(final String luLevel2Code) {
		this.luLevel2Code = luLevel2Code;
	}

	public String getLuLevel3Code() {
		return luLevel3Code;
	}

	public void setLuLevel3Code(final String luLevel3Code) {
		this.luLevel3Code = luLevel3Code;
	}

	public String getLuLevel4Code() {
		return luLevel4Code;
	}

	public void setLuLevel4Code(final String luLevel4Code) {
		this.luLevel4Code = luLevel4Code;
	}

	public BigDecimal getOffPrgrefId() {
		return offPrgrefId;
	}

	public void setOffPrgrefId(final BigDecimal offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	public void setOffenderFirstName(final String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getOffenderLastName() {
		return offenderLastName;
	}

	public void setOffenderLastName(final String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(final Date outTime) {
		this.outTime = outTime;
	}

	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	public void setOutcomeReasonCode(final String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public String getPerformanceCode() {
		return performanceCode;
	}

	public void setPerformanceCode(final String performanceCode) {
		this.performanceCode = performanceCode;
	}

	public BigDecimal getPieceWork() {
		return pieceWork;
	}

	public void setPieceWork(final BigDecimal pieceWork) {
		this.pieceWork = pieceWork;
	}

	public String getProvStateCode() {
		return provStateCode;
	}

	public void setProvStateCode(final String provStateCode) {
		this.provStateCode = provStateCode;
	}

	public String getProvStateDesc() {
		return provStateDesc;
	}

	public void setProvStateDesc(final String provStateDesc) {
		this.provStateDesc = provStateDesc;
	}

	public String getRecordSource() {
		return recordSource;
	}

	public void setRecordSource(final String recordSource) {
		this.recordSource = recordSource;
	}

	public BigDecimal getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(final BigDecimal referenceId) {
		this.referenceId = referenceId;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(final Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(final Date returnTime) {
		this.returnTime = returnTime;
	}

	public Date getScheduleMovementTime() {
		return scheduleMovementTime;
	}

	public void setScheduleMovementTime(final Date scheduleMovementTime) {
		this.scheduleMovementTime = scheduleMovementTime;
	}

	public BigDecimal getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(final BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public Date getSickNoteExpiryDate() {
		return sickNoteExpiryDate;
	}

	public void setSickNoteExpiryDate(final Date sickNoteExpiryDate) {
		this.sickNoteExpiryDate = sickNoteExpiryDate;
	}

	public Date getSickNoteReceivedDate() {
		return sickNoteReceivedDate;
	}

	public void setSickNoteReceivedDate(final Date sickNoteReceivedDate) {
		this.sickNoteReceivedDate = sickNoteReceivedDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getTaId() {
		return taId;
	}

	public void setTaId(final BigDecimal taId) {
		this.taId = taId;
	}

	public BigDecimal getToAddressId() {
		return toAddressId;
	}

	public void setToAddressId(final BigDecimal toAddressId) {
		this.toAddressId = toAddressId;
	}

	public String getToAddressOwnerClass() {
		return toAddressOwnerClass;
	}

	public void setToAddressOwnerClass(final String toAddressOwnerClass) {
		this.toAddressOwnerClass = toAddressOwnerClass;
	}

	public String getToAgyLocDesc() {
		return toAgyLocDesc;
	}

	public void setToAgyLocDesc(final String toAgyLocDesc) {
		this.toAgyLocDesc = toAgyLocDesc;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(final String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public String getToCityCode() {
		return toCityCode;
	}

	public void setToCityCode(final String toCityCode) {
		this.toCityCode = toCityCode;
	}

	public String getToCityName() {
		return toCityName;
	}

	public void setToCityName(final String toCityName) {
		this.toCityName = toCityName;
	}

	public String getToIntLocLevel1Code() {
		return toIntLocLevel1Code;
	}

	public void setToIntLocLevel1Code(final String toIntLocLevel1Code) {
		this.toIntLocLevel1Code = toIntLocLevel1Code;
	}

	public String getToIntLocLevel2Code() {
		return toIntLocLevel2Code;
	}

	public void setToIntLocLevel2Code(final String toIntLocLevel2Code) {
		this.toIntLocLevel2Code = toIntLocLevel2Code;
	}

	public String getToIntLocLevel3Code() {
		return toIntLocLevel3Code;
	}

	public void setToIntLocLevel3Code(final String toIntLocLevel3Code) {
		this.toIntLocLevel3Code = toIntLocLevel3Code;
	}

	public String getToIntLocUserDesc() {
		return toIntLocUserDesc;
	}

	public void setToIntLocUserDesc(final String toIntLocUserDesc) {
		this.toIntLocUserDesc = toIntLocUserDesc;
	}

	public String getToInternalLocationDesc() {
		return toInternalLocationDesc;
	}

	public void setToInternalLocationDesc(final String toInternalLocationDesc) {
		this.toInternalLocationDesc = toInternalLocationDesc;
	}

	public BigDecimal getToInternalLocationId() {
		return toInternalLocationId;
	}

	public void setToInternalLocationId(final BigDecimal toInternalLocationId) {
		this.toInternalLocationId = toInternalLocationId;
	}

	public String getToLoc() {
		return toLoc;
	}

	public void setToLoc(final String toLoc) {
		this.toLoc = toLoc;
	}

	public String getToLocDesc() {
		return toLocDesc;
	}

	public void setToLocDesc(final String toLocDesc) {
		this.toLocDesc = toLocDesc;
	}

	public String getTransportCode() {
		return transportCode;
	}

	public void setTransportCode(final String transportCode) {
		this.transportCode = transportCode;
	}

	public String getUnderstandingCode() {
		return understandingCode;
	}

	public void setUnderstandingCode(final String understandingCode) {
		this.understandingCode = understandingCode;
	}

	public String getUnexcusedAbsenceFlag() {
		return unexcusedAbsenceFlag;
	}

	public void setUnexcusedAbsenceFlag(final String unexcusedAbsenceFlag) {
		this.unexcusedAbsenceFlag = unexcusedAbsenceFlag;
	}

	public String getUnpaidWorkAction() {
		return unpaidWorkAction;
	}

	public void setUnpaidWorkAction(final String unpaidWorkAction) {
		this.unpaidWorkAction = unpaidWorkAction;
	}

	public String getUnpaidWorkBehaviour() {
		return unpaidWorkBehaviour;
	}

	public void setUnpaidWorkBehaviour(final String unpaidWorkBehaviour) {
		this.unpaidWorkBehaviour = unpaidWorkBehaviour;
	}

	public String getUnpaidWorkSupervisor() {
		return unpaidWorkSupervisor;
	}

	public void setUnpaidWorkSupervisor(final String unpaidWorkSupervisor) {
		this.unpaidWorkSupervisor = unpaidWorkSupervisor;
	}

	public String getDaysOut() {
		return daysOut;
	}

	public void setDaysOut(final String daysOut) {
		this.daysOut = daysOut;
	}

	public String getHoursOut() {
		return hoursOut;
	}

	public void setHoursOut(final String hoursOut) {
		this.hoursOut = hoursOut;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the fMTime
	 */
	public Date getfMTime() {
		return fMTime;
	}

	/**
	 * @param fMTime the fMTime to set
	 */
	public void setfMTime(final Date fMTime) {
		this.fMTime = fMTime;
	}

	/**
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	/**
	 * @param caseLoadId the caseLoadId to set
	 */
	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}
	
	/**
	 * @return the nbtEventId
	 */
	public int getNbtEventId() {
		return nbtEventId;
	}

	/**
	 * @param nbtEventId the nbtEventId to set
	 */
	public void setNbtEventId(final int nbtEventId) {
		this.nbtEventId = nbtEventId;
	}

	/**
	 * @return the nbtRequestDate
	 */
	public Date getNbtRequestDate() {
		return nbtRequestDate;
	}

	/**
	 * @param nbtRequestDate the nbtRequestDate to set
	 */
	public void setNbtRequestDate(final Date nbtRequestDate) {
		this.nbtRequestDate = nbtRequestDate;
	}

	/**
	 * @return the nbtwaitListStatus
	 */
	public String getNbtWaitListStatus() {
		return nbtWaitListStatus;
	}

	/**
	 * @param nbtWaitListStatus the nbtwaitListStatus to set
	 */
	public void setNbtwaitListStatus(final String nbtWaitListStatus) {
		this.nbtWaitListStatus = nbtWaitListStatus;
	}

	/**
	 * @return the nbtStatusDate
	 */
	public Date getNbtStatusDate() {
		return nbtStatusDate;
	}

	/**
	 * @param nbtStatusDate the nbtStatusDate to set
	 */
	public void setNbtStatusDate(final Date nbtStatusDate) {
		this.nbtStatusDate = nbtStatusDate;
	}

	/**
	 * @return the nbtTransferPriority
	 */
	public String getNbtTransferPriority() {
		return nbtTransferPriority;
	}

	/**
	 * @param nbtTransferPriority the nbtTransferPriority to set
	 */
	public void setNbtTransferPriority(final String nbtTransferPriority) {
		this.nbtTransferPriority = nbtTransferPriority;
	}

	/**
	 * @return the nbtApprovedFlag
	 */
	public String getNbtApprovedFlag() {
		return nbtApprovedFlag;
	}

	/**
	 * @param nbtApprovedFlag the nbtApprovedFlag to set
	 */
	public void setNbtApprovedFlag(final String nbtApprovedFlag) {
		this.nbtApprovedFlag = nbtApprovedFlag;
	}

	/**
	 * @return the nbtApprovedStaffId
	 */
	public Integer getNbtApprovedStaffId() {
		return nbtApprovedStaffId;
	}

	/**
	 * @param nbtApprovedStaffId the nbtApprovedStaffId to set
	 */
	public void setNbtApprovedStaffId(final Integer nbtApprovedStaffId) {
		this.nbtApprovedStaffId = nbtApprovedStaffId;
	}

	/**
	 * @return the nbtOutcomeReasonCode
	 */
	public String getNbtOutcomeReasonCode() {
		return nbtOutcomeReasonCode;
	}

	/**
	 * @param nbtOutcomeReasonCode the nbtOutcomeReasonCode to set
	 */
	public void setNbtOutcomeReasonCode(final String nbtOutcomeReasonCode) {
		this.nbtOutcomeReasonCode = nbtOutcomeReasonCode;
	}

	/**
	 * @return the nbtCommentText1
	 */
	public String getNbtCommentText1() {
		return nbtCommentText1;
	}

	/**
	 * @param nbtCommentText1 the nbtCommentText1 to set
	 */
	public void setNbtCommentText1(final String nbtCommentText1) {
		this.nbtCommentText1 = nbtCommentText1;
	}

	/**
	 * @return the nbtLastName
	 */
	public String getNbtLastName() {
		return nbtLastName;
	}

	/**
	 * @param nbtLastName the nbtLastName to set
	 */
	public void setNbtLastName(final String nbtLastName) {
		this.nbtLastName = nbtLastName;
	}

	/**
	 * @return the nbtFirstName
	 */
	public String getNbtFirstName() {
		return nbtFirstName;
	}

	/**
	 * @param nbtFirstName the nbtFirstName to set
	 */
	public void setNbtFirstName(final String nbtFirstName) {
		this.nbtFirstName = nbtFirstName;
	}

	
	/**
	 * @return the nbtCreateDatetime
	 */
	public Date getNbtCreateDatetime() {
		return nbtCreateDatetime;
	}

	/**
	 * @param nbtCreateDatetime the nbtCreateDatetime to set
	 */
	public void setNbtCreateDatetime(final Date nbtCreateDatetime) {
		this.nbtCreateDatetime = nbtCreateDatetime;
	}
	

	public boolean getConflictFlag() {
		return conflictFlag;
	}

	public void setConflictFlag(final boolean conflictFlag) {
		this.conflictFlag = conflictFlag;
	}

	public boolean getInsertedFlag() {
		return insertedFlag;
	}

	public void setInsertedFlag(final boolean insertedFlag) {
		this.insertedFlag = insertedFlag;
	}

	public void setNbtWaitListStatus(final String nbtWaitListStatus) {
		this.nbtWaitListStatus = nbtWaitListStatus;
	}
	
	 public String getNbtCreateUserId() {
			return nbtCreateUserId;
		}

	public void setNbtCreateUserId(final String nbtCreateUserId) {
		this.nbtCreateUserId = nbtCreateUserId;
	}

	public BigDecimal getOffenderBookIdOne() {
		return offenderBookIdOne;
	}

	public void setOffenderBookIdOne(BigDecimal offenderBookIdOne) {
		this.offenderBookIdOne = offenderBookIdOne;
	}

	public BigDecimal getOffenderIdOne() {
		return offenderIdOne;
	}

	public void setOffenderIdOne(BigDecimal offenderIdOne) {
		this.offenderIdOne = offenderIdOne;
	}

	public BigDecimal getToInternalLocationIdOne() {
		return toInternalLocationIdOne;
	}

	public void setToInternalLocationIdOne(BigDecimal toInternalLocationIdOne) {
		this.toInternalLocationIdOne = toInternalLocationIdOne;
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

	public String getLvNaFlag() {
		return lvNaFlag;
	}

	public void setLvNaFlag(String lvNaFlag) {
		this.lvNaFlag = lvNaFlag;
	}

	public String getOffenderIdDisplayStg() {
		return offenderIdDisplayStg;
	}

	public void setOffenderIdDisplayStg(String offenderIdDisplayStg) {
		this.offenderIdDisplayStg = offenderIdDisplayStg;
	}

	public String getFirstNameStg() {
		return firstNameStg;
	}

	public void setFirstNameStg(String firstNameStg) {
		this.firstNameStg = firstNameStg;
	}

	public String getLastNameStg() {
		return lastNameStg;
	}

	public void setLastNameStg(String lastNameStg) {
		this.lastNameStg = lastNameStg;
	}

	public String getLvNaFlagOne() {
		return lvNaFlagOne;
	}

	public void setLvNaFlagOne(String lvNaFlagOne) {
		this.lvNaFlagOne = lvNaFlagOne;
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

	public Integer getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(Integer returnValue) {
		this.returnValue = returnValue;
	}

	public Integer getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(Integer adjustment) {
		this.adjustment = adjustment;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getMovementTime() {
		return movementTime;
	}

	public void setMovementTime(Date movementTime) {
		this.movementTime = movementTime;
	}

	public Integer getPhoneNumberCount() {
		return phoneNumberCount;
	}

	public void setPhoneNumberCount(Integer phoneNumberCount) {
		this.phoneNumberCount = phoneNumberCount;
	}

	public String getNonAssociationFlag() {
		return nonAssociationFlag;
	}

	public void setNonAssociationFlag(String nonAssociationFlag) {
		this.nonAssociationFlag = nonAssociationFlag;
	}

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public Boolean getIsSeriesDelete() {
		return isSeriesDelete;
	}

	public void setIsSeriesDelete(Boolean isSeriesDelete) {
		this.isSeriesDelete = isSeriesDelete;
	}

	public String getAppearanceLocation() {
		return appearanceLocation;
	}

	public void setAppearanceLocation(String appearanceLocation) {
		this.appearanceLocation = appearanceLocation;
	}

	public String getAppearanceType() {
		return appearanceType;
	}

	public void setAppearanceType(String appearanceType) {
		this.appearanceType = appearanceType;
	}

	public String getTierLevelCode() {
		return tierLevelCode;
	}

	public void setTierLevelCode(String tierLevelCode) {
		this.tierLevelCode = tierLevelCode;
	}

	public BigDecimal getOffenderTierLevelId() {
		return offenderTierLevelId;
	}

	public void setOffenderTierLevelId(BigDecimal offenderTierLevelId) {
		this.offenderTierLevelId = offenderTierLevelId;
	}

	public BigDecimal getEventIdTemp() {
		return eventIdTemp;
	}

	public void setEventIdTemp(BigDecimal eventIdTemp) {
		this.eventIdTemp = eventIdTemp;
	}

	public Integer getLinkData() {
		return linkData;
	}

	public void setLinkData(Integer linkData) {
		this.linkData = linkData;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getCurrentSelectedViewClass() {
		return currentSelectedViewClass;
	}

	public void setCurrentSelectedViewClass(String currentSelectedViewClass) {
		this.currentSelectedViewClass = currentSelectedViewClass;
	}

	public String getCsAddress() {
		return csAddress;
	}

	public void setCsAddress(String csAddress) {
		this.csAddress = csAddress;
	}

	public String getCsDescription() {
		return csDescription;
	}

	public void setCsDescription(String csDescription) {
		this.csDescription = csDescription;
	}


	public Date getDepartStartTime() {
		return departStartTime;
	}

	public void setDepartStartTime(Date departStartTime) {
		this.departStartTime = departStartTime;
	}
	public String getOicHearingDescription() {
		return oicHearingDescription;
	}
	public void setOicHearingDescription(String oicHearingDescription) {
		this.oicHearingDescription = oicHearingDescription;
	}
	public String getVisitDescription() {
		return visitDescription;
	}
	public void setVisitDescription(String visitDescription) {
		this.visitDescription = visitDescription;
	}
	public String getWorkRelaeseProjectCode() {
		return workRelaeseProjectCode;
	}
	public void setWorkRelaeseProjectCode(String workRelaeseProjectCode) {
		this.workRelaeseProjectCode = workRelaeseProjectCode;
	}
	public String getWorkRelaeseProvideDesc() {
		return workRelaeseProvideDesc;
	}
	public void setWorkRelaeseProvideDesc(String workRelaeseProvideDesc) {
		this.workRelaeseProvideDesc = workRelaeseProvideDesc;
	}
	public String getOutComeFlag() {
		return outComeFlag;
	}
	public void setOutComeFlag(String outComeFlag) {
		this.outComeFlag = outComeFlag;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getTapDescription() {
		return tapDescription;
	}
	public void setTapDescription(String tapDescription) {
		this.tapDescription = tapDescription;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	
	
	
	
}
