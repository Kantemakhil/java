package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Class VOffassAss
 * 
 */
public class VOffassAss extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String approvedSupLevelType;

	private String assessCommentText;

	private String assessCommitteCode;

	private BigDecimal assessStaffId;

	private String assessStatus;

	private Date assessmentDate;

	private BigDecimal assessmentSeq;

	private BigDecimal assessmentTypeId;

	private String calcSupLevelType;

	private String caseloadType;

	private String committeCommentText;

	private Date creationDate;

	private String creationUser;

	private Date evaluationDate;

	private String evaluationResultCode;

	private String medicalFlag;

	private String modifyUserId;

	private Date nextReviewDate;

	private BigDecimal offenderBookId;

	private String overrideCommentText;

	private String overrideReasonText;

	private BigDecimal overrideStaffId;

	private String overridedSupLevelType;

	private String placeAgyLocId;

	private String requireApprovalFlag;

	private String reviewCommitteCode;

	private String reviewPlaceAgyLocId;

	private String reviewPlacementText;

	private String reviewSupLevelText;

	private String reviewSupLevelType;

	private BigDecimal score;
	
	private BigDecimal programId;
	
	private String assessmentTypeDesc;
	
	private String assessmentTypeCode;
	
	private String assessmentStatus;
	
	private Integer overrideScore;
	

	public Integer getOverrideScore() {
		return overrideScore;
	}

	public void setOverrideScore(Integer overrideScore) {
		this.overrideScore = overrideScore;
	}

	public VOffassAss() {
		// VOffassAss
	}

	public String getApprovedSupLevelType() {
		return this.approvedSupLevelType;
	}

	public void setApprovedSupLevelType(String approvedSupLevelType) {
		this.approvedSupLevelType = approvedSupLevelType;
	}

	public String getAssessCommentText() {
		return this.assessCommentText;
	}

	public void setAssessCommentText(String assessCommentText) {
		this.assessCommentText = assessCommentText;
	}

	public String getAssessCommitteCode() {
		return this.assessCommitteCode;
	}

	public void setAssessCommitteCode(String assessCommitteCode) {
		this.assessCommitteCode = assessCommitteCode;
	}

	public BigDecimal getAssessStaffId() {
		return this.assessStaffId;
	}

	public void setAssessStaffId(BigDecimal assessStaffId) {
		this.assessStaffId = assessStaffId;
	}

	public String getAssessStatus() {
		return this.assessStatus;
	}

	public void setAssessStatus(String assessStatus) {
		this.assessStatus = assessStatus;
	}

	public Date getAssessmentDate() {
		return this.assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public BigDecimal getAssessmentSeq() {
		return this.assessmentSeq;
	}

	public void setAssessmentSeq(BigDecimal assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	public BigDecimal getAssessmentTypeId() {
		return this.assessmentTypeId;
	}

	public void setAssessmentTypeId(BigDecimal assessmentTypeId) {
		this.assessmentTypeId = assessmentTypeId;
	}

	public String getCalcSupLevelType() {
		return this.calcSupLevelType;
	}

	public void setCalcSupLevelType(String calcSupLevelType) {
		this.calcSupLevelType = calcSupLevelType;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCommitteCommentText() {
		return this.committeCommentText;
	}

	public void setCommitteCommentText(String committeCommentText) {
		this.committeCommentText = committeCommentText;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Date getEvaluationDate() {
		return this.evaluationDate;
	}

	public void setEvaluationDate(Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public String getEvaluationResultCode() {
		return this.evaluationResultCode;
	}

	public void setEvaluationResultCode(String evaluationResultCode) {
		this.evaluationResultCode = evaluationResultCode;
	}

	public String getMedicalFlag() {
		return this.medicalFlag;
	}

	public void setMedicalFlag(String medicalFlag) {
		this.medicalFlag = medicalFlag;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getNextReviewDate() {
		return this.nextReviewDate;
	}

	public void setNextReviewDate(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOverrideCommentText() {
		return this.overrideCommentText;
	}

	public void setOverrideCommentText(String overrideCommentText) {
		this.overrideCommentText = overrideCommentText;
	}

	public String getOverrideReasonText() {
		return this.overrideReasonText;
	}

	public void setOverrideReasonText(String overrideReasonText) {
		this.overrideReasonText = overrideReasonText;
	}

	public BigDecimal getOverrideStaffId() {
		return this.overrideStaffId;
	}

	public void setOverrideStaffId(BigDecimal overrideStaffId) {
		this.overrideStaffId = overrideStaffId;
	}

	public String getOverridedSupLevelType() {
		return this.overridedSupLevelType;
	}

	public void setOverridedSupLevelType(String overridedSupLevelType) {
		this.overridedSupLevelType = overridedSupLevelType;
	}

	public String getPlaceAgyLocId() {
		return this.placeAgyLocId;
	}

	public void setPlaceAgyLocId(String placeAgyLocId) {
		this.placeAgyLocId = placeAgyLocId;
	}

	public String getRequireApprovalFlag() {
		return this.requireApprovalFlag;
	}

	public void setRequireApprovalFlag(String requireApprovalFlag) {
		this.requireApprovalFlag = requireApprovalFlag;
	}

	public String getReviewCommitteCode() {
		return this.reviewCommitteCode;
	}

	public void setReviewCommitteCode(String reviewCommitteCode) {
		this.reviewCommitteCode = reviewCommitteCode;
	}

	public String getReviewPlaceAgyLocId() {
		return this.reviewPlaceAgyLocId;
	}

	public void setReviewPlaceAgyLocId(String reviewPlaceAgyLocId) {
		this.reviewPlaceAgyLocId = reviewPlaceAgyLocId;
	}

	public String getReviewPlacementText() {
		return this.reviewPlacementText;
	}

	public void setReviewPlacementText(String reviewPlacementText) {
		this.reviewPlacementText = reviewPlacementText;
	}

	public String getReviewSupLevelText() {
		return this.reviewSupLevelText;
	}

	public void setReviewSupLevelText(String reviewSupLevelText) {
		this.reviewSupLevelText = reviewSupLevelText;
	}

	public String getReviewSupLevelType() {
		return this.reviewSupLevelType;
	}

	public void setReviewSupLevelType(String reviewSupLevelType) {
		this.reviewSupLevelType = reviewSupLevelType;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getAssessmentTypeDesc() {
		return assessmentTypeDesc;
	}

	public void setAssessmentTypeDesc(String assessmentTypeDesc) {
		this.assessmentTypeDesc = assessmentTypeDesc;
	}
	public BigDecimal getProgramId() {
		return programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public String getAssessmentTypeCode() {
		return assessmentTypeCode;
	}

	public void setAssessmentTypeCode(String assessmentTypeCode) {
		this.assessmentTypeCode = assessmentTypeCode;
	}

	public String getAssessmentStatus() {
		return assessmentStatus;
	}

	public void setAssessmentStatus(String assessmentStatus) {
		this.assessmentStatus = assessmentStatus;
	}
	
	
}
