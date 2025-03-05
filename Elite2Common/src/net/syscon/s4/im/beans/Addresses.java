package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class Addresses
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Addresses extends BaseModel implements Serializable {

	private static final Long serialVersionUID = 1L;

	@JsonProperty("addressId")
	@NotNull
	private Long addressId;

	@JsonProperty("addressType")
	@Size(max = 12)
	private String addressType;

	@JsonProperty("businessHour")
	@Size(max = 60)
	private String businessHour;

	@JsonProperty("capacity")
	private BigDecimal capacity;

	@JsonProperty("addressTypeDbCode")
	private String addressTypeDbCode;

	@JsonProperty("cityCode")
	@Size(max = 12)
	private String cityCode;

	@JsonProperty("cityName")
	@Size(max = 40)
	private String cityName;

	@JsonProperty("commentText")
	@Size(max = 240)
	private String commentText;

	@JsonProperty("contactPersonName")
	@Size(max = 40)
	private String contactPersonName;

	@JsonProperty("countryCode")
	@Size(max = 12)
	private String countryCode;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("mailCareOf")
	@Size(max = 40)
	private String mailCareOf;

	@JsonProperty("mailFlag")
	@NotNull
	@Size(max = 1)
	private String mailFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("noFixedAddressFlag")
	@Size(max = 1)
	private String noFixedAddressFlag;

	@JsonProperty("ownerClass")
	@NotNull
	@Size(max = 12)
	private String ownerClass;

	@JsonProperty("ownerCode")
	@Size(max = 12)
	private String ownerCode;

	@JsonProperty("ownerId")
	private BigDecimal ownerId;

	@JsonProperty("ownerSeq")
	private BigDecimal ownerSeq;

	@JsonProperty("primaryFlag")
	@NotNull
	@Size(max = 1)
	private String primaryFlag;

	@JsonProperty("provStateCode")
	@Size(max = 12)
	private String provStateCode;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("servicesFlag")
	@Size(max = 1)
	private String servicesFlag;

	@JsonProperty("specialNeedsCode")
	@Size(max = 12)
	private String specialNeedsCode;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("street")
	@Size(max = 160)
	private String street;

	@JsonProperty("streetDirection")
	@Size(max = 12)
	private String streetDirection;

	@JsonProperty("streetNumber")
	@Size(max = 12)
	private String streetNumber;

	@JsonProperty("suiteNumber")
	@Size(max = 30)
	private String suiteNumber;

	@JsonProperty("validatedPafFlag")
	@Size(max = 1)
	private String validatedPafFlag;

	@JsonProperty("zipPostalCode")
	@Size(max = 12)
	private String zipPostalCode;
	
	
	@JsonProperty("longitude")
	private BigDecimal longitude;
	
	
	@JsonProperty("latitude")
	private BigDecimal latitude;
	
	@JsonProperty("meshBlock")
	private BigDecimal meshBlock;
	
	@JsonProperty("isAddressValid")
	private String isAddressValid;
	
	
	@JsonProperty("streetAddress")
	private String streetAddress;

	@JsonProperty("length")
	private int length;

	/**
	 *
	 * @return
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 *
	 * @param addressId
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 *
	 * @return
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 *
	 * @param addressType
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 *
	 * @return
	 */
	public String getBusinessHour() {
		return businessHour;
	}

	/**
	 *
	 * @param businessHour
	 */
	public void setBusinessHour(String businessHour) {
		this.businessHour = businessHour;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCapacity() {
		return capacity;
	}

	/**
	 *
	 * @param capacity
	 */
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	/**
	 *
	 * @return
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 *
	 * @param cityCode
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 *
	 * @return
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 *
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	public String getContactPersonName() {
		return contactPersonName;
	}

	/**
	 *
	 * @param contactPersonName
	 */
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	/**
	 *
	 * @return
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 *
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	public Date getEndDate() {
		return endDate;
	}

	/**
	 *
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 *
	 * @return
	 */
	public String getMailCareOf() {
		return mailCareOf;
	}

	/**
	 *
	 * @param mailCareOf
	 */
	public void setMailCareOf(String mailCareOf) {
		this.mailCareOf = mailCareOf;
	}

	/**
	 *
	 * @return
	 */
	public String getMailFlag() {
		return mailFlag;
	}

	/**
	 *
	 * @param mailFlag
	 */
	public void setMailFlag(String mailFlag) {
		this.mailFlag = mailFlag;
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
	public String getNoFixedAddressFlag() {
		return noFixedAddressFlag;
	}

	/**
	 *
	 * @param noFixedAddressFlag
	 */
	public void setNoFixedAddressFlag(String noFixedAddressFlag) {
		this.noFixedAddressFlag = noFixedAddressFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getOwnerClass() {
		return ownerClass;
	}

	/**
	 *
	 * @param ownerClass
	 */
	public void setOwnerClass(String ownerClass) {
		this.ownerClass = ownerClass;
	}

	/**
	 *
	 * @return
	 */
	public String getOwnerCode() {
		return ownerCode;
	}

	/**
	 *
	 * @param ownerCode
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOwnerId() {
		return ownerId;
	}

	/**
	 *
	 * @param ownerId
	 */
	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOwnerSeq() {
		return ownerSeq;
	}

	/**
	 *
	 * @param ownerSeq
	 */
	public void setOwnerSeq(BigDecimal ownerSeq) {
		this.ownerSeq = ownerSeq;
	}

	/**
	 *
	 * @return
	 */
	public String getPrimaryFlag() {
		return primaryFlag;
	}

	/**
	 *
	 * @param primaryFlag
	 */
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
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
	public String getServicesFlag() {
		return servicesFlag;
	}

	/**
	 *
	 * @param servicesFlag
	 */
	public void setServicesFlag(String servicesFlag) {
		this.servicesFlag = servicesFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getSpecialNeedsCode() {
		return specialNeedsCode;
	}

	/**
	 *
	 * @param specialNeedsCode
	 */
	public void setSpecialNeedsCode(String specialNeedsCode) {
		this.specialNeedsCode = specialNeedsCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 *
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 *
	 * @return
	 */
	public String getStreet() {
		return street;
	}

	/**
	 *
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 *
	 * @return
	 */
	public String getStreetDirection() {
		return streetDirection;
	}

	/**
	 *
	 * @param streetDirection
	 */
	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	/**
	 *
	 * @return
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 *
	 * @param streetNumber
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getSuiteNumber() {
		return suiteNumber;
	}

	/**
	 *
	 * @param suiteNumber
	 */
	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getValidatedPafFlag() {
		return validatedPafFlag;
	}

	/**
	 *
	 * @param validatedPafFlag
	 */
	public void setValidatedPafFlag(String validatedPafFlag) {
		this.validatedPafFlag = validatedPafFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getZipPostalCode() {
		return zipPostalCode;
	}

	/**
	 *
	 * @param zipPostalCode
	 */
	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	/**
	 *
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 *
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	public String getAddressTypeDbCode() {
		return addressTypeDbCode;
	}

	public void setAddressTypeDbCode(String addressTypeDbCode) {
		this.addressTypeDbCode = addressTypeDbCode;
	}

}