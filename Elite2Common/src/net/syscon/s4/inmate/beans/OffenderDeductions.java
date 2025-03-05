package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderTransactions;

public class OffenderDeductions extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;

	@JsonProperty("adjustmentAmount")
	private BigDecimal adjustmentAmount;

	@JsonProperty("adjustmentReasonCode")
	private String adjustmentReasonCode;

	@JsonProperty("adjustmentText")
	private String adjustmentText;

	@JsonProperty("adjustmentTxnId")
	private BigDecimal adjustmentTxnId;

	@JsonProperty("adjustmentUserId")
	private String adjustmentUserId;

	@JsonProperty("caseId")
	private BigDecimal caseId;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("collectAgencyAmount")
	private BigDecimal collectAgencyAmount;

	@JsonProperty("collectAgencyFlag")
	private String collectAgencyFlag;

	@JsonProperty("collectSentDate")
	private Date collectSentDate;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creditLimit")
	private BigDecimal creditLimit;

	@JsonProperty("deductionAmount")
	private BigDecimal deductionAmount;

	@JsonProperty("deductionPercentage")
	private BigDecimal deductionPercentage;

	@JsonProperty("deductionPriority")
	private BigDecimal deductionPriority;

	@JsonProperty("deductionStatus")
	private String deductionStatus;

	@JsonProperty("deductionType")
	private String deductionType;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("fifoFlag")
	private String fifoFlag;

	@JsonProperty("groupId")
	private BigDecimal groupId;

	@JsonProperty("informationNumber")
	private String informationNumber;

	@JsonProperty("jsStatus")
	private String jsStatus;

	@JsonProperty("maxMonthlyAmount")
	private BigDecimal maxMonthlyAmount;

	@JsonProperty("maxRecursiveAmount")
	private BigDecimal maxRecursiveAmount;

	@JsonProperty("maxTotalAmount")
	private BigDecimal maxTotalAmount;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("offenderPaymentProfileId")
	private BigDecimal offenderPaymentProfileId;

	@JsonProperty("payDeductionFlag")
	private String payDeductionFlag;

	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;

	@JsonProperty("payeePersonId")
	private BigDecimal payeePersonId;

	@JsonProperty("processPriorityNumber")
	private BigDecimal processPriorityNumber;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("deductionDesc")
	private String deductionDesc;

	@JsonProperty("fixedFlag")
	private String fixedFlag;

	@JsonProperty("mthFlag")
	private String mthFlag;

	@JsonProperty("actFlag")
	private String actFlag;

	@JsonProperty("indigentFlag")
	private String indigentFlag;

	@JsonProperty("accountClosedFlag")
	private String accountClosedFlag;

	@JsonProperty("processPriorityNumb")
	private BigDecimal processPriorityNumb;

	@JsonProperty("parentDeductionId")
	private BigDecimal parentDeductionId;

	@JsonProperty("totPaid")
	private Double totPaid;

	@JsonProperty("totOwing")
	private Double totOwing;

	@JsonProperty("offTransList")
	private List<OffenderTransactions> offTransList;

	@JsonProperty("acntClosedFlag")
	private String acntClosedFlag;

	@JsonProperty("cgnbtMaxMonthlyAmount")
	private String cgnbtMaxMonthlyAmount;

	@JsonProperty("uniqueobligationprofile")
	private String uniqueobligationprofile;

	@JsonProperty("cgnbtAdjustmentStatus")
	private String cgnbtAdjustmentStatus;

	@JsonProperty("vsDamtCurVal")
	private Integer vsDamtCurVal;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("profilePayplnFlg")
	private String profilePayplnFlg;

	@JsonProperty("percentageOfParent")
	private String percentageOfParent;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("personId")
	private Integer personId;

	@JsonProperty("corporateId")
	private Integer corporateId;

	@JsonProperty("priority")
	private Integer priority;

	@JsonProperty("vUnlimDed")
	private String vUnlimDed;

	@JsonProperty("vrectifiedTotal")
	private BigDecimal vrectifiedTotal;

	@JsonProperty("percent")
	private BigDecimal percent;

	@JsonProperty("deductionCategory")
	private String deductionCategory;

	@JsonProperty("nbtAdjustmentReasonCode")
	private String nbtAdjustmentReasonCode;

	@JsonProperty("nbtWriteOffAmount")
	private BigDecimal nbtWriteOffAmount;

	@JsonProperty("nbtDeductionAmount")
	private String nbtDeductionAmount;

	@JsonProperty("unlimited")
	private String unlimited;

	@JsonProperty("deductionStatusDesc")
	private String deductionStatusDesc;

	
	@JsonProperty("amountStatus")
	private String amountStatus;

	@JsonProperty("parentDeductionType")
	private DeductionTypes parentDeductionType;

	@JsonProperty("offenderDeductionReceipts")
	private List<OffenderDeductionReceipts> offenderDeductionReceipts;

	@JsonProperty("offenderBeneficiaries")
	private List<OffenderBeneficiaries> offenderBeneficiaries;

	@JsonProperty("transactionDetails")
	private OffenderTransactions transactionDetails;

	@JsonProperty("dedFlag")
	private String dedFlag;

	@JsonProperty("tbdFlag")
	private String tbdFlag;

	@JsonProperty("orgAmount")
	private BigDecimal orgAmount;

	@JsonProperty("totCollected")
	private BigDecimal totCollected;

	@JsonProperty("totalOwing")
	private BigDecimal totalOwing;
	@JsonProperty("txnType")
	private String txnType;
	
	@JsonProperty("txnDescription")
	private String txnDescription;
	
   private String description;
	private String code;
	
	@JsonProperty("receiptDeductionType")	
	private String receiptDeductionType;
	
	@JsonProperty("oldMaxTotalAmount")
	private BigDecimal oldMaxTotalAmount;
	
	@JsonProperty("deductionSeq")
	private BigDecimal deductionSeq;
	
	@JsonProperty("externalPriorityNo")
	private Integer externalPriorityNo;
	
	@JsonProperty("internalPriorityNo")
	private Integer internalPriorityNo;
	
	@JsonProperty("flatRate")
	private BigDecimal flatRate;
	
	@JsonProperty("monthlyDeductionDate")
	private Date monthlyDeductionDate;
	
	@JsonProperty("monthlyDeductionAmount")
	private BigDecimal monthlyDeductionAmount;
	
	@JsonProperty("deductionTxnEntry")
	private BigDecimal deductionTxnEntry;
	
	@JsonProperty("accountCode")
	private Integer accountCode;
	
	@JsonProperty("txnPostingType")
	private String txnPostingType;
	
	@JsonProperty("confirmFlag")
	private String confirmFlag;

	@JsonProperty("fromBalanceType")
	private String fromBalanceType;
	
	@JsonProperty("unlimitedDeduction")
	private String unlimitedDeduction;
	
	@JsonProperty("receiptPercentage")
	private BigDecimal receiptPercentage;
	
	@JsonProperty("nmbrPrcnt")
	private BigDecimal nmbrPrcnt;
	
	private Date startDate;

	private String  paymentMode;
	
	private String postingStatus;
	
	
	public String getPostingStatus() {
		return postingStatus;
	}



	public void setPostingStatus(String postingStatus) {
		this.postingStatus = postingStatus;
	}



	public String getPaymentMode() {
		return paymentMode;
	}



	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public String getReceiptDeductionType() {
		return receiptDeductionType;
	}



	public void setReceiptDeductionType(String receiptDeductionType) {
		this.receiptDeductionType = receiptDeductionType;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}



	

	/**
	 * @return the parentDeductionType
	 */
	public DeductionTypes getParentDeductionType() {
		return parentDeductionType;
	}

	/**
	 * @param parentDeductionType
	 *            the parentDeductionType to set
	 */
	public void setParentDeductionType(final DeductionTypes parentDeductionType) {
		this.parentDeductionType = parentDeductionType;
	}

	public OffenderDeductions() {
		// OffenderDeductions
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

	public Long getOffenderDeductionId() {
		return this.offenderDeductionId;
	}

	public void setOffenderDeductionId(final Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	public BigDecimal getAdjustmentAmount() {
		return this.adjustmentAmount;
	}

	public void setAdjustmentAmount(final BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
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

	public BigDecimal getAdjustmentTxnId() {
		return this.adjustmentTxnId;
	}

	public void setAdjustmentTxnId(final BigDecimal adjustmentTxnId) {
		this.adjustmentTxnId = adjustmentTxnId;
	}

	public String getAdjustmentUserId() {
		return this.adjustmentUserId;
	}

	public void setAdjustmentUserId(final String adjustmentUserId) {
		this.adjustmentUserId = adjustmentUserId;
	}

	public BigDecimal getCaseId() {
		return this.caseId;
	}

	public void setCaseId(final BigDecimal caseId) {
		this.caseId = caseId;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public BigDecimal getCollectAgencyAmount() {
		return this.collectAgencyAmount;
	}

	public void setCollectAgencyAmount(final BigDecimal collectAgencyAmount) {
		this.collectAgencyAmount = collectAgencyAmount;
	}

	public String getCollectAgencyFlag() {
		return this.collectAgencyFlag;
	}

	public void setCollectAgencyFlag(final String collectAgencyFlag) {
		this.collectAgencyFlag = collectAgencyFlag;
	}

	public Date getCollectSentDate() {
		return this.collectSentDate;
	}

	public void setCollectSentDate(final Date collectSentDate) {
		this.collectSentDate = collectSentDate;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	

	public Date getCreateDateTime() {
		return createDateTime;
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

	public BigDecimal getCreditLimit() {
		return this.creditLimit;
	}

	public void setCreditLimit(final BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getDeductionAmount() {
		return this.deductionAmount;
	}

	public void setDeductionAmount(final BigDecimal deductionAmount) {
		this.deductionAmount = deductionAmount;
	}

	public BigDecimal getDeductionPercentage() {
		return this.deductionPercentage;
	}

	public void setDeductionPercentage(final BigDecimal deductionPercentage) {
		this.deductionPercentage = deductionPercentage;
	}

	public BigDecimal getDeductionPriority() {
		return this.deductionPriority;
	}

	public void setDeductionPriority(final BigDecimal deductionPriority) {
		this.deductionPriority = deductionPriority;
	}

	public String getDeductionStatus() {
		return this.deductionStatus;
	}

	public void setDeductionStatus(final String deductionStatus) {
		this.deductionStatus = deductionStatus;
	}

	public String getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(final String deductionType) {
		this.deductionType = deductionType;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getFifoFlag() {
		return this.fifoFlag;
	}

	public void setFifoFlag(final String fifoFlag) {
		this.fifoFlag = fifoFlag;
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(final BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getInformationNumber() {
		return this.informationNumber;
	}

	public void setInformationNumber(final String informationNumber) {
		this.informationNumber = informationNumber;
	}

	public String getJsStatus() {
		return this.jsStatus;
	}

	public void setJsStatus(final String jsStatus) {
		this.jsStatus = jsStatus;
	}

	public BigDecimal getMaxMonthlyAmount() {
		return this.maxMonthlyAmount;
	}

	public void setMaxMonthlyAmount(final BigDecimal maxMonthlyAmount) {
		this.maxMonthlyAmount = maxMonthlyAmount;
	}

	public BigDecimal getMaxRecursiveAmount() {
		return this.maxRecursiveAmount;
	}

	public void setMaxRecursiveAmount(final BigDecimal maxRecursiveAmount) {
		this.maxRecursiveAmount = maxRecursiveAmount;
	}

	public BigDecimal getMaxTotalAmount() {
		return this.maxTotalAmount;
	}

	public void setMaxTotalAmount(final BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Long getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getOffenderPaymentProfileId() {
		return this.offenderPaymentProfileId;
	}

	public void setOffenderPaymentProfileId(final BigDecimal offenderPaymentProfileId) {
		this.offenderPaymentProfileId = offenderPaymentProfileId;
	}

	public String getPayDeductionFlag() {
		return this.payDeductionFlag;
	}

	public void setPayDeductionFlag(final String payDeductionFlag) {
		this.payDeductionFlag = payDeductionFlag;
	}

	public BigDecimal getPayeeCorporateId() {
		return this.payeeCorporateId;
	}

	public void setPayeeCorporateId(final BigDecimal payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	public BigDecimal getPayeePersonId() {
		return this.payeePersonId;
	}

	public void setPayeePersonId(final BigDecimal payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	public BigDecimal getProcessPriorityNumber() {
		return this.processPriorityNumber;
	}

	public void setProcessPriorityNumber(final BigDecimal processPriorityNumber) {
		this.processPriorityNumber = processPriorityNumber;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the deductionDesc
	 */
	public String getDeductionDesc() {
		return deductionDesc;
	}

	/**
	 * @param deductionDesc
	 *            the deductionDesc to set
	 */
	public void setDeductionDesc(final String deductionDesc) {
		this.deductionDesc = deductionDesc;
	}

	/**
	 * @return the fixedFlag
	 */
	public String getFixedFlag() {
		return fixedFlag;
	}

	/**
	 * @param fixedFlag
	 *            the fixedFlag to set
	 */
	public void setFixedFlag(final String fixedFlag) {
		this.fixedFlag = fixedFlag;
	}

	/**
	 * @return the mthFlag
	 */
	public String getMthFlag() {
		return mthFlag;
	}

	/**
	 * @param mthFlag
	 *            the mthFlag to set
	 */
	public void setMthFlag(final String mthFlag) {
		this.mthFlag = mthFlag;
	}

	/**
	 * @return the actFlag
	 */
	public String getActFlag() {
		return actFlag;
	}

	/**
	 * @param actFlag
	 *            the actFlag to set
	 */
	public void setActFlag(final String actFlag) {
		this.actFlag = actFlag;
	}

	/**
	 * @return the indigentFlag
	 */
	public String getIndigentFlag() {
		return indigentFlag;
	}

	/**
	 * @param indigentFlag
	 *            the indigentFlag to set
	 */
	public void setIndigentFlag(final String indigentFlag) {
		this.indigentFlag = indigentFlag;
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
	 * @return the processPriorityNumb
	 */
	public BigDecimal getProcessPriorityNumb() {
		return processPriorityNumb;
	}

	/**
	 * @param processPriorityNumb
	 *            the processPriorityNumb to set
	 */
	public void setProcessPriorityNumb(final BigDecimal processPriorityNumb) {
		this.processPriorityNumb = processPriorityNumb;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return the parentDeductionId
	 */
	public BigDecimal getParentDeductionId() {
		return parentDeductionId;
	}

	/**
	 * @param parentDeductionId
	 *            the parentDeductionId to set
	 */
	public void setParentDeductionId(final BigDecimal parentDeductionId) {
		this.parentDeductionId = parentDeductionId;
	}

	/**
	 * @return the totPaid
	 */
	public Double getTotPaid() {
		return totPaid;
	}

	/**
	 * @param totPaid
	 *            the totPaid to set
	 */
	public void setTotPaid(final Double totPaid) {
		this.totPaid = totPaid;
	}

	/**
	 * @return the totOwing
	 */
	public Double getTotOwing() {
		return totOwing;
	}

	/**
	 * @param totOwing
	 *            the totOwing to set
	 */
	public void setTotOwing(final Double totOwing) {
		this.totOwing = totOwing;
	}

	/**
	 * @return the offTransList
	 */
	public List<OffenderTransactions> getOffTransList() {
		return offTransList;
	}

	/**
	 * @param offTransList
	 *            the offTransList to set
	 */
	public void setOffTransList(final List<OffenderTransactions> offTransList) {
		this.offTransList = offTransList;
	}

	/**
	 * @return the cgnbtMaxMonthlyAmount
	 */
	public String getCgnbtMaxMonthlyAmount() {
		return cgnbtMaxMonthlyAmount;
	}

	/**
	 * @param cgnbtMaxMonthlyAmount
	 *            the cgnbtMaxMonthlyAmount to set
	 */
	public void setCgnbtMaxMonthlyAmount(final String cgnbtMaxMonthlyAmount) {
		this.cgnbtMaxMonthlyAmount = cgnbtMaxMonthlyAmount;
	}

	/**
	 * @return the uniqueobligationprofile
	 */
	public String getUniqueobligationprofile() {
		return uniqueobligationprofile;
	}

	/**
	 * @param uniqueobligationprofile
	 *            the uniqueobligationprofile to set
	 */
	public void setUniqueobligationprofile(final String uniqueobligationprofile) {
		this.uniqueobligationprofile = uniqueobligationprofile;
	}

	/**
	 * @return the cgnbtAdjustmentStatus
	 */
	public String getCgnbtAdjustmentStatus() {
		return cgnbtAdjustmentStatus;
	}

	/**
	 * @param cgnbtAdjustmentStatus
	 *            the cgnbtAdjustmentStatus to set
	 */
	public void setCgnbtAdjustmentStatus(final String cgnbtAdjustmentStatus) {
		this.cgnbtAdjustmentStatus = cgnbtAdjustmentStatus;
	}

	/**
	 * @return the vsDamtCurVal
	 */
	public Integer getVsDamtCurVal() {
		return vsDamtCurVal;
	}

	/**
	 * @param vsDamtCurVal
	 *            the vsDamtCurVal to set
	 */
	public void setVsDamtCurVal(final Integer vsDamtCurVal) {
		this.vsDamtCurVal = vsDamtCurVal;
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
	 * @return the profilePayplnFlg
	 */
	public String getProfilePayplnFlg() {
		return profilePayplnFlg;
	}

	/**
	 * @param profilePayplnFlg
	 *            the profilePayplnFlg to set
	 */
	public void setProfilePayplnFlg(final String profilePayplnFlg) {
		this.profilePayplnFlg = profilePayplnFlg;
	}

	/**
	 * @return the percentageOfParent
	 */
	public String getPercentageOfParent() {
		return percentageOfParent;
	}

	/**
	 * @param percentageOfParent
	 *            the percentageOfParent to set
	 */
	public void setPercentageOfParent(final String percentageOfParent) {
		this.percentageOfParent = percentageOfParent;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(final Integer personId) {
		this.personId = personId;
	}

	/**
	 * @return the corporateId
	 */
	public Integer getCorporateId() {
		return corporateId;
	}

	/**
	 * @param corporateId
	 *            the corporateId to set
	 */
	public void setCorporateId(final Integer corporateId) {
		this.corporateId = corporateId;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(final Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the vUnlimDed
	 */
	public String getvUnlimDed() {
		return vUnlimDed;
	}

	/**
	 * @param vUnlimDed
	 *            the vUnlimDed to set
	 */
	public void setvUnlimDed(final String vUnlimDed) {
		this.vUnlimDed = vUnlimDed;
	}

	/**
	 * @return the vrectifiedTotal
	 */
	public BigDecimal getVrectifiedTotal() {
		return vrectifiedTotal;
	}

	/**
	 * @param vrectifiedTotal
	 *            the vrectifiedTotal to set
	 */
	public void setVrectifiedTotal(final BigDecimal vrectifiedTotal) {
		this.vrectifiedTotal = vrectifiedTotal;
	}

	/**
	 * @return the percent
	 */
	public BigDecimal getPercent() {
		return percent;
	}

	/**
	 * @param percent
	 *            the percent to set
	 */
	public void setPercent(final BigDecimal percent) {
		this.percent = percent;
	}

	/**
	 * @return the deductionCategory
	 */
	public String getDeductionCategory() {
		return deductionCategory;
	}

	/**
	 * @param deductionCategory
	 *            the deductionCategory to set
	 */
	public void setDeductionCategory(final String deductionCategory) {
		this.deductionCategory = deductionCategory;
	}

	/**
	 * @return the nbtAdjustmentReasonCode
	 */
	public String getNbtAdjustmentReasonCode() {
		return nbtAdjustmentReasonCode;
	}

	/**
	 * @param nbtAdjustmentReasonCode
	 *            the nbtAdjustmentReasonCode to set
	 */
	public void setNbtAdjustmentReasonCode(final String nbtAdjustmentReasonCode) {
		this.nbtAdjustmentReasonCode = nbtAdjustmentReasonCode;
	}

	/**
	 * @return the nbtWriteOffAmount
	 */
	public BigDecimal getNbtWriteOffAmount() {
		return nbtWriteOffAmount;
	}

	/**
	 * @param nbtWriteOffAmount
	 *            the nbtWriteOffAmount to set
	 */
	public void setNbtWriteOffAmount(final BigDecimal nbtWriteOffAmount) {
		this.nbtWriteOffAmount = nbtWriteOffAmount;
	}

	/**
	 * @return the nbtDeductionAmount
	 */
	public String getNbtDeductionAmount() {
		return nbtDeductionAmount;
	}

	/**
	 * @param nbtDeductionAmount
	 *            the nbtDeductionAmount to set
	 */
	public void setNbtDeductionAmount(final String nbtDeductionAmount) {
		this.nbtDeductionAmount = nbtDeductionAmount;
	}

	/**
	 * @return the unlimited
	 */
	public String getUnlimited() {
		return unlimited;
	}

	/**
	 * @param unlimited
	 *            the unlimited to set
	 */
	public void setUnlimited(final String unlimited) {
		this.unlimited = unlimited;
	}

	/**
	 * @return the deductionStatusDesc
	 */
	public String getDeductionStatusDesc() {
		return deductionStatusDesc;
	}

	/**
	 * @param deductionStatusDesc
	 *            the deductionStatusDesc to set
	 */
	public void setDeductionStatusDesc(final String deductionStatusDesc) {
		this.deductionStatusDesc = deductionStatusDesc;
	}

	/**
	 * @return the amountStatus
	 */
	public String getAmountStatus() {
		return amountStatus;
	}

	/**
	 * @param amountStatus
	 *            the amountStatus to set
	 */
	public void setAmountStatus(final String amountStatus) {
		this.amountStatus = amountStatus;
	}

	/**
	 * @return the offenderDeductionReceipts
	 */
	public List<OffenderDeductionReceipts> getOffenderDeductionReceipts() {
		return offenderDeductionReceipts;
	}

	/**
	 * @param offenderDeductionReceipts
	 *            the offenderDeductionReceipts to set
	 */
	public void setOffenderDeductionReceipts(final List<OffenderDeductionReceipts> offenderDeductionReceipts) {
		this.offenderDeductionReceipts = offenderDeductionReceipts;
	}

	/**
	 * @return the offenderBeneficiaries
	 */
	public List<OffenderBeneficiaries> getOffenderBeneficiaries() {
		return offenderBeneficiaries;
	}

	/**
	 * @param offenderBeneficiaries
	 *            the offenderBeneficiaries to set
	 */
	public void setOffenderBeneficiaries(final List<OffenderBeneficiaries> offenderBeneficiaries) {
		this.offenderBeneficiaries = offenderBeneficiaries;
	}

	/**
	 * @return the transactionDetails
	 */
	public OffenderTransactions getTransactionDetails() {
		return transactionDetails;
	}

	/**
	 * @param transactionDetails
	 *            the transactionDetails to set
	 */
	public void setTransactionDetails(final OffenderTransactions transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public String getDedFlag() {
		return dedFlag;
	}

	public void setDedFlag(final String dedFlag) {
		this.dedFlag = dedFlag;
	}

	public String getTbdFlag() {
		return tbdFlag;
	}

	public void setTbdFlag(final String tbdFlag) {
		this.tbdFlag = tbdFlag;
	}

	public BigDecimal getOrgAmount() {
		return orgAmount;
	}

	public void setOrgAmount(final BigDecimal orgAmount) {
		this.orgAmount = orgAmount;
	}

	public BigDecimal getTotCollected() {
		return totCollected;
	}

	public void setTotCollected(final BigDecimal totCollected) {
		this.totCollected = totCollected;
	}

	public BigDecimal getTotalOwing() {
		return totalOwing;
	}

	public void setTotalOwing(final BigDecimal totalOwing) {
		this.totalOwing = totalOwing;
	}
	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	public String getTxnDescription() {
		return txnDescription;
	}

	public void setTxnDescription(final String txnDescription) {
		this.txnDescription = txnDescription;
	}



	public BigDecimal getOldMaxTotalAmount() {
		return oldMaxTotalAmount;
	}



	public void setOldMaxTotalAmount(BigDecimal oldMaxTotalAmount) {
		this.oldMaxTotalAmount = oldMaxTotalAmount;
	}



	public BigDecimal getDeductionSeq() {
		return deductionSeq;
	}



	public void setDeductionSeq(BigDecimal deductionSeq) {
		this.deductionSeq = deductionSeq;
	}

	public Integer getExternalPriorityNo() {
		return externalPriorityNo;
	}



	public void setExternalPriorityNo(Integer externalPriorityNo) {
		this.externalPriorityNo = externalPriorityNo;
	}



	public Integer getInternalPriorityNo() {
		return internalPriorityNo;
	}



	public void setInternalPriorityNo(Integer internalPriorityNo) {
		this.internalPriorityNo = internalPriorityNo;
	}



	public BigDecimal getFlatRate() {
		return flatRate;
	}



	public void setFlatRate(BigDecimal flatRate) {
		this.flatRate = flatRate;
	}



	public Date getMonthlyDeductionDate() {
		return monthlyDeductionDate;
	}



	public void setMonthlyDeductionDate(Date monthlyDeductionDate) {
		this.monthlyDeductionDate = monthlyDeductionDate;
	}



	public BigDecimal getMonthlyDeductionAmount() {
		return monthlyDeductionAmount;
	}



	public void setMonthlyDeductionAmount(BigDecimal monthlyDeductionAmount) {
		this.monthlyDeductionAmount = monthlyDeductionAmount;
	}



	public BigDecimal getDeductionTxnEntry() {
		return deductionTxnEntry;
	}



	public void setDeductionTxnEntry(BigDecimal deductionTxnEntry) {
		this.deductionTxnEntry = deductionTxnEntry;
	}



	public Integer getAccountCode() {
		return accountCode;
	}



	public void setAccountCode(Integer accountCode) {
		this.accountCode = accountCode;
	}



	public String getTxnPostingType() {
		return txnPostingType;
	}



	public void setTxnPostingType(String txnPostingType) {
		this.txnPostingType = txnPostingType;
	}



	public String getConfirmFlag() {
		return confirmFlag;
	}



	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}



	public String getFromBalanceType() {
		return fromBalanceType;
	}



	public void setFromBalanceType(String fromBalanceType) {
		this.fromBalanceType = fromBalanceType;
	}



	public String getUnlimitedDeduction() {
		return unlimitedDeduction;
	}



	public void setUnlimitedDeduction(String unlimitedDeduction) {
		this.unlimitedDeduction = unlimitedDeduction;
	}



	public BigDecimal getReceiptPercentage() {
		return receiptPercentage;
	}



	public void setReceiptPercentage(BigDecimal receiptPercentage) {
		this.receiptPercentage = receiptPercentage;
	}



	public BigDecimal getNmbrPrcnt() {
		return nmbrPrcnt;
	}



	public void setNmbrPrcnt(BigDecimal nmbrPrcnt) {
		this.nmbrPrcnt = nmbrPrcnt;
	}
	
	

}
