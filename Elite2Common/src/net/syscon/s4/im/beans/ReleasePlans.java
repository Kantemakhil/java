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
public class ReleasePlans extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("releasePlanId")
	@NotNull
	private Long releasePlanId;

	@JsonProperty("addressId")
	private BigDecimal addressId;

	@JsonProperty("addressType")
	private String addressType;

	@JsonProperty("agentRecommend")
	private String agentRecommend;

	@JsonProperty("assessmentSeq")
	private BigDecimal assessmentSeq;

	@JsonProperty("caseManagerId")
	private Long caseManagerId;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("employSeq")
	private BigDecimal employSeq;

	@JsonProperty("employmentComment")
	private String employmentComment;

	@JsonProperty("employmentStatus")
	private String employmentStatus;

	@JsonProperty("housingComment")
	private String housingComment;

	@JsonProperty("memoText")
	private String memoText;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("paroleAgentId")
	private Long paroleAgentId;

	@JsonProperty("planStatus")
	private String planStatus;

	@JsonProperty("proposedEmployment")
	private String proposedEmployment;

	@JsonProperty("proposedEndDate")
	private Date proposedEndDate;

	@JsonProperty("proposedHousing")
	private String proposedHousing;

	@JsonProperty("proposedStartDate")
	private Date proposedStartDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sponsor")
	private String sponsor;

	@JsonProperty("taType")
	private String taType;

	@JsonProperty("updatedBy")
	private String updatedBy;

	@JsonProperty("nbtCaseManagerId")
	private String nbtCaseManagerId;

	@JsonProperty("nbtParoleAgentId")
	private String nbtParoleAgentId;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("assessmentDate")
	private Date assessmentDate;

	@JsonProperty("reviewSupLevelType")
	private String reviewSupLevelType;

	@JsonProperty("conditions")
	private String conditions;

	/**
	 * Creates new ReleasePlans class Object
	 */
	public ReleasePlans() {
		// ReleasePlans
	}

	/**
	 * @return the releasePlanId
	 */
	

	/**
	 * @return the addressId
	 */
	public BigDecimal getAddressId() {
		return addressId;
	}

	public Long getReleasePlanId() {
		return releasePlanId;
	}

	public void setReleasePlanId(Long releasePlanId) {
		this.releasePlanId = releasePlanId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(BigDecimal addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return the agentRecommend
	 */
	public String getAgentRecommend() {
		return agentRecommend;
	}

	/**
	 * @param agentRecommend
	 *            the agentRecommend to set
	 */
	public void setAgentRecommend(String agentRecommend) {
		this.agentRecommend = agentRecommend;
	}

	/**
	 * @return the assessmentSeq
	 */
	public BigDecimal getAssessmentSeq() {
		return assessmentSeq;
	}

	/**
	 * @param assessmentSeq
	 *            the assessmentSeq to set
	 */
	public void setAssessmentSeq(BigDecimal assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	/**
	 * @return the caseManagerId
	 */
	public Long getCaseManagerId() {
		return caseManagerId;
	}

	/**
	 * @param caseManagerId
	 *            the caseManagerId to set
	 */
	public void setCaseManagerId(Long caseManagerId) {
		this.caseManagerId = caseManagerId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the employSeq
	 */
	public BigDecimal getEmploySeq() {
		return employSeq;
	}

	/**
	 * @param employSeq
	 *            the employSeq to set
	 */
	public void setEmploySeq(BigDecimal employSeq) {
		this.employSeq = employSeq;
	}

	/**
	 * @return the employmentComment
	 */
	public String getEmploymentComment() {
		return employmentComment;
	}

	/**
	 * @param employmentComment
	 *            the employmentComment to set
	 */
	public void setEmploymentComment(String employmentComment) {
		this.employmentComment = employmentComment;
	}

	/**
	 * @return the employmentStatus
	 */
	public String getEmploymentStatus() {
		return employmentStatus;
	}

	/**
	 * @param employmentStatus
	 *            the employmentStatus to set
	 */
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	/**
	 * @return the housingComment
	 */
	public String getHousingComment() {
		return housingComment;
	}

	/**
	 * @param housingComment
	 *            the housingComment to set
	 */
	public void setHousingComment(String housingComment) {
		this.housingComment = housingComment;
	}

	/**
	 * @return the memoText
	 */
	public String getMemoText() {
		return memoText;
	}

	/**
	 * @param memoText
	 *            the memoText to set
	 */
	public void setMemoText(String memoText) {
		this.memoText = memoText;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the paroleAgentId
	 */
	public Long getParoleAgentId() {
		return paroleAgentId;
	}

	/**
	 * @param paroleAgentId
	 *            the paroleAgentId to set
	 */
	public void setParoleAgentId(Long paroleAgentId) {
		this.paroleAgentId = paroleAgentId;
	}

	/**
	 * @return the planStatus
	 */
	public String getPlanStatus() {
		return planStatus;
	}

	/**
	 * @param planStatus
	 *            the planStatus to set
	 */
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	/**
	 * @return the proposedEmployment
	 */
	public String getProposedEmployment() {
		return proposedEmployment;
	}

	/**
	 * @param proposedEmployment
	 *            the proposedEmployment to set
	 */
	public void setProposedEmployment(String proposedEmployment) {
		this.proposedEmployment = proposedEmployment;
	}

	/**
	 * @return the proposedEndDate
	 */
	public Date getProposedEndDate() {
		return proposedEndDate;
	}

	/**
	 * @param proposedEndDate
	 *            the proposedEndDate to set
	 */
	public void setProposedEndDate(Date proposedEndDate) {
		this.proposedEndDate = proposedEndDate;
	}

	/**
	 * @return the proposedHousing
	 */
	public String getProposedHousing() {
		return proposedHousing;
	}

	/**
	 * @param proposedHousing
	 *            the proposedHousing to set
	 */
	public void setProposedHousing(String proposedHousing) {
		this.proposedHousing = proposedHousing;
	}

	/**
	 * @return the proposedStartDate
	 */
	public Date getProposedStartDate() {
		return proposedStartDate;
	}

	/**
	 * @param proposedStartDate
	 *            the proposedStartDate to set
	 */
	public void setProposedStartDate(Date proposedStartDate) {
		this.proposedStartDate = proposedStartDate;
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
	 * @return the sponsor
	 */
	public String getSponsor() {
		return sponsor;
	}

	/**
	 * @param sponsor
	 *            the sponsor to set
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * @return the taType
	 */
	public String getTaType() {
		return taType;
	}

	/**
	 * @param taType
	 *            the taType to set
	 */
	public void setTaType(String taType) {
		this.taType = taType;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the nbtCaseManagerId
	 */
	public String getNbtCaseManagerId() {
		return nbtCaseManagerId;
	}

	/**
	 * @param nbtCaseManagerId
	 *            the nbtCaseManagerId to set
	 */
	public void setNbtCaseManagerId(String nbtCaseManagerId) {
		this.nbtCaseManagerId = nbtCaseManagerId;
	}

	/**
	 * @return the nbtParoleAgentId
	 */
	public String getNbtParoleAgentId() {
		return nbtParoleAgentId;
	}

	/**
	 * @param nbtParoleAgentId
	 *            the nbtParoleAgentId to set
	 */
	public void setNbtParoleAgentId(String nbtParoleAgentId) {
		this.nbtParoleAgentId = nbtParoleAgentId;
	}

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the assessmentDate
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}

	/**
	 * @param assessmentDate
	 *            the assessmentDate to set
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	/**
	 * @return the reviewSupLevelType
	 */
	public String getReviewSupLevelType() {
		return reviewSupLevelType;
	}

	/**
	 * @param reviewSupLevelType
	 *            the reviewSupLevelType to set
	 */
	public void setReviewSupLevelType(String reviewSupLevelType) {
		this.reviewSupLevelType = reviewSupLevelType;
	}

	/**
	 * @return the conditions
	 */
	public String getConditions() {
		return conditions;
	}

	/**
	 * @param conditions
	 *            the conditions to set
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReleasePlans [releasePlanId=" + releasePlanId + ", addressId=" + addressId + ", addressType="
				+ addressType + ", agentRecommend=" + agentRecommend + ", assessmentSeq=" + assessmentSeq
				+ ", caseManagerId=" + caseManagerId + ", createDate=" + createDate + ", createDateTime="
				+ createDateTime + ", createUserId=" + createUserId + ", employSeq=" + employSeq
				+ ", employmentComment=" + employmentComment + ", employmentStatus=" + employmentStatus
				+ ", housingComment=" + housingComment + ", memoText=" + memoText + ", modifyDateTime=" + modifyDateTime
				+ ", modifyUserId=" + modifyUserId + ", offenderBookId=" + offenderBookId + ", paroleAgentId="
				+ paroleAgentId + ", planStatus=" + planStatus + ", proposedEmployment=" + proposedEmployment
				+ ", proposedEndDate=" + proposedEndDate + ", proposedHousing=" + proposedHousing
				+ ", proposedStartDate=" + proposedStartDate + ", sealFlag=" + sealFlag + ", sponsor=" + sponsor
				+ ", taType=" + taType + ", updatedBy=" + updatedBy + ", nbtCaseManagerId=" + nbtCaseManagerId
				+ ", nbtParoleAgentId=" + nbtParoleAgentId + ", rootOffenderId=" + rootOffenderId + ", assessmentDate="
				+ assessmentDate + ", reviewSupLevelType=" + reviewSupLevelType + ", conditions=" + conditions + "]";
	}

}
