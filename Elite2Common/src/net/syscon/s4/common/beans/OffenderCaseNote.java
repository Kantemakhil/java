package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCaseNote extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseNoteId")
	private long caseNoteId;

	@JsonProperty("amendmentFlag")
	private String amendmentFlag;

	@JsonProperty("caseNoteSubType")
	private String caseNoteSubType;

	@JsonProperty("caseNoteText")
	private String caseNoteText;

	@JsonProperty("caseNoteType")
	private String caseNoteType;

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

	@JsonProperty("contactDate")
	private Date contactDate;

	@JsonProperty("contactTime")
	private Date contactTime;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("dateCreation")
	private Date dateCreation;

	@JsonProperty("eventId")
	private BigDecimal eventId;

	@JsonProperty("iwpFlag")
	private String iwpFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("noteSourceCode")
	private String noteSourceCode;

	@JsonProperty("objectId")
	private BigDecimal objectId;

	@JsonProperty("objectType")
	private String objectType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("staffId")
	private BigDecimal staffId;

	@JsonProperty("timeCreation")
	private Date timeCreation;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderCaseNoteSents")
	private List<OffenderCaseNoteSent> offenderCaseNoteSents;

	/**
	 *
	 * @return
	 */
	public long getCaseNoteId() {
		return caseNoteId;
	}

	/**
	 *
	 * @param caseNoteId
	 */
	public void setCaseNoteId(long caseNoteId) {
		this.caseNoteId = caseNoteId;
	}

	/**
	 *
	 * @return
	 */
	public String getAmendmentFlag() {
		return amendmentFlag;
	}

	/**
	 *
	 * @param amendmentFlag
	 */
	public void setAmendmentFlag(String amendmentFlag) {
		this.amendmentFlag = amendmentFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseNoteSubType() {
		return caseNoteSubType;
	}

	/**
	 *
	 * @param caseNoteSubType
	 */
	public void setCaseNoteSubType(String caseNoteSubType) {
		this.caseNoteSubType = caseNoteSubType;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseNoteText() {
		return caseNoteText;
	}

	/**
	 *
	 * @param caseNoteText
	 */
	public void setCaseNoteText(String caseNoteText) {
		this.caseNoteText = caseNoteText;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseNoteType() {
		return caseNoteType;
	}

	/**
	 *
	 * @param caseNoteType
	 */
	public void setCaseNoteType(String caseNoteType) {
		this.caseNoteType = caseNoteType;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox1() {
		return checkBox1;
	}

	/**
	 *
	 * @param checkBox1
	 */
	public void setCheckBox1(String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox2() {
		return checkBox2;
	}

	/**
	 *
	 * @param checkBox2
	 */
	public void setCheckBox2(String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox3() {
		return checkBox3;
	}

	/**
	 *
	 * @param checkBox3
	 */
	public void setCheckBox3(String checkBox3) {
		this.checkBox3 = checkBox3;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox4() {
		return checkBox4;
	}

	/**
	 *
	 * @param checkBox4
	 */
	public void setCheckBox4(String checkBox4) {
		this.checkBox4 = checkBox4;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox5() {
		return checkBox5;
	}

	/**
	 *
	 * @param checkBox5
	 */
	public void setCheckBox5(String checkBox5) {
		this.checkBox5 = checkBox5;
	}

	/**
	 *
	 * @return
	 */
	public Date getContactDate() {
		return contactDate;
	}

	/**
	 *
	 * @param contactDate
	 */
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getContactTime() {
		return contactTime;
	}

	/**
	 *
	 * @param contactTime
	 */
	public void setContactTime(Date contactTime) {
		this.contactTime = contactTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 *
	 * @param dateCreation
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getEventId() {
		return eventId;
	}

	/**
	 *
	 * @param eventId
	 */
	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	/**
	 *
	 * @return
	 */
	public String getIwpFlag() {
		return iwpFlag;
	}

	/**
	 *
	 * @param iwpFlag
	 */
	public void setIwpFlag(String iwpFlag) {
		this.iwpFlag = iwpFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getNoteSourceCode() {
		return noteSourceCode;
	}

	/**
	 *
	 * @param noteSourceCode
	 */
	public void setNoteSourceCode(String noteSourceCode) {
		this.noteSourceCode = noteSourceCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getObjectId() {
		return objectId;
	}

	/**
	 *
	 * @param objectId
	 */
	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}

	/**
	 *
	 * @return
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 *
	 * @param objectType
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getStaffId() {
		return staffId;
	}

	/**
	 *
	 * @param staffId
	 */
	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	/**
	 *
	 * @return
	 */
	public Date getTimeCreation() {
		return timeCreation;
	}

	/**
	 *
	 * @param timeCreation
	 */
	public void setTimeCreation(Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCaseNoteSent> getOffenderCaseNoteSents() {
		return offenderCaseNoteSents;
	}

	/**
	 *
	 * @param offenderCaseNoteSents
	 */
	public void setOffenderCaseNoteSents(List<OffenderCaseNoteSent> offenderCaseNoteSents) {
		this.offenderCaseNoteSents = offenderCaseNoteSents;
	}

}