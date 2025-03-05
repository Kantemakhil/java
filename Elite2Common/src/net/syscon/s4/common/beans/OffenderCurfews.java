package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCurfews extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderCurfewId")
	@NotNull
	private long offenderCurfewId;

	@JsonProperty("approvalStatus")
	@Size(max = 12)
	private String approvalStatus;

	@JsonProperty("ardCrdDate")
	private Date ardCrdDate;

	@JsonProperty("assessmentDate")
	private Date assessmentDate;

	@JsonProperty("commentText")
	@Size(max = 240)
	private String commentText;

	@JsonProperty("contractorCorpId")
	private BigDecimal contractorCorpId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("decisionDate")
	private Date decisionDate;

	@JsonProperty("eligibilityDate")
	@NotNull
	private Date eligibilityDate;

	@JsonProperty("familyLinksFlag")
	@Size(max = 1)
	private String familyLinksFlag;

	@JsonProperty("friEndTime")
	private Date friEndTime;

	@JsonProperty("friStartTime")
	private Date friStartTime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("monEndTime")
	private Date monEndTime;

	@JsonProperty("monStartTime")
	private Date monStartTime;

	@JsonProperty("offendingBehaviourFlag")
	@Size(max = 1)
	private String offendingBehaviourFlag;

	@JsonProperty("refusedReason")
	@Size(max = 12)
	private String refusedReason;

	@JsonProperty("reimprisonmentRisk")
	@Size(max = 12)
	private String reimprisonmentRisk;

	@JsonProperty("reviewFlag")
	@Size(max = 1)
	private String reviewFlag;

	@JsonProperty("reviewReason")
	@Size(max = 12)
	private String reviewReason;

	@JsonProperty("riskBehaviourAttitudeFlag")
	@Size(max = 1)
	private String riskBehaviourAttitudeFlag;

	@JsonProperty("satEndTime")
	private Date satEndTime;

	@JsonProperty("satStartTime")
	private Date satStartTime;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("sexOffendingRisk")
	@Size(max = 12)
	private String sexOffendingRisk;

	@JsonProperty("successfulRotlFlag")
	@Size(max = 1)
	private String successfulRotlFlag;

	@JsonProperty("sunEndTime")
	private Date sunEndTime;

	@JsonProperty("sunStartTime")
	private Date sunStartTime;

	@JsonProperty("thuEndTime")
	private Date thuEndTime;

	@JsonProperty("thuStartTime")
	private Date thuStartTime;

	@JsonProperty("tueEndTime")
	private Date tueEndTime;

	@JsonProperty("tueStartTime")
	private Date tueStartTime;

	@JsonProperty("violentOffendingRisk")
	@Size(max = 12)
	private String violentOffendingRisk;

	@JsonProperty("wedEndTime")
	private Date wedEndTime;

	@JsonProperty("wedStartTime")
	private Date wedStartTime;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;
	
	private BigDecimal curfewAddressId;
	
	private BigDecimal videoReviewSummaryId;
	
	private String sectionCode;

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public BigDecimal getVideoReviewSummaryId() {
		return videoReviewSummaryId;
	}

	public void setVideoReviewSummaryId(BigDecimal videoReviewSummaryId) {
		this.videoReviewSummaryId = videoReviewSummaryId;
	}

	public BigDecimal getCurfewAddressId() {
		return curfewAddressId;
	}

	public void setCurfewAddressId(BigDecimal curfewAddressId) {
		this.curfewAddressId = curfewAddressId;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderCurfewId() {
		return offenderCurfewId;
	}

	/**
	 *
	 * @param offenderCurfewId
	 */
	public void setOffenderCurfewId(long offenderCurfewId) {
		this.offenderCurfewId = offenderCurfewId;
	}

	/**
	 *
	 * @return
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 *
	 * @param approvalStatus
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 *
	 * @return
	 */
	public Date getArdCrdDate() {
		return ardCrdDate;
	}

	/**
	 *
	 * @param ardCrdDate
	 */
	public void setArdCrdDate(Date ardCrdDate) {
		this.ardCrdDate = ardCrdDate;
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
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getContractorCorpId() {
		return contractorCorpId;
	}

	/**
	 *
	 * @param contractorCorpId
	 */
	public void setContractorCorpId(BigDecimal contractorCorpId) {
		this.contractorCorpId = contractorCorpId;
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
	public Date getDecisionDate() {
		return decisionDate;
	}

	/**
	 *
	 * @param decisionDate
	 */
	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getEligibilityDate() {
		return eligibilityDate;
	}

	/**
	 *
	 * @param eligibilityDate
	 */
	public void setEligibilityDate(Date eligibilityDate) {
		this.eligibilityDate = eligibilityDate;
	}

	/**
	 *
	 * @return
	 */
	public String getFamilyLinksFlag() {
		return familyLinksFlag;
	}

	/**
	 *
	 * @param familyLinksFlag
	 */
	public void setFamilyLinksFlag(String familyLinksFlag) {
		this.familyLinksFlag = familyLinksFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getFriEndTime() {
		return friEndTime;
	}

	/**
	 *
	 * @param friEndTime
	 */
	public void setFriEndTime(Date friEndTime) {
		this.friEndTime = friEndTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getFriStartTime() {
		return friStartTime;
	}

	/**
	 *
	 * @param friStartTime
	 */
	public void setFriStartTime(Date friStartTime) {
		this.friStartTime = friStartTime;
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
	public Date getMonEndTime() {
		return monEndTime;
	}

	/**
	 *
	 * @param monEndTime
	 */
	public void setMonEndTime(Date monEndTime) {
		this.monEndTime = monEndTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getMonStartTime() {
		return monStartTime;
	}

	/**
	 *
	 * @param monStartTime
	 */
	public void setMonStartTime(Date monStartTime) {
		this.monStartTime = monStartTime;
	}

	/**
	 *
	 * @return
	 */
	public String getOffendingBehaviourFlag() {
		return offendingBehaviourFlag;
	}

	/**
	 *
	 * @param offendingBehaviourFlag
	 */
	public void setOffendingBehaviourFlag(String offendingBehaviourFlag) {
		this.offendingBehaviourFlag = offendingBehaviourFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getRefusedReason() {
		return refusedReason;
	}

	/**
	 *
	 * @param refusedReason
	 */
	public void setRefusedReason(String refusedReason) {
		this.refusedReason = refusedReason;
	}

	/**
	 *
	 * @return
	 */
	public String getReimprisonmentRisk() {
		return reimprisonmentRisk;
	}

	/**
	 *
	 * @param reimprisonmentRisk
	 */
	public void setReimprisonmentRisk(String reimprisonmentRisk) {
		this.reimprisonmentRisk = reimprisonmentRisk;
	}

	/**
	 *
	 * @return
	 */
	public String getReviewFlag() {
		return reviewFlag;
	}

	/**
	 *
	 * @param reviewFlag
	 */
	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getReviewReason() {
		return reviewReason;
	}

	/**
	 *
	 * @param reviewReason
	 */
	public void setReviewReason(String reviewReason) {
		this.reviewReason = reviewReason;
	}

	/**
	 *
	 * @return
	 */
	public String getRiskBehaviourAttitudeFlag() {
		return riskBehaviourAttitudeFlag;
	}

	/**
	 *
	 * @param riskBehaviourAttitudeFlag
	 */
	public void setRiskBehaviourAttitudeFlag(String riskBehaviourAttitudeFlag) {
		this.riskBehaviourAttitudeFlag = riskBehaviourAttitudeFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getSatEndTime() {
		return satEndTime;
	}

	/**
	 *
	 * @param satEndTime
	 */
	public void setSatEndTime(Date satEndTime) {
		this.satEndTime = satEndTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getSatStartTime() {
		return satStartTime;
	}

	/**
	 *
	 * @param satStartTime
	 */
	public void setSatStartTime(Date satStartTime) {
		this.satStartTime = satStartTime;
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
	public String getSexOffendingRisk() {
		return sexOffendingRisk;
	}

	/**
	 *
	 * @param sexOffendingRisk
	 */
	public void setSexOffendingRisk(String sexOffendingRisk) {
		this.sexOffendingRisk = sexOffendingRisk;
	}

	/**
	 *
	 * @return
	 */
	public String getSuccessfulRotlFlag() {
		return successfulRotlFlag;
	}

	/**
	 *
	 * @param successfulRotlFlag
	 */
	public void setSuccessfulRotlFlag(String successfulRotlFlag) {
		this.successfulRotlFlag = successfulRotlFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getSunEndTime() {
		return sunEndTime;
	}

	/**
	 *
	 * @param sunEndTime
	 */
	public void setSunEndTime(Date sunEndTime) {
		this.sunEndTime = sunEndTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getSunStartTime() {
		return sunStartTime;
	}

	/**
	 *
	 * @param sunStartTime
	 */
	public void setSunStartTime(Date sunStartTime) {
		this.sunStartTime = sunStartTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getThuEndTime() {
		return thuEndTime;
	}

	/**
	 *
	 * @param thuEndTime
	 */
	public void setThuEndTime(Date thuEndTime) {
		this.thuEndTime = thuEndTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getThuStartTime() {
		return thuStartTime;
	}

	/**
	 *
	 * @param thuStartTime
	 */
	public void setThuStartTime(Date thuStartTime) {
		this.thuStartTime = thuStartTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getTueEndTime() {
		return tueEndTime;
	}

	/**
	 *
	 * @param tueEndTime
	 */
	public void setTueEndTime(Date tueEndTime) {
		this.tueEndTime = tueEndTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getTueStartTime() {
		return tueStartTime;
	}

	/**
	 *
	 * @param tueStartTime
	 */
	public void setTueStartTime(Date tueStartTime) {
		this.tueStartTime = tueStartTime;
	}

	/**
	 *
	 * @return
	 */
	public String getViolentOffendingRisk() {
		return violentOffendingRisk;
	}

	/**
	 *
	 * @param violentOffendingRisk
	 */
	public void setViolentOffendingRisk(String violentOffendingRisk) {
		this.violentOffendingRisk = violentOffendingRisk;
	}

	/**
	 *
	 * @return
	 */
	public Date getWedEndTime() {
		return wedEndTime;
	}

	/**
	 *
	 * @param wedEndTime
	 */
	public void setWedEndTime(Date wedEndTime) {
		this.wedEndTime = wedEndTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getWedStartTime() {
		return wedStartTime;
	}

	/**
	 *
	 * @param wedStartTime
	 */
	public void setWedStartTime(Date wedStartTime) {
		this.wedStartTime = wedStartTime;
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

}