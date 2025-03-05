package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAssessments extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("approvedSupLevelType")
	private String approvedSupLevelType;

	@JsonProperty("assessCommentText")
	private String assessCommentText;

	@JsonProperty("assessCommitteCode")
	private String assessCommitteCode;

	@JsonProperty("assessStaffId")
	private BigDecimal assessStaffId;

	@JsonProperty("assessStatus")
	private String assessStatus;

	@JsonProperty("assessmentCreateLocation")
	private String assessmentCreateLocation;

	@JsonProperty("assessmentDate")
	private Date assessmentDate;

	@JsonProperty("assessmentTypeId")
	private BigDecimal assessmentTypeId;

	@JsonProperty("assessorStaffId")
	private BigDecimal assessorStaffId;

	@JsonProperty("calcSupLevelType")
	private String calcSupLevelType;

	@JsonProperty("committeCommentText")
	private String committeCommentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("creationUser")
	private String creationUser;

	@JsonProperty("evaluationDate")
	private Date evaluationDate;

	@JsonProperty("evaluationResultCode")
	private String evaluationResultCode;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("nextReviewDate")
	private Date nextReviewDate;

	@JsonProperty("overrideCommentText")
	private String overrideCommentText;

	@JsonProperty("overrideReason")
	private String overrideReason;

	@JsonProperty("overrideReasonText")
	private String overrideReasonText;

	@JsonProperty("overrideStaffId")
	private BigDecimal overrideStaffId;

	@JsonProperty("overrideUserId")
	private String overrideUserId;

	@JsonProperty("overridedSupLevelType")
	private String overridedSupLevelType;

	@JsonProperty("placeAgyLocId")
	private String placeAgyLocId;

	@JsonProperty("reviewCommitteCode")
	private String reviewCommitteCode;

	@JsonProperty("reviewPlaceAgyLocId")
	private String reviewPlaceAgyLocId;

	@JsonProperty("reviewPlacementText")
	private String reviewPlacementText;

	@JsonProperty("reviewSupLevelText")
	private String reviewSupLevelText;

	@JsonProperty("reviewSupLevelType")
	private String reviewSupLevelType;

	@JsonProperty("score")
	private BigDecimal score;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("assessmentSeq")
	private Long assessmentSeq;
	
	@JsonProperty("calcSupLevelTypeDesc")
	private String calcSupLevelTypeDesc;
	
	@JsonProperty("overridedSupLevelTypeDesc")
	private String overridedSupLevelTypeDesc;
	
	@JsonProperty("profileValue")
	private String profileValue;
	
	@JsonProperty("assessmentTypeCode")
	private String assessmentTypeCode;
	
	@JsonProperty("assessorName")
	private String assessorName;
	
	@JsonProperty("requireApprovalFlag")
	private String requireApprovalFlag;
	
	@JsonProperty("assessmentStatus")
	private String assessmentStatus;
	@JsonProperty("overrideScore")
	private Integer overrideScore;

	public Integer getOverrideScore() {
		return overrideScore;
	}

	public void setOverrideScore(Integer overrideScore) {
		this.overrideScore = overrideScore;
	}

	public String getProfileValue() {
		return profileValue;
	}

	public void setProfileValue( final String profileValue) {
		this.profileValue = profileValue;
	}

	/**
	 *
	 * @return
	 */
	public String getApprovedSupLevelType() {
		return approvedSupLevelType;
	}

	/**
	 *
	 * @param approvedSupLevelType
	 */
	public void setApprovedSupLevelType(final String approvedSupLevelType) {
		this.approvedSupLevelType = approvedSupLevelType;
	}

	/**
	 *
	 * @return
	 */
	public String getAssessCommentText() {
		return assessCommentText;
	}

	/**
	 *
	 * @param assessCommentText
	 */
	public void setAssessCommentText(final String assessCommentText) {
		this.assessCommentText = assessCommentText;
	}

	/**
	 *
	 * @return
	 */
	public String getAssessCommitteCode() {
		return assessCommitteCode;
	}

	/**
	 *
	 * @param assessCommitteCode
	 */
	public void setAssessCommitteCode(final String assessCommitteCode) {
		this.assessCommitteCode = assessCommitteCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAssessStaffId() {
		return assessStaffId;
	}

	/**
	 *
	 * @param assessStaffId
	 */
	public void setAssessStaffId(final BigDecimal assessStaffId) {
		this.assessStaffId = assessStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getAssessStatus() {
		return assessStatus;
	}

	/**
	 *
	 * @param assessStatus
	 */
	public void setAssessStatus(final String assessStatus) {
		this.assessStatus = assessStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getAssessmentCreateLocation() {
		return assessmentCreateLocation;
	}

	/**
	 *
	 * @param assessmentCreateLocation
	 */
	public void setAssessmentCreateLocation(final String assessmentCreateLocation) {
		this.assessmentCreateLocation = assessmentCreateLocation;
	}

	/**
	 *
	 * @return
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}

	/**
	 *
	 * @param assessmentDate
	 */
	public void setAssessmentDate(final Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAssessmentTypeId() {
		return assessmentTypeId;
	}

	/**
	 *
	 * @param assessmentTypeId
	 */
	public void setAssessmentTypeId(final BigDecimal assessmentTypeId) {
		this.assessmentTypeId = assessmentTypeId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAssessorStaffId() {
		return assessorStaffId;
	}

	/**
	 *
	 * @param assessorStaffId
	 */
	public void setAssessorStaffId(final BigDecimal assessorStaffId) {
		this.assessorStaffId = assessorStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getCalcSupLevelType() {
		return calcSupLevelType;
	}

	/**
	 *
	 * @param calcSupLevelType
	 */
	public void setCalcSupLevelType(final String calcSupLevelType) {
		this.calcSupLevelType = calcSupLevelType;
	}

	/**
	 *
	 * @return
	 */
	public String getCommitteCommentText() {
		return committeCommentText;
	}

	/**
	 *
	 * @param committeCommentText
	 */
	public void setCommitteCommentText(final String committeCommentText) {
		this.committeCommentText = committeCommentText;
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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 *
	 * @param creationDate
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 *
	 * @return
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 *
	 * @param creationUser
	 */
	public void setCreationUser(final String creationUser) {
		this.creationUser = creationUser;
	}

	/**
	 *
	 * @return
	 */
	public Date getEvaluationDate() {
		return evaluationDate;
	}

	/**
	 *
	 * @param evaluationDate
	 */
	public void setEvaluationDate(final Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	/**
	 *
	 * @return
	 */
	public String getEvaluationResultCode() {
		return evaluationResultCode;
	}

	/**
	 *
	 * @param evaluationResultCode
	 */
	public void setEvaluationResultCode(final String evaluationResultCode) {
		this.evaluationResultCode = evaluationResultCode;
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
	public Date getNextReviewDate() {
		return nextReviewDate;
	}

	/**
	 *
	 * @param nextReviewDate
	 */
	public void setNextReviewDate(final Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	/**
	 *
	 * @return
	 */
	public String getOverrideCommentText() {
		return overrideCommentText;
	}

	/**
	 *
	 * @param overrideCommentText
	 */
	public void setOverrideCommentText(final String overrideCommentText) {
		this.overrideCommentText = overrideCommentText;
	}

	/**
	 *
	 * @return
	 */
	public String getOverrideReason() {
		return overrideReason;
	}

	/**
	 *
	 * @param overrideReason
	 */
	public void setOverrideReason(final String overrideReason) {
		this.overrideReason = overrideReason;
	}

	/**
	 *
	 * @return
	 */
	public String getOverrideReasonText() {
		return overrideReasonText;
	}

	/**
	 *
	 * @param overrideReasonText
	 */
	public void setOverrideReasonText(final String overrideReasonText) {
		this.overrideReasonText = overrideReasonText;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOverrideStaffId() {
		return overrideStaffId;
	}

	/**
	 *
	 * @param overrideStaffId
	 */
	public void setOverrideStaffId(final BigDecimal overrideStaffId) {
		this.overrideStaffId = overrideStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getOverrideUserId() {
		return overrideUserId;
	}

	/**
	 *
	 * @param overrideUserId
	 */
	public void setOverrideUserId(final String overrideUserId) {
		this.overrideUserId = overrideUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getOverridedSupLevelType() {
		return overridedSupLevelType;
	}

	/**
	 *
	 * @param overridedSupLevelType
	 */
	public void setOverridedSupLevelType(final String overridedSupLevelType) {
		this.overridedSupLevelType = overridedSupLevelType;
	}

	/**
	 *
	 * @return
	 */
	public String getPlaceAgyLocId() {
		return placeAgyLocId;
	}

	/**
	 *
	 * @param placeAgyLocId
	 */
	public void setPlaceAgyLocId(final String placeAgyLocId) {
		this.placeAgyLocId = placeAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getReviewCommitteCode() {
		return reviewCommitteCode;
	}

	/**
	 *
	 * @param reviewCommitteCode
	 */
	public void setReviewCommitteCode(final String reviewCommitteCode) {
		this.reviewCommitteCode = reviewCommitteCode;
	}

	/**
	 *
	 * @return
	 */
	public String getReviewPlaceAgyLocId() {
		return reviewPlaceAgyLocId;
	}

	/**
	 *
	 * @param reviewPlaceAgyLocId
	 */
	public void setReviewPlaceAgyLocId(final String reviewPlaceAgyLocId) {
		this.reviewPlaceAgyLocId = reviewPlaceAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getReviewPlacementText() {
		return reviewPlacementText;
	}

	/**
	 *
	 * @param reviewPlacementText
	 */
	public void setReviewPlacementText(final String reviewPlacementText) {
		this.reviewPlacementText = reviewPlacementText;
	}

	/**
	 *
	 * @return
	 */
	public String getReviewSupLevelText() {
		return reviewSupLevelText;
	}

	/**
	 *
	 * @param reviewSupLevelText
	 */
	public void setReviewSupLevelText(final String reviewSupLevelText) {
		this.reviewSupLevelText = reviewSupLevelText;
	}

	/**
	 *
	 * @return
	 */
	public String getReviewSupLevelType() {
		return reviewSupLevelType;
	}

	/**
	 *
	 * @param reviewSupLevelType
	 */
	public void setReviewSupLevelType(final String reviewSupLevelType) {
		this.reviewSupLevelType = reviewSupLevelType;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getScore() {
		return score;
	}

	/**
	 *
	 * @param score
	 */
	public void setScore(final BigDecimal score) {
		this.score = score;
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
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 *
	 * @param offenderBooking
	 */
	public void setOffenderBooking(final OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
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

	/**
	 *
	 * @return
	 */
	public Long getAssessmentSeq() {
		return assessmentSeq;
	}

	/**
	 *
	 * @param assessmentSeq
	 */
	public void setAssessmentSeq(final Long assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	public String getCalcSupLevelTypeDesc() {
		return calcSupLevelTypeDesc;
	}

	public void setCalcSupLevelTypeDesc(final String calcSupLevelTypeDesc) {
		this.calcSupLevelTypeDesc = calcSupLevelTypeDesc;
	}

	public String getOverridedSupLevelTypeDesc() {
		return overridedSupLevelTypeDesc;
	}

	public void setOverridedSupLevelTypeDesc(final String overridedSupLevelTypeDesc) {
		this.overridedSupLevelTypeDesc = overridedSupLevelTypeDesc;
	}

	public String getAssessmentTypeCode() {
		return assessmentTypeCode;
	}

	public void setAssessmentTypeCode(final String assessmentTypeCode) {
		this.assessmentTypeCode = assessmentTypeCode;
	}

	public String getAssessorName() {
		return assessorName;
	}

	public void setAssessorName(final String assessorName) {
		this.assessorName = assessorName;
	}

	public String getRequireApprovalFlag() {
		return requireApprovalFlag;
	}

	public void setRequireApprovalFlag(String requireApprovalFlag) {
		this.requireApprovalFlag = requireApprovalFlag;
	}

	public String getAssessmentStatus() {
		return assessmentStatus;
	}

	public void setAssessmentStatus(String assessmentStatus) {
		this.assessmentStatus = assessmentStatus;
	}
	

}