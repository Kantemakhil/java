package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAssessment extends BaseModel implements Serializable{

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
	private long offenderBookId;

	@JsonProperty("assessmentSeq")
	private long assessmentSeq;
	
	@JsonProperty("assessmentTypeCode")
	private String assessmentTypeCode;
	
	@JsonProperty("calcSupLevelTypeDesc")
	private String calcSupLevelTypeDesc;

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
	public void setApprovedSupLevelType(String approvedSupLevelType) {
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
	public void setAssessCommentText(String assessCommentText) {
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
	public void setAssessCommitteCode(String assessCommitteCode) {
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
	public void setAssessStaffId(BigDecimal assessStaffId) {
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
	public void setAssessStatus(String assessStatus) {
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
	public void setAssessmentCreateLocation(String assessmentCreateLocation) {
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
	public void setAssessmentDate(Date assessmentDate) {
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
	public void setAssessmentTypeId(BigDecimal assessmentTypeId) {
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
	public void setAssessorStaffId(BigDecimal assessorStaffId) {
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
	public void setCalcSupLevelType(String calcSupLevelType) {
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
	public void setCommitteCommentText(String committeCommentText) {
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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
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
	public void setCreationDate(Date creationDate) {
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
	public void setCreationUser(String creationUser) {
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
	public void setEvaluationDate(Date evaluationDate) {
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
	public void setEvaluationResultCode(String evaluationResultCode) {
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
	public void setModifyDatetime(Date modifyDatetime) {
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
	public void setModifyUserId(String modifyUserId) {
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
	public void setNextReviewDate(Date nextReviewDate) {
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
	public void setOverrideCommentText(String overrideCommentText) {
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
	public void setOverrideReason(String overrideReason) {
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
	public void setOverrideReasonText(String overrideReasonText) {
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
	public void setOverrideStaffId(BigDecimal overrideStaffId) {
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
	public void setOverrideUserId(String overrideUserId) {
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
	public void setOverridedSupLevelType(String overridedSupLevelType) {
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
	public void setPlaceAgyLocId(String placeAgyLocId) {
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
	public void setReviewCommitteCode(String reviewCommitteCode) {
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
	public void setReviewPlaceAgyLocId(String reviewPlaceAgyLocId) {
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
	public void setReviewPlacementText(String reviewPlacementText) {
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
	public void setReviewSupLevelText(String reviewSupLevelText) {
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
	public void setReviewSupLevelType(String reviewSupLevelType) {
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
	public void setScore(BigDecimal score) {
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
	public void setSealFlag(String sealFlag) {
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
	public void setOffenderBooking(OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getAssessmentSeq() {
		return assessmentSeq;
	}

	/**
	 *
	 * @param assessmentSeq
	 */
	public void setAssessmentSeq(long assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	public String getAssessmentTypeCode() {
		return assessmentTypeCode;
	}

	public void setAssessmentTypeCode(String assessmentTypeCode) {
		this.assessmentTypeCode = assessmentTypeCode;
	}

	public String getCalcSupLevelTypeDesc() {
		return calcSupLevelTypeDesc;
	}

	public void setCalcSupLevelTypeDesc(String calcSupLevelTypeDesc) {
		this.calcSupLevelTypeDesc = calcSupLevelTypeDesc;
	}

}