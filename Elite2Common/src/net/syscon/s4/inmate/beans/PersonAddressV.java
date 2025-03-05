package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class PersonAddressV
 *
 */
public class PersonAddressV extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("addressId")
	private BigDecimal addressId;
	
	@JsonProperty("personId")
	private BigDecimal personId;
	
	@JsonProperty("mailFlag")
	private String mailFlag;
	
	@JsonProperty("primaryFlag")
	private String primaryFlag;
	
	@JsonProperty("street")
	private String street;

	@JsonProperty("streetDirection")
	private String streetDirection;

	@JsonProperty("streetInformation")
	private String streetInformation;

	@JsonProperty("streetNumber")
	private String streetNumber;

	@JsonProperty("suiteNumber")
	private String suiteNumber;

	@JsonProperty("mailCareOf")
	private String mailCareOf;

	@JsonProperty("zipPostalCode")
	private String zipPostalCode;
	
	@JsonProperty("ownerClass")
	private String ownerClass;

	@JsonProperty("cityCode")
	private String cityCode;

	@JsonProperty("cityDesc")
	private String cityDesc;

	@JsonProperty("stateDesc")
	private String stateDesc;

	@JsonProperty("countryDesc")
	private String countryDesc;

	@JsonProperty("countryCode")
	private String countryCode;
	
	@JsonProperty("provStateCode")
	private String provStateCode;
	
	@JsonProperty("ownerId")
	private String ownerId;

	@JsonProperty("ownerCode")
	private String ownerCode;
	
	@JsonProperty("ownerSeq")
	private String ownerSeq;
	
	public PersonAddressV( ){
		//PersonAddressV
	}

	/**
	 * @return the addressId
	 */
	public BigDecimal getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(final BigDecimal addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the personId
	 */
	public BigDecimal getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(final BigDecimal personId) {
		this.personId = personId;
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
	 * @return the mailCareOf
	 */
	public String getMailCareOf() {
		return mailCareOf;
	}

	/**
	 * @param mailCareOf the mailCareOf to set
	 */
	public void setMailCareOf(final String mailCareOf) {
		this.mailCareOf = mailCareOf;
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
	 * @return the ownerClass
	 */
	public String getOwnerClass() {
		return ownerClass;
	}

	/**
	 * @param ownerClass the ownerClass to set
	 */
	public void setOwnerClass(final String ownerClass) {
		this.ownerClass = ownerClass;
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
	 * @return the cityDesc
	 */
	public String getCityDesc() {
		return cityDesc;
	}

	/**
	 * @param cityDesc the cityDesc to set
	 */
	public void setCityDesc(final String cityDesc) {
		this.cityDesc = cityDesc;
	}

	/**
	 * @return the stateDesc
	 */
	public String getStateDesc() {
		return stateDesc;
	}

	/**
	 * @param stateDesc the stateDesc to set
	 */
	public void setStateDesc(final String stateDesc) {
		this.stateDesc = stateDesc;
	}

	/**
	 * @return the countryDesc
	 */
	public String getCountryDesc() {
		return countryDesc;
	}

	/**
	 * @param countryDesc the countryDesc to set
	 */
	public void setCountryDesc(final String countryDesc) {
		this.countryDesc = countryDesc;
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
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(final String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the ownerCode
	 */
	public String getOwnerCode() {
		return ownerCode;
	}

	/**
	 * @param ownerCode the ownerCode to set
	 */
	public void setOwnerCode(final String ownerCode) {
		this.ownerCode = ownerCode;
	}

	/**
	 * @return the ownerSeq
	 */
	public String getOwnerSeq() {
		return ownerSeq;
	}

	/**
	 * @param ownerSeq the ownerSeq to set
	 */
	public void setOwnerSeq(final String ownerSeq) {
		this.ownerSeq = ownerSeq;
	}
	
	
}
