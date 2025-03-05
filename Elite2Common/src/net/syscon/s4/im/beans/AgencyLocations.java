package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyLocations extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	@NotNull
	@Size(max = 6)
	private String agyLocId;

	@JsonProperty("description")
	@NotNull
	@Size(max = 40)
	private String description;

	@JsonProperty("agencyLocationType")
	@Size(max = 12)
	private String agencyLocationType;

	@JsonProperty("districtCode")
	@Size(max = 12)
	private String districtCode;

	@JsonProperty("Abbreviation")
	@Size(max = 12)
	private String Abbreviation;

	@JsonProperty("deactivationDate")
	private Date deactivationDate;

	@JsonProperty("staffId")
	private Integer staffId;
	
	@JsonProperty("contactName")
	@Size(max = 40)
	private String contactName;

	@JsonProperty("printQueue")
	@Size(max = 240)
	private String printQueue;

	@JsonProperty("jurisdictionCode")
	@Size(max = 12)
	private String jurisdictionCode;

	@JsonProperty("bailOfficeFlag")
	@NotNull
	@Size(max = 1)
	private String bailOfficeFlag;

	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("housingLev1Code")
	@Size(max = 12)
	private String housingLev1Code;

	@JsonProperty("housingLev2Code")
	@Size(max = 12)
	private String housingLev2Code;

	@JsonProperty("housingLev3Code")
	@Size(max = 12)
	private String housingLev3Code;

	@JsonProperty("housingLev4Code")
	@Size(max = 12)
	private String housingLev4Code;

	@JsonProperty("propertyLev1Code")
	@Size(max = 12)
	private String propertyLev1Code;

	@JsonProperty("propertyLev2Code")
	@Size(max = 12)
	private String propertyLev2Code;

	@JsonProperty("propertyLev3Code")
	@Size(max = 12)
	private String propertyLev3Code;

	@JsonProperty("lastBookingNo")
	private Integer lastBookingNo;

	@JsonProperty("commissaryPrivilege")
	@Size(max = 12)
	private String commissaryPrivilege;

	@JsonProperty("businessHours")
	@Size(max = 40)
	private String businessHours;

	@JsonProperty("addressType")
	@Size(max = 12)
	private String addressType;

	@JsonProperty("activeFlag")
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("disabilityAccessCode")
	@Size(max = 12)
	private String disabilityAccessCode;

	@JsonProperty("intakeFlag")
	@Size(max = 1)
	private String intakeFlag;

	@JsonProperty("subAreaCode")
	@Size(max = 12)
	private String subAreaCode;

	@JsonProperty("areaCode")
	@Size(max = 12)
	private String areaCode;

	@JsonProperty("nomsRegionCode")
	@Size(max = 12)
	private String nomsRegionCode;

	@JsonProperty("geographicRegionCode")
	@Size(max = 12)
	private String geographicRegionCode;

	@JsonProperty("justiceAreaCode")
	@Size(max = 12)
	private String justiceAreaCode;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 12)
	private String createUserId;

	@JsonProperty("modifyDatetime")
	@NotNull
	private Date modifyDatetime;
	
	@JsonProperty("tempSubAreaCode")
	@NotNull
	private String tempSubAreaCode;
	
	@JsonProperty("tempAreaCode")
	@NotNull
	private String tempAreaCode;
	
	@JsonProperty("tempNomsRegionCode")
	@NotNull
	private String tempNomsRegionCode;
	
	@JsonProperty("tempGeographicRegionCode")
	@NotNull
	private String tempGeographicRegionCode;
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

	@JsonProperty("modifyUserId")
	@NotNull
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("longDescription")
	@NotNull
	@Size(max = 3000)
	private String longDescription;

	@JsonProperty("labcorpClientId")
	@NotNull
	@Size(max = 50)
	private String labcorpClientId;

	@JsonProperty("sealFlag")
	@NotNull
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("localAuthorityCode")
	private String localAuthorityCode;
	/**
	 * Creates new AgencyLocations class Object
	 */
	public AgencyLocations() {
		// AgencyLocations
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
	 * @return the agencyLocationType
	 */
	public String getAgencyLocationType() {
		return agencyLocationType;
	}

	/**
	 * @param agencyLocationType
	 *            the agencyLocationType to set
	 */
	public void setAgencyLocationType(final String agencyLocationType) {
		this.agencyLocationType = agencyLocationType;
	}

	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode
	 *            the districtCode to set
	 */
	public void setDistrictCode(final String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return Abbreviation;
	}

	/**
	 * @param abbreviation
	 *            the abbreviation to set
	 */
	public void setAbbreviation(final String abbreviation) {
		Abbreviation = abbreviation;
	}

	/**
	 * @return the deactivationDate
	 */
	public Date getDeactivationDate() {
		return deactivationDate;
	}

	/**
	 * @param deactivationDate
	 *            the deactivationDate to set
	 */
	public void setDeactivationDate(final Date deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(final String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the printQueue
	 */
	public String getPrintQueue() {
		return printQueue;
	}

	/**
	 * @param printQueue
	 *            the printQueue to set
	 */
	public void setPrintQueue(final String printQueue) {
		this.printQueue = printQueue;
	}

	/**
	 * @return the jurisdictionCode
	 */
	public String getJurisdictionCode() {
		return jurisdictionCode;
	}

	/**
	 * @param jurisdictionCode
	 *            the jurisdictionCode to set
	 */
	public void setJurisdictionCode(final String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}

	/**
	 * @return the bailOfficeFlag
	 */
	public String getBailOfficeFlag() {
		return bailOfficeFlag;
	}

	/**
	 * @param bailOfficeFlag
	 *            the bailOfficeFlag to set
	 */
	public void setBailOfficeFlag(final String bailOfficeFlag) {
		this.bailOfficeFlag = bailOfficeFlag;
	}

	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the housingLev1Code
	 */
	public String getHousingLev1Code() {
		return housingLev1Code;
	}

	/**
	 * @param housingLev1Code
	 *            the housingLev1Code to set
	 */
	public void setHousingLev1Code(final String housingLev1Code) {
		this.housingLev1Code = housingLev1Code;
	}

	/**
	 * @return the housingLev2Code
	 */
	public String getHousingLev2Code() {
		return housingLev2Code;
	}

	/**
	 * @param housingLev2Code
	 *            the housingLev2Code to set
	 */
	public void setHousingLev2Code(final String housingLev2Code) {
		this.housingLev2Code = housingLev2Code;
	}

	/**
	 * @return the housingLev3Code
	 */
	public String getHousingLev3Code() {
		return housingLev3Code;
	}

	/**
	 * @param housingLev3Code
	 *            the housingLev3Code to set
	 */
	public void setHousingLev3Code(final String housingLev3Code) {
		this.housingLev3Code = housingLev3Code;
	}

	/**
	 * @return the housingLev4Code
	 */
	public String getHousingLev4Code() {
		return housingLev4Code;
	}

	/**
	 * @param housingLev4Code
	 *            the housingLev4Code to set
	 */
	public void setHousingLev4Code(final String housingLev4Code) {
		this.housingLev4Code = housingLev4Code;
	}

	/**
	 * @return the propertyLev1Code
	 */
	public String getPropertyLev1Code() {
		return propertyLev1Code;
	}

	/**
	 * @param propertyLev1Code
	 *            the propertyLev1Code to set
	 */
	public void setPropertyLev1Code(final String propertyLev1Code) {
		this.propertyLev1Code = propertyLev1Code;
	}

	/**
	 * @return the propertyLev2Code
	 */
	public String getPropertyLev2Code() {
		return propertyLev2Code;
	}

	/**
	 * @param propertyLev2Code
	 *            the propertyLev2Code to set
	 */
	public void setPropertyLev2Code(final String propertyLev2Code) {
		this.propertyLev2Code = propertyLev2Code;
	}

	/**
	 * @return the propertyLev3Code
	 */
	public String getPropertyLev3Code() {
		return propertyLev3Code;
	}

	/**
	 * @param propertyLev3Code
	 *            the propertyLev3Code to set
	 */
	public void setPropertyLev3Code(final String propertyLev3Code) {
		this.propertyLev3Code = propertyLev3Code;
	}

	/**
	 * @return the lastBookingNo
	 */
	public Integer getLastBookingNo() {
		return lastBookingNo;
	}

	/**
	 * @param lastBookingNo
	 *            the lastBookingNo to set
	 */
	public void setLastBookingNo(final Integer lastBookingNo) {
		this.lastBookingNo = lastBookingNo;
	}

	/**
	 * @return the commissaryPrivilege
	 */
	public String getCommissaryPrivilege() {
		return commissaryPrivilege;
	}

	/**
	 * @param commissaryPrivilege
	 *            the commissaryPrivilege to set
	 */
	public void setCommissaryPrivilege(final String commissaryPrivilege) {
		this.commissaryPrivilege = commissaryPrivilege;
	}

	/**
	 * @return the businessHours
	 */
	public String getBusinessHours() {
		return businessHours;
	}

	/**
	 * @param businessHours
	 *            the businessHours to set
	 */
	public void setBusinessHours(final String businessHours) {
		this.businessHours = businessHours;
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
	 * @return the disabilityAccessCode
	 */
	public String getDisabilityAccessCode() {
		return disabilityAccessCode;
	}

	/**
	 * @param disabilityAccessCode
	 *            the disabilityAccessCode to set
	 */
	public void setDisabilityAccessCode(final String disabilityAccessCode) {
		this.disabilityAccessCode = disabilityAccessCode;
	}

	/**
	 * @return the intakeFlag
	 */
	public String getIntakeFlag() {
		return intakeFlag;
	}

	/**
	 * @param intakeFlag
	 *            the intakeFlag to set
	 */
	public void setIntakeFlag(final String intakeFlag) {
		this.intakeFlag = intakeFlag;
	}

	/**
	 * @return the subAreaCode
	 */
	public String getSubAreaCode() {
		return subAreaCode;
	}

	/**
	 * @param subAreaCode
	 *            the subAreaCode to set
	 */
	public void setSubAreaCode(final String subAreaCode) {
		this.subAreaCode = subAreaCode;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            the areaCode to set
	 */
	public void setAreaCode(final String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return the nomsRegionCode
	 */
	public String getNomsRegionCode() {
		return nomsRegionCode;
	}

	/**
	 * @param nomsRegionCode
	 *            the nomsRegionCode to set
	 */
	public void setNomsRegionCode(final String nomsRegionCode) {
		this.nomsRegionCode = nomsRegionCode;
	}

	/**
	 * @return the geographicRegionCode
	 */
	public String getGeographicRegionCode() {
		return geographicRegionCode;
	}

	/**
	 * @param geographicRegionCode
	 *            the geographicRegionCode to set
	 */
	public void setGeographicRegionCode(final String geographicRegionCode) {
		this.geographicRegionCode = geographicRegionCode;
	}

	/**
	 * @return the justiceAreaCode
	 */
	public String getJusticeAreaCode() {
		return justiceAreaCode;
	}

	/**
	 * @param justiceAreaCode
	 *            the justiceAreaCode to set
	 */
	public void setJusticeAreaCode(final String justiceAreaCode) {
		this.justiceAreaCode = justiceAreaCode;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription
	 *            the longDescription to set
	 */
	public void setLongDescription(final String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * @return the labcorpClientId
	 */
	public String getLabcorpClientId() {
		return labcorpClientId;
	}

	/**
	 * @param labcorpClientId
	 *            the labcorpClientId to set
	 */
	public void setLabcorpClientId(final String labcorpClientId) {
		this.labcorpClientId = labcorpClientId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
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
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the canDisplay
	 */
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay the canDisplay to set
	 */
	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getTempSubAreaCode() {
		return tempSubAreaCode;
	}

	public void setTempSubAreaCode(String tempSubAreaCode) {
		this.tempSubAreaCode = tempSubAreaCode;
	}

	public String getTempAreaCode() {
		return tempAreaCode;
	}

	public void setTempAreaCode(String tempAreaCode) {
		this.tempAreaCode = tempAreaCode;
	}

	public String getTempNomsRegionCode() {
		return tempNomsRegionCode;
	}

	public void setTempNomsRegionCode(String tempNomsRegionCode) {
		this.tempNomsRegionCode = tempNomsRegionCode;
	}

	public String getTempGeographicRegionCode() {
		return tempGeographicRegionCode;
	}

	public void setTempGeographicRegionCode(String tempGeographicRegionCode) {
		this.tempGeographicRegionCode = tempGeographicRegionCode;
	}

	public String getLocalAuthorityCode() {
		return localAuthorityCode;
	}

	public void setLocalAuthorityCode(String localAuthorityCode) {
		this.localAuthorityCode = localAuthorityCode;
	}

}