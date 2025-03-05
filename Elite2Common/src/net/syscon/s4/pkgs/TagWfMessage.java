package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TagWfMessage extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("originalMsgId")
	private BigDecimal originalMsgId;

	@JsonProperty("completeReasonCode")
	private String completeReasonCode;

	@JsonProperty("senderId")
	private String senderId;

	@JsonProperty("completeCommentText")
	private String completeCommentText;

	@JsonProperty("completeUserId")
	private String completeUserId;

	@JsonProperty("assignmentDate")
	private Date assignmentDate;

	@JsonProperty("triggerReason")
	private String triggerReason;

	@JsonProperty("teamMemberId")
	private Long teamMemberId;

	@JsonProperty("workflowType")
	private String workflowType;

	@JsonProperty("teamId")
	private Integer teamId;

	@JsonProperty("staffId")
	private Integer staffId;

	@JsonProperty("msgId")
	private String msgId;

	@JsonProperty("spareNumber")
	private Integer spareNumber;

	@JsonProperty("workTrigger")
	private String workTrigger;

	@JsonProperty("workId")
	private Long workId;

	@JsonProperty("noteSourceCode")
	private String noteSourceCode;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("messageText")
	private String messageText;

	@JsonProperty("spareDate")
	private Date spareDate;

	@JsonProperty("dueDate")
	private Date dueDate;

	@JsonProperty("overrideDueDate")
	private Date overrideDueDate;

	@JsonProperty("dueDatePeriod")
	private Integer dueDatePeriod;
	
	@JsonProperty("eventId")
	private BigDecimal eventId;

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getOverrideDueDate() {
		return overrideDueDate;
	}

	public void setOverrideDueDate(Date overrideDueDate) {
		this.overrideDueDate = overrideDueDate;
	}

	public Integer getDueDatePeriod() {
		return dueDatePeriod;
	}

	public void setDueDatePeriod(Integer dueDatePeriod) {
		this.dueDatePeriod = dueDatePeriod;
	}

	public Date getSpareDate() {
		return spareDate;
	}

	public void setSpareDate(Date spareDate) {
		this.spareDate = spareDate;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the originalMsgId
	 */
	public BigDecimal getOriginalMsgId() {
		return originalMsgId;
	}

	/**
	 * @param originalMsgId the originalMsgId to set
	 */
	public void setOriginalMsgId(BigDecimal originalMsgId) {
		this.originalMsgId = originalMsgId;
	}

	/**
	 * @return the completeReasonCode
	 */
	public String getCompleteReasonCode() {
		return completeReasonCode;
	}

	/**
	 * @param completeReasonCode the completeReasonCode to set
	 */
	public void setCompleteReasonCode(String completeReasonCode) {
		this.completeReasonCode = completeReasonCode;
	}

	/**
	 * @return the completeCommentText
	 */
	public String getCompleteCommentText() {
		return completeCommentText;
	}

	/**
	 * @param completeCommentText the completeCommentText to set
	 */
	public void setCompleteCommentText(String completeCommentText) {
		this.completeCommentText = completeCommentText;
	}

	/**
	 * @return the completeUserId
	 */
	public String getCompleteUserId() {
		return completeUserId;
	}

	/**
	 * @param completeUserId the completeUserId to set
	 */
	public void setCompleteUserId(String completeUserId) {
		this.completeUserId = completeUserId;
	}

	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}

	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	/**
	 * @return the triggerReason
	 */
	public String getTriggerReason() {
		return triggerReason;
	}

	/**
	 * @param triggerReason the triggerReason to set
	 */
	public void setTriggerReason(String triggerReason) {
		this.triggerReason = triggerReason;
	}

	/**
	 * @return the teamMemberId
	 */
	public Long getTeamMemberId() {
		return teamMemberId;
	}

	/**
	 * @param teamMemberId the teamMemberId to set
	 */
	public void setTeamMemberId(Long teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	/**
	 * @return the workflowType
	 */
	public String getWorkflowType() {
		return workflowType;
	}

	/**
	 * @param workflowType the workflowType to set
	 */
	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	/**
	 * @return the teamId
	 */
	public Integer getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the senderId
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the spareNumber
	 */
	public Integer getSpareNumber() {
		return spareNumber;
	}

	/**
	 * @param spareNumber the spareNumber to set
	 */
	public void setSpareNumber(Integer spareNumber) {
		this.spareNumber = spareNumber;
	}

	/**
	 * @return the workTrigger
	 */
	public String getWorkTrigger() {
		return workTrigger;
	}

	/**
	 * @param workTrigger the workTrigger to set
	 */
	public void setWorkTrigger(String workTrigger) {
		this.workTrigger = workTrigger;
	}

	/**
	 * @return the workId
	 */
	public Long getWorkId() {
		return workId;
	}

	/**
	 * @param workId the workId to set
	 */
	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	/**
	 * @return the noteSourceCode
	 */
	public String getNoteSourceCode() {
		return noteSourceCode;
	}

	/**
	 * @param noteSourceCode the noteSourceCode to set
	 */
	public void setNoteSourceCode(String noteSourceCode) {
		this.noteSourceCode = noteSourceCode;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
