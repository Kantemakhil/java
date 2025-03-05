package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VPersonAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private BigDecimal addressId;

	private String addressType;

	private String addressTypeDesc;

	private String area;

	private String cityCode;

	private String cityName;

	private String commentText;

	private String country;

	private String countryCode;

	private Date endDate;

	private String house;

	private String mailFlag;

	private BigDecimal personId;

	private String primaryFlag;

	private String provStateCode;

	private String provStateDesc;

	private Date startDate;

	private String street;

	private String streetDirection;

	private String streetInformation;

	private String streetNumber;

	private String suiteNumber;

	private String validatedFlag;

	private String zipPostalCode;
	
	@JsonProperty("isAddressValid")
	private String isAddressValid;
	
	
	@JsonProperty("streetAddress")
	private String streetAddress;

	public VPersonAddress() {
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
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

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
