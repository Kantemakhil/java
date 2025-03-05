package net.syscon.s4.common.beans;

import java.math.BigDecimal;
import java.util.Date;

public class CommGlTransactions {


	BigDecimal txnId;
	BigDecimal txnEntrySeq;
	BigDecimal glEntrySeq;
	BigDecimal accountPeriodId;
	Integer accountCode;
	Date txnEntryDate;
	String txnType;
	String txnPostUsage;
	String caseloadId;
	BigDecimal offenderId;
	BigDecimal txnEntryAmount;
	String txnEntryDesc;
	String txnReferenceNumber;
	Date bankStatementDate;
	String 	reconClearFlag;
	String 	txnReversedFlag;
	BigDecimal reversedTxnId;
	BigDecimal reversedTxnEntrySeq;
	BigDecimal reversedGlEntrySeq;
	String payeeNameText;
	BigDecimal payeeCorporateId;
	BigDecimal payeePersonId;
	BigDecimal stockItemId;
	BigDecimal salesOrderId;
	BigDecimal shipmentPaymentId;
	String modifyUserId;
	Date modifyDatetime;
	BigDecimal returnId;
	String txnLocId;
	BigDecimal offTxnId;
	Date createDatetime;
	String createUserId;
	String sealFlag;
	public BigDecimal getTxnId() {
		return txnId;
	}
	public void setTxnId(BigDecimal txnId) {
		this.txnId = txnId;
	}
	public BigDecimal getTxnEntrySeq() {
		return txnEntrySeq;
	}
	public void setTxnEntrySeq(BigDecimal txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}
	public BigDecimal getGlEntrySeq() {
		return glEntrySeq;
	}
	public void setGlEntrySeq(BigDecimal glEntrySeq) {
		this.glEntrySeq = glEntrySeq;
	}
	public BigDecimal getAccountPeriodId() {
		return accountPeriodId;
	}
	public void setAccountPeriodId(BigDecimal accountPeriodId) {
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
	public BigDecimal getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
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
	public BigDecimal getReversedTxnId() {
		return reversedTxnId;
	}
	public void setReversedTxnId(BigDecimal reversedTxnId) {
		this.reversedTxnId = reversedTxnId;
	}
	public BigDecimal getReversedTxnEntrySeq() {
		return reversedTxnEntrySeq;
	}
	public void setReversedTxnEntrySeq(BigDecimal reversedTxnEntrySeq) {
		this.reversedTxnEntrySeq = reversedTxnEntrySeq;
	}
	public BigDecimal getReversedGlEntrySeq() {
		return reversedGlEntrySeq;
	}
	public void setReversedGlEntrySeq(BigDecimal reversedGlEntrySeq) {
		this.reversedGlEntrySeq = reversedGlEntrySeq;
	}
	public String getPayeeNameText() {
		return payeeNameText;
	}
	public void setPayeeNameText(String payeeNameText) {
		this.payeeNameText = payeeNameText;
	}
	public BigDecimal getPayeeCorporateId() {
		return payeeCorporateId;
	}
	public void setPayeeCorporateId(BigDecimal payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}
	public BigDecimal getPayeePersonId() {
		return payeePersonId;
	}
	public void setPayeePersonId(BigDecimal payeePersonId) {
		this.payeePersonId = payeePersonId;
	}
	public BigDecimal getStockItemId() {
		return stockItemId;
	}
	public void setStockItemId(BigDecimal stockItemId) {
		this.stockItemId = stockItemId;
	}
	public BigDecimal getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(BigDecimal salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public BigDecimal getShipmentPaymentId() {
		return shipmentPaymentId;
	}
	public void setShipmentPaymentId(BigDecimal shipmentPaymentId) {
		this.shipmentPaymentId = shipmentPaymentId;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public BigDecimal getReturnId() {
		return returnId;
	}
	public void setReturnId(BigDecimal returnId) {
		this.returnId = returnId;
	}
	public String getTxnLocId() {
		return txnLocId;
	}
	public void setTxnLocId(String txnLocId) {
		this.txnLocId = txnLocId;
	}
	public BigDecimal getOffTxnId() {
		return offTxnId;
	}
	public void setOffTxnId(BigDecimal offTxnId) {
		this.offTxnId = offTxnId;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	
	
}
