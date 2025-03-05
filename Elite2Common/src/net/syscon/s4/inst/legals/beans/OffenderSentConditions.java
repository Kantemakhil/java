package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the OFFENDER_SENT_CONDITIONS database table.
 * 
 */
public class OffenderSentConditions implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderSentConditionId")
	private long offenderSentConditionId;
	@JsonProperty("activityCode")
	private String activityCode;
	@JsonProperty("activityStatus")
	private String activityStatus;
	@JsonProperty("alcoholTreatmentProvider")
	private String alcoholTreatmentProvider;
	@JsonProperty("appointmentPersonName")
	private String appointmentPersonName;
	@JsonProperty("attendanceCentre")
	private String attendanceCentre;
	@JsonProperty("boardOrderFlag")
	private String boardOrderFlag;
	@JsonProperty("categoryType")
	private String categoryType;
	@JsonProperty("commConditionCode")
	private String commConditionCode;
	@JsonProperty("commConditionType")
	private String commConditionType;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("condActType")
	private String condActType;
	@JsonProperty("conditionAppliedFlag")
	private String conditionAppliedFlag;
	@JsonProperty("conditionRecommendedFlag")
	private String conditionRecommendedFlag;
	@JsonProperty("conditionRequiredFlag")
	private String conditionRequiredFlag;
	@JsonProperty("conditionStatus")
	private String conditionStatus;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("curfewEndTime")
	private Date curfewEndTime;
	@JsonProperty("curfewProvider")
	private String curfewProvider;
	@JsonProperty("curfewStartTime")
	private Date curfewStartTime;
	@JsonProperty("curfewTaggingFlag")
	private String curfewTaggingFlag;
	@JsonProperty("detailsText")
	private String detailsText;
	@JsonProperty("drugTesting")
	private String drugTesting;
	@JsonProperty("exclusionCode")
	private String exclusionCode;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("financialTotalAmount")
	private BigDecimal financialTotalAmount;
	@JsonProperty("governorConditionFlag")
	private String governorConditionFlag;
	@JsonProperty("length")
	private BigDecimal length;
	@JsonProperty("lengthUnit")
	private String lengthUnit;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("longCommentText")
	private String longCommentText;
	@JsonProperty("mentalHealthProvider")
	private String mentalHealthProvider;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("noAccessToInternet")
	private String noAccessToInternet;
	@JsonProperty("noResidentUnderAgeOf")
	private BigDecimal noResidentUnderAgeOf;
	@JsonProperty("noUserOfComputer")
	private String noUserOfComputer;
	@JsonProperty("noWorkWithUnderAge")
	private String noWorkWithUnderAge;
	@JsonProperty("noWorkWithUnderAgeOf")
	private BigDecimal noWorkWithUnderAgeOf;
	@JsonProperty("nonAssociatedOffenders")
	private String nonAssociatedOffenders;
	@JsonProperty("nonAssociationText")
	private String nonAssociationText;
	@JsonProperty("DateId")
	private BigDecimal DateId;
	@JsonProperty("DateType")
	private String DateType;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("otherProgram")
	private String otherProgram;
	@JsonProperty("personalRelationshipFlag")
	private String personalRelationshipFlag;
	@JsonProperty("programId")
	private BigDecimal programId;
	@JsonProperty("prohibitedContact")
	private String prohibitedContact;
	@JsonProperty("reportDate")
	private Date reportDate;
	@JsonProperty("reportTime")
	private Date reportTime;
	@JsonProperty("residencyAddressId")
	private BigDecimal residencyAddressId;
	@JsonProperty("restrictedApprovalPerson")
	private String restrictedApprovalPerson;
	@JsonProperty("restrictedChildAgeOf")
	private BigDecimal restrictedChildAgeOf;
	@JsonProperty("reviewCode")
	private String reviewCode;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("sentenceSeq")
	private BigDecimal sentenceSeq;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("statusDate")
	private Date statusDate;
	@JsonProperty("statusReasonCode")
	private String statusReasonCode;
	@JsonProperty("statusUpdateComment")
	private String statusUpdateComment;
	@JsonProperty("statusUpdateDate")
	private Date statusUpdateDate;
	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;
	@JsonProperty("statusUpdateStaffId")
	private BigDecimal statusUpdateStaffId;
	@JsonProperty("supervisorName")
	private String supervisorName;
	@JsonProperty("terminationDate")
	private Date terminationDate;
	@JsonProperty("vehicleDetailsFlag")
	private String vehicleDetailsFlag;
	@JsonProperty("workflowId")
	private BigDecimal workflowId;
	@JsonProperty("provisoFlag")
	private String provisoFlag;
	@JsonProperty("requirement")
	private String requirement;
	@JsonProperty("nbtCondition")
	private String nbtCondition;
	@JsonProperty("nbtConditionStatus")
	private String nbtConditionStatus;
	@JsonProperty("nbtProviso")
	private String nbtProviso;
	@JsonProperty("conditionSuspendedFlag")
	private String conditionSuspendedFlag;
	@JsonProperty("workHours")
	private Long workHours;
	@JsonProperty("conditionText")
	private String conditionText;
	@JsonProperty("nbtRequirement")
	private String nbtRequirement;
	@JsonProperty("nbtProgram")
	private String nbtProgram;
	@JsonProperty("nbtActivity")
	private String nbtActivity;
	@JsonProperty("nbtCurfiewProvider")
	private String nbtCurfiewProvider;
	@JsonProperty("nbtExclusionCode")
	private String nbtExclusionCode;
	@JsonProperty("nbtMentalHealth")
	private String nbtMentalHealth;
	@JsonProperty("nbtAlchohalTreatMentDescription")
	private String nbtAlchohalTreatMentDescription;
	@JsonProperty("nbtAttendenceCenter")
	private String nbtAttendenceCenter;
	@JsonProperty("nbtUnit")
	private String nbtUnit;
	@JsonProperty("nbtCurfiewReviewCode")
	private String nbtCurfiewReviewCode;
	@JsonProperty("nbtStatus")
	private String nbtStatus;
	@JsonProperty("returnValue")
	private Integer returnValue;
	@JsonProperty("serverCode")
	private int serverCode;
	@JsonProperty("program")
	private String program;
	@JsonProperty("objectId")
	private BigDecimal objectId;
	@JsonProperty("objectType")
	private String objectType;
	@JsonProperty("programMethod")
	private String programMethod;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("rootOffenderId")
	private Long rootOffenderId;
	@JsonProperty("caseInfoNumber")
	private String caseInfoNumber;
	@JsonProperty("courseProfilesActs")
	private Integer courseProfilesActs;
	@JsonProperty("appointmentsActs")
	private Integer appointmentsActs;
	@JsonProperty("appointmentsSa")
	private Integer appointmentsSa;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("sentenceSeqNo")
	private String sentenceSeqNo;
	@JsonProperty("allocationFlag")
	private String allocationFlag;
	@JsonProperty("planOfActionFlag")
	private String planOfActionFlag;
	@JsonProperty("commProjAllocFlag")
	private String commProjAllocFlag;
	private String programReferral;
	private String orderNo;
	private String description;
	private String assignedOfficer;
	
	private String orderCategory;
	
	
	
	
	@JsonProperty("offenderProceedingId")
	private BigDecimal offenderProceedingId	;
	
	@JsonProperty("offProceedingCondId")
	private BigDecimal offProceedingCondId;
	
	@JsonProperty("linkFlag")
	private String linkFlag;
	
	@JsonProperty("linkedCount")
	private Integer linkedCount;
	
	@JsonProperty("linkedToOtherProceeding")
	private Integer linkedToOtherProceeding;
	
	@JsonProperty("orderOperations")
	private String orderOperations;
	
	public String getNbtProviso() {
		return nbtProviso;
	}

	public void setNbtProviso(String nbtProviso) {
		this.nbtProviso = nbtProviso;
	}

	public String getNbtConditionStatus() {
		return nbtConditionStatus;
	}

	public void setNbtConditionStatus(String nbtConditionStatus) {
		this.nbtConditionStatus = nbtConditionStatus;
	}

	public String getNbtCondition() {
		return nbtCondition;
	}

	public void setNbtCondition(String nbtCondition) {
		this.nbtCondition = nbtCondition;
	}

	public OffenderSentConditions() {
		// OffenderSentConditions
	}

	public long getOffenderSentConditionId() {
		return this.offenderSentConditionId;
	}

	public void setOffenderSentConditionId(long offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityStatus() {
		return this.activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getAlcoholTreatmentProvider() {
		return this.alcoholTreatmentProvider;
	}

	public void setAlcoholTreatmentProvider(String alcoholTreatmentProvider) {
		this.alcoholTreatmentProvider = alcoholTreatmentProvider;
	}

	public String getAppointmentPersonName() {
		return this.appointmentPersonName;
	}

	public void setAppointmentPersonName(String appointmentPersonName) {
		this.appointmentPersonName = appointmentPersonName;
	}

	public String getAttendanceCentre() {
		return this.attendanceCentre;
	}

	public void setAttendanceCentre(String attendanceCentre) {
		this.attendanceCentre = attendanceCentre;
	}

	public String getBoardOrderFlag() {
		return this.boardOrderFlag;
	}

	public void setBoardOrderFlag(String boardOrderFlag) {
		this.boardOrderFlag = boardOrderFlag;
	}

	public String getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCommConditionCode() {
		return this.commConditionCode;
	}

	public void setCommConditionCode(String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}

	public String getCommConditionType() {
		return this.commConditionType;
	}

	public void setCommConditionType(String commConditionType) {
		this.commConditionType = commConditionType;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCondActType() {
		return this.condActType;
	}

	public void setCondActType(String condActType) {
		this.condActType = condActType;
	}

	public String getConditionAppliedFlag() {
		return this.conditionAppliedFlag;
	}

	public void setConditionAppliedFlag(String conditionAppliedFlag) {
		this.conditionAppliedFlag = conditionAppliedFlag;
	}

	public String getConditionRecommendedFlag() {
		return this.conditionRecommendedFlag;
	}

	public void setConditionRecommendedFlag(String conditionRecommendedFlag) {
		this.conditionRecommendedFlag = conditionRecommendedFlag;
	}

	public String getConditionRequiredFlag() {
		return this.conditionRequiredFlag;
	}

	public void setConditionRequiredFlag(String conditionRequiredFlag) {
		this.conditionRequiredFlag = conditionRequiredFlag;
	}

	public String getConditionStatus() {
		return this.conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCurfewEndTime() {
		return this.curfewEndTime;
	}

	public void setCurfewEndTime(Date curfewEndTime) {
		this.curfewEndTime = curfewEndTime;
	}

	public String getCurfewProvider() {
		return this.curfewProvider;
	}

	public void setCurfewProvider(String curfewProvider) {
		this.curfewProvider = curfewProvider;
	}

	public Date getCurfewStartTime() {
		return this.curfewStartTime;
	}

	public void setCurfewStartTime(Date curfewStartTime) {
		this.curfewStartTime = curfewStartTime;
	}

	public String getCurfewTaggingFlag() {
		return this.curfewTaggingFlag;
	}

	public void setCurfewTaggingFlag(String curfewTaggingFlag) {
		this.curfewTaggingFlag = curfewTaggingFlag;
	}

	public String getDetailsText() {
		return this.detailsText;
	}

	public void setDetailsText(String detailsText) {
		this.detailsText = detailsText;
	}

	public String getDrugTesting() {
		return this.drugTesting;
	}

	public void setDrugTesting(String drugTesting) {
		this.drugTesting = drugTesting;
	}

	public String getExclusionCode() {
		return this.exclusionCode;
	}

	public void setExclusionCode(String exclusionCode) {
		this.exclusionCode = exclusionCode;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getFinancialTotalAmount() {
		return this.financialTotalAmount;
	}

	public void setFinancialTotalAmount(BigDecimal financialTotalAmount) {
		this.financialTotalAmount = financialTotalAmount;
	}

	public String getGovernorConditionFlag() {
		return this.governorConditionFlag;
	}

	public void setGovernorConditionFlag(String governorConditionFlag) {
		this.governorConditionFlag = governorConditionFlag;
	}

	public BigDecimal getLength() {
		return this.length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getLengthUnit() {
		return this.lengthUnit;
	}

	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public String getLongCommentText() {
		return this.longCommentText;
	}

	public void setLongCommentText(String longCommentText) {
		this.longCommentText = longCommentText;
	}

	public String getMentalHealthProvider() {
		return this.mentalHealthProvider;
	}

	public void setMentalHealthProvider(String mentalHealthProvider) {
		this.mentalHealthProvider = mentalHealthProvider;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNoAccessToInternet() {
		return this.noAccessToInternet;
	}

	public void setNoAccessToInternet(String noAccessToInternet) {
		this.noAccessToInternet = noAccessToInternet;
	}

	public BigDecimal getNoResidentUnderAgeOf() {
		return this.noResidentUnderAgeOf;
	}

	public void setNoResidentUnderAgeOf(BigDecimal noResidentUnderAgeOf) {
		this.noResidentUnderAgeOf = noResidentUnderAgeOf;
	}

	public String getNoUserOfComputer() {
		return this.noUserOfComputer;
	}

	public void setNoUserOfComputer(String noUserOfComputer) {
		this.noUserOfComputer = noUserOfComputer;
	}

	public String getNoWorkWithUnderAge() {
		return this.noWorkWithUnderAge;
	}

	public void setNoWorkWithUnderAge(String noWorkWithUnderAge) {
		this.noWorkWithUnderAge = noWorkWithUnderAge;
	}

	public BigDecimal getNoWorkWithUnderAgeOf() {
		return this.noWorkWithUnderAgeOf;
	}

	public void setNoWorkWithUnderAgeOf(BigDecimal noWorkWithUnderAgeOf) {
		this.noWorkWithUnderAgeOf = noWorkWithUnderAgeOf;
	}

	public String getNonAssociatedOffenders() {
		return this.nonAssociatedOffenders;
	}

	public void setNonAssociatedOffenders(String nonAssociatedOffenders) {
		this.nonAssociatedOffenders = nonAssociatedOffenders;
	}

	public String getNonAssociationText() {
		return this.nonAssociationText;
	}

	public void setNonAssociationText(String nonAssociationText) {
		this.nonAssociationText = nonAssociationText;
	}

	public BigDecimal getDateId() {
		return this.DateId;
	}

	public void setDateId(BigDecimal DateId) {
		this.DateId = DateId;
	}

	public String getDateType() {
		return this.DateType;
	}

	public void setDateType(String DateType) {
		this.DateType = DateType;
	}

	public long getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOtherProgram() {
		return this.otherProgram;
	}

	public void setOtherProgram(String otherProgram) {
		this.otherProgram = otherProgram;
	}

	public String getPersonalRelationshipFlag() {
		return this.personalRelationshipFlag;
	}

	public void setPersonalRelationshipFlag(String personalRelationshipFlag) {
		this.personalRelationshipFlag = personalRelationshipFlag;
	}

	public BigDecimal getProgramId() {
		return this.programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public String getProhibitedContact() {
		return this.prohibitedContact;
	}

	public void setProhibitedContact(String prohibitedContact) {
		this.prohibitedContact = prohibitedContact;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public BigDecimal getResidencyAddressId() {
		return this.residencyAddressId;
	}

	public void setResidencyAddressId(BigDecimal residencyAddressId) {
		this.residencyAddressId = residencyAddressId;
	}

	public String getRestrictedApprovalPerson() {
		return this.restrictedApprovalPerson;
	}

	public void setRestrictedApprovalPerson(String restrictedApprovalPerson) {
		this.restrictedApprovalPerson = restrictedApprovalPerson;
	}

	public BigDecimal getRestrictedChildAgeOf() {
		return this.restrictedChildAgeOf;
	}

	public void setRestrictedChildAgeOf(BigDecimal restrictedChildAgeOf) {
		this.restrictedChildAgeOf = restrictedChildAgeOf;
	}

	public String getReviewCode() {
		return this.reviewCode;
	}

	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getSentenceSeq() {
		return this.sentenceSeq;
	}

	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatusReasonCode() {
		return this.statusReasonCode;
	}

	public void setStatusReasonCode(String statusReasonCode) {
		this.statusReasonCode = statusReasonCode;
	}

	public String getStatusUpdateComment() {
		return this.statusUpdateComment;
	}

	public void setStatusUpdateComment(String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	public Date getStatusUpdateDate() {
		return this.statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public String getStatusUpdateReason() {
		return this.statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public BigDecimal getStatusUpdateStaffId() {
		return this.statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(BigDecimal statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public String getSupervisorName() {
		return this.supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public Date getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getVehicleDetailsFlag() {
		return this.vehicleDetailsFlag;
	}

	public void setVehicleDetailsFlag(String vehicleDetailsFlag) {
		this.vehicleDetailsFlag = vehicleDetailsFlag;
	}

	public BigDecimal getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(BigDecimal workflowId) {
		this.workflowId = workflowId;
	}

	public String getProvisoFlag() {
		return provisoFlag;
	}

	public void setProvisoFlag(String provisoFlag) {
		this.provisoFlag = provisoFlag;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getConditionSuspendedFlag() {
		return conditionSuspendedFlag;
	}

	public void setConditionSuspendedFlag(String conditionSuspendedFlag) {
		this.conditionSuspendedFlag = conditionSuspendedFlag;
	}

	public Long getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Long workHours) {
		this.workHours = workHours;
	}

	public String getConditionText() {
		return conditionText;
	}

	public void setConditionText(String conditionText) {
		this.conditionText = conditionText;
	}

	public String getNbtRequirement() {
		return nbtRequirement;
	}

	public void setNbtRequirement(String nbtRequirement) {
		this.nbtRequirement = nbtRequirement;
	}

	public String getNbtProgram() {
		return nbtProgram;
	}

	public void setNbtProgram(String nbtProgram) {
		this.nbtProgram = nbtProgram;
	}

	public String getNbtActivity() {
		return nbtActivity;
	}

	public void setNbtActivity(String nbtActivity) {
		this.nbtActivity = nbtActivity;
	}

	public String getNbtCurfiewProvider() {
		return nbtCurfiewProvider;
	}

	public void setNbtCurfiewProvider(String nbtCurfiewProvider) {
		this.nbtCurfiewProvider = nbtCurfiewProvider;
	}

	public String getNbtExclusionCode() {
		return nbtExclusionCode;
	}

	public void setNbtExclusionCode(String nbtExclusionCode) {
		this.nbtExclusionCode = nbtExclusionCode;
	}

	public String getNbtMentalHealth() {
		return nbtMentalHealth;
	}

	public void setNbtMentalHealth(String nbtMentalHealth) {
		this.nbtMentalHealth = nbtMentalHealth;
	}

	public String getNbtAlchohalTreatMentDescription() {
		return nbtAlchohalTreatMentDescription;
	}

	public void setNbtAlchohalTreatMentDescription(String nbtAlchohalTreatMentDescription) {
		this.nbtAlchohalTreatMentDescription = nbtAlchohalTreatMentDescription;
	}

	public String getNbtAttendenceCenter() {
		return nbtAttendenceCenter;
	}

	public void setNbtAttendenceCenter(String nbtAttendenceCenter) {
		this.nbtAttendenceCenter = nbtAttendenceCenter;
	}

	public String getNbtUnit() {
		return nbtUnit;
	}

	public void setNbtUnit(String nbtUnit) {
		this.nbtUnit = nbtUnit;
	}

	public String getNbtCurfiewReviewCode() {
		return nbtCurfiewReviewCode;
	}

	public void setNbtCurfiewReviewCode(String nbtCurfiewReviewCode) {
		this.nbtCurfiewReviewCode = nbtCurfiewReviewCode;
	}

	public String getNbtStatus() {
		return nbtStatus;
	}

	public void setNbtStatus(String nbtStatus) {
		this.nbtStatus = nbtStatus;
	}

	public Integer getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(Integer returnValue) {
		this.returnValue = returnValue;
	}

	public int getServerCode() {
		return serverCode;
	}

	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public BigDecimal getObjectId() {
		return objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getProgramMethod() {
		return programMethod;
	}

	public void setProgramMethod(String programMethod) {
		this.programMethod = programMethod;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Long getRootOffenderId() {
		return rootOffenderId;
	}

	public void setRootOffenderId(Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public Integer getCourseProfilesActs() {
		return courseProfilesActs;
	}

	public void setCourseProfilesActs(Integer courseProfilesActs) {
		this.courseProfilesActs = courseProfilesActs;
	}

	public Integer getAppointmentsActs() {
		return appointmentsActs;
	}

	public void setAppointmentsActs(Integer appointmentsActs) {
		this.appointmentsActs = appointmentsActs;
	}

	public Integer getAppointmentsSa() {
		return appointmentsSa;
	}

	public void setAppointmentsSa(Integer appointmentsSa) {
		this.appointmentsSa = appointmentsSa;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getSentenceSeqNo() {
		return sentenceSeqNo;
	}

	public void setSentenceSeqNo(String sentenceSeqNo) {
		this.sentenceSeqNo = sentenceSeqNo;
	}

	public String getAllocationFlag() {
		return allocationFlag;
	}

	public void setAllocationFlag(String allocationFlag) {
		this.allocationFlag = allocationFlag;
	}

	public String getPlanOfActionFlag() {
		return planOfActionFlag;
	}

	public void setPlanOfActionFlag(String planOfActionFlag) {
		this.planOfActionFlag = planOfActionFlag;
	}

	public String getCommProjAllocFlag() {
		return commProjAllocFlag;
	}

	public void setCommProjAllocFlag(String commProjAllocFlag) {
		this.commProjAllocFlag = commProjAllocFlag;
	}

	public String getProgramReferral() {
		return programReferral;
	}

	public void setProgramReferral(String programReferral) {
		this.programReferral = programReferral;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignedOfficer() {
		return assignedOfficer;
	}

	public void setAssignedOfficer(String assignedOfficer) {
		this.assignedOfficer = assignedOfficer;
	}

	public BigDecimal getOffenderProceedingId() {
		return offenderProceedingId;
	}

	public void setOffenderProceedingId(BigDecimal offenderProceedingId) {
		this.offenderProceedingId = offenderProceedingId;
	}

	public BigDecimal getOffProceedingCondId() {
		return offProceedingCondId;
	}

	public void setOffProceedingCondId(BigDecimal offProceedingCondId) {
		this.offProceedingCondId = offProceedingCondId;
	}

	public String getLinkFlag() {
		return linkFlag;
	}

	public void setLinkFlag(String linkFlag) {
		this.linkFlag = linkFlag;
	}

	public Integer getLinkedCount() {
		return linkedCount;
	}

	public Integer getLinkedToOtherProceeding() {
		return linkedToOtherProceeding;
	}

	public void setLinkedCount(Integer linkedCount) {
		this.linkedCount = linkedCount;
	}

	public void setLinkedToOtherProceeding(Integer linkedToOtherProceeding) {
		this.linkedToOtherProceeding = linkedToOtherProceeding;
	}

	public String getOrderOperations() {
		return orderOperations;
	}

	public void setOrderOperations(String orderOperations) {
		this.orderOperations = orderOperations;
	}

	public String getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(String orderCategory) {
		this.orderCategory = orderCategory;
	}

	
	
	


}
