package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class VAddressUsages
 * 
 */
public class VAddressUsages implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String addressActiveFlag;

	private BigDecimal addressId;

	private String addressType;

	private String addressUsage;

	private String area;

	private BigDecimal capacity;

	private String cityCode;

	private String cityName;

	private String commentText;

	private String country;

	private String countryCode;

	private Date createDatetime;

	private String createUserId;

	private Date endDate;

	private String fullAddress;

	private String house;

	private String mailFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private String ownerClass;

	private String ownerCode;

	private BigDecimal ownerId;

	private BigDecimal ownerSeq;

	private String primaryFlag;

	private String provStateCode;

	private String provStateDesc;

	private Date startDate;

	private String street;

	private String streetDirection;

	private String streetInformation;

	private String streetNumber;

	private String suiteNumber;

	private String usageActiveFlag;

	private String validatedFlag;

	private String zipPostalCode;
	
	private String description;
	
	@JsonProperty("isAddressValid")
	private String isAddressValid;
	
	
	@JsonProperty("streetAddress")
	private String streetAddress;

	public VAddressUsages() {
		// VAddressUsages
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAddressActiveFlag() {
		return this.addressActiveFlag;
	}

	public void setAddressActiveFlag(String addressActiveFlag) {
		this.addressActiveFlag = addressActiveFlag;
	}

	public BigDecimal getAddressId() {
		return this.addressId;
	}

	public void setAddressId(BigDecimal addressId) {
		this.addressId = addressId;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressUsage() {
		return this.addressUsage;
	}

	public void setAddressUsage(String addressUsage) {
		this.addressUsage = addressUsage;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public BigDecimal getCapacity() {
		return this.capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFullAddress() {
		return this.fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getHouse() {
		return this.house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getMailFlag() {
		return this.mailFlag;
	}

	public void setMailFlag(String mailFlag) {
		this.mailFlag = mailFlag;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getOwnerClass() {
		return this.ownerClass;
	}

	public void setOwnerClass(String ownerClass) {
		this.ownerClass = ownerClass;
	}

	public String getOwnerCode() {
		return this.ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public BigDecimal getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	public BigDecimal getOwnerSeq() {
		return this.ownerSeq;
	}

	public void setOwnerSeq(BigDecimal ownerSeq) {
		this.ownerSeq = ownerSeq;
	}

	public String getPrimaryFlag() {
		return this.primaryFlag;
	}

	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public String getProvStateCode() {
		return this.provStateCode;
	}

	public void setProvStateCode(String provStateCode) {
		this.provStateCode = provStateCode;
	}

	public String getProvStateDesc() {
		return this.provStateDesc;
	}

	public void setProvStateDesc(String provStateDesc) {
		this.provStateDesc = provStateDesc;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetDirection() {
		return this.streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getStreetInformation() {
		return this.streetInformation;
	}

	public void setStreetInformation(String streetInformation) {
		this.streetInformation = streetInformation;
	}

	public String getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getSuiteNumber() {
		return this.suiteNumber;
	}

	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	public String getUsageActiveFlag() {
		return this.usageActiveFlag;
	}

	public void setUsageActiveFlag(String usageActiveFlag) {
		this.usageActiveFlag = usageActiveFlag;
	}

	public String getValidatedFlag() {
		return this.validatedFlag;
	}

	public void setValidatedFlag(String validatedFlag) {
		this.validatedFlag = validatedFlag;
	}

	public String getZipPostalCode() {
		return this.zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsAddressValid() {
		return isAddressValid;
	}

	public void setIsAddressValid(String isAddressValid) {
		this.isAddressValid = isAddressValid;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

}
