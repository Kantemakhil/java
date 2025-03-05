package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCompactAgreement extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("address1")
	private String address1;

	@JsonProperty("address2")
	private String address2;

	@JsonProperty("agencyName")
	private String agencyName;

	@JsonProperty("agreementSeq")
	private BigDecimal agreementSeq;

	@JsonProperty("agreementType")
	private String agreementType;

	@JsonProperty("city")
	private String city;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("contactName")
	private String contactName;

	@JsonProperty("country")
	private String country;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("directionCode")
	private String directionCode;

	@JsonProperty("jurisdictionAgyLocId")
	private String jurisdictionAgyLocId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("movementDate")
	private Date movementDate;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("phoneArea")
	private String phoneArea;

	@JsonProperty("phoneExt")
	private String phoneExt;

	@JsonProperty("provStateCode")
	private String provStateCode;

	@JsonProperty("requestDate")
	private Date requestDate;

	@JsonProperty("returnDate")
	private Date returnDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("zipCode")
	private String zipCode;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	/**
	 *
	 * @return
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 *
	 * @param address1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 *
	 * @return
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 *
	 * @param address2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 *
	 * @return
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 *
	 * @param agencyName
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAgreementSeq() {
		return agreementSeq;
	}

	/**
	 *
	 * @param agreementSeq
	 */
	public void setAgreementSeq(BigDecimal agreementSeq) {
		this.agreementSeq = agreementSeq;
	}

	/**
	 *
	 * @return
	 */
	public String getAgreementType() {
		return agreementType;
	}

	/**
	 *
	 * @param agreementType
	 */
	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	/**
	 *
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 *
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
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
	public String getContactName() {
		return contactName;
	}

	/**
	 *
	 * @param contactName
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 *
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 *
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
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
	public String getJurisdictionAgyLocId() {
		return jurisdictionAgyLocId;
	}

	/**
	 *
	 * @param jurisdictionAgyLocId
	 */
	public void setJurisdictionAgyLocId(String jurisdictionAgyLocId) {
		this.jurisdictionAgyLocId = jurisdictionAgyLocId;
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
	public Date getMovementDate() {
		return movementDate;
	}

	/**
	 *
	 * @param movementDate
	 */
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	/**
	 *
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 *
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 *
	 * @return
	 */
	public String getPhoneArea() {
		return phoneArea;
	}

	/**
	 *
	 * @param phoneArea
	 */
	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}

	/**
	 *
	 * @return
	 */
	public String getPhoneExt() {
		return phoneExt;
	}

	/**
	 *
	 * @param phoneExt
	 */
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
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
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 *
	 * @param requestDate
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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
	public String getZipCode() {
		return zipCode;
	}

	/**
	 *
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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