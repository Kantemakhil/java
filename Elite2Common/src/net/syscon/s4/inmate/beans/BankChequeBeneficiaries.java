package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * class BankChequeBeneficiaries
 * 
 */
public class BankChequeBeneficiaries extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("chequeAmount")
	private BigDecimal chequeAmount;

	@JsonProperty("chequeTxnId")
	private BigDecimal chequeTxnId;

	@JsonProperty("corporateId")
	private BigDecimal corporateId;

	@JsonProperty("createDatetime")
	private Object createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Object modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderDeductionId")
	private BigDecimal offenderDeductionId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("personId")
	private BigDecimal personId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnId")
	private BigDecimal txnId;

	public BankChequeBeneficiaries() {
		// BankChequeBeneficiaries
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getChequeAmount() {
		return this.chequeAmount;
	}

	public void setChequeAmount(final BigDecimal chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public BigDecimal getChequeTxnId() {
		return this.chequeTxnId;
	}

	public void setChequeTxnId(final BigDecimal chequeTxnId) {
		this.chequeTxnId = chequeTxnId;
	}

	public BigDecimal getCorporateId() {
		return this.corporateId;
	}

	public void setCorporateId(final BigDecimal corporateId) {
		this.corporateId = corporateId;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getOffenderDeductionId() {
		return this.offenderDeductionId;
	}

	public void setOffenderDeductionId(final BigDecimal offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(final BigDecimal personId) {
		this.personId = personId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getTxnId() {
		return this.txnId;
	}

	public void setTxnId(final BigDecimal txnId) {
		this.txnId = txnId;
	}

}