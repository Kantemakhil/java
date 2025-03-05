package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.FieldMatch;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.Offenders;

@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "bookingCreatedDate", second = "bookingEndDate", message = "bookingCreatedDate.should.not.be.greater.than.bookingEndDate")
@GlobalValidation(message = "atleast.one.mandatory")
@SuppressWarnings("PMD.ExcessivePublicCount")
public class OffenderBookings extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("offenderId")
	@NotNull
	private BigDecimal offenderId;

	@JsonProperty("activeFlag")
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("activityDate")
	private Date activityDate;

	@JsonProperty("agencyImlId")
	private BigDecimal agencyImlId;

	@JsonProperty("agyLocId")
	@Size(max = 6)
	private String agyLocId;

	@JsonProperty("agyLocIdList")
	@Size(max = 80)
	private String agyLocIdList;

	@JsonProperty("assignedStaffId")
	private BigDecimal assignedStaffId;

	@JsonProperty("bookingBeginDate")
	@NotNull
	private Date bookingBeginDate;

	@JsonProperty("bookingCreatedDate")
	private Date bookingCreatedDate;

	@JsonProperty("bookingEndDate")
	private Date bookingEndDate;

	@JsonProperty("bookingNo")
	@Size(max = 14)
	private String bookingNo;

	@JsonProperty("bookingStatus")
	@Size(max = 12)
	private String bookingStatus;

	@JsonProperty("bookingType")
	@Size(max = 12)
	private String bookingType;

	@JsonProperty("caseDate")
	private Date caseDate;

	@JsonProperty("caseOfficerId")
	private BigDecimal caseOfficerId;

	@JsonProperty("caseTime")
	private Date caseTime;

	@JsonProperty("commStaffId")
	private BigDecimal commStaffId;

	@JsonProperty("commStaffRole")
	@Size(max = 12)
	private String commStaffRole;

	@JsonProperty("commStatus")
	private String commStatus;
	
	@JsonProperty("cgnbtBookingStatus")
	private String cgnbtBookingStatus;
	

	@JsonProperty("communityActiveFlag")
	@Size(max = 1)
	private String communityActiveFlag;

	@JsonProperty("communityAgyLocId")
	@Size(max = 6)
	private String communityAgyLocId;

	@JsonProperty("createAgyLocId")
	@Size(max = 6)
	private String createAgyLocId;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createIntakeAgyLocId")
	@Size(max = 6)
	private String createIntakeAgyLocId;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;
	
	@JsonProperty("dspFirstName")
	private String dspFirstName;
	
	@JsonProperty("disclosureFlag")
	@NotNull
	@Size(max = 1)
	private String disclosureFlag;

	@JsonProperty("earnedCreditLevel")
	@Size(max = 12)
	private String earnedCreditLevel;

	@JsonProperty("ekstrandCreditLevel")
	@Size(max = 12)
	private String ekstrandCreditLevel;

	@JsonProperty("fingerPrintedStaffId")
	private BigDecimal fingerPrintedStaffId;

	@JsonProperty("inOutStatus")
	@NotNull
	@Size(max = 12)
	private String inOutStatus;

	@JsonProperty("intakeAgyLocAssignDate")
	private Date intakeAgyLocAssignDate;

	@JsonProperty("intakeAgyLocId")
	@Size(max = 6)
	private String intakeAgyLocId;

	@JsonProperty("intakeCaseloadId")
	@Size(max = 6)
	private String intakeCaseloadId;

	@JsonProperty("intakeUserId")
	@Size(max = 32)
	private String intakeUserId;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("noCommAgyLocId")
	private BigDecimal noCommAgyLocId;

	@JsonProperty("photoTakingStaffId")
	private BigDecimal photoTakingStaffId;

	@JsonProperty("recordUserId")
	@Size(max = 30)
	private String recordUserId;

	@JsonProperty("requestName")
	@Size(max = 240)
	private String requestName;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("searchStaffId")
	private BigDecimal searchStaffId;

	@JsonProperty("serviceFeeFlag")
	@Size(max = 1)
	private String serviceFeeFlag;

	@JsonProperty("statusReason")
	@Size(max = 32)
	private String statusReason;
	
	@JsonProperty("livUnitDesc")
	private String livUnitDesc;

	@JsonProperty("totalUnexcusedAbsences")
	private BigDecimal totalUnexcusedAbsences;

	@JsonProperty("youthAdultCode")
	@NotNull
	@Size(max = 12)
	private String youthAdultCode;

	@JsonProperty("offenderActivityEvents")
	private List<OffenderActivityEvent> offenderActivityEvents;

	@JsonProperty("offenderAlerts")
	private List<OffenderAlert> offenderAlerts;

	@JsonProperty("offenderAssessments")
	private List<OffenderAssessments> offenderAssessments;

	@JsonProperty("offenderAssets")
	private List<OffenderAsset> offenderAssets;

	@JsonProperty("offenderAssociatedParties")
	private List<OffenderAssociatedParty> offenderAssociatedParties;

	@JsonProperty("offenderAuthorisedVisitors1")
	private List<OffenderAuthorisedVisitor> offenderAuthorisedVisitors1;

	@JsonProperty("offenderAuthorisedVisitors2")
	private List<OffenderAuthorisedVisitor> offenderAuthorisedVisitors2;

	@JsonProperty("offenderBailDetails")
	private List<OffenderBailDetail> offenderBailDetails;

	@JsonProperty("offenders")
	private Offenders offenders;

	@JsonProperty("offenderBookingAgyLocs")
	private List<OffenderBookingAgyLocs> offenderBookingAgyLocs;

	@JsonProperty("offenderBookingDetail")
	private OffenderBookingDetail offenderBookingDetail;

	@JsonProperty("offenderBookingEvents")
	private List<OffenderBookingEvent> offenderBookingEvents;

	@JsonProperty("offenderCases")
	private List<OffenderCas> offenderCases;

	@JsonProperty("offenderCaseNotes")
	private List<OffenderCaseNote> offenderCaseNotes;

	@JsonProperty("offenderCharges")
	private List<OffenderCharge> offenderCharges;

	@JsonProperty("offenderCipDetails")
	private List<OffenderCipDetail> offenderCipDetails;

	@JsonProperty("offenderClassPrograms")
	private List<OffenderClassProgram> offenderClassPrograms;

	@JsonProperty("offenderClothes")
	private List<OffenderClothe> offenderClothes;

	@JsonProperty("offenderClothingBundles")
	private List<OffenderClothingBundle> offenderClothingBundles;

	@JsonProperty("offenderClothingIssues")
	private List<OffenderClothingIssue> offenderClothingIssues;

	@JsonProperty("offenderClothingItems")
	private List<OffenderClothingItem> offenderClothingItems;

	@JsonProperty("offenderCodefendants1")
	private List<OffenderCodefendant> offenderCodefendants1;

	@JsonProperty("offenderCodefendants2")
	private List<OffenderCodefendant> offenderCodefendants2;

	@JsonProperty("offenderComments")
	private List<OffenderComment> offenderComments;

	@JsonProperty("offenderCommunityConditions")
	private List<OffenderCommunityCondition> offenderCommunityConditions;

	@JsonProperty("offenderCommSupHistories")
	private List<OffenderCommSupHistory> offenderCommSupHistories;

	@JsonProperty("offenderCompactAgreements")
	private List<OffenderCompactAgreement> offenderCompactAgreements;

	@JsonProperty("offenderContactPersons")
	private List<OffenderContactPerson> offenderContactPersons;

	@JsonProperty("offenderCourseAttendances")
	private List<OffenderCourseAttendance> offenderCourseAttendances;

	@JsonProperty("offenderCurfews")
	private List<OffenderCurfews> offenderCurfews;

	@JsonProperty("offenderDrugAdmissions")
	private List<OffenderDrugAdmission> offenderDrugAdmissions;

	@JsonProperty("offenderDrugSamples")
	private List<OffenderDrugSample> offenderDrugSamples;

	@JsonProperty("offenderEducations")
	private List<OffenderEducation> offenderEducations;

	@JsonProperty("offenderEmployments")
	private List<OffenderEmployment> offenderEmployments;

	@JsonProperty("offenderEscapes")
	private List<OffenderEscape> offenderEscapes;

	@JsonProperty("offenderExpenses")
	private List<OffenderExpens> offenderExpenses;

	@JsonProperty("offenderExternalMovements")
	private List<OffenderExternalMovement> offenderExternalMovements;

	@JsonProperty("offenderFileLocations")
	private List<OffenderFileLocation> offenderFileLocations;

	@JsonProperty("offenderGangAffiliations")
	private List<OffenderGangAffiliation> offenderGangAffiliations;

	@JsonProperty("offenderGangEvidences")
	private List<OffenderGangEvidence> offenderGangEvidences;

	@JsonProperty("offenderGangInvests")
	private List<OffenderGangInvest> offenderGangInvests;

	@JsonProperty("offenderGrievances")
	private List<OffenderGrievance> offenderGrievances;

	@JsonProperty("offenderHospitalVisits")
	private List<OffenderHospitalVisit> offenderHospitalVisits;

	@JsonProperty("offenderHwds")
	private List<OffenderHwd> offenderHwds;

	@JsonProperty("offenderIdentifyingMarks")
	private List<OffenderIdentifyingMark> offenderIdentifyingMarks;

	@JsonProperty("offenderIepLevels")
	private List<OffenderIepLevel> offenderIepLevels;

	@JsonProperty("offenderImages")
	private List<OffenderImage> offenderImages;

	@JsonProperty("offenderImprisonStatuses")
	private List<OffenderImprisonStatus> offenderImprisonStatuses;

	@JsonProperty("offenderIncomes")
	private List<OffenderIncome> offenderIncomes;

	@JsonProperty("offenderIndSchedules")
	private List<OffenderIndSchedule> offenderIndSchedules;

	@JsonProperty("domain")
	private String domain;

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("intakeStatus")
	private String intakeStatus;
	
	@JsonProperty("instStatus")
	private String instStatus;
	
	
	
	@JsonProperty("dspLastName")
	private String dspLastName;

	
	@JsonProperty("dspDescription")
	private String dspDescription;
	
	@JsonProperty("trustAccountFlag")
	private String trustAccountFlag;
	
	
	
	
	
	@JsonProperty("position")
	private String position;
	@JsonProperty("pOffIdDisp")
	private String pOffIdDisp;
	@JsonProperty("staffId")
	private Long staffId;
	@JsonProperty("staffIdFlag")
	private String staffIdFlag;
	
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("warningMsg")
	private String warningMsg;
	
	@JsonProperty("warningPrompt")
	private String warningPrompt;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("prisonStatus")
	private String prisonStatus;
	

	@JsonProperty("agencyLocationType")
	private String agencyLocationType;
	
	
	@JsonProperty("offenderEndDate")
	private Date offenderEndDate;
	
	@JsonProperty("offenderStartDate")
	private Date offenderStartDate;
	
	@JsonProperty("referralDate")
	private Date referralDate;
	
	@JsonProperty("conditionStartDate")
	private Date conditionStartDate;
	
   @JsonProperty("conditionEndDate")
   private Date conditionEndDate;
	
   @JsonProperty("conditionLength")
   private String conditionLength	;

	@JsonProperty("offenderSentConditionId")
	private BigDecimal offenderSentConditionId	;
	
	@JsonProperty("weekday")
	private List<String> weekday;
	
	@JsonProperty("remainingHours")
   private BigDecimal remainingHours;
	
	@JsonProperty("length")
	private BigDecimal length;
	
	@JsonProperty("sentenceSeq")
	private BigDecimal sentenceSeq	;

	private BigDecimal creditedUnits	;
	@JsonProperty("notification")
	private String notification;
	
	@JsonProperty("offenderPrgObligationId")
	private Long offenderPrgObligationId;
	
	@JsonProperty("systemGeneratedStaff")
	private String systemGeneratedStaff;
	
	private String iepLevel;
	
	@JsonProperty("toOffRootId")
	private Long toOffRootId;
	
	@JsonProperty("fromOffRootId")
	private Long fromOffRootId;
	
	@JsonProperty("fromOffenderBookId")
	private Long fromOffenderBookId;
	
	@JsonProperty("toOffenderBookId")
	private Long toOffenderBookId;
	
	@JsonProperty("lastName")
	private String lastName;
	
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("workloadUnits")
	private Integer workloadUnits;
	
	@JsonProperty("nsType")
	private String nsType;
	
	@JsonProperty("workedStaffMembers")
	private List<Integer> workedStaffMembers;
	
	@JsonProperty("intCorrelationId")
	private Long intCorrelationId;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("newBookingFlag")
	private String newBookingFlag;
	
	public BigDecimal getCreditedUnits() {
		return creditedUnits;
	}

	public void setCreditedUnits(BigDecimal creditedUnits) {
		this.creditedUnits = creditedUnits;
	}

	public String getAgencyLocationType() {
		return agencyLocationType;
	}

	public void setAgencyLocationType(String agencyLocationType) {
		this.agencyLocationType = agencyLocationType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrisonStatus() {
		return prisonStatus;
	}

	public void setPrisonStatus(String prisonStatus) {
		this.prisonStatus = prisonStatus;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(final String position) {
		this.position = position;
	}

	public String getTrustAccountFlag() {
		return trustAccountFlag;
	}

	public void setTrustAccountFlag(final String trustAccountFlag) {
		this.trustAccountFlag = trustAccountFlag;
	}

	/**
	 * Creates new OffenderAlertsCommitBean class Object
	 */
	public OffenderBookings() {
		// OffenderBookings
	}

	/**
	 *
	 * @return
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
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
	public Date getActivityDate() {
		return activityDate;
	}

	/**
	 *
	 * @param activityDate
	 */
	public void setActivityDate(final Date activityDate) {
		this.activityDate = activityDate;
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
	public String getAgyLocIdList() {
		return agyLocIdList;
	}

	/**
	 *
	 * @param agyLocIdList
	 */
	public void setAgyLocIdList(final String agyLocIdList) {
		this.agyLocIdList = agyLocIdList;
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
	public Date getCaseDate() {
		return caseDate;
	}

	/**
	 *
	 * @param caseDate
	 */
	public void setCaseDate(final Date caseDate) {
		this.caseDate = caseDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCaseOfficerId() {
		return caseOfficerId;
	}

	/**
	 *
	 * @param caseOfficerId
	 */
	public void setCaseOfficerId(final BigDecimal caseOfficerId) {
		this.caseOfficerId = caseOfficerId;
	}

	/**
	 *
	 * @return
	 */
	public Date getCaseTime() {
		return caseTime;
	}

	/**
	 *
	 * @param caseTime
	 */
	public void setCaseTime(final Date caseTime) {
		this.caseTime = caseTime;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCommStaffId() {
		return commStaffId;
	}

	/**
	 *
	 * @param commStaffId
	 */
	public void setCommStaffId(final BigDecimal commStaffId) {
		this.commStaffId = commStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getCommStaffRole() {
		return commStaffRole;
	}

	/**
	 *
	 * @param commStaffRole
	 */
	public void setCommStaffRole(final String commStaffRole) {
		this.commStaffRole = commStaffRole;
	}

	/**
	 *
	 * @return
	 */
	public String getCommStatus() {
		return commStatus;
	}

	/**
	 *
	 * @param commStatus
	 */
	public void setCommStatus(final String commStatus) {
		this.commStatus = commStatus;
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
	public String getCommunityAgyLocId() {
		return communityAgyLocId;
	}

	/**
	 *
	 * @param communityAgyLocId
	 */
	public void setCommunityAgyLocId(final String communityAgyLocId) {
		this.communityAgyLocId = communityAgyLocId;
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
	public String getEarnedCreditLevel() {
		return earnedCreditLevel;
	}

	/**
	 *
	 * @param earnedCreditLevel
	 */
	public void setEarnedCreditLevel(final String earnedCreditLevel) {
		this.earnedCreditLevel = earnedCreditLevel;
	}

	/**
	 *
	 * @return
	 */
	public String getEkstrandCreditLevel() {
		return ekstrandCreditLevel;
	}

	/**
	 *
	 * @param ekstrandCreditLevel
	 */
	public void setEkstrandCreditLevel(final String ekstrandCreditLevel) {
		this.ekstrandCreditLevel = ekstrandCreditLevel;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getFingerPrintedStaffId() {
		return fingerPrintedStaffId;
	}

	/**
	 *
	 * @param fingerPrintedStaffId
	 */
	public void setFingerPrintedStaffId(final BigDecimal fingerPrintedStaffId) {
		this.fingerPrintedStaffId = fingerPrintedStaffId;
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
	public Date getIntakeAgyLocAssignDate() {
		return intakeAgyLocAssignDate;
	}

	/**
	 *
	 * @param intakeAgyLocAssignDate
	 */
	public void setIntakeAgyLocAssignDate(final Date intakeAgyLocAssignDate) {
		this.intakeAgyLocAssignDate = intakeAgyLocAssignDate;
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
	 * @return the cgnbtBookingStatus
	 */
	public String getCgnbtBookingStatus() {
		return cgnbtBookingStatus;
	}

	/**
	 * @param cgnbtBookingStatus the cgnbtBookingStatus to set
	 */
	public void setCgnbtBookingStatus(final String cgnbtBookingStatus) {
		this.cgnbtBookingStatus = cgnbtBookingStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getIntakeCaseloadId() {
		return intakeCaseloadId;
	}

	/**
	 *
	 * @param intakeCaseloadId
	 */
	public void setIntakeCaseloadId(final String intakeCaseloadId) {
		this.intakeCaseloadId = intakeCaseloadId;
	}

	/**
	 *
	 * @return
	 */
	public String getIntakeUserId() {
		return intakeUserId;
	}

	/**
	 *
	 * @param intakeUserId
	 */
	public void setIntakeUserId(final String intakeUserId) {
		this.intakeUserId = intakeUserId;
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
	public BigDecimal getNoCommAgyLocId() {
		return noCommAgyLocId;
	}

	/**
	 *
	 * @param noCommAgyLocId
	 */
	public void setNoCommAgyLocId(final BigDecimal noCommAgyLocId) {
		this.noCommAgyLocId = noCommAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getPhotoTakingStaffId() {
		return photoTakingStaffId;
	}

	/**
	 *
	 * @param photoTakingStaffId
	 */
	public void setPhotoTakingStaffId(final BigDecimal photoTakingStaffId) {
		this.photoTakingStaffId = photoTakingStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getRecordUserId() {
		return recordUserId;
	}

	/**
	 *
	 * @param recordUserId
	 */
	public void setRecordUserId(final String recordUserId) {
		this.recordUserId = recordUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getRequestName() {
		return requestName;
	}

	/**
	 *
	 * @param requestName
	 */
	public void setRequestName(final String requestName) {
		this.requestName = requestName;
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
	public BigDecimal getSearchStaffId() {
		return searchStaffId;
	}

	/**
	 *
	 * @param searchStaffId
	 */
	public void setSearchStaffId(final BigDecimal searchStaffId) {
		this.searchStaffId = searchStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getServiceFeeFlag() {
		return serviceFeeFlag;
	}

	/**
	 *
	 * @param serviceFeeFlag
	 */
	public void setServiceFeeFlag(final String serviceFeeFlag) {
		this.serviceFeeFlag = serviceFeeFlag;
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
	public BigDecimal getTotalUnexcusedAbsences() {
		return totalUnexcusedAbsences;
	}

	/**
	 *
	 * @param totalUnexcusedAbsences
	 */
	public void setTotalUnexcusedAbsences(final BigDecimal totalUnexcusedAbsences) {
		this.totalUnexcusedAbsences = totalUnexcusedAbsences;
	}

	/**
	 *
	 * @return
	 */
	public String getYouthAdultCode() {
		return youthAdultCode;
	}

	/**
	 *
	 * @param youthAdultCode
	 */
	public void setYouthAdultCode(final String youthAdultCode) {
		this.youthAdultCode = youthAdultCode;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderActivityEvent> getOffenderActivityEvents() {
		return offenderActivityEvents;
	}

	/**
	 *
	 * @param offenderActivityEvents
	 */
	public void setOffenderActivityEvents(final List<OffenderActivityEvent> offenderActivityEvents) {
		this.offenderActivityEvents = offenderActivityEvents;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAlert> getOffenderAlerts() {
		return offenderAlerts;
	}

	/**
	 *
	 * @param offenderAlerts
	 */
	public void setOffenderAlerts(final List<OffenderAlert> offenderAlerts) {
		this.offenderAlerts = offenderAlerts;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAssessments> getOffenderAssessments() {
		return offenderAssessments;
	}

	/**
	 *
	 * @param offenderAssessments
	 */
	public void setOffenderAssessments(final List<OffenderAssessments> offenderAssessments) {
		this.offenderAssessments = offenderAssessments;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAsset> getOffenderAssets() {
		return offenderAssets;
	}

	/**
	 *
	 * @param offenderAssets
	 */
	public void setOffenderAssets(final List<OffenderAsset> offenderAssets) {
		this.offenderAssets = offenderAssets;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAssociatedParty> getOffenderAssociatedParties() {
		return offenderAssociatedParties;
	}

	/**
	 *
	 * @param offenderAssociatedParties
	 */
	public void setOffenderAssociatedParties(final List<OffenderAssociatedParty> offenderAssociatedParties) {
		this.offenderAssociatedParties = offenderAssociatedParties;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAuthorisedVisitor> getOffenderAuthorisedVisitors1() {
		return offenderAuthorisedVisitors1;
	}

	/**
	 *
	 * @param offenderAuthorisedVisitors1
	 */
	public void setOffenderAuthorisedVisitors1(final List<OffenderAuthorisedVisitor> offenderAuthorisedVisitors1) {
		this.offenderAuthorisedVisitors1 = offenderAuthorisedVisitors1;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAuthorisedVisitor> getOffenderAuthorisedVisitors2() {
		return offenderAuthorisedVisitors2;
	}

	/**
	 *
	 * @param offenderAuthorisedVisitors2
	 */
	public void setOffenderAuthorisedVisitors2(final List<OffenderAuthorisedVisitor> offenderAuthorisedVisitors2) {
		this.offenderAuthorisedVisitors2 = offenderAuthorisedVisitors2;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderBailDetail> getOffenderBailDetails() {
		return offenderBailDetails;
	}

	/**
	 *
	 * @param offenderBailDetails
	 */
	public void setOffenderBailDetails(final List<OffenderBailDetail> offenderBailDetails) {
		this.offenderBailDetails = offenderBailDetails;
	}

	/**
	 * @return the offenders
	 */
	public Offenders getOffenders() {
		return offenders;
	}

	/**
	 * @param offenders
	 *            the offenders to set
	 */
	public void setOffenders(final Offenders offenders) {
		this.offenders = offenders;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderBookingAgyLocs> getOffenderBookingAgyLocs() {
		return offenderBookingAgyLocs;
	}

	/**
	 *
	 * @param offenderBookingAgyLocs
	 */
	public void setOffenderBookingAgyLocs(final List<OffenderBookingAgyLocs> offenderBookingAgyLocs) {
		this.offenderBookingAgyLocs = offenderBookingAgyLocs;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookingDetail getOffenderBookingDetail() {
		return offenderBookingDetail;
	}

	/**
	 *
	 * @param offenderBookingDetail
	 */
	public void setOffenderBookingDetail(final OffenderBookingDetail offenderBookingDetail) {
		this.offenderBookingDetail = offenderBookingDetail;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderBookingEvent> getOffenderBookingEvents() {
		return offenderBookingEvents;
	}

	/**
	 *
	 * @param offenderBookingEvents
	 */
	public void setOffenderBookingEvents(final List<OffenderBookingEvent> offenderBookingEvents) {
		this.offenderBookingEvents = offenderBookingEvents;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCas> getOffenderCases() {
		return offenderCases;
	}

	/**
	 *
	 * @param offenderCases
	 */
	public void setOffenderCases(final List<OffenderCas> offenderCases) {
		this.offenderCases = offenderCases;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCaseNote> getOffenderCaseNotes() {
		return offenderCaseNotes;
	}

	/**
	 *
	 * @param offenderCaseNotes
	 */
	public void setOffenderCaseNotes(final List<OffenderCaseNote> offenderCaseNotes) {
		this.offenderCaseNotes = offenderCaseNotes;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCharge> getOffenderCharges() {
		return offenderCharges;
	}

	/**
	 *
	 * @param offenderCharges
	 */
	public void setOffenderCharges(final List<OffenderCharge> offenderCharges) {
		this.offenderCharges = offenderCharges;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCipDetail> getOffenderCipDetails() {
		return offenderCipDetails;
	}

	/**
	 *
	 * @param offenderCipDetails
	 */
	public void setOffenderCipDetails(final List<OffenderCipDetail> offenderCipDetails) {
		this.offenderCipDetails = offenderCipDetails;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderClassProgram> getOffenderClassPrograms() {
		return offenderClassPrograms;
	}

	/**
	 *
	 * @param offenderClassPrograms
	 */
	public void setOffenderClassPrograms(final List<OffenderClassProgram> offenderClassPrograms) {
		this.offenderClassPrograms = offenderClassPrograms;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderClothe> getOffenderClothes() {
		return offenderClothes;
	}

	/**
	 *
	 * @param offenderClothes
	 */
	public void setOffenderClothes(final List<OffenderClothe> offenderClothes) {
		this.offenderClothes = offenderClothes;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderClothingBundle> getOffenderClothingBundles() {
		return offenderClothingBundles;
	}

	/**
	 *
	 * @param offenderClothingBundles
	 */
	public void setOffenderClothingBundles(final List<OffenderClothingBundle> offenderClothingBundles) {
		this.offenderClothingBundles = offenderClothingBundles;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderClothingIssue> getOffenderClothingIssues() {
		return offenderClothingIssues;
	}

	/**
	 *
	 * @param offenderClothingIssues
	 */
	public void setOffenderClothingIssues(final List<OffenderClothingIssue> offenderClothingIssues) {
		this.offenderClothingIssues = offenderClothingIssues;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderClothingItem> getOffenderClothingItems() {
		return offenderClothingItems;
	}

	/**
	 *
	 * @param offenderClothingItems
	 */
	public void setOffenderClothingItems(final List<OffenderClothingItem> offenderClothingItems) {
		this.offenderClothingItems = offenderClothingItems;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCodefendant> getOffenderCodefendants1() {
		return offenderCodefendants1;
	}

	/**
	 *
	 * @param offenderCodefendants1
	 */
	public void setOffenderCodefendants1(final List<OffenderCodefendant> offenderCodefendants1) {
		this.offenderCodefendants1 = offenderCodefendants1;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCodefendant> getOffenderCodefendants2() {
		return offenderCodefendants2;
	}

	/**
	 *
	 * @param offenderCodefendants2
	 */
	public void setOffenderCodefendants2(final List<OffenderCodefendant> offenderCodefendants2) {
		this.offenderCodefendants2 = offenderCodefendants2;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderComment> getOffenderComments() {
		return offenderComments;
	}

	/**
	 *
	 * @param offenderComments
	 */
	public void setOffenderComments(final List<OffenderComment> offenderComments) {
		this.offenderComments = offenderComments;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCommunityCondition> getOffenderCommunityConditions() {
		return offenderCommunityConditions;
	}

	/**
	 *
	 * @param offenderCommunityConditions
	 */
	public void setOffenderCommunityConditions(final List<OffenderCommunityCondition> offenderCommunityConditions) {
		this.offenderCommunityConditions = offenderCommunityConditions;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCommSupHistory> getOffenderCommSupHistories() {
		return offenderCommSupHistories;
	}

	/**
	 *
	 * @param offenderCommSupHistories
	 */
	public void setOffenderCommSupHistories(final List<OffenderCommSupHistory> offenderCommSupHistories) {
		this.offenderCommSupHistories = offenderCommSupHistories;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCompactAgreement> getOffenderCompactAgreements() {
		return offenderCompactAgreements;
	}

	/**
	 *
	 * @param offenderCompactAgreements
	 */
	public void setOffenderCompactAgreements(final List<OffenderCompactAgreement> offenderCompactAgreements) {
		this.offenderCompactAgreements = offenderCompactAgreements;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderContactPerson> getOffenderContactPersons() {
		return offenderContactPersons;
	}

	/**
	 *
	 * @param offenderContactPersons
	 */
	public void setOffenderContactPersons(final List<OffenderContactPerson> offenderContactPersons) {
		this.offenderContactPersons = offenderContactPersons;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCourseAttendance> getOffenderCourseAttendances() {
		return offenderCourseAttendances;
	}

	/**
	 *
	 * @param offenderCourseAttendances
	 */
	public void setOffenderCourseAttendances(final List<OffenderCourseAttendance> offenderCourseAttendances) {
		this.offenderCourseAttendances = offenderCourseAttendances;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCurfews> getOffenderCurfews() {
		return offenderCurfews;
	}

	/**
	 *
	 * @param offenderCurfews
	 */
	public void setOffenderCurfews(final List<OffenderCurfews> offenderCurfews) {
		this.offenderCurfews = offenderCurfews;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderDrugAdmission> getOffenderDrugAdmissions() {
		return offenderDrugAdmissions;
	}

	/**
	 *
	 * @param offenderDrugAdmissions
	 */
	public void setOffenderDrugAdmissions(final List<OffenderDrugAdmission> offenderDrugAdmissions) {
		this.offenderDrugAdmissions = offenderDrugAdmissions;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderDrugSample> getOffenderDrugSamples() {
		return offenderDrugSamples;
	}

	/**
	 *
	 * @param offenderDrugSamples
	 */
	public void setOffenderDrugSamples(final List<OffenderDrugSample> offenderDrugSamples) {
		this.offenderDrugSamples = offenderDrugSamples;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderEducation> getOffenderEducations() {
		return offenderEducations;
	}

	/**
	 *
	 * @param offenderEducations
	 */
	public void setOffenderEducations(final List<OffenderEducation> offenderEducations) {
		this.offenderEducations = offenderEducations;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderEmployment> getOffenderEmployments() {
		return offenderEmployments;
	}

	/**
	 *
	 * @param offenderEmployments
	 */
	public void setOffenderEmployments(final List<OffenderEmployment> offenderEmployments) {
		this.offenderEmployments = offenderEmployments;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderEscape> getOffenderEscapes() {
		return offenderEscapes;
	}

	/**
	 *
	 * @param offenderEscapes
	 */
	public void setOffenderEscapes(final List<OffenderEscape> offenderEscapes) {
		this.offenderEscapes = offenderEscapes;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderExpens> getOffenderExpenses() {
		return offenderExpenses;
	}

	/**
	 *
	 * @param offenderExpenses
	 */
	public void setOffenderExpenses(final List<OffenderExpens> offenderExpenses) {
		this.offenderExpenses = offenderExpenses;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderExternalMovement> getOffenderExternalMovements() {
		return offenderExternalMovements;
	}

	/**
	 *
	 * @param offenderExternalMovements
	 */
	public void setOffenderExternalMovements(final List<OffenderExternalMovement> offenderExternalMovements) {
		this.offenderExternalMovements = offenderExternalMovements;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderFileLocation> getOffenderFileLocations() {
		return offenderFileLocations;
	}

	/**
	 *
	 * @param offenderFileLocations
	 */
	public void setOffenderFileLocations(final List<OffenderFileLocation> offenderFileLocations) {
		this.offenderFileLocations = offenderFileLocations;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderGangAffiliation> getOffenderGangAffiliations() {
		return offenderGangAffiliations;
	}

	/**
	 *
	 * @param offenderGangAffiliations
	 */
	public void setOffenderGangAffiliations(final List<OffenderGangAffiliation> offenderGangAffiliations) {
		this.offenderGangAffiliations = offenderGangAffiliations;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderGangEvidence> getOffenderGangEvidences() {
		return offenderGangEvidences;
	}

	/**
	 *
	 * @param offenderGangEvidences
	 */
	public void setOffenderGangEvidences(final List<OffenderGangEvidence> offenderGangEvidences) {
		this.offenderGangEvidences = offenderGangEvidences;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderGangInvest> getOffenderGangInvests() {
		return offenderGangInvests;
	}

	/**
	 *
	 * @param offenderGangInvests
	 */
	public void setOffenderGangInvests(final List<OffenderGangInvest> offenderGangInvests) {
		this.offenderGangInvests = offenderGangInvests;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderGrievance> getOffenderGrievances() {
		return offenderGrievances;
	}

	/**
	 *
	 * @param offenderGrievances
	 */
	public void setOffenderGrievances(final List<OffenderGrievance> offenderGrievances) {
		this.offenderGrievances = offenderGrievances;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderHospitalVisit> getOffenderHospitalVisits() {
		return offenderHospitalVisits;
	}

	/**
	 *
	 * @param offenderHospitalVisits
	 */
	public void setOffenderHospitalVisits(final List<OffenderHospitalVisit> offenderHospitalVisits) {
		this.offenderHospitalVisits = offenderHospitalVisits;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderHwd> getOffenderHwds() {
		return offenderHwds;
	}

	/**
	 *
	 * @param offenderHwds
	 */
	public void setOffenderHwds(final List<OffenderHwd> offenderHwds) {
		this.offenderHwds = offenderHwds;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderIdentifyingMark> getOffenderIdentifyingMarks() {
		return offenderIdentifyingMarks;
	}

	/**
	 *
	 * @param offenderIdentifyingMarks
	 */
	public void setOffenderIdentifyingMarks(final List<OffenderIdentifyingMark> offenderIdentifyingMarks) {
		this.offenderIdentifyingMarks = offenderIdentifyingMarks;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderIepLevel> getOffenderIepLevels() {
		return offenderIepLevels;
	}

	/**
	 *
	 * @param offenderIepLevels
	 */
	public void setOffenderIepLevels(final List<OffenderIepLevel> offenderIepLevels) {
		this.offenderIepLevels = offenderIepLevels;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderImage> getOffenderImages() {
		return offenderImages;
	}

	/**
	 *
	 * @param offenderImages
	 */
	public void setOffenderImages(final List<OffenderImage> offenderImages) {
		this.offenderImages = offenderImages;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderImprisonStatus> getOffenderImprisonStatuses() {
		return offenderImprisonStatuses;
	}

	/**
	 *
	 * @param offenderImprisonStatuses
	 */
	public void setOffenderImprisonStatuses(final List<OffenderImprisonStatus> offenderImprisonStatuses) {
		this.offenderImprisonStatuses = offenderImprisonStatuses;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderIncome> getOffenderIncomes() {
		return offenderIncomes;
	}

	/**
	 *
	 * @param offenderIncomes
	 */
	public void setOffenderIncomes(final List<OffenderIncome> offenderIncomes) {
		this.offenderIncomes = offenderIncomes;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderIndSchedule> getOffenderIndSchedules() {
		return offenderIndSchedules;
	}

	/**
	 *
	 * @param offenderIndSchedules
	 */
	public void setOffenderIndSchedules(final List<OffenderIndSchedule> offenderIndSchedules) {
		this.offenderIndSchedules = offenderIndSchedules;
	}

	public String getDomain() {
		return domain;
	}

	/**
	 *
	 * @param domain
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	/**
	 *
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 *
	 * @param code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the dspFirstName
	 */
	public String getDspFirstName() {
		return dspFirstName;
	}

	/**
	 * @param dspFirstName the dspFirstName to set
	 */
	public void setDspFirstName(final String dspFirstName) {
		this.dspFirstName = dspFirstName;
	}

	/**
	 * @return the livUnitDesc
	 */
	public String getLivUnitDesc() {
		return livUnitDesc;
	}

	/**
	 * @param livUnitDesc the livUnitDesc to set
	 */
	public void setLivUnitDesc(final String livUnitDesc) {
		this.livUnitDesc = livUnitDesc;
	}
	/**
	 * @return the intakeStatus
	 */
	public String getIntakeStatus() {
		return intakeStatus;
	}
	/**
	 * @param intakeStatus the intakeStatus to set
	 */
	public void setIntakeStatus(final String intakeStatus) {
		this.intakeStatus = intakeStatus;
	}

	/**
	 * @return the instStatus
	 */
	public String getInstStatus() {
		return instStatus;
	}

	/**
	 * @param instStatus the instStatus to set
	 */
	public void setInstStatus(final String instStatus) {
		this.instStatus = instStatus;
	}

	

	/**
	 * @return the dspLastName
	 */
	public String getDspLastName() {
		return dspLastName;
	}

	/**
	 * @param dspLastName the dspLastName to set
	 */
	public void setDspLastName(final String dspLastName) {
		this.dspLastName = dspLastName;
	}

	

	/**
	 * @return the dspDescription
	 */
	public String getDspDescription() {
		return dspDescription;
	}

	/**
	 * @param dspDescription the dspDescription to set
	 */
	public void setDspDescription(final String dspDescription) {
		this.dspDescription = dspDescription;
	}

	/**
	 * @return the pOffIdDisp
	 */
	public String getpOffIdDisp() {
		return pOffIdDisp;
	}

	/**
	 * @param pOffIdDisp the pOffIdDisp to set
	 */
	public void setpOffIdDisp(final String pOffIdDisp) {
		this.pOffIdDisp = pOffIdDisp;
	}

	/**
	 * @return the staffId
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(final Long staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the staffIdFlag
	 */
	public String getStaffIdFlag() {
		return staffIdFlag;
	}

	/**
	 * @param staffIdFlag the staffIdFlag to set
	 */
	public void setStaffIdFlag(final String staffIdFlag) {
		this.staffIdFlag = staffIdFlag;
	}

	@Override
	public String toString() {
		return "OffenderBookings [offenderBookId=" + offenderBookId + ", offenderId=" + offenderId + ", activeFlag="
				+ activeFlag + ", activityDate=" + activityDate + ", agencyImlId=" + agencyImlId + ", agyLocId="
				+ agyLocId + ", agyLocIdList=" + agyLocIdList + ", assignedStaffId=" + assignedStaffId
				+ ", bookingBeginDate=" + bookingBeginDate + ", bookingCreatedDate=" + bookingCreatedDate
				+ ", bookingEndDate=" + bookingEndDate + ", bookingNo=" + bookingNo + ", bookingStatus=" + bookingStatus
				+ ", bookingType=" + bookingType + ", caseDate=" + caseDate + ", caseOfficerId=" + caseOfficerId
				+ ", caseTime=" + caseTime + ", commStaffId=" + commStaffId + ", commStaffRole=" + commStaffRole
				+ ", commStatus=" + commStatus + ", cgnbtBookingStatus=" + cgnbtBookingStatus + ", communityActiveFlag="
				+ communityActiveFlag + ", communityAgyLocId=" + communityAgyLocId + ", createAgyLocId="
				+ createAgyLocId + ", createDatetime=" + createDatetime + ", createIntakeAgyLocId="
				+ createIntakeAgyLocId + ", createUserId=" + createUserId + ", dspFirstName=" + dspFirstName
				+ ", disclosureFlag=" + disclosureFlag + ", earnedCreditLevel=" + earnedCreditLevel
				+ ", ekstrandCreditLevel=" + ekstrandCreditLevel + ", fingerPrintedStaffId=" + fingerPrintedStaffId
				+ ", inOutStatus=" + inOutStatus + ", intakeAgyLocAssignDate=" + intakeAgyLocAssignDate
				+ ", intakeAgyLocId=" + intakeAgyLocId + ", intakeCaseloadId=" + intakeCaseloadId + ", intakeUserId="
				+ intakeUserId + ", livingUnitId=" + livingUnitId + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", noCommAgyLocId=" + noCommAgyLocId + ", photoTakingStaffId="
				+ photoTakingStaffId + ", recordUserId=" + recordUserId + ", requestName=" + requestName
				+ ", rootOffenderId=" + rootOffenderId + ", sealFlag=" + sealFlag + ", searchStaffId=" + searchStaffId
				+ ", serviceFeeFlag=" + serviceFeeFlag + ", statusReason=" + statusReason + ", livUnitDesc="
				+ livUnitDesc + ", totalUnexcusedAbsences=" + totalUnexcusedAbsences + ", youthAdultCode="
				+ youthAdultCode + ", offenderActivityEvents=" + offenderActivityEvents + ", offenderAlerts="
				+ offenderAlerts + ", offenderAssessments=" + offenderAssessments + ", offenderAssets=" + offenderAssets
				+ ", offenderAssociatedParties=" + offenderAssociatedParties + ", offenderAuthorisedVisitors1="
				+ offenderAuthorisedVisitors1 + ", offenderAuthorisedVisitors2=" + offenderAuthorisedVisitors2
				+ ", offenderBailDetails=" + offenderBailDetails + ", offenders=" + offenders
				+ ", offenderBookingAgyLocs=" + offenderBookingAgyLocs + ", offenderBookingDetail="
				+ offenderBookingDetail + ", offenderBookingEvents=" + offenderBookingEvents + ", offenderCases="
				+ offenderCases + ", offenderCaseNotes=" + offenderCaseNotes + ", offenderCharges=" + offenderCharges
				+ ", offenderCipDetails=" + offenderCipDetails + ", offenderClassPrograms=" + offenderClassPrograms
				+ ", offenderClothes=" + offenderClothes + ", offenderClothingBundles=" + offenderClothingBundles
				+ ", offenderClothingIssues=" + offenderClothingIssues + ", offenderClothingItems="
				+ offenderClothingItems + ", offenderCodefendants1=" + offenderCodefendants1
				+ ", offenderCodefendants2=" + offenderCodefendants2 + ", offenderComments=" + offenderComments
				+ ", offenderCommunityConditions=" + offenderCommunityConditions + ", offenderCommSupHistories="
				+ offenderCommSupHistories + ", offenderCompactAgreements=" + offenderCompactAgreements
				+ ", offenderContactPersons=" + offenderContactPersons + ", offenderCourseAttendances="
				+ offenderCourseAttendances + ", offenderCurfews=" + offenderCurfews + ", offenderDrugAdmissions="
				+ offenderDrugAdmissions + ", offenderDrugSamples=" + offenderDrugSamples + ", offenderEducations="
				+ offenderEducations + ", offenderEmployments=" + offenderEmployments + ", offenderEscapes="
				+ offenderEscapes + ", offenderExpenses=" + offenderExpenses + ", offenderExternalMovements="
				+ offenderExternalMovements + ", offenderFileLocations=" + offenderFileLocations
				+ ", offenderGangAffiliations=" + offenderGangAffiliations + ", offenderGangEvidences="
				+ offenderGangEvidences + ", offenderGangInvests=" + offenderGangInvests + ", offenderGrievances="
				+ offenderGrievances + ", offenderHospitalVisits=" + offenderHospitalVisits + ", offenderHwds="
				+ offenderHwds + ", offenderIdentifyingMarks=" + offenderIdentifyingMarks + ", offenderIepLevels="
				+ offenderIepLevels + ", offenderImages=" + offenderImages + ", offenderImprisonStatuses="
				+ offenderImprisonStatuses + ", offenderIncomes=" + offenderIncomes + ", offenderIndSchedules="
				+ offenderIndSchedules + ", domain=" + domain + ", code=" + code + ", intakeStatus=" + intakeStatus
				+ ", instStatus=" + instStatus + ", dspLastName=" + dspLastName + ", dspDescription=" + dspDescription
				+ ", trustAccountFlag=" + trustAccountFlag + ", position=" + position + ", pOffIdDisp=" + pOffIdDisp
				+ ", staffId=" + staffId + ", staffIdFlag=" + staffIdFlag + ", offenderIdDisplay=" + offenderIdDisplay
				+ "]";
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getWarningPrompt() {
		return warningPrompt;
	}

	public void setWarningPrompt(String warningPrompt) {
		this.warningPrompt = warningPrompt;
	}

	public Date getOffenderEndDate() {
		return offenderEndDate;
	}

	public void setOffenderEndDate(Date offenderEndDate) {
		this.offenderEndDate = offenderEndDate;
	}

	public Date getOffenderStartDate() {
		return offenderStartDate;
	}

	public void setOffenderStartDate(Date offenderStartDate) {
		this.offenderStartDate = offenderStartDate;
	}

	public Date getReferralDate() {
		return referralDate;
	}

	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	public Date getConditionStartDate() {
		return conditionStartDate;
	}

	public void setConditionStartDate(Date conditionStartDate) {
		this.conditionStartDate = conditionStartDate;
	}

	public Date getConditionEndDate() {
		return conditionEndDate;
	}

	public void setConditionEndDate(Date conditionEndDate) {
		this.conditionEndDate = conditionEndDate;
	}

	public String getConditionLength() {
		return conditionLength;
	}

	public void setConditionLength(String conditionLength) {
		this.conditionLength = conditionLength;
	}

	public BigDecimal getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public List<String> getWeekday() {
		return weekday;
	}

	public void setWeekday(List<String> weekday) {
		this.weekday = weekday;
	}

	public BigDecimal getRemainingHours() {
		return remainingHours;
	}

	public void setRemainingHours(BigDecimal remainingHours) {
		this.remainingHours = remainingHours;
	}

	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	
	private String lengthUnit	;



	public BigDecimal getLength() {
		return length;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public Long getOffenderPrgObligationId() {
		return offenderPrgObligationId;
	}

	public void setOffenderPrgObligationId(Long offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	public String getSystemGeneratedStaff() {
		return systemGeneratedStaff;
	}

	public void setSystemGeneratedStaff(String systemGeneratedStaff) {
		this.systemGeneratedStaff = systemGeneratedStaff;
	}

	public String getIepLevel() {
		return iepLevel;
	}

	public void setIepLevel(String iepLevel) {
		this.iepLevel = iepLevel;
	}

	public Long getToOffRootId() {
		return toOffRootId;
	}

	public void setToOffRootId(Long toOffRootId) {
		this.toOffRootId = toOffRootId;
	}

	public Long getFromOffRootId() {
		return fromOffRootId;
	}

	public void setFromOffRootId(Long fromOffRootId) {
		this.fromOffRootId = fromOffRootId;
	}

	public Long getFromOffenderBookId() {
		return fromOffenderBookId;
	}

	public void setFromOffenderBookId(Long fromOffenderBookId) {
		this.fromOffenderBookId = fromOffenderBookId;
	}

	public Long getToOffenderBookId() {
		return toOffenderBookId;
	}

	public void setToOffenderBookId(Long toOffenderBookId) {
		this.toOffenderBookId = toOffenderBookId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getWorkloadUnits() {
		return workloadUnits;
	}

	public void setWorkloadUnits(Integer workloadUnits) {
		this.workloadUnits = workloadUnits;
	}

	public String getNsType() {
		return nsType;
	}

	public void setNsType(String nsType) {
		this.nsType = nsType;
	}

	public List<Integer> getWorkedStaffMembers() {
		return workedStaffMembers;
	}

	public void setWorkedStaffMembers(List<Integer> workedStaffMembers) {
		this.workedStaffMembers = workedStaffMembers;
	}

	public Long getIntCorrelationId() {
		return intCorrelationId;
	}

	public void setIntCorrelationId(Long intCorrelationId) {
		this.intCorrelationId = intCorrelationId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getNewBookingFlag() {
		return newBookingFlag;
	}

	public void setNewBookingFlag(String newBookingFlag) {
		this.newBookingFlag = newBookingFlag;
	}

}