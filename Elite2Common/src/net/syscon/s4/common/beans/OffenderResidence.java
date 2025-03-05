package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the OFFENDER_RESIDENCES database table.
 * 
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public class OffenderResidence implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address1;

	private String address2;

	private String addressType;

	private String caseloadType;

	private String city;

	private String cityText;

	private String commentText;

	private String contactName;

	private String countryCode;

	private String createAgyLocId;

	private Date createDate;

	private Date createDatetime;

	private String createUserId;

	private String currentAddressFlag;

	private Date mailingAddFromDate;

	private Date mailingAddToDate;

	private String mailingAddressFlag;

	private Date mailingInDate;

	private Date mailingOutDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private Date movedInDate;

	private Date movedOutDate;

	private String partialMailingInFlag;

	private String partialMailingOutFlag;

	private String partialMovedInFlag;

	private String partialMovedOutFlag;

	private String provStateCode;

	private String registrationFlag;

	private String residenceComment;

	private BigDecimal rootOffenderId;

	private String sealFlag;

	private String updatedFlag;

	private String verifiedFlag;

	private String zipPostalCode;

	private Long offenderBookId;

	private Long residenceSeq;
	
	private Date movedOutTime;

	
	private Date eventDate;
	
	private Date eventTime;
	
	
	public OffenderResidence() {
		// OffenderResidence
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getResidenceSeq() {
		return residenceSeq;
	}

	public void setResidenceSeq(final Long residenceSeq) {
		this.residenceSeq = residenceSeq;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(final String addressType) {
		this.addressType = addressType;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getCityText() {
		return this.cityText;
	}

	public void setCityText(final String cityText) {
		this.cityText = cityText;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(final String contactName) {
		this.contactName = contactName;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCreateAgyLocId() {
		return this.createAgyLocId;
	}

	public void setCreateAgyLocId(final String createAgyLocId) {
		this.createAgyLocId = createAgyLocId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCurrentAddressFlag() {
		return this.currentAddressFlag;
	}

	public void setCurrentAddressFlag(final String currentAddressFlag) {
		this.currentAddressFlag = currentAddressFlag;
	}

	public Date getMailingAddFromDate() {
		return this.mailingAddFromDate;
	}

	public void setMailingAddFromDate(final Date mailingAddFromDate) {
		this.mailingAddFromDate = mailingAddFromDate;
	}

	public Date getMailingAddToDate() {
		return this.mailingAddToDate;
	}

	public void setMailingAddToDate(final Date mailingAddToDate) {
		this.mailingAddToDate = mailingAddToDate;
	}

	public String getMailingAddressFlag() {
		return this.mailingAddressFlag;
	}

	public void setMailingAddressFlag(final String mailingAddressFlag) {
		this.mailingAddressFlag = mailingAddressFlag;
	}

	public Date getMailingInDate() {
		return this.mailingInDate;
	}

	public void setMailingInDate(final Date mailingInDate) {
		this.mailingInDate = mailingInDate;
	}

	public Date getMailingOutDate() {
		return this.mailingOutDate;
	}

	public void setMailingOutDate(final Date mailingOutDate) {
		this.mailingOutDate = mailingOutDate;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getMovedInDate() {
		return this.movedInDate;
	}

	public void setMovedInDate(final Date movedInDate) {
		this.movedInDate = movedInDate;
	}

	public Date getMovedOutDate() {
		return this.movedOutDate;
	}

	public Date setMovedOutDate(final Date movedOutDate) {
		return this.movedOutDate = movedOutDate;
	}

	public String getPartialMailingInFlag() {
		return this.partialMailingInFlag;
	}

	public void setPartialMailingInFlag(final String partialMailingInFlag) {
		this.partialMailingInFlag = partialMailingInFlag;
	}

	public String getPartialMailingOutFlag() {
		return this.partialMailingOutFlag;
	}

	public void setPartialMailingOutFlag(final String partialMailingOutFlag) {
		this.partialMailingOutFlag = partialMailingOutFlag;
	}

	public String getPartialMovedInFlag() {
		return this.partialMovedInFlag;
	}

	public void setPartialMovedInFlag(final String partialMovedInFlag) {
		this.partialMovedInFlag = partialMovedInFlag;
	}

	public String getPartialMovedOutFlag() {
		return this.partialMovedOutFlag;
	}

	public void setPartialMovedOutFlag(final String partialMovedOutFlag) {
		this.partialMovedOutFlag = partialMovedOutFlag;
	}

	public String getProvStateCode() {
		return this.provStateCode;
	}

	public void setProvStateCode(final String provStateCode) {
		this.provStateCode = provStateCode;
	}

	public String getRegistrationFlag() {
		return this.registrationFlag;
	}

	public void setRegistrationFlag(final String registrationFlag) {
		this.registrationFlag = registrationFlag;
	}

	public String getResidenceComment() {
		return this.residenceComment;
	}

	public void setResidenceComment(final String residenceComment) {
		this.residenceComment = residenceComment;
	}

	public BigDecimal getRootOffenderId() {
		return this.rootOffenderId;
	}

	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getUpdatedFlag() {
		return this.updatedFlag;
	}

	public void setUpdatedFlag(final String updatedFlag) {
		this.updatedFlag = updatedFlag;
	}

	public String getVerifiedFlag() {
		return this.verifiedFlag;
	}

	public void setVerifiedFlag(final String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	public String getZipPostalCode() {
		return this.zipPostalCode;
	}

	public void setZipPostalCode(final String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	public Date getMovedOutTime() {
		return movedOutTime;
	}

	public void setMovedOutTime(final Date movedOutTime) {
		this.movedOutTime = movedOutTime;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

}
