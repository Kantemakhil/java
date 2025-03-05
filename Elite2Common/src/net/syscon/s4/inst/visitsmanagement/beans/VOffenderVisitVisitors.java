package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VOffenderVisitVisitors implements Serializable {
	private static final long serialVersionUID = 1L;

	private String commentText;

	private BigDecimal eventId;

	private String eventOutcome;

	private String eventStatus;

	private String groupLeaderFlag;

	private BigDecimal offenderBookId;

	private BigDecimal offenderVisitId;

	private String offenderVisitOrderId;

	private BigDecimal offenderVisitVisitorId;

	private String outcomeReasonCode;

	private BigDecimal personId;

	private BigDecimal visitOffenderBookId;
	
	private Date visitDate;
	
	private String lastName;
	
	private String firstName;
	
	private String contactType;
	
	private String relationshipType;
	
	private BigDecimal age;
	
	private String restriction;
	
	private String globalRestriction;
	
	private Date startTime;
	
	private Date endTime;
    private String contactTypeDesc;
	
	private String relationshipTypeDesc;
	
	private String userName;
	
	private Date createDatetime;
	private Date modifyDatetime;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public VOffenderVisitVisitors() {
	// VOffenderVisitVisitors
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderVisitId() {
		return this.offenderVisitId;
	}

	public void setOffenderVisitId(BigDecimal offenderVisitId) {
		this.offenderVisitId = offenderVisitId;
	}

	public String getOffenderVisitOrderId() {
		return this.offenderVisitOrderId;
	}

	public void setOffenderVisitOrderId(String offenderVisitOrderId) {
		this.offenderVisitOrderId = offenderVisitOrderId;
	}

	public BigDecimal getOffenderVisitVisitorId() {
		return this.offenderVisitVisitorId;
	}

	public void setOffenderVisitVisitorId(BigDecimal offenderVisitVisitorId) {
		this.offenderVisitVisitorId = offenderVisitVisitorId;
	}

	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	public BigDecimal getVisitOffenderBookId() {
		return this.visitOffenderBookId;
	}

	public void setVisitOffenderBookId(BigDecimal visitOffenderBookId) {
		this.visitOffenderBookId = visitOffenderBookId;
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
	 * @return the age
	 */
	public BigDecimal getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(BigDecimal age) {
		this.age = age;
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
	 * @return the globalRestriction
	 */
	public String getGlobalRestriction() {
		return globalRestriction;
	}

	/**
	 * @param globalRestriction the globalRestriction to set
	 */
	public void setGlobalRestriction(String globalRestriction) {
		this.globalRestriction = globalRestriction;
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

	public String getContactTypeDesc() {
		return contactTypeDesc;
	}

	public void setContactTypeDesc(String contactTypeDesc) {
		this.contactTypeDesc = contactTypeDesc;
	}

	public String getRelationshipTypeDesc() {
		return relationshipTypeDesc;
	}

	public void setRelationshipTypeDesc(String relationshipTypeDesc) {
		this.relationshipTypeDesc = relationshipTypeDesc;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	
	private String modifyUserId;


	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	

}
