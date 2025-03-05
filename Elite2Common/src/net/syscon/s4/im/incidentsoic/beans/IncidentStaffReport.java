package net.syscon.s4.im.incidentsoic.beans;
import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class IncidentStaffReport extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("incidentReportId")
	@NotNull
	private Integer incidentReportId;
	
	@JsonProperty("reporterStaffId")
	@NotNull
	private Integer reporterStaffId;
	
	@JsonProperty("reportDate")
	@NotNull
	private Date reportDate;
	
	@JsonProperty("reportTime")
	@NotNull
	private Date reportTime;
	
	@JsonProperty("reportType")
	@NotNull
	private String reportType;
	
	@JsonProperty("lockFlag")
	@NotNull
	private String lockFlag;
	
	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;
	
	@JsonProperty("createUserId")
	@NotNull
	private String createUserId;
	
	@JsonProperty("modifyUserId")
	@NotNull
	private String modifyUserId;
	
	@JsonProperty("modifyDateTime")
	@NotNull
	private Date modifyDateTime;
	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;
	
	@JsonProperty("partySeq")
	@NotNull
	private  Integer partySeq;
	
	@JsonProperty("sealFlag")
	@NotNull
	private String sealFlag;
	
	@JsonProperty("reportDetails")
	private String reportDetails;
	
	@JsonProperty("lockedBy")
	private Integer lockedBy;
	
	@JsonProperty("lockReferenceTime")
	private Date lockReferenceTime;
	
	
	@JsonProperty("repCompletFlag")
	private String repCompletFlag;
	
	@JsonProperty("staffId")
	private Integer staffId;
	
	@JsonProperty("incidentDetails")
	private String incidentDetails;
	
	
	public String getIncidentDetails() {
		return incidentDetails;
	}
	public void setIncidentDetails(String incidentDetails) {
		this.incidentDetails = incidentDetails;
	}
	public Integer getIncidentReportId() {
		return incidentReportId;
	}
	public void setIncidentReportId(Integer incidentReportId) {
		this.incidentReportId = incidentReportId;
	}

	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}
	public void setAgencyIncidentId(Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}
	public Integer getPartySeq() {
		return partySeq;
	}
	public void setPartySeq(Integer partySeq) {
		this.partySeq = partySeq;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public Integer getLockedBy() {
		return lockedBy;
	}
	public void setLockedBy(Integer lockedBy) {
		this.lockedBy = lockedBy;
	}
	public Date getLockReferenceTime() {
		return lockReferenceTime;
	}
	public void setLockReferenceTime(Date lockReferenceTime) {
		this.lockReferenceTime = lockReferenceTime;
	}
	public String getReportDetails() {
		return reportDetails;
	}
	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}
	public String getRepCompletFlag() {
		return repCompletFlag;
	}
	public void setRepCompletFlag(String repCompletFlag) {
		this.repCompletFlag = repCompletFlag;
	}
	public Integer getReporterStaffId() {
		return reporterStaffId;
	}
	public void setReporterStaffId(Integer reporterStaffId) {
		this.reporterStaffId = reporterStaffId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

}
