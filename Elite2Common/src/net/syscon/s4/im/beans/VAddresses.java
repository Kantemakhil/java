package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.Phones;

/**
 * Class VAddresses
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VAddresses extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("addressId")
	private BigDecimal addressId;

	@JsonProperty("addressType")
	private String addressType;

	@JsonProperty("addressTypeDesc")
	private String addressTypeDesc;

	@JsonProperty("area")
	private String area;

	@JsonProperty("businessHour")
	private String businessHour;

	@JsonProperty("capacity")
	private BigDecimal capacity;

	@JsonProperty("cityCode")
	private String cityCode;

	@JsonProperty("cityName")
	private String cityName;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("contactPersonName")
	private String contactPersonName;

	@JsonProperty("country")
	private String country;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("countryDesc")
	private String countryDesc;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("fullAddress")
	private String fullAddress;

	@JsonProperty("house")
	private String house;

	@JsonProperty("mailCareOf")
	private String mailCareOf;

	@JsonProperty("mailFlag")
	private String mailFlag;

	@JsonProperty("noFixedAddressFlag")
	private String noFixedAddressFlag;

	@JsonProperty("ownerClass")
	private String ownerClass;

	@JsonProperty("ownerCode")
	private String ownerCode;

	@JsonProperty("ownerId")
	private BigDecimal ownerId;

	@JsonProperty("ownerSeq")
	private BigDecimal ownerSeq;

	@JsonProperty("primaryFlag")
	private String primaryFlag;

	@JsonProperty("provStateCode")
	private String provStateCode;

	@JsonProperty("provStateDesc")
	private String provStateDesc;

	@JsonProperty("servicesFlag")
	private String servicesFlag;

	@JsonProperty("specialNeeds")
	private String specialNeeds;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("street")
	private String street;

	@JsonProperty("streetDirection")
	private String streetDirection;

	@JsonProperty("streetDirectionDesc")
	private String streetDirectionDesc;

	@JsonProperty("streetInformation")
	private String streetInformation;

	@JsonProperty("streetNumber")
	private String streetNumber;

	@JsonProperty("suiteNumber")
	private String suiteNumber;

	@JsonProperty("validatedFlag")
	private String validatedFlag;

	@JsonProperty("zipPostalCode")
	private String zipPostalCode;

	@JsonProperty("countryDbCode")
	private String countryDbCode;

	@JsonProperty("cityDbCode")
	private String cityDbCode;

	@JsonProperty("provStateDbCode")
	private String provStateDbCode;

	@JsonProperty("streetDirectionDbCode")
	private String streetDirectionDbCode;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("addr")
	private String addr;
	
	private String agency;
	private String code;
	@JsonProperty("phones")
	private List<Phones> phones;
	
	@JsonProperty("addressUsages")
	private List <AddressUsages> addressUsages;
	
	private String facility;
	
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
	
	@JsonProperty("corporateName")
	private String corporateName;

	

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAddressId() {
		return addressId;
	}

	/**
	 *
	 * @param addressId
	 */
	public void setAddressId(BigDecimal addressId) {
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
	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}

	/**
	 *
	 * @param addressTypeDesc
	 */
	public void setAddressTypeDesc(String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}

	/**
	 *
	 * @return
	 */
	public String getArea() {
		return area;
	}

	/**
	 *
	 * @param area
	 */
	public void setArea(String area) {
		this.area = area;
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

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	public String getCountry() {
		return country;
	}

	/**
	 *
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
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
	public String getCountryDesc() {
		return countryDesc;
	}

	/**
	 *
	 * @param countryDesc
	 */
	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
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
	public String getFullAddress() {
		return fullAddress;
	}

	/**
	 *
	 * @param fullAddress
	 */
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	/**
	 *
	 * @return
	 */
	public String getHouse() {
		return house;
	}

	/**
	 *
	 * @param house
	 */
	public void setHouse(String house) {
		this.house = house;
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
	public String getProvStateDesc() {
		return provStateDesc;
	}

	/**
	 *
	 * @param provStateDesc
	 */
	public void setProvStateDesc(String provStateDesc) {
		this.provStateDesc = provStateDesc;
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
	public String getSpecialNeeds() {
		return specialNeeds;
	}

	/**
	 *
	 * @param specialNeeds
	 */
	public void setSpecialNeeds(String specialNeeds) {
		this.specialNeeds = specialNeeds;
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
	public String getStreetDirectionDesc() {
		return streetDirectionDesc;
	}

	/**
	 *
	 * @param streetDirectionDesc
	 */
	public void setStreetDirectionDesc(String streetDirectionDesc) {
		this.streetDirectionDesc = streetDirectionDesc;
	}

	/**
	 *
	 * @return
	 */
	public String getStreetInformation() {
		return streetInformation;
	}

	/**
	 *
	 * @param streetInformation
	 */
	public void setStreetInformation(String streetInformation) {
		this.streetInformation = streetInformation;
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
	public String getValidatedFlag() {
		return validatedFlag;
	}

	/**
	 *
	 * @param validatedFlag
	 */
	public void setValidatedFlag(String validatedFlag) {
		this.validatedFlag = validatedFlag;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the phones
	 */
	public List<Phones> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}

	/**
	 * @return the addressUsages
	 */
	public List<AddressUsages> getAddressUsages() {
		return addressUsages;
	}

	/**
	 * @param addressUsages the addressUsages to set
	 */
	public void setAddressUsages(List<AddressUsages> addressUsages) {
		this.addressUsages = addressUsages;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
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

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}	
	
}