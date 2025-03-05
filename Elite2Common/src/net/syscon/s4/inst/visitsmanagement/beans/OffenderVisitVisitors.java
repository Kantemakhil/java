package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderVisitVisitors implements Serializable {
	private static final long serialVersionUID = 1L;

	private long offenderVisitVisitorId;

	private String assistedVisitFlag;

	private String commentText;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal eventId;

	private String eventOutcome;

	private String eventStatus;

	private String groupLeaderFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal offenderBookId;

	private String outcomeReasonCode;

	private String sealFlag;
	
	private BigDecimal offenderVisitId;
	
	private Date visitDate;
	
   private String lastName;
	
	private String firstName;
	
	private String agyLocId;
	
private String contactType;
	
	private String relationshipType;
	
	private String restriction;
	
	private String offenderIdDisplay;
	
	private BigDecimal visitorOffenderId;
	@JsonProperty("personId")
	private BigDecimal personId;
	
	private Date startTime;
	
	private Date endTime;


	public OffenderVisitVisitors() {
		// OffenderVisitVisitor
	}

	public long getOffenderVisitVisitorId() {
		return this.offenderVisitVisitorId;
	}

	public void setOffenderVisitVisitorId(long offenderVisitVisitorId) {
		this.offenderVisitVisitorId = offenderVisitVisitorId;
	}

	public String getAssistedVisitFlag() {
		return this.assistedVisitFlag;
	}

	public void setAssistedVisitFlag(String assistedVisitFlag) {
		this.assistedVisitFlag = assistedVisitFlag;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getGroupLeaderFlag() {
		return this.groupLeaderFlag;
	}

	public void setGroupLeaderFlag(String groupLeaderFlag) {
		this.groupLeaderFlag = groupLeaderFlag;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getOffenderVisitId() {
		return this.offenderVisitId;
	}

	public void setOffenderVisitId(BigDecimal offenderVisitId) {
		this.offenderVisitId = offenderVisitId;
	}

	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the contactType
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * @param contactType the contactType to set
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return the relationshipType
	 */
	public String getRelationshipType() {
		return relationshipType;
	}

	/**
	 * @param relationshipType the relationshipType to set
	 */
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	/**
	 * @return the restriction
	 */
	public String getRestriction() {
		return restriction;
	}

	/**
	 * @param restriction the restriction to set
	 */
	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the visitorOffenderId
	 */
	public BigDecimal getVisitorOffenderId() {
		return visitorOffenderId;
	}

	/**
	 * @param visitorOffenderId the visitorOffenderId to set
	 */
	public void setVisitorOffenderId(BigDecimal visitorOffenderId) {
		this.visitorOffenderId = visitorOffenderId;
	}

	/**
	 * @return the personId
	 */
	public BigDecimal getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
