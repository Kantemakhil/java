package net.syscon.s4.cf.deductions.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffFeeBillTransactions extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("billId")
	private String billId;

	@JsonProperty("billTxnNo")
	private Integer billTxnNo;

	@JsonProperty("billTxnDatetime")
	private Date billTxnDatetime;

	@JsonProperty("billTxnStaffId")
	private Integer billTxnStaffId;

	@JsonProperty("billTxnUser") 
	private String billTxnUser;

	@JsonProperty("billTxnCategory")
	private String billTxnCategory;

	@JsonProperty("billTxnType")
	private String billTxnType;

	@JsonProperty("billTxnAmount")
	private BigDecimal billTxnAmount;

	@JsonProperty("trustTxnId")
	private Integer trustTxnId;

	@JsonProperty("trustTxnEntrySeq")
	private Integer trustTxnEntrySeq;

	@JsonProperty("offAdjCancRsn")
	private String offAdjCancRsn;

	@JsonProperty("offAdjSubRsn")
	private String offAdjSubRsn;

	@JsonProperty("offAdjTxnId")
	private Integer offAdjTxnId;

	@JsonProperty("offAdjRevRsn")
	private String offAdjRevRsn;

	@JsonProperty("billStatus")
	private String billStatus;

	@JsonProperty("billAgingStartDate")
	private Date billAgingStartDate;

	@JsonProperty("billAgingEndDate")
	private Date billAgingEndDate;

	@JsonProperty("billTxnComment")
	private String billTxnComment;

	@JsonProperty("originalBillId")
	private String originalBillId;

	@JsonProperty("originalBillTxnNo")
	private Integer originalBillTxnNo;

	@JsonProperty("originalOffAdjTxnId")
	private Integer originalOffAdjTxnId;

	@JsonProperty("txnStatementGeneratedFlag")
	private String txnStatementGeneratedFlag;

	@JsonProperty("billingStatementId")
	private Integer billingStatementId;

	@JsonProperty("offenderFeeId")
	private BigDecimal offenderFeeId;
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("feeCode")
	private String feeCode;

	@JsonProperty("description")
	private String description;

	@JsonProperty("amount")
	private Double amount;

	@JsonProperty("balance")
	private Double balance;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("billOverrideIncreaseDecAmount")
	private BigDecimal billOverrideIncreaseDecAmount;

	@JsonProperty("txnUsage")
	private String txnUsage;

	@JsonProperty("adjustmentAmountTot")
	private BigDecimal adjustmentAmountTot;

	@JsonProperty("adjustmentType")
	private String adjustmentType;

	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("billingCycleStartDate")
	private Date billingCycleStartDate;

	@JsonProperty("billingCycleEndDate")
	private Date billingCycleEndDate;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("casePlanId")
	private Integer casePlanId;

	@JsonProperty("adjustmentAmount")
	private BigDecimal adjustmentAmount;

	@JsonProperty("billArDueDate")
	private Date billArDueDate;

	@JsonProperty("balanceOwingAmount")
	private BigDecimal balanceOwingAmount;

	@JsonProperty("incre")
	private String incre;

	@JsonProperty("paymentAmount")
	private BigDecimal paymentAmount;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("billStatusDescription")
	private String billStatusDescription;

	@JsonProperty("txnReferenceNumber")
	private String txnReferenceNumber;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("billLdppEndDate")
	private Date billLdppEndDate;

	@JsonProperty("billLdppStartDate")
	private Date billLdppStartDate;

	@JsonProperty("billTxnTypeDesc")
	private String billTxnTypeDesc;

	@JsonProperty("liReturn")
	private Integer liReturn;

	@JsonProperty("billGenerateDatetime")
	private Date billGenerateDatetime;

	@JsonProperty("sessionId")
	private Long sessionId;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("feeActStatus")
	private String feeActStatus;

	@JsonProperty("billTxnAmountTot")
	private BigDecimal billTxnAmountTot;

	@JsonProperty("currentBalanceOwning")
	private BigDecimal currentBalanceOwning;

	@JsonProperty("previousAmount")
	private BigDecimal previousAmount;

	@JsonProperty("caseloadDesc")
	private String caseloadDesc;

	@JsonProperty("feecodeDesc")
	private String feecodeDesc;

	@JsonProperty("infoNumber")
	private String infoNumber;

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	@JsonProperty("billGenerateAmount")
	private BigDecimal billGenerateAmount;
	
	@JsonProperty("offAdjRevAmount")
	private BigDecimal offAdjRevAmount;
	
	@JsonProperty("BillArStartDate")
    private Date BillArStartDate;
	
	@JsonProperty("billTxnTypeDescripton")
	private String billTxnTypeDescripton;
	


	public OffFeeBillTransactions() {
		// constructor
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public Integer getBillTxnNo() {
		return billTxnNo;
	}

	public void setBillTxnNo(Integer billTxnNo) {
		this.billTxnNo = billTxnNo;
	}

	public Date getBillTxnDatetime() {
		return billTxnDatetime;
	}

	public void setBillTxnDatetime(Date billTxnDatetime) {
		this.billTxnDatetime = billTxnDatetime;
	}

	public Integer getBillTxnStaffId() {
		return billTxnStaffId;
	}

	public void setBillTxnStaffId(Integer billTxnStaffId) {
		this.billTxnStaffId = billTxnStaffId;
	}

	public String getBillTxnUser() {
		return billTxnUser;
	}

	public void setBillTxnUser(String billTxnUser) {
		this.billTxnUser = billTxnUser;
	}

	public String getBillTxnCategory() {
		return billTxnCategory;
	}

	public void setBillTxnCategory(String billTxnCategory) {
		this.billTxnCategory = billTxnCategory;
	}

	public String getBillTxnType() {
		return billTxnType;
	}

	public void setBillTxnType(String billTxnType) {
		this.billTxnType = billTxnType;
	}

	public BigDecimal getBillTxnAmount() {
		return billTxnAmount;
	}

	public void setBillTxnAmount(BigDecimal billTxnAmount) {
		this.billTxnAmount = billTxnAmount;
	}

	public Integer getTrustTxnId() {
		return trustTxnId;
	}

	public void setTrustTxnId(Integer trustTxnId) {
		this.trustTxnId = trustTxnId;
	}

	public Integer getTrustTxnEntrySeq() {
		return trustTxnEntrySeq;
	}

	public void setTrustTxnEntrySeq(Integer trustTxnEntrySeq) {
		this.trustTxnEntrySeq = trustTxnEntrySeq;
	}

	public String getOffAdjCancRsn() {
		return offAdjCancRsn;
	}

	public void setOffAdjCancRsn(String offAdjCancRsn) {
		this.offAdjCancRsn = offAdjCancRsn;
	}

	public String getOffAdjSubRsn() {
		return offAdjSubRsn;
	}

	public void setOffAdjSubRsn(String offAdjSubRsn) {
		this.offAdjSubRsn = offAdjSubRsn;
	}

	public Integer getOffAdjTxnId() {
		return offAdjTxnId;
	}

	public void setOffAdjTxnId(Integer offAdjTxnId) {
		this.offAdjTxnId = offAdjTxnId;
	}

	public String getOffAdjRevRsn() {
		return offAdjRevRsn;
	}

	public void setOffAdjRevRsn(String offAdjRevRsn) {
		this.offAdjRevRsn = offAdjRevRsn;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Date getBillAgingStartDate() {
		return billAgingStartDate;
	}

	public void setBillAgingStartDate(Date billAgingStartDate) {
		this.billAgingStartDate = billAgingStartDate;
	}

	public Date getBillAgingEndDate() {
		return billAgingEndDate;
	}

	public void setBillAgingEndDate(Date billAgingEndDate) {
		this.billAgingEndDate = billAgingEndDate;
	}

	public String getBillTxnComment() {
		return billTxnComment;
	}

	public void setBillTxnComment(String billTxnComment) {
		this.billTxnComment = billTxnComment;
	}

	public String getOriginalBillId() {
		return originalBillId;
	}

	public void setOriginalBillId(String originalBillId) {
		this.originalBillId = originalBillId;
	}

	public Integer getOriginalBillTxnNo() {
		return originalBillTxnNo;
	}

	public void setOriginalBillTxnNo(Integer originalBillTxnNo) {
		this.originalBillTxnNo = originalBillTxnNo;
	}

	public Integer getOriginalOffAdjTxnId() {
		return originalOffAdjTxnId;
	}

	public void setOriginalOffAdjTxnId(Integer originalOffAdjTxnId) {
		this.originalOffAdjTxnId = originalOffAdjTxnId;
	}

	public String getTxnStatementGeneratedFlag() {
		return txnStatementGeneratedFlag;
	}

	public void setTxnStatementGeneratedFlag(String txnStatementGeneratedFlag) {
		this.txnStatementGeneratedFlag = txnStatementGeneratedFlag;
	}

	public Integer getBillingStatementId() {
		return billingStatementId;
	}

	public void setBillingStatementId(Integer billingStatementId) {
		this.billingStatementId = billingStatementId;
	}

	public BigDecimal getOffenderFeeId() {
		return offenderFeeId;
	}

	public void setOffenderFeeId(BigDecimal offenderFeeId) {
		this.offenderFeeId = offenderFeeId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getBillOverrideIncreaseDecAmount() {
		return billOverrideIncreaseDecAmount;
	}

	public void setBillOverrideIncreaseDecAmount(BigDecimal billOverrideIncreaseDecAmount) {
		this.billOverrideIncreaseDecAmount = billOverrideIncreaseDecAmount;
	}

	public String getTxnUsage() {
		return txnUsage;
	}

	public void setTxnUsage(String txnUsage) {
		this.txnUsage = txnUsage;
	}

	public BigDecimal getAdjustmentAmountTot() {
		return adjustmentAmountTot;
	}

	public void setAdjustmentAmountTot(BigDecimal adjustmentAmountTot) {
		this.adjustmentAmountTot = adjustmentAmountTot;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public Date getBillingCycleStartDate() {
		return billingCycleStartDate;
	}

	public void setBillingCycleStartDate(Date billingCycleStartDate) {
		this.billingCycleStartDate = billingCycleStartDate;
	}

	public Date getBillingCycleEndDate() {
		return billingCycleEndDate;
	}

	public void setBillingCycleEndDate(Date billingCycleEndDate) {
		this.billingCycleEndDate = billingCycleEndDate;
	}

	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public Integer getCasePlanId() {
		return casePlanId;
	}

	public void setCasePlanId(Integer casePlanId) {
		this.casePlanId = casePlanId;
	}

	public BigDecimal getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public Date getBillArDueDate() {
		return billArDueDate;
	}

	public void setBillArDueDate(Date billArDueDate) {
		this.billArDueDate = billArDueDate;
	}

	public BigDecimal getBalanceOwingAmount() {
		return balanceOwingAmount;
	}

	public void setBalanceOwingAmount(BigDecimal balanceOwingAmount) {
		this.balanceOwingAmount = balanceOwingAmount;
	}

	public String getIncre() {
		return incre;
	}

	public void setIncre(String incre) {
		this.incre = incre;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBillStatusDescription() {
		return billStatusDescription;
	}

	public void setBillStatusDescription(String billStatusDescription) {
		this.billStatusDescription = billStatusDescription;
	}

	public String getTxnReferenceNumber() {
		return txnReferenceNumber;
	}

	public void setTxnReferenceNumber(String txnReferenceNumber) {
		this.txnReferenceNumber = txnReferenceNumber;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public Date getBillLdppEndDate() {
		return billLdppEndDate;
	}

	public void setBillLdppEndDate(Date billLdppEndDate) {
		this.billLdppEndDate = billLdppEndDate;
	}

	public Date getBillLdppStartDate() {
		return billLdppStartDate;
	}

	public void setBillLdppStartDate(Date billLdppStartDate) {
		this.billLdppStartDate = billLdppStartDate;
	}

	public String getBillTxnTypeDesc() {
		return billTxnTypeDesc;
	}

	public BigDecimal getBillGenerateAmount() {
		return billGenerateAmount;
	}

	public void setBillGenerateAmount(BigDecimal billGenerateAmount) {
		this.billGenerateAmount = billGenerateAmount;
	}

	public void setBillTxnTypeDesc(String billTxnTypeDesc) {
		this.billTxnTypeDesc = billTxnTypeDesc;
	}

	public Integer getLiReturn() {
		return liReturn;
	}

	public void setLiReturn(Integer liReturn) {
		this.liReturn = liReturn;
	}

	public Date getBillGenerateDatetime() {
		return billGenerateDatetime;
	}

	public void setBillGenerateDatetime(Date billGenerateDatetime) {
		this.billGenerateDatetime = billGenerateDatetime;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFeeActStatus() {
		return feeActStatus;
	}

	public void setFeeActStatus(String feeActStatus) {
		this.feeActStatus = feeActStatus;
	}

	public BigDecimal getBillTxnAmountTot() {
		return billTxnAmountTot;
	}

	public void setBillTxnAmountTot(BigDecimal billTxnAmountTot) {
		this.billTxnAmountTot = billTxnAmountTot;
	}

	public BigDecimal getCurrentBalanceOwning() {
		return currentBalanceOwning;
	}

	public void setCurrentBalanceOwning(BigDecimal currentBalanceOwning) {
		this.currentBalanceOwning = currentBalanceOwning;
	}

	public BigDecimal getPreviousAmount() {
		return previousAmount;
	}

	public void setPreviousAmount(BigDecimal previousAmount) {
		this.previousAmount = previousAmount;
	}

	public String getCaseloadDesc() {
		return caseloadDesc;
	}

	public void setCaseloadDesc(String caseloadDesc) {
		this.caseloadDesc = caseloadDesc;
	}

	public String getFeecodeDesc() {
		return feecodeDesc;
	}

	public void setFeecodeDesc(String feecodeDesc) {
		this.feecodeDesc = feecodeDesc;
	}

	public String getInfoNumber() {
		return infoNumber;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setInfoNumber(String infoNumber) {
		this.infoNumber = infoNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public BigDecimal getOffAdjRevAmount() {
		return offAdjRevAmount;
	}

	public void setOffAdjRevAmount(BigDecimal offAdjRevAmount) {
		this.offAdjRevAmount = offAdjRevAmount;
	}

	public Date getBillArStartDate() {
		return BillArStartDate;
	}

	public void setBillArStartDate(Date billArStartDate) {
		BillArStartDate = billArStartDate;
	}

	public String getBillTxnTypeDescripton() {
		return billTxnTypeDescripton;
	}

	public void setBillTxnTypeDescripton(String billTxnTypeDescripton) {
		this.billTxnTypeDescripton = billTxnTypeDescripton;
	}

		

}
