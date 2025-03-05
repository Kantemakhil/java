package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VBankChequeBeneficiaries implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal chequeAmount;

	private BigDecimal chequeNumber;

	private Date chequePaidDate;

	private String chequeStatus;

	private BigDecimal chequeTxnId;

	private BigDecimal corporateId;

	private Date createDate;

	private BigDecimal personId;

	private Date txnEntryDate;

	private String txnEntryDesc;

	private Date txnEntryTime;

	private String txnPostUsage;

	public VBankChequeBeneficiaries() {
	}

	public BigDecimal getChequeAmount() {
		return this.chequeAmount;
	}

	public void setChequeAmount(BigDecimal chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public BigDecimal getChequeNumber() {
		return this.chequeNumber;
	}

	public void setChequeNumber(BigDecimal chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public Date getChequePaidDate() {
		return this.chequePaidDate;
	}

	public void setChequePaidDate(Date chequePaidDate) {
		this.chequePaidDate = chequePaidDate;
	}

	public String getChequeStatus() {
		return this.chequeStatus;
	}

	public void setChequeStatus(String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}

	public BigDecimal getChequeTxnId() {
		return this.chequeTxnId;
	}

	public void setChequeTxnId(BigDecimal chequeTxnId) {
		this.chequeTxnId = chequeTxnId;
	}

	public BigDecimal getCorporateId() {
		return this.corporateId;
	}

	public void setCorporateId(BigDecimal corporateId) {
		this.corporateId = corporateId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	public Date getTxnEntryDate() {
		return this.txnEntryDate;
	}

	public void setTxnEntryDate(Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	public String getTxnEntryDesc() {
		return this.txnEntryDesc;
	}

	public void setTxnEntryDesc(String txnEntryDesc) {
		this.txnEntryDesc = txnEntryDesc;
	}

	public Date getTxnEntryTime() {
		return this.txnEntryTime;
	}

	public void setTxnEntryTime(Date txnEntryTime) {
		this.txnEntryTime = txnEntryTime;
	}

	public String getTxnPostUsage() {
		return this.txnPostUsage;
	}

	public void setTxnPostUsage(String txnPostUsage) {
		this.txnPostUsage = txnPostUsage;
	}

}
