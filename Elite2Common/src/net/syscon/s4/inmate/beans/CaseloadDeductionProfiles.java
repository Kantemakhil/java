package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CaseloadDeductionProfiles extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	@NotNull
	@Size(max = 6)
	private String caseloadId;

	@JsonProperty("deductionType")
	@NotNull
	@Size(max = 6)
	private String deductionType;

	@JsonProperty("accountCode")
	@NotNull
	private BigDecimal accountCode;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("categoryType")
	private String categoryType;

	@JsonProperty("coCreditWhenIndigentFlag")
	private String coCreditWhenIndigentFlag;

	@JsonProperty("coLimitAmount")
	private BigDecimal coLimitAmount;

	@JsonProperty("commConditionCode")
	private String commConditionCode;

	@JsonProperty("commConditionType")
	private String commConditionType;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;
	
	private Date createDatetime;
	
	private Date modifyDatetime;

	@JsonProperty("frequencyCode")
	private String frequencyCode;

	@JsonProperty("frequencyType")
	private String frequencyType;
	
	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;

	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}

	public void setOffenderDeductionId(Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	private int returnValue;
	

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}
	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("delayRecapture")
	private BigDecimal delayRecapture;

	@JsonProperty("effectiveDate")
	@NotNull
	private Date effectiveDate;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("externalPriorityNo")
	@NotNull
	private BigDecimal externalPriorityNo;

	@JsonProperty("fifoFlag")
	@NotNull
	@Size(max = 1)
	private String fifoFlag;

	@JsonProperty("flatRate")
	private BigDecimal flatRate;

	@JsonProperty("foAlAllOffenderFlag")
	@NotNull
	@Size(max = 1)
	private String foAlAllOffenderFlag;

	@JsonProperty("indigentMandatoryFlag")
	private String indigentMandatoryFlag;

	@JsonProperty("internalPriorityNo")
	@NotNull
	private BigDecimal internalPriorityNo;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("maxMonthlyAmount")
	private BigDecimal maxMonthlyAmount;

	@JsonProperty("maxRecursiveAmount")
	private BigDecimal maxRecursiveAmount;

	@JsonProperty("maxTotalAmount")
	private BigDecimal maxTotalAmount;

	@JsonProperty("minimumTrustBalance")
	private BigDecimal minimumTrustBalance;

	@JsonProperty("modifyDate")
	@NotNull
	private Date modifyDate;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;

	@JsonProperty("payeePersonId")
	private BigDecimal payeePersonId;

	@JsonProperty("percentage")
	@NotNull
	private BigDecimal percentage;

	@JsonProperty("sealFlag")
	private String sealFlag;
	  
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("txnUsage")
	private String txnUsage;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("receiptTxnType")
	private String receiptTxnType;
	
	@JsonProperty("ddDescription")
	private String ddDescription;
	
	@JsonProperty("nbtModifyUserId")
	private String nbtModifyUserId;
	
	
	@JsonProperty("deductionTypeDesc")
	private String deductionTypeDesc;
	
	@JsonProperty("fromBalType")
	private String fromBalType;
	
	@JsonProperty("caseloadDedBeneficiariesCommitBean")
	private CaseloadDedBeneficiariesCommitBean caseloadDedBeneficiariesCommitBean;
	
	@JsonProperty("caseloadDeductionDetailsCommitBean")
	private CaseloadDeductionDetailsCommitBean caseloadDeductionDetailsCommitBean;
	
	@JsonProperty("nbtDeductionType")
	private String nbtDeductionType;
	
	@JsonProperty("nbtAccountCode")
	private String nbtAccountCode;
	
	@JsonProperty("corporateName")
	private String corporateName;
	
	@JsonProperty("monthlyMax")
	private String monthlyMax;
	
	@JsonProperty("totalMax")
    private String totalMax;
	
	@JsonProperty("minTrustBal")
    private String minTrustBal;
	
	@JsonProperty("nbtPersonIdOne")
    private String nbtPersonIdOne;
	
	@JsonProperty("nbtPersonIdTwo")
    private String nbtPersonIdTwo;
	
	@JsonProperty("receiptNumber")
    private String receiptNumber;
	
	@JsonProperty("copies")
    private BigDecimal copies;
	
	
	@JsonProperty("corpNameNotFound")
    private String corpNameNotFound;
	
	@JsonProperty("code")
	private String code;

	@JsonProperty("location")
	private String location;

	@JsonProperty("amount")
	private Long  amount;

	@JsonProperty("frequency")
	private String frequency;

	@JsonProperty("dayOfMonth")
	private Long  dayOfMonth;

	@JsonProperty("backBill")
	private String backBill;
	@JsonProperty("nbtCode")
	private String nbtCode;

	@JsonProperty("nbtFrequency")
	private String nbtFrequency;

	@JsonProperty("nonBillableStatus")
	private String nonBillableStatus;
	
	
	@JsonProperty("vSysdat")
	private Date vSysdat;
	
	@JsonProperty("pOffId")
	private Integer pOffId;
	
	@JsonProperty("corpId")
	private BigDecimal corpId;
	public BigDecimal getCorpId() {
		return corpId;
	}

	public void setCorpId(BigDecimal corpId) {
		this.corpId = corpId;
	}

	public Date getvSysdat() {
		return vSysdat;
	}

	public void setvSysdat(Date vSysdat) {
		this.vSysdat = vSysdat;
	}

	public Integer getpOffId() {
		return pOffId;
	}

	public void setpOffId(Integer pOffId) {
		this.pOffId = pOffId;
	}

	public BigDecimal getLvOdp() {
		return lvOdp;
	}

	public void setLvOdp(BigDecimal lvOdp) {
		this.lvOdp = lvOdp;
	}
	@JsonProperty("lvOdp")
	private BigDecimal lvOdp;
	
	public String getCorpNameNotFound() {
		return corpNameNotFound;
	}

	public void setCorpNameNotFound(String corpNameNotFound) {
		this.corpNameNotFound = corpNameNotFound;
	}

	/**
	 * Creates new CaseloadDeductionProfiles class Object
	 */
	public CaseloadDeductionProfiles() {
		// CaseloadDeductionProfiles
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
	 * @return the categoryType
	 */
	public String getCategoryType() {
		return categoryType;
	}

	/**
	 * @param categoryType
	 *            the categoryType to set
	 */
	public void setCategoryType(final String categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 * @return the coCreditWhenIndigentFlag
	 */
	public String getCoCreditWhenIndigentFlag() {
		return coCreditWhenIndigentFlag;
	}

	/**
	 * @param coCreditWhenIndigentFlag
	 *            the coCreditWhenIndigentFlag to set
	 */
	public void setCoCreditWhenIndigentFlag(final String coCreditWhenIndigentFlag) {
		this.coCreditWhenIndigentFlag = coCreditWhenIndigentFlag;
	}

	/**
	 * @return the coLimitAmount
	 */
	public BigDecimal getCoLimitAmount() {
		return coLimitAmount;
	}

	/**
	 * @param coLimitAmount
	 *            the coLimitAmount to set
	 */
	public void setCoLimitAmount(final BigDecimal coLimitAmount) {
		this.coLimitAmount = coLimitAmount;
	}

	/**
	 * @return the commConditionCode
	 */
	public String getCommConditionCode() {
		return commConditionCode;
	}

	/**
	 * @param commConditionCode
	 *            the commConditionCode to set
	 */
	public void setCommConditionCode(final String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}

	/**
	 * @return the commConditionType
	 */
	public String getCommConditionType() {
		return commConditionType;
	}

	/**
	 * @param commConditionType
	 *            the commConditionType to set
	 */
	public void setCommConditionType(final String commConditionType) {
		this.commConditionType = commConditionType;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the delayRecapture
	 */
	public BigDecimal getDelayRecapture() {
		return delayRecapture;
	}

	/**
	 * @param delayRecapture
	 *            the delayRecapture to set
	 */
	public void setDelayRecapture(final BigDecimal delayRecapture) {
		this.delayRecapture = delayRecapture;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the externalPriorityNo
	 */
	public BigDecimal getExternalPriorityNo() {
		return externalPriorityNo;
	}

	/**
	 * @param externalPriorityNo
	 *            the externalPriorityNo to set
	 */
	public void setExternalPriorityNo(final BigDecimal externalPriorityNo) {
		this.externalPriorityNo = externalPriorityNo;
	}

	/**
	 * @return the fifoFlag
	 */
	public String getFifoFlag() {
		return fifoFlag;
	}

	/**
	 * @param fifoFlag
	 *            the fifoFlag to set
	 */
	public void setFifoFlag(final String fifoFlag) {
		this.fifoFlag = fifoFlag;
	}

	/**
	 * @return the flatRate
	 */
	public BigDecimal getFlatRate() {
		return flatRate;
	}

	/**
	 * @param flatRate
	 *            the flatRate to set
	 */
	public void setFlatRate(final BigDecimal flatRate) {
		this.flatRate = flatRate;
	}

	/**
	 * @return the foAlAllOffenderFlag
	 */
	public String getFoAlAllOffenderFlag() {
		return foAlAllOffenderFlag;
	}

	/**
	 * @param foAlAllOffenderFlag
	 *            the foAlAllOffenderFlag to set
	 */
	public void setFoAlAllOffenderFlag(final String foAlAllOffenderFlag) {
		this.foAlAllOffenderFlag = foAlAllOffenderFlag;
	}

	/**
	 * @return the indigentMandatoryFlag
	 */
	public String getIndigentMandatoryFlag() {
		return indigentMandatoryFlag;
	}

	/**
	 * @param indigentMandatoryFlag
	 *            the indigentMandatoryFlag to set
	 */
	public void setIndigentMandatoryFlag(final String indigentMandatoryFlag) {
		this.indigentMandatoryFlag = indigentMandatoryFlag;
	}

	/**
	 * @return the internalPriorityNo
	 */
	public BigDecimal getInternalPriorityNo() {
		return internalPriorityNo;
	}

	/**
	 * @param internalPriorityNo
	 *            the internalPriorityNo to set
	 */
	public void setInternalPriorityNo(final BigDecimal internalPriorityNo) {
		this.internalPriorityNo = internalPriorityNo;
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
	 * @return the maxMonthlyAmount
	 */
	public BigDecimal getMaxMonthlyAmount() {
		return maxMonthlyAmount;
	}

	/**
	 * @param maxMonthlyAmount
	 *            the maxMonthlyAmount to set
	 */
	public void setMaxMonthlyAmount(final BigDecimal maxMonthlyAmount) {
		this.maxMonthlyAmount = maxMonthlyAmount;
	}

	/**
	 * @return the maxRecursiveAmount
	 */
	public BigDecimal getMaxRecursiveAmount() {
		return maxRecursiveAmount;
	}

	/**
	 * @param maxRecursiveAmount
	 *            the maxRecursiveAmount to set
	 */
	public void setMaxRecursiveAmount(final BigDecimal maxRecursiveAmount) {
		this.maxRecursiveAmount = maxRecursiveAmount;
	}

	/**
	 * @return the maxTotalAmount
	 */
	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	/**
	 * @param maxTotalAmount
	 *            the maxTotalAmount to set
	 */
	public void setMaxTotalAmount(final BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	/**
	 * @return the minimumTrustBalance
	 */
	public BigDecimal getMinimumTrustBalance() {
		return minimumTrustBalance;
	}

	/**
	 * @param minimumTrustBalance
	 *            the minimumTrustBalance to set
	 */
	public void setMinimumTrustBalance(final BigDecimal minimumTrustBalance) {
		this.minimumTrustBalance = minimumTrustBalance;
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
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(final BigDecimal percentage) {
		this.percentage = percentage;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getTxnUsage() {
		return txnUsage;
	}

	public void setTxnUsage(final String txnUsage) {
		this.txnUsage = txnUsage;
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
	 * @return the receiptTxnType
	 */
	public String getReceiptTxnType() {
		return receiptTxnType;
	}

	/**
	 * @param receiptTxnType the receiptTxnType to set
	 */
	public void setReceiptTxnType(final String receiptTxnType) {
		this.receiptTxnType = receiptTxnType;
	}

	/**
	 * @return the ddDescription
	 */
	public String getDdDescription() {
		return ddDescription;
	}

	/**
	 * @param ddDescription the ddDescription to set
	 */
	public void setDdDescription(final String ddDescription) {
		this.ddDescription = ddDescription;
	}

	/**
	 * @return the nbtModifyUserId
	 */
	public String getNbtModifyUserId() {
		return nbtModifyUserId;
	}

	/**
	 * @param nbtModifyUserId the nbtModifyUserId to set
	 */
	public void setNbtModifyUserId(final String nbtModifyUserId) {
		this.nbtModifyUserId = nbtModifyUserId;
	}

	/**
	 * @return the deductionTypeDesc
	 */
	public String getDeductionTypeDesc() {
		return deductionTypeDesc;
	}

	/**
	 * @param deductionTypeDesc the deductionTypeDesc to set
	 */
	public void setDeductionTypeDesc(final String deductionTypeDesc) {
		this.deductionTypeDesc = deductionTypeDesc;
	}

	/**
	 * @return the fromBalType
	 */
	public String getFromBalType() {
		return fromBalType;
	}

	/**
	 * @param fromBalType the fromBalType to set
	 */
	public void setFromBalType(final String fromBalType) {
		this.fromBalType = fromBalType;
	}

	/**
	 * @return the caseloadDedBeneficiariesCommitBean
	 */
	public CaseloadDedBeneficiariesCommitBean getCaseloadDedBeneficiariesCommitBean() {
		return caseloadDedBeneficiariesCommitBean;
	}

	/**
	 * @param caseloadDedBeneficiariesCommitBean the caseloadDedBeneficiariesCommitBean to set
	 */
	public void setCaseloadDedBeneficiariesCommitBean(
			final CaseloadDedBeneficiariesCommitBean caseloadDedBeneficiariesCommitBean) {
		this.caseloadDedBeneficiariesCommitBean = caseloadDedBeneficiariesCommitBean;
	}

	/**
	 * @return the caseloadDeductionDetailsCommitBean
	 */
	public CaseloadDeductionDetailsCommitBean getCaseloadDeductionDetailsCommitBean() {
		return caseloadDeductionDetailsCommitBean;
	}

	/**
	 * @param caseloadDeductionDetailsCommitBean the caseloadDeductionDetailsCommitBean to set
	 */
	public void setCaseloadDeductionDetailsCommitBean(
			final CaseloadDeductionDetailsCommitBean caseloadDeductionDetailsCommitBean) {
		this.caseloadDeductionDetailsCommitBean = caseloadDeductionDetailsCommitBean;
	}

	/**
	 * @return the nbtDeductionType
	 */
	public String getNbtDeductionType() {
		return nbtDeductionType;
	}

	/**
	 * @param nbtDeductionType the nbtDeductionType to set
	 */
	public void setNbtDeductionType(final String nbtDeductionType) {
		this.nbtDeductionType = nbtDeductionType;
	}

	/**
	 * @return the nbtAccountCode
	 */
	public String getNbtAccountCode() {
		return nbtAccountCode;
	}

	/**
	 * @param nbtAccountCode the nbtAccountCode to set
	 */
	public void setNbtAccountCode(final String nbtAccountCode) {
		this.nbtAccountCode = nbtAccountCode;
	}

	/**
	 * @return the corporateName
	 */
	public String getCorporateName() {
		return corporateName;
	}

	/**
	 * @param corporateName the corporateName to set
	 */
	public void setCorporateName(final String corporateName) {
		this.corporateName = corporateName;
	}

	/**
	 * @return the monthlyMax
	 */
	public String getMonthlyMax() {
		return monthlyMax;
	}

	/**
	 * @param monthlyMax the monthlyMax to set
	 */
	public void setMonthlyMax(final String monthlyMax) {
		this.monthlyMax = monthlyMax;
	}

	/**
	 * @return the totalMax
	 */
	public String getTotalMax() {
		return totalMax;
	}

	/**
	 * @param totalMax the totalMax to set
	 */
	public void setTotalMax(final String totalMax) {
		this.totalMax = totalMax;
	}

	/**
	 * @return the minTrustBal
	 */
	public String getMinTrustBal() {
		return minTrustBal;
	}

	/**
	 * @param minTrustBal the minTrustBal to set
	 */
	public void setMinTrustBal(final String minTrustBal) {
		this.minTrustBal = minTrustBal;
	}

	/**
	 * @return the nbtPersonIdOne
	 */
	public String getNbtPersonIdOne() {
		return nbtPersonIdOne;
	}

	/**
	 * @param nbtPersonIdOne the nbtPersonIdOne to set
	 */
	public void setNbtPersonIdOne(final String nbtPersonIdOne) {
		this.nbtPersonIdOne = nbtPersonIdOne;
	}

	/**
	 * @return the nbtPersonIdTwo
	 */
	public String getNbtPersonIdTwo() {
		return nbtPersonIdTwo;
	}

	/**
	 * @param nbtPersonIdTwo the nbtPersonIdTwo to set
	 */
	public void setNbtPersonIdTwo(final String nbtPersonIdTwo) {
		this.nbtPersonIdTwo = nbtPersonIdTwo;
	}

	/**
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(final String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	/**
	 * @return the copies
	 */
	public BigDecimal getCopies() {
		return copies;
	}

	/**
	 * @param copies the copies to set
	 */
	public void setCopies(final BigDecimal copies) {
		this.copies = copies;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Long getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Long dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getBackBill() {
		return backBill;
	}

	public void setBackBill(String backBill) {
		this.backBill = backBill;
	}
	public String getNbtCode() {
		return nbtCode;
	}

	public void setNbtCode(String nbtCode) {
		this.nbtCode = nbtCode;
	}

	public String getNbtFrequency() {
		return nbtFrequency;
	}

	public void setNbtFrequency(String nbtFrequency) {
		this.nbtFrequency = nbtFrequency;
	}

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public String getNonBillableStatus() {
		return nonBillableStatus;
	}

	public void setNonBillableStatus(String nonBillableStatus) {
		this.nonBillableStatus = nonBillableStatus;
	}

}
