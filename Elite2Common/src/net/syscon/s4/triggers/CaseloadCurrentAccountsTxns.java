package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class CaseloadCurrentAccountsTxns extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long caseloadCurrentAccountId;
	private String caseloadId;
	private Integer accountCode;
	private Integer accountPeriodId;
	private BigDecimal currentBalance;
	private String modifyUserId;
	private Date modifyDate;
	private Integer listSeq;
	private Date consolidationDate;
	private Long txnId;
	private Integer txnEntrySeq;
	private Integer glEntrySeq;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String sealFlag;

	public Long getCaseloadCurrentAccountId() {
		return caseloadCurrentAccountId;
	}

	public void setCaseloadCurrentAccountId(Long caseloadCurrentAccountId) {
		this.caseloadCurrentAccountId = caseloadCurrentAccountId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Integer getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(Integer accountCode) {
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

	public Date getConsolidationDate() {
		return consolidationDate;
	}

	public void setConsolidationDate(Date consolidationDate) {
		this.consolidationDate = consolidationDate;
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
