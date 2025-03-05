package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders extends BaseModel implements Serializable{
	
	@JsonProperty("orderId")
	private BigDecimal orderId;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	

	@JsonProperty("workflowId")
	private BigDecimal workflowId;
	
	public BigDecimal getWorkflowId() {
		return workflowId;
	}


	public void setWorkflowId(BigDecimal workflowId) {
		this.workflowId = workflowId;
	}


	@JsonProperty("caseId")
	private Long caseId;
	
	@JsonProperty("courtDate")
	private Date courtDate;
	
	@JsonProperty("orderType")
	private String orderType;
	
	@JsonProperty("issuingAgyLocId")
	private String issuingAgyLocId;
	

	@JsonProperty("courtInfoId")
	private String courtInfoId;
	
	@JsonProperty("orderStatus")
	private String orderStatus;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("dueDate")
	private Date dueDate;
	
	@JsonProperty("requestDate")
	private Date requestDate;
	

	@JsonProperty("eventId")
	private Long eventId;
	
	@JsonProperty("completeDate")
	private Date completeDate;
	
	@JsonProperty("issueDate")
	private Date issueDate;
	
	@JsonProperty("messageId")
	private String messageId;
	
	@JsonProperty("updatedAllowedFlag")
	private String updatedAllowedFlag;
	

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("createDate")
	private Date createDate;
	

	@JsonProperty("courtTime")
	private Date courtTime;
	
	@JsonProperty("closedDate")
	private Date closedDate;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("expiryTime")
	private Long expiryTime;
	
	@JsonProperty("offenceDate")
	private Date offenceDate;
	
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	
	
	@JsonProperty("creationDate")
	private Date creationDate;
	
	
	@JsonProperty("nonReportFlag")
	private String nonReportFlag;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("arrestAgyLocId")
	private String arrestAgyLocId;
	
	@JsonProperty("courtSeriousnessLevel")
	private String courtSeriousnessLevel;
	
	@JsonProperty("staffWorkId")
	private BigDecimal staffWorkId;
	@JsonProperty("completeStaffId")
	private BigDecimal completeStaffId;
	@JsonProperty("interventionTierCode")
	private String interventionTierCode;
	@JsonProperty("cpsReceivedDate")
	private Date cpsReceivedDate;
	
	@JsonProperty("offenderProceedingId")
	private BigDecimal offenderProceedingId;
	
	@JsonProperty("requestingOfficer")
	private String requestingOfficer;
	
	@JsonProperty("teamId")
	private String teamId;
	
	@JsonProperty("staffMemberId")
	private String staffMemberId;
	
	@JsonProperty("statusDate")
	private Date statusDate;
	
	@JsonProperty("defenceSolicitor")
	private String defenceSolicitor;
	
	
	public BigDecimal getOffenderProceedingId() {
		return offenderProceedingId;
	}


	public void setOffenderProceedingId(BigDecimal offenderProceedingId) {
		this.offenderProceedingId = offenderProceedingId;
	}


	public Date getCpsReceivedDate() {
		return cpsReceivedDate;
	}


	public void setCpsReceivedDate(Date cpsReceivedDate) {
		this.cpsReceivedDate = cpsReceivedDate;
	}


	public String getInterventionTierCode() {
		return interventionTierCode;
	}


	public void setInterventionTierCode(String interventionTierCode) {
		this.interventionTierCode = interventionTierCode;
	}


	public BigDecimal getCompleteStaffId() {
		return completeStaffId;
	}


	public void setCompleteStaffId(BigDecimal completeStaffId) {
		this.completeStaffId = completeStaffId;
	}


	public BigDecimal getStaffWorkId() {
		return staffWorkId;
	}


	public void setStaffWorkId(BigDecimal staffWorkId) {
		this.staffWorkId = staffWorkId;
	}


	public String getOrderSeriousnessLevel() {
		return orderSeriousnessLevel;
	}


	public void setOrderSeriousnessLevel(String orderSeriousnessLevel) {
		this.orderSeriousnessLevel = orderSeriousnessLevel;
	}


	@JsonProperty("orderSeriousnessLevel")
	private String orderSeriousnessLevel;

	public String getCourtSeriousnessLevel() {
		return courtSeriousnessLevel;
	}


	public void setCourtSeriousnessLevel(String courtSeriousnessLevel) {
		this.courtSeriousnessLevel = courtSeriousnessLevel;
	}


	public String getCommentText() {
		return commentText;
	}


	public String getUpdatedAllowedFlag() {
		return updatedAllowedFlag;
	}


	public void setUpdatedAllowedFlag(String updatedAllowedFlag) {
		this.updatedAllowedFlag = updatedAllowedFlag;
	}


	public String getArrestAgyLocId() {
		return arrestAgyLocId;
	}


	public void setArrestAgyLocId(String arrestAgyLocId) {
		this.arrestAgyLocId = arrestAgyLocId;
	}


	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}


	public BigDecimal getOrderId() {
		return orderId;
	}


	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}


	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}


	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}


	public Long getCaseId() {
		return caseId;
	}


	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}


	public Date getCourtDate() {
		return courtDate;
	}


	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public String getIssuingAgyLocId() {
		return issuingAgyLocId;
	}


	public void setIssuingAgyLocId(String issuingAgyLocId) {
		this.issuingAgyLocId = issuingAgyLocId;
	}


	public String getCourtInfoId() {
		return courtInfoId;
	}


	public void setCourtInfoId(String courtInfoId) {
		this.courtInfoId = courtInfoId;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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


	public Date getModifyDatetime() {
		return modifyDatetime;
	}


	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}


	public Date getCreateDatetime() {
		return createDatetime;
	}


	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Date getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}


	public Long getEventId() {
		return eventId;
	}


	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}


	public Date getCompleteDate() {
		return completeDate;
	}


	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}


	public Date getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	public String getSealFlag() {
		return sealFlag;
	}


	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getCourtTime() {
		return courtTime;
	}


	public void setCourtTime(Date courtTime) {
		this.courtTime = courtTime;
	}


	public Date getClosedDate() {
		return closedDate;
	}


	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}




	public Long getExpiryTime() {
		return expiryTime;
	}


	public void setExpiryTime(Long expiryTime) {
		this.expiryTime = expiryTime;
	}


	public Date getOffenceDate() {
		return offenceDate;
	}


	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}


	public Date getEffectiveDate() {
		return effectiveDate;
	}


	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public String getNonReportFlag() {
		return nonReportFlag;
	}


	public void setNonReportFlag(String nonReportFlag) {
		this.nonReportFlag = nonReportFlag;
	}


	public String getRequestingOfficer() {
		return requestingOfficer;
	}


	public void setRequestingOfficer(String requestingOfficer) {
		this.requestingOfficer = requestingOfficer;
	}


	public String getTeamId() {
		return teamId;
	}


	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}


	public String getStaffMemberId() {
		return staffMemberId;
	}


	public void setStaffMemberId(String staffMemberId) {
		this.staffMemberId = staffMemberId;
	}


	public Date getStatusDate() {
		return statusDate;
	}


	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}


	public String getDefenceSolicitor() {
		return defenceSolicitor;
	}


	public void setDefenceSolicitor(String defenceSolicitor) {
		this.defenceSolicitor = defenceSolicitor;
	}


	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

}
