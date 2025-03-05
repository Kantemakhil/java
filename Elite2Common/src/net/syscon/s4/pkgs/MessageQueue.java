package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class MessageQueue extends BaseModel implements Serializable{
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
	private Long teamMemberId;
	
	private Date requestDate;
	
	private String funType;

	
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getFunType() {
		return funType;
	}
	public void setFunType(String funType) {
		this.funType = funType;
	}
	public Long getTeamMemberId() {
		return teamMemberId;
	}
	public void setTeamMemberId(Long teamMemberId) {
		this.teamMemberId = teamMemberId;
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
	

}
