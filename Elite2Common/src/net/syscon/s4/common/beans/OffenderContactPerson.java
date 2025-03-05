package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderContactPerson extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderContactPersonId")
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

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	/**
	 *
	 * @return
	 */
	public long getOffenderContactPersonId() {
		return offenderContactPersonId;
	}

	/**
	 *
	 * @param offenderContactPersonId
	 */
	public void setOffenderContactPersonId(long offenderContactPersonId) {
		this.offenderContactPersonId = offenderContactPersonId;
	}

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getApprovedVisitorFlag() {
		return approvedVisitorFlag;
	}

	/**
	 *
	 * @param approvedVisitorFlag
	 */
	public void setApprovedVisitorFlag(String approvedVisitorFlag) {
		this.approvedVisitorFlag = approvedVisitorFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getAwareOfChargesFlag() {
		return awareOfChargesFlag;
	}

	/**
	 *
	 * @param awareOfChargesFlag
	 */
	public void setAwareOfChargesFlag(String awareOfChargesFlag) {
		this.awareOfChargesFlag = awareOfChargesFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCanBeContactedFlag() {
		return canBeContactedFlag;
	}

	/**
	 *
	 * @param canBeContactedFlag
	 */
	public void setCanBeContactedFlag(String canBeContactedFlag) {
		this.canBeContactedFlag = canBeContactedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	/**
	 *
	 * @param caseInfoNumber
	 */
	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 *
	 * @param caseloadType
	 */
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
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
	public BigDecimal getContactRootOffenderId() {
		return contactRootOffenderId;
	}

	/**
	 *
	 * @param contactRootOffenderId
	 */
	public void setContactRootOffenderId(BigDecimal contactRootOffenderId) {
		this.contactRootOffenderId = contactRootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 *
	 * @param contactType
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
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
	public String getEmergencyContactFlag() {
		return emergencyContactFlag;
	}

	/**
	 *
	 * @param emergencyContactFlag
	 */
	public void setEmergencyContactFlag(String emergencyContactFlag) {
		this.emergencyContactFlag = emergencyContactFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	public String getNextOfKinFlag() {
		return nextOfKinFlag;
	}

	/**
	 *
	 * @param nextOfKinFlag
	 */
	public void setNextOfKinFlag(String nextOfKinFlag) {
		this.nextOfKinFlag = nextOfKinFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getPersonId() {
		return personId;
	}

	/**
	 *
	 * @param personId
	 */
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	/**
	 *
	 * @return
	 */
	public String getRelationshipType() {
		return relationshipType;
	}

	/**
	 *
	 * @param relationshipType
	 */
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
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

}