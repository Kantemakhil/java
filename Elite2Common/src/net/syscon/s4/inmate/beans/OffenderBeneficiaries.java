package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderBeneficiaries extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("beneficiaryId")
	private Long beneficiaryId;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("corporateId")
	private BigDecimal corporateId;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("monthlyAmount")
	private BigDecimal monthlyAmount;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("overrideAmount")
	private BigDecimal overrideAmount;

	@JsonProperty("percent")
	private BigDecimal percent;

	@JsonProperty("personId")
	private BigDecimal personId;

	@JsonProperty("priority")
	private BigDecimal priority;

	@JsonProperty("receivedAmount")
	private BigDecimal receivedAmount;

	@JsonProperty("recursiveAmount")
	private BigDecimal recursiveAmount;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("tbdFlag")
	private String tbdFlag;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("caseLoadType")
	private String caseLoadType;

	@JsonProperty("txnType")
	private String txnType;

	@JsonProperty("unknownBenId")
	private BigDecimal unknownBenId;

	@JsonProperty("drvAmount")
	private BigDecimal drvAmount;

	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;

	@JsonProperty("corporateName")
	private String corporateName;

	@JsonProperty("deductionType")
	private String deductionType;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("maxTotalAmount")
	private BigDecimal maxTotalAmount;

	@JsonProperty("maxRecursiveAmount")
	private BigDecimal maxRecursiveAmount;

	@JsonProperty("informationNumber")
	private String informationNumber;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("writeOfAmount")
	private BigDecimal writeOfAmount;

	@JsonProperty("totalAmount")
	private BigDecimal totalAmount;

	@JsonProperty("totalCollected")
	private String totalCollected;

	@JsonProperty("totalOwing")
	private String totalOwing;

	@JsonProperty("deductionDesc")
	private String deductionDesc;

	@JsonProperty("maxMontlyAmount")
	private BigDecimal maxMontlyAmount;

	@JsonProperty("suspendedFlag")
	private String suspendedFlag;

	@JsonProperty("cgnbtPersonId")
	private String cgnbtPersonId;

	@JsonProperty("cgnbtPercent")
	private String cgnbtPercent;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("subAccountType")
	private String subAccountType;

	@JsonProperty("txnEntryAmount")
	private BigDecimal txnEntryAmount;

	@JsonProperty("txnEntrySeq")
	private Integer txnEntrySeq;

	@JsonProperty("dspPercent")
	private BigDecimal dspPercent;

	@JsonProperty("totalDescription")
	private String totalDescription;
	
	@JsonProperty("maintainceFlag")
	private String maintainceFlag;
	
	private String location;
	
	private Integer txnId;
	
	@JsonProperty("totalPaid")
	private BigDecimal totalPaid;
	
	@JsonProperty("originalAmount")
	private String originalAmount;
	
	
	@JsonProperty("personName")
	private String personName;

	@JsonProperty("unknownBenIdFlag")
	private String unknownBenIdFlag;
	
	@JsonProperty("monthlyDeductionDate")
	private Date monthlyDeductionDate;
	
	

	public Date getMonthlyDeductionDate() {
		return monthlyDeductionDate;
	}

	public void setMonthlyDeductionDate(Date monthlyDeductionDate) {
		this.monthlyDeductionDate = monthlyDeductionDate;
	}

	/**
	 * Creates new OffenderBeneficiaries class Object
	 */
	public OffenderBeneficiaries() {
		// OffenderBeneficiaries
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
	 * @return the beneficiaryId
	 */
	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	/**
	 * @param beneficiaryId
	 *            the beneficiaryId to set
	 */
	public void setBeneficiaryId(final Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
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
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the corporateId
	 */
	public BigDecimal getCorporateId() {
		return corporateId;
	}

	/**
	 * @param corporateId
	 *            the corporateId to set
	 */
	public void setCorporateId(final BigDecimal corporateId) {
		this.corporateId = corporateId;
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
	 * @return the monthlyAmount
	 */
	public BigDecimal getMonthlyAmount() {
		return monthlyAmount;
	}

	/**
	 * @param monthlyAmount
	 *            the monthlyAmount to set
	 */
	public void setMonthlyAmount(final BigDecimal monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
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
	 * @return the overrideAmount
	 */
	public BigDecimal getOverrideAmount() {
		return overrideAmount;
	}

	/**
	 * @param overrideAmount
	 *            the overrideAmount to set
	 */
	public void setOverrideAmount(final BigDecimal overrideAmount) {
		this.overrideAmount = overrideAmount;
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
	 * @return the personId
	 */
	public BigDecimal getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(final BigDecimal personId) {
		this.personId = personId;
	}

	/**
	 * @return the priority
	 */
	public BigDecimal getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(final BigDecimal priority) {
		this.priority = priority;
	}

	/**
	 * @return the receivedAmount
	 */
	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	/**
	 * @param receivedAmount
	 *            the receivedAmount to set
	 */
	public void setReceivedAmount(final BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	/**
	 * @return the recursiveAmount
	 */
	public BigDecimal getRecursiveAmount() {
		return recursiveAmount;
	}

	/**
	 * @param recursiveAmount
	 *            the recursiveAmount to set
	 */
	public void setRecursiveAmount(final BigDecimal recursiveAmount) {
		this.recursiveAmount = recursiveAmount;
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
	 * @return the tbdFlag
	 */
	public String getTbdFlag() {
		return tbdFlag;
	}

	/**
	 * @param tbdFlag
	 *            the tbdFlag to set
	 */
	public void setTbdFlag(final String tbdFlag) {
		this.tbdFlag = tbdFlag;
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
	 * @return the caseLoadType
	 */
	public String getCaseLoadType() {
		return caseLoadType;
	}

	/**
	 * @param caseLoadType
	 *            the caseLoadType to set
	 */
	public void setCaseLoadType(final String caseLoadType) {
		this.caseLoadType = caseLoadType;
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
	 * @return the unknownBenId
	 */
	public BigDecimal getUnknownBenId() {
		return unknownBenId;
	}

	/**
	 * @param unknownBenId
	 *            the unknownBenId to set
	 */
	public void setUnknownBenId(final BigDecimal unknownBenId) {
		this.unknownBenId = unknownBenId;
	}

	/**
	 * @return the drvAmount
	 */
	public BigDecimal getDrvAmount() {
		return drvAmount;
	}

	/**
	 * @param drvAmount
	 *            the drvAmount to set
	 */
	public void setDrvAmount(final BigDecimal drvAmount) {
		this.drvAmount = drvAmount;
	}

	/**
	 * @return the offenderDeductionId
	 */
	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}

	/**
	 * @param offenderDeductionId
	 *            the offenderDeductionId to set
	 */
	public void setOffenderDeductionId(final Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
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
	 * @return the informationNumber
	 */
	public String getInformationNumber() {
		return informationNumber;
	}

	/**
	 * @param informationNumber
	 *            the informationNumber to set
	 */
	public void setInformationNumber(final String informationNumber) {
		this.informationNumber = informationNumber;
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
	 * @return the writeOfAmount
	 */
	public BigDecimal getWriteOfAmount() {
		return writeOfAmount;
	}

	/**
	 * @param writeOfAmount
	 *            the writeOfAmount to set
	 */
	public void setWriteOfAmount(final BigDecimal writeOfAmount) {
		this.writeOfAmount = writeOfAmount;
	}

	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(final BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the totalCollected
	 */
	public String getTotalCollected() {
		return totalCollected;
	}

	/**
	 * @param totalCollected
	 *            the totalCollected to set
	 */
	public void setTotalCollected(final String totalCollected) {
		this.totalCollected = totalCollected;
	}

	/**
	 * @return the totalOwing
	 */
	public String getTotalOwing() {
		return totalOwing;
	}

	/**
	 * @param totalOwing
	 *            the totalOwing to set
	 */
	public void setTotalOwing(final String totalOwing) {
		this.totalOwing = totalOwing;
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
	 * @return the maxMontlyAmount
	 */
	public BigDecimal getMaxMontlyAmount() {
		return maxMontlyAmount;
	}

	/**
	 * @param maxMontlyAmount
	 *            the maxMontlyAmount to set
	 */
	public void setMaxMontlyAmount(final BigDecimal maxMontlyAmount) {
		this.maxMontlyAmount = maxMontlyAmount;
	}

	/**
	 * @return the suspendedFlag
	 */
	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	/**
	 * @param suspendedFlag
	 *            the suspendedFlag to set
	 */
	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 * @return the cgnbtPersonId
	 */
	public String getCgnbtPersonId() {
		return cgnbtPersonId;
	}

	/**
	 * @param cgnbtPersonId
	 *            the cgnbtPersonId to set
	 */
	public void setCgnbtPersonId(final String cgnbtPersonId) {
		this.cgnbtPersonId = cgnbtPersonId;
	}

	/**
	 * @return the cgnbtPercent
	 */
	public String getCgnbtPercent() {
		return cgnbtPercent;
	}

	/**
	 * @param cgnbtPercent
	 *            the cgnbtPercent to set
	 */
	public void setCgnbtPercent(final String cgnbtPercent) {
		this.cgnbtPercent = cgnbtPercent;
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
	 * @return the dspPercent
	 */
	public BigDecimal getDspPercent() {
		return dspPercent;
	}

	/**
	 * @param dspPercent
	 *            the dspPercent to set
	 */
	public void setDspPercent(final BigDecimal dspPercent) {
		this.dspPercent = dspPercent;
	}

	/**
	 * @return the totalDescription
	 */
	public String getTotalDescription() {
		return totalDescription;
	}

	/**
	 * @param totalDescription
	 *            the totalDescription to set
	 */
	public void setTotalDescription(final String totalDescription) {
		this.totalDescription = totalDescription;
	}

	/**
	 * @return the maintainceFlag
	 */
	public String getMaintainceFlag() {
		return maintainceFlag;
	}

	/**
	 * @param maintainceFlag the maintainceFlag to set
	 */
	public void setMaintainceFlag(final String maintainceFlag) {
		this.maintainceFlag = maintainceFlag;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public Integer getTxnId() {
		return txnId;
	}

	public void setTxnId(final Integer txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the totalPaid
	 */
	public BigDecimal getTotalPaid() {
		return totalPaid;
	}

	/**
	 * @return the originalAmount
	 */
	public String getOriginalAmount() {
		return originalAmount;
	}

	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * @return the unknownBenIdFlag
	 */
	public String getUnknownBenIdFlag() {
		return unknownBenIdFlag;
	}

	/**
	 * @param totalPaid the totalPaid to set
	 */
	public void setTotalPaid(final BigDecimal totalPaid) {
		this.totalPaid = totalPaid;
	}

	/**
	 * @param originalAmount the originalAmount to set
	 */
	public void setOriginalAmount(final String originalAmount) {
		this.originalAmount = originalAmount;
	}

	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(final String personName) {
		this.personName = personName;
	}

	/**
	 * @param unknownBenIdFlag the unknownBenIdFlag to set
	 */
	public void setUnknownBenIdFlag(final String unknownBenIdFlag) {
		this.unknownBenIdFlag = unknownBenIdFlag;
	}

}
