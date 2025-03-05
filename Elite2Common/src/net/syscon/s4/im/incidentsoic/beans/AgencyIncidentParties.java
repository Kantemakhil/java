package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyIncidentParties extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;

	@JsonProperty("partySeq")
	@NotNull
	private Integer partySeq;

	@JsonProperty("incidentRole")
	@NotNull
	@Size(max = 12)
	private String incidentRole;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("staffId")
	private Integer staffId;

	@JsonProperty("personId")
	private Integer personId;

	@JsonProperty("dispositionType")
	private String dispositionType;

	@JsonProperty("dispositionDate")
	private Date dispositionDate;

	@JsonProperty("oicIncidentId")
	private Integer oicIncidentId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("actionCode")
	private String actionCode;

	@JsonProperty("partyAddedDate")
	@NotNull
	private Date partyAddedDate;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("repCompletFlag")
	private String repCompletFlag;

	// @JsonProperty("offenderId")
	// private Integer offenderId;

	@JsonProperty("staffIdDes")
	private String staffIdDes;

	@JsonProperty("incidentRoleDes")
	private String incidentRoleDes;

	@JsonProperty("lname")
	private String lname;

	@JsonProperty("fname")
	private String fname;

	@JsonProperty("actionCodeDes")
	private String actionCodeDes;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("code")
	private String code;

	@JsonProperty("offenderIdDisplay")
	@Size(max = 10)
	private String offenderIdDisplay;

	@JsonProperty("affiliation")
	private String affiliation;

	@JsonProperty("roleDescription")
	private String roleDescription;

	@JsonProperty("stgId")
	private BigDecimal stgId;

	@JsonProperty("description")
	private String description;
	
	@JsonProperty("forceUsedFlag")
	private String forceUsedFlag;
	
	@JsonProperty("reportTypeFlag")
	private String reportTypeFlag;
	
	@JsonProperty("staffReportType")
	private String staffReportType;
	
	@JsonProperty("lockReferenceTime")
	private Date lockReferenceTime; 
	
	
	@JsonProperty("reporterStaffId")
	private Integer reporterStaffId;
	
	@JsonProperty("reportType")
	private String reportType;
	
	@JsonProperty("incidentReportId")
	private Integer incidentReportId;
	
	public Integer getIncidentReportId() {
		return incidentReportId;
	}

	public void setIncidentReportId(Integer incidentReportId) {
		this.incidentReportId = incidentReportId;
	}

	public String getForceUsedFlag() {
		return forceUsedFlag;
	}

	public void setForceUsedFlag(String forceUsedFlag) {
		this.forceUsedFlag = forceUsedFlag;
	}

	public String getReportTypeFlag() {
		return reportTypeFlag;
	}

	public void setReportTypeFlag(String reportTypeFlag) {
		this.reportTypeFlag = reportTypeFlag;
	}

	/**
	 * Creates new AgencyIncidentParties class Object
	 */
	public AgencyIncidentParties() {
		// AgencyIncidentParties
	}

	/**
	 * @return the affiliation
	 */
	public String getAffiliation() {
		return affiliation;
	}

	/**
	 * @param affiliation
	 *            the affiliation to set
	 */
	public void setAffiliation(final String affiliation) {
		this.affiliation = affiliation;
	}

	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}

	/**
	 * @param roleDescription
	 *            the roleDescription to set
	 */
	public void setRoleDescription(final String roleDescription) {
		this.roleDescription = roleDescription;
	}

	/**
	 * @return the typeDescription
	 */
	public String getTypeDescription() {
		return typeDescription;
	}

	/**
	 * @param typeDescription
	 *            the typeDescription to set
	 */
	public void setTypeDescription(final String typeDescription) {
		this.typeDescription = typeDescription;
	}

	@JsonProperty("typeDescription")
	private String typeDescription;

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
	 * @param partySeq
	 *            partySeq to set
	 */
	public void setPartySeq(final Integer partySeq) {
		this.partySeq = partySeq;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * return thepartySeq
	 */
	public Integer getPartySeq() {
		return this.partySeq;
	}

	/**
	 * @param incidentRole
	 *            incidentRole to set
	 */
	public void setIncidentRole(final String incidentRole) {
		this.incidentRole = incidentRole;
	}

	/**
	 * return theincidentRole
	 */
	public String getIncidentRole() {
		return this.incidentRole;
	}

	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public Integer getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * @param staffId
	 *            staffId to set
	 */
	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * return thestaffId
	 */
	public Integer getStaffId() {
		return this.staffId;
	}

	/**
	 * @param personId
	 *            personId to set
	 */
	public void setPersonId(final Integer personId) {
		this.personId = personId;
	}

	/**
	 * return thepersonId
	 */
	public Integer getPersonId() {
		return this.personId;
	}

	/**
	 * @param dispositionType
	 *            dispositionType to set
	 */
	public void setDispositionType(final String dispositionType) {
		this.dispositionType = dispositionType;
	}

	/**
	 * return thedispositionType
	 */
	public String getDispositionType() {
		return this.dispositionType;
	}

	/**
	 * @param dispositionDate
	 *            dispositionDate to set
	 */
	public void setDispositionDate(final Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	/**
	 * return the dispositionDate
	 */
	public Date getDispositionDate() {
		return this.dispositionDate;
	}

	/**
	 * @param oicIncidentId
	 *            oicIncidentId to set
	 */
	public void setOicIncidentId(final Integer oicIncidentId) {
		this.oicIncidentId = oicIncidentId;
	}

	/**
	 * return theoicIncidentId
	 */
	public Integer getOicIncidentId() {
		return this.oicIncidentId;
	}

	/**
	 * @param commentText
	 *            commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * return thecommentText
	 */
	public String getCommentText() {
		return this.commentText;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * return thecreateDatetime
	 */
	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param actionCode
	 *            actionCode to set
	 */
	public void setActionCode(final String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * return the actionCode
	 */
	public String getActionCode() {
		return this.actionCode;
	}

	/**
	 * @param partyAddedDate
	 *            partyAddedDate to set
	 */
	public void setPartyAddedDate(final Date partyAddedDate) {
		this.partyAddedDate = partyAddedDate;
	}

	/**
	 * @return the staffIdDes
	 */
	public String getStaffIdDes() {
		return staffIdDes;
	}

	/**
	 * @param staffIdDes
	 *            the staffIdDes to set
	 */
	public void setStaffIdDes(final String staffIdDes) {
		this.staffIdDes = staffIdDes;
	}

	/**
	 * return thepartyAddedDate
	 */
	public Date getPartyAddedDate() {
		return this.partyAddedDate;
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
	 * @param repCompletFlag
	 *            repCompletFlag to set
	 */
	public void setRepCompletFlag(final String repCompletFlag) {
		this.repCompletFlag = repCompletFlag;
	}

	/**
	 * return therepCompletFlag
	 */
	public String getRepCompletFlag() {
		return this.repCompletFlag;
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

	// /**
	// * @return the offenderId
	// */
	// public int getOffenderId() {
	// return offenderId;
	// }
	//
	// /**
	// * @param offenderId
	// * the offenderId to set
	// */
	// public void setOffenderId(final int offenderId) {
	// this.offenderId = offenderId;
	// }

	/**
	 * @return the incidentRoleDes
	 */
	public String getIncidentRoleDes() {
		return incidentRoleDes;
	}

	/**
	 * @param incidentRoleDes
	 *            the incidentRoleDes to set
	 */
	public void setIncidentRoleDes(final String incidentRoleDes) {
		this.incidentRoleDes = incidentRoleDes;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(final String lname) {
		this.lname = lname;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(final String fname) {
		this.fname = fname;
	}

	/**
	 * @return the actionCodeDes
	 */
	public String getActionCodeDes() {
		return actionCodeDes;
	}

	/**
	 * @param actionCodeDes
	 *            the actionCodeDes to set
	 */
	public void setActionCodeDes(final String actionCodeDes) {
		this.actionCodeDes = actionCodeDes;
	}

	/**
	 * @return the stgId
	 */
	public BigDecimal getStgId() {
		return stgId;
	}

	/**
	 * @param stgId
	 *            the stgId to set
	 */
	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	public String getStaffReportType() {
		return staffReportType;
	}

	public void setStaffReportType(String staffReportType) {
		this.staffReportType = staffReportType;
	}

	public Date getLockReferenceTime() {
		return lockReferenceTime;
	}

	public void setLockReferenceTime(Date lockReferenceTime) {
		this.lockReferenceTime = lockReferenceTime;
	}

	public Integer getReporterStaffId() {
		return reporterStaffId;
	}

	public void setReporterStaffId(Integer reporterStaffId) {
		this.reporterStaffId = reporterStaffId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	

}