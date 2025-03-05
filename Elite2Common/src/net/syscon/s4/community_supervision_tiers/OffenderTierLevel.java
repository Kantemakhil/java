package net.syscon.s4.community_supervision_tiers;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderTierLevel implements Serializable{
	
	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;
	@JsonProperty("tierLevel")
	private String tierLevel;
	@JsonProperty("listSeq")
	private Long listSeq;
	@JsonProperty("dateAssigned")
	private Date dateAssigned;
	@JsonProperty("assignmentReason")
	private String assignmentReason;
	@JsonProperty("assignedBy")
	private String assignedBy;
	@JsonProperty("approvedBy")
	private String approvedBy;
	@JsonProperty("nextReviewDate")
	private Date nextReviewDate;
	@JsonProperty("comment")
	private String comment;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	@JsonProperty("canDisplay")
	private boolean canDisplay = true;
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("assignedByStaffId")
	private Long assignedByStaffId;
	@JsonProperty("approvedByStaffId")
	private Long approvedByStaffId;
	@JsonProperty("approveFlag")
	private String approveFlag;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDataTime")
	private Date createDateTime;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	@JsonProperty("recordCreationDatetime")
	private Date recordCreationDatetime;
	@JsonProperty("reviewDays")
	private Long reviewDays;
	@JsonProperty("offenderTierLevelId")
	private Long offenderTierLevelId;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("deactivatedDate")
	private Date deactivatedDate;
	@JsonProperty("versionId")
	private Long versionId;
	
	public String getTierLevel() {
		return tierLevel;
	}
	public void setTierLevel(String tierLevel) {
		this.tierLevel = tierLevel;
	}
	public Date getDateAssigned() {
		return dateAssigned;
	}
	public void setDateAssigned(Date dateAssigned) {
		this.dateAssigned = dateAssigned;
	}
	public String getAssignmentReason() {
		return assignmentReason;
	}
	public void setAssignmentReason(String assignmentReason) {
		this.assignmentReason = assignmentReason;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getNextReviewDate() {
		return nextReviewDate;
	} 
	public void setNextReviewDate(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public void setErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	public String getCaseLoadId() {
		return caseLoadId;
	}
	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}
	public boolean isCanDisplay() {
		return canDisplay;
	}
	public void setCanDisplay(boolean canDisplay) {
		this.canDisplay = canDisplay;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getAssignedByStaffId() {
		return assignedByStaffId;
	}
	public void setAssignedByStaffId(Long assignedByStaffId) {
		this.assignedByStaffId = assignedByStaffId;
	}
	public Long getApprovedByStaffId() {
		return approvedByStaffId;
	}
	public void setApprovedByStaffId(Long approvedByStaffId) {
		this.approvedByStaffId = approvedByStaffId;
	}
	public String getApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	public Date getRecordCreationDatetime() {
		return recordCreationDatetime;
	}
	public void setRecordCreationDatetime(Date recordCreationDatetime) {
		this.recordCreationDatetime = recordCreationDatetime;
	}
	public Long getReviewDays() {
		return reviewDays;
	}
	public void setReviewDays(Long reviewDays) {
		this.reviewDays = reviewDays;
	}
	public Long getOffenderTierLevelId() {
		return offenderTierLevelId;
	}
	public void setOffenderTierLevelId(Long offenderTierLevelId) {
		this.offenderTierLevelId = offenderTierLevelId;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getDeactivatedDate() {
		return deactivatedDate;
	}
	public void setDeactivatedDate(Date deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	
	
}
