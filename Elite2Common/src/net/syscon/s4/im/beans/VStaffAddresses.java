package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VStaffAddresses
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VStaffAddresses extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("addressId")
	private Integer addressId;

	@JsonProperty("addressType")
	private String addressType;

	@JsonProperty("addressTypeDesc")
	private String addressTypeDesc;

	@JsonProperty("staffId")
	private Integer staffId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

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

	@JsonProperty("streetDirectionDesc")
	private String streetDirectionDesc;

	@JsonProperty("streetInformation")
	private String streetInformation;

	@JsonProperty("cityCode")
	private String cityCode;

	@JsonProperty("cityName")
	private String cityName;

	@JsonProperty("provStateCode")
	private String provStateCode;

	@JsonProperty("provStateDesc")
	private String provStateDesc;

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

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("isAddressValid")
	private String isAddressValid;
	
	
	@JsonProperty("streetAddress")
	private String streetAddress;
	/**
	 * Creates new VStaffAddresses class Object
	 */
	public VStaffAddresses() {
		// VStaffAddresses
	}
	
	
	/**
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(final Integer addressId) {
		this.addressId = addressId;
	}
	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(final String addressType) {
		this.addressType = addressType;
	}
	/**
	 * @return the addressTypeDesc
	 */
	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}
	/**
	 * @param addressTypeDesc the addressTypeDesc to set
	 */
	public void setAddressTypeDesc(final String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}
	/**
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}
	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
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
	public void setLastName(final String lastName) {
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
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}
	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return the house
	 */
	public String getHouse() {
		return house;
	}
	/**
	 * @param house the house to set
	 */
	public void setHouse(final String house) {
		this.house = house;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(final String street) {
		this.street = street;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(final String area) {
		this.area = area;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}
	/**
	 * @return the suiteNumber
	 */
	public String getSuiteNumber() {
		return suiteNumber;
	}
	/**
	 * @param suiteNumber the suiteNumber to set
	 */
	public void setSuiteNumber(final String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}
	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(final String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return the streetDirection
	 */
	public String getStreetDirection() {
		return streetDirection;
	}
	/**
	 * @param streetDirection the streetDirection to set
	 */
	public void setStreetDirection(final String streetDirection) {
		this.streetDirection = streetDirection;
	}
	/**
	 * @return the streetDirectionDesc
	 */
	public String getStreetDirectionDesc() {
		return streetDirectionDesc;
	}
	/**
	 * @param streetDirectionDesc the streetDirectionDesc to set
	 */
	public void setStreetDirectionDesc(final String streetDirectionDesc) {
		this.streetDirectionDesc = streetDirectionDesc;
	}
	/**
	 * @return the streetInformation
	 */
	public String getStreetInformation() {
		return streetInformation;
	}
	/**
	 * @param streetInformation the streetInformation to set
	 */
	public void setStreetInformation(final String streetInformation) {
		this.streetInformation = streetInformation;
	}
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(final String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the provStateCode
	 */
	public String getProvStateCode() {
		return provStateCode;
	}
	/**
	 * @param provStateCode the provStateCode to set
	 */
	public void setProvStateCode(final String provStateCode) {
		this.provStateCode = provStateCode;
	}
	/**
	 * @return the provStateDesc
	 */
	public String getProvStateDesc() {
		return provStateDesc;
	}
	/**
	 * @param provStateDesc the provStateDesc to set
	 */
	public void setProvStateDesc(final String provStateDesc) {
		this.provStateDesc = provStateDesc;
	}
	/**
	 * @return the zipPostalCode
	 */
	public String getZipPostalCode() {
		return zipPostalCode;
	}
	/**
	 * @param zipPostalCode the zipPostalCode to set
	 */
	public void setZipPostalCode(final String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}
	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}
	/**
	 * @return the primaryFlag
	 */
	public String getPrimaryFlag() {
		return primaryFlag;
	}
	/**
	 * @param primaryFlag the primaryFlag to set
	 */
	public void setPrimaryFlag(final String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	/**
	 * @return the mailFlag
	 */
	public String getMailFlag() {
		return mailFlag;
	}
	/**
	 * @param mailFlag the mailFlag to set
	 */
	public void setMailFlag(final String mailFlag) {
		this.mailFlag = mailFlag;
	}
	/**
	 * @return the validatedFlag
	 */
	public String getValidatedFlag() {
		return validatedFlag;
	}
	/**
	 * @param validatedFlag the validatedFlag to set
	 */
	public void setValidatedFlag(final String validatedFlag) {
		this.validatedFlag = validatedFlag;
	}
	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}
	/**
	 * @param inserted the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
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
