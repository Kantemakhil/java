package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class BankReconAudits
 * 
 */
public class BankReconAudits extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal adjBookBal;

	private String adjustmentComment;

	private BigDecimal adjustmentGlNumber;

	private BigDecimal amntLessAdj;

	private String amntLessComment;

	private BigDecimal amntPlusAdj;

	private String amntPlusComment;

	private BigDecimal balanceDiscrepancy;

	private BigDecimal bankBalance;

	private BigDecimal bankClearedAmount;

	private BigDecimal bankClearedCount;

	private Date bankStatementDate;

	private String bankStatementStatus;

	private BigDecimal bankUnpostedAmount;

	private BigDecimal bankUnpostedCount;

	private BigDecimal bookClearedAmount;

	private BigDecimal bookClearedCount;

	private BigDecimal bookOutstandingAmount;

	private BigDecimal bookOutstandingCount;

	private BigDecimal clearingDiscrepancy;

	private BigDecimal closingBankBalance;

	private BigDecimal closingBookBalance;

	private Date createDate;

	private Date createDateTime;

	private String createUserId;

	private BigDecimal glOpenBal;

	private BigDecimal listSeq;

	private Date modifyDateTime;

	private String modifyUserId;

	private Date reconDatetime;

	private BigDecimal reconId;

	private String reconUserId;

	private String sealFlag;

	private String status;
	
	private Integer accountCode;
	
	private String caseloadId;

	public BankReconAudits() {
		// BankReconAudits
	}

	public BigDecimal getAdjBookBal() {
		return this.adjBookBal;
	}

	public void setAdjBookBal(BigDecimal adjBookBal) {
		this.adjBookBal = adjBookBal;
	}

	public String getAdjustmentComment() {
		return this.adjustmentComment;
	}

	public void setAdjustmentComment(String adjustmentComment) {
		this.adjustmentComment = adjustmentComment;
	}

	public BigDecimal getAdjustmentGlNumber() {
		return this.adjustmentGlNumber;
	}

	public void setAdjustmentGlNumber(BigDecimal adjustmentGlNumber) {
		this.adjustmentGlNumber = adjustmentGlNumber;
	}

	public BigDecimal getAmntLessAdj() {
		return this.amntLessAdj;
	}

	public void setAmntLessAdj(BigDecimal amntLessAdj) {
		this.amntLessAdj = amntLessAdj;
	}

	public String getAmntLessComment() {
		return this.amntLessComment;
	}

	public void setAmntLessComment(String amntLessComment) {
		this.amntLessComment = amntLessComment;
	}

	public BigDecimal getAmntPlusAdj() {
		return this.amntPlusAdj;
	}

	public void setAmntPlusAdj(BigDecimal amntPlusAdj) {
		this.amntPlusAdj = amntPlusAdj;
	}

	public String getAmntPlusComment() {
		return this.amntPlusComment;
	}

	public void setAmntPlusComment(String amntPlusComment) {
		this.amntPlusComment = amntPlusComment;
	}

	public BigDecimal getBalanceDiscrepancy() {
		return this.balanceDiscrepancy;
	}

	public void setBalanceDiscrepancy(BigDecimal balanceDiscrepancy) {
		this.balanceDiscrepancy = balanceDiscrepancy;
	}

	public BigDecimal getBankBalance() {
		return this.bankBalance;
	}

	public void setBankBalance(BigDecimal bankBalance) {
		this.bankBalance = bankBalance;
	}

	public BigDecimal getBankClearedAmount() {
		return this.bankClearedAmount;
	}

	public void setBankClearedAmount(BigDecimal bankClearedAmount) {
		this.bankClearedAmount = bankClearedAmount;
	}

	public BigDecimal getBankClearedCount() {
		return this.bankClearedCount;
	}

	public void setBankClearedCount(BigDecimal bankClearedCount) {
		this.bankClearedCount = bankClearedCount;
	}

	public Date getBankStatementDate() {
		return this.bankStatementDate;
	}

	public void setBankStatementDate(Date bankStatementDate) {
		this.bankStatementDate = bankStatementDate;
	}

	public String getBankStatementStatus() {
		return this.bankStatementStatus;
	}

	public void setBankStatementStatus(String bankStatementStatus) {
		this.bankStatementStatus = bankStatementStatus;
	}

	public BigDecimal getBankUnpostedAmount() {
		return this.bankUnpostedAmount;
	}

	public void setBankUnpostedAmount(BigDecimal bankUnpostedAmount) {
		this.bankUnpostedAmount = bankUnpostedAmount;
	}

	public BigDecimal getBankUnpostedCount() {
		return this.bankUnpostedCount;
	}

	public void setBankUnpostedCount(BigDecimal bankUnpostedCount) {
		this.bankUnpostedCount = bankUnpostedCount;
	}

	public BigDecimal getBookClearedAmount() {
		return this.bookClearedAmount;
	}

	public void setBookClearedAmount(BigDecimal bookClearedAmount) {
		this.bookClearedAmount = bookClearedAmount;
	}

	public BigDecimal getBookClearedCount() {
		return this.bookClearedCount;
	}

	public void setBookClearedCount(BigDecimal bookClearedCount) {
		this.bookClearedCount = bookClearedCount;
	}

	public BigDecimal getBookOutstandingAmount() {
		return this.bookOutstandingAmount;
	}

	public void setBookOutstandingAmount(BigDecimal bookOutstandingAmount) {
		this.bookOutstandingAmount = bookOutstandingAmount;
	}

	public BigDecimal getBookOutstandingCount() {
		return this.bookOutstandingCount;
	}

	public void setBookOutstandingCount(BigDecimal bookOutstandingCount) {
		this.bookOutstandingCount = bookOutstandingCount;
	}

	public BigDecimal getClearingDiscrepancy() {
		return this.clearingDiscrepancy;
	}

	public void setClearingDiscrepancy(BigDecimal clearingDiscrepancy) {
		this.clearingDiscrepancy = clearingDiscrepancy;
	}

	public BigDecimal getClosingBankBalance() {
		return this.closingBankBalance;
	}

	public void setClosingBankBalance(BigDecimal closingBankBalance) {
		this.closingBankBalance = closingBankBalance;
	}

	public BigDecimal getClosingBookBalance() {
		return this.closingBookBalance;
	}

	public void setClosingBookBalance(BigDecimal closingBookBalance) {
		this.closingBookBalance = closingBookBalance;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getGlOpenBal() {
		return this.glOpenBal;
	}

	public void setGlOpenBal(BigDecimal glOpenBal) {
		this.glOpenBal = glOpenBal;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	public void setModifyDatetime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getReconDatetime() {
		return this.reconDatetime;
	}

	public void setReconDatetime(Date reconDatetime) {
		this.reconDatetime = reconDatetime;
	}

	public BigDecimal getReconId() {
		return this.reconId;
	}

	public void setReconId(BigDecimal reconId) {
		this.reconId = reconId;
	}

	public String getReconUserId() {
		return this.reconUserId;
	}

	public void setReconUserId(String reconUserId) {
		this.reconUserId = reconUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(Integer accountCode) {
		this.accountCode = accountCode;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

}
