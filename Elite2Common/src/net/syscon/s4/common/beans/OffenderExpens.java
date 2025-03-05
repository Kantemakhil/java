package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderExpens extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("expenseSeq")
	private long expenseSeq;

	@JsonProperty("amount")
	private BigDecimal amount;

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

	@JsonProperty("fromDate")
	private Date fromDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("partialFromDateFlag")
	private String partialFromDateFlag;

	@JsonProperty("partialToDateFlag")
	private String partialToDateFlag;

	@JsonProperty("paymentFor")
	private String paymentFor;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("toDate")
	private Date toDate;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	/**
	 *
	 * @return
	 */
	public long getExpenseSeq() {
		return expenseSeq;
	}

	/**
	 *
	 * @param expenseSeq
	 */
	public void setExpenseSeq(long expenseSeq) {
		this.expenseSeq = expenseSeq;
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
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 *
	 * @param fromDate
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
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
	public String getPartialFromDateFlag() {
		return partialFromDateFlag;
	}

	/**
	 *
	 * @param partialFromDateFlag
	 */
	public void setPartialFromDateFlag(String partialFromDateFlag) {
		this.partialFromDateFlag = partialFromDateFlag;
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
	public String getPaymentFor() {
		return paymentFor;
	}

	/**
	 *
	 * @param paymentFor
	 */
	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
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

}