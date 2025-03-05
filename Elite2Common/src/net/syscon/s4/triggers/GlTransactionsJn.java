package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class GlTransactionsJn extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jnOperation;
	private String jnOracleUser;
	private Date jnDatetime;
	private String jnNotes;
	private String jnAppln;
	private BigDecimal jnSession;
	private Long txnId;
	private Integer txnEntrySeq;
	private Integer glEntrySeq;
	private Integer accountPeriodId;
	private Integer accountCode;
	private Date txnEntryDate;
	private String txnType;
	private String txnPostUsage;
	private String caseloadId;
	private Long offenderId;
	private Long offenderBookId;
	private BigDecimal txnEntryAmount;
	private String txnEntryDesc;
	private String txnReferenceNumber;
	private Date bankStatementDate;
	private String reconClearFlag;
	private String txnReversedFlag;
	private Long reversedTxnId;
	private Long payeePersonId;
	private Integer reversedTxnEntrySeq;
	private Integer reversedGlEntrySeq;
	private Long payeeCorporateId;
	private String payeeNameText;
	private String txnObjectCode;
	private Integer listSeq;
	private Long txnObjectId;
	private String createUserId;
	private Date createDate;
	private String infoNumber;
	private Long deductionId;
	private Date txnEntryTime;
	private String receiptNumber;
	private String reversalReasonCode;
	private String txnLocId;
	private String payeeClearFlag;
	private Date createDatetime;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;

	public String getJnOperation() {
		return jnOperation;
	}

	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}

	public String getJnOracleUser() {
		return jnOracleUser;
	}

	public void setJnOracleUser(String jnOracleUser) {
		this.jnOracleUser = jnOracleUser;
	}

	public Date getJnDatetime() {
		return jnDatetime;
	}

	public void setJnDatetime(Date jnDatetime) {
		this.jnDatetime = jnDatetime;
	}

	public String getJnNotes() {
		return jnNotes;
	}

	public void setJnNotes(String jnNotes) {
		this.jnNotes = jnNotes;
	}

	public String getJnAppln() {
		return jnAppln;
	}

	public void setJnAppln(String jnAppln) {
		this.jnAppln = jnAppln;
	}

	public BigDecimal getJnSession() {
		return jnSession;
	}

	public void setJnSession(BigDecimal jnSession) {
		this.jnSession = jnSession;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public Integer getTxnEntrySeq() {
		return txnEntrySeq;
	}

	public void setTxnEntrySeq(Integer txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	public Integer getGlEntrySeq() {
		return glEntrySeq;
	}

	public void setGlEntrySeq(Integer glEntrySeq) {
		this.glEntrySeq = glEntrySeq;
	}

	public Integer getAccountPeriodId() {
		return accountPeriodId;
	}

	public void setAccountPeriodId(Integer accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	public Integer getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(Integer accountCode) {
		this.accountCode = accountCode;
	}

	public Date getTxnEntryDate() {
		return txnEntryDate;
	}

	public void setTxnEntryDate(Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTxnPostUsage() {
		return txnPostUsage;
	}

	public void setTxnPostUsage(String txnPostUsage) {
		this.txnPostUsage = txnPostUsage;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getTxnEntryAmount() {
		return txnEntryAmount;
	}

	public void setTxnEntryAmount(BigDecimal txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

	public String getTxnEntryDesc() {
		return txnEntryDesc;
	}

	public void setTxnEntryDesc(String txnEntryDesc) {
		this.txnEntryDesc = txnEntryDesc;
	}

	public String getTxnReferenceNumber() {
		return txnReferenceNumber;
	}

	public void setTxnReferenceNumber(String txnReferenceNumber) {
		this.txnReferenceNumber = txnReferenceNumber;
	}

	public Date getBankStatementDate() {
		return bankStatementDate;
	}

	public void setBankStatementDate(Date bankStatementDate) {
		this.bankStatementDate = bankStatementDate;
	}

	public String getReconClearFlag() {
		return reconClearFlag;
	}

	public void setReconClearFlag(String reconClearFlag) {
		this.reconClearFlag = reconClearFlag;
	}

	public String getTxnReversedFlag() {
		return txnReversedFlag;
	}

	public void setTxnReversedFlag(String txnReversedFlag) {
		this.txnReversedFlag = txnReversedFlag;
	}

	public Long getReversedTxnId() {
		return reversedTxnId;
	}

	public void setReversedTxnId(Long reversedTxnId) {
		this.reversedTxnId = reversedTxnId;
	}

	public Long getPayeePersonId() {
		return payeePersonId;
	}

	public void setPayeePersonId(Long payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	public Integer getReversedTxnEntrySeq() {
		return reversedTxnEntrySeq;
	}

	public void setReversedTxnEntrySeq(Integer reversedTxnEntrySeq) {
		this.reversedTxnEntrySeq = reversedTxnEntrySeq;
	}

	public Integer getReversedGlEntrySeq() {
		return reversedGlEntrySeq;
	}

	public void setReversedGlEntrySeq(Integer reversedGlEntrySeq) {
		this.reversedGlEntrySeq = reversedGlEntrySeq;
	}

	public Long getPayeeCorporateId() {
		return payeeCorporateId;
	}

	public void setPayeeCorporateId(Long payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	public String getPayeeNameText() {
		return payeeNameText;
	}

	public void setPayeeNameText(String payeeNameText) {
		this.payeeNameText = payeeNameText;
	}

	public String getTxnObjectCode() {
		return txnObjectCode;
	}

	public void setTxnObjectCode(String txnObjectCode) {
		this.txnObjectCode = txnObjectCode;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public Long getTxnObjectId() {
		return txnObjectId;
	}

	public void setTxnObjectId(Long txnObjectId) {
		this.txnObjectId = txnObjectId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getInfoNumber() {
		return infoNumber;
	}

	public void setInfoNumber(String infoNumber) {
		this.infoNumber = infoNumber;
	}

	public Long getDeductionId() {
		return deductionId;
	}

	public void setDeductionId(Long deductionId) {
		this.deductionId = deductionId;
	}

	public Date getTxnEntryTime() {
		return txnEntryTime;
	}

	public void setTxnEntryTime(Date txnEntryTime) {
		this.txnEntryTime = txnEntryTime;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getReversalReasonCode() {
		return reversalReasonCode;
	}

	public void setReversalReasonCode(String reversalReasonCode) {
		this.reversalReasonCode = reversalReasonCode;
	}

	public String getTxnLocId() {
		return txnLocId;
	}

	public void setTxnLocId(String txnLocId) {
		this.txnLocId = txnLocId;
	}

	public String getPayeeClearFlag() {
		return payeeClearFlag;
	}

	public void setPayeeClearFlag(String payeeClearFlag) {
		this.payeeClearFlag = payeeClearFlag;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
