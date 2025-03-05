package net.syscon.s4.inst.workflow.managingworkassignments;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the V_WORK_ASSIGNMENT_HISTORY database table.
 * 
 */
public class VWorkAssignmentHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date assignmentDate;

	private String assignmentStatus;

	private String detail;

	private BigDecimal offenderBookId;

	private BigDecimal staffId;

	private String staffLastName;

	private String staffName;

	private String staffPosition;

	private String staffRole;

	private BigDecimal taskAssignmentHtyId;

	private BigDecimal taskAssignmentId;

	private String teamCode;

	private String teamDescription;

	private BigDecimal teamId;

	private BigDecimal teamMemberId;

	private BigDecimal workId;

	private BigDecimal workflowHistoryId;

	public VWorkAssignmentHistory() {
	}

	public Date getAssignmentDate() {
		return this.assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public String getAssignmentStatus() {
		return this.assignmentStatus;
	}

	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public String getStaffLastName() {
		return this.staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffPosition() {
		return this.staffPosition;
	}

	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

	public String getStaffRole() {
		return this.staffRole;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	public BigDecimal getTaskAssignmentHtyId() {
		return this.taskAssignmentHtyId;
	}

	public void setTaskAssignmentHtyId(BigDecimal taskAssignmentHtyId) {
		this.taskAssignmentHtyId = taskAssignmentHtyId;
	}

	public BigDecimal getTaskAssignmentId() {
		return this.taskAssignmentId;
	}

	public void setTaskAssignmentId(BigDecimal taskAssignmentId) {
		this.taskAssignmentId = taskAssignmentId;
	}

	public String getTeamCode() {
		return this.teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamDescription() {
		return this.teamDescription;
	}

	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	public BigDecimal getTeamId() {
		return this.teamId;
	}

	public void setTeamId(BigDecimal teamId) {
		this.teamId = teamId;
	}

	public BigDecimal getTeamMemberId() {
		return this.teamMemberId;
	}

	public void setTeamMemberId(BigDecimal teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public BigDecimal getWorkId() {
		return this.workId;
	}

	public void setWorkId(BigDecimal workId) {
		this.workId = workId;
	}

	public BigDecimal getWorkflowHistoryId() {
		return this.workflowHistoryId;
	}

	public void setWorkflowHistoryId(BigDecimal workflowHistoryId) {
		this.workflowHistoryId = workflowHistoryId;
	}

}
