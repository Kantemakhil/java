package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class ComCsldCurrentAccountsBase extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String caseloadId;
	private String accountCode;
	private Integer accountPeriodId;
	private BigDecimal currentBalance;
	private String bankAccountType;
	private String bankAccountNumber;
	private String accountPartyType;
	private Long payeePersonId;
	private Long payeeCorporateId;
	private String modifyUserId;
	private Date modifyDate;
	private Integer listSeq;
	private Long routingNumber;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String sealFlag;

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Integer getAccountPeriodId() {
		return accountPeriodId;
	}

	public void setAccountPeriodId(Integer accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getAccountPartyType() {
		return accountPartyType;
	}

	public void setAccountPartyType(String accountPartyType) {
		this.accountPartyType = accountPartyType;
	}

	public Long getPayeePersonId() {
		return payeePersonId;
	}

	public void setPayeePersonId(Long payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	public Long getPayeeCorporateId() {
		return payeeCorporateId;
	}

	public void setPayeeCorporateId(Long payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public Long getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(Long routingNumber) {
		this.routingNumber = routingNumber;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
