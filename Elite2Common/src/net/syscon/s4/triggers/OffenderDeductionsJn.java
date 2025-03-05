package net.syscon.s4.triggers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderDeductionsJn extends BaseModel implements Serializable{
	
	private String jnOperation;
	private String jnOracleUser;
	private Date jnDatetime;
	private String jnNotes;
	private String jnAppln;
	private BigDecimal jnSession;
	private Long offenderDeductionId;
	private String caseloadId;
	private Long offenderId;
	private Double creditLimit;
	private String deductionType;
	private String deductionStatus;
	private Integer deductionPriority;
	private String informationNumber;
	private Integer deductionPercentage;
	private Integer processPriorityNumber;
	private Date effectiveDate;
	private String commentText;
	private String fifoFlag;
	private Long payeePersonId;
	private Long payeeCorporateId;
	private Double maxMonthlyAmount;
	private Double maxTotalAmount;
	private Double deductionAmount;
	private String adjustmentReasonCode;
	private Double adjustmentAmount;
	private String adjustmentUserId;
	private Long adjustmentTxnId;
	private String adjustmentText;
	private Date modifyDate;
	private String payDeductionFlag;
	private Double maxRecursiveAmount;
	private Long groupId;
	private Long caseId;
	private Long parentDeductionId;
	private String jsStatus;
	private Double collectAgencyAmount;
	private String collectAgencyFlag;
	private Date collectSentDate;
	private Long offenderPaymentProfileId;
	private String sealFlag;
	private String createUserId;
	private String modifyUserId;
	
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
	public Double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Double creditLimit) {
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
	public Integer getDeductionPriority() {
		return deductionPriority;
	}
	public void setDeductionPriority(Integer deductionPriority) {
		this.deductionPriority = deductionPriority;
	}
	public String getInformationNumber() {
		return informationNumber;
	}
	public void setInformationNumber(String informationNumber) {
		this.informationNumber = informationNumber;
	}
	public Integer getDeductionPercentage() {
		return deductionPercentage;
	}
	public void setDeductionPercentage(Integer deductionPercentage) {
		this.deductionPercentage = deductionPercentage;
	}
	public Integer getProcessPriorityNumber() {
		return processPriorityNumber;
	}
	public void setProcessPriorityNumber(Integer processPriorityNumber) {
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
	public Double getMaxMonthlyAmount() {
		return maxMonthlyAmount;
	}
	public void setMaxMonthlyAmount(Double maxMonthlyAmount) {
		this.maxMonthlyAmount = maxMonthlyAmount;
	}
	public Double getMaxTotalAmount() {
		return maxTotalAmount;
	}
	public void setMaxTotalAmount(Double maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}
	public Double getDeductionAmount() {
		return deductionAmount;
	}
	public void setDeductionAmount(Double deductionAmount) {
		this.deductionAmount = deductionAmount;
	}
	public String getAdjustmentReasonCode() {
		return adjustmentReasonCode;
	}
	public void setAdjustmentReasonCode(String adjustmentReasonCode) {
		this.adjustmentReasonCode = adjustmentReasonCode;
	}
	public Double getAdjustmentAmount() {
		return adjustmentAmount;
	}
	public void setAdjustmentAmount(Double adjustmentAmount) {
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
	public Double getMaxRecursiveAmount() {
		return maxRecursiveAmount;
	}
	public void setMaxRecursiveAmount(Double maxRecursiveAmount) {
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
	public Double getCollectAgencyAmount() {
		return collectAgencyAmount;
	}
	public void setCollectAgencyAmount(Double collectAgencyAmount) {
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
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	
}
