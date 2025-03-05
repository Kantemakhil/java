package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_SUB_ACCOUNTS database table.
 * 
 */
public class OffenderSubAccounts extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("balance")
	private Double balance;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("holdBalance")
	private BigDecimal holdBalance;

	@JsonProperty("indDate")
	private Date indDate;

	@JsonProperty("indDays")
	private BigDecimal indDays;

	@JsonProperty("lastTxnId")
	private BigDecimal lastTxnId;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("minimumBalance")
	private BigDecimal minimumBalance;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("trustAccountCode")
	private Long trustAccountCode;

	@JsonProperty("subAccountType")
	private String subAccountType;

	@JsonProperty("description")
	private String description;

	@JsonProperty("daysRemain")
	private Integer daysRemain;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("txnEntryAmount")
	private Double txnEntryAmount;
	
	

	public OffenderSubAccounts() {
		// OffenderSubAccounts
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	public Long getTrustAccountCode() {
		return trustAccountCode;
	}

	public void setTrustAccountCode(final Long trustAccountCode) {
		this.trustAccountCode = trustAccountCode;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(final Double balance) {
		this.balance = balance;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getHoldBalance() {
		return this.holdBalance;
	}

	public void setHoldBalance(final BigDecimal holdBalance) {
		this.holdBalance = holdBalance;
	}

	public Date getIndDate() {
		return this.indDate;
	}

	public void setIndDate(final Date indDate) {
		this.indDate = indDate;
	}

	public BigDecimal getIndDays() {
		return this.indDays;
	}

	public void setIndDays(final BigDecimal indDays) {
		this.indDays = indDays;
	}

	public BigDecimal getLastTxnId() {
		return this.lastTxnId;
	}

	public void setLastTxnId(final BigDecimal lastTxnId) {
		this.lastTxnId = lastTxnId;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public BigDecimal getMinimumBalance() {
		return this.minimumBalance;
	}

	public void setMinimumBalance(final BigDecimal minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the subAccountType
	 */
	public String getSubAccountType() {
		return subAccountType;
	}

	/**
	 * @param subAccountType
	 *            the subAccountType to set
	 */
	public void setSubAccountType(final String subAccountType) {
		this.subAccountType = subAccountType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the daysRemain
	 */
	public Integer getDaysRemain() {
		return daysRemain;
	}

	/**
	 * @param daysRemain
	 *            the daysRemain to set
	 */
	public void setDaysRemain(final Integer daysRemain) {
		this.daysRemain = daysRemain;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the txnEntryAmount
	 */
	public Double getTxnEntryAmount() {
		return txnEntryAmount;
	}

	/**
	 * @param txnEntryAmount the txnEntryAmount to set
	 */
	public void setTxnEntryAmount(Double txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

}
