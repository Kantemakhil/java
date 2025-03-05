package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class BeneficiaryTransactions extends BaseModel implements Serializable {
	private static final Long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private BigDecimal accountCode;

	@JsonProperty("beneficiaryClearedFlag")
	private String beneficiaryClearedFlag;

	@JsonProperty("beneficiaryId")
	private BigDecimal beneficiaryId;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("checkClearedDate")
	private Date checkClearedDate;

	@JsonProperty("corporateId")
	private BigDecimal corporateId;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderDeductionId")
	private BigDecimal offenderDeductionId;

	@JsonProperty("personId")
	private BigDecimal personId;

	@JsonProperty("receiptTxnType")
	private String receiptTxnType;

	@JsonProperty("revBenEntrySeq")
	private BigDecimal revBenEntrySeq;

	@JsonProperty("revGlEntrySeq")
	private BigDecimal revGlEntrySeq;

	@JsonProperty("revTxnEntrySeq")
	private BigDecimal revTxnEntrySeq;

	@JsonProperty("revTxnFlag")
	private String revTxnFlag;

	@JsonProperty("revTxnId")
	private BigDecimal revTxnId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnEntryAmount")
	private BigDecimal txnEntryAmount;

	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;

	@JsonProperty("txnEntryDesc")
	private String txnEntryDesc;

	@JsonProperty("txnEntryTime")
	private Date txnEntryTime;

	@JsonProperty("txnPostUsage")
	private String txnPostUsage;

	@JsonProperty("txnType")
	private String txnType;

	@JsonProperty("unknownBenId")
	private BigDecimal unknownBenId;

	@JsonProperty("txnId")
	private Long txnId;

	@JsonProperty("txnEntrySeq")
	private Long txnEntrySeq;

	@JsonProperty("glEntrySeq")
	private Long glEntrySeq;

	@JsonProperty("benEntrySeq")
	private Long benEntrySeq;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("rootOffenderId")
	private Long rootOffenderId;

	@JsonProperty("lastFirstName")
	private String lastFirstName;

	@JsonProperty("offTxnId")
	private String offTxnId;
	
	
	@JsonProperty("offenderId")
	private String offenderId;


	public String getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(String offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffTxnId() {
		return offTxnId;
	}

	public void setOffTxnId(final String offTxnId) {
		this.offTxnId = offTxnId;
	}

	public BeneficiaryTransactions() {
		// BeneficiaryTransactions
	}

	public BigDecimal getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
	}

	public String getBeneficiaryClearedFlag() {
		return this.beneficiaryClearedFlag;
	}

	public void setBeneficiaryClearedFlag(final String beneficiaryClearedFlag) {
		this.beneficiaryClearedFlag = beneficiaryClearedFlag;
	}

	public BigDecimal getBeneficiaryId() {
		return this.beneficiaryId;
	}

	public void setBeneficiaryId(final BigDecimal beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Date getCheckClearedDate() {
		return this.checkClearedDate;
	}

	public void setCheckClearedDate(final Date checkClearedDate) {
		this.checkClearedDate = checkClearedDate;
	}

	public BigDecimal getCorporateId() {
		return this.corporateId;
	}

	public void setCorporateId(final BigDecimal corporateId) {
		this.corporateId = corporateId;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(final BigDecimal personId) {
		this.personId = personId;
	}

	public String getReceiptTxnType() {
		return this.receiptTxnType;
	}

	public void setReceiptTxnType(final String receiptTxnType) {
		this.receiptTxnType = receiptTxnType;
	}

	public BigDecimal getRevBenEntrySeq() {
		return this.revBenEntrySeq;
	}

	public void setRevBenEntrySeq(final BigDecimal revBenEntrySeq) {
		this.revBenEntrySeq = revBenEntrySeq;
	}

	public BigDecimal getRevGlEntrySeq() {
		return this.revGlEntrySeq;
	}

	public void setRevGlEntrySeq(final BigDecimal revGlEntrySeq) {
		this.revGlEntrySeq = revGlEntrySeq;
	}

	public BigDecimal getRevTxnEntrySeq() {
		return this.revTxnEntrySeq;
	}

	public void setRevTxnEntrySeq(final BigDecimal revTxnEntrySeq) {
		this.revTxnEntrySeq = revTxnEntrySeq;
	}

	public String getRevTxnFlag() {
		return this.revTxnFlag;
	}

	public void setRevTxnFlag(final String revTxnFlag) {
		this.revTxnFlag = revTxnFlag;
	}

	public BigDecimal getRevTxnId() {
		return this.revTxnId;
	}

	public void setRevTxnId(final BigDecimal revTxnId) {
		this.revTxnId = revTxnId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getTxnEntryAmount() {
		return this.txnEntryAmount;
	}

	public void setTxnEntryAmount(final BigDecimal txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

	public Date getTxnEntryDate() {
		return this.txnEntryDate;
	}

	public void setTxnEntryDate(final Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	public String getTxnEntryDesc() {
		return this.txnEntryDesc;
	}

	public void setTxnEntryDesc(final String txnEntryDesc) {
		this.txnEntryDesc = txnEntryDesc;
	}

	public Date getTxnEntryTime() {
		return this.txnEntryTime;
	}

	public void setTxnEntryTime(final Date txnEntryTime) {
		this.txnEntryTime = txnEntryTime;
	}

	public String getTxnPostUsage() {
		return this.txnPostUsage;
	}

	public void setTxnPostUsage(final String txnPostUsage) {
		this.txnPostUsage = txnPostUsage;
	}

	public String getTxnType() {
		return this.txnType;
	}

	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	public BigDecimal getUnknownBenId() {
		return this.unknownBenId;
	}

	public void setUnknownBenId(final BigDecimal unknownBenId) {
		this.unknownBenId = unknownBenId;
	}

	public Long getTxnId() {
		return this.txnId;
	}

	public void setTxnId(final Long txnId) {
		this.txnId = txnId;
	}

	public Long getTxnEntrySeq() {
		return this.txnEntrySeq;
	}

	public void setTxnEntrySeq(final Long txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	public Long getGlEntrySeq() {
		return this.glEntrySeq;
	}

	public void setGlEntrySeq(final Long glEntrySeq) {
		this.glEntrySeq = glEntrySeq;
	}

	public Long getBenEntrySeq() {
		return this.benEntrySeq;
	}

	public void setBenEntrySeq(final Long benEntrySeq) {
		this.benEntrySeq = benEntrySeq;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the rootOffenderId
	 */
	public Long getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(final Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the lastFirstName
	 */
	public String getLastFirstName() {
		return lastFirstName;
	}

	/**
	 * @param lastFirstName
	 *            the lastFirstName to set
	 */
	public void setLastFirstName(final String lastFirstName) {
		this.lastFirstName = lastFirstName;
	}

}
