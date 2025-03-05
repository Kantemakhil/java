package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class GlTransactions
 * 
 */
public class GlTransactions extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private BigDecimal accountCode;
	@JsonProperty("accountCodeOne")
	private BigDecimal accountCodeOne;
	@JsonProperty("accountCodeTwo")
	private BigDecimal accountCodeTwo;
	@JsonProperty("accountPeriodId")
	private BigDecimal accountPeriodId;
	@JsonProperty("bankStatementDate")
	private Date bankStatementDate;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("createDate")
	private Date createDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("deductionId")
	private BigDecimal deductionId;
	@JsonProperty("infoNumber")
	private String infoNumber;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("payeeClearFlag")
	private String payeeClearFlag;
	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;
	@JsonProperty("payeeNameText")
	private String payeeNameText;
	@JsonProperty("payeePersonId")
	private BigDecimal payeePersonId;
	@JsonProperty("receiptNumber")
	private String receiptNumber;
	@JsonProperty("reconClearFlag")
	private String reconClearFlag;
	@JsonProperty("reversalReasonCode")
	private String reversalReasonCode;
	@JsonProperty("reversedGlEntrySeq")
	private BigDecimal reversedGlEntrySeq;
	@JsonProperty("reversedTxnEntrySeq")
	private BigDecimal reversedTxnEntrySeq;
	@JsonProperty("reversedTxnId")
	private BigDecimal reversedTxnId;
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
	@JsonProperty("txnLocId")
	private String txnLocId;
	@JsonProperty("txnObjectCode")
	private String txnObjectCode;
	@JsonProperty("txnObjectId")
	private BigDecimal txnObjectId;
	@JsonProperty("txnPostUsage")
	private String txnPostUsage;
	@JsonProperty("txnReferenceNumber")
	private String txnReferenceNumber;
	@JsonProperty("txnReversedFlag")
	private String txnReversedFlag;
	@JsonProperty("txnType")
	private String txnType;
	@JsonProperty("txnId")
	private Long txnId;
	@JsonProperty("txnEntrySeq")
	private Long txnEntrySeq;
	@JsonProperty("glEntrySeq")
	private Long glEntrySeq;
	@JsonProperty("txnReversedFlagOne")
	private String txnReversedFlagOne;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("description")
	private String description;
	@JsonProperty("txnEntryDescOne")
	private String txnEntryDescOne;
	@JsonProperty("nextButton")
	private String nextButton;
	@JsonProperty("deductionType")
	private String deductionType;
	@JsonProperty("offTxnType")
	private String offTxnType;
	@JsonProperty("offDeductionId")
	private BigDecimal offDeductionId;
	@JsonProperty("dspTxnPostingType")
	private String dspTxnPostingType;
	@JsonProperty("dspAccountName")
	private String dspAccountName;
	@JsonProperty("nbtBalance")
	private BigDecimal nbtBalance;
	@JsonProperty("nbtTxnType")
	private String nbtTxnType;
	@JsonProperty("nbtBalanceDisplay")
	private String nbtBalanceDisplay;
	@JsonProperty("drAccountCode")
	private BigDecimal drAccountCode;
	@JsonProperty("crAccountCode")
	private BigDecimal crAccountCode;
	@JsonProperty("caseloadType")
	private String caseloadType;
	@JsonProperty("nbtOffenderId")
	private BigDecimal nbtOffenderId;
	@JsonProperty("checkProduce")
	private String checkProduce;
	@JsonProperty("txnPostUsageGrid")
	private String txnPostUsageGrid;
	@JsonProperty("cgnbtPayeeNameTextOne")
	private BigDecimal cgnbtPayeeNameTextOne;
	@JsonProperty("cgnbtPayeeNameTextTwo")
	private BigDecimal cgnbtPayeeNameTextTwo;
	
	@JsonProperty("txnPostUsageCr")
	private String txnPostUsageCr;
	@JsonProperty("nbtOffenderIdDisplay")
	private String nbtOffenderIdDisplay;
	
	@JsonProperty("acntPosting")
	private String acntPosting;
	private String interfaceFlag;
	private String voidFlag;
	private String futureFlag;
	private Date lastReconDate;
	private String selectMode;
	

	public Date getLastReconDate() {
		return lastReconDate;
	}

	public void setLastReconDate(Date lastReconDate) {
		this.lastReconDate = lastReconDate;
	}

	public String getSelectMode() {
		return selectMode;
	}

	public void setSelectMode(String selectMode) {
		this.selectMode = selectMode;
	}

	public String getInterfaceFlag() {
		return interfaceFlag;
	}

	public void setInterfaceFlag(String interfaceFlag) {
		this.interfaceFlag = interfaceFlag;
	}

	public String getVoidFlag() {
		return voidFlag;
	}

	public void setVoidFlag(String voidFlag) {
		this.voidFlag = voidFlag;
	}

	public String getFutureFlag() {
		return futureFlag;
	}

	public void setFutureFlag(String futureFlag) {
		this.futureFlag = futureFlag;
	}

	public String getAcntPosting() {
		return acntPosting;
	}

	public void setAcntPosting(String acntPosting) {
		this.acntPosting = acntPosting;
	}

	/**
	 * @return the accountCode
	 */
	public BigDecimal getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode
	 *            the accountCode to set
	 */
	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the accountCodeOne
	 */
	public BigDecimal getAccountCodeOne() {
		return accountCodeOne;
	}

	/**
	 * @param accountCodeOne
	 *            the accountCodeOne to set
	 */
	public void setAccountCodeOne(final BigDecimal accountCodeOne) {
		this.accountCodeOne = accountCodeOne;
	}

	/**
	 * @return the accountCodeTwo
	 */
	public BigDecimal getAccountCodeTwo() {
		return accountCodeTwo;
	}

	/**
	 * @param accountCodeTwo
	 *            the accountCodeTwo to set
	 */
	public void setAccountCodeTwo(final BigDecimal accountCodeTwo) {
		this.accountCodeTwo = accountCodeTwo;
	}

	/**
	 * @return the accountPeriodId
	 */
	public BigDecimal getAccountPeriodId() {
		return accountPeriodId;
	}

	/**
	 * @param accountPeriodId
	 *            the accountPeriodId to set
	 */
	public void setAccountPeriodId(final BigDecimal accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	/**
	 * @return the bankStatementDate
	 */
	public Date getBankStatementDate() {
		return bankStatementDate;
	}

	/**
	 * @param bankStatementDate
	 *            the bankStatementDate to set
	 */
	public void setBankStatementDate(final Date bankStatementDate) {
		this.bankStatementDate = bankStatementDate;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the deductionId
	 */
	public BigDecimal getDeductionId() {
		return deductionId;
	}

	/**
	 * @param deductionId
	 *            the deductionId to set
	 */
	public void setDeductionId(final BigDecimal deductionId) {
		this.deductionId = deductionId;
	}

	/**
	 * @return the infoNumber
	 */
	public String getInfoNumber() {
		return infoNumber;
	}

	/**
	 * @param infoNumber
	 *            the infoNumber to set
	 */
	public void setInfoNumber(final String infoNumber) {
		this.infoNumber = infoNumber;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the payeeClearFlag
	 */
	public String getPayeeClearFlag() {
		return payeeClearFlag;
	}

	/**
	 * @param payeeClearFlag
	 *            the payeeClearFlag to set
	 */
	public void setPayeeClearFlag(final String payeeClearFlag) {
		this.payeeClearFlag = payeeClearFlag;
	}

	/**
	 * @return the payeeCorporateId
	 */
	public BigDecimal getPayeeCorporateId() {
		return payeeCorporateId;
	}

	/**
	 * @param payeeCorporateId
	 *            the payeeCorporateId to set
	 */
	public void setPayeeCorporateId(final BigDecimal payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	/**
	 * @return the payeeNameText
	 */
	public String getPayeeNameText() {
		return payeeNameText;
	}

	/**
	 * @param payeeNameText
	 *            the payeeNameText to set
	 */
	public void setPayeeNameText(final String payeeNameText) {
		this.payeeNameText = payeeNameText;
	}

	/**
	 * @return the payeePersonId
	 */
	public BigDecimal getPayeePersonId() {
		return payeePersonId;
	}

	/**
	 * @param payeePersonId
	 *            the payeePersonId to set
	 */
	public void setPayeePersonId(final BigDecimal payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	/**
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber
	 *            the receiptNumber to set
	 */
	public void setReceiptNumber(final String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	/**
	 * @return the reconClearFlag
	 */
	public String getReconClearFlag() {
		return reconClearFlag;
	}

	/**
	 * @param reconClearFlag
	 *            the reconClearFlag to set
	 */
	public void setReconClearFlag(final String reconClearFlag) {
		this.reconClearFlag = reconClearFlag;
	}

	/**
	 * @return the reversalReasonCode
	 */
	public String getReversalReasonCode() {
		return reversalReasonCode;
	}

	/**
	 * @param reversalReasonCode
	 *            the reversalReasonCode to set
	 */
	public void setReversalReasonCode(final String reversalReasonCode) {
		this.reversalReasonCode = reversalReasonCode;
	}

	/**
	 * @return the reversedGlEntrySeq
	 */
	public BigDecimal getReversedGlEntrySeq() {
		return reversedGlEntrySeq;
	}

	/**
	 * @param reversedGlEntrySeq
	 *            the reversedGlEntrySeq to set
	 */
	public void setReversedGlEntrySeq(final BigDecimal reversedGlEntrySeq) {
		this.reversedGlEntrySeq = reversedGlEntrySeq;
	}

	/**
	 * @return the reversedTxnEntrySeq
	 */
	public BigDecimal getReversedTxnEntrySeq() {
		return reversedTxnEntrySeq;
	}

	/**
	 * @param reversedTxnEntrySeq
	 *            the reversedTxnEntrySeq to set
	 */
	public void setReversedTxnEntrySeq(final BigDecimal reversedTxnEntrySeq) {
		this.reversedTxnEntrySeq = reversedTxnEntrySeq;
	}

	/**
	 * @return the reversedTxnId
	 */
	public BigDecimal getReversedTxnId() {
		return reversedTxnId;
	}

	/**
	 * @param reversedTxnId
	 *            the reversedTxnId to set
	 */
	public void setReversedTxnId(final BigDecimal reversedTxnId) {
		this.reversedTxnId = reversedTxnId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the txnEntryAmount
	 */
	public BigDecimal getTxnEntryAmount() {
		return txnEntryAmount;
	}

	/**
	 * @param txnEntryAmount
	 *            the txnEntryAmount to set
	 */
	public void setTxnEntryAmount(final BigDecimal txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

	/**
	 * @return the txnEntryDate
	 */
	public Date getTxnEntryDate() {
		return txnEntryDate;
	}

	/**
	 * @param txnEntryDate
	 *            the txnEntryDate to set
	 */
	public void setTxnEntryDate(final Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	/**
	 * @return the txnEntryDesc
	 */
	public String getTxnEntryDesc() {
		return txnEntryDesc;
	}

	/**
	 * @param txnEntryDesc
	 *            the txnEntryDesc to set
	 */
	public void setTxnEntryDesc(final String txnEntryDesc) {
		this.txnEntryDesc = txnEntryDesc;
	}

	/**
	 * @return the txnEntryTime
	 */
	public Date getTxnEntryTime() {
		return txnEntryTime;
	}

	/**
	 * @param txnEntryTime
	 *            the txnEntryTime to set
	 */
	public void setTxnEntryTime(final Date txnEntryTime) {
		this.txnEntryTime = txnEntryTime;
	}

	/**
	 * @return the txnLocId
	 */
	public String getTxnLocId() {
		return txnLocId;
	}

	/**
	 * @param txnLocId
	 *            the txnLocId to set
	 */
	public void setTxnLocId(final String txnLocId) {
		this.txnLocId = txnLocId;
	}

	/**
	 * @return the txnObjectCode
	 */
	public String getTxnObjectCode() {
		return txnObjectCode;
	}

	/**
	 * @param txnObjectCode
	 *            the txnObjectCode to set
	 */
	public void setTxnObjectCode(final String txnObjectCode) {
		this.txnObjectCode = txnObjectCode;
	}

	/**
	 * @return the txnObjectId
	 */
	public BigDecimal getTxnObjectId() {
		return txnObjectId;
	}

	/**
	 * @param txnObjectId
	 *            the txnObjectId to set
	 */
	public void setTxnObjectId(final BigDecimal txnObjectId) {
		this.txnObjectId = txnObjectId;
	}

	/**
	 * @return the txnPostUsage
	 */
	public String getTxnPostUsage() {
		return txnPostUsage;
	}

	/**
	 * @param txnPostUsage
	 *            the txnPostUsage to set
	 */
	public void setTxnPostUsage(final String txnPostUsage) {
		this.txnPostUsage = txnPostUsage;
	}

	/**
	 * @return the txnReferenceNumber
	 */
	public String getTxnReferenceNumber() {
		return txnReferenceNumber;
	}

	/**
	 * @param txnReferenceNumber
	 *            the txnReferenceNumber to set
	 */
	public void setTxnReferenceNumber(final String txnReferenceNumber) {
		this.txnReferenceNumber = txnReferenceNumber;
	}

	/**
	 * @return the txnReversedFlag
	 */
	public String getTxnReversedFlag() {
		return txnReversedFlag;
	}

	/**
	 * @param txnReversedFlag
	 *            the txnReversedFlag to set
	 */
	public void setTxnReversedFlag(final String txnReversedFlag) {
		this.txnReversedFlag = txnReversedFlag;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	/**
	 * @return the txnId
	 */
	public Long getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId
	 *            the txnId to set
	 */
	public void setTxnId(final Long txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the txnEntrySeq
	 */
	public Long getTxnEntrySeq() {
		return txnEntrySeq;
	}

	/**
	 * @param txnEntrySeq
	 *            the txnEntrySeq to set
	 */
	public void setTxnEntrySeq(final Long txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	/**
	 * @return the glEntrySeq
	 */
	public Long getGlEntrySeq() {
		return glEntrySeq;
	}

	/**
	 * @param glEntrySeq
	 *            the glEntrySeq to set
	 */
	public void setGlEntrySeq(final Long glEntrySeq) {
		this.glEntrySeq = glEntrySeq;
	}

	/**
	 * @return the txnReversedFlagOne
	 */
	public String getTxnReversedFlagOne() {
		return txnReversedFlagOne;
	}

	/**
	 * @param txnReversedFlagOne
	 *            the txnReversedFlagOne to set
	 */
	public void setTxnReversedFlagOne(final String txnReversedFlagOne) {
		this.txnReversedFlagOne = txnReversedFlagOne;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the txnEntryDescOne
	 */
	public String getTxnEntryDescOne() {
		return txnEntryDescOne;
	}

	/**
	 * @param txnEntryDescOne
	 *            the txnEntryDescOne to set
	 */
	public void setTxnEntryDescOne(final String txnEntryDescOne) {
		this.txnEntryDescOne = txnEntryDescOne;
	}

	/**
	 * @return the nextButton
	 */
	public String getNextButton() {
		return nextButton;
	}

	/**
	 * @param nextButton
	 *            the nextButton to set
	 */
	public void setNextButton(final String nextButton) {
		this.nextButton = nextButton;
	}

	/**
	 * @return the deductionType
	 */
	public String getDeductionType() {
		return deductionType;
	}

	/**
	 * @param deductionType
	 *            the deductionType to set
	 */
	public void setDeductionType(final String deductionType) {
		this.deductionType = deductionType;
	}

	/**
	 * @return the offTxnType
	 */
	public String getOffTxnType() {
		return offTxnType;
	}

	/**
	 * @param offTxnType
	 *            the offTxnType to set
	 */
	public void setOffTxnType(final String offTxnType) {
		this.offTxnType = offTxnType;
	}

	/**
	 * @return the offDeductionId
	 */
	public BigDecimal getOffDeductionId() {
		return offDeductionId;
	}

	/**
	 * @param offDeductionId
	 *            the offDeductionId to set
	 */
	public void setOffDeductionId(final BigDecimal offDeductionId) {
		this.offDeductionId = offDeductionId;
	}

	/**
	 * @return the dspTxnPostingType
	 */
	public String getDspTxnPostingType() {
		return dspTxnPostingType;
	}

	/**
	 * @param dspTxnPostingType
	 *            the dspTxnPostingType to set
	 */
	public void setDspTxnPostingType(final String dspTxnPostingType) {
		this.dspTxnPostingType = dspTxnPostingType;
	}

	/**
	 * @return the dspAccountName
	 */
	public String getDspAccountName() {
		return dspAccountName;
	}

	/**
	 * @param dspAccountName
	 *            the dspAccountName to set
	 */
	public void setDspAccountName(final String dspAccountName) {
		this.dspAccountName = dspAccountName;
	}

	/**
	 * @return the nbtBalance
	 */
	public BigDecimal getNbtBalance() {
		return nbtBalance;
	}

	/**
	 * @param nbtBalance
	 *            the nbtBalance to set
	 */
	public void setNbtBalance(final BigDecimal nbtBalance) {
		this.nbtBalance = nbtBalance;
	}

	/**
	 * @return the nbtTxnType
	 */
	public String getNbtTxnType() {
		return nbtTxnType;
	}

	/**
	 * @param nbtTxnType
	 *            the nbtTxnType to set
	 */
	public void setNbtTxnType(final String nbtTxnType) {
		this.nbtTxnType = nbtTxnType;
	}

	/**
	 * @return the nbtBalanceDisplay
	 */
	public String getNbtBalanceDisplay() {
		return nbtBalanceDisplay;
	}

	/**
	 * @param nbtBalanceDisplay
	 *            the nbtBalanceDisplay to set
	 */
	public void setNbtBalanceDisplay(final String nbtBalanceDisplay) {
		this.nbtBalanceDisplay = nbtBalanceDisplay;
	}

	/**
	 * @return the drAccountCode
	 */
	public BigDecimal getDrAccountCode() {
		return drAccountCode;
	}

	/**
	 * @param drAccountCode
	 *            the drAccountCode to set
	 */
	public void setDrAccountCode(final BigDecimal drAccountCode) {
		this.drAccountCode = drAccountCode;
	}

	/**
	 * @return the crAccountCode
	 */
	public BigDecimal getCrAccountCode() {
		return crAccountCode;
	}

	/**
	 * @param crAccountCode
	 *            the crAccountCode to set
	 */
	public void setCrAccountCode(final BigDecimal crAccountCode) {
		this.crAccountCode = crAccountCode;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the nbtOffenderId
	 */
	public BigDecimal getNbtOffenderId() {
		return nbtOffenderId;
	}

	/**
	 * @param nbtOffenderId
	 *            the nbtOffenderId to set
	 */
	public void setNbtOffenderId(final BigDecimal nbtOffenderId) {
		this.nbtOffenderId = nbtOffenderId;
	}

	/**
	 * @return the checkProduce
	 */
	public String getCheckProduce() {
		return checkProduce;
	}

	/**
	 * @param checkProduce
	 *            the checkProduce to set
	 */
	public void setCheckProduce(final String checkProduce) {
		this.checkProduce = checkProduce;
	}

	/**
	 * @return the txnPostUsageGrid
	 */
	public String getTxnPostUsageGrid() {
		return txnPostUsageGrid;
	}

	/**
	 * @param txnPostUsageGrid
	 *            the txnPostUsageGrid to set
	 */
	public void setTxnPostUsageGrid(final String txnPostUsageGrid) {
		this.txnPostUsageGrid = txnPostUsageGrid;
	}

	/**
	 * @return the cgnbtPayeeNameTextOne
	 */
	public BigDecimal getCgnbtPayeeNameTextOne() {
		return cgnbtPayeeNameTextOne;
	}

	/**
	 * @param cgnbtPayeeNameTextOne
	 *            the cgnbtPayeeNameTextOne to set
	 */
	public void setCgnbtPayeeNameTextOne(final BigDecimal cgnbtPayeeNameTextOne) {
		this.cgnbtPayeeNameTextOne = cgnbtPayeeNameTextOne;
	}

	/**
	 * @return the cgnbtPayeeNameTextTwo
	 */
	public BigDecimal getCgnbtPayeeNameTextTwo() {
		return cgnbtPayeeNameTextTwo;
	}

	/**
	 * @param cgnbtPayeeNameTextTwo
	 *            the cgnbtPayeeNameTextTwo to set
	 */
	public void setCgnbtPayeeNameTextTwo(final BigDecimal cgnbtPayeeNameTextTwo) {
		this.cgnbtPayeeNameTextTwo = cgnbtPayeeNameTextTwo;
	}

	/**
	 * @return the txnPostUsageCr
	 */
	public String getTxnPostUsageCr() {
		return txnPostUsageCr;
	}

	/**
	 * @param txnPostUsageCr the txnPostUsageCr to set
	 */
	public void setTxnPostUsageCr(final String txnPostUsageCr) {
		this.txnPostUsageCr = txnPostUsageCr;
	}

	public String getNbtOffenderIdDisplay() {
		return nbtOffenderIdDisplay;
	}

	public void setNbtOffenderIdDisplay(String nbtOffenderIdDisplay) {
		this.nbtOffenderIdDisplay = nbtOffenderIdDisplay;
	}

}