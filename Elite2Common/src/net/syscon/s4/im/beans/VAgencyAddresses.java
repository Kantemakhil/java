package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VAgencyAddresses extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("addressId")
	private BigDecimal addressId;

	@JsonProperty("addressType")
	private String addressType;

	@JsonProperty("addressTypeDesc")
	private String addressTypeDesc;

	@JsonProperty("agyLocId")
	@NotNull
	@Size(max = 6)
	private String agyLocId;

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
	@NotNull
	@Size(max = 1)
	private String primaryFlag;

	@JsonProperty("mailFlag")
	@NotNull
	@Size(max = 1)
	private String mailFlag;

	@JsonProperty("validatedFlag")
	private String validatedFlag;

	@JsonProperty("mailCareOf")
	private String mailCareOf;

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
	 * creates new VAgencyAddresses class Object
	 */
	public VAgencyAddresses() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the addressId
	 */
	public BigDecimal getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */

	public void setAddressId(final BigDecimal addressId) {
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
	 * @param addressTypeDesc
	 *            the addressTypeDesc to set
	 */

	public void setAddressTypeDesc(final String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
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
	 * @param endDate
	 *            the endDate to set
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
	 * @param activeFlag
	 *            the activeFlag to set
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
	 * @param house
	 *            the house to set
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
	 * @param street
	 *            the street to set
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
	 * @param area
	 *            the area to set
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
	 * @param country
	 *            the country to set
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
	 * @param suiteNumber
	 *            the suiteNumber to set
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
	 * @param streetNumber
	 *            the streetNumber to set
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
	 * @param streetDirection
	 *            the streetDirection to set
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
	 * @param streetDirectionDesc
	 *            the streetDirectionDesc to set
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
	 * @param streetInformation
	 *            the streetInformation to set
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
	 * @param cityCode
	 *            the cityCode to set
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
	 * @param cityName
	 *            the cityName to set
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
	 * @param provStateCode
	 *            the provStateCode to set
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
	 * @param provStateDesc
	 *            the provStateDesc to set
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
	 * @param zipPostalCode
	 *            the zipPostalCode to set
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
	 * @param countryCode
	 *            the countryCode to set
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
	 * @param commentText
	 *            the commentText to set
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
	 * @param primaryFlag
	 *            the primaryFlag to set
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
	 * @param mailFlag
	 *            the mailFlag to set
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
	 * @param validatedFlag
	 *            the validatedFlag to set
	 */
	public void setValidatedFlag(final String validatedFlag) {
		this.validatedFlag = validatedFlag;
	}

	/**
	 * @return the mailCareOf
	 */
	public String getMailCareOf() {
		return mailCareOf;
	}

	/**
	 * @param mailCareOf
	 *            the mailCareOf to set
	 */
	public void setMailCareOf(final String mailCareOf) {
		this.mailCareOf = mailCareOf;
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
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
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
	public void setCountryDbCode(final String countryDbCode) {
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
	public void setCityDbCode(final String cityDbCode) {
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
	public void setProvStateDbCode(final String provStateDbCode) {
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
	public void setStreetDirectionDbCode(final String streetDirectionDbCode) {
		this.streetDirectionDbCode = streetDirectionDbCode;
	}

	public String getAddressTypeDbCode() {
		return addressTypeDbCode;
	}

	public void setAddressTypeDbCode(final String addressTypeDbCode) {
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

}
