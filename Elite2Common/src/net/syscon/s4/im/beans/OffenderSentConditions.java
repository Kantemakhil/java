package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderSentConditions implements Serializable {
	private static final long serialVersionUID = 1L;

	private long offenderSentConditionId;

	private String activityCode;

	private String activityStatus;

	private String alcoholTreatmentProvider;

	private String appointmentPersonName;

	private String attendanceCentre;

	private String boardOrderFlag;

	private String categoryType;

	private String commConditionCode;

	private String commConditionType;

	private String commentText;

	private String condActType;

	private String conditionAppliedFlag;

	private String conditionRecommendedFlag;

	private String conditionRequiredFlag;

	private String conditionStatus;

	private Object createDatetime;

	private String createUserId;

	private Object curfewEndTime;

	private String curfewProvider;

	private Object curfewStartTime;

	private String curfewTaggingFlag;

	private String detailsText;

	private String drugTesting;

	private String exclusionCode;

	private Object expiryDate;

	private BigDecimal financialTotalAmount;

	private String governorConditionFlag;

	private BigDecimal length;

	private String lengthUnit;

	private BigDecimal listSeq;

	private String longCommentText;

	private String mentalHealthProvider;

	private Object modifyDatetime;

	private String modifyUserId;

	private String noAccessToInternet;

	private BigDecimal noResidentUnderAgeOf;

	private String noUserOfComputer;

	private String noWorkWithUnderAge;

	private BigDecimal noWorkWithUnderAgeOf;

	private String nonAssociatedOffenders;

	private String nonAssociationText;

	private BigDecimal objectId;

	private String objectType;

	private BigDecimal offenderBookId;

	private String otherProgram;

	private String personalRelationshipFlag;

	private BigDecimal programId;

	private String prohibitedContact;

	private Object reportDate;

	private Object reportTime;

	private BigDecimal residencyAddressId;

	private String restrictedApprovalPerson;

	private BigDecimal restrictedChildAgeOf;

	private String reviewCode;

	private String sealFlag;

	private BigDecimal sentenceSeq;

	private Object startDate;

	private Object statusDate;

	private String statusReasonCode;

	private String statusUpdateComment;

	private Object statusUpdateDate;

	private String statusUpdateReason;

	private BigDecimal statusUpdateStaffId;

	private String supervisorName;

	private Object terminationDate;

	private String vehicleDetailsFlag;

	private BigDecimal workflowId;
	
	private String description;
	
	@JsonProperty("parentCondTransferId")
	private BigDecimal parentCondTransferId;
	
	@JsonProperty("conTransferId")
	private BigDecimal conTransferId;
	
	@JsonProperty("teamId")
	private BigDecimal teamId;
	
	@JsonProperty("toTeamId")
	private BigDecimal toTeamId;
	
	@JsonProperty("receivedFromLocation")
	private String receivedFromLocation;
	
	@JsonProperty("receivedFromTeam")
	private BigDecimal receivedFromTeam;
	
	@JsonProperty("assgnTeamCount")
	private BigDecimal assgnTeamCount;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("intakeAgyLocId")
	private String intakeAgyLocId;
	
	@JsonProperty("initialCondition")
	private String initialCondition;

	private BigDecimal defaultAssignedTeam;
	
	public OffenderSentConditions() {
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

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getCurfewEndTime() {
		return this.curfewEndTime;
	}

	public void setCurfewEndTime(Object curfewEndTime) {
		this.curfewEndTime = curfewEndTime;
	}

	public String getCurfewProvider() {
		return this.curfewProvider;
	}

	public void setCurfewProvider(String curfewProvider) {
		this.curfewProvider = curfewProvider;
	}

	public Object getCurfewStartTime() {
		return this.curfewStartTime;
	}

	public void setCurfewStartTime(Object curfewStartTime) {
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

	public Object getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Object expiryDate) {
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

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
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

	public BigDecimal getObjectId() {
		return this.objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
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

	public Object getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Object reportDate) {
		this.reportDate = reportDate;
	}

	public Object getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Object reportTime) {
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

	public Object getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Object startDate) {
		this.startDate = startDate;
	}

	public Object getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Object statusDate) {
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

	public Object getStatusUpdateDate() {
		return this.statusUpdateDate;
	}

	public void setStatusUpdateDate(Object statusUpdateDate) {
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

	public Object getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(Object terminationDate) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getParentCondTransferId() {
		return parentCondTransferId;
	}

	public void setParentCondTransferId(BigDecimal parentCondTransferId) {
		this.parentCondTransferId = parentCondTransferId;
	}

	public BigDecimal getConTransferId() {
		return conTransferId;
	}

	public void setConTransferId(BigDecimal conTransferId) {
		this.conTransferId = conTransferId;
	}

	public BigDecimal getTeamId() {
		return teamId;
	}

	public void setTeamId(BigDecimal teamId) {
		this.teamId = teamId;
	}

	public BigDecimal getToTeamId() {
		return toTeamId;
	}

	public void setToTeamId(BigDecimal toTeamId) {
		this.toTeamId = toTeamId;
	}

	public String getReceivedFromLocation() {
		return receivedFromLocation;
	}

	public void setReceivedFromLocation(String receivedFromLocation) {
		this.receivedFromLocation = receivedFromLocation;
	}

	public BigDecimal getReceivedFromTeam() {
		return receivedFromTeam;
	}

	public void setReceivedFromTeam(BigDecimal receivedFromTeam) {
		this.receivedFromTeam = receivedFromTeam;
	}

	public BigDecimal getAssgnTeamCount() {
		return assgnTeamCount;
	}

	public void setAssgnTeamCount(BigDecimal assgnTeamCount) {
		this.assgnTeamCount = assgnTeamCount;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public String getIntakeAgyLocId() {
		return intakeAgyLocId;
	}

	public void setIntakeAgyLocId(String intakeAgyLocId) {
		this.intakeAgyLocId = intakeAgyLocId;
	}

	public String getInitialCondition() {
		return initialCondition;
	}

	public void setInitialCondition(String initialCondition) {
		this.initialCondition = initialCondition;
	}

	public BigDecimal getDefaultAssignedTeam() {
		return defaultAssignedTeam;
	}

	public void setDefaultAssignedTeam(BigDecimal defaultAssignedTeam) {
		this.defaultAssignedTeam = defaultAssignedTeam;
	}

			
	
}
