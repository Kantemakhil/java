package net.syscon.s4.common.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CaseloadAccountSummaries extends BaseModel {
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("accountCode")
	private Integer accountCode;
	
	@JsonProperty("accountPeriodId")
	private Long accountPeriodId;
	
	@JsonProperty("openBalance")
	private BigDecimal openBalance;
	
	@JsonProperty("closeBalance")
	private BigDecimal closeBalance;
	
	@JsonProperty("periodTxnAmount")
	private BigDecimal periodTxnAmount;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("modifyDate")
	private Date modifyDate;
	
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
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

	public Long getAccountPeriodId() {
		return accountPeriodId;
	}

	public void setAccountPeriodId(Long accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	public BigDecimal getOpenBalance() {
		return openBalance;
	}

	public void setOpenBalance(BigDecimal openBalance) {
		this.openBalance = openBalance;
	}

	public BigDecimal getCloseBalance() {
		return closeBalance;
	}

	public void setCloseBalance(BigDecimal closeBalance) {
		this.closeBalance = closeBalance;
	}

	public BigDecimal getPeriodTxnAmount() {
		return periodTxnAmount;
	}

	public void setPeriodTxnAmount(BigDecimal periodTxnAmount) {
		this.periodTxnAmount = periodTxnAmount;
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

	public BigDecimal getListSeq() {
		return listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("sealFlag")
	private String sealFlag;

}
