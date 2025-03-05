package net.syscon.s4.cm.teamsworkflow.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the TASK_ASSIGNMENT_HTY database table.
 * 
 */
public class TaskAssignmentHty  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("taskAssignmentHtyId")
	private Long taskAssignmentHtyId;
	
	@JsonProperty("teamId")
	private Long teamId;
	
	@JsonProperty("flag")
	private Boolean flag;
	
	@JsonProperty("assignmentDate")
	private Date assignmentDate;

	@JsonProperty("assignmentStatus")
	private String assignmentStatus;
	
	@JsonProperty("completeCommentText")
	private String completeCommentText;

	@JsonProperty("completeReasonCode")
	private String completeReasonCode;
	
	@JsonProperty("workTypeDescription")
	private String workTypeDescription;

	@JsonProperty("completeUserId")
	private String completeUserId;

	@JsonProperty("completionDate")
	private Date completionDate;

	
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("details")
	private String details;

	@JsonProperty("dueDate")
	private Date dueDate;

	@JsonProperty("eventDate")
	private Date eventDate;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("messageId")
	private String messageId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("originalMessageId")
	private String originalMessageId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("staffId")
	private BigDecimal staffId;

	@JsonProperty("taskAssignmentId")
	private BigDecimal taskAssignmentId;

	@JsonProperty("triggerName")
	private String triggerName;

	@JsonProperty("workId")
	private BigDecimal workId;

	@JsonProperty("workflowHistoryId")
	private BigDecimal workflowHistoryId;
	
	@JsonProperty("workType")
	private String workType;
	
	@JsonProperty("workSubType")
	private String workSubType;
	
	@JsonProperty("teamCode")
	private Integer teamCode;
	
	@JsonProperty("officerName")
	private String officerName;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getTaskAssignmentHtyId() {
		return taskAssignmentHtyId;
	}

	public TaskAssignmentHty() {
		// TaskAssignmentHty
	}

	public Date getAssignmentDate() {
		return this.assignmentDate;
	}

	public void setAssignmentDate(final Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public String getAssignmentStatus() {
		return this.assignmentStatus;
	}

	public void setAssignmentStatus(final String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public String getCompleteCommentText() {
		return this.completeCommentText;
	}

	public void setCompleteCommentText(final String completeCommentText) {
		this.completeCommentText = completeCommentText;
	}

	public String getCompleteReasonCode() {
		return this.completeReasonCode;
	}

	public void setCompleteReasonCode(final String completeReasonCode) {
		this.completeReasonCode = completeReasonCode;
	}

	public String getCompleteUserId() {
		return this.completeUserId;
	}

	public void setCompleteUserId(final String completeUserId) {
		this.completeUserId = completeUserId;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(final Date completionDate) {
		this.completionDate = completionDate;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public Object getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(final Date dueDate) {
		this.dueDate = dueDate;
	}

	public Object getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}

	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(final String messageId) {
		this.messageId = messageId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOriginalMessageId() {
		return this.originalMessageId;
	}

	public void setOriginalMessageId(final String originalMessageId) {
		this.originalMessageId = originalMessageId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(final BigDecimal staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getTaskAssignmentId() {
		return this.taskAssignmentId;
	}

	public void setTaskAssignmentId(final BigDecimal taskAssignmentId) {
		this.taskAssignmentId = taskAssignmentId;
	}

	public String getTriggerName() {
		return this.triggerName;
	}

	public void setTriggerName(final String triggerName) {
		this.triggerName = triggerName;
	}

	public BigDecimal getWorkId() {
		return this.workId;
	}

	public void setWorkId(final BigDecimal workId) {
		this.workId = workId;
	}

	public BigDecimal getWorkflowHistoryId() {
		return this.workflowHistoryId;
	}

	public void setWorkflowHistoryId(final BigDecimal workflowHistoryId) {
		this.workflowHistoryId = workflowHistoryId;
	}

	/**
	 * @return the workType
	 */
	public String getWorkType() {
		return workType;
	}

	/**
	 * @param workType the workType to set
	 */
	public void setWorkType(final String workType) {
		this.workType = workType;
	}

	/**
	 * @return the workSubType
	 */
	public String getWorkSubType() {
		return workSubType;
	}

	/**
	 * @param workSubType the workSubType to set
	 */
	public void setWorkSubType(final String workSubType) {
		this.workSubType = workSubType;
	}

	/**
	 * @return the teamCode
	 */
	public Integer getTeamCode() {
		return teamCode;
	}

	/**
	 * @param teamCode the teamCode to set
	 */
	public void setTeamCode(final Integer teamCode) {
		this.teamCode = teamCode;
	}

	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}

	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(final String officerName) {
		this.officerName = officerName;
	}

	/**
	 * @param taskAssignmentHtyId the taskAssignmentHtyId to set
	 */
	public void setTaskAssignmentHtyId(final Long taskAssignmentHtyId) {
		this.taskAssignmentHtyId = taskAssignmentHtyId;
	}
	
	public String getWorkTypeDescription() {
		return workTypeDescription;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public void setWorkTypeDescription(String workTypeDescription) {
		this.workTypeDescription = workTypeDescription;
	}

}
