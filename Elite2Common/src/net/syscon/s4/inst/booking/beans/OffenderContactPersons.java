package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderContactPersons implements Serializable {
	private static final long serialVersionUID = 1L;

	private long offenderContactPersonId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("approvedVisitorFlag")
	private String approvedVisitorFlag;

	@JsonProperty("awareOfChargesFlag")
	private String awareOfChargesFlag;

	@JsonProperty("canBeContactedFlag")
	private String canBeContactedFlag;

	@JsonProperty("caseInfoNumber")
	private String caseInfoNumber;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("contactRootOffenderId")
	private BigDecimal contactRootOffenderId;

	@JsonProperty("contactType")
	private String contactType;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("emergencyContactFlag")
	private String emergencyContactFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("nextOfKinFlag")
	private String nextOfKinFlag;

	@JsonProperty("personId")
	private BigDecimal personId;

	@JsonProperty("relationshipType")
	private String relationshipType;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("age")
	private int age;
	
	@JsonProperty("visitBan")
	private String visitBan;
	
	@JsonProperty("restriction")
	private String restriction;
	
	@JsonProperty("birthDate")
	private Date birthDate;
	
	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	public OffenderContactPersons() {
	}

	public long getOffenderContactPersonId() {
		return this.offenderContactPersonId;
	}

	public void setOffenderContactPersonId(long offenderContactPersonId) {
		this.offenderContactPersonId = offenderContactPersonId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getApprovedVisitorFlag() {
		return this.approvedVisitorFlag;
	}

	public void setApprovedVisitorFlag(String approvedVisitorFlag) {
		this.approvedVisitorFlag = approvedVisitorFlag;
	}

	public String getAwareOfChargesFlag() {
		return this.awareOfChargesFlag;
	}

	public void setAwareOfChargesFlag(String awareOfChargesFlag) {
		this.awareOfChargesFlag = awareOfChargesFlag;
	}

	public String getCanBeContactedFlag() {
		return this.canBeContactedFlag;
	}

	public void setCanBeContactedFlag(String canBeContactedFlag) {
		this.canBeContactedFlag = canBeContactedFlag;
	}

	public String getCaseInfoNumber() {
		return this.caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public BigDecimal getContactRootOffenderId() {
		return this.contactRootOffenderId;
	}

	public void setContactRootOffenderId(BigDecimal contactRootOffenderId) {
		this.contactRootOffenderId = contactRootOffenderId;
	}

	public String getContactType() {
		return this.contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
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

	public String getEmergencyContactFlag() {
		return this.emergencyContactFlag;
	}

	public void setEmergencyContactFlag(String emergencyContactFlag) {
		this.emergencyContactFlag = emergencyContactFlag;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getNextOfKinFlag() {
		return this.nextOfKinFlag;
	}

	public void setNextOfKinFlag(String nextOfKinFlag) {
		this.nextOfKinFlag = nextOfKinFlag;
	}

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	public String getRelationshipType() {
		return this.relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
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
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the visitBan
	 */
	public String getVisitBan() {
		return visitBan;
	}

	/**
	 * @param visitBan the visitBan to set
	 */
	public void setVisitBan(String visitBan) {
		this.visitBan = visitBan;
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
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the livingUnitDescription
	 */
	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}

	/**
	 * @param livingUnitDescription the livingUnitDescription to set
	 */
	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
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

}
