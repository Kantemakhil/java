package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderCaseNotes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCaseNotes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("contactDate")
	private Date contactDate;
	@JsonProperty("contactTime")
	private Date contactTime;
	@JsonProperty("caseNoteType")
	private String caseNoteType;
	@JsonProperty("caseNoteSubType")
	private String caseNoteSubType;
	@JsonProperty("staffId")
	private Integer staffId;
	@JsonProperty("caseNoteText")
	private String caseNoteText;
	@JsonProperty("amendmentFlag")
	private String amendmentFlag;
	@JsonProperty("iwpFlag")
	private String iwpFlag;
	@JsonProperty("checkBox1")
	private String checkBox1;
	@JsonProperty("checkBox2")
	private String checkBox2;
	@JsonProperty("checkBox3")
	private String checkBox3;
	@JsonProperty("checkBox4")
	private String checkBox4;
	@JsonProperty("checkBox5")
	private String checkBox5;
	@JsonProperty("eventId")
	private Integer eventId;
	@JsonProperty("caseNoteId")
	private Integer caseNoteId;
	@JsonProperty("noteSourceCode")
	private String noteSourceCode;
	@JsonProperty("dateCreation")
	private Date dateCreation;
	@JsonProperty("timeCreation")
	private Date timeCreation;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("objectType")
	private String objectType;
	@JsonProperty("objectId")
	private Integer objectId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("staffName")
	private String staffName;
	@JsonProperty("staffIdTemp")
	private String staffIdTemp;
	@JsonProperty("fromDate")
	private Date fromDate;
	@JsonProperty("toDate")
	private Date toDate;
	@JsonProperty("moduleName")
	private String moduleName;
	@JsonProperty("pNbtNoteSourceCodeDesc")
	private String pNbtNoteSourceCodeDesc;
	@JsonProperty("pNbtCaseNoteSubTypeDesc")
	private String pNbtCaseNoteSubTypeDesc;
	@JsonProperty("pNbtStaffNameDesc")
	private String pNbtStaffNameDesc;
	@JsonProperty("createFlag")
	private String createFlag;

	@JsonProperty("lvRoleCode")
	private String lvRoleCode;

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("caseLoadType")
	private String caseLoadType;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("facility")
	private String facility;
	
	@JsonProperty("viewFlag")
	private String viewFlag;
	
	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLvRoleCode() {
		return lvRoleCode;
	}

	public void setLvRoleCode(String lvRoleCode) {
		this.lvRoleCode = lvRoleCode;
	}

	private boolean inserted;

	public OffenderCaseNotes() {
		// OffenderCaseNotes
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the staffIdTemp
	 */
	public String getStaffIdTemp() {
		return staffIdTemp;
	}

	/**
	 * @param staffIdTemp the staffIdTemp to set
	 */
	public void setStaffIdTemp(final String staffIdTemp) {
		this.staffIdTemp = staffIdTemp;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(final String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @param offenderBookId offenderBookId to set
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
	 * @param contactDate contactDate to set
	 */
	public void setContactDate(final Date contactDate) {
		this.contactDate = contactDate;
	}

	/**
	 * return thecontactDate
	 */
	public Date getContactDate() {
		return this.contactDate;
	}

	/**
	 * @param contactTime contactTime to set
	 */
	public void setContactTime(final Date contactTime) {
		this.contactTime = contactTime;
	}

	/**
	 * return thecontactTime
	 */
	public Date getContactTime() {
		return this.contactTime;
	}

	/**
	 * @param caseNoteType caseNoteType to set
	 */
	public void setCaseNoteType(final String caseNoteType) {
		this.caseNoteType = caseNoteType;
	}

	/**
	 * return thecaseNoteType
	 */
	public String getCaseNoteType() {
		return this.caseNoteType;
	}

	/**
	 * @param caseNoteSubType caseNoteSubType to set
	 */
	public void setCaseNoteSubType(final String caseNoteSubType) {
		this.caseNoteSubType = caseNoteSubType;
	}

	/**
	 * return thecaseNoteSubType
	 */
	public String getCaseNoteSubType() {
		return this.caseNoteSubType;
	}

	/**
	 * @param staffId staffId to set
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
	 * @param caseNoteText caseNoteText to set
	 */
	public void setCaseNoteText(final String caseNoteText) {
		this.caseNoteText = caseNoteText;
	}

	/**
	 * return thecaseNoteText
	 */
	public String getCaseNoteText() {
		return this.caseNoteText;
	}

	/**
	 * @param amendmentFlag amendmentFlag to set
	 */
	public void setAmendmentFlag(final String amendmentFlag) {
		this.amendmentFlag = amendmentFlag;
	}

	/**
	 * return theamendmentFlag
	 */
	public String getAmendmentFlag() {
		return this.amendmentFlag;
	}

	/**
	 * @param iwpFlag iwpFlag to set
	 */
	public void setIwpFlag(final String iwpFlag) {
		this.iwpFlag = iwpFlag;
	}

	/**
	 * return theiwpFlag
	 */
	public String getIwpFlag() {
		return this.iwpFlag;
	}

	/**
	 * @param checkBox1 checkBox1 to set
	 */
	public void setCheckBox1(final String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 * return thecheckBox1
	 */
	public String getCheckBox1() {
		return this.checkBox1;
	}

	/**
	 * @param checkBox2 checkBox2 to set
	 */
	public void setCheckBox2(final String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	/**
	 * return thecheckBox2
	 */
	public String getCheckBox2() {
		return this.checkBox2;
	}

	/**
	 * @param checkBox3 checkBox3 to set
	 */
	public void setCheckBox3(final String checkBox3) {
		this.checkBox3 = checkBox3;
	}

	/**
	 * return thecheckBox3
	 */
	public String getCheckBox3() {
		return this.checkBox3;
	}

	/**
	 * @param checkBox4 checkBox4 to set
	 */
	public void setCheckBox4(final String checkBox4) {
		this.checkBox4 = checkBox4;
	}

	/**
	 * return thecheckBox4
	 */
	public String getCheckBox4() {
		return this.checkBox4;
	}

	/**
	 * @param checkBox5 checkBox5 to set
	 */
	public void setCheckBox5(final String checkBox5) {
		this.checkBox5 = checkBox5;
	}

	/**
	 * return thecheckBox5
	 */
	public String getCheckBox5() {
		return this.checkBox5;
	}

	/**
	 * @param eventId eventId to set
	 */
	public void setEventId(final Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * return theeventId
	 */
	public Integer getEventId() {
		return this.eventId;
	}

	/**
	 * @param caseNoteId caseNoteId to set
	 */
	public void setCaseNoteId(final Integer caseNoteId) {
		this.caseNoteId = caseNoteId;
	}

	/**
	 * return thecaseNoteId
	 */
	public Integer getCaseNoteId() {
		return this.caseNoteId;
	}

	/**
	 * @param noteSourceCode noteSourceCode to set
	 */
	public void setNoteSourceCode(final String noteSourceCode) {
		this.noteSourceCode = noteSourceCode;
	}

	/**
	 * return thenoteSourceCode
	 */
	public String getNoteSourceCode() {
		return this.noteSourceCode;
	}

	/**
	 * @param dateCreation dateCreation to set
	 */
	public void setDateCreation(final Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * return thedateCreation
	 */
	public Date getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * @param timeCreation timeCreation to set
	 */
	public void setTimeCreation(final Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	/**
	 * return thetimeCreation
	 */
	public Date getTimeCreation() {
		return this.timeCreation;
	}

	/**
	 * @param sealFlag sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param objectType objectType to set
	 */
	public void setObjectType(final String objectType) {
		this.objectType = objectType;
	}

	/**
	 * return theobjectType
	 */
	public String getObjectType() {
		return this.objectType;
	}

	/**
	 * @param objectId objectId to set
	 */
	public void setObjectId(final Integer objectId) {
		this.objectId = objectId;
	}

	/**
	 * return theobjectId
	 */
	public Integer getObjectId() {
		return this.objectId;
	}

	/**
	 * @param createDatetime createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId createUserId to set
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
	 * @param modifyDatetime modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	public String getpNbtNoteSourceCodeDesc() {
		return pNbtNoteSourceCodeDesc;
	}

	public void setpNbtNoteSourceCodeDesc(final String pNbtNoteSourceCodeDesc) {
		this.pNbtNoteSourceCodeDesc = pNbtNoteSourceCodeDesc;
	}

	public String getpNbtCaseNoteSubTypeDesc() {
		return pNbtCaseNoteSubTypeDesc;
	}

	public void setpNbtCaseNoteSubTypeDesc(final String pNbtCaseNoteSubTypeDesc) {
		this.pNbtCaseNoteSubTypeDesc = pNbtCaseNoteSubTypeDesc;
	}

	public String getpNbtStaffNameDesc() {
		return pNbtStaffNameDesc;
	}

	public void setpNbtStaffNameDesc(final String pNbtStaffNameDesc) {
		this.pNbtStaffNameDesc = pNbtStaffNameDesc;
	}

	public String getCaseLoadType() {
		return caseLoadType;
	}

	public void setCaseLoadType(String caseLoadType) {
		this.caseLoadType = caseLoadType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	
}