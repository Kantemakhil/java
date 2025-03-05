package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Condition extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	
	@JsonProperty("conditionCode")
	private String conditionCode;
	
	@JsonProperty("conditionTypeCode")
	private String conditionTypeCode;
	
	@JsonProperty("categoryTypeCode")
	private String categoryTypeCode;
	
	@JsonProperty("length")
	private Integer length;
	
	@JsonProperty("lengthUnit")
	private String lengthUnit;
	
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("endDate")
	private Date endDate;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("program")
	private String program;
	
	@JsonProperty("programId")
	private Long programId;  //
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("appliedFlag")
	private String appliedFlag;
	
	@JsonProperty("sentConditionId")
	private Long sentConditionId;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;
	
	@JsonProperty("statusUpdateDate")
	private Date statusUpdateDate;
	
	@JsonProperty("statusUpdateStaffId")
	private Integer statusUpdateStaffId;
	
	@JsonProperty("conditionStatus")
	private String conditionStatus;
	
	@JsonProperty("conditionType")
	private String conditionType;
	
	@JsonProperty("offenderSentConditionId")
	private Integer offenderSentConditionId;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("condition")
	private String condition;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;

	@JsonProperty("curfewStartTime")
	private Date curfewStartTime;
	
	@JsonProperty("curfewEndTime")
	private Date curfewEndTime;
	
	@JsonProperty("finTotalAmount")
	private Long finTotalAmount;
	
	@JsonProperty("nonAssociationText")
	private String nonAssociationText;
	
	@JsonProperty("prohibitedContact")
	private String prohibitedContact;
	
	@JsonProperty("sortComment")
	private String sortComment;
	private Long offenderSentCondStatusId;
	private String previousStatus;
	private String lConditionStatus;
	
	@JsonProperty("commConditionType")
	private String commConditionType;
	
	@JsonProperty("commConditionCode")
	private String commConditionCode;
	
	@JsonProperty("provisoFlag")
	private String provisoFlag;
	
	public String getProvisoFlag() {
		return provisoFlag;
	}

	public void setProvisoFlag(final String provisoFlag) {
		this.provisoFlag = provisoFlag;
	}	
	
	public String getCommConditionCode() {
		return commConditionCode;
	}

	public void setCommConditionCode(final String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}
	
	public String getCommConditionType() {
		return commConditionType;
	}

	public void setCommConditionType(final String commConditionType) {
		this.commConditionType = commConditionType;
	}
	public String getlConditionStatus() {
		return lConditionStatus;
	}

	public void setlConditionStatus(String lConditionStatus) {
		this.lConditionStatus = lConditionStatus;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	public Long getOffenderSentCondStatusId() {
		return offenderSentCondStatusId;
	}

	public void setOffenderSentCondStatusId(Long offenderSentCondStatusId) {
		this.offenderSentCondStatusId = offenderSentCondStatusId;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public String getConditionCode() {
		return conditionCode;
	}

	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getLengthUnit() {
		return lengthUnit;
	}

	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getCommentText() {
		return commentText;
	}

	public String getCategoryTypeCode() {
		return categoryTypeCode;
	}

	public String getConditionTypeCode() {
		return conditionTypeCode;
	}

	public void setConditionTypeCode(String conditionTypeCode) {
		this.conditionTypeCode = conditionTypeCode;
	}

	public void setCategoryTypeCode(String categoryTypeCode) {
		this.categoryTypeCode = categoryTypeCode;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public Integer getStatusUpdateStaffId() {
		return statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(Integer statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public String getConditionStatus() {
		return conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public Integer getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	public void setOffenderSentConditionId(Integer offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}	
	public String getAppliedFlag() {
		return appliedFlag;
	}

	public void setAppliedFlag(String appliedFlag) {
		this.appliedFlag = appliedFlag;
	}
		public Long getSentConditionId() {
		return sentConditionId;
	}

	public void setSentConditionId(Long sentConditionId) {
		this.sentConditionId = sentConditionId;
	}
	
	
	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Date getCurfewStartTime() {
		return curfewStartTime;
	}

	public void setCurfewStartTime(Date curfewStartTime) {
		this.curfewStartTime = curfewStartTime;
	}

	public Date getCurfewEndTime() {
		return curfewEndTime;
	}

	public void setCurfewEndTime(Date curfewEndTime) {
		this.curfewEndTime = curfewEndTime;
	}

	public Long getFinTotalAmount() {
		return finTotalAmount;
	}

	public void setFinTotalAmount(Long finTotalAmount) {
		this.finTotalAmount = finTotalAmount;
	}

	public String getNonAssociationText() {
		return nonAssociationText;
	}

	public void setNonAssociationText(String nonAssociationText) {
		this.nonAssociationText = nonAssociationText;
	}

	public String getProhibitedContact() {
		return prohibitedContact;
	}

	public void setProhibitedContact(String prohibitedContact) {
		this.prohibitedContact = prohibitedContact;
	}


	public String getSortComment() {
		return sortComment;
	}

	public void setSortComment(String sortComment) {
		this.sortComment = sortComment;
	}	

	
}
