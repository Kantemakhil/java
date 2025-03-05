package net.syscon.s4.cf.deductions.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the OFFENDER_TIER_TXN_FEE_AMOUNTS database table.
 * 
 */
public class OffenderTierTxnFeeAmounts implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal feeAmount;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal percentage;

	private String sealFlag;

	private Long offenderDeductionId;

	private BigDecimal fromAmount;

	private BigDecimal toAmount;

	private String rowId;
	@JsonProperty("pFlag")
	private String pFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("deductionType")
	private String deductionType;
	
	public String getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	public String getpFlag() {
		return pFlag;
	}

	public void setpFlag(String pFlag) {
		this.pFlag = pFlag;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public OffenderTierTxnFeeAmounts() {
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(final String rowId) {
		this.rowId = rowId;
	}

	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}

	public void setOffenderDeductionId(final Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(final BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(final BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getFeeAmount() {
		return this.feeAmount;
	}

	public void setFeeAmount(final BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setPercentage(final BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
