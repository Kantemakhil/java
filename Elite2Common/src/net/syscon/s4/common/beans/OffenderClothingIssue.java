package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderClothingIssue extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderClothingIssueId")
	private long offenderClothingIssueId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("issueComment")
	private String issueComment;

	@JsonProperty("issueDate")
	private Date issueDate;

	@JsonProperty("issueReasonCode")
	private String issueReasonCode;

	@JsonProperty("issueTime")
	private Date issueTime;

	@JsonProperty("issueUserId")
	private String issueUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderClothingItems")
	private List<OffenderClothingItem> offenderClothingItems;

	/**
	 *
	 * @return
	 */
	public long getOffenderClothingIssueId() {
		return offenderClothingIssueId;
	}

	/**
	 *
	 * @param offenderClothingIssueId
	 */
	public void setOffenderClothingIssueId(long offenderClothingIssueId) {
		this.offenderClothingIssueId = offenderClothingIssueId;
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
	public String getIssueComment() {
		return issueComment;
	}

	/**
	 *
	 * @param issueComment
	 */
	public void setIssueComment(String issueComment) {
		this.issueComment = issueComment;
	}

	/**
	 *
	 * @return
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 *
	 * @param issueDate
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 *
	 * @return
	 */
	public String getIssueReasonCode() {
		return issueReasonCode;
	}

	/**
	 *
	 * @param issueReasonCode
	 */
	public void setIssueReasonCode(String issueReasonCode) {
		this.issueReasonCode = issueReasonCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getIssueTime() {
		return issueTime;
	}

	/**
	 *
	 * @param issueTime
	 */
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	/**
	 *
	 * @return
	 */
	public String getIssueUserId() {
		return issueUserId;
	}

	/**
	 *
	 * @param issueUserId
	 */
	public void setIssueUserId(String issueUserId) {
		this.issueUserId = issueUserId;
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
	public List<OffenderClothingItem> getOffenderClothingItems() {
		return offenderClothingItems;
	}

	/**
	 *
	 * @param offenderClothingItems
	 */
	public void setOffenderClothingItems(List<OffenderClothingItem> offenderClothingItems) {
		this.offenderClothingItems = offenderClothingItems;
	}

}