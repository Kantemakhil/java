package net.syscon.s4.cm.teamsworkflow.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TagWorkflowBrowseQueue extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("teamId")
	private Integer teamId;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("workId")
	private Integer workId;

	@JsonProperty("queueName")
	private String queueName;

	@JsonProperty("staffId")
	private Integer staffId;

	@JsonProperty("teamMemberId")
	private Integer teamMemberId;

	@JsonProperty("assignmentDate")
	private Date assignmentDate;

	@JsonProperty("dueDate")
	private Date dueDate;

	@JsonProperty("msgId")
	private String msgId;

	@JsonProperty("messageText")
	private String messageText;

	@JsonProperty("workflowType")
	private String workflowType;

	@JsonProperty("originalMsgId")
	private String originalMsgId;

	@JsonProperty("eventDate")
	private Date eventDate;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("acknowledgementRequired")
	private String acknowledgementRequired;

	@JsonProperty("acknowledgementSubject")
	private String acknowledgementSubject;

	@JsonProperty("senderId")
	private String senderId;

	@JsonProperty("severityCode")
	private String severityCode;

	@JsonProperty("clusterId")
	private String clusterId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("workType")
	private String workType;

	@JsonProperty("workSubType")
	private String workSubType;

	@JsonProperty("completeReasonode")
	private String completeReasonode;

	@JsonProperty("completeCommentText")
	private String completeCommentText;

	@JsonProperty("completeUserId")
	private String completeUserId;

	@JsonProperty("assignedFlag")
	private Boolean assignedFlag;

	@JsonProperty("completeFlag")
	private Boolean completeFlag;

	@JsonProperty("assignOtherTeam")
	private Boolean assignOtherTeam;

	@JsonProperty("assignedTeamId")
	private Integer assignedTeamId;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("manualCloseFlag")
	private String manualCloseFlag;

	@JsonProperty("rootOffenderId")
	private Long rootOffenderId;

	@JsonProperty("enableOrDisable")
	private int enableOrDisable;

	@JsonProperty("taskAssignmentHtyId")
	private Long taskAssignmentHtyId;

	@JsonProperty("taskAssignmentId")
	private BigDecimal taskAssignmentId;

	@JsonProperty("dateRequested")
	private Date dateRequested;

	@JsonProperty("orderId")
	private Integer orderId;

	@JsonProperty("taskId")
	private String taskId;
	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	@JsonProperty("teamCode")
	private String teamCode;

	@JsonProperty("assignedTeamCode")
	private String assignedTeamCode;

	@JsonProperty("completionDate")
	private Date completionDate;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	private String sourceName;

	private String description;

	private String processDefinitionId;

	private String userId;

	private String assignee;

	private Boolean assignToOtherUser;

	private Boolean assignToUser;

	private String assigneeOtherUser;

	private Boolean assignFromUserToTeam;
	
	@JsonProperty("workflowTypeDesc")
	private String workflowTypeDesc;

	@JsonProperty("workTypeDesc")
	private String workTypeDesc;

	@JsonProperty("workSubTypeDesc")
	private String workSubTypeDesc;

	/**
	 * @return the enableOrDisable
	 */
	public int getEnableOrDisable() {
		return enableOrDisable;
	}

	/**
	 * @param enableOrDisable
	 *            the enableOrDisable to set
	 */
	public void setEnableOrDisable(final int enableOrDisable) {
		this.enableOrDisable = enableOrDisable;
	}

	public Integer getTeamId() {
		return teamId;
	}

	/**
	 * @return the rootOffenderId
	 */
	public Long getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(final Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public void setTeamId(final Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(final Integer workId) {
		this.workId = workId;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(final String queueName) {
		this.queueName = queueName;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(final Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(final Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(final Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(final String msgId) {
		this.msgId = msgId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(final String messageText) {
		this.messageText = messageText;
	}

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(final String workflowType) {
		this.workflowType = workflowType;
	}

	public String getOriginalMsgId() {
		return originalMsgId;
	}

	public void setOriginalMsgId(final String originalMsgId) {
		this.originalMsgId = originalMsgId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}

	public String getAcknowledgementRequired() {
		return acknowledgementRequired;
	}

	public void setAcknowledgementRequired(final String acknowledgementRequired) {
		this.acknowledgementRequired = acknowledgementRequired;
	}

	public String getAcknowledgementSubject() {
		return acknowledgementSubject;
	}

	public void setAcknowledgementSubject(final String acknowledgementSubject) {
		this.acknowledgementSubject = acknowledgementSubject;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(final String senderId) {
		this.senderId = senderId;
	}

	public String getSeverityCode() {
		return severityCode;
	}

	public void setSeverityCode(final String severityCode) {
		this.severityCode = severityCode;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(final String clusterId) {
		this.clusterId = clusterId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(final String workType) {
		this.workType = workType;
	}

	public String getWorkSubType() {
		return workSubType;
	}

	public void setWorkSubType(final String workSubType) {
		this.workSubType = workSubType;
	}

	public String getCompleteReasonode() {
		return completeReasonode;
	}

	public void setCompleteReasonode(final String completeReasonode) {
		this.completeReasonode = completeReasonode;
	}

	public String getCompleteCommentText() {
		return completeCommentText;
	}

	public void setCompleteCommentText(final String completeCommentText) {
		this.completeCommentText = completeCommentText;
	}

	public String getCompleteUserId() {
		return completeUserId;
	}

	public void setCompleteUserId(final String completeUserId) {
		this.completeUserId = completeUserId;
	}

	public Boolean getAssignedFlag() {
		return assignedFlag;
	}

	public void setAssignedFlag(final Boolean assignedFlag) {
		this.assignedFlag = assignedFlag;
	}

	public Boolean getCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(final Boolean completeFlag) {
		this.completeFlag = completeFlag;
	}

	public Boolean getAssignOtherTeam() {
		return assignOtherTeam;
	}

	public void setAssignOtherTeam(final Boolean assignOtherTeam) {
		this.assignOtherTeam = assignOtherTeam;
	}

	public Integer getAssignedTeamId() {
		return assignedTeamId;
	}

	public void setAssignedTeamId(final Integer assignedTeamId) {
		this.assignedTeamId = assignedTeamId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	public String getManualCloseFlag() {
		return manualCloseFlag;
	}

	public void setManualCloseFlag(final String manualCloseFlag) {
		this.manualCloseFlag = manualCloseFlag;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(final String sourceName) {
		this.sourceName = sourceName;
	}

	public Long getTaskAssignmentHtyId() {
		return taskAssignmentHtyId;
	}

	public void setTaskAssignmentHtyId(final Long taskAssignmentHtyId) {
		this.taskAssignmentHtyId = taskAssignmentHtyId;
	}

	public BigDecimal getTaskAssignmentId() {
		return taskAssignmentId;
	}

	public void setTaskAssignmentId(final BigDecimal taskAssignmentId) {
		this.taskAssignmentId = taskAssignmentId;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(final Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(final Integer orderId) {
		this.orderId = orderId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(final String teamCode) {
		this.teamCode = teamCode;
	}

	public String getAssignedTeamCode() {
		return assignedTeamCode;
	}

	public void setAssignedTeamCode(final String assignedTeamCode) {
		this.assignedTeamCode = assignedTeamCode;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(final Date completionDate) {
		this.completionDate = completionDate;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(final String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(final String assignee) {
		this.assignee = assignee;
	}

	public Boolean getAssignToOtherUser() {
		return assignToOtherUser;
	}

	public void setAssignToOtherUser(final Boolean assignToOtherUser) {
		this.assignToOtherUser = assignToOtherUser;
	}

	public Boolean getAssignToUser() {
		return assignToUser;
	}

	public void setAssignToUser(final Boolean assignToUser) {
		this.assignToUser = assignToUser;
	}

	public String getAssigneeOtherUser() {
		return assigneeOtherUser;
	}

	public void setAssigneeOtherUser(final String assigneeOtherUser) {
		this.assigneeOtherUser = assigneeOtherUser;
	}

	public Boolean getAssignFromUserToTeam() {
		return assignFromUserToTeam;
	}

	public void setAssignFromUserToTeam(final Boolean assignFromUserToTeam) {
		this.assignFromUserToTeam = assignFromUserToTeam;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(final String taskId) {
		this.taskId = taskId;
	}

	public String getWorkflowTypeDesc() {
		return workflowTypeDesc;
	}

	public void setWorkflowTypeDesc(String workflowTypeDesc) {
		this.workflowTypeDesc = workflowTypeDesc;
	}

	public String getWorkTypeDesc() {
		return workTypeDesc;
	}

	public void setWorkTypeDesc(String workTypeDesc) {
		this.workTypeDesc = workTypeDesc;
	}

	public String getWorkSubTypeDesc() {
		return workSubTypeDesc;
	}

	public void setWorkSubTypeDesc(String workSubTypeDesc) {
		this.workSubTypeDesc = workSubTypeDesc;
	}

}
