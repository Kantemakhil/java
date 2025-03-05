package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourtReport  extends BaseModel implements Serializable {

private static final long serialVersionUID = 1L;
	
	@JsonProperty("orderId")
	private Integer orderId;
	
	@JsonProperty("caseId")
	private Integer caseId;
	
	@JsonProperty("eventId")
	private Integer eventId;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("workflowId")
	private Long workflowId;

	@JsonProperty("reportType")
	private String reportType;
	
	@JsonProperty("nbtReportType")
	private String nbtReportType;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("nbtagyLocDesc")
	private String nbtagyLocDesc;
	
	@JsonProperty("courtSeriousnessLevel")
	private String courtSeriousnessLevel;
	
	@JsonProperty("nbtCourtSeriousnessLevel")
	private String nbtCourtSeriousnessLevel;
	
	@JsonProperty("dateRequested")
	private Date dateRequested;
	
	@JsonProperty("dueDate")
	private Date dueDate;
	
	@JsonProperty("dateOfCompletion")
	private Date dateOfCompletion;
	
	@JsonProperty("courtDate")
	private Date courtDate;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("nbtStatus")
	private String nbtStatus;
	
	@JsonProperty("nonReportFlag")
	private String nonReportFlag;
	
	@JsonProperty("offenderProceedingId")
	private Long offenderProceedingId;
	
	@JsonProperty("functionType")
	private String functionType;
	
	@JsonProperty("areaType")
	private String areaType;
	
	@JsonProperty("area")
	private String area;
	
	@JsonProperty("teamId")
	private Integer teamId;
	
	@JsonProperty("teamMemberId")
	private Integer teamMemberId;
	
	@JsonProperty("teamResponsible")
	private String teamResponsible;
	
	@JsonProperty("teamName")
	private String teamName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;	
	
	@JsonProperty("comments")
	private String comments;

	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("assignmentDate")
	private Date assignmentDate;
	
	@JsonProperty("workType")
	private String workType;
	
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	/**
	 * NON_REPORT_FLAG
	 */
	@JsonProperty("nr")
	private String nr;
	
	private String sourceName;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	
	public Date getCourtDate() {
		return courtDate;
	}

	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
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

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	
	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getNbtReportType() {
		return nbtReportType;
	}

	public void setNbtReportType(String nbtReportType) {
		this.nbtReportType = nbtReportType;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	
	public String getNbtagyLocDesc() {
		return nbtagyLocDesc;
	}

	public void setNbtagyLocDesc(String nbtagyLocDesc) {
		this.nbtagyLocDesc = nbtagyLocDesc;
	}
	
	public String getCourtSeriousnessLevel() {
		return courtSeriousnessLevel;
	}

	public void setCourtSeriousnessLevel(String courtSeriousnessLevel) {
		this.courtSeriousnessLevel = courtSeriousnessLevel;
	}

	public String getNbtCourtSeriousnessLevel() {
		return nbtCourtSeriousnessLevel;
	}

	public void setNbtCourtSeriousnessLevel(String nbtCourtSeriousnessLevel) {
		this.nbtCourtSeriousnessLevel = nbtCourtSeriousnessLevel;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(Date dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNbtStatus() {
		return nbtStatus;
	}

	public void setNbtStatus(String nbtStatus) {
		this.nbtStatus = nbtStatus;
	}

	public String getnonReportFlag() {
		return nonReportFlag;
	}

	public void setnonReportFlag(String nr) {
		this.nonReportFlag = nr;
	}
	
	public String getCommentText() {
		return comments;
	}

	public void setCommentText(String comments) {
		this.comments = comments;
	}

	public Long getOffenderProceedingId() {
		return offenderProceedingId;
	}

	public void setOffenderProceedingId(Long offenderProceedingId) {
		this.offenderProceedingId = offenderProceedingId;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getTeamResponsible() {
		return teamResponsible;
	}

	public void setTeamResponsible(String teamResponsible) {
		this.teamResponsible = teamResponsible;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}
	
}