package net.syscon.s4.inst.classification.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.BaseModel;

public class Assessments extends BaseModel {
	private static final long serialVersionUID = 1L;

	private Long assessmentId;
	
	private Long parentAssessmentId;

	private String activeFlag;

	private String assessComment;

	private String assessmentClass;

	private String assessmentCode;

	private String assessmentType;

	private String calculateTotalFlag;

	private String caseloadType;

	private String cellSharingAlertFlag;

	private Date createDate;

	private Date createDatetime;

	private String createUserId;

	private String description;

	private String determineSupLevelFlag;

	private Date effectiveDate;

	private Date expiryDate;

	private BigDecimal highValue;

	private BigDecimal listSeq;

	private BigDecimal lowValue;

	private String measure;

	private String medicalFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private String mutualExclusiveFlag;

	private String overrideableFlag;

	private String requireApprovalFlag;

	private String requireEvaluationFlag;

	private BigDecimal reviewCycleDays;

	private String reviewFlag;

	private BigDecimal scheduleCompleteDays;

	private BigDecimal score;

	private String screenCode;

	private String sealFlag;

	private String searchCriteriaFlag;

	private String sectScoreIncludeFlag;

	private String sectScoreOverrideFlag;

	private BigDecimal totalPercent;

	private String updateAllowedFlag;
	
	private String code;
	
	private String answers;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("assessmentSeq")
	private long assessmentSeq;
	
	private BigDecimal rank;
	
	private long itemSeq;
	
	private Long ansId;
	
	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;
	
	@JsonProperty("answerCode")
	private String answerCode;
	
	@JsonProperty("questionCode")
	private String questionCode;
	
	@JsonProperty("sessionCode")
	private String sessionCode;

	@JsonProperty("requiredFlag")
	private String requiredFlag;
	
	@JsonProperty("ansBookMark")
	private String ansBookMark;
	
	@JsonProperty("bookmarkCondition")
	private String bookmarkCondition;

	@JsonProperty("minValue")
	private Long minValue;
	
	@JsonProperty("maxValue")
	private Long maxValue;
	
	@JsonProperty("age")
	private Long age;
	
	@JsonProperty("query")
	private String query;
	
	@JsonProperty("answerValue")
	private String answerValue;
	
	@JsonProperty("assSecNoti")
	private List<AssessSectionNotifications> assSecNoti;
	
	@JsonProperty("assCodeTemp")
	private String assCodeTemp;


