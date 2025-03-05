package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SelectHearing extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("eventId")
	private int eventId;
	@JsonProperty("caseId")
	private int caseId;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("eventDate")
	private Date eventDate;
	@JsonProperty("nbtEventDate")
	private Date nbtEventDate;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("hearingType")
	private String hearingType;
	@JsonProperty("judgeName")
	private String judgeName;
	@JsonProperty("eventStatus")
	private String eventStatus;
	@JsonProperty("parentEventId")
	private int parentEventId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("eventOutcome")
	private String eventOutcome;
	@JsonProperty("nextEventRequestFlag")
	private String nextEventRequestFlag;
	@JsonProperty("orderRequestedFlag")
	private String orderRequestedFlag;
	@JsonProperty("resultCode")
	private String resultCode;
	@JsonProperty("nextEventDate")
	private Date nextEventDate;
	@JsonProperty("nextEventStartTime")
	private Date nextEventStartTime;
	@JsonProperty("outcomeDate")
	private Date outcomeDate;
	@JsonProperty("offenderProceedingId")
	private int offenderProceedingId;
	@JsonProperty("directionCode")
	private String directionCode;
	@JsonProperty("holdFlag")
	private String holdFlag;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("scheduledTripId")
	private int scheduledTripId;
	private boolean inserted;
	@JsonProperty("nbtLastName")
	private String nbtLastName;
	@JsonProperty("nbtFirstName")
	private String nbtFirstName;
	@JsonProperty("nbtOffenderIdDisplay")
	private String nbtOffenderIdDisplay;
	@JsonProperty("nbtInst")
	private String nbtInst;
	@JsonProperty("courtAgyLocId")
	private String courtAgyLocId;
	@JsonProperty("movementReasonCode")
	private String movementReasonCode;
	/**
	 * @param eventId
	 *            eventId to set
	 */
	public void setEventId(final int eventId) {
		this.eventId = eventId;
	}

	/**
	 * return theeventId
	 */
	public int getEventId() {
		return this.eventId;
	}

	/**
	 * @param caseId
	 *            caseId to set
	 */
	public void setCaseId(final int caseId) {
		this.caseId = caseId;
	}

	/**
	 * return thecaseId
	 */
	public int getCaseId() {
		return this.caseId;
	}

	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public Long getOffenderBookId() {
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
	
	public Date getNbtEventDate() {
		return nbtEventDate;
	}

	public void setNbtEventDate(Date nbtEventDate) {
		this.nbtEventDate = nbtEventDate;
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
	 * @param courtEventType
	 *            courtEventType to set
	 */
	public void setHearingType(final String hearingType) {
		this.hearingType = hearingType;
	}

	/**
	 * return thecourtEventType
	 */
	public String getHearingType() {
		return this.hearingType;
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
	 * @param parentEventId
	 *            parentEventId to set
	 */
	public void setParentEventId(final int parentEventId) {
		this.parentEventId = parentEventId;
	}

	/**
	 * return theparentEventId
	 */
	public int getParentEventId() {
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
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getcreateDateTime() {
		return this.createDateTime;
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
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
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
	 * @param nextEventRequestFlag
	 *            nextEventRequestFlag to set
	 */
	public void setNextEventRequestFlag(final String nextEventRequestFlag) {
		this.nextEventRequestFlag = nextEventRequestFlag;
	}

	/**
	 * return thenextEventRequestFlag
	 */
	public String getNextEventRequestFlag() {
		return this.nextEventRequestFlag;
	}

	/**
	 * @param orderRequestedFlag
	 *            orderRequestedFlag to set
	 */
	public void setOrderRequestedFlag(final String orderRequestedFlag) {
		this.orderRequestedFlag = orderRequestedFlag;
	}

	/**
	 * return theorderRequestedFlag
	 */
	public String getOrderRequestedFlag() {
		return this.orderRequestedFlag;
	}

	/**
	 * @param resultCode
	 *            resultCode to set
	 */
	public void setResultCode(final String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * return theresultCode
	 */
	public String getResultCode() {
		return this.resultCode;
	}

	/**
	 * @param nextEventDate
	 *            nextEventDate to set
	 */
	public void setNextEventDate(final Date nextEventDate) {
		this.nextEventDate = nextEventDate;
	}

	/**
	 * return thenextEventDate
	 */
	public Date getNextEventDate() {
		return this.nextEventDate;
	}

	/**
	 * @param nextEventStartTime
	 *            nextEventStartTime to set
	 */
	public void setNextEventStartTime(final Date nextEventStartTime) {
		this.nextEventStartTime = nextEventStartTime;
	}

	/**
	 * return thenextEventStartTime
	 */
	public Date getNextEventStartTime() {
		return this.nextEventStartTime;
	}

	/**
	 * @param outcomeDate
	 *            outcomeDate to set
	 */
	public void setOutcomeDate(final Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

	/**
	 * return theoutcomeDate
	 */
	public Date getOutcomeDate() {
		return this.outcomeDate;
	}

	/**
	 * @param offenderProceedingId
	 *            offenderProceedingId to set
	 */
	public void setOffenderProceedingId(final int offenderProceedingId) {
		this.offenderProceedingId = offenderProceedingId;
	}

	/**
	 * return theoffenderProceedingId
	 */
	public int getOffenderProceedingId() {
		return this.offenderProceedingId;
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
	 * @param holdFlag
	 *            holdFlag to set
	 */
	public void setHoldFlag(final String holdFlag) {
		this.holdFlag = holdFlag;
	}

	/**
	 * return theholdFlag
	 */
	public String getHoldFlag() {
		return this.holdFlag;
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
	public void setScheduledTripId(final int scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	/**
	 * return thescheduledTripId
	 */
	public int getScheduledTripId() {
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
	
	/**
	 * @return the nbtLastName
	 */
	public String getNbtLastName() {
		return nbtLastName;
	}

	/**
	 * @param nbtLastName
	 *            the nbtLastName to set
	 */
	public void setNbtLastName(String nbtLastName) {
		this.nbtLastName = nbtLastName;
	}

	/**
	 * @return the nbtFirstName
	 */
	public String getNbtFirstName() {
		return nbtFirstName;
	}

	/**
	 * @param nbtFirstName
	 *            the nbtFirstName to set
	 */
	public void setNbtFirstName(String nbtFirstName) {
		this.nbtFirstName = nbtFirstName;
	}

	/**
	 * @return the nbtOffenderIdDisplay
	 */
	public String getNbtOffenderIdDisplay() {
		return nbtOffenderIdDisplay;
	}

	/**
	 * @param nbtOffenderIdDisplay
	 *            the nbtOffenderIdDisplay to set
	 */
	public void setNbtOffenderIdDisplay(String nbtOffenderIdDisplay) {
		this.nbtOffenderIdDisplay = nbtOffenderIdDisplay;
	}

	/**
	 * @return the nbtInst
	 */
	public String getNbtInst() {
		return nbtInst;
	}

	/**
	 * @param nbtInst
	 *            the nbtInst to set
	 */
	public void setNbtInst(String nbtInst) {
		this.nbtInst = nbtInst;
	}

	/**
	 * @param courtAgyLocId
	 *            courtAgyLocId to set
	 */
	public void setCourtAgyLocId(final String courtAgyLocId) {
		this.courtAgyLocId = courtAgyLocId;
	}

	/**
	 * return the courtAgyLocId
	 */
	public String getCourtAgyLocId() {
		return this.courtAgyLocId;
	}

	/**
	 * @param movementReasonCode
	 *            movementReasonCode to set
	 */
	public void setMovementReasonCode(final String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	/**
	 * return the movementReasonCode
	 */
	public String getMovementReasonCode() {
		return this.movementReasonCode;
	}

}
