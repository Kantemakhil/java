package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderCourseAttendances extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String actionCode;

	private Long agreedTravelHour;

	private String agyLocId;

	private String behaviourCode;

	private String commentText;

	private Long creditedHours;

	private Long crsActyId;

	private Long crsApptId;

	private Long crsSchId;

	private String details;

	private Date endTime;

	private String engagementCode;

	private String eventClass;

	private Date eventDate;

	private Long eventId;

	private String eventOutcome;

	private String eventOutcomeDesc;

	private String eventStatus;

	private String eventSubType;

	private String eventType;

	private String firstName;

	private String hiddenCommentText;

	private Date inTime;

	private String lastName;

	private Long offCrsSchRefId;

	private Long offPrgrefId;

	private Long offenderBookId;

	private Long offenderCourseApptRuleId;

	private Long offenderId;

	private String offenderIdDisplay;

	private String offenderName;

	private Long offenderSentConditionId;

	private Date outTime;

	private String outcomeReasonCode;

	private String performanceCode;

	private String performanceDesc;

	private Long pieceWork;

	private String programCategory;

	private String programCategoryDesc;

	private Long programId;

	private Long referenceId;

	private Long sentenceSeq;

	private Date sickNoteExpiryDate;

	private Date sickNoteReceivedDate;

	private Date startTime;

	private String supervisorName;

	private Long supervisorStaffId;

	private Long toAddressId;

	private String toAddressOwnerClass;

	private String toInternalLocationDesc;

	private Long toInternalLocationId;

	private String understandingCode;
	
	private String sealFlag;
	
	private String modifyUserId;
	
	@JsonProperty("payFlag")
	private String payFlag;

	@JsonProperty("paySystemRate")
	private BigDecimal paySystemRate;
	
	@JsonProperty("payHours")
	private BigDecimal payHours;
	
	@JsonProperty("paySystemUnit")
	private String paySystemUnit;
	
	@JsonProperty("activityDescription")
	private String activityDescription;
	
	@JsonProperty("programDescription")
	private String programDescription;
	
	@JsonProperty("payBatchId")
	private Integer payBatchId;
	
	@JsonProperty("totalAmount")
	private BigDecimal totalAmount;
	
	@JsonProperty("payActualAmount")
	private BigDecimal payActualAmount;
	
	@JsonProperty("payActualRate")
	private BigDecimal payActualRate;
	
	@JsonProperty("payActualUnit")
	private String payActualUnit;
	
	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("parentCrsActyId")
	private Long parentCrsActyId;
	
	@JsonProperty("payRunDetails")
	private String payRunDetails;
	
	@JsonProperty("detailId")
	private Integer detailId;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("offAllowanceId")
	private BigDecimal offAllowanceId;
	
	@JsonProperty("allowancePayAmount")
	private Integer allowancePayAmount;
	

	/**
	 * @return the actionCode
	 */
	public String getActionCode() {
		return actionCode;
	}

	/**
	 * @param actionCode
	 *            the actionCode to set
	 */
	public void setActionCode(final String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * @return the agreedTravelHour
	 */
	public Long getAgreedTravelHour() {
		return agreedTravelHour;
	}

	/**
	 * @param agreedTravelHour
	 *            the agreedTravelHour to set
	 */
	public void setAgreedTravelHour(final Long agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the behaviourCode
	 */
	public String getBehaviourCode() {
		return behaviourCode;
	}

	/**
	 * @param behaviourCode
	 *            the behaviourCode to set
	 */
	public void setBehaviourCode(final String behaviourCode) {
		this.behaviourCode = behaviourCode;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the creditedHours
	 */
	public Long getCreditedHours() {
		return creditedHours;
	}

	/**
	 * @param creditedHours
	 *            the creditedHours to set
	 */
	public void setCreditedHours(final Long creditedHours) {
		this.creditedHours = creditedHours;
	}

	/**
	 * @return the crsActyId
	 */
	public Long getCrsActyId() {
		return crsActyId;
	}

	/**
	 * @param crsActyId
	 *            the crsActyId to set
	 */
	public void setCrsActyId(final Long crsActyId) {
		this.crsActyId = crsActyId;
	}

	/**
	 * @return the crsApptId
	 */
	public Long getCrsApptId() {
		return crsApptId;
	}

	/**
	 * @param crsApptId
	 *            the crsApptId to set
	 */
	public void setCrsApptId(final Long crsApptId) {
		this.crsApptId = crsApptId;
	}

	/**
	 * @return the crsSchId
	 */
	public Long getCrsSchId() {
		return crsSchId;
	}

	/**
	 * @param crsSchId
	 *            the crsSchId to set
	 */
	public void setCrsSchId(final Long crsSchId) {
		this.crsSchId = crsSchId;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(final String details) {
		this.details = details;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the engagementCode
	 */
	public String getEngagementCode() {
		return engagementCode;
	}

	/**
	 * @param engagementCode
	 *            the engagementCode to set
	 */
	public void setEngagementCode(final String engagementCode) {
		this.engagementCode = engagementCode;
	}

	/**
	 * @return the eventClass
	 */
	public String getEventClass() {
		return eventClass;
	}

	/**
	 * @param eventClass
	 *            the eventClass to set
	 */
	public void setEventClass(final String eventClass) {
		this.eventClass = eventClass;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(final Long eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventOutcome
	 */
	public String getEventOutcome() {
		return eventOutcome;
	}

	/**
	 * @param eventOutcome
	 *            the eventOutcome to set
	 */
	public void setEventOutcome(final String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	/**
	 * @return the eventOutcomeDesc
	 */
	public String getEventOutcomeDesc() {
		return eventOutcomeDesc;
	}

	/**
	 * @param eventOutcomeDesc
	 *            the eventOutcomeDesc to set
	 */
	public void setEventOutcomeDesc(final String eventOutcomeDesc) {
		this.eventOutcomeDesc = eventOutcomeDesc;
	}

	/**
	 * @return the eventStatus
	 */
	public String getEventStatus() {
		return eventStatus;
	}

	/**
	 * @param eventStatus
	 *            the eventStatus to set
	 */
	public void setEventStatus(final String eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * @return the eventSubType
	 */
	public String getEventSubType() {
		return eventSubType;
	}

	/**
	 * @param eventSubType
	 *            the eventSubType to set
	 */
	public void setEventSubType(final String eventSubType) {
		this.eventSubType = eventSubType;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the hiddenCommentText
	 */
	public String getHiddenCommentText() {
		return hiddenCommentText;
	}

	/**
	 * @param hiddenCommentText
	 *            the hiddenCommentText to set
	 */
	public void setHiddenCommentText(final String hiddenCommentText) {
		this.hiddenCommentText = hiddenCommentText;
	}

	/**
	 * @return the inTime
	 */
	public Date getInTime() {
		return inTime;
	}

	/**
	 * @param inTime
	 *            the inTime to set
	 */
	public void setInTime(final Date inTime) {
		this.inTime = inTime;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the offCrsSchRefId
	 */
	public Long getOffCrsSchRefId() {
		return offCrsSchRefId;
	}

	/**
	 * @param offCrsSchRefId
	 *            the offCrsSchRefId to set
	 */
	public void setOffCrsSchRefId(final Long offCrsSchRefId) {
		this.offCrsSchRefId = offCrsSchRefId;
	}

	/**
	 * @return the offPrgrefId
	 */
	public Long getOffPrgrefId() {
		return offPrgrefId;
	}

	/**
	 * @param offPrgrefId
	 *            the offPrgrefId to set
	 */
	public void setOffPrgrefId(final Long offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the offenderCourseApptRuleId
	 */
	public Long getOffenderCourseApptRuleId() {
		return offenderCourseApptRuleId;
	}

	/**
	 * @param offenderCourseApptRuleId
	 *            the offenderCourseApptRuleId to set
	 */
	public void setOffenderCourseApptRuleId(final Long offenderCourseApptRuleId) {
		this.offenderCourseApptRuleId = offenderCourseApptRuleId;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the offenderName
	 */
	public String getOffenderName() {
		return offenderName;
	}

	/**
	 * @param offenderName
	 *            the offenderName to set
	 */
	public void setOffenderName(final String offenderName) {
		this.offenderName = offenderName;
	}

	/**
	 * @return the offenderSentConditionId
	 */
	public Long getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	/**
	 * @param offenderSentConditionId
	 *            the offenderSentConditionId to set
	 */
	public void setOffenderSentConditionId(final Long offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	/**
	 * @return the outTime
	 */
	public Date getOutTime() {
		return outTime;
	}

	/**
	 * @param outTime
	 *            the outTime to set
	 */
	public void setOutTime(final Date outTime) {
		this.outTime = outTime;
	}

	/**
	 * @return the outcomeReasonCode
	 */
	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	/**
	 * @param outcomeReasonCode
	 *            the outcomeReasonCode to set
	 */
	public void setOutcomeReasonCode(final String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	/**
	 * @return the performanceCode
	 */
	public String getPerformanceCode() {
		return performanceCode;
	}

	/**
	 * @param performanceCode
	 *            the performanceCode to set
	 */
	public void setPerformanceCode(final String performanceCode) {
		this.performanceCode = performanceCode;
	}

	/**
	 * @return the performanceDesc
	 */
	public String getPerformanceDesc() {
		return performanceDesc;
	}

	/**
	 * @param performanceDesc
	 *            the performanceDesc to set
	 */
	public void setPerformanceDesc(final String performanceDesc) {
		this.performanceDesc = performanceDesc;
	}

	/**
	 * @return the pieceWork
	 */
	public Long getPieceWork() {
		return pieceWork;
	}

	/**
	 * @param pieceWork
	 *            the pieceWork to set
	 */
	public void setPieceWork(final Long pieceWork) {
		this.pieceWork = pieceWork;
	}

	/**
	 * @return the programCategory
	 */
	public String getProgramCategory() {
		return programCategory;
	}

	/**
	 * @param programCategory
	 *            the programCategory to set
	 */
	public void setProgramCategory(final String programCategory) {
		this.programCategory = programCategory;
	}

	/**
	 * @return the programCategoryDesc
	 */
	public String getProgramCategoryDesc() {
		return programCategoryDesc;
	}

	/**
	 * @param programCategoryDesc
	 *            the programCategoryDesc to set
	 */
	public void setProgramCategoryDesc(final String programCategoryDesc) {
		this.programCategoryDesc = programCategoryDesc;
	}

	/**
	 * @return the programId
	 */
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(final Long programId) {
		this.programId = programId;
	}

	/**
	 * @return the referenceId
	 */
	public Long getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId
	 *            the referenceId to set
	 */
	public void setReferenceId(final Long referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return the sentenceSeq
	 */
	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @param sentenceSeq
	 *            the sentenceSeq to set
	 */
	public void setSentenceSeq(final Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 * @return the sickNoteExpiryDate
	 */
	public Date getSickNoteExpiryDate() {
		return sickNoteExpiryDate;
	}

	/**
	 * @param sickNoteExpiryDate
	 *            the sickNoteExpiryDate to set
	 */
	public void setSickNoteExpiryDate(final Date sickNoteExpiryDate) {
		this.sickNoteExpiryDate = sickNoteExpiryDate;
	}

	/**
	 * @return the sickNoteReceivedDate
	 */
	public Date getSickNoteReceivedDate() {
		return sickNoteReceivedDate;
	}

	/**
	 * @param sickNoteReceivedDate
	 *            the sickNoteReceivedDate to set
	 */
	public void setSickNoteReceivedDate(final Date sickNoteReceivedDate) {
		this.sickNoteReceivedDate = sickNoteReceivedDate;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the supervisorName
	 */
	public String getSupervisorName() {
		return supervisorName;
	}

	/**
	 * @param supervisorName
	 *            the supervisorName to set
	 */
	public void setSupervisorName(final String supervisorName) {
		this.supervisorName = supervisorName;
	}

	/**
	 * @return the supervisorStaffId
	 */
	public Long getSupervisorStaffId() {
		return supervisorStaffId;
	}

	/**
	 * @param supervisorStaffId
	 *            the supervisorStaffId to set
	 */
	public void setSupervisorStaffId(final Long supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	/**
	 * @return the toAddressId
	 */
	public Long getToAddressId() {
		return toAddressId;
	}

	/**
	 * @param toAddressId
	 *            the toAddressId to set
	 */
	public void setToAddressId(final Long toAddressId) {
		this.toAddressId = toAddressId;
	}

	/**
	 * @return the toAddressOwnerClass
	 */
	public String getToAddressOwnerClass() {
		return toAddressOwnerClass;
	}

	/**
	 * @param toAddressOwnerClass
	 *            the toAddressOwnerClass to set
	 */
	public void setToAddressOwnerClass(final String toAddressOwnerClass) {
		this.toAddressOwnerClass = toAddressOwnerClass;
	}

	/**
	 * @return the toInternalLocationDesc
	 */
	public String getToInternalLocationDesc() {
		return toInternalLocationDesc;
	}

	/**
	 * @param toInternalLocationDesc
	 *            the toInternalLocationDesc to set
	 */
	public void setToInternalLocationDesc(final String toInternalLocationDesc) {
		this.toInternalLocationDesc = toInternalLocationDesc;
	}

	/**
	 * @return the toInternalLocationId
	 */
	public Long getToInternalLocationId() {
		return toInternalLocationId;
	}

	/**
	 * @param toInternalLocationId
	 *            the toInternalLocationId to set
	 */
	public void setToInternalLocationId(final Long toInternalLocationId) {
		this.toInternalLocationId = toInternalLocationId;
	}

	/**
	 * @return the understandingCode
	 */
	public String getUnderstandingCode() {
		return understandingCode;
	}

	/**
	 * @param understandingCode
	 *            the understandingCode to set
	 */
	public void setUnderstandingCode(final String understandingCode) {
		this.understandingCode = understandingCode;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public BigDecimal getPaySystemRate() {
		return paySystemRate;
	}

	public String getPaySystemUnit() {
		return paySystemUnit;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public String getProgramDescription() {
		return programDescription;
	}

	public void setPaySystemRate(BigDecimal paySystemRate) {
		this.paySystemRate = paySystemRate;
	}

	public void setPaySystemUnit(String paySystemUnit) {
		this.paySystemUnit = paySystemUnit;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public Integer getPayBatchId() {
		return payBatchId;
	}

	public void setPayBatchId(Integer payBatchId) {
		this.payBatchId = payBatchId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPayHours() {
		return payHours;
	}

	public void setPayHours(BigDecimal payHours) {
		this.payHours = payHours;
	}

	public BigDecimal getPayActualAmount() {
		return payActualAmount;
	}

	public void setPayActualAmount(BigDecimal payActualAmount) {
		this.payActualAmount = payActualAmount;
	}

	public BigDecimal getPayActualRate() {
		return payActualRate;
	}

	public void setPayActualRate(BigDecimal payActualRate) {
		this.payActualRate = payActualRate;
	}

	public String getPayActualUnit() {
		return payActualUnit;
	}

	public void setPayActualUnit(String payActualUnit) {
		this.payActualUnit = payActualUnit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Long getParentCrsActyId() {
		return parentCrsActyId;
	}

	public void setParentCrsActyId(Long parentCrsActyId) {
		this.parentCrsActyId = parentCrsActyId;
	}

	public void setPayRunDetails(String payRunDetails) {
		this.payRunDetails = payRunDetails;
	}

	public Object getPayRunDetails() {
		return payRunDetails;
	}
	
	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getOffAllowanceId() {
		return offAllowanceId;
	}

	public void setOffAllowanceId(BigDecimal offAllowanceId) {
		this.offAllowanceId = offAllowanceId;
	}
	
	public Integer getAllowancePayAmount() {
		return allowancePayAmount;
	}

	public void setAllowancePayAmount(Integer allowancePayAmount) {
		this.allowancePayAmount = allowancePayAmount;
	}
}
