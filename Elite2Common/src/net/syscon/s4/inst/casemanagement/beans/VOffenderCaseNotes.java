package net.syscon.s4.inst.casemanagement.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class VOffenderCaseNotes
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
public class VOffenderCaseNotes {

	@JsonProperty("offenderBookId")
	private int offenderBookId;
	@JsonProperty("caseNoteId")
	private int caseNoteId;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("contactDate")
	private Date contactDate;
	@JsonProperty("contactTime")
	private Date contactTime;
	@JsonProperty("caseNoteType")
	private String caseNoteType;
	@JsonProperty("contactType")
	private String contactType;
	@JsonProperty("caseNoteSubType")
	private String caseNoteSubType;
	@JsonProperty("contactSubType")
	private String contactSubType;
	@JsonProperty("staffId")
	private int staffId;
	@JsonProperty("staffName")
	private String staffName;
	@JsonProperty("amendmentFlag")
	private String amendmentFlag;
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
	@JsonProperty("caseNoteText")
	private String caseNoteText;
	@JsonProperty("eventId")
	private int eventId;
	@JsonProperty("noteSourceCode")
	private String noteSourceCode;
	@JsonProperty("noteSource")
	private String noteSource;
	@JsonProperty("iwpFlag")
	private String iwpFlag;
	@JsonProperty("dateCreation")
	private Date dateCreation;
	@JsonProperty("timeCreation")
	private Date timeCreation;
	private boolean inserted;

	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(int offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public int getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * @param caseNoteId
	 *            caseNoteId to set
	 */
	public void setCaseNoteId(int caseNoteId) {
		this.caseNoteId = caseNoteId;
	}

	/**
	 * return thecaseNoteId
	 */
	public int getCaseNoteId() {
		return this.caseNoteId;
	}

	/**
	 * @param offenderIdDisplay
	 *            offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * return theoffenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	/**
	 * @param lastName
	 *            lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * return thelastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param firstName
	 *            firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * return thefirstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param contactDate
	 *            contactDate to set
	 */
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	/**
	 * return thecontactDate
	 */
	public Date getContactDate() {
		return this.contactDate;
	}

	/**
	 * @param contactTime
	 *            contactTime to set
	 */
	public void setContactTime(Date contactTime) {
		this.contactTime = contactTime;
	}

	/**
	 * return thecontactTime
	 */
	public Date getContactTime() {
		return this.contactTime;
	}

	/**
	 * @param caseNoteType
	 *            caseNoteType to set
	 */
	public void setCaseNoteType(String caseNoteType) {
		this.caseNoteType = caseNoteType;
	}

	/**
	 * return thecaseNoteType
	 */
	public String getCaseNoteType() {
		return this.caseNoteType;
	}

	/**
	 * @param contactType
	 *            contactType to set
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * return thecontactType
	 */
	public String getContactType() {
		return this.contactType;
	}

	/**
	 * @param caseNoteSubType
	 *            caseNoteSubType to set
	 */
	public void setCaseNoteSubType(String caseNoteSubType) {
		this.caseNoteSubType = caseNoteSubType;
	}

	/**
	 * return thecaseNoteSubType
	 */
	public String getCaseNoteSubType() {
		return this.caseNoteSubType;
	}

	/**
	 * @param contactSubType
	 *            contactSubType to set
	 */
	public void setContactSubType(String contactSubType) {
		this.contactSubType = contactSubType;
	}

	/**
	 * return thecontactSubType
	 */
	public String getContactSubType() {
		return this.contactSubType;
	}

	/**
	 * @param staffId
	 *            staffId to set
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	/**
	 * return thestaffId
	 */
	public int getStaffId() {
		return this.staffId;
	}

	/**
	 * @param staffName
	 *            staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * return thestaffName
	 */
	public String getStaffName() {
		return this.staffName;
	}

	/**
	 * @param amendmentFlag
	 *            amendmentFlag to set
	 */
	public void setAmendmentFlag(String amendmentFlag) {
		this.amendmentFlag = amendmentFlag;
	}

	/**
	 * return theamendmentFlag
	 */
	public String getAmendmentFlag() {
		return this.amendmentFlag;
	}

	/**
	 * @param checkBox1
	 *            checkBox1 to set
	 */
	public void setCheckBox1(String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 * return thecheckBox1
	 */
	public String getCheckBox1() {
		return this.checkBox1;
	}

	/**
	 * @param checkBox2
	 *            checkBox2 to set
	 */
	public void setCheckBox2(String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	/**
	 * return thecheckBox2
	 */
	public String getCheckBox2() {
		return this.checkBox2;
	}

	/**
	 * @param checkBox3
	 *            checkBox3 to set
	 */
	public void setCheckBox3(String checkBox3) {
		this.checkBox3 = checkBox3;
	}

	/**
	 * return thecheckBox3
	 */
	public String getCheckBox3() {
		return this.checkBox3;
	}

	/**
	 * @param checkBox4
	 *            checkBox4 to set
	 */
	public void setCheckBox4(String checkBox4) {
		this.checkBox4 = checkBox4;
	}

	/**
	 * return thecheckBox4
	 */
	public String getCheckBox4() {
		return this.checkBox4;
	}

	/**
	 * @param checkBox5
	 *            checkBox5 to set
	 */
	public void setCheckBox5(String checkBox5) {
		this.checkBox5 = checkBox5;
	}

	/**
	 * return thecheckBox5
	 */
	public String getCheckBox5() {
		return this.checkBox5;
	}

	/**
	 * @param caseNoteText
	 *            caseNoteText to set
	 */
	public void setCaseNoteText(String caseNoteText) {
		this.caseNoteText = caseNoteText;
	}

	/**
	 * return thecaseNoteText
	 */
	public String getCaseNoteText() {
		return this.caseNoteText;
	}

	/**
	 * @param eventId
	 *            eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * return theeventId
	 */
	public int getEventId() {
		return this.eventId;
	}

	/**
	 * @param noteSourceCode
	 *            noteSourceCode to set
	 */
	public void setNoteSourceCode(String noteSourceCode) {
		this.noteSourceCode = noteSourceCode;
	}

	/**
	 * return thenoteSourceCode
	 */
	public String getNoteSourceCode() {
		return this.noteSourceCode;
	}

	/**
	 * @param noteSource
	 *            noteSource to set
	 */
	public void setNoteSource(String noteSource) {
		this.noteSource = noteSource;
	}

	/**
	 * return thenoteSource
	 */
	public String getNoteSource() {
		return this.noteSource;
	}

	/**
	 * @param iwpFlag
	 *            iwpFlag to set
	 */
	public void setIwpFlag(String iwpFlag) {
		this.iwpFlag = iwpFlag;
	}

	/**
	 * return theiwpFlag
	 */
	public String getIwpFlag() {
		return this.iwpFlag;
	}

	/**
	 * @param dateCreation
	 *            dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * return thedateCreation
	 */
	public Date getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * @param timeCreation
	 *            timeCreation to set
	 */
	public void setTimeCreation(Date timeCreation) {
		this.timeCreation = timeCreation;
	}

	/**
	 * return thetimeCreation
	 */
	public Date getTimeCreation() {
		return this.timeCreation;
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
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

}