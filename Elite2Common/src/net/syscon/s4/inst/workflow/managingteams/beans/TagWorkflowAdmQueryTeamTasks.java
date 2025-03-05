package net.syscon.s4.inst.workflow.managingteams.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TagWorkflowAdmQueryTeamTasks extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("pCaseloadId")
	private String pCaseloadId;
	
	@JsonProperty("workId")
	private BigDecimal workId;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("pOffenderBookId")
	private Integer pOffenderBookId;

	@JsonProperty("pWorkType")
	private String pWorkType;

	@JsonProperty("pWorkSubType")
	private String pWorkSubType;

	@JsonProperty("pTeamId")
	private Integer pTeamId;

	@JsonProperty("pStaffId")
	private Integer pStaffId;

	@JsonProperty("pCompletionStatus")
	private String pCompletionStatus;

	@JsonProperty("pDueFromDate")
	private Date pDueFromDate;

	@JsonProperty("pDueToDate")
	private Date pDueToDate;

	@JsonProperty("assignmentDate")
	private String assignmentDate;

	@JsonProperty("workType")
	private String workType;

	@JsonProperty("workTypeDesc")
	private String workTypeDesc;
	
	@JsonProperty("workTypeDescription")
	private String workTypeDescription;

	@JsonProperty("officerName")
	private String officerName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("offenderName")
	private String offenderName;

	@JsonProperty("dueDate")
	private String dueDate;

	@JsonProperty("completionDate")
	private String completionDate;

	@JsonProperty("completeReasonDesc")
	private String completeReasonDesc;

	@JsonProperty("details")
	private String details;
	
	@JsonProperty("offenderLastName")
	private String offenderLastName;
	
	@JsonProperty("offenderFirstName")
	private String offenderFirstName;
	
	@JsonProperty("taskAssignmentHtyId")
	private long taskAssignmentHtyId;
	
	@JsonProperty("workflowHistoryId")
	private long workflowHistoryId;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("workSubType")
	private String workSubType;
	
	@JsonProperty("completeReasonCode")
	private String completeReasonCode;

	
	
	public String getCompleteReasonCode() {
		return completeReasonCode;
	}

	public void setCompleteReasonCode(String completeReasonCode) {
		this.completeReasonCode = completeReasonCode;
	}

	public long getTaskAssignmentHtyId() {
		return taskAssignmentHtyId;
	}

	public void setTaskAssignmentHtyId(long taskAssignmentHtyId) {
		this.taskAssignmentHtyId = taskAssignmentHtyId;
	}

	public long getWorkflowHistoryId() {
		return workflowHistoryId;
	}

	public void setWorkflowHistoryId(long workflowHistoryId) {
		this.workflowHistoryId = workflowHistoryId;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getWorkSubType() {
		return workSubType;
	}

	public void setWorkSubType(String workSubType) {
		this.workSubType = workSubType;
	}

	public String getOffenderLastName() {
		return offenderLastName;
	}

	public void setOffenderLastName(String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}

	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	public void setOffenderFirstName(String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	public String getpCaseloadId() {
		return pCaseloadId;
	}

	public void setpCaseloadId(String pCaseloadId) {
		this.pCaseloadId = pCaseloadId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Integer getpOffenderBookId() {
		return pOffenderBookId;
	}

	public void setpOffenderBookId(Integer pOffenderBookId) {
		this.pOffenderBookId = pOffenderBookId;
	}

	public String getpWorkType() {
		return pWorkType;
	}

	public void setpWorkType(String pWorkType) {
		this.pWorkType = pWorkType;
	}

	public String getpWorkSubType() {
		return pWorkSubType;
	}

	public void setpWorkSubType(String pWorkSubType) {
		this.pWorkSubType = pWorkSubType;
	}

	public Integer getpTeamId() {
		return pTeamId;
	}

	public void setpTeamId(Integer pTeamId) {
		this.pTeamId = pTeamId;
	}

	public Integer getpStaffId() {
		return pStaffId;
	}

	public void setpStaffId(Integer pStaffId) {
		this.pStaffId = pStaffId;
	}

	public String getpCompletionStatus() {
		return pCompletionStatus;
	}

	public void setpCompletionStatus(String pCompletionStatus) {
		this.pCompletionStatus = pCompletionStatus;
	}

	public Date getpDueFromDate() {
		return pDueFromDate;
	}

	public void setpDueFromDate(Date pDueFromDate) {
		this.pDueFromDate = pDueFromDate;
	}

	public Date getpDueToDate() {
		return pDueToDate;
	}

	public void setpDueToDate(Date pDueToDate) {
		this.pDueToDate = pDueToDate;
	}

	public String getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(String assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkTypeDescription() {
		return workTypeDescription;
	}

	public void setWorkTypeDescription(String workTypeDescription) {
		this.workTypeDescription = workTypeDescription;
	}
	
	public String getWorkTypeDesc() {
		return workTypeDesc;
	}

	public void setWorkTypeDesc(String workTypeDesc) {
		this.workTypeDesc = workTypeDesc;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getCompleteReasonDesc() {
		return completeReasonDesc;
	}

	public void setCompleteReasonDesc(String completeReasonDesc) {
		this.completeReasonDesc = completeReasonDesc;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getWorkId() {
		return this.workId;
	}

	public void setWorkId(final BigDecimal workId) {
		this.workId = workId;
	}
	
	private String modifyUserId;
	
	
	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	@Override
	public String toString() {
		return "TagWorkflowAdmQueryTeamTasks [pCaseloadId=" + pCaseloadId + ",caseloadId=" + caseloadId + ", pOffenderBookId=" + pOffenderBookId
				+ ", pWorkType=" + pWorkType + ", pWorkSubType=" + pWorkSubType + ", pTeamId=" + pTeamId + ", pStaffId="
				+ pStaffId + ", pCompletionStatus=" + pCompletionStatus + ", pDueFromDate=" + pDueFromDate
				+ ", pDueToDate=" + pDueToDate + ", assignmentDate=" + assignmentDate + ", workType=" + workType
				+ ", workTypeDesc=" + workTypeDesc + ", officerName=" + officerName + ", offenderIdDisplay="
				+ offenderIdDisplay + ", offenderName=" + offenderName + ", dueDate=" + dueDate + ", completionDate="
				+ completionDate + ", completeReasonDesc=" + completeReasonDesc + ", details=" + details + ", details=" + details + ", workTypeDescription=" + workTypeDescription + "]";
	}



}
