package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.common.validators.ValidBirthDate;
import net.syscon.s4.im.beans.Offenders;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VHeaderBlock extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("age")
	private BigDecimal age;

	@JsonProperty("agencyImlId")
	private BigDecimal agencyImlId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("agyLocType")
	private String agyLocType;

	@JsonProperty("aliasOffenderId")
	private BigDecimal aliasOffenderId;

	@JsonProperty("assignedStaffId")
	private BigDecimal assignedStaffId;

	@JsonProperty("birthDate")
	@ValidBirthDate(message = "date.format.not.valid", pattern = Utilities.DATE_PATTEN)
	private Date birthDate;

	@JsonProperty("bookingBeginDate")
	private Date bookingBeginDate;

	@JsonProperty("bookingCreatedDate")
	private Date bookingCreatedDate;

	@JsonProperty("bookingEndDate")
	private Date bookingEndDate;

	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("bookingStatus")
	private String bookingStatus;

	@JsonProperty("bookingType")
	private String bookingType;

	@JsonProperty("communityActiveFlag")
	private String communityActiveFlag;

	@JsonProperty("communityStatus")
	private String communityStatus;

	@JsonProperty("createAgyLocId")
	private String createAgyLocId;

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

	@JsonProperty("imageId")
	private BigDecimal imageId;

	@JsonProperty("imageThumbnail")
	private byte[] imageThumbnail;

	@JsonProperty("inOutStatus")
	private String inOutStatus;

	@JsonProperty("intakeAgyLocId")
	private String intakeAgyLocId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("livUnitDesc")
	private String livUnitDesc;

	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("locationCode")
	private String locationCode;

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

	@JsonProperty("caseLoadId")
	private String caseLoadId;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("createuserId")
	private String createuserId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("activeDatetime")
	private Date activeDatetime;

	@JsonProperty("nbtAssignReason")
	private String nbtAssignReason;

	@JsonProperty("nbtOffenderBookId")
	private BigDecimal nbtOffenderBookId;

	@JsonProperty("nbtNonAssBProceed")
	private boolean nbtNonAssBProceed;

	@JsonProperty("nbtNonAssVProceed")
	private boolean nbtNonAssVProceed;

	@JsonProperty("nbtChkSecBProceed")
	private boolean nbtChkSecBProceed;

	@JsonProperty("nbtChkSecVProceed")
	private boolean nbtChkSecVProceed;

	@JsonProperty("insertedFlag")
	private boolean insertedFlag;

	@JsonProperty("assignmentDate")
	private Date assignmentDate;

	@JsonProperty("assignmentTime")
	private Date assignmentTime;

	@JsonProperty("trustAccount")
	private Boolean trustAccount;

	@JsonProperty("createAccount")
	private Boolean createAccount;
	
	@JsonProperty("globalPurge")
	private Boolean globalPurge;
	
	@JsonProperty("pDelType")
	private String pDelType;
	
	@JsonProperty("lvBookingCnt")
	private Long lvBookingCnt;
	
	@JsonProperty("pSealType")
	private String pSealType;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sex")
	private String sex;

	@JsonProperty("notification")
	private String notification;
	
	@JsonProperty("internalLocationId")
	private Integer internalLocationId;
	
	@JsonProperty("offenderFullName")
	private String offenderFullName;
	
	@JsonProperty("courtReportFlag")
	private String courtReportFlag;
	
	@JsonProperty("courtActionFlag")
	private String courtActionFlag;
	
	@JsonProperty("casePlanFlag")
	private String casePlanFlag;
	
	@JsonProperty("conditionsFlag")
	private String conditionsFlag;
	
	@JsonProperty("nonAssocationDetails")
	private List<Offenders> nonAssocationDetails;
	
	@JsonProperty("nonAssocationData")
	private String nonAssocationData;
	
	@JsonProperty("trustFlag")
	private String trustFlag;
	
	@JsonProperty("gangConflitData")
	private String gangConflitData;	
	
	@JsonProperty("parentForm")
	private String parentForm;
	
	@JsonProperty("birthYear")
	private String birthYear;
	
	@JsonProperty("secondMiddleName")
	private String secondMiddleName;
	
	@JsonProperty("birthRange")
	private BigDecimal birthRange;
	
	public String getNonAssocationData() {
		return nonAssocationData;
	}

	public void setNonAssocationData(String nonAssocationData) {
		this.nonAssocationData = nonAssocationData;
	}

	public List<Offenders> getNonAssocationDetails() {
		return nonAssocationDetails;
	}

	public void setNonAssocationDetails(List<Offenders> nonAssocationDetails) {
		this.nonAssocationDetails = nonAssocationDetails;
	}

	
	
	
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
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAge() {
		return age;
	}

	/**
	 *
	 * @param age
	 */
	public void setAge(final BigDecimal age) {
		this.age = age;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAgencyImlId() {
		return agencyImlId;
	}

	/**
	 *
	 * @param agencyImlId
	 */
	public void setAgencyImlId(final BigDecimal agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getAgyLocType() {
		return agyLocType;
	}

	/**
	 *
	 * @param agyLocType
	 */
	public void setAgyLocType(final String agyLocType) {
		this.agyLocType = agyLocType;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAliasOffenderId() {
		return aliasOffenderId;
	}

	/**
	 *
	 * @param aliasOffenderId
	 */
	public void setAliasOffenderId(final BigDecimal aliasOffenderId) {
		this.aliasOffenderId = aliasOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAssignedStaffId() {
		return assignedStaffId;
	}

	/**
	 *
	 * @param assignedStaffId
	 */
	public void setAssignedStaffId(final BigDecimal assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}

	/**
	 *
	 * @return
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 *
	 * @param birthDate
	 */
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getBookingBeginDate() {
		return bookingBeginDate;
	}

	/**
	 *
	 * @param bookingBeginDate
	 */
	public void setBookingBeginDate(final Date bookingBeginDate) {
		this.bookingBeginDate = bookingBeginDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getBookingCreatedDate() {
		return bookingCreatedDate;
	}

	/**
	 *
	 * @param bookingCreatedDate
	 */
	public void setBookingCreatedDate(final Date bookingCreatedDate) {
		this.bookingCreatedDate = bookingCreatedDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getBookingEndDate() {
		return bookingEndDate;
	}

	/**
	 *
	 * @param bookingEndDate
	 */
	public void setBookingEndDate(final Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	/**
	 *
	 * @return
	 */
	public String getBookingNo() {
		return bookingNo;
	}

	/**
	 *
	 * @param bookingNo
	 */
	public void setBookingNo(final String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 *
	 * @return
	 */
	public String getBookingStatus() {
		return bookingStatus;
	}

	/**
	 *
	 * @param bookingStatus
	 */
	public void setBookingStatus(final String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getBookingType() {
		return bookingType;
	}

	/**
	 *
	 * @param bookingType
	 */
	public void setBookingType(final String bookingType) {
		this.bookingType = bookingType;
	}

	/**
	 *
	 * @return
	 */
	public String getCommunityActiveFlag() {
		return communityActiveFlag;
	}

	/**
	 *
	 * @param communityActiveFlag
	 */
	public void setCommunityActiveFlag(final String communityActiveFlag) {
		this.communityActiveFlag = communityActiveFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCommunityStatus() {
		return communityStatus;
	}

	/**
	 *
	 * @param communityStatus
	 */
	public void setCommunityStatus(final String communityStatus) {
		this.communityStatus = communityStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateAgyLocId() {
		return createAgyLocId;
	}

	/**
	 *
	 * @param createAgyLocId
	 */
	public void setCreateAgyLocId(final String createAgyLocId) {
		this.createAgyLocId = createAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateIntakeAgyLocId() {
		return createIntakeAgyLocId;
	}

	/**
	 *
	 * @param createIntakeAgyLocId
	 */
	public void setCreateIntakeAgyLocId(final String createIntakeAgyLocId) {
		this.createIntakeAgyLocId = createIntakeAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getDisclosureFlag() {
		return disclosureFlag;
	}

	/**
	 *
	 * @param disclosureFlag
	 */
	public void setDisclosureFlag(final String disclosureFlag) {
		this.disclosureFlag = disclosureFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getEthnicity() {
		return ethnicity;
	}

	/**
	 *
	 * @param ethnicity
	 */
	public void setEthnicity(final String ethnicity) {
		this.ethnicity = ethnicity;
	}

	/**
	 *
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 *
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 *
	 * @param gender
	 */
	public void setGender(final String gender) {
		this.gender = gender;
	}

	/**
	 *
	 * @return
	 */
	public String getHeaderStatus() {
		return headerStatus;
	}

	/**
	 *
	 * @param headerStatus
	 */
	public void setHeaderStatus(final String headerStatus) {
		this.headerStatus = headerStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getInOutStatus() {
		return inOutStatus;
	}

	/**
	 *
	 * @param inOutStatus
	 */
	public void setInOutStatus(final String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getIntakeAgyLocId() {
		return intakeAgyLocId;
	}

	/**
	 *
	 * @param intakeAgyLocId
	 */
	public void setIntakeAgyLocId(final String intakeAgyLocId) {
		this.intakeAgyLocId = intakeAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * @return
	 */
	public String getLivUnitDesc() {
		return livUnitDesc;
	}

	/**
	 *
	 * @param livUnitDesc
	 */
	public void setLivUnitDesc(final String livUnitDesc) {
		this.livUnitDesc = livUnitDesc;
	}

	/**
	 *
	 * @return
	 */
	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}

	/**
	 *
	 * @param livingUnitDescription
	 */
	public void setLivingUnitDescription(final String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 *
	 * @param livingUnitId
	 */
	public void setLivingUnitId(final BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 *
	 * @return
	 */
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 *
	 * @param locationCode
	 */
	public void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 *
	 * @return
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 *
	 * @param middleName
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 *
	 * @return
	 */
	public String getMovementReason() {
		return movementReason;
	}

	/**
	 *
	 * @param movementReason
	 */
	public void setMovementReason(final String movementReason) {
		this.movementReason = movementReason;
	}

	/**
	 *
	 * @return
	 */
	public String getOffAlerts() {
		return offAlerts;
	}

	/**
	 *
	 * @param offAlerts
	 */
	public void setOffAlerts(final String offAlerts) {
		this.offAlerts = offAlerts;
	}

	/**
	 *
	 * @return
	 */
	public String getOffSupLevel() {
		return offSupLevel;
	}

	/**
	 *
	 * @param offSupLevel
	 */
	public void setOffSupLevel(final String offSupLevel) {
		this.offSupLevel = offSupLevel;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 *
	 * @param offenderId
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 *
	 * @param offenderIdDisplay
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 *
	 * @return
	 */
	public String getOfficer() {
		return officer;
	}

	/**
	 *
	 * @param officer
	 */
	public void setOfficer(final String officer) {
		this.officer = officer;
	}

	/**
	 *
	 * @return
	 */
	public String getPrisonLocation() {
		return prisonLocation;
	}

	/**
	 *
	 * @param prisonLocation
	 */
	public void setPrisonLocation(final String prisonLocation) {
		this.prisonLocation = prisonLocation;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 *
	 * @param rootOffenderId
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getStatus1() {
		return status1;
	}

	/**
	 *
	 * @param status1
	 */
	public void setStatus1(final String status1) {
		this.status1 = status1;
	}

	/**
	 *
	 * @return
	 */
	public String getStatus2() {
		return status2;
	}

	/**
	 *
	 * @param status2
	 */
	public void setStatus2(final String status2) {
		this.status2 = status2;
	}

	/**
	 *
	 * @return
	 */
	public String getStatus3() {
		return status3;
	}

	/**
	 *
	 * @param status3
	 */
	public void setStatus3(final String status3) {
		this.status3 = status3;
	}

	/**
	 *
	 * @return
	 */
	public String getStatusDisplay() {
		return statusDisplay;
	}

	/**
	 *
	 * @param statusDisplay
	 */
	public void setStatusDisplay(final String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	/**
	 *
	 * @return
	 */
	public String getStatusReason() {
		return statusReason;
	}

	/**
	 *
	 * @param statusReason
	 */
	public void setStatusReason(final String statusReason) {
		this.statusReason = statusReason;
	}

	/**
	 *
	 * @return
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 *
	 * @param suffix
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	public BigDecimal getImageId() {
		return imageId;
	}

	public void setImageId(BigDecimal imageId) {
		this.imageId = imageId;
	}

	public byte[] getImageThumbnail() {
		return imageThumbnail;
	}

	public void setImageThumbnail(byte[] imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	/**
	 * @param caseLoadId
	 *            the caseLoadId to set
	 */
	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * @return the createuserId
	 */
	public String getCreateUserId() {
		return createuserId;
	}

	/**
	 * @param createuserId
	 *            the createuserId to set
	 */
	public void setCreateUserId(final String createuserId) {
		this.createuserId = createuserId;
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
	 * @return the activeDatetime
	 */
	public Date getActiveDatetime() {
		return activeDatetime;
	}

	/**
	 * @param activeDatetime
	 *            the activeDatetime to set
	 */
	public void setActiveDatetime(final Date activeDatetime) {
		this.activeDatetime = activeDatetime;
	}

	/**
	 * @return the nbtAssignReason
	 */
	public String getNbtAssignReason() {
		return nbtAssignReason;
	}

	/**
	 * @param nbtAssignReason
	 *            the nbtAssignReason to set
	 */
	public void setNbtAssignReason(final String nbtAssignReason) {
		this.nbtAssignReason = nbtAssignReason;
	}

	/**
	 * @return the nbtOffenderBookId
	 */
	public BigDecimal getNbtOffenderBookId() {
		return nbtOffenderBookId;
	}

	/**
	 * @param nbtOffenderBookId
	 *            the nbtOffenderBookId to set
	 */
	public void setNbtOffenderBookId(final BigDecimal nbtOffenderBookId) {
		this.nbtOffenderBookId = nbtOffenderBookId;
	}

	/**
	 * @return the nbtNonAssBProceed
	 */
	public boolean isNbtNonAssBProceed() {
		return nbtNonAssBProceed;
	}

	/**
	 * @param nbtNonAssBProceed
	 *            the nbtNonAssBProceed to set
	 */
	public void setNbtNonAssBProceed(final boolean nbtNonAssBProceed) {
		this.nbtNonAssBProceed = nbtNonAssBProceed;
	}

	/**
	 * @return the nbtNonAssVProceed
	 */
	public boolean isNbtNonAssVProceed() {
		return nbtNonAssVProceed;
	}

	/**
	 * @param nbtNonAssVProceed
	 *            the nbtNonAssVProceed to set
	 */
	public void setNbtNonAssVProceed(final boolean nbtNonAssVProceed) {
		this.nbtNonAssVProceed = nbtNonAssVProceed;
	}

	/**
	 * @return the nbtChkSecBProceed
	 */
	public boolean isNbtChkSecBProceed() {
		return nbtChkSecBProceed;
	}

	/**
	 * @param nbtChkSecBProceed
	 *            the nbtChkSecBProceed to set
	 */
	public void setNbtChkSecBProceed(final boolean nbtChkSecBProceed) {
		this.nbtChkSecBProceed = nbtChkSecBProceed;
	}

	/**
	 * @return the nbtChkSecVProceed
	 */
	public boolean isNbtChkSecVProceed() {
		return nbtChkSecVProceed;
	}

	/**
	 * @param nbtChkSecVProceed
	 *            the nbtChkSecVProceed to set
	 */
	public void setNbtChkSecVProceed(final boolean nbtChkSecVProceed) {
		this.nbtChkSecVProceed = nbtChkSecVProceed;
	}

	/**
	 * @return the insertedFlag
	 */
	public boolean isInsertedFlag() {
		return insertedFlag;
	}

	/**
	 * @param insertedFlag
	 *            the insertedFlag to set
	 */
	public void setInsertedFlag(final boolean insertedFlag) {
		this.insertedFlag = insertedFlag;
	}

	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}

	/**
	 * @param assignmentDate
	 *            the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	/**
	 * @return the assignmentTime
	 */
	public Date getAssignmentTime() {
		return assignmentTime;
	}

	/**
	 * @param assignmentTime
	 *            the assignmentTime to set
	 */
	public void setAssignmentTime(Date assignmentTime) {
		this.assignmentTime = assignmentTime;
	}

	/**
	 * @return the trustAccount
	 */
	public Boolean getTrustAccount() {
		return trustAccount;
	}

	/**
	 * @param trustAccount
	 *            the trustAccount to set
	 */
	public void setTrustAccount(Boolean trustAccount) {
		this.trustAccount = trustAccount;
	}

	/**
	 * @return the createuserId
	 */
	public String getCreateuserId() {
		return createuserId;
	}

	/**
	 * @param createuserId
	 *            the createuserId to set
	 */
	public void setCreateuserId(String createuserId) {
		this.createuserId = createuserId;
	}

	/**
	 * @return the createAccount
	 */
	public Boolean getCreateAccount() {
		return createAccount;
	}

	/**
	 * @param createAccount
	 *            the createAccount to set
	 */
	public void setCreateAccount(Boolean createAccount) {
		this.createAccount = createAccount;
	}

	/**
	 * @return the globalPurge
	 */
	public Boolean getGlobalPurge() {
		return globalPurge;
	}

	/**
	 * @param globalPurge the globalPurge to set
	 */
	public void setGlobalPurge(final Boolean globalPurge) {
		this.globalPurge = globalPurge;
	}

	/**
	 * @return the pDelType
	 */
	public String getpDelType() {
		return pDelType;
	}

	/**
	 * @param pDelType the pDelType to set
	 */
	public void setpDelType(final String pDelType) {
		this.pDelType = pDelType;
	}

	/**
	 * @return the lvBookingCnt
	 */
	public Long getLvBookingCnt() {
		return lvBookingCnt;
	}

	/**
	 * @param lvBookingCnt the lvBookingCnt to set
	 */
	public void setLvBookingCnt(final Long lvBookingCnt) {
		this.lvBookingCnt = lvBookingCnt;
	}

	/**
	 * @return the pSealType
	 */
	public String getpSealType() {
		return pSealType;
	}

	/**
	 * @param pSealType the pSealType to set
	 */
	public void setpSealType(final String pSealType) {
		this.pSealType = pSealType;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}



	public String getOffenderFullName() {
		return offenderFullName;
	}

	public void setOffenderFullName(String offenderFullName) {
		this.offenderFullName = offenderFullName;
	}

	
	
	public String getCourtReportFlag() {
		return courtReportFlag;
	}

	public void setCourtReportFlag(String courtReportFlag) {
		this.courtReportFlag = courtReportFlag;
	}

	public String getCourtActionFlag() {
		return courtActionFlag;
	}

	public void setCourtActionFlag(String courtActionFlag) {
		this.courtActionFlag = courtActionFlag;
	}

	public String getCasePlanFlag() {
		return casePlanFlag;
	}

	public void setCasePlanFlag(String casePlanFlag) {
		this.casePlanFlag = casePlanFlag;
	}

	public String getConditionsFlag() {
		return conditionsFlag;
	}

	public void setConditionsFlag(String conditionsFlag) {
		this.conditionsFlag = conditionsFlag;
	}

	public String getTrustFlag() {
		return trustFlag;
	}

	public void setTrustFlag(String trustFlag) {
		this.trustFlag = trustFlag;
	}
	
	public String getGangConflitData() {
		return gangConflitData;
	}

	public void setGangConflitData(String gangConflitData) {
		this.gangConflitData = gangConflitData;
	}

	public String getparentForm() {
		return parentForm;
	}

	public void setparentForm(String moduleName) {
		this.parentForm = moduleName;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getSecondMiddleName() {
		return secondMiddleName;
	}

	public void setSecondMiddleName(String secondMiddleName) {
		this.secondMiddleName = secondMiddleName;
	}

	public BigDecimal getBirthRange() {
		return birthRange;
	}

	public void setBirthRange(BigDecimal birthRange) {
		this.birthRange = birthRange;
	}

	@Override
	public String toString() {
		return "VHeaderBlock [activeFlag=" + activeFlag + ", age=" + age + ", agencyImlId=" + agencyImlId
				+ ", agyLocId=" + agyLocId + ", agyLocType=" + agyLocType + ", aliasOffenderId=" + aliasOffenderId
				+ ", assignedStaffId=" + assignedStaffId + ", birthDate=" + birthDate + ", bookingBeginDate="
				+ bookingBeginDate + ", bookingCreatedDate=" + bookingCreatedDate + ", bookingEndDate=" + bookingEndDate
				+ ", bookingNo=" + bookingNo + ", bookingStatus=" + bookingStatus + ", bookingType=" + bookingType
				+ ", communityActiveFlag=" + communityActiveFlag + ", communityStatus=" + communityStatus
				+ ", createAgyLocId=" + createAgyLocId + ", createIntakeAgyLocId=" + createIntakeAgyLocId
				+ ", disclosureFlag=" + disclosureFlag + ", ethnicity=" + ethnicity + ", firstName=" + firstName
				+ ", gender=" + gender + ", headerStatus=" + headerStatus + ", imageId=" + imageId + ", imageThumbnail="
				+ Arrays.toString(imageThumbnail) + ", inOutStatus=" + inOutStatus + ", intakeAgyLocId="
				+ intakeAgyLocId + ", lastName=" + lastName + ", livUnitDesc=" + livUnitDesc
				+ ", livingUnitDescription=" + livingUnitDescription + ", livingUnitId=" + livingUnitId
				+ ", locationCode=" + locationCode + ", middleName=" + middleName + ", movementReason=" + movementReason
				+ ", offAlerts=" + offAlerts + ", offSupLevel=" + offSupLevel + ", offenderBookId=" + offenderBookId
				+ ", offenderId=" + offenderId + ", offenderIdDisplay=" + offenderIdDisplay + ", officer=" + officer
				+ ", prisonLocation=" + prisonLocation + ", rootOffenderId=" + rootOffenderId + ", status1=" + status1
				+ ", status2=" + status2 + ", status3=" + status3 + ", statusDisplay=" + statusDisplay
				+ ", statusReason=" + statusReason + ", suffix=" + suffix + ", caseLoadId=" + caseLoadId + ", userId="
				+ userId + ", createuserId=" + createuserId + ", createDatetime=" + createDatetime + ", activeDatetime="
				+ activeDatetime + ", nbtAssignReason=" + nbtAssignReason + ", nbtOffenderBookId=" + nbtOffenderBookId
				+ ", nbtNonAssBProceed=" + nbtNonAssBProceed + ", nbtNonAssVProceed=" + nbtNonAssVProceed
				+ ", nbtChkSecBProceed=" + nbtChkSecBProceed + ", nbtChkSecVProceed=" + nbtChkSecVProceed
				+ ", insertedFlag=" + insertedFlag + ", assignmentDate=" + assignmentDate + ", assignmentTime="
				+ assignmentTime + ", trustAccount=" + trustAccount + ", createAccount=" + createAccount
				+ ", globalPurge=" + globalPurge + ", pDelType=" + pDelType + ", lvBookingCnt=" + lvBookingCnt
				+ ", pSealType=" + pSealType + ", sealFlag=" + sealFlag + ", offenderFullName=" + offenderFullName + "]";
	}

}