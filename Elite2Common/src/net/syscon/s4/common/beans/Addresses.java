package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.address.AddressDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Addresses extends BaseModel  implements Serializable{
	private static final long serialVersionUID = 1L;

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

	@JsonProperty("length")
	private int length;

	@JsonProperty("countryDbCode")
	private String countryDbCode;

	@JsonProperty("cityDbCode")
	private String cityDbCode;

	@JsonProperty("provStateDbCode")
	private String provStateDbCode;

	@JsonProperty("streetDirectionDbCode")
	private String streetDirectionDbCode;
	
	private BigDecimal offenderBookId;
	
	private BigDecimal serviceAddressId;
	
	private String facility;
	
	@JsonProperty("jnOperation")
	private String jnOperation;

	@JsonProperty("jnOracleUser")
	private String jnOracleUser;
	
	@JsonProperty("jnDatetime")
	private Date jnDatetime;
	
	@JsonProperty("jnNotes")
	private String jnNotes;
	
	@JsonProperty("jnAppln")
	private String jnAppln;
	
	@JsonProperty("jnSession")
	private BigDecimal jnSession;
	
	
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
	
	@JsonProperty("verifiedPayload")
	private String verifiedPayload;
	
	@JsonProperty("fullValidatedAddress")
    private String fullValidatedAddress;
	
	
	public Addresses() {
		// Addresses
	}

	/**
	 *
	 * @return
	 */
	public long getAddressId() {
		return addressId;
	}

	/**
	 *
	 * @param addressId
	 */
	public void setAddressId(final long addressId) {
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
	public void setAddressType(final String addressType) {
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
	public void setBusinessHour(final String businessHour) {
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
	public void setCapacity(final BigDecimal capacity) {
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
	public void setCityCode(final String cityCode) {
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
	public void setCityName(final String cityName) {
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
	public void setCommentText(final String commentText) {
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
	public void setContactPersonName(final String contactPersonName) {
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
	public void setCountryCode(final String countryCode) {
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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
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
	public void setEndDate(final Date endDate) {
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
	public void setMailCareOf(final String mailCareOf) {
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
	public void setMailFlag(final String mailFlag) {
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
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
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
	public void setNoFixedAddressFlag(final String noFixedAddressFlag) {
		this.noFixedAddressFlag = noFixedAddressFlag;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
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
	public void setOwnerClass(final String ownerClass) {
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
	public void setOwnerCode(final String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public BigDecimal getServiceAddressId() {
		return serviceAddressId;
	}

	public void setServiceAddressId(BigDecimal serviceAddressId) {
		this.serviceAddressId = serviceAddressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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
	public void setOwnerId(final BigDecimal ownerId) {
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
	public void setOwnerSeq(final BigDecimal ownerSeq) {
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
	public void setPrimaryFlag(final String primaryFlag) {
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
	public void setProvStateCode(final String provStateCode) {
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
	public void setSealFlag(final String sealFlag) {
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
	public void setServicesFlag(final String servicesFlag) {
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
	public void setSpecialNeedsCode(final String specialNeedsCode) {
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
	public void setStartDate(final Date startDate) {
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
	public void setStreet(final String street) {
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
	public void setStreetDirection(final String streetDirection) {
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
	public void setStreetNumber(final String streetNumber) {
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
	public void setSuiteNumber(final String suiteNumber) {
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
	public void setValidatedPafFlag(final String validatedPafFlag) {
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
	public void setZipPostalCode(final String zipPostalCode) {
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
	public void setLength(final int length) {
		this.length = length;
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

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getAddressTypeDbCode() {
		return addressTypeDbCode;
	}

	public void setAddressTypeDbCode(final String addressTypeDbCode) {
		this.addressTypeDbCode = addressTypeDbCode;
	}

	public String getJnOperation() {
		return jnOperation;
	}

	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}

	public String getJnOracleUser() {
		return jnOracleUser;
	}

	public void setJnOracleUser(String jnOracleUser) {
		this.jnOracleUser = jnOracleUser;
	}

	public Date getJnDatetime() {
		return jnDatetime;
	}

	public void setJnDatetime(Date jnDatetime) {
		this.jnDatetime = jnDatetime;
	}

	public String getJnNotes() {
		return jnNotes;
	}

	public void setJnNotes(String jnNotes) {
		this.jnNotes = jnNotes;
	}

	public String getJnAppln() {
		return jnAppln;
	}

	public void setJnAppln(String jnAppln) {
		this.jnAppln = jnAppln;
	}

	public BigDecimal getJnSession() {
		return jnSession;
	}

	public void setJnSession(BigDecimal jnSession) {
		this.jnSession = jnSession;
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

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getMeshBlock() {
		return meshBlock;
	}

	public void setMeshBlock(BigDecimal meshBlock) {
		this.meshBlock = meshBlock;
	}

    public String getVerifiedPayload() {
        return verifiedPayload;
    }

    public void setVerifiedPayload(String verifiedPayload) {
        this.verifiedPayload = verifiedPayload;
    }

	public String getFullValidatedAddress() {
		return fullValidatedAddress;
	}

	public void setFullValidatedAddress(String fullValidatedAddress) {
		this.fullValidatedAddress = fullValidatedAddress;
	}

    
    
	
}