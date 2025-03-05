package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 * The persistent class for the OFFENDER_DEDUCTIONS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class DeductionsHistoryBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("fTxnEntry")
	private Date fTxnEntry;
	@JsonProperty("fTxnId")
	private Long fTxnId;
	@JsonProperty("fEntrySeq")
	private Long fEntrySeq;
	@JsonProperty("fTxnEntryDesc")
	private String fTxnEntryDesc;
	@JsonProperty("fFive")
	private BigDecimal fFive;
	@JsonProperty("fSix")
	private BigDecimal fSix;
	@JsonProperty("fSeven")
	private String fSeven;
	@JsonProperty("fBalance")
	private String fBalance;
	@JsonProperty("fPayment")
	private String fPayment;
	@JsonProperty("fModifyUserId")
	private String fModifyUserId;
	@JsonProperty("fCsTotDebit")
	private String fCsTotDebit;
	@JsonProperty("fCsTotPayments")
	private BigDecimal fCsTotPayments;
	@JsonProperty("fCfTotOwing")
	private String fCfTotOwing;
	@JsonProperty("fModify")
	private Date fModify;
	@JsonProperty("fStatus")
	private String fStatus;
	@JsonProperty("fInfoNumber")
	private String fInfoNumber;
	@JsonProperty("fEffectiveDate")
	private Date fEffectiveDate;
	@JsonProperty("maxTotalAmount")
	private BigDecimal maxTotalAmount;
	@JsonProperty("maxMonthlyAmount")
	private BigDecimal maxMonthlyAmount;
	@JsonProperty("fCommentTextOne")
	private String fCommentTextOne;
	@JsonProperty("fModifyUserIdOne")
	private String fModifyUserIdOne;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the fTxnEntry
	 */
	public Date getfTxnEntry() {
		return fTxnEntry;
	}

	/**
	 * @param fTxnEntry
	 *            the fTxnEntry to set
	 */
	public void setfTxnEntry(final Date fTxnEntry) {
		this.fTxnEntry = fTxnEntry;
	}

	/**
	 * @return the fTxnId
	 */
	public Long getfTxnId() {
		return fTxnId;
	}

	/**
	 * @param fTxnId
	 *            the fTxnId to set
	 */
	public void setfTxnId(final Long fTxnId) {
		this.fTxnId = fTxnId;
	}

	/**
	 * @return the fEntrySeq
	 */
	public Long getfEntrySeq() {
		return fEntrySeq;
	}

	/**
	 * @param fEntrySeq
	 *            the fEntrySeq to set
	 */
	public void setfEntrySeq(final Long fEntrySeq) {
		this.fEntrySeq = fEntrySeq;
	}

	/**
	 * @return the fTxnEntryDesc
	 */
	public String getfTxnEntryDesc() {
		return fTxnEntryDesc;
	}

	/**
	 * @param fTxnEntryDesc
	 *            the fTxnEntryDesc to set
	 */
	public void setfTxnEntryDesc(final String fTxnEntryDesc) {
		this.fTxnEntryDesc = fTxnEntryDesc;
	}

	/**
	 * @return the fFive
	 */
	public BigDecimal getfFive() {
		return fFive;
	}

	/**
	 * @param fFive
	 *            the fFive to set
	 */
	public void setfFive(final BigDecimal fFive) {
		this.fFive = fFive;
	}

	/**
	 * @return the fSix
	 */
	public BigDecimal getfSix() {
		return fSix;
	}

	/**
	 * @param fSix
	 *            the fSix to set
	 */
	public void setfSix(final BigDecimal fSix) {
		this.fSix = fSix;
	}

	/**
	 * @return the fSeven
	 */
	public String getfSeven() {
		return fSeven;
	}

	/**
	 * @param fSeven
	 *            the fSeven to set
	 */
	public void setfSeven(final String fSeven) {
		this.fSeven = fSeven;
	}

	/**
	 * @return the fBalance
	 */
	public String getfBalance() {
		return fBalance;
	}

	/**
	 * @param fBalance
	 *            the fBalance to set
	 */
	public void setfBalance(final String fBalance) {
		this.fBalance = fBalance;
	}

	/**
	 * @return the fPayment
	 */
	public String getfPayment() {
		return fPayment;
	}

	/**
	 * @param fPayment
	 *            the fPayment to set
	 */
	public void setfPayment(final String fPayment) {
		this.fPayment = fPayment;
	}

	/**
	 * @return the fModifyUserId
	 */
	public String getfModifyUserId() {
		return fModifyUserId;
	}

	/**
	 * @param fModifyUserId
	 *            the fModifyUserId to set
	 */
	public void setfModifyUserId(final String fModifyUserId) {
		this.fModifyUserId = fModifyUserId;
	}

	/**
	 * @return the fCsTotDebit
	 */
	public String getfCsTotDebit() {
		return fCsTotDebit;
	}

	/**
	 * @param fCsTotDebit
	 *            the fCsTotDebit to set
	 */
	public void setfCsTotDebit(final String fCsTotDebit) {
		this.fCsTotDebit = fCsTotDebit;
	}

	/**
	 * @return the fCsTotPayments
	 */
	public BigDecimal getfCsTotPayments() {
		return fCsTotPayments;
	}

	/**
	 * @param fCsTotPayments
	 *            the fCsTotPayments to set
	 */
	public void setfCsTotPayments(final BigDecimal fCsTotPayments) {
		this.fCsTotPayments = fCsTotPayments;
	}

	/**
	 * @return the fCfTotOwing
	 */
	public String getfCfTotOwing() {
		return fCfTotOwing;
	}

	/**
	 * @param fCfTotOwing
	 *            the fCfTotOwing to set
	 */
	public void setfCfTotOwing(final String fCfTotOwing) {
		this.fCfTotOwing = fCfTotOwing;
	}

	/**
	 * @return the fModify
	 */
	public Date getfModify() {
		return fModify;
	}

	/**
	 * @param fModify
	 *            the fModify to set
	 */
	public void setfModify(final Date fModify) {
		this.fModify = fModify;
	}

	/**
	 * @return the fStatus
	 */
	public String getfStatus() {
		return fStatus;
	}

	/**
	 * @param fStatus
	 *            the fStatus to set
	 */
	public void setfStatus(final String fStatus) {
		this.fStatus = fStatus;
	}

	/**
	 * @return the fInfoNumber
	 */
	public String getfInfoNumber() {
		return fInfoNumber;
	}

	/**
	 * @param fInfoNumber
	 *            the fInfoNumber to set
	 */
	public void setfInfoNumber(final String fInfoNumber) {
		this.fInfoNumber = fInfoNumber;
	}

	/**
	 * @return the fEffectiveDate
	 */
	public Date getfEffectiveDate() {
		return fEffectiveDate;
	}

	/**
	 * @param fEffectiveDate
	 *            the fEffectiveDate to set
	 */
	public void setfEffectiveDate(final Date fEffectiveDate) {
		this.fEffectiveDate = fEffectiveDate;
	}

	/**
	 * @return the maxTotalAmount
	 */
	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	/**
	 * @param maxTotalAmount
	 *            the maxTotalAmount to set
	 */
	public void setMaxTotalAmount(final BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	/**
	 * @return the maxMonthlyAmount
	 */
	public BigDecimal getMaxMonthlyAmount() {
		return maxMonthlyAmount;
	}

	/**
	 * @param maxMonthlyAmount
	 *            the maxMonthlyAmount to set
	 */
	public void setMaxMonthlyAmount(final BigDecimal maxMonthlyAmount) {
		this.maxMonthlyAmount = maxMonthlyAmount;
	}

	/**
	 * @return the fCommentTextOne
	 */
	public String getfCommentTextOne() {
		return fCommentTextOne;
	}

	/**
	 * @param fCommentTextOne
	 *            the fCommentTextOne to set
	 */
	public void setfCommentTextOne(final String fCommentTextOne) {
		this.fCommentTextOne = fCommentTextOne;
	}

	/**
	 * @return the fModifyUserIdOne
	 */
	public String getfModifyUserIdOne() {
		return fModifyUserIdOne;
	}

	/**
	 * @param fModifyUserIdOne
	 *            the fModifyUserIdOne to set
	 */
	public void setfModifyUserIdOne(final String fModifyUserIdOne) {
		this.fModifyUserIdOne = fModifyUserIdOne;
	}

}
