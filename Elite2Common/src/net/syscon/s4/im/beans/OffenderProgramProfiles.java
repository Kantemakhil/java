package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderProgramProfiles extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offPrgrefId")
	@NotNull
	private Long offPrgrefId;

	@JsonProperty("activity")
	private String activity;

	private String code;

	private String projectCode;
	private String projectDescription;
	private String teamDescription;
	private String directionCode;

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getTeamDescription() {
		return teamDescription;
	}

	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	@JsonProperty("agreedTravelFare")
	private Long agreedTravelFare;
	
	@JsonProperty("agreedTravelFare1")
	private Float agreedTravelFare1;

	public Float getAgreedTravelFare1() {
		return agreedTravelFare1;
	}

	public void setAgreedTravelFare1(Float agreedTravelFare1) {
		this.agreedTravelFare1 = agreedTravelFare1;
	}

	@JsonProperty("agreedTravelHour")
	private BigDecimal agreedTravelHour;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("completionDate")
	private Date completionDate;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("creditOtherHours")
	private Long creditOtherHours;

	@JsonProperty("creditWorkHours")
	private Long creditWorkHours;

	@JsonProperty("crsActyId")
	private Long crsActyId;

	@JsonProperty("earlyEndReason")
	private String earlyEndReason;

	@JsonProperty("holidayFlag")
	private String holidayFlag;

	@JsonProperty("medicalRecordSeq")
	private Long medicalRecordSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("neededFlag")
	private String neededFlag;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("offenderEndCommentText")
	private String offenderEndCommentText;

	@JsonProperty("offenderEndDate")
	private Date offenderEndDate;

	@JsonProperty("offenderEndReason")
	private String offenderEndReason;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("offenderPrgObligationId")
	@NotNull
	private Long offenderPrgObligationId;

	@JsonProperty("offenderProgramStatus")
	@NotNull
	@Size(max = 12)
	private String offenderProgramStatus;

	@JsonProperty("offenderSentConditionId")
	private Long offenderSentConditionId;

	@JsonProperty("offenderStartDate")
	private Date offenderStartDate;

	@JsonProperty("parameter1")
	private String parameter1;

	@JsonProperty("parentOffPrgrefId")
	private Long parentOffPrgrefId;

	@JsonProperty("profileClass")
	private String profileClass;

	@JsonProperty("programId")
	@NotNull
	private Long programId;

	private BigDecimal pProgramId;

	@JsonProperty("programOffPrgrefId")
	private Long programOffPrgrefId;

	@JsonProperty("providerName")
	private String providerName;

	@JsonProperty("programDescription")
	private String programDescription;

	@JsonProperty("referralCommentText")
	private String referralCommentText;

	@JsonProperty("referralDate")
	private Date referralDate;

	@JsonProperty("referralPriority")
	private String referralPriority;

	@JsonProperty("referralStaffId")
	private Long referralStaffId;

	@JsonProperty("rejectDate")
	private Date rejectDate;

	@JsonProperty("rejectReasonCode")
	private String rejectReasonCode;

	@JsonProperty("reviewedBy")
	private String reviewedBy;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;

	@JsonProperty("startSessionNo")
	private Long startSessionNo;

	@JsonProperty("suspendedFlag")
	private String suspendedFlag;

	@JsonProperty("waitlistDecisionCode")
	private String waitlistDecisionCode;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("activityDescription")
	private String activityDescription;

	@JsonProperty("vacancy")
	private String vacancy;

	@JsonProperty("decision")
	private String decision;

	@JsonProperty("rejectReason")
	private String rejectReason;

	@JsonProperty("decisionDate")
	private Date decisionDate;

	@JsonProperty("facilityDescription")
	private String facilityDescription;

	@JsonProperty("internalLocationId")
	private Integer internalLocationId;

	@JsonProperty("offEndReasonVal")
	private String offEndReasonVal;

	@JsonProperty("offEndCommentVal")
	private String offEndCommentVal;

	@JsonProperty("refCommentVal")
	private String refCommentVal;

	@JsonProperty("rejReason")
	private String rejReason;

	@JsonProperty("rejDate")
	private Date rejDate;

	@JsonProperty("scheduleEndDate")
	private Date scheduleEndDate;

	@JsonProperty("scheduleStartDate")
	private Date scheduleStartDate;

	@JsonProperty("allocate")
	private String allocate;

	@JsonProperty("chkActiveIaAllocation")
	private Integer chkActiveIaAllocation;

	@JsonProperty("offEndDate")
	private Date offEndDate;

	@JsonProperty("warningMsg")
	private String warningMsg;
	@JsonProperty("warningPrompt")
	private String warningPrompt;

	private BigDecimal maxCapacity;

	private String view;

	private String phaseDesc;

	private String moduleFlag;

	private String occuranceCode;
	
	private String nbtteamAreaCode;
	
	private String nbtAgyLocId;
	
	private Integer moduleFrom;
	
	private Integer moduleTo;
	
	private String nbtStatus;
	
	
	@JsonProperty("offPrgStatusDbVal")
	private String offPrgStatusDbVal;
	
	@JsonProperty("offendDateDbVal")
	private Date offendDateDbVal;
	
	@JsonProperty("placementRecord")
	private String placementRecord;
		
	@JsonProperty("emailFlag")
	private String emailFlag;
	
	@JsonProperty("smsFlag")
	private String smsFlag;
	
	@JsonProperty("smsScheduleHoursBefore")
	private Integer smsScheduleHoursBefore;
	
	@JsonProperty("emailScheduleHoursBefore")
	private Integer emailScheduleHoursBefore;
	
	@JsonProperty("programLastEventDate")
	private Date programLastEventDate;
	
	private Integer emailAddressCount;
	private Integer phoneNumberCount;
	
	@JsonProperty("statusChangeFlag")
	private String statusChangeFlag;
	
	private Date oldStartDate;	
	
	private String referralPriorityDesc;
	
	public Integer confirmedRecord;
	
	public Integer payflagCount;

	public String getReferralPriorityDesc() {
		return referralPriorityDesc;
	}

	public void setReferralPriorityDesc(String referralPriorityDesc) {
		this.referralPriorityDesc = referralPriorityDesc;
	}

	public Date getProgramLastEventDate() {
		return programLastEventDate;
	}

	public void setProgramLastEventDate(Date programLastEventDate) {
		this.programLastEventDate = programLastEventDate;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public Integer getSmsScheduleHoursBefore() {
		return smsScheduleHoursBefore;
	}

	public void setSmsScheduleHoursBefore(Integer smsScheduleHoursBefore) {
		this.smsScheduleHoursBefore = smsScheduleHoursBefore;
	}

	public Integer getEmailScheduleHoursBefore() {
		return emailScheduleHoursBefore;
	}

	public void setEmailScheduleHoursBefore(Integer emailScheduleHoursBefore) {
		this.emailScheduleHoursBefore = emailScheduleHoursBefore;
	}

	public String getProviderPartyClass() {
		return providerPartyClass;
	}

	public void setProviderPartyClass(String providerPartyClass) {
		this.providerPartyClass = providerPartyClass;
	}

	public String getProviderPartyCode() {
		return providerPartyCode;
	}

	public void setProviderPartyCode(String providerPartyCode) {
		this.providerPartyCode = providerPartyCode;
	}

	public String getAgencyLocationType() {
		return agencyLocationType;
	}

	public void setAgencyLocationType(String agencyLocationType) {
		this.agencyLocationType = agencyLocationType;
	}

	public String getInOutStatus() {
		return inOutStatus;
	}

	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	private String providerPartyClass;

	private String providerPartyCode;
	
	private String agencyLocationType;
	
	private String inOutStatus;
	
	private String toAgyLocId; 
	
	@JsonProperty("nonAssocationByIngAndGang")
	private String nonAssocationByIngAndGang;
	
	public String getNonAssocationByIngAndGang() {
		return nonAssocationByIngAndGang;
	}

	public void setNonAssocationByIngAndGang(String nonAssocationByIngAndGang) {
		this.nonAssocationByIngAndGang = nonAssocationByIngAndGang;
	}

	


	public BigDecimal getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(BigDecimal maxCapacity) {
		this.maxCapacity = maxCapacity;
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

	public String getAllocate() {
		return allocate;
	}

	public void setAllocate(String allocate) {
		this.allocate = allocate;
	}

	public String getFacilityDescription() {
		return facilityDescription;
	}

	public void setFacilityDescription(String facilityDescription) {
		this.facilityDescription = facilityDescription;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Creates new OffenderProgramProfiles class Object
	 */
	public OffenderProgramProfiles() {
		// OffenderProgramProfiles
	}

	/**
	 * @return the offenderSentConditionId
	 */
	public Long getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	/**
	 * @param offenderSentConditionId
	 *            the offenderSentConditionId to set
	 */
	public void setOffenderSentConditionId(Long offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	/**
	 * @return the programOffPrgrefId
	 */
	public Long getProgramOffPrgrefId() {
		return programOffPrgrefId;
	}

	/**
	 * @param programOffPrgrefId
	 *            the programOffPrgrefId to set
	 */
	public void setProgramOffPrgrefId(Long programOffPrgrefId) {
		this.programOffPrgrefId = programOffPrgrefId;
	}

	/**
	 * @return the providerName
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * @param providerName
	 *            the providerName to set
	 */
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	/**
	 * @return the programDescription
	 */
	public String getProgramDescription() {
		return programDescription;
	}

	/**
	 * @param programDescription
	 *            the programDescription to set
	 */
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	/**
	 * @return the offenderPrgObligationId
	 */
	public Long getOffenderPrgObligationId() {
		return offenderPrgObligationId;
	}

	/**
	 * @param offenderPrgObligationId
	 *            the offenderPrgObligationId to set
	 */
	public void setOffenderPrgObligationId(Long offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	/**
	 * @return the offPrgrefId
	 */
	public Long getOffPrgrefId() {
		return offPrgrefId;
	}

	/**
	 * @param offPrgrefId
	 *            the offPrgrefId to set
	 */
	public void setOffPrgrefId(Long offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	/**
	 * @return the agreedTravelFare
	 */
	public Long getAgreedTravelFare() {
		return agreedTravelFare;
	}

	/**
	 * @param agreedTravelFare
	 *            the agreedTravelFare to set
	 */
	public void setAgreedTravelFare(Long agreedTravelFare) {
		this.agreedTravelFare = agreedTravelFare;
	}

	/**
	 * @return the agreedTravelHour
	 */
	public BigDecimal getAgreedTravelHour() {
		return agreedTravelHour;
	}

	/**
	 * @param agreedTravelHour
	 *            the agreedTravelHour to set
	 */
	public void setAgreedTravelHour(BigDecimal agreedTravelHour) {
		this.agreedTravelHour = agreedTravelHour;
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
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param completionDate
	 *            the completionDate to set
	 */
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the creditOtherHours
	 */
	public Long getCreditOtherHours() {
		return creditOtherHours;
	}

	/**
	 * @param creditOtherHours
	 *            the creditOtherHours to set
	 */
	public void setCreditOtherHours(Long creditOtherHours) {
		this.creditOtherHours = creditOtherHours;
	}

	/**
	 * @return the creditWorkHours
	 */
	public Long getCreditWorkHours() {
		return creditWorkHours;
	}

	/**
	 * @param creditWorkHours
	 *            the creditWorkHours to set
	 */
	public void setCreditWorkHours(Long creditWorkHours) {
		this.creditWorkHours = creditWorkHours;
	}

	/**
	 * @return the crsActyId
	 */
	public Long getCrsActyId() {
		return crsActyId;
	}

	/**
	 * @param crsActyId
	 *            the crsActyId to set
	 */
	public void setCrsActyId(Long crsActyId) {
		this.crsActyId = crsActyId;
	}

	/**
	 * @return the earlyEndReason
	 */
	public String getEarlyEndReason() {
		return earlyEndReason;
	}

	/**
	 * @param earlyEndReason
	 *            the earlyEndReason to set
	 */
	public void setEarlyEndReason(String earlyEndReason) {
		this.earlyEndReason = earlyEndReason;
	}

	/**
	 * @return the holidayFlag
	 */
	public String getHolidayFlag() {
		return holidayFlag;
	}

	/**
	 * @param holidayFlag
	 *            the holidayFlag to set
	 */
	public void setHolidayFlag(String holidayFlag) {
		this.holidayFlag = holidayFlag;
	}

	/**
	 * @return the medicalRecordSeq
	 */
	public Long getMedicalRecordSeq() {
		return medicalRecordSeq;
	}

	/**
	 * @param medicalRecordSeq
	 *            the medicalRecordSeq to set
	 */
	public void setMedicalRecordSeq(Long medicalRecordSeq) {
		this.medicalRecordSeq = medicalRecordSeq;
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
	public void setModifyDatetime(Date modifyDatetime) {
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
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the neededFlag
	 */
	public String getNeededFlag() {
		return neededFlag;
	}

	/**
	 * @param neededFlag
	 *            the neededFlag to set
	 */
	public void setNeededFlag(String neededFlag) {
		this.neededFlag = neededFlag;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the offenderEndCommentText
	 */
	public String getOffenderEndCommentText() {
		return offenderEndCommentText;
	}

	/**
	 * @param offenderEndCommentText
	 *            the offenderEndCommentText to set
	 */
	public void setOffenderEndCommentText(String offenderEndCommentText) {
		this.offenderEndCommentText = offenderEndCommentText;
	}

	/**
	 * @return the offenderEndDate
	 */
	public Date getOffenderEndDate() {
		return offenderEndDate;
	}

	/**
	 * @param offenderEndDate
	 *            the offenderEndDate to set
	 */
	public void setOffenderEndDate(Date offenderEndDate) {
		this.offenderEndDate = offenderEndDate;
	}

	/**
	 * @return the offenderEndReason
	 */
	public String getOffenderEndReason() {
		return offenderEndReason;
	}

	/**
	 * @param offenderEndReason
	 *            the offenderEndReason to set
	 */
	public void setOffenderEndReason(String offenderEndReason) {
		this.offenderEndReason = offenderEndReason;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the offenderProgramStatus
	 */
	public String getOffenderProgramStatus() {
		return offenderProgramStatus;
	}

	/**
	 * @param offenderProgramStatus
	 *            the offenderProgramStatus to set
	 */
	public void setOffenderProgramStatus(String offenderProgramStatus) {
		this.offenderProgramStatus = offenderProgramStatus;
	}

	/**
	 * @return the offenderStartDate
	 */
	public Date getOffenderStartDate() {
		return offenderStartDate;
	}

	/**
	 * @param offenderStartDate
	 *            the offenderStartDate to set
	 */
	public void setOffenderStartDate(Date offenderStartDate) {
		this.offenderStartDate = offenderStartDate;
	}

	/**
	 * @return the parameter1
	 */
	public String getParameter1() {
		return parameter1;
	}

	/**
	 * @param parameter1
	 *            the parameter1 to set
	 */
	public void setParameter1(String parameter1) {
		this.parameter1 = parameter1;
	}

	/**
	 * @return the parentOffPrgrefId
	 */
	public Long getParentOffPrgrefId() {
		return parentOffPrgrefId;
	}

	/**
	 * @param parentOffPrgrefId
	 *            the parentOffPrgrefId to set
	 */
	public void setParentOffPrgrefId(Long parentOffPrgrefId) {
		this.parentOffPrgrefId = parentOffPrgrefId;
	}

	/**
	 * @return the profileClass
	 */
	public String getProfileClass() {
		return profileClass;
	}

	/**
	 * @param profileClass
	 *            the profileClass to set
	 */
	public void setProfileClass(String profileClass) {
		this.profileClass = profileClass;
	}

	/**
	 * @return the programId
	 */
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	/**
	 * @return the referralCommentText
	 */
	public String getReferralCommentText() {
		return referralCommentText;
	}

	/**
	 * @param referralCommentText
	 *            the referralCommentText to set
	 */
	public void setReferralCommentText(String referralCommentText) {
		this.referralCommentText = referralCommentText;
	}

	/**
	 * @return the referralDate
	 */
	public Date getReferralDate() {
		return referralDate;
	}

	/**
	 * @param referralDate
	 *            the referralDate to set
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	/**
	 * @return the referralPriority
	 */
	public String getReferralPriority() {
		return referralPriority;
	}

	/**
	 * @param referralPriority
	 *            the referralPriority to set
	 */
	public void setReferralPriority(String referralPriority) {
		this.referralPriority = referralPriority;
	}

	/**
	 * @return the referralStaffId
	 */
	public Long getReferralStaffId() {
		return referralStaffId;
	}

	/**
	 * @param referralStaffId
	 *            the referralStaffId to set
	 */
	public void setReferralStaffId(Long referralStaffId) {
		this.referralStaffId = referralStaffId;
	}

	/**
	 * @return the rejectDate
	 */
	public Date getRejectDate() {
		return rejectDate;
	}

	/**
	 * @param rejectDate
	 *            the rejectDate to set
	 */
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}

	/**
	 * @return the rejectReasonCode
	 */
	public String getRejectReasonCode() {
		return rejectReasonCode;
	}

	/**
	 * @param rejectReasonCode
	 *            the rejectReasonCode to set
	 */
	public void setRejectReasonCode(String rejectReasonCode) {
		this.rejectReasonCode = rejectReasonCode;
	}

	/**
	 * @return the reviewedBy
	 */
	public String getReviewedBy() {
		return reviewedBy;
	}

	/**
	 * @param reviewedBy
	 *            the reviewedBy to set
	 */
	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
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
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the sentenceSeq
	 */
	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @param sentenceSeq
	 *            the sentenceSeq to set
	 */
	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 * @return the startSessionNo
	 */
	public Long getStartSessionNo() {
		return startSessionNo;
	}

	/**
	 * @param startSessionNo
	 *            the startSessionNo to set
	 */
	public void setStartSessionNo(Long startSessionNo) {
		this.startSessionNo = startSessionNo;
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
	public void setSuspendedFlag(String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 * @return the waitlistDecisionCode
	 */
	public String getWaitlistDecisionCode() {
		return waitlistDecisionCode;
	}

	/**
	 * @param waitlistDecisionCode
	 *            the waitlistDecisionCode to set
	 */
	public void setWaitlistDecisionCode(String waitlistDecisionCode) {
		this.waitlistDecisionCode = waitlistDecisionCode;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getVacancy() {
		return vacancy;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getOffEndReasonVal() {
		return offEndReasonVal;
	}

	public void setOffEndReasonVal(String offEndReasonVal) {
		this.offEndReasonVal = offEndReasonVal;
	}

	public String getOffEndCommentVal() {
		return offEndCommentVal;
	}

	public void setOffEndCommentVal(String offEndCommentVal) {
		this.offEndCommentVal = offEndCommentVal;
	}

	public String getRefCommentVal() {
		return refCommentVal;
	}

	public void setRefCommentVal(String refCommentVal) {
		this.refCommentVal = refCommentVal;
	}

	public String getRejReason() {
		return rejReason;
	}

	public void setRejReason(String rejReason) {
		this.rejReason = rejReason;
	}

	public Date getRejDate() {
		return rejDate;
	}

	public void setRejDate(Date rejDate) {
		this.rejDate = rejDate;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Integer getChkActiveIaAllocation() {
		return chkActiveIaAllocation;
	}

	public void setChkActiveIaAllocation(Integer chkActiveIaAllocation) {
		this.chkActiveIaAllocation = chkActiveIaAllocation;
	}

	public Date getOffEndDate() {
		return offEndDate;
	}

	public void setOffEndDate(Date offEndDate) {
		this.offEndDate = offEndDate;
	}

	public BigDecimal getpProgramId() {
		return pProgramId;
	}

	public void setpProgramId(BigDecimal pProgramId) {
		this.pProgramId = pProgramId;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getPhaseDesc() {
		return phaseDesc;
	}

	public void setPhaseDesc(String phaseDesc) {
		this.phaseDesc = phaseDesc;
	}

	public String getModuleFlag() {
		return moduleFlag;
	}

	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	
	public String getOccuranceCode() {
		return occuranceCode;
	}

	public void setOccuranceCode(String occuranceCode) {
		this.occuranceCode = occuranceCode;
	}

	public String getNbtteamAreaCode() {
		return nbtteamAreaCode;
	}

	public void setNbtteamAreaCode(String nbtteamAreaCode) {
		this.nbtteamAreaCode = nbtteamAreaCode;
	}

	public String getNbtAgyLocId() {
		return nbtAgyLocId;
	}

	public void setNbtAgyLocId(String nbtAgyLocId) {
		this.nbtAgyLocId = nbtAgyLocId;
	}

	public Integer getModuleFrom() {
		return moduleFrom;
	}

	public void setModuleFrom(Integer moduleFrom) {
		this.moduleFrom = moduleFrom;
	}

	public Integer getModuleTo() {
		return moduleTo;
	}

	public void setModuleTo(Integer moduleTo) {
		this.moduleTo = moduleTo;
	}

	public String getNbtStatus() {
		return nbtStatus;
	}

	public void setNbtStatus(String nbtStatus) {
		this.nbtStatus = nbtStatus;
	}

	public String getOffPrgStatusDbVal() {
		return offPrgStatusDbVal;
	}

	public void setOffPrgStatusDbVal(String offPrgStatusDbVal) {
		this.offPrgStatusDbVal = offPrgStatusDbVal;
	}

	public Date getOffendDateDbVal() {
		return offendDateDbVal;
	}

	public void setOffendDateDbVal(Date offendDateDbVal) {
		this.offendDateDbVal = offendDateDbVal;
	}

	public String getPlacementRecord() {
		return placementRecord;
	}

	public void setPlacementRecord(String placementRecord) {
		this.placementRecord = placementRecord;
	}

	public Integer getEmailAddressCount() {
		return emailAddressCount;
	}

	public void setEmailAddressCount(Integer emailAddressCount) {
		this.emailAddressCount = emailAddressCount;
	}

	public Integer getPhoneNumberCount() {
		return phoneNumberCount;
	}

	public void setPhoneNumberCount(Integer phoneNumberCount) {
		this.phoneNumberCount = phoneNumberCount;
	}

	public String getStatusChangeFlag() {
		return statusChangeFlag;
	}

	public void setStatusChangeFlag(String statusChangeFlag) {
		this.statusChangeFlag = statusChangeFlag;
	}

	public Date getOldStartDate() {
		return oldStartDate;
	}

	public void setOldStartDate(Date oldStartDate) {
		this.oldStartDate = oldStartDate;
	}
}