	public String getAnswerValue() {
		return answerValue;
	}

	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Long getMinValue() {
		return minValue;
	}

	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}

	public Long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	public String getBookmarkCondition() {
		return bookmarkCondition;
	}

	public void setBookmarkCondition(String bookmarkCondition) {
		this.bookmarkCondition = bookmarkCondition;
	}


	public Assessments() {
		// Assessments
	}

	public Long getAssessmentId() {
		return this.assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	 * @return the parentAssessmentId
	 */
	public Long getParentAssessmentId() {
		return parentAssessmentId;
	}

	/**
	 * @param parentAssessmentId the parentAssessmentId to set
	 */
	public void setParentAssessmentId(Long parentAssessmentId) {
		this.parentAssessmentId = parentAssessmentId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAssessComment() {
		return this.assessComment;
	}

	public void setAssessComment(String assessComment) {
		this.assessComment = assessComment;
	}

	public String getAssessmentClass() {
		return this.assessmentClass;
	}

	public void setAssessmentClass(String assessmentClass) {
		this.assessmentClass = assessmentClass;
	}

	public String getAssessmentCode() {
		return this.assessmentCode;
	}

	public void setAssessmentCode(String assessmentCode) {
		this.assessmentCode = assessmentCode;
	}

	public String getAssessmentType() {
		return this.assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public String getCalculateTotalFlag() {
		return this.calculateTotalFlag;
	}

	public void setCalculateTotalFlag(String calculateTotalFlag) {
		this.calculateTotalFlag = calculateTotalFlag;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCellSharingAlertFlag() {
		return this.cellSharingAlertFlag;
	}

	public void setCellSharingAlertFlag(String cellSharingAlertFlag) {
		this.cellSharingAlertFlag = cellSharingAlertFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetermineSupLevelFlag() {
		return this.determineSupLevelFlag;
	}

	public void setDetermineSupLevelFlag(String determineSupLevelFlag) {
		this.determineSupLevelFlag = determineSupLevelFlag;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getHighValue() {
		return this.highValue;
	}

	public void setHighValue(BigDecimal highValue) {
		this.highValue = highValue;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public BigDecimal getLowValue() {
		return this.lowValue;
	}

	public void setLowValue(BigDecimal lowValue) {
		this.lowValue = lowValue;
	}

	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getMedicalFlag() {
		return this.medicalFlag;
	}

	public void setMedicalFlag(String medicalFlag) {
		this.medicalFlag = medicalFlag;
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

	public String getMutualExclusiveFlag() {
		return this.mutualExclusiveFlag;
	}

	public void setMutualExclusiveFlag(String mutualExclusiveFlag) {
		this.mutualExclusiveFlag = mutualExclusiveFlag;
	}

	public String getOverrideableFlag() {
		return this.overrideableFlag;
	}

	public void setOverrideableFlag(String overrideableFlag) {
		this.overrideableFlag = overrideableFlag;
	}

	public String getRequireApprovalFlag() {
		return this.requireApprovalFlag;
	}

	public void setRequireApprovalFlag(String requireApprovalFlag) {
		this.requireApprovalFlag = requireApprovalFlag;
	}

	public String getRequireEvaluationFlag() {
		return this.requireEvaluationFlag;
	}

	public void setRequireEvaluationFlag(String requireEvaluationFlag) {
		this.requireEvaluationFlag = requireEvaluationFlag;
	}

	public BigDecimal getReviewCycleDays() {
		return this.reviewCycleDays;
	}

	public void setReviewCycleDays(BigDecimal reviewCycleDays) {
		this.reviewCycleDays = reviewCycleDays;
	}

	public String getReviewFlag() {
		return this.reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public BigDecimal getScheduleCompleteDays() {
		return this.scheduleCompleteDays;
	}

	public void setScheduleCompleteDays(BigDecimal scheduleCompleteDays) {
		this.scheduleCompleteDays = scheduleCompleteDays;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getScreenCode() {
		return this.screenCode;
	}

	public void setScreenCode(String screenCode) {
		this.screenCode = screenCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSearchCriteriaFlag() {
		return this.searchCriteriaFlag;
	}

	public void setSearchCriteriaFlag(String searchCriteriaFlag) {
		this.searchCriteriaFlag = searchCriteriaFlag;
	}

	public String getSectScoreIncludeFlag() {
		return this.sectScoreIncludeFlag;
	}

	public void setSectScoreIncludeFlag(String sectScoreIncludeFlag) {
		this.sectScoreIncludeFlag = sectScoreIncludeFlag;
	}

	public String getSectScoreOverrideFlag() {
		return this.sectScoreOverrideFlag;
	}

	public void setSectScoreOverrideFlag(String sectScoreOverrideFlag) {
		this.sectScoreOverrideFlag = sectScoreOverrideFlag;
	}

	public BigDecimal getTotalPercent() {
		return this.totalPercent;
	}

	public void setTotalPercent(BigDecimal totalPercent) {
		this.totalPercent = totalPercent;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the offenderBookId
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the assessmentSeq
	 */
	public long getAssessmentSeq() {
		return assessmentSeq;
	}

	/**
	 * @param assessmentSeq the assessmentSeq to set
	 */
	public void setAssessmentSeq(long assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	/**
	 * @return the answers
	 */
	public String getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(String answers) {
		this.answers = answers;
	}

	/**
	 * @return the rank
	 */
	public BigDecimal getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(BigDecimal rank) {
		this.rank = rank;
	}

	/**
	 * @return the itemSeq
	 */
	public long getItemSeq() {
		return itemSeq;
	}

	/**
	 * @param itemSeq the itemSeq to set
	 */
	public void setItemSeq(long itemSeq) {
		this.itemSeq = itemSeq;
	}

	/**
	 * @return the ansId
	 */
	public Long getAnsId() {
		return ansId;
	}

	/**
	 * @param ansId the ansId to set
	 */
	public void setAnsId(Long ansId) {
		this.ansId = ansId;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getAnswerCode() {
		return answerCode;
	}

	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	public String getRequiredFlag() {
		return requiredFlag;
	}

	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

	public String getAnsBookMark() {
		return ansBookMark;
	}

	public void setAnsBookMark(String ansBookMark) {
		this.ansBookMark = ansBookMark;
	}

	public List<AssessSectionNotifications> getAssSecNoti() {
		return assSecNoti;
	}

	public void setAssSecNoti(List<AssessSectionNotifications> assSecNoti) {
		this.assSecNoti = assSecNoti;
	}

	
	public String getAssCodeTemp() {
		return assCodeTemp;
	}

	public void setAssCodeTemp(String assCodeTemp) {
		this.assCodeTemp = assCodeTemp;
	}

	@Override
	public String toString() {
		return "Assessments [assessmentId=" + assessmentId + ", parentAssessmentId=" + parentAssessmentId
				+ ", activeFlag=" + activeFlag + ", assessComment=" + assessComment + ", assessmentClass="
				+ assessmentClass + ", assessmentCode=" + assessmentCode + ", assessmentType=" + assessmentType
				+ ", calculateTotalFlag=" + calculateTotalFlag + ", caseloadType=" + caseloadType
				+ ", cellSharingAlertFlag=" + cellSharingAlertFlag + ", createDate=" + createDate + ", createDatetime="
				+ createDatetime + ", createUserId=" + createUserId + ", description=" + description
				+ ", determineSupLevelFlag=" + determineSupLevelFlag + ", effectiveDate=" + effectiveDate
				+ ", expiryDate=" + expiryDate + ", highValue=" + highValue + ", listSeq=" + listSeq + ", lowValue="
				+ lowValue + ", measure=" + measure + ", medicalFlag=" + medicalFlag + ", modifyDatetime="
				+ modifyDatetime + ", modifyUserId=" + modifyUserId + ", mutualExclusiveFlag=" + mutualExclusiveFlag
				+ ", overrideableFlag=" + overrideableFlag + ", requireApprovalFlag=" + requireApprovalFlag
				+ ", requireEvaluationFlag=" + requireEvaluationFlag + ", reviewCycleDays=" + reviewCycleDays
				+ ", reviewFlag=" + reviewFlag + ", scheduleCompleteDays=" + scheduleCompleteDays + ", score=" + score
				+ ", screenCode=" + screenCode + ", sealFlag=" + sealFlag + ", searchCriteriaFlag=" + searchCriteriaFlag
				+ ", sectScoreIncludeFlag=" + sectScoreIncludeFlag + ", sectScoreOverrideFlag=" + sectScoreOverrideFlag
				+ ", totalPercent=" + totalPercent + ", updateAllowedFlag=" + updateAllowedFlag + ", code=" + code
				+ ", answers=" + answers + ", offenderBookId=" + offenderBookId + ", assessmentSeq=" + assessmentSeq
				+ ", rank=" + rank + ", itemSeq=" + itemSeq + ", ansId=" + ansId + ", canDisplay=" + canDisplay
				+ ", answerCode=" + answerCode + ", questionCode=" + questionCode + ", sessionCode=" + sessionCode
				+ ", requiredFlag=" + requiredFlag + ", ansBookMark=" + ansBookMark + ", bookmarkCondition="
				+ bookmarkCondition + ", minValue=" + minValue + ", maxValue=" + maxValue + ", age=" + age + ", query="
				+ query + ", answerValue=" + answerValue + ", assSecNoti=" + assSecNoti + ", assCodeTemp=" + assCodeTemp
				+ "]";
	}

	

	
	

}
