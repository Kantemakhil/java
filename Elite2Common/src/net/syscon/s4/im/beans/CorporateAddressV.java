package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CorporateAddressV extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("corporateId")
	private Integer corporateId;

	@JsonProperty("corporateName")
	private String corporateName;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("contactPersonName")
	private String contactPersonName;

	@JsonProperty("suspendedDate")
	private Date suspendedDate;

	@JsonProperty("suspendedFlag")
	private String suspendedFlag;

	@JsonProperty("addressId")
	private Integer addressId;

	@JsonProperty("addressType")
	private String addressType;

	@JsonProperty("ownerClass")
	private String ownerClass;

	@JsonProperty("ownerId")
	private Integer ownerId;

	@JsonProperty("ownerCode")
	private String ownerCode;

	@JsonProperty("ownerSeq")
	private Integer ownerSeq;

	@JsonProperty("suiteNumber")
	private String suiteNumber;

	@JsonProperty("streetNumber")
	private String streetNumber;

	@JsonProperty("street")
	private String street;

	@JsonProperty("streetDirection")
	private String streetDirection;

	@JsonProperty("zipPostalCode")
	private String zipPostalCode;

	@JsonProperty("cityCode")
	private String cityCode;

	@JsonProperty("provStateCode")
	private String provStateCode;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("cityDesc")
	private String cityDesc;

	@JsonProperty("provStateDesc")
	private String provStateDesc;

	@JsonProperty("countryDesc")
	private String countryDesc;

	@JsonProperty("mailCareOf")
	private String mailCareOf;

	@JsonProperty("primaryFlag")
	private String primaryFlag;

	@JsonProperty("mailFlag")
	private String mailFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	/**
	 * Creates new CorporateAddressV class Object
	 */
	public CorporateAddressV() {
		// CorporateAddressV
	}

	/**
	 * @return the corporateId
	 */
	public Integer getCorporateId() {
		return corporateId;
	}

	/**
	 * @param corporateId
	 *            the corporateId to set
	 */
	public void setCorporateId(final Integer corporateId) {
		this.corporateId = corporateId;
	}

	/**
	 * @return the corporateName
	 */
	public String getCorporateName() {
		return corporateName;
	}

	/**
	 * @param corporateName
	 *            the corporateName to set
	 */
	public void setCorporateName(final String corporateName) {
		this.corporateName = corporateName;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the contact_PersonName
	 */
	public String getContactPersonName() {
		return contactPersonName;
	}

	/**
	 * @param contact_PersonName
	 *            the contact_PersonName to set
	 */
	public void setContactPersonName(final String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	/**
	 * @return the suspendedDate
	 */
	public Date getSuspendedDate() {
		return suspendedDate;
	}

	/**
	 * @param suspendedDate
	 *            the suspendedDate to set
	 */
	public void setSuspendedDate(final Date suspendedDate) {
		this.suspendedDate = suspendedDate;
	}

	/**
	 * @return the suspendedFlag
	 */
	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	/**
	 * @param suspendedFlag
	 *            the suspendedFlag to set
	 */
	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
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
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(final String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return the ownerClass
	 */
	public String getOwnerClass() {
		return ownerClass;
	}

	/**
	 * @param ownerClass
	 *            the ownerClass to set
	 */
	public void setOwnerClass(final String ownerClass) {
		this.ownerClass = ownerClass;
	}

	/**
	 * @return the ownerId
	 */
	public Integer getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(final Integer ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the ownerCode
	 */
	public String getOwnerCode() {
		return ownerCode;
	}

	/**
	 * @param ownerCode
	 *            the ownerCode to set
	 */
	public void setOwnerCode(final String ownerCode) {
		this.ownerCode = ownerCode;
	}

	/**
	 * @return the ownerSeq
	 */
	public Integer getOwnerSeq() {
		return ownerSeq;
	}

	/**
	 * @param ownerSeq
	 *            the ownerSeq to set
	 */
	public void setOwnerSeq(final Integer ownerSeq) {
		this.ownerSeq = ownerSeq;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the zipPostalCode
	 */
	public String getZipPostalCode() {
		return zipPostalCode;
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
	 * @return the zip_PostalCode
	 */
	public String getZip_PostalCode() {
		return zipPostalCode;
	}

	/**
	 * @param zip_PostalCode
	 *            the zip_PostalCode to set
	 */
	public void setZipPostalCode(final String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
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
	 * @return the prov_StateCode
	 */
	public String getProv_StateCode() {
		return provStateCode;
	}

	/**
	 * @param prov_StateCode
	 *            the prov_StateCode to set
	 */
	public void setProv_StateCode(final String prov_StateCode) {
		this.provStateCode = prov_StateCode;
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
	 * @return the cityDesc
	 */
	public String getCityDesc() {
		return cityDesc;
	}

	/**
	 * @param cityDesc
	 *            the cityDesc to set
	 */
	public void setCityDesc(final String cityDesc) {
		this.cityDesc = cityDesc;
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
	 * @return the countryDesc
	 */
	public String getCountryDesc() {
		return countryDesc;
	}

	/**
	 * @param countryDesc
	 *            the countryDesc to set
	 */
	public void setCountryDesc(final String countryDesc) {
		this.countryDesc = countryDesc;
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

}