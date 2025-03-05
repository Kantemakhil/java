package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.OffenderTransactions;


/**
 * Class BankClearReconcilesTmp
 * 
 */
public class BankClearReconcilesTmp extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserIdc")
	private String createUserId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("referenceNo")
	private BigDecimal referenceNo;

	@JsonProperty("referenceNoType")
	private String referenceNoType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnEntryAmount")
	private BigDecimal txnEntryAmount;

	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;

	@JsonProperty("txnPostUsage")
	private String txnPostUsage;
	
	@JsonProperty("txnId")
	private Long txnId;

	@JsonProperty("txnEntrySeq")
	private Long txnEntrySeq;

	@JsonProperty("glEntrySeq")
	private Long glEntrySeq;
	
	@JsonProperty("cgNbtDescription")
	private String cgNbtDescription;
	
	@JsonProperty("nbtBankStatementDate")
	private Date nbtBankStatementDate;
	
	@JsonProperty("accountCode")
	private Integer accountCode;
	
	@JsonProperty("plusTxnEntryAmount")
	private BigDecimal plusTxnEntryAmount;
	
	@JsonProperty("minusTxnEntryAmount")
	private BigDecimal minusTxnEntryAmount;
	
	@JsonProperty("reconciledstmntbalance")
	private BigDecimal reconciledstmntbalance;
	
	@JsonProperty("trustbalance")
	private BigDecimal trustbalance;
	
	@JsonProperty("differenceBal")
	private BigDecimal differenceBal;
	
	@JsonProperty("cgNbtAccountCode")
	private String cgNbtAccountCode;
	
	@JsonProperty("lastReconciledDate")
	private Date lastReconciledDate;
	
	@JsonProperty("cgnbtBankStatementDate")
	private Date cgnbtBankStatementDate;
	
	
	@JsonProperty("checkFlag")
	private String checkFlag;
	
	@JsonProperty("rowId")
	private String rowId;
	private List<OffenderTransactions> offenderTransaction;
	
	
	

	public BankClearReconcilesTmp() {
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
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

	public BigDecimal getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(final BigDecimal referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getReferenceNoType() {
		return this.referenceNoType;
	}

	public void setReferenceNoType(final String referenceNoType) {
		this.referenceNoType = referenceNoType;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getTxnEntryAmount() {
		return this.txnEntryAmount;
	}

	public void setTxnEntryAmount(final BigDecimal txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

	public Date getTxnEntryDate() {
		return this.txnEntryDate;
	}

	public void setTxnEntryDate(final Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	public String getTxnPostUsage() {
		return this.txnPostUsage;
	}

	public void setTxnPostUsage(final String txnPostUsage) {
		this.txnPostUsage = txnPostUsage;
	}
	public Long getTxnId() {
		return this.txnId;
	}
	public void setTxnId(final Long txnId) {
		this.txnId = txnId;
	}
	public Long getTxnEntrySeq() {
		return this.txnEntrySeq;
	}
	public void setTxnEntrySeq(final Long txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}
	public Long getGlEntrySeq() {
		return this.glEntrySeq;
	}
	public void setGlEntrySeq(final Long glEntrySeq) {
		this.glEntrySeq = glEntrySeq;
	}

	/**
	 * @return the cgNbtDescription
	 */
	public String getCgNbtDescription() {
		return cgNbtDescription;
	}

	/**
	 * @param cgNbtDescription the cgNbtDescription to set
	 */
	public void setCgNbtDescription(final String cgNbtDescription) {
		this.cgNbtDescription = cgNbtDescription;
	}

	/**
	 * @return the nbtBankStatementDate
	 */
	public Date getNbtBankStatementDate() {
		return nbtBankStatementDate;
	}

	/**
	 * @param nbtBankStatementDate the nbtBankStatementDate to set
	 */
	public void setNbtBankStatementDate(final Date nbtBankStatementDate) {
		this.nbtBankStatementDate = nbtBankStatementDate;
	}

	/**
	 * @return the accountCode
	 */
	public Integer getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final Integer accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the plusTxnEntryAmount
	 */
	public BigDecimal getPlusTxnEntryAmount() {
		return plusTxnEntryAmount;
	}

	/**
	 * @param plusTxnEntryAmount the plusTxnEntryAmount to set
	 */
	public void setPlusTxnEntryAmount(final BigDecimal plusTxnEntryAmount) {
		this.plusTxnEntryAmount = plusTxnEntryAmount;
	}

	/**
	 * @return the minusTxnEntryAmount
	 */
	public BigDecimal getMinusTxnEntryAmount() {
		return minusTxnEntryAmount;
	}

	/**
	 * @param minusTxnEntryAmount the minusTxnEntryAmount to set
	 */
	public void setMinusTxnEntryAmount(final BigDecimal minusTxnEntryAmount) {
		this.minusTxnEntryAmount = minusTxnEntryAmount;
	}

	/**
	 * @return the reconciledstmntbalance
	 */
	public BigDecimal getReconciledstmntbalance() {
		return reconciledstmntbalance;
	}

	/**
	 * @param reconciledstmntbalance the reconciledstmntbalance to set
	 */
	public void setReconciledstmntbalance(BigDecimal reconciledstmntbalance) {
		this.reconciledstmntbalance = reconciledstmntbalance;
	}

	/**
	 * @return the trustbalance
	 */
	public BigDecimal getTrustbalance() {
		return trustbalance;
	}

	/**
	 * @param trustbalance the trustbalance to set
	 */
	public void setTrustbalance(BigDecimal trustbalance) {
		this.trustbalance = trustbalance;
	}

	/**
	 * @return the differenceBal
	 */
	public BigDecimal getDifferenceBal() {
		return differenceBal;
	}

	/**
	 * @param differenceBal the differenceBal to set
	 */
	public void setDifferenceBal(BigDecimal differenceBal) {
		this.differenceBal = differenceBal;
	}

	/**
	 * @return the cgNbtAccountCode
	 */
	public String getCgNbtAccountCode() {
		return cgNbtAccountCode;
	}

	/**
	 * @param cgNbtAccountCode the cgNbtAccountCode to set
	 */
	public void setCgNbtAccountCode(final String cgNbtAccountCode) {
		this.cgNbtAccountCode = cgNbtAccountCode;
	}

	/**
	 * @return the lastReconciledDate
	 */
	public Date getLastReconciledDate() {
		return lastReconciledDate;
	}

	/**
	 * @param lastReconciledDate the lastReconciledDate to set
	 */
	public void setLastReconciledDate(final Date lastReconciledDate) {
		this.lastReconciledDate = lastReconciledDate;
	}

	/**
	 * @return the cgnbtBankStatementDate
	 */
	public Date getCgnbtBankStatementDate() {
		return cgnbtBankStatementDate;
	}

	/**
	 * @param cgnbtBankStatementDate the cgnbtBankStatementDate to set
	 */
	public void setCgnbtBankStatementDate(final Date cgnbtBankStatementDate) {
		this.cgnbtBankStatementDate = cgnbtBankStatementDate;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

}
