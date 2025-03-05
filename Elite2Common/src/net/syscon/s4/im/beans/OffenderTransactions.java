package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderTransactions extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("txnId")
	private Integer txnId;
	@JsonProperty("txnEntrySeq")
	private Integer txnEntrySeq;
	@JsonProperty("cgnbtTxnEntryDate")
	private String cgnbtTxnEntryDate;

	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("offenderId")
	private Long offenderId;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("txnPostingType")
	private String txnPostingType;
	@JsonProperty("txnType")
	private String txnType;
	@JsonProperty("txnEntryDesc")
	private String txnEntryDesc;
	@JsonProperty("txnEntryAmount")
	private Double txnEntryAmount;
	@JsonProperty("txnEntryAmountOne")
	private Double txnEntryAmountOne;
	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;
	@JsonProperty("subAccountType")
	private String subAccountType;
	@JsonProperty("txnReferenceNumber")
	private String txnReferenceNumber;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("receiptNumber")
	private String receiptNumber;
	@JsonProperty("slipPrintedFlag")
	private String slipPrintedFlag;
	@JsonProperty("transferCaseloadId")
	private String transferCaseloadId;
	@JsonProperty("receiptPrintedFlag")
	private String receiptPrintedFlag;
	@JsonProperty("preWithholdAmount")
	private Double preWithholdAmount;
	@JsonProperty("deductionFlag")
	private String deductionFlag;
	@JsonProperty("closingChequeNumber")
	private String closingChequeNumber;
	@JsonProperty("remitterName")
	private String remitterName;
	@JsonProperty("payeeCode")
	private String payeeCode;
	@JsonProperty("payeeNameText")
	private String payeeNameText;
	@JsonProperty("payeeCorporateId")
	private Integer payeeCorporateId;
	@JsonProperty("payeePersonId")
	private Integer payeePersonId;
	@JsonProperty("adjustTxnId")
	private Integer adjustTxnId;
	@JsonProperty("adjustTxnEntryId")
	private Integer adjustTxnEntryId;
	@JsonProperty("adjustOffenderId")
	private Integer adjustOffenderId;
	@JsonProperty("adjustAccountCode")
	private Integer adjustAccountCode;
	@JsonProperty("txnAdjustedFlag")
	private String txnAdjustedFlag;
	@JsonProperty("deductionType")
	private String deductionType;
	@JsonProperty("infoNumber")
	private String infoNumber;
	@JsonProperty("holdClearFlag")
	private String holdClearFlag;
	@JsonProperty("holdUntilDate")
	private Date holdUntilDate;
	@JsonProperty("holdNumber")
	private Integer holdNumber;
	@JsonProperty("grossAmount")
	private Integer grossAmount;
	@JsonProperty("grossNetFlag")
	private String grossNetFlag;
	@JsonProperty("remitterId")
	private Integer remitterId;
	@JsonProperty("applySpendingLimitAmount")
	private Integer applySpendingLimitAmount;
	@JsonProperty("receiptPendingPrintFlag")
	private String receiptPendingPrintFlag;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("orgTxnType")
	private String orgTxnType;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("payRollId")
	private Integer payRollId;
	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("payeeId")
	private Integer payeeId;

	@JsonProperty("payeeName")
	private String payeeName;

	@JsonProperty("currentBalance")
	private Double currentBalance;

	@JsonProperty("holdBalance")
	private Double holdBalance;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("totalPaid")
	private Double totalPaid;

	@JsonProperty("totalOwed")
	private Double totalOwed;

	@JsonProperty("txnUsage")
	private String txnUsage;

	@JsonProperty("chequePayeeType")
	private String chequePayeeType;

	@JsonProperty("chequeProductionFlag")
	private String chequeProductionFlag;

	@JsonProperty("receiptProductionFlag")
	private String receiptProductionFlag;

	@JsonProperty("totTxnFee")
	private Double totTxnFee;

	@JsonProperty("checkInd")
	private String checkInd;

	@JsonProperty("currentCaseLoad")
	private String currentCaseLoad;

	@JsonProperty("accountClosedFlag")
	private String accountClosedFlag;

	@JsonProperty("fmSubAccountType")
	private String fmSubAccountType;

	@JsonProperty("toSubAccountType")
	private String toSubAccountType;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("corporateName")
	private String corporateName;

	@JsonProperty("crAccountCode")
	private Integer crAccountCode;

	@JsonProperty("drAcountCode")
	private Integer drAcountCode;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("nbtOffenderId")
	private String nbtOffenderId;

	@JsonProperty("checkAmnt")
	private Double checkAmnt;

	@JsonProperty("txnHoldEntryAmount")
	private Double txnHoldEntryAmount;

	@JsonProperty("fromCaseloadId")
	private String fromCaseloadId;

	@JsonProperty("toCaseloadId")
	private String toCaseloadId;

	@JsonProperty("acntClosedFlag")
	private String acntClosedFlag;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("sta")
	private String sta;

	@JsonProperty("nbtTxnEntryAmount2")
	private Double nbtTxnEntryAmount2;

	@JsonProperty("nbtModifyUserId")
	private String nbtModifyUserId;

	@JsonProperty("profileVal")
	private String profileVal;

	@JsonProperty("txnExistFlg")
	private String txnExistFlg;

	@JsonProperty("vprevBncFlg")
	private String vprevBncFlg;

	@JsonProperty("glEntrySeq")
	private Long glEntrySeq;

	@JsonProperty("txnPostUsage")
	private String txnPostUsage;

	@JsonProperty("accountCode")
	private BigDecimal accountCode;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("rootOffenderId")
	private Long rootOffenderId;

	@JsonProperty("statusDisplay")
	private String statusDisplay;

	@JsonProperty("nbtOffenderIdDisplay")
	private String nbtOffenderIdDisplay;
	@JsonProperty("nbtNameText")
	private String nbtNameText;
	@JsonProperty("nbtReceiptPrintFlag")
	private String nbtReceiptPrintFlag;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("txnDescription")
	private String txnDescription;

	@JsonProperty("report")
	private byte[] report;

	@JsonProperty("sessionId")
	private Long sessionId;
	
	@JsonProperty("nbtOverpaymentAmount")
	private Double nbtOverpaymentAmount;

	@JsonProperty("comment")
	private String comment;

	@JsonProperty("feeCode")
	private String feeCode;
	
	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("beginDate")
	private String beginDate;
	
	@JsonProperty("toDate")
	private String toDate;
	
	@JsonProperty("poCaseload")
	private String poCaseload;
	
	@JsonProperty("upperLimit")
	private String upperLimit;
	
	@JsonProperty("lowerLimit")
	private String lowerLimit;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("inOutStatus")
	private String inOutStatus;
	
	private List<OffFeeBillTransactions> eventUpdateList;

	private List<OffFeeBillTransactions> offFeeBillList;
	 

	/**
	 * @return the setNbtOverpaymentAmount
	 */
	

	public Double getNbtOverpaymentAmount() {
		return nbtOverpaymentAmount;
	}

	public void setNbtOverpaymentAmount(Double nbtOverpaymentAmount) {
		this.nbtOverpaymentAmount = nbtOverpaymentAmount;
	}

	/**
	 * @return the nbtTxnEntryAmount2
	 */
	public Double getNbtTxnEntryAmount2() {
		return nbtTxnEntryAmount2;
	}

	/**
	 * @param nbtTxnEntryAmount2
	 *            the nbtTxnEntryAmount2 to set
	 */
	public void setNbtTxnEntryAmount2(final Double nbtTxnEntryAmount2) {
		this.nbtTxnEntryAmount2 = nbtTxnEntryAmount2;
	}

	/**
	 * @return the nbtModifyUserId
	 */
	public String getNbtModifyUserId() {
		return nbtModifyUserId;
	}

	/**
	 * @param nbtModifyUserId
	 *            the nbtModifyUserId to set
	 */
	public void setNbtModifyUserId(final String nbtModifyUserId) {
		this.nbtModifyUserId = nbtModifyUserId;
	}

	/**
	 * @return the payRollId
	 */
	public Integer getPayRollId() {
		return payRollId;
	}

	/**
	 * @param payRollId
	 *            the payRollId to set
	 */
	public void setPayRollId(final Integer payRollId) {
		this.payRollId = payRollId;
	}

	/**
	 * @return the bookingNo
	 */
	public String getBookingNo() {
		return bookingNo;
	}

	/**
	 * @param bookingNo
	 *            the bookingNo to set
	 */
	public void setBookingNo(final String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 * Creates new OffenderTransactions class Object
	 */
	public OffenderTransactions() {
		// OffenderTransactions
	}

	/**
	 * @return the txnId
	 */
	public Integer getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId
	 *            the txnId to set
	 */
	public void setTxnId(final Integer txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the txnEntrySeq
	 */
	public Integer getTxnEntrySeq() {
		return txnEntrySeq;
	}

	/**
	 * @param txnEntrySeq
	 *            the txnEntrySeq to set
	 */
	public void setTxnEntrySeq(final Integer txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
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
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the txnPostingType
	 */
	public String getTxnPostingType() {
		return txnPostingType;
	}

	/**
	 * @param txnPostingType
	 *            the txnPostingType to set
	 */
	public void setTxnPostingType(final String txnPostingType) {
		this.txnPostingType = txnPostingType;
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
	 * @return the txnEntryAmount
	 */
	public Double getTxnEntryAmount() {
		return txnEntryAmount;
	}

	/**
	 * @param txnEntryAmount
	 *            the txnEntryAmount to set
	 */
	public void setTxnEntryAmount(final Double txnEntryAmount) {
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
	 * @return the subAccountType
	 */
	public String getSubAccountType() {
		return subAccountType;
	}

	/**
	 * @param subAccountType
	 *            the subAccountType to set
	 */
	public void setSubAccountType(final String subAccountType) {
		this.subAccountType = subAccountType;
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
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
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
	 * @return the slipPrintedFlag
	 */
	public String getSlipPrintedFlag() {
		return slipPrintedFlag;
	}

	/**
	 * @param slipPrintedFlag
	 *            the slipPrintedFlag to set
	 */
	public void setSlipPrintedFlag(final String slipPrintedFlag) {
		this.slipPrintedFlag = slipPrintedFlag;
	}

	/**
	 * @return the transferCaseloadId
	 */
	public String getTransferCaseloadId() {
		return transferCaseloadId;
	}

	/**
	 * @param transferCaseloadId
	 *            the transferCaseloadId to set
	 */
	public void setTransferCaseloadId(final String transferCaseloadId) {
		this.transferCaseloadId = transferCaseloadId;
	}

	/**
	 * @return the receiptPrintedFlag
	 */
	public String getReceiptPrintedFlag() {
		return receiptPrintedFlag;
	}

	/**
	 * @param receiptPrintedFlag
	 *            the receiptPrintedFlag to set
	 */
	public void setReceiptPrintedFlag(final String receiptPrintedFlag) {
		this.receiptPrintedFlag = receiptPrintedFlag;
	}

	/**
	 * @return the preWithholdAmount
	 */
	public Double getPreWithholdAmount() {
		return preWithholdAmount;
	}

	/**
	 * @param preWithholdAmount
	 *            the preWithholdAmount to set
	 */
	public void setPreWithholdAmount(final Double preWithholdAmount) {
		this.preWithholdAmount = preWithholdAmount;
	}

	/**
	 * @return the deductionFlag
	 */
	public String getDeductionFlag() {
		return deductionFlag;
	}

	/**
	 * @param deductionFlag
	 *            the deductionFlag to set
	 */
	public void setDeductionFlag(final String deductionFlag) {
		this.deductionFlag = deductionFlag;
	}

	/**
	 * @return the closingChequeNumber
	 */
	public String getClosingChequeNumber() {
		return closingChequeNumber;
	}

	/**
	 * @param closingChequeNumber
	 *            the closingChequeNumber to set
	 */
	public void setClosingChequeNumber(final String closingChequeNumber) {
		this.closingChequeNumber = closingChequeNumber;
	}

	/**
	 * @return the remitterName
	 */
	public String getRemitterName() {
		return remitterName;
	}

	/**
	 * @param remitterName
	 *            the remitterName to set
	 */
	public void setRemitterName(final String remitterName) {
		this.remitterName = remitterName;
	}

	/**
	 * @return the payeeCode
	 */
	public String getPayeeCode() {
		return payeeCode;
	}

	/**
	 * @param payeeCode
	 *            the payeeCode to set
	 */
	public void setPayeeCode(final String payeeCode) {
		this.payeeCode = payeeCode;
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
	 * @return the payeeCorporateId
	 */
	public Integer getPayeeCorporateId() {
		return payeeCorporateId;
	}

	/**
	 * @param payeeCorporateId
	 *            the payeeCorporateId to set
	 */
	public void setPayeeCorporateId(final Integer payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	/**
	 * @return the payeePrisonId
	 */
	public Integer getPayeePersonId() {
		return payeePersonId;
	}

	/**
	 * @param payeePrisonId
	 *            the payeePrisonId to set
	 */
	public void setPayeePersonId(final Integer payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	/**
	 * @return the adjustTxnId
	 */
	public Integer getAdjustTxnId() {
		return adjustTxnId;
	}

	/**
	 * @param adjustTxnId
	 *            the adjustTxnId to set
	 */
	public void setAdjustTxnId(final Integer adjustTxnId) {
		this.adjustTxnId = adjustTxnId;
	}

	/**
	 * @return the adjustTxnEntryId
	 */
	public Integer getAdjustTxnEntryId() {
		return adjustTxnEntryId;
	}

	/**
	 * @param adjustTxnEntryId
	 *            the adjustTxnEntryId to set
	 */
	public void setAdjustTxnEntryId(final Integer adjustTxnEntryId) {
		this.adjustTxnEntryId = adjustTxnEntryId;
	}

	/**
	 * @return the adjustOffenderId
	 */
	public Integer getAdjustOffenderId() {
		return adjustOffenderId;
	}

	/**
	 * @param adjustOffenderId
	 *            the adjustOffenderId to set
	 */
	public void setAdjustOffenderId(final Integer adjustOffenderId) {
		this.adjustOffenderId = adjustOffenderId;
	}

	/**
	 * @return the adjustAccountCode
	 */
	public Integer getAdjustAccountCode() {
		return adjustAccountCode;
	}

	/**
	 * @param adjustAccountCode
	 *            the adjustAccountCode to set
	 */
	public void setAdjustAccountCode(final Integer adjustAccountCode) {
		this.adjustAccountCode = adjustAccountCode;
	}

	/**
	 * @return the txnAdjustedFlag
	 */
	public String getTxnAdjustedFlag() {
		return txnAdjustedFlag;
	}

	/**
	 * @param txnAdjustedFlag
	 *            the txnAdjustedFlag to set
	 */
	public void setTxnAdjustedFlag(final String txnAdjustedFlag) {
		this.txnAdjustedFlag = txnAdjustedFlag;
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
	 * @return the holdClearFlag
	 */
	public String getHoldClearFlag() {
		return holdClearFlag;
	}

	/**
	 * @param holdClearFlag
	 *            the holdClearFlag to set
	 */
	public void setHoldClearFlag(final String holdClearFlag) {
		this.holdClearFlag = holdClearFlag;
	}

	/**
	 * @return the holdUntilDate
	 */
	public Date getHoldUntilDate() {
		return holdUntilDate;
	}

	/**
	 * @param holdUntilDate
	 *            the holdUntilDate to set
	 */
	public void setHoldUntilDate(final Date holdUntilDate) {
		this.holdUntilDate = holdUntilDate;
	}

	/**
	 * @return the holdNumber
	 */
	public Integer getHoldNumber() {
		return holdNumber;
	}

	/**
	 * @param holdNumber
	 *            the holdNumber to set
	 */
	public void setHoldNumber(final Integer holdNumber) {
		this.holdNumber = holdNumber;
	}

	/**
	 * @return the grossAmount
	 */
	public Integer getGrossAmount() {
		return grossAmount;
	}

	/**
	 * @param grossAmount
	 *            the grossAmount to set
	 */
	public void setGrossAmount(final Integer grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * @return the grossNetFlag
	 */
	public String getGrossNetFlag() {
		return grossNetFlag;
	}

	/**
	 * @param grossNetFlag
	 *            the grossNetFlag to set
	 */
	public void setGrossNetFlag(final String grossNetFlag) {
		this.grossNetFlag = grossNetFlag;
	}

	/**
	 * @return the remitterId
	 */
	public Integer getRemitterId() {
		return remitterId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public String getTxnDescription() {
		return txnDescription;
	}

	public void setTxnDescription(final String txnDescription) {
		this.txnDescription = txnDescription;
	}

	/**
	 * @param remitterId
	 *            the remitterId to set
	 */
	public void setRemitterId(final Integer remitterId) {
		this.remitterId = remitterId;
	}

	/**
	 * @return the applySpendingLimitAmount
	 */
	public Integer getApplySpendingLimitAmount() {
		return applySpendingLimitAmount;
	}

	/**
	 * @param applySpendingLimitAmount
	 *            the applySpendingLimitAmount to set
	 */
	public void setApplySpendingLimitAmount(final Integer applySpendingLimitAmount) {
		this.applySpendingLimitAmount = applySpendingLimitAmount;
	}

	/**
	 * @return the receiptPendingPrintFlag
	 */
	public String getReceiptPendingPrintFlag() {
		return receiptPendingPrintFlag;
	}

	/**
	 * @param receiptPendingPrintFlag
	 *            the receiptPendingPrintFlag to set
	 */
	public void setReceiptPendingPrintFlag(final String receiptPendingPrintFlag) {
		this.receiptPendingPrintFlag = receiptPendingPrintFlag;
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
	 * @return the orgTxnType
	 */
	public String getOrgTxnType() {
		return orgTxnType;
	}

	/**
	 * @param orgTxnType
	 *            the orgTxnType to set
	 */
	public void setOrgTxnType(final String orgTxnType) {
		this.orgTxnType = orgTxnType;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDatetime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDatetime = createDateTime;
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
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDatetime = modifyDateTime;
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
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the payeeId
	 */
	public Integer getPayeeId() {
		return payeeId;
	}

	/**
	 * @param payeeId
	 *            the payeeId to set
	 */
	public void setPayeeId(final Integer payeeId) {
		this.payeeId = payeeId;
	}

	/**
	 * @return the payeeName
	 */
	public String getPayeeName() {
		return payeeName;
	}

	/**
	 * @param payeeName
	 *            the payeeName to set
	 */
	public void setPayeeName(final String payeeName) {
		this.payeeName = payeeName;
	}

	/**
	 * @return the currentBalance
	 */
	public Double getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * @param currentBalance
	 *            the currentBalance to set
	 */
	public void setCurrentBalance(final Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**
	 * @return the holdBalance
	 */
	public Double getHoldBalance() {
		return holdBalance;
	}

	/**
	 * @param holdBalance
	 *            the holdBalance to set
	 */
	public void setHoldBalance(final Double holdBalance) {
		this.holdBalance = holdBalance;
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
	 * @return the totalPaid
	 */
	public Double getTotalPaid() {
		return totalPaid;
	}

	/**
	 * @param totalPaid
	 *            the totalPaid to set
	 */
	public void setTotalPaid(final Double totalPaid) {
		this.totalPaid = totalPaid;
	}

	/**
	 * @return the totalOwed
	 */
	public Double getTotalOwed() {
		return totalOwed;
	}

	/**
	 * @param totalOwed
	 *            the totalOwed to set
	 */
	public void setTotalOwed(final Double totalOwed) {
		this.totalOwed = totalOwed;
	}

	/**
	 * @return the txnUsage
	 */
	public String getTxnUsage() {
		return txnUsage;
	}

	/**
	 * @param txnUsage
	 *            the txnUsage to set
	 */
	public void setTxnUsage(final String txnUsage) {
		this.txnUsage = txnUsage;
	}

	/**
	 * @return the chequePayeeType
	 */
	public String getChequePayeeType() {
		return chequePayeeType;
	}

	/**
	 * @param chequePayeeType
	 *            the chequePayeeType to set
	 */
	public void setChequePayeeType(final String chequePayeeType) {
		this.chequePayeeType = chequePayeeType;
	}

	/**
	 * @return the chequeProductionFlag
	 */
	public String getChequeProductionFlag() {
		return chequeProductionFlag;
	}

	/**
	 * @param chequeProductionFlag
	 *            the chequeProductionFlag to set
	 */
	public void setChequeProductionFlag(final String chequeProductionFlag) {
		this.chequeProductionFlag = chequeProductionFlag;
	}

	/**
	 * @return the receiptProductionFlag
	 */
	public String getReceiptProductionFlag() {
		return receiptProductionFlag;
	}

	/**
	 * @param receiptProductionFlag
	 *            the receiptProductionFlag to set
	 */
	public void setReceiptProductionFlag(final String receiptProductionFlag) {
		this.receiptProductionFlag = receiptProductionFlag;
	}

	/**
	 * @return the totTxnFee
	 */
	public Double getTotTxnFee() {
		return totTxnFee;
	}

	/**
	 * @param totTxnFee
	 *            the totTxnFee to set
	 */
	public void setTotTxnFee(final Double totTxnFee) {
		this.totTxnFee = totTxnFee;
	}

	/**
	 * @return the checkInd
	 */
	public String getCheckInd() {
		return checkInd;
	}

	/**
	 * @param checkInd
	 *            the checkInd to set
	 */
	public void setCheckInd(final String checkInd) {
		this.checkInd = checkInd;
	}

	/**
	 * @return the currentCaseLoad
	 */
	public String getCurrentCaseLoad() {
		return currentCaseLoad;
	}

	/**
	 * @param currentCaseLoad
	 *            the currentCaseLoad to set
	 */
	public void setCurrentCaseLoad(final String currentCaseLoad) {
		this.currentCaseLoad = currentCaseLoad;
	}

	/**
	 * @return the accountClosedFlag
	 */
	public String getAccountClosedFlag() {
		return accountClosedFlag;
	}

	/**
	 * @param accountClosedFlag
	 *            the accountClosedFlag to set
	 */
	public void setAccountClosedFlag(final String accountClosedFlag) {
		this.accountClosedFlag = accountClosedFlag;
	}

	/**
	 * @return the fromSubAccountType
	 */

	/**
	 * @return the toSubAccountType
	 */
	public String getToSubAccountType() {
		return toSubAccountType;
	}

	/**
	 * @param toSubAccountType
	 *            the toSubAccountType to set
	 */
	public void setToSubAccountType(final String toSubAccountType) {
		this.toSubAccountType = toSubAccountType;
	}

	/**
	 * @return the fmSubAccountType
	 */
	public String getFmSubAccountType() {
		return fmSubAccountType;
	}

	/**
	 * @param fmSubAccountType
	 *            the fmSubAccountType to set
	 */
	public void setFmSubAccountType(final String fmSubAccountType) {
		this.fmSubAccountType = fmSubAccountType;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the corporateName
	 */
	public String getCorporateName() {
		return corporateName;
	}

	/**
	 * @param corporateName
	 *            the corporateName to set
	 */
	public void setCorporateName(final String corporateName) {
		this.corporateName = corporateName;
	}

	/**
	 * @return the crAccountCode
	 */
	public Integer getCrAccountCode() {
		return crAccountCode;
	}

	/**
	 * @param crAccountCode
	 *            the crAccountCode to set
	 */
	public void setCrAccountCode(final Integer crAccountCode) {
		this.crAccountCode = crAccountCode;
	}

	/**
	 * @return the drAcountCode
	 */
	public Integer getDrAcountCode() {
		return drAcountCode;
	}

	/**
	 * @param drAcountCode
	 *            the drAcountCode to set
	 */
	public void setDrAcountCode(final Integer drAcountCode) {
		this.drAcountCode = drAcountCode;
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
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the nbtOffenderId
	 */
	public String getNbtOffenderId() {
		return nbtOffenderId;
	}

	/**
	 * @param nbtOffenderId
	 *            the nbtOffenderId to set
	 */
	public void setNbtOffenderId(final String nbtOffenderId) {
		this.nbtOffenderId = nbtOffenderId;
	}

	/**
	 * @return the checkAmnt
	 */
	public Double getCheckAmnt() {
		return checkAmnt;
	}

	/**
	 * @param checkAmnt
	 *            the checkAmnt to set
	 */
	public void setCheckAmnt(final Double checkAmnt) {
		this.checkAmnt = checkAmnt;
	}

	/**
	 * @return the txnHoldEntryAmount
	 */
	public Double getTxnHoldEntryAmount() {
		return txnHoldEntryAmount;
	}

	/**
	 * @param txnHoldEntryAmount
	 *            the txnHoldEntryAmount to set
	 */
	public void setTxnHoldEntryAmount(final Double txnHoldEntryAmount) {
		this.txnHoldEntryAmount = txnHoldEntryAmount;
	}

	/**
	 * @return the fromCaseloadId
	 */
	public String getFromCaseloadId() {
		return fromCaseloadId;
	}

	/**
	 * @param fromCaseloadId
	 *            the fromCaseloadId to set
	 */
	public void setFromCaseloadId(final String fromCaseloadId) {
		this.fromCaseloadId = fromCaseloadId;
	}

	/**
	 * @return the toCaseloadId
	 */
	public String getToCaseloadId() {
		return toCaseloadId;
	}

	/**
	 * @param toCaseloadId
	 *            the toCaseloadId to set
	 */
	public void setToCaseloadId(final String toCaseloadId) {
		this.toCaseloadId = toCaseloadId;
	}

	/**
	 * @return the acntClosedFlag
	 */
	public String getAcntClosedFlag() {
		return acntClosedFlag;
	}

	/**
	 * @param acntClosedFlag
	 *            the acntClosedFlag to set
	 */
	public void setAcntClosedFlag(final String acntClosedFlag) {
		this.acntClosedFlag = acntClosedFlag;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the sta
	 */
	public String getSta() {
		return sta;
	}

	/**
	 * @param sta
	 *            the sta to set
	 */
	public void setSta(final String sta) {
		this.sta = sta;
	}

	/**
	 * @return the profileVal
	 */
	public String getProfileVal() {
		return profileVal;
	}

	/**
	 * @param profileVal
	 *            the profileVal to set
	 */
	public void setProfileVal(final String profileVal) {
		this.profileVal = profileVal;
	}

	/**
	 * @return the txnExistFlg
	 */
	public String getTxnExistFlg() {
		return txnExistFlg;
	}

	/**
	 * @param txnExistFlg
	 *            the txnExistFlg to set
	 */
	public void setTxnExistFlg(final String txnExistFlg) {
		this.txnExistFlg = txnExistFlg;
	}

	/**
	 * @return the vprevBncFlg
	 */
	public String getVprevBncFlg() {
		return vprevBncFlg;
	}

	/**
	 * @param vprevBncFlg
	 *            the vprevBncFlg to set
	 */
	public void setVprevBncFlg(final String vprevBncFlg) {
		this.vprevBncFlg = vprevBncFlg;
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
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
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
	 * @return the statusDispaly
	 */
	public String getStatusDisplay() {
		return statusDisplay;
	}

	/**
	 * @param statusDispaly
	 *            the statusDispaly to set
	 */
	public void setStatusDisplay(final String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	/**
	 * @return the txnEntryAmountOne
	 */
	public Double getTxnEntryAmountOne() {
		return txnEntryAmountOne;
	}

	/**
	 * @param txnEntryAmountOne
	 *            the txnEntryAmountOne to set
	 */
	public void setTxnEntryAmountOne(final Double txnEntryAmountOne) {
		this.txnEntryAmountOne = txnEntryAmountOne;
	}

	/**
	 * @return the nbtOffenderIdDisplay
	 */
	public String getNbtOffenderIdDisplay() {
		return nbtOffenderIdDisplay;
	}

	/**
	 * @param nbtOffenderIdDisplay
	 *            the nbtOffenderIdDisplay to set
	 */
	public void setNbtOffenderIdDisplay(final String nbtOffenderIdDisplay) {
		this.nbtOffenderIdDisplay = nbtOffenderIdDisplay;
	}

	/**
	 * @return the nbtNameText
	 */
	public String getNbtNameText() {
		return nbtNameText;
	}

	/**
	 * @param nbtNameText
	 *            the nbtNameText to set
	 */
	public void setNbtNameText(final String nbtNameText) {
		this.nbtNameText = nbtNameText;
	}

	/**
	 * @return the nbtReceiptPrintFlag
	 */
	public String getNbtReceiptPrintFlag() {
		return nbtReceiptPrintFlag;
	}

	/**
	 * @param nbtReceiptPrintFlag
	 *            the nbtReceiptPrintFlag to set
	 */
	public void setNbtReceiptPrintFlag(final String nbtReceiptPrintFlag) {
		this.nbtReceiptPrintFlag = nbtReceiptPrintFlag;
	}

	public String getCgnbtTxnEntryDate() {
		return cgnbtTxnEntryDate;
	}

	public void setCgnbtTxnEntryDate(final String cgnbtTxnEntryDate) {
		this.cgnbtTxnEntryDate = cgnbtTxnEntryDate;
	}

	/**
	 * @return the report
	 */
	public byte[] getReport() {
		return report;
	}

	/**
	 * @param report
	 *            the report to set
	 */
	public void setReport(final byte[] report) {
		this.report = report;
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
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(final Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<OffFeeBillTransactions> getEventUpdateList() {
		return eventUpdateList;
	}

	public void setEventUpdateList(List<OffFeeBillTransactions> eventUpdateList) {
		this.eventUpdateList = eventUpdateList;
	}

	public List<OffFeeBillTransactions> getOffFeeBillList() {
		return offFeeBillList;
	}

	public void setOffFeeBillList(List<OffFeeBillTransactions> offFeeBillList) {
		this.offFeeBillList = offFeeBillList;
	}

	@Override
	public String toString() {
		return "OffenderTransactions [txnId=" + txnId + ", txnEntrySeq=" + txnEntrySeq + ", cgnbtTxnEntryDate="
				+ cgnbtTxnEntryDate + ", caseloadId=" + caseloadId + ", offenderId=" + offenderId + ", offenderBookId="
				+ offenderBookId + ", txnPostingType=" + txnPostingType + ", txnType=" + txnType + ", txnEntryDesc="
				+ txnEntryDesc + ", txnEntryAmount=" + txnEntryAmount + ", txnEntryAmountOne=" + txnEntryAmountOne
				+ ", txnEntryDate=" + txnEntryDate + ", subAccountType=" + subAccountType + ", txnReferenceNumber="
				+ txnReferenceNumber + ", modifyDate=" + modifyDate + ", receiptNumber=" + receiptNumber
				+ ", slipPrintedFlag=" + slipPrintedFlag + ", transferCaseloadId=" + transferCaseloadId
				+ ", receiptPrintedFlag=" + receiptPrintedFlag + ", preWithholdAmount=" + preWithholdAmount
				+ ", deductionFlag=" + deductionFlag + ", closingChequeNumber=" + closingChequeNumber
				+ ", remitterName=" + remitterName + ", payeeCode=" + payeeCode + ", payeeNameText=" + payeeNameText
				+ ", payeeCorporateId=" + payeeCorporateId + ", payeePersonId=" + payeePersonId + ", adjustTxnId="
				+ adjustTxnId + ", adjustTxnEntryId=" + adjustTxnEntryId + ", adjustOffenderId=" + adjustOffenderId
				+ ", adjustAccountCode=" + adjustAccountCode + ", txnAdjustedFlag=" + txnAdjustedFlag
				+ ", deductionType=" + deductionType + ", infoNumber=" + infoNumber + ", holdClearFlag=" + holdClearFlag
				+ ", holdUntilDate=" + holdUntilDate + ", holdNumber=" + holdNumber + ", grossAmount=" + grossAmount
				+ ", grossNetFlag=" + grossNetFlag + ", remitterId=" + remitterId + ", applySpendingLimitAmount="
				+ applySpendingLimitAmount + ", receiptPendingPrintFlag=" + receiptPendingPrintFlag + ", sealFlag="
				+ sealFlag + ", orgTxnType=" + orgTxnType + ", createDatetime=" + createDatetime + ", createUserId="
				+ createUserId + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId + ", inserted="
				+ inserted + ", errorMessage=" + errorMessage + ", payRollId=" + payRollId + ", bookingNo=" + bookingNo
				+ ", payeeId=" + payeeId + ", payeeName=" + payeeName + ", currentBalance=" + currentBalance
				+ ", holdBalance=" + holdBalance + ", caseloadType=" + caseloadType + ", totalPaid=" + totalPaid
				+ ", totalOwed=" + totalOwed + ", txnUsage=" + txnUsage + ", chequePayeeType=" + chequePayeeType
				+ ", chequeProductionFlag=" + chequeProductionFlag + ", receiptProductionFlag=" + receiptProductionFlag
				+ ", totTxnFee=" + totTxnFee + ", checkInd=" + checkInd + ", currentCaseLoad=" + currentCaseLoad
				+ ", accountClosedFlag=" + accountClosedFlag + ", fmSubAccountType=" + fmSubAccountType
				+ ", toSubAccountType=" + toSubAccountType + ", moduleName=" + moduleName + ", corporateName="
				+ corporateName + ", crAccountCode=" + crAccountCode + ", drAcountCode=" + drAcountCode
				+ ", offenderIdDisplay=" + offenderIdDisplay + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", nbtOffenderId=" + nbtOffenderId + ", checkAmnt=" + checkAmnt + ", txnHoldEntryAmount="
				+ txnHoldEntryAmount + ", fromCaseloadId=" + fromCaseloadId + ", toCaseloadId=" + toCaseloadId
				+ ", acntClosedFlag=" + acntClosedFlag + ", activeFlag=" + activeFlag + ", sta=" + sta
				+ ", nbtTxnEntryAmount2=" + nbtTxnEntryAmount2 + ", nbtModifyUserId=" + nbtModifyUserId
				+ ", profileVal=" + profileVal + ", txnExistFlg=" + txnExistFlg + ", vprevBncFlg=" + vprevBncFlg
				+ ", glEntrySeq=" + glEntrySeq + ", txnPostUsage=" + txnPostUsage + ", accountCode=" + accountCode
				+ ", agyLocId=" + agyLocId + ", rootOffenderId=" + rootOffenderId + ", statusDisplay=" + statusDisplay
				+ ", nbtOffenderIdDisplay=" + nbtOffenderIdDisplay + ", nbtNameText=" + nbtNameText
				+ ", nbtReceiptPrintFlag=" + nbtReceiptPrintFlag + ", amount=" + amount + ", txnDescription="
				+ txnDescription + ", report=" + Arrays.toString(report) + ", sessionId=" + sessionId
				+ ", nbtOverpaymentAmount=" + nbtOverpaymentAmount + ", comment=" + comment + ", feeCode=" + feeCode
				+ ", eventUpdateList=" + eventUpdateList + ", offFeeBillList=" + offFeeBillList + "]";
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getPoCaseload() {
		return poCaseload;
	}

	public void setPoCaseload(String poCaseload) {
		this.poCaseload = poCaseload;
	}

	public String getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInOutStatus() {
		return inOutStatus;
	}

	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	

	
}
