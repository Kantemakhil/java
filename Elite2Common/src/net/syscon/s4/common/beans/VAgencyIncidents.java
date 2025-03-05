package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

/**
 * Class VAgencyIncidents
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VAgencyIncidents extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;

	@JsonProperty("reportedStaffId")
	@NotNull
	private Integer reportedStaffId;

	@JsonProperty("staffFirstName")
	private String staffFirstName;

	@JsonProperty("staffLastName")
	private String staffLastName;

	@JsonProperty("incidentDate")
	@NotNull
	private Date incidentDate;

	@JsonProperty("incidentTime")
	@NotNull
	private Date incidentTime;

	@JsonProperty("internalLocationId")
	@NotNull
	private Integer internalLocationId;
	
	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@JsonProperty("incidentType")
	@NotNull
	@Size(max = 12)
	private String incidentType;

	@JsonProperty("incidentTypeDesc")
	private String incidentTypeDesc;

	@JsonProperty("incidentStatus")
	@NotNull
	@Size(max = 12)
	private String incidentStatus;

	@JsonProperty("reportDate")
	@NotNull
	private Date reportDate;

	@JsonProperty("reportTime")
	@NotNull
	private Date reportTime;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("intLocDescription")
	private String intLocDescription;

	@JsonProperty("repairFlag")
	private String repairFlag;

	@JsonProperty("incidentDetails")
	private String incidentDetails;

	@JsonProperty("fromDate")
	private Date fromDate;

	@JsonProperty("toDate")
	private Date toDate;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("reportid")
	private String reportid;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("lockFlag")
	@NotNull
	@Size(max = 1)
	private String lockFlag;

	@JsonProperty("flag")
	private Boolean flag;

	@JsonProperty("appendDetailesflag")
	private Boolean appendDetailesflag;

	@JsonProperty("code")
	private String code;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("caseloadType")
	private String caseloadType;
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("description")
	private String description;
	/**
	 * Creates new VAgencyIncidents class Object
	 */
	public VAgencyIncidents() {
	}

	/**
	 * @return the agencyIncidentId
	 */
	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}

	/**
	 * @param agencyIncidentId
	 *            the agencyIncidentId to set
	 */
	public void setAgencyIncidentId(final Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	/**
	 * @return the reportedStaffId
	 */
	public Integer getReportedStaffId() {
		return reportedStaffId;
	}

	/**
	 * @param reportedStaffId
	 *            the reportedStaffId to set
	 */
	public void setReportedStaffId(final Integer reportedStaffId) {
		this.reportedStaffId = reportedStaffId;
	}

	/**
	 * @return the staffFirstName
	 */
	public String getStaffFirstName() {
		return staffFirstName;
	}

	/**
	 * @param staffFirstName
	 *            the staffFirstName to set
	 */
	public void setStaffFirstName(final String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	/**
	 * @return the staffLastName
	 */
	public String getStaffLastName() {
		return staffLastName;
	}

	/**
	 * @param staffLastName
	 *            the staffLastName to set
	 */
	public void setStaffLastName(final String staffLastName) {
		this.staffLastName = staffLastName;
	}

	/**
	 * @return the incidentDate
	 */
	public Date getIncidentDate() {
		return incidentDate;
	}

	/**
	 * @param incidentDate
	 *            the incidentDate to set
	 */
	public void setIncidentDate(final Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	/**
	 * @return the incidentTime
	 */
	public Date getIncidentTime() {
		return incidentTime;
	}

	/**
	 * @param incidentTime
	 *            the incidentTime to set
	 */
	public void setIncidentTime(final Date incidentTime) {
		this.incidentTime = incidentTime;
	}

	/**
	 * @return the appendDetailesflag
	 */
	public Boolean getAppendDetailesflag() {
		return appendDetailesflag;
	}

	/**
	 * @param appendDetailesflag
	 *            the appendDetailesflag to set
	 */
	public void setAppendDetailesflag(Boolean appendDetailesflag) {
		this.appendDetailesflag = appendDetailesflag;
	}

	/**
	 * @return the internalLocationId
	 */
	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId
	 *            the internalLocationId to set
	 */
	public void setInternalLocationId(final Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * @return the incidentType
	 */
	public String getIncidentType() {
		return incidentType;
	}

	/**
	 * @param incidentType
	 *            the incidentType to set
	 */
	public void setIncidentType(final String incidentType) {
		this.incidentType = incidentType;
	}

	/**
	 * @return the lockFlag
	 */
	public String getLockFlag() {
		return lockFlag;
	}

	/**
	 * @param lockFlag
	 *            the lockFlag to set
	 */
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	/**
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the reportid
	 */
	public String getReportid() {
		return reportid;
	}

	/**
	 * @param reportid
	 *            the reportid to set
	 */
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	/**
	 * @return the incidentTypeDesc
	 */
	public String getIncidentTypeDesc() {
		return incidentTypeDesc;
	}

	/**
	 * @param incidentTypeDesc
	 *            the incidentTypeDesc to set
	 */
	public void setIncidentTypeDesc(final String incidentTypeDesc) {
		this.incidentTypeDesc = incidentTypeDesc;
	}

	/**
	 * @return the incidentStatus
	 */
	public String getIncidentStatus() {
		return incidentStatus;
	}

	/**
	 * @param incidentStatus
	 *            the incidentStatus to set
	 */
	public void setIncidentStatus(final String incidentStatus) {
		this.incidentStatus = incidentStatus;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate
	 *            the reportDate to set
	 */
	public void setReportDate(final Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}

	/**
	 * @param reportTime
	 *            the reportTime to set
	 */
	public void setReportTime(final Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the intLocDescription
	 */
	public String getIntLocDescription() {
		return intLocDescription;
	}

	/**
	 * @param intLocDescription
	 *            the intLocDescription to set
	 */
	public void setIntLocDescription(final String intLocDescription) {
		this.intLocDescription = intLocDescription;
	}

	/**
	 * @return the repairFlag
	 */
	public String getRepairFlag() {
		return repairFlag;
	}

	/**
	 * @param repairFlag
	 *            the repairFlag to set
	 */
	public void setRepairFlag(final String repairFlag) {
		this.repairFlag = repairFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @param incidentDetails
	 *            incidentDetails to set
	 */
	public void setIncidentDetails(final String incidentDetails) {
		this.incidentDetails = incidentDetails;
	}

	/**
	 * return the incidentDetails
	 */
	public String getIncidentDetails() {
		return this.incidentDetails;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}