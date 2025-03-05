package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * class BankTransactionCode
 * 
 */
public class BankTransactionCodes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private BigDecimal accountCode;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("bankTransactionCode")
	private BigDecimal bankTransactionCode;

	@JsonProperty("bankTxnDescription")
	private String bankTxnDescription;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("chequeNumberFlag")
	private String chequeNumberFlag;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("postUsage")
	private String postUsage;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnMappedFlag")
	private String txnMappedFlag;

	public BankTransactionCodes() {
		// BankTransactionCode
	}

	public BigDecimal getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public BigDecimal getBankTransactionCode() {
		return this.bankTransactionCode;
	}

	public void setBankTransactionCode(final BigDecimal bankTransactionCode) {
		this.bankTransactionCode = bankTransactionCode;
	}

	public String getBankTxnDescription() {
		return this.bankTxnDescription;
	}

	public void setBankTxnDescription(final String bankTxnDescription) {
		this.bankTxnDescription = bankTxnDescription;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getChequeNumberFlag() {
		return this.chequeNumberFlag;
	}

	public void setChequeNumberFlag(final String chequeNumberFlag) {
		this.chequeNumberFlag = chequeNumberFlag;
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

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getPostUsage() {
		return this.postUsage;
	}

	public void setPostUsage(final String postUsage) {
		this.postUsage = postUsage;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getTxnMappedFlag() {
		return this.txnMappedFlag;
	}

	public void setTxnMappedFlag(final String txnMappedFlag) {
		this.txnMappedFlag = txnMappedFlag;
	}

}