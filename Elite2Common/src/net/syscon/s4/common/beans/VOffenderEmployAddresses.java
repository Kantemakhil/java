package net.syscon.s4.common.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VOffenderEmployAddresses extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("addressId")
	private Integer addressId;

	@JsonProperty("addressType")
	private String addressType;

	@JsonProperty("addressTypeDesc")
	private String addressTypeDesc;

	@JsonProperty("employmentSeq")
	private Integer employmentSeq;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("house")
	private String house;

	@JsonProperty("street")
	private String street;

	@JsonProperty("area")
	private String area;

	@JsonProperty("country")
	private String country;

	@JsonProperty("suiteNumber")
	private String suiteNumber;

	@JsonProperty("streetNumber")
	private String streetNumber;

	@JsonProperty("streetDirection")
	private String streetDirection;

	@JsonProperty("cityCode")
	private String cityCode;

	@JsonProperty("provStateCode")
	private String provStateCode;

	@JsonProperty("zipPostalCode")
	private String zipPostalCode;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("primaryFlag")
	private String primaryFlag;

	@JsonProperty("mailFlag")
	private String mailFlag;

	@JsonProperty("validatedFlag")
	private String validatedFlag;

	@JsonProperty("streetInformation")
	private String streetInformation;

	@JsonProperty("cityName")
	private String cityName;

	@JsonProperty("provStateDesc")
	private String provStateDesc;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("countryDbCode")
	private String countryDbCode;

	@JsonProperty("cityDbCode")
	private String cityDbCode;

	@JsonProperty("provStateDbCode")
	private String provStateDbCode;

	@JsonProperty("streetDirectionDbCode")
	private String streetDirectionDbCode;

	@JsonProperty("addressTypeDbCode")
	private String addressTypeDbCode;

	
	@JsonProperty("isAddressValid")
	private String isAddressValid;
	
	
	@JsonProperty("streetAddress")
	private String streetAddress;
	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the aaddressId
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * @param aaddressId
	 *            the aaddressId to set
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return the addressTypeDesc
	 */
	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}

	/**
	 * @param addressTypeDesc
	 *            the addressTypeDesc to set
	 */
	public void setAddressTypeDesc(String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}

	/**
	 * @return the employmentSeq
	 */
	public Integer getEmploymentSeq() {
		return employmentSeq;
	}

	/**
	 * @param employmentSeq
	 *            the employmentSeq to set
	 */
	public void setEmploymentSeq(Integer employmentSeq) {
		this.employmentSeq = employmentSeq;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the avtiveFlag
	 */
	

	/**
	 * @return the house
	 */
	public String getHouse() {
		return house;
	}

	/**
	 * @param house
	 *            the house to set
	 */
	public void setHouse(String house) {
		this.house = house;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the suiteNumber
	 */
	public String getSuiteNumber() {
		return suiteNumber;
	}

	/**
	 * @param suiteNumber
	 *            the suiteNumber to set
	 */
	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber
	 *            the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the streetDirection
	 */
	public String getStreetDirection() {
		return streetDirection;
	}

	/**
	 * @param streetDirection
	 *            the streetDirection to set
	 */
	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the provStateCode
	 */
	public String getProvStateCode() {
		return provStateCode;
	}

	/**
	 * @param provStateCode
	 *            the provStateCode to set
	 */
	public void setProvStateCode(String provStateCode) {
		this.provStateCode = provStateCode;
	}

	/**
	 * @return the zipPostalCode
	 */
	public String getZipPostalCode() {
		return zipPostalCode;
	}

	/**
	 * @param zipPostalCode
	 *            the zipPostalCode to set
	 */
	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the primaryFlag
	 */
	public String getPrimaryFlag() {
		return primaryFlag;
	}

	/**
	 * @param primaryFlag
	 *            the primaryFlag to set
	 */
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	/**
	 * @return the mailFlag
	 */
	public String getMailFlag() {
		return mailFlag;
	}

	/**
	 * @param mailFlag
	 *            the mailFlag to set
	 */
	public void setMailFlag(String mailFlag) {
		this.mailFlag = mailFlag;
	}

	/**
	 * @return the validatedFlag
	 */
	public String getValidatedFlag() {
		return validatedFlag;
	}

	/**
	 * @param validatedFlag
	 *            the validatedFlag to set
	 */
	public void setValidatedFlag(String validatedFlag) {
		this.validatedFlag = validatedFlag;
	}

	/**
	 * @return the streetInformation
	 */
	public String getStreetInformation() {
		return streetInformation;
	}

	/**
	 * @param streetInformation
	 *            the streetInformation to set
	 */
	public void setStreetInformation(String streetInformation) {
		this.streetInformation = streetInformation;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the provStateDesc
	 */
	public String getProvStateDesc() {
		return provStateDesc;
	}

	/**
	 * @param provStateDesc
	 *            the provStateDesc to set
	 */
	public void setProvStateDesc(String provStateDesc) {
		this.provStateDesc = provStateDesc;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the countryDbCode
	 */
	public String getCountryDbCode() {
		return countryDbCode;
	}

	/**
	 * @param countryDbCode
	 *            the countryDbCode to set
	 */
	public void setCountryDbCode(String countryDbCode) {
		this.countryDbCode = countryDbCode;
	}

	/**
	 * @return the cityDbCode
	 */
	public String getCityDbCode() {
		return cityDbCode;
	}

	/**
	 * @param cityDbCode
	 *            the cityDbCode to set
	 */
	public void setCityDbCode(String cityDbCode) {
		this.cityDbCode = cityDbCode;
	}

	/**
	 * @return the provStateDbCode
	 */
	public String getProvStateDbCode() {
		return provStateDbCode;
	}

	/**
	 * @param provStateDbCode
	 *            the provStateDbCode to set
	 */
	public void setProvStateDbCode(String provStateDbCode) {
		this.provStateDbCode = provStateDbCode;
	}

	/**
	 * @return the streetDirectionDbCode
	 */
	public String getStreetDirectionDbCode() {
		return streetDirectionDbCode;
	}

	/**
	 * @param streetDirectionDbCode
	 *            the streetDirectionDbCode to set
	 */
	public void setStreetDirectionDbCode(String streetDirectionDbCode) {
		this.streetDirectionDbCode = streetDirectionDbCode;
	}

	public String getAddressTypeDbCode() {
		return addressTypeDbCode;
	}

	public void setAddressTypeDbCode(String addressTypeDbCode) {
		this.addressTypeDbCode = addressTypeDbCode;
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

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

}