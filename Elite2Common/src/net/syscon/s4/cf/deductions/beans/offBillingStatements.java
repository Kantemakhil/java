package net.syscon.s4.cf.deductions.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class offBillingStatements extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("billingStatementId")
	private Integer billingStatementId;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("billingCycleStartDate")
	private Date billingCycleStartDate;

	@JsonProperty("billingCycleEndDate")
	private Date billingCycleEndDate;

	@JsonProperty("statementGenerateDatetime")
	private Date statementGenerateDatetime;

	@JsonProperty("statementGenerateStaffId")
	private Integer statementGenerateStaffId;

	@JsonProperty("statementGenerateUser")
	private String statementGenerateUser;


	@JsonProperty("beginingBalanceAmount")
	private BigDecimal beginingBalanceAmount;

	@JsonProperty("paymentsCreditsAmount")
	private BigDecimal paymentsCreditsAmount;

	@JsonProperty("billingsAmount")
	private BigDecimal billingsAmount;

	@JsonProperty("endingBalanceAmount")
	private BigDecimal endingBalanceAmount;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("casePlanId")
	private Integer casePlanId;

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
	
	@JsonProperty("billId")
	private String billId;

	@JsonProperty("billTxnNo")
	private Integer billTxnNo;

	public offBillingStatements() {
		// constructor
	}

	public Integer getBillingStatementId() {
		return billingStatementId;
	}

	public void setBillingStatementId(Integer billingStatementId) {
		this.billingStatementId = billingStatementId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
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

	public Date getStatementGenerateDatetime() {
		return statementGenerateDatetime;
	}

	public void setStatementGenerateDatetime(Date statementGenerateDatetime) {
		this.statementGenerateDatetime = statementGenerateDatetime;
	}

	public Integer getStatementGenerateStaffId() {
		return statementGenerateStaffId;
	}

	public void setStatementGenerateStaffId(Integer statementGenerateStaffId) {
		this.statementGenerateStaffId = statementGenerateStaffId;
	}

	public String getStatementGenerateUser() {
		return statementGenerateUser;
	}

	public void setStatementGenerateUser(String statementGenerateUser) {
		this.statementGenerateUser = statementGenerateUser;
	}


	public BigDecimal getBeginingBalanceAmount() {
		return beginingBalanceAmount;
	}

	public void setBeginingBalanceAmount(BigDecimal beginingBalanceAmount) {
		this.beginingBalanceAmount = beginingBalanceAmount;
	}

	public BigDecimal getPaymentsCreditsAmount() {
		return paymentsCreditsAmount;
	}

	public void setPaymentsCreditsAmount(BigDecimal paymentsCreditsAmount) {
		this.paymentsCreditsAmount = paymentsCreditsAmount;
	}

	public BigDecimal getBillingsAmount() {
		return billingsAmount;
	}

	public void setBillingsAmount(BigDecimal billingsAmount) {
		this.billingsAmount = billingsAmount;
	}

	public BigDecimal getEndingBalanceAmount() {
		return endingBalanceAmount;
	}

	public void setEndingBalanceAmount(BigDecimal endingBalanceAmount) {
		this.endingBalanceAmount = endingBalanceAmount;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getCasePlanId() {
		return casePlanId;
	}

	public void setCasePlanId(Integer casePlanId) {
		this.casePlanId = casePlanId;
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
}
