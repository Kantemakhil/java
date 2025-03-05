package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the OFFENDER_ADJUSTMENT_TXNS database table.
 * 
 */
public class OffenderAdjustmentTxns extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("adjustmentAmount")
	private BigDecimal adjustmentAmount;

	@JsonProperty("adjustmentDate")
	private Date adjustmentDate;

	@JsonProperty("adjustmentReasonCode")
	private String adjustmentReasonCode;

	@JsonProperty("adjustmentText")
	private String adjustmentText;

	@JsonProperty("adjustmentUserId")
	private String adjustmentUserId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnPostingType")
	private String txnPostingType;

	@JsonProperty("txnId")
	private Long txnId;

	@JsonProperty("txnEntrySeq")
	private Long txnEntrySeq;
	
	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;
	
	@JsonProperty("drAccountCode")
	private Integer drAccountCode;
	
	@JsonProperty("accountCode")
	private Integer accountCode;
	
	@JsonProperty("cr")
	private String cr;
	
	@JsonProperty("txnType")
	private String txnType;
	
	@JsonProperty("deductionType")
	private String deductionType;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("txnEntryAmnt")
	private BigDecimal txnEntryAmnt;
	
	@JsonProperty("txnEntryDesc")
	private String txnEntryDesc;
	
	@JsonProperty("lvWriteOffAmount")
	private BigDecimal lvWriteOffAmount;
	
	
	
	
	
	
	public OffenderAdjustmentTxns() {
		// OffenderAdjustmentTxns
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(final Long txnId) {
		this.txnId = txnId;
	}

	public long getTxnEntrySeq() {
		return txnEntrySeq;
	}

	public void setTxnEntrySeq(final Long txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}


	public BigDecimal getAdjustmentAmount() {
		return this.adjustmentAmount;
	}

	public void setAdjustmentAmount(final BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public Date getAdjustmentDate() {
		return this.adjustmentDate;
	}

	public void setAdjustmentDate(final Date adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}

	public String getAdjustmentReasonCode() {
		return this.adjustmentReasonCode;
	}

	public void setAdjustmentReasonCode(final String adjustmentReasonCode) {
		this.adjustmentReasonCode = adjustmentReasonCode;
	}

	public String getAdjustmentText() {
		return this.adjustmentText;
	}

	public void setAdjustmentText(final String adjustmentText) {
		this.adjustmentText = adjustmentText;
	}

	public String getAdjustmentUserId() {
		return this.adjustmentUserId;
	}

	public void setAdjustmentUserId(final String adjustmentUserId) {
		this.adjustmentUserId = adjustmentUserId;
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

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getTxnPostingType() {
		return this.txnPostingType;
	}

	public void setTxnPostingType(final String txnPostingType) {
		this.txnPostingType = txnPostingType;
	}

	/**
	 * @return the offenderDeductionId
	 */
	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}

	/**
	 * @param offenderDeductionId the offenderDeductionId to set
	 */
	public void setOffenderDeductionId(final Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	/**
	 * @return the drAccountCode
	 */
	public Integer getDrAccountCode() {
		return drAccountCode;
	}

	/**
	 * @param drAccountCode the drAccountCode to set
	 */
	public void setDrAccountCode(final Integer drAccountCode) {
		this.drAccountCode = drAccountCode;
	}

	/**
	 * @return the accountCode
	 */
	public Integer getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final Integer accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the cr
	 */
	public String getCr() {
		return cr;
	}

	/**
	 * @param cr the cr to set
	 */
	public void setCr(final String cr) {
		this.cr = cr;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	/**
	 * @return the deductionType
	 */
	public String getDeductionType() {
		return deductionType;
	}

	/**
	 * @param deductionType the deductionType to set
	 */
	public void setDeductionType(final String deductionType) {
		this.deductionType = deductionType;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the txnEntryAmnt
	 */
	public BigDecimal getTxnEntryAmnt() {
		return txnEntryAmnt;
	}

	/**
	 * @param txnEntryAmnt the txnEntryAmnt to set
	 */
	public void setTxnEntryAmnt(final BigDecimal txnEntryAmnt) {
		this.txnEntryAmnt = txnEntryAmnt;
	}

	/**
	 * @return the txnEntryDesc
	 */
	public String getTxnEntryDesc() {
		return txnEntryDesc;
	}

	/**
	 * @param txnEntryDesc the txnEntryDesc to set
	 */
	public void setTxnEntryDesc(final String txnEntryDesc) {
		this.txnEntryDesc = txnEntryDesc;
	}

	/**
	 * @return the lvWriteOffAmount
	 */
	public BigDecimal getLvWriteOffAmount() {
		return lvWriteOffAmount;
	}

	/**
	 * @param lvWriteOffAmount the lvWriteOffAmount to set
	 */
	public void setLvWriteOffAmount(final BigDecimal lvWriteOffAmount) {
		this.lvWriteOffAmount = lvWriteOffAmount;
	}

}
