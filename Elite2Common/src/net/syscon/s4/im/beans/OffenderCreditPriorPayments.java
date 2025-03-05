package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderCreditPriorPayments implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("location")
	private String location;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderId")
	private Long offenderId;
	@JsonProperty("paymentAmount")
	private BigDecimal paymentAmount;
	@JsonProperty("paymentDate")
	private Date paymentDate;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("txnId")
	private Long txnId;
	@JsonProperty("txnEntrySeq")
	private Long txnEntrySeq;

	/**
	 * Creates new OffenderCases class Object
	 */
	public OffenderCreditPriorPayments() {
		// OffenderCreditPriorPayments
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the paymentAmount
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount
	 *            the paymentAmount to set
	 */
	public void setPaymentAmount(final BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the txnId
	 */
	public Long getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId
	 *            the txnId to set
	 */
	public void setTxnId(final Long txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the txnEntrySeq
	 */
	public Long getTxnEntrySeq() {
		return txnEntrySeq;
	}

	/**
	 * @param txnEntrySeq
	 *            the txnEntrySeq to set
	 */
	public void setTxnEntrySeq(final Long txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
