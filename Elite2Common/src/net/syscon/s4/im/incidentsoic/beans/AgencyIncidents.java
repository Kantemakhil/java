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
public class AgencyIncidents extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("reportStaffIdAsCode")
	@NotNull
	private String reportStaffIdAsCode;

	@JsonProperty("reportedStaffId")
	@NotNull
	private Integer reportedStaffId;

	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;

	@JsonProperty("incidentDate")
	@NotNull
	private Date incidentDate;

	@JsonProperty("internalLocationId")
	@NotNull
	private Integer internalLocationId;

	@JsonProperty("incidentTime")
	@NotNull
	private Date incidentTime;

	@JsonProperty("incidentType")
	@NotNull
	@Size(max = 12)
	private String incidentType;

	@JsonProperty("incidentStatus")
	@NotNull
	@Size(max = 12)
	private String incidentStatus;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("lockFlag")
	@NotNull
	@Size(max = 1)
	private String lockFlag;

	@JsonProperty("incidentDetails")
	private String incidentDetails;

	@JsonProperty("reportDate")
	@NotNull
	private Date reportDate;

	@JsonProperty("reportTime")
	@NotNull
	private Date reportTime;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("levelCode")
	private String levelCode;

	@JsonProperty("logNo")
	private String logNo;

	@JsonProperty("incidentText")
	private Clob incidentText;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("createStaffName")
	private String createStaffName;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("interLocationIdDes")
	private String interLocationIdDes;

	@JsonProperty("flag")
	private Boolean flag;

	@JsonProperty("appendDetailesflag")
	private Boolean appendDetailesflag;

	@JsonProperty("fromDate")
	private Date fromDate;

	@JsonProperty("toDate")
	private Date toDate;

	@JsonProperty("incidentTypeDescription")
	private String incidentTypeDescription;

	@JsonProperty("originatingForm")
	private String originatingForm;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("parentForm")
	private String parentForm;
	
	public String getParentForm() {
		return parentForm;
	}

	public void setParentForm(String parentForm) {
		this.parentForm = parentForm;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * Creates new AgencyIncidents class Object
	 */
	public AgencyIncidents() {
		// AgencyIncidents
	}

	/**
	 * @param reportedStaffId
	 *            reportedStaffId to set
	 */
	public void setReportedStaffId(final Integer reportedStaffId) {
		this.reportedStaffId = reportedStaffId;
	}

	/**
	 * return thereportedStaffId
	 */
	public Integer getReportedStaffId() {
		return this.reportedStaffId;
	}

	/**
	 * @param agencyIncidentId
	 *            agencyIncidentId to set
	 */
	public void setAgencyIncidentId(final Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	/**
	 * return the agencyIncidentId
	 */
	public Integer getAgencyIncidentId() {
		return this.agencyIncidentId;
	}

	/**
	 * @param incidentDate
	 *            incidentDate to set
	 */
	public void setIncidentDate(final Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	/**
	 * return the incidentDate
	 */
	public Date getIncidentDate() {
		return this.incidentDate;
	}

	/**
	 * @param internalLocationId
	 *            internalLocationId to set
	 */
	public void setInternalLocationId(final Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * return the internalLocationId
	 */
	public Integer getInternalLocationId() {
		return this.internalLocationId;
	}

	/**
	 * @param incidentTime
	 *            incidentTime to set
	 */
	public void setIncidentTime(final Date incidentTime) {
		this.incidentTime = incidentTime;
	}

	/**
	 * return the incidentTime
	 */
	public Date getIncidentTime() {
		return this.incidentTime;
	}

	/**
	 * @param incidentType
	 *            incidentType to set
	 */
	public void setIncidentType(final String incidentType) {
		this.incidentType = incidentType;
	}

	/**
	 * return the incidentType
	 */
	public String getIncidentType() {
		return this.incidentType;
	}

	/**
	 * @param incidentStatus
	 *            incidentStatus to set
	 */
	public void setIncidentStatus(final String incidentStatus) {
		this.incidentStatus = incidentStatus;
	}

	/**
	 * return the incidentStatus
	 */
	public String getIncidentStatus() {
		return this.incidentStatus;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDateTime(final Date createDatetime) {
		this.createDateTime = createDatetime;
	}

	/**
	 * return theCreateDatetime
	 */
	public Date getCreateDateTime() {
		return this.createDateTime;
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
	public void setAppendDetailesflag(final Boolean appendDetailesflag) {
		this.appendDetailesflag = appendDetailesflag;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
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
	public void setFlag(final Boolean flag) {
		this.flag = flag;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * return the modifyDatetime
	 */
	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	/**
	 * @param lockFlag
	 *            lockFlag to set
	 */
	public void setLockFlag(final String lockFlag) {
		this.lockFlag = lockFlag;
	}

	/**
	 * return the lockFlag
	 */
	public String getLockFlag() {
		return this.lockFlag;
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

	/**
	 * @param reportDate
	 *            reportDate to set
	 */
	public void setReportDate(final Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the createStaffName
	 */
	public String getCreateStaffName() {
		return createStaffName;
	}

	/**
	 * @param createStaffName
	 *            the createStaffName to set
	 */
	public void setCreateStaffName(final String createStaffName) {
		this.createStaffName = createStaffName;
	}

	/**
	 * return thereportDate
	 */
	public Date getReportDate() {
		return this.reportDate;
	}

	/**
	 * @param reportTime
	 *            reportTime to set
	 */
	public void setReportTime(final Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * return the reportTime
	 */
	public Date getReportTime() {
		return this.reportTime;
	}

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return the agyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param levelCode
	 *            levelCode to set
	 */
	public void setLevelCode(final String levelCode) {
		this.levelCode = levelCode;
	}

	/**
	 * return the levelCode
	 */
	public String getLevelCode() {
		return this.levelCode;
	}

	/**
	 * @param logNo
	 *            logNo to set
	 */
	public void setLogNo(final String logNo) {
		this.logNo = logNo;
	}

	/**
	 * return thelogNo
	 */
	public String getLogNo() {
		return this.logNo;
	}

	/**
	 * @param incidentText
	 *            incidentText to set
	 */
	public void setIncidentText(final Clob incidentText) {
		this.incidentText = incidentText;
	}

	/**
	 * return the incidentText
	 */
	public Clob getIncidentText() {
		return this.incidentText;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
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
	 * @return the interLocationIdDes
	 */
	public final String getInterLocationIdDes() {
		return interLocationIdDes;
	}

	/**
	 * @param interLocationIdDes
	 *            the interLocationIdDes to set
	 */
	public final void setInterLocationIdDes(final String interLocationIdDes) {
		this.interLocationIdDes = interLocationIdDes;
	}

	public String getReportStaffIdAsCode() {
		return reportStaffIdAsCode;
	}

	public void setReportStaffIdAsCode(final String reportStaffIdAsCode) {
		this.reportStaffIdAsCode = reportStaffIdAsCode;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the todate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	public String getIncidentTypeDescription() {
		return incidentTypeDescription;
	}

	public void setIncidentTypeDescription(final String incidentTypeDescription) {
		this.incidentTypeDescription = incidentTypeDescription;
	}

	/**
	 * @return the originatingForm
	 */
	public String getOriginatingForm() {
		return originatingForm;
	}

	/**
	 * @param originatingForm
	 *            the originatingForm to set
	 */
	public void setOriginatingForm(final String originatingForm) {
		this.originatingForm = originatingForm;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

}