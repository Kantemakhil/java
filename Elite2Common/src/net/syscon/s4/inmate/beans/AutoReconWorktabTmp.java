package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * class AutoReconWorktabTmp
 * 
 */
public class AutoReconWorktabTmp extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;


	@JsonProperty("accountCode")
	private BigDecimal accountCode;

	@JsonProperty("adjustmentAmount")
	private BigDecimal adjustmentAmount;

	@JsonProperty("bankAccountNumber")
	private String bankAccountNumber;

	@JsonProperty("bankChequeNumberFlag")
	private String bankChequeNumberFlag;

	@JsonProperty("bankCustomerRefNum")
	private String bankCustomerRefNum;

	@JsonProperty("bankFileRefId")
	private BigDecimal bankFileRefId;

	@JsonProperty("bankStatementDate")
	private Date bankStatementDate;

	@JsonProperty("bankTxnAmount")
	private BigDecimal bankTxnAmount;

	@JsonProperty("bankTxnDate")
	private Date bankTxnDate;

	@JsonProperty("bankTxnId")
	private BigDecimal bankTxnId;

	@JsonProperty("bankTxnPostUsage")
	private String bankTxnPostUsage;

	@JsonProperty("bankTxnType")
	private String bankTxnType;

	@JsonProperty("bankTxnTypeDesc")
	private String bankTxnTypeDesc;

	@JsonProperty("bankTypeCode")
	private BigDecimal bankTypeCode;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("glEntrySeq")
	private BigDecimal glEntrySeq;

	@JsonProperty("matchedFlag")
	private String matchedFlag;

	@JsonProperty("modifyDatetime")
	private Object modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("recordType")
	private String recordType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnEntryAmount")
	private BigDecimal txnEntryAmount;

	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;

	@JsonProperty("txnEntryDesc")
	private String txnEntryDesc;

	@JsonProperty("txnEntrySeq")
	private BigDecimal txnEntrySeq;

	@JsonProperty("txnId")
	private BigDecimal txnId;

	@JsonProperty("txnPostUsage")
	private String txnPostUsage;

	@JsonProperty("txnType")
	private String txnType;

	public AutoReconWorktabTmp() {
		//AutoReconWorktabTmp
	}

	/**
	 * @return the accountCode
	 */
	public BigDecimal getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the adjustmentAmount
	 */
	public BigDecimal getAdjustmentAmount() {
		return adjustmentAmount;
	}

	/**
	 * @param adjustmentAmount the adjustmentAmount to set
	 */
	public void setAdjustmentAmount(final BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(final String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the bankChequeNumberFlag
	 */
	public String getBankChequeNumberFlag() {
		return bankChequeNumberFlag;
	}

	/**
	 * @param bankChequeNumberFlag the bankChequeNumberFlag to set
	 */
	public void setBankChequeNumberFlag(final String bankChequeNumberFlag) {
		this.bankChequeNumberFlag = bankChequeNumberFlag;
	}

	/**
	 * @return the bankCustomerRefNum
	 */
	public String getBankCustomerRefNum() {
		return bankCustomerRefNum;
	}

	/**
	 * @param bankCustomerRefNum the bankCustomerRefNum to set
	 */
	public void setBankCustomerRefNum(final String bankCustomerRefNum) {
		this.bankCustomerRefNum = bankCustomerRefNum;
	}

	/**
	 * @return the bankFileRefId
	 */
	public BigDecimal getBankFileRefId() {
		return bankFileRefId;
	}

	/**
	 * @param bankFileRefId the bankFileRefId to set
	 */
	public void setBankFileRefId(final BigDecimal bankFileRefId) {
		this.bankFileRefId = bankFileRefId;
	}

	/**
	 * @return the bankStatementDate
	 */
	public Date getBankStatementDate() {
		return bankStatementDate;
	}

	/**
	 * @param bankStatementDate the bankStatementDate to set
	 */
	public void setBankStatementDate(final Date bankStatementDate) {
		this.bankStatementDate = bankStatementDate;
	}

	/**
	 * @return the bankTxnAmount
	 */
	public BigDecimal getBankTxnAmount() {
		return bankTxnAmount;
	}

	/**
	 * @param bankTxnAmount the bankTxnAmount to set
	 */
	public void setBankTxnAmount(final BigDecimal bankTxnAmount) {
		this.bankTxnAmount = bankTxnAmount;
	}

	/**
	 * @return the bankTxnDate
	 */
	public Date getBankTxnDate() {
		return bankTxnDate;
	}

	/**
	 * @param bankTxnDate the bankTxnDate to set
	 */
	public void setBankTxnDate(final Date bankTxnDate) {
		this.bankTxnDate = bankTxnDate;
	}

	/**
	 * @return the bankTxnId
	 */
	public BigDecimal getBankTxnId() {
		return bankTxnId;
	}

	/**
	 * @param bankTxnId the bankTxnId to set
	 */
	public void setBankTxnId(final BigDecimal bankTxnId) {
		this.bankTxnId = bankTxnId;
	}

	/**
	 * @return the bankTxnPostUsage
	 */
	public String getBankTxnPostUsage() {
		return bankTxnPostUsage;
	}

	/**
	 * @param bankTxnPostUsage the bankTxnPostUsage to set
	 */
	public void setBankTxnPostUsage(final String bankTxnPostUsage) {
		this.bankTxnPostUsage = bankTxnPostUsage;
	}

	/**
	 * @return the bankTxnType
	 */
	public String getBankTxnType() {
		return bankTxnType;
	}

	/**
	 * @param bankTxnType the bankTxnType to set
	 */
	public void setBankTxnType(final String bankTxnType) {
		this.bankTxnType = bankTxnType;
	}

	/**
	 * @return the bankTxnTypeDesc
	 */
	public String getBankTxnTypeDesc() {
		return bankTxnTypeDesc;
	}

	/**
	 * @param bankTxnTypeDesc the bankTxnTypeDesc to set
	 */
	public void setBankTxnTypeDesc(final String bankTxnTypeDesc) {
		this.bankTxnTypeDesc = bankTxnTypeDesc;
	}

	/**
	 * @return the bankTypeCode
	 */
	public BigDecimal getBankTypeCode() {
		return bankTypeCode;
	}

	/**
	 * @param bankTypeCode the bankTypeCode to set
	 */
	public void setBankTypeCode(final BigDecimal bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the glEntrySeq
	 */
	public BigDecimal getGlEntrySeq() {
		return glEntrySeq;
	}

	/**
	 * @param glEntrySeq the glEntrySeq to set
	 */
	public void setGlEntrySeq(final BigDecimal glEntrySeq) {
		this.glEntrySeq = glEntrySeq;
	}

	/**
	 * @return the matchedFlag
	 */
	public String getMatchedFlag() {
		return matchedFlag;
	}

	/**
	 * @param matchedFlag the matchedFlag to set
	 */
	public void setMatchedFlag(final String matchedFlag) {
		this.matchedFlag = matchedFlag;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Object getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(final String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the txnEntryAmount
	 */
	public BigDecimal getTxnEntryAmount() {
		return txnEntryAmount;
	}

	/**
	 * @param txnEntryAmount the txnEntryAmount to set
	 */
	public void setTxnEntryAmount(final BigDecimal txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

	/**
	 * @return the txnEntryDate
	 */
	public Date getTxnEntryDate() {
		return txnEntryDate;
	}

	/**
	 * @param txnEntryDate the txnEntryDate to set
	 */
	public void setTxnEntryDate(final Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	/**
	 * @return the txnEntryDesc
	 */
	public String getTxnEntryDesc() {
		return txnEntryDesc;
	}

	/**
	 * @param txnEntryDesc the txnEntryDesc to set
	 */
	public void setTxnEntryDesc(final String txnEntryDesc) {
		this.txnEntryDesc = txnEntryDesc;
	}

	/**
	 * @return the txnEntrySeq
	 */
	public BigDecimal getTxnEntrySeq() {
		return txnEntrySeq;
	}

	/**
	 * @param txnEntrySeq the txnEntrySeq to set
	 */
	public void setTxnEntrySeq(final BigDecimal txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	/**
	 * @return the txnId
	 */
	public BigDecimal getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(final BigDecimal txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the txnPostUsage
	 */
	public String getTxnPostUsage() {
		return txnPostUsage;
	}

	/**
	 * @param txnPostUsage the txnPostUsage to set
	 */
	public void setTxnPostUsage(final String txnPostUsage) {
		this.txnPostUsage = txnPostUsage;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	
}