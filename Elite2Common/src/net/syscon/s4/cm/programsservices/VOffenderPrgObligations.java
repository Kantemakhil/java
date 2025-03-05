package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.Offenders;

/**
 * The persistent class for the V_OFFENDER_PRG_OBLIGATIONS database table.
 * 
 */
public class VOffenderPrgObligations implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String activityCode;

	private String activityDesc;

	private BigDecimal age;

	private String availabilityCode;

	private String availabilityDesc;

	private String caseInfoNumber;

	private String categoryType;

	private BigDecimal checkSum;

	private String commConditionCode;

	private String commConditionType;

	private String commentText;

	private String communityActiveFlag;

	private String courtAgyLocId;

	private BigDecimal courtEventId;

	private String courtName;

	private String creditedUnits;

	private Date endDate;

	private String eventSubType;

	private String eventType;

	private String firstName;

	private String lastName;

	private Date legalEndDate;

	private BigDecimal length;

	private String lengthUnit;

	private String obligationSource;

	private String obligationSourceDesc;

	private String offenceTypes;

	private String offenderAgyLocId;

	private BigDecimal offenderBookId;

	private String offenderCommunityAgyLocId;

	private BigDecimal offenderId;

	private String offenderIdDisplay;

	private String offenderName;

	private BigDecimal offenderPrgObligationId;

	private BigDecimal offenderSentCondActId;

	private BigDecimal offenderSentConditionId;

	private BigDecimal orderId;

	private String programCategory;

	private BigDecimal programId;

	private String programLength;

	private String raceCode;

	private String raceDesc;

	private Date referralDate;

	private String riskCode;

	private String sentenceCalcType;

	private String sentenceCategory;

	private String sentenceConditionDesc;

	private String sentenceDesc;

	private Date sentenceEndDate;

	private BigDecimal sentenceSeq;

	private Date sentenceStartDate;

	private String sentenceStatus;

	private String serviceObligationCode;

	private String sexCode;

	private String sexDesc;

	private String specialNeedFlag;

	private Date startDate;

	private String status;

	private Date statusChangeDate;

	private String statusChangeReason;
	
	private String conflictMsg;
	
	@JsonProperty("nonAssocationByIngAndGang")
	private String nonAssocationByIngAndGang;
	
	public String getNonAssocationByIngAndGang() {
		return nonAssocationByIngAndGang;
	}

	public void setNonAssocationByIngAndGang(String nonAssocationByIngAndGang) {
		this.nonAssocationByIngAndGang = nonAssocationByIngAndGang;
	}


	@JsonProperty("offenderNonAssociationsByInd")
	private List<Offenders> offenderNonAssociationsByInd;
	
	@JsonProperty("offenderNonAssociationsByGang")
	private List<Offenders> offenderNonAssociationsByGang;
	

	public List<Offenders> getOffenderNonAssociationsByInd() {
		return offenderNonAssociationsByInd;
	}

	public void setOffenderNonAssociationsByInd(List<Offenders> offenderNonAssociationsByInd) {
		this.offenderNonAssociationsByInd = offenderNonAssociationsByInd;
	}

	public List<Offenders> getOffenderNonAssociationsByGang() {
		return offenderNonAssociationsByGang;
	}

	public void setOffenderNonAssociationsByGang(List<Offenders> offenderNonAssociationsByGang) {
		this.offenderNonAssociationsByGang = offenderNonAssociationsByGang;
	}
	
	
	@JsonProperty("nbtActivityDesc")
	private String nbtActivityDesc;

	@JsonProperty("nbtArea")
	private String nbtArea;

	@JsonProperty("nbtAgyLocId")
	private String nbtAgyLocId;

	@JsonProperty("nbtRegion")
	private String nbtRegion;

	@JsonProperty("nbtTeam")
	private String nbtTeam;

	@JsonProperty("nbtActivityCode")
	private String nbtActivityCode;

	@JsonProperty("caseLoadType")
	private String caseLoadType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("serverCode")
	private int serverCode;

	@JsonProperty("returnValue")
	private int returnValue;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("teamId")
	private Integer teamId;
	
	@JsonProperty("allocCount")
	private Integer allocCount;
	
	@JsonProperty("courseProfile")
	private Integer courseProfile;
	
	@JsonProperty("chkAppointment")
	private Integer chkAppointment;
	
	@JsonProperty("statusDesc")
	private String statusDesc;
	
	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("coursePhaseId")
	private Integer coursePhaseId;
	
	@JsonProperty("assignFlag")
	private String assignFlag;
	
	@JsonProperty("orderType")
	private String orderType;
	
	private String LegalEndDateTemp;
	
	public String getLegalEndDateTemp() {
		return LegalEndDateTemp;
	}

	public void setLegalEndDateTemp(String legalEndDateTemp) {
		LegalEndDateTemp = legalEndDateTemp;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	public int getServerCode() {
		return serverCode;
	}

	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getCaseLoadType() {
		return caseLoadType;
	}

	public void setCaseLoadType(String caseLoadType) {
		this.caseLoadType = caseLoadType;
	}

	@JsonProperty("nbtBulkAssign")
	private String nbtBulkAssign;

	public String getNbtActivityDesc() {
		return nbtActivityDesc;
	}

	public void setNbtActivityDesc(String nbtActivityDesc) {
		this.nbtActivityDesc = nbtActivityDesc;
	}

	public String getNbtArea() {
		return nbtArea;
	}

	public void setNbtArea(String nbtArea) {
		this.nbtArea = nbtArea;
	}

	public String getNbtAgyLocId() {
		return nbtAgyLocId;
	}

	public void setNbtAgyLocId(String nbtAgyLocId) {
		this.nbtAgyLocId = nbtAgyLocId;
	}

	public String getNbtRegion() {
		return nbtRegion;
	}

	public void setNbtRegion(String nbtRegion) {
		this.nbtRegion = nbtRegion;
	}

	public String getNbtTeam() {
		return nbtTeam;
	}

	public void setNbtTeam(String nbtTeam) {
		this.nbtTeam = nbtTeam;
	}

	public String getNbtActivityCode() {
		return nbtActivityCode;
	}

	public void setNbtActivityCode(String nbtActivityCode) {
		this.nbtActivityCode = nbtActivityCode;
	}

	public String getNbtBulkAssign() {
		return nbtBulkAssign;
	}

	public void setNbtBulkAssign(String nbtBulkAssign) {
		this.nbtBulkAssign = nbtBulkAssign;
	}

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}

	@JsonProperty("firstFlag")
	private String firstFlag;

	private BigDecimal waitDays;

	public VOffenderPrgObligations() {
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityDesc() {
		return this.activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public BigDecimal getAge() {
		return this.age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public String getAvailabilityCode() {
		return this.availabilityCode;
	}

	public void setAvailabilityCode(String availabilityCode) {
		this.availabilityCode = availabilityCode;
	}

	public String getAvailabilityDesc() {
		return this.availabilityDesc;
	}

	public void setAvailabilityDesc(String availabilityDesc) {
		this.availabilityDesc = availabilityDesc;
	}

	public String getCaseInfoNumber() {
		return this.caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public String getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public BigDecimal getCheckSum() {
		return this.checkSum;
	}

	public void setCheckSum(BigDecimal checkSum) {
		this.checkSum = checkSum;
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

	public String getCommunityActiveFlag() {
		return this.communityActiveFlag;
	}

	public void setCommunityActiveFlag(String communityActiveFlag) {
		this.communityActiveFlag = communityActiveFlag;
	}

	public String getCourtAgyLocId() {
		return this.courtAgyLocId;
	}

	public void setCourtAgyLocId(String courtAgyLocId) {
		this.courtAgyLocId = courtAgyLocId;
	}

	public BigDecimal getCourtEventId() {
		return this.courtEventId;
	}

	public void setCourtEventId(BigDecimal courtEventId) {
		this.courtEventId = courtEventId;
	}

	public String getCourtName() {
		return this.courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public String getCreditedUnits() {
		return this.creditedUnits;
	}

	public void setCreditedUnits(String creditedUnits) {
		this.creditedUnits = creditedUnits;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEventSubType() {
		return this.eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLegalEndDate() {
		return this.legalEndDate;
	}

	public void setLegalEndDate(Date legalEndDate) {
		this.legalEndDate = legalEndDate;
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

	public String getObligationSource() {
		return this.obligationSource;
	}

	public void setObligationSource(String obligationSource) {
		this.obligationSource = obligationSource;
	}

	public String getObligationSourceDesc() {
		return this.obligationSourceDesc;
	}

	public void setObligationSourceDesc(String obligationSourceDesc) {
		this.obligationSourceDesc = obligationSourceDesc;
	}

	public String getOffenceTypes() {
		return this.offenceTypes;
	}

	public void setOffenceTypes(String offenceTypes) {
		this.offenceTypes = offenceTypes;
	}

	public String getOffenderAgyLocId() {
		return this.offenderAgyLocId;
	}

	public void setOffenderAgyLocId(String offenderAgyLocId) {
		this.offenderAgyLocId = offenderAgyLocId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderCommunityAgyLocId() {
		return this.offenderCommunityAgyLocId;
	}

	public void setOffenderCommunityAgyLocId(String offenderCommunityAgyLocId) {
		this.offenderCommunityAgyLocId = offenderCommunityAgyLocId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getOffenderName() {
		return this.offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public BigDecimal getOffenderPrgObligationId() {
		return this.offenderPrgObligationId;
	}

	public void setOffenderPrgObligationId(BigDecimal offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	public BigDecimal getOffenderSentCondActId() {
		return this.offenderSentCondActId;
	}

	public void setOffenderSentCondActId(BigDecimal offenderSentCondActId) {
		this.offenderSentCondActId = offenderSentCondActId;
	}

	public BigDecimal getOffenderSentConditionId() {
		return this.offenderSentConditionId;
	}

	public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public BigDecimal getOrderId() {
		return this.orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public String getProgramCategory() {
		return this.programCategory;
	}

	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}

	public BigDecimal getProgramId() {
		return this.programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public String getProgramLength() {
		return this.programLength;
	}

	public void setProgramLength(String programLength) {
		this.programLength = programLength;
	}

	public String getRaceCode() {
		return this.raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getRaceDesc() {
		return this.raceDesc;
	}

	public void setRaceDesc(String raceDesc) {
		this.raceDesc = raceDesc;
	}

	public Date getReferralDate() {
		return this.referralDate;
	}

	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	public String getRiskCode() {
		return this.riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getSentenceCalcType() {
		return this.sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceCategory() {
		return this.sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceConditionDesc() {
		return this.sentenceConditionDesc;
	}

	public void setSentenceConditionDesc(String sentenceConditionDesc) {
		this.sentenceConditionDesc = sentenceConditionDesc;
	}

	public String getSentenceDesc() {
		return this.sentenceDesc;
	}

	public void setSentenceDesc(String sentenceDesc) {
		this.sentenceDesc = sentenceDesc;
	}

	public Date getSentenceEndDate() {
		return this.sentenceEndDate;
	}

	public void setSentenceEndDate(Date sentenceEndDate) {
		this.sentenceEndDate = sentenceEndDate;
	}

	public BigDecimal getSentenceSeq() {
		return this.sentenceSeq;
	}

	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Date getSentenceStartDate() {
		return this.sentenceStartDate;
	}

	public void setSentenceStartDate(Date sentenceStartDate) {
		this.sentenceStartDate = sentenceStartDate;
	}

	public String getSentenceStatus() {
		return this.sentenceStatus;
	}

	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getServiceObligationCode() {
		return this.serviceObligationCode;
	}

	public void setServiceObligationCode(String serviceObligationCode) {
		this.serviceObligationCode = serviceObligationCode;
	}

	public String getSexCode() {
		return this.sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getSexDesc() {
		return this.sexDesc;
	}

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public String getSpecialNeedFlag() {
		return this.specialNeedFlag;
	}

	public void setSpecialNeedFlag(String specialNeedFlag) {
		this.specialNeedFlag = specialNeedFlag;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusChangeDate() {
		return this.statusChangeDate;
	}

	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

	public String getStatusChangeReason() {
		return this.statusChangeReason;
	}

	public void setStatusChangeReason(String statusChangeReason) {
		this.statusChangeReason = statusChangeReason;
	}

	public BigDecimal getWaitDays() {
		return this.waitDays;
	}

	public void setWaitDays(BigDecimal waitDays) {
		this.waitDays = waitDays;
	}

	public Integer getAllocCount() {
		return allocCount;
	}

	public void setAllocCount(Integer allocCount) {
		this.allocCount = allocCount;
	}

	public Integer getCourseProfile() {
		return courseProfile;
	}

	public void setCourseProfile(Integer courseProfile) {
		this.courseProfile = courseProfile;
	}

	public Integer getChkAppointment() {
		return chkAppointment;
	}

	public void setChkAppointment(Integer chkAppointment) {
		this.chkAppointment = chkAppointment;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getAssignFlag() {
		return assignFlag;
	}

	public void setAssignFlag(String assignFlag) {
		this.assignFlag = assignFlag;
	}

	public Integer getCoursePhaseId() {
		return coursePhaseId;
	}

	public void setCoursePhaseId(Integer coursePhaseId) {
		this.coursePhaseId = coursePhaseId;
	}

	public String getConflictMsg() {
		return conflictMsg;
	}

	public void setConflictMsg(String conflictMsg) {
		this.conflictMsg = conflictMsg;
	}

}
