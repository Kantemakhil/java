package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class Message extends BaseModel implements Serializable{
	private String messageText;
	private Date dueDate;
	private String severity;
	private String acknowledgementReqired;
	private String acknowledgementSubject;
	private Long offenderBookId;
	private Long workId;
	private Long teamId;
	private Long staffId;
	private String workFlowType;
	private Date eventDate;
	private String workTriggger;
	private String spareText;
	public String getSpareText() {
		return spareText;
	}
	public void setSpareText(String spareText) {
		this.spareText = spareText;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public String getWorkTriggger() {
		return workTriggger;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public void setWorkTriggger(String workTriggger) {
		this.workTriggger = workTriggger;
	}
	public Date geteventDate() {
		return eventDate;
	}
	public void seteventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getAcknowledgementReqired() {
		return acknowledgementReqired;
	}
	public void setAcknowledgementReqired(String acknowledgementReqired) {
		this.acknowledgementReqired = acknowledgementReqired;
	}
	public String getAcknowledgementSubject() {
		return acknowledgementSubject;
	}
	public void setAcknowledgementSubject(String acknowledgementSubject) {
		this.acknowledgementSubject = acknowledgementSubject;
	}
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public String getWorkFlowType() {
		return workFlowType;
	}
	public void setWorkFlowType(String workFlowType) {
		this.workFlowType = workFlowType;
	}
	public Long getTeamMemberId() {
		return teamMemberId;
	}
	public void setTeamMemberId(Long teamMemberId) {
		this.teamMemberId = teamMemberId;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public Date getSpareDate() {
		return spareDate;
	}
	public void setSpareDate(Date spareDate) {
		this.spareDate = spareDate;
	}
	public String getTriggereReason() {
		return triggereReason;
	}
	public void setTriggereReason(String triggereReason) {
		this.triggereReason = triggereReason;
	}
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	public String getNoteSourseCode() {
		return noteSourseCode;
	}
	public void setNoteSourseCode(String noteSourseCode) {
		this.noteSourseCode = noteSourseCode;
	}
	public String getOriginalMsGrid() {
		return originalMsGrid;
	}
	public void setOriginalMsGrid(String originalMsGrid) {
		this.originalMsGrid = originalMsGrid;
	}
	public Integer getSpareNumber() {
		return spareNumber;
	}
	public void setSpareNumber(Integer spareNumber) {
		this.spareNumber = spareNumber;
	}
	public String getCompleteCommentText() {
		return completeCommentText;
	}
	public void setCompleteCommentText(String completeCommentText) {
		this.completeCommentText = completeCommentText;
	}
	private Long teamMemberId;
	private Date assignDate;
	private Date spareDate;
	private String triggereReason;
	private String workFlowId;
	private String noteSourseCode;
	private String originalMsGrid;
	private Integer spareNumber;
	private String completeCommentText;
	

}


