package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAsset extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("assestSeq")
	private long assestSeq;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("assestType")
	private String assestType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("creationUser")
	private String creationUser;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("partialToDateFlag")
	private String partialToDateFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("toDate")
	private Date toDate;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	/**
	 *
	 * @return
	 */
	public long getAssestSeq() {
		return assestSeq;
	}

	/**
	 *
	 * @param assestSeq
	 */
	public void setAssestSeq(long assestSeq) {
		this.assestSeq = assestSeq;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 *
	 * @param amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 *
	 * @return
	 */
	public String getAssestType() {
		return assestType;
	}

	/**
	 *
	 * @param assestType
	 */
	public void setAssestType(String assestType) {
		this.assestType = assestType;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 *
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 *
	 * @return
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 *
	 * @param creationUser
	 */
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
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
	public String getPartialToDateFlag() {
		return partialToDateFlag;
	}

	/**
	 *
	 * @param partialToDateFlag
	 */
	public void setPartialToDateFlag(String partialToDateFlag) {
		this.partialToDateFlag = partialToDateFlag;
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
	public Date getToDate() {
		return toDate;
	}

	/**
	 *
	 * @param toDate
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 *
	 * @param offenderBooking
	 */
	public void setOffenderBooking(OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
	}

}