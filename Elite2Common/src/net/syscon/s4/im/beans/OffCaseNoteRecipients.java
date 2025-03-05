package net.syscon.s4.im.beans;



import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class OffCaseNoteRecipients
 * 
 */
public class OffCaseNoteRecipients {

	@JsonProperty("caseNoteId")
	private Integer caseNoteId;
	@JsonProperty("teamId")
	private Integer teamId;
	@JsonProperty("staffId")
	private Integer staffId;
	@JsonProperty("offCaseNoteRecipientId")
	private Integer offCaseNoteRecipientId;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("teamIdDesc")
	private String teamIdDesc ;
	@JsonProperty("staffIdDesc")
	private String staffIdDesc;
	

	public OffCaseNoteRecipients() {
		// OffCaseNoteRecipients
	}

	/**
	 * @param caseNoteId
	 *            caseNoteId to set
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
	 * @param teamId
	 *            teamId to set
	 */
	public void setTeamId(final Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * return theteamId
	 */
	public Integer getTeamId() {
		return this.teamId;
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
	 * @param offCaseNoteRecipientId
	 *            offCaseNoteRecipientId to set
	 */
	public void setOffCaseNoteRecipientId(final Integer offCaseNoteRecipientId) {
		this.offCaseNoteRecipientId = offCaseNoteRecipientId;
	}

	/**
	 * return theoffCaseNoteRecipientId
	 */
	public Integer getOffCaseNoteRecipientId() {
		return this.offCaseNoteRecipientId;
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
	 * @param modifyDatetime
	 *            modifyDatetime to set
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
	 * @param modifyUserId
	 *            modifyUserId to set
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
	 * @param sealFlag
	 *            sealFlag to set
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
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the teamIdDesc
	 */
	public String getTeamIdDesc() {
		return teamIdDesc;
	}

	/**
	 * @param teamIdDesc the teamIdDesc to set
	 */
	public void setTeamIdDesc(String teamIdDesc) {
		this.teamIdDesc = teamIdDesc;
	}

	/**
	 * @return the staffIdDesc
	 */
	public String getStaffIdDesc() {
		return staffIdDesc;
	}

	/**
	 * @param staffIdDesc the staffIdDesc to set
	 */
	public void setStaffIdDesc(String staffIdDesc) {
		this.staffIdDesc = staffIdDesc;
	}

}