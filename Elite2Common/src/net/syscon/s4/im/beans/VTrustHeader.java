package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.common.validators.ValidBirthDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VTrustHeader extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountClosedFlag")
	private String accountClosedFlag;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("age")
	private BigDecimal age;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("agyLocType")
	private String agyLocType;

	@JsonProperty("aliasOffenderId")
	private BigDecimal aliasOffenderId;

//	@JsonProperty("birthDate")
//	private Object birthDate;

	@JsonProperty("birthDate")
	@ValidBirthDate(message = "date.format.not.valid", pattern = Utilities.DATE_PATTEN)
	private Date birthDate;
	
	@JsonProperty("bookingBeginDate")
	private Object bookingBeginDate;

	@JsonProperty("bookingEndDate")
	private Object bookingEndDate;

	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("bookingStatus")
	private String bookingStatus;

	@JsonProperty("bookingType")
	private String bookingType;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("commissaryTrustCaseload")
	private String commissaryTrustCaseload;

	@JsonProperty("communityActiveFlag")
	private String communityActiveFlag;

	@JsonProperty("communityStatus")
	private String communityStatus;

	@JsonProperty("communityTrustCaseload")
	private String communityTrustCaseload;

	@JsonProperty("createIntakeAgyLocId")
	private String createIntakeAgyLocId;

	@JsonProperty("disclosureFlag")
	private String disclosureFlag;

	@JsonProperty("ethnicity")
	private String ethnicity;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("headerStatus")
	private String headerStatus;

	@JsonProperty("inOutStatus")
	private String inOutStatus;

	@JsonProperty("indigentFlag")
	private String indigentFlag;

	@JsonProperty("intakeAgyLocId")
	private String intakeAgyLocId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("movementReason")
	private String movementReason;

	@JsonProperty("offAlerts")
	private String offAlerts;

	@JsonProperty("offSupLevel")
	private String offSupLevel;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("officer")
	private String officer;

	@JsonProperty("prisonLocation")
	private String prisonLocation;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("status1")
	private String status1;

	@JsonProperty("status2")
	private String status2;

	@JsonProperty("status3")
	private String status3;

	@JsonProperty("statusDisplay")
	private String statusDisplay;

	@JsonProperty("statusReason")
	private String statusReason;

	@JsonProperty("suffix")
	private String suffix;

	@JsonProperty("trustCaseloadId")
	private String trustCaseloadId;

	@JsonProperty("dialogData")
	private Integer dialogData;

	@JsonProperty("currentBalance")
	private Double currentBalance;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("imageId")
	private Long imageId;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("trustAccount")
	private Boolean trustAccount;
	
	private String name;
	
	@JsonProperty("sex")
	private String sex;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("parentForm")
	private String parentForm;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Creates new VTrustHeader class Object
	 */
	public VTrustHeader() {
		// VTrustHeader
	}

	/**
	 * @return the accountClosedFlag
	 */
	public String getAccountClosedFlag() {
		return accountClosedFlag;
	}

	/**
	 * @param accountClosedFlag
	 *            the accountClosedFlag to set
	 */
	public void setAccountClosedFlag(String accountClosedFlag) {
		this.accountClosedFlag = accountClosedFlag;
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
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the age
	 */
	public BigDecimal getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(BigDecimal age) {
		this.age = age;
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
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the agyLocType
	 */
	public String getAgyLocType() {
		return agyLocType;
	}

	/**
	 * @param agyLocType
	 *            the agyLocType to set
	 */
	public void setAgyLocType(String agyLocType) {
		this.agyLocType = agyLocType;
	}

	/**
	 * @return the aliasOffenderId
	 */
	public BigDecimal getAliasOffenderId() {
		return aliasOffenderId;
	}

	/**
	 * @param aliasOffenderId
	 *            the aliasOffenderId to set
	 */
	public void setAliasOffenderId(BigDecimal aliasOffenderId) {
		this.aliasOffenderId = aliasOffenderId;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the bookingBeginDate
	 */
	public Object getBookingBeginDate() {
		return bookingBeginDate;
	}

	/**
	 * @param bookingBeginDate
	 *            the bookingBeginDate to set
	 */
	public void setBookingBeginDate(Object bookingBeginDate) {
		this.bookingBeginDate = bookingBeginDate;
	}

	/**
	 * @return the bookingEndDate
	 */
	public Object getBookingEndDate() {
		return bookingEndDate;
	}

	/**
	 * @param bookingEndDate
	 *            the bookingEndDate to set
	 */
	public void setBookingEndDate(Object bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	/**
	 * @return the bookingNo
	 */
	public String getBookingNo() {
		return bookingNo;
	}

	/**
	 * @param bookingNo
	 *            the bookingNo to set
	 */
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 * @return the bookingStatus
	 */
	public String getBookingStatus() {
		return bookingStatus;
	}

	/**
	 * @param bookingStatus
	 *            the bookingStatus to set
	 */
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	/**
	 * @return the bookingType
	 */
	public String getBookingType() {
		return bookingType;
	}

	/**
	 * @param bookingType
	 *            the bookingType to set
	 */
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
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
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the commissaryTrustCaseload
	 */
	public String getCommissaryTrustCaseload() {
		return commissaryTrustCaseload;
	}

	/**
	 * @param commissaryTrustCaseload
	 *            the commissaryTrustCaseload to set
	 */
	public void setCommissaryTrustCaseload(String commissaryTrustCaseload) {
		this.commissaryTrustCaseload = commissaryTrustCaseload;
	}

	/**
	 * @return the communityActiveFlag
	 */
	public String getCommunityActiveFlag() {
		return communityActiveFlag;
	}

	/**
	 * @param communityActiveFlag
	 *            the communityActiveFlag to set
	 */
	public void setCommunityActiveFlag(String communityActiveFlag) {
		this.communityActiveFlag = communityActiveFlag;
	}

	/**
	 * @return the communityStatus
	 */
	public String getCommunityStatus() {
		return communityStatus;
	}

	/**
	 * @param communityStatus
	 *            the communityStatus to set
	 */
	public void setCommunityStatus(String communityStatus) {
		this.communityStatus = communityStatus;
	}

	/**
	 * @return the communityTrustCaseload
	 */
	public String getCommunityTrustCaseload() {
		return communityTrustCaseload;
	}

	/**
	 * @param communityTrustCaseload
	 *            the communityTrustCaseload to set
	 */
	public void setCommunityTrustCaseload(String communityTrustCaseload) {
		this.communityTrustCaseload = communityTrustCaseload;
	}

	/**
	 * @return the createIntakeAgyLocId
	 */
	public String getCreateIntakeAgyLocId() {
		return createIntakeAgyLocId;
	}

	/**
	 * @param createIntakeAgyLocId
	 *            the createIntakeAgyLocId to set
	 */
	public void setCreateIntakeAgyLocId(String createIntakeAgyLocId) {
		this.createIntakeAgyLocId = createIntakeAgyLocId;
	}

	/**
	 * @return the disclosureFlag
	 */
	public String getDisclosureFlag() {
		return disclosureFlag;
	}

	/**
	 * @param disclosureFlag
	 *            the disclosureFlag to set
	 */
	public void setDisclosureFlag(String disclosureFlag) {
		this.disclosureFlag = disclosureFlag;
	}

	/**
	 * @return the ethnicity
	 */
	public String getEthnicity() {
		return ethnicity;
	}

	/**
	 * @param ethnicity
	 *            the ethnicity to set
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the headerStatus
	 */
	public String getHeaderStatus() {
		return headerStatus;
	}

	/**
	 * @param headerStatus
	 *            the headerStatus to set
	 */
	public void setHeaderStatus(String headerStatus) {
		this.headerStatus = headerStatus;
	}

	/**
	 * @return the inOutStatus
	 */
	public String getInOutStatus() {
		return inOutStatus;
	}

	/**
	 * @param inOutStatus
	 *            the inOutStatus to set
	 */
	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	/**
	 * @return the indigentFlag
	 */
	public String getIndigentFlag() {
		return indigentFlag;
	}

	/**
	 * @param indigentFlag
	 *            the indigentFlag to set
	 */
	public void setIndigentFlag(String indigentFlag) {
		this.indigentFlag = indigentFlag;
	}

	/**
	 * @return the intakeAgyLocId
	 */
	public String getIntakeAgyLocId() {
		return intakeAgyLocId;
	}

	/**
	 * @param intakeAgyLocId
	 *            the intakeAgyLocId to set
	 */
	public void setIntakeAgyLocId(String intakeAgyLocId) {
		this.intakeAgyLocId = intakeAgyLocId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the livingUnitDescription
	 */
	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}

	/**
	 * @param livingUnitDescription
	 *            the livingUnitDescription to set
	 */
	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	/**
	 * @return the livingUnitId
	 */
	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 * @param livingUnitId
	 *            the livingUnitId to set
	 */
	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the movementReason
	 */
	public String getMovementReason() {
		return movementReason;
	}

	/**
	 * @param movementReason
	 *            the movementReason to set
	 */
	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}

	/**
	 * @return the offAlerts
	 */
	public String getOffAlerts() {
		return offAlerts;
	}

	/**
	 * @param offAlerts
	 *            the offAlerts to set
	 */
	public void setOffAlerts(String offAlerts) {
		this.offAlerts = offAlerts;
	}

	/**
	 * @return the offSupLevel
	 */
	public String getOffSupLevel() {
		return offSupLevel;
	}

	/**
	 * @param offSupLevel
	 *            the offSupLevel to set
	 */
	public void setOffSupLevel(String offSupLevel) {
		this.offSupLevel = offSupLevel;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the officer
	 */
	public String getOfficer() {
		return officer;
	}

	/**
	 * @param officer
	 *            the officer to set
	 */
	public void setOfficer(String officer) {
		this.officer = officer;
	}

	/**
	 * @return the prisonLocation
	 */
	public String getPrisonLocation() {
		return prisonLocation;
	}

	/**
	 * @param prisonLocation
	 *            the prisonLocation to set
	 */
	public void setPrisonLocation(String prisonLocation) {
		this.prisonLocation = prisonLocation;
	}

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the status1
	 */
	public String getStatus1() {
		return status1;
	}

	/**
	 * @param status1
	 *            the status1 to set
	 */
	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	/**
	 * @return the status2
	 */
	public String getStatus2() {
		return status2;
	}

	/**
	 * @param status2
	 *            the status2 to set
	 */
	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	/**
	 * @return the status3
	 */
	public String getStatus3() {
		return status3;
	}

	/**
	 * @param status3
	 *            the status3 to set
	 */
	public void setStatus3(String status3) {
		this.status3 = status3;
	}

	/**
	 * @return the statusDisplay
	 */
	public String getStatusDisplay() {
		return statusDisplay;
	}

	/**
	 * @param statusDisplay
	 *            the statusDisplay to set
	 */
	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}

	/**
	 * @param statusReason
	 *            the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix
	 *            the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the trustCaseloadId
	 */
	public String getTrustCaseloadId() {
		return trustCaseloadId;
	}

	/**
	 * @param trustCaseloadId
	 *            the trustCaseloadId to set
	 */
	public void setTrustCaseloadId(String trustCaseloadId) {
		this.trustCaseloadId = trustCaseloadId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the dialogData
	 */
	public Integer getDialogData() {
		return dialogData;
	}

	/**
	 * @param dialogData
	 *            the dialogData to set
	 */
	public void setDialogData(Integer dialogData) {
		this.dialogData = dialogData;
	}

	/**
	 * @return the currentBalance
	 */
	public Double getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * @param currentBalance
	 *            the currentBalance to set
	 */
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the imageId
	 */
	public Long getImageId() {
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType the caseloadType to set
	 */
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the trustAccount
	 */
	public Boolean getTrustAccount() {
		return trustAccount;
	}

	/**
	 * @param trustAccount the trustAccount to set
	 */
	public void setTrustAccount(Boolean trustAccount) {
		this.trustAccount = trustAccount;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public String getParentForm() {
		return parentForm;
	}

	public void setParentForm(String parentForm) {
		this.parentForm = parentForm;
	}
	
	

	
}
