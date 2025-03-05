package net.syscon.s4.triggers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.BaseModel;

@Repository
public class OffenderDeductionsHty extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jnOperation;
	private String jnOracleUser;
	private Date jnDatetime;
	private String jnNotes;
	private String jnAppln;
	private BigDecimal jnSession;
	private Long offenderDeductionId;
	private String caseloadId;
	private Long offenderId;
	private BigDecimal creditLimit;
	private String deductionType;
	private String deductionStatus;
	private Double deductionPriority;
	private String informationNumber;
	private Double deductionPercentage;
	private Double processPriorityNumber;
	private Date effectiveDate;
	private String commentText;
	private String fifoFlag;
	private Long payeePersonId;
	private Long payeeCorporateId;
	private BigDecimal maxMonthlyAmount;
	private BigDecimal maxTotalAmount;
	private BigDecimal deductionAmount;
	private String adjustmentReasonCode;
	private BigDecimal adjustmentAmount;
	private String adjustmentUserId;
	private Long adjustmentTxnId;
	private String adjustmentText;
	private String modifyUserId;
	private Date modifyDate;
	private String payDeductionFlag;
	private BigDecimal maxRecursiveAmount;
	private Long groupId;
	private Long caseId;
	private Long parentDeductionId;
	private String jsStatus;
	private BigDecimal collectAgencyAmount;
	private String collectAgencyFlag;
	private Date collectSentDate;
	private String createUserId;
	private Long offenderPaymentProfileId;
	private String sealFlag;
	private Date createDatetime;
	private Date modifyDatetime;

	public String getJnOperation() {
		return jnOperation;
	}

	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}

	public String getJnOracleUser() {
		return jnOracleUser;
	}

	public void setJnOracleUser(String jnOracleUser) {
		this.jnOracleUser = jnOracleUser;
	}

	public Date getJnDatetime() {
		return jnDatetime;
	}

	public void setJnDatetime(Date jnDatetime) {
		this.jnDatetime = jnDatetime;
	}

	public String getJnNotes() {
		return jnNotes;
	}

	public void setJnNotes(String jnNotes) {
		this.jnNotes = jnNotes;
	}

	public String getJnAppln() {
		return jnAppln;
	}

	public void setJnAppln(String jnAppln) {
		this.jnAppln = jnAppln;
	}

	public BigDecimal getJnSession() {
		return jnSession;
	}

	public void setJnSession(BigDecimal jnSession) {
		this.jnSession = jnSession;
	}

	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}

	public void setOffenderDeductionId(Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	public String getDeductionStatus() {
		return deductionStatus;
	}

	public void setDeductionStatus(String deductionStatus) {
		this.deductionStatus = deductionStatus;
	}

	public Double getDeductionPriority() {
		return deductionPriority;
	}

	public void setDeductionPriority(Double deductionPriority) {
		this.deductionPriority = deductionPriority;
	}

	public String getInformationNumber() {
		return informationNumber;
	}

	public void setInformationNumber(String informationNumber) {
		this.informationNumber = informationNumber;
	}

	public Double getDeductionPercentage() {
		return deductionPercentage;
	}

	public void setDeductionPercentage(Double deductionPercentage) {
		this.deductionPercentage = deductionPercentage;
	}

	public Double getProcessPriorityNumber() {
		return processPriorityNumber;
	}

	public void setProcessPriorityNumber(Double processPriorityNumber) {
		this.processPriorityNumber = processPriorityNumber;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getFifoFlag() {
		return fifoFlag;
	}

	public void setFifoFlag(String fifoFlag) {
		this.fifoFlag = fifoFlag;
	}

	public Long getPayeePersonId() {
		return payeePersonId;
	}

	public void setPayeePersonId(Long payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	public Long getPayeeCorporateId() {
		return payeeCorporateId;
	}

	public void setPayeeCorporateId(Long payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	public BigDecimal getMaxMonthlyAmount() {
		return maxMonthlyAmount;
	}

	public void setMaxMonthlyAmount(BigDecimal maxMonthlyAmount) {
		this.maxMonthlyAmount = maxMonthlyAmount;
	}

	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	public void setMaxTotalAmount(BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	public BigDecimal getDeductionAmount() {
		return deductionAmount;
	}

	public void setDeductionAmount(BigDecimal deductionAmount) {
		this.deductionAmount = deductionAmount;
	}

	public String getAdjustmentReasonCode() {
		return adjustmentReasonCode;
	}

	public void setAdjustmentReasonCode(String adjustmentReasonCode) {
		this.adjustmentReasonCode = adjustmentReasonCode;
	}

	public BigDecimal getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public String getAdjustmentUserId() {
		return adjustmentUserId;
	}

	public void setAdjustmentUserId(String adjustmentUserId) {
		this.adjustmentUserId = adjustmentUserId;
	}

	public Long getAdjustmentTxnId() {
		return adjustmentTxnId;
	}

	public void setAdjustmentTxnId(Long adjustmentTxnId) {
		this.adjustmentTxnId = adjustmentTxnId;
	}

	public String getAdjustmentText() {
		return adjustmentText;
	}

	public void setAdjustmentText(String adjustmentText) {
		this.adjustmentText = adjustmentText;
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

	public String getPayDeductionFlag() {
		return payDeductionFlag;
	}

	public void setPayDeductionFlag(String payDeductionFlag) {
		this.payDeductionFlag = payDeductionFlag;
	}

	public BigDecimal getMaxRecursiveAmount() {
		return maxRecursiveAmount;
	}

	public void setMaxRecursiveAmount(BigDecimal maxRecursiveAmount) {
		this.maxRecursiveAmount = maxRecursiveAmount;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Long getParentDeductionId() {
		return parentDeductionId;
	}

	public void setParentDeductionId(Long parentDeductionId) {
		this.parentDeductionId = parentDeductionId;
	}

	public String getJsStatus() {
		return jsStatus;
	}

	public void setJsStatus(String jsStatus) {
		this.jsStatus = jsStatus;
	}

	public BigDecimal getCollectAgencyAmount() {
		return collectAgencyAmount;
	}

	public void setCollectAgencyAmount(BigDecimal collectAgencyAmount) {
		this.collectAgencyAmount = collectAgencyAmount;
	}

	public String getCollectAgencyFlag() {
		return collectAgencyFlag;
	}

	public void setCollectAgencyFlag(String collectAgencyFlag) {
		this.collectAgencyFlag = collectAgencyFlag;
	}

	public Date getCollectSentDate() {
		return collectSentDate;
	}

	public void setCollectSentDate(Date collectSentDate) {
		this.collectSentDate = collectSentDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Long getOffenderPaymentProfileId() {
		return offenderPaymentProfileId;
	}

	public void setOffenderPaymentProfileId(Long offenderPaymentProfileId) {
		this.offenderPaymentProfileId = offenderPaymentProfileId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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

}
