package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the V_CORPORATE_ADDRESSES database table.
 * 
 */
public class VCorporateAddresses implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private BigDecimal addressId;

	private BigDecimal addressSeq;

	private String addressType;

	private String addressTypeDesc;

	private String area;

	private String businessHour;

	private String cityCode;

	private String cityName;

	private String commentText;

	private String contactPersonName;

	private BigDecimal corporateId;

	private String country;

	private String countryCode;

	private Date endDate;

	private String house;

	private String mailFlag;

	private String primaryFlag;

	private String provStateCode;

	private String provStateDesc;

	private String servicesFlag;

	private String specialNeeds;

	private Date startDate;

	private String street;

	private String streetDirection;

	private String streetDirectionDesc;

	private String streetInformation;

	private String streetNumber;

	private String suiteNumber;

	private String validatedFlag;

	private String zipPostalCode;
	
	private String description;
	
	private String code;
	
	@JsonProperty("isAddressValid")
	private String isAddressValid;
	
	
	@JsonProperty("streetAddress")
	private String streetAddress;
	public VCorporateAddresses() {
		// VCorporateAddresses
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public BigDecimal getAddressId() {
		return this.addressId;
	}

	public void setAddressId(BigDecimal addressId) {
		this.addressId = addressId;
	}

	public BigDecimal getAddressSeq() {
		return this.addressSeq;
	}

	public void setAddressSeq(BigDecimal addressSeq) {
		this.addressSeq = addressSeq;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressTypeDesc() {
		return this.addressTypeDesc;
	}

	public void setAddressTypeDesc(String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBusinessHour() {
		return this.businessHour;
	}

	public void setBusinessHour(String businessHour) {
		this.businessHour = businessHour;
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

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public BigDecimal getCorporateId() {
		return this.corporateId;
	}

	public void setCorporateId(BigDecimal corporateId) {
		this.corporateId = corporateId;
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

	public String getServicesFlag() {
		return this.servicesFlag;
	}

	public void setServicesFlag(String servicesFlag) {
		this.servicesFlag = servicesFlag;
	}

	public String getSpecialNeeds() {
		return this.specialNeeds;
	}

	public void setSpecialNeeds(String specialNeeds) {
		this.specialNeeds = specialNeeds;
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

	public String getStreetDirectionDesc() {
		return this.streetDirectionDesc;
	}

	public void setStreetDirectionDesc(String streetDirectionDesc) {
		this.streetDirectionDesc = streetDirectionDesc;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	
	private String modifyUserId;

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	

}
