package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * 
 * class OffenderIndSchWaitLists
 *
 */
public class OffenderIndSchWaitLists extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("eventId")
	private int eventId;
	@JsonProperty("requestDate")
	private Date requestDate;
	@JsonProperty("waitListStatus")
	private String waitListStatus;
	@JsonProperty("statusDate")
	private Date statusDate;
	@JsonProperty("transferPriority")
	private String transferPriority;
	@JsonProperty("approvedFlag")
	private String approvedFlag;
	@JsonProperty("approvedStaffId")
	private Integer approvedStaffId;
	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;
	@JsonProperty("commentText1")
	private String commentText1;
	@JsonProperty("commentText2")
	private String commentText2;
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
	@JsonProperty("nbtLastName")
	private String nbtLastName;
	@JsonProperty("nbtFirstName")
    private String nbtFirstName;


	/**
	 * @param eventId
	 *            eventId to set
	 */
	public void setEventId(final int eventId) {
		this.eventId = eventId;
	}

	/**
	 * return theeventId
	 */
	public int getEventId() {
		return this.eventId;
	}

	/**
	 * @param requestDate
	 *            requestDate to set
	 */
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * return therequestDate
	 */
	public Date getRequestDate() {
		return this.requestDate;
	}

	/**
	 * @param waitListStatus
	 *            waitListStatus to set
	 */
	public void setWaitListStatus(final String waitListStatus) {
		this.waitListStatus = waitListStatus;
	}

	/**
	 * return thewaitListStatus
	 */
	public String getWaitListStatus() {
		return this.waitListStatus;
	}

	/**
	 * @param statusDate
	 *            statusDate to set
	 */
	public void setStatusDate(final Date statusDate) {
		this.statusDate = statusDate;
	}

	/**
	 * return thestatusDate
	 */
	public Date getStatusDate() {
		return this.statusDate;
	}

	/**
	 * @param transferPriority
	 *            transferPriority to set
	 */
	public void setTransferPriority(final String transferPriority) {
		this.transferPriority = transferPriority;
	}

	/**
	 * return thetransferPriority
	 */
	public String getTransferPriority() {
		return this.transferPriority;
	}

	/**
	 * @param approvedFlag
	 *            approvedFlag to set
	 */
	public void setApprovedFlag(final String approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	/**
	 * return theapprovedFlag
	 */
	public String getApprovedFlag() {
		return this.approvedFlag;
	}

	/**
	 * @param approvedStaffId
	 *            approvedStaffId to set
	 */
	public void setApprovedStaffId(final Integer approvedStaffId) {
		this.approvedStaffId = approvedStaffId;
	}

	/**
	 * return theapprovedStaffId
	 */
	public Integer getApprovedStaffId() {
		return this.approvedStaffId;
	}

	/**
	 * @param outcomeReasonCode
	 *            outcomeReasonCode to set
	 */
	public void setOutcomeReasonCode(final String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	/**
	 * return theoutcomeReasonCode
	 */
	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	/**
	 * @param commentText1
	 *            commentText1 to set
	 */
	public void setCommentText1(final String commentText1) {
		this.commentText1 = commentText1;
	}

	/**
	 * return thecommentText1
	 */
	public String getCommentText1() {
		return this.commentText1;
	}

	/**
	 * @param commentText2
	 *            commentText2 to set
	 */
	public void setCommentText2(final String commentText2) {
		this.commentText2 = commentText2;
	}

	/**
	 * return thecommentText2
	 */
	public String getCommentText2() {
		return this.commentText2;
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
	 * @param nbtLastName
	 *            nbtLastName to set
	 */
	public String getNbtLastName() {
		return nbtLastName;
	}
	/**
	 * return the nbtLastName
	 */
	public void setNbtLastName(final String nbtLastName) {
		this.nbtLastName = nbtLastName;
	}

	/**
	 * @param nbtFirstName
	 *            nbtFirstName to set
	 */
	public String getNbtFirstName() {
		return nbtFirstName;
	}
	/**
	 * return the nbtFirstName
	 */
	public void setNbtFirstName(final String nbtFirstName) {
		this.nbtFirstName = nbtFirstName;
	}


}
