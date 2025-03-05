package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the OFFENDER_SENTENCES_HTY database table.
 * 
 */
public class OffenderSentencesHty extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal aggregateSentenceSeq;

	private String applicableAdjustCode; 

	private String asGoodtimeType;

	private BigDecimal chargeSeq;

	private String commentText;

	private BigDecimal consecToSentenceSeq;

	private String consecutiveCountFlag;

	private BigDecimal counts;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;

	private Date creationDate;

	private String creationUser;

	private BigDecimal defaultDays;

	private String description;

	private BigDecimal dischargeAuthority;

	private String dischargeComment;

	private Date dischargeDate;

	private String dischargeReason;

	private String dtbfFlag;

	private Date effectiveDate;

	private String eksSentence;

	private Date extendedDate;

	private String extendedFlag;

	private String extendingAuthority;

	private BigDecimal fineAmount;

	private String fineCommentText;

	private String gapFlag;

	private BigDecimal goodConductDays;

	private String installmentDetails;

	private String jurisdictionCode;

	private Date modifyDatetime;

	private String modifyUserId;

	private Date nonProbationStartDate;

	private String orderCode;

	private String orderType;

	private Date paidDate;

	private Date paymentDueDate;

	private Date probableReleaseDate;

	private Date probableReleaseTime;

	private Date registrationDate;

	private Date reportDueDate;

	private Date revocationDate;

	private String sealFlag;

	private String sentCalcNeededFlag;

	private String sentClosCommentText;

	private String sentClosReasonCode;

	private Date sentClosedDate;

	private String sentenceCalcType;

	private Date sentenceDate;

	private Date sentenceExpiryDate;

	private Date sentenceExpiryTime;

	private String sentenceStatus;

	private Date startDate;

	private Date startTime;

	private String supervisingAgyLocId;

	private Date supervisionExpiryDate;

	private String termChangedFlag;

	private BigDecimal totalCompensation;

	private BigDecimal totalFine;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("sentenceSeq")
	private long sentenceSeq;

	private long sentenceEventId;
	private BigDecimal version;

	private String breachAmend;

	private String jurisCode;

	private String sentenceType;

	private BigDecimal orderId;

	private String sentCalcType;
	private String sentenceCategory;
	private String sentenceCalcTypeDesc;
	@JsonProperty("offenderSentenceHtyId")
	private BigDecimal offenderSentenceHtyId;
	@JsonProperty("adjustReason")
	private String adjustReason;
	private BigDecimal staffId;
	@JsonProperty("noOfUnexcusedAbsence")
	private long noOfUnexcusedAbsence;
	@JsonProperty("adjustDate")
	private Date adjustDate;
	@JsonProperty("adjustTime")
	private Date adjustTime;
	@JsonProperty("staffName")
	private String staffName; 

	public Date getAdjustTime() {
		return adjustTime;
	}

	public void setAdjustTime(final Date adjustTime) {
		this.adjustTime = adjustTime;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(final Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public long getNoOfUnexcusedAbsence() {
		return noOfUnexcusedAbsence;
	}

	public void setNoOfUnexcusedAbsence(final long noOfUnexcusedAbsence) {
		this.noOfUnexcusedAbsence = noOfUnexcusedAbsence;
	}

	public BigDecimal getStaffId() {
		return staffId;
	}

	public void setStaffId(final BigDecimal staffId) {
		this.staffId = staffId;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(final String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public BigDecimal getOffenderSentenceHtyId() {
		return offenderSentenceHtyId;
	}

	public void setOffenderSentenceHtyId(final BigDecimal offenderSentenceHtyId) {
		this.offenderSentenceHtyId = offenderSentenceHtyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public long getSentenceEventId() {
		return sentenceEventId;
	}

	public void setSentenceEventId(long sentenceEventId) {
		this.sentenceEventId = sentenceEventId;
	}

	public OffenderSentencesHty() {
	}

	public BigDecimal getAggregateSentenceSeq() {
		return this.aggregateSentenceSeq;
	}

	public void setAggregateSentenceSeq(BigDecimal aggregateSentenceSeq) {
		this.aggregateSentenceSeq = aggregateSentenceSeq;
	}

	public String getApplicableAdjustCode() {
		return this.applicableAdjustCode;
	}

	public void setApplicableAdjustCode(String applicableAdjustCode) {
		this.applicableAdjustCode = applicableAdjustCode;
	}

	public String getAsGoodtimeType() {
		return this.asGoodtimeType;
	}

	public void setAsGoodtimeType(String asGoodtimeType) {
		this.asGoodtimeType = asGoodtimeType;
	}

	public BigDecimal getChargeSeq() {
		return this.chargeSeq;
	}

	public void setChargeSeq(BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public BigDecimal getConsecToSentenceSeq() {
		return this.consecToSentenceSeq;
	}

	public void setConsecToSentenceSeq(BigDecimal consecToSentenceSeq) {
		this.consecToSentenceSeq = consecToSentenceSeq;
	}

	public String getConsecutiveCountFlag() {
		return this.consecutiveCountFlag;
	}

	public void setConsecutiveCountFlag(String consecutiveCountFlag) {
		this.consecutiveCountFlag = consecutiveCountFlag;
	}

	public BigDecimal getCounts() {
		return this.counts;
	}

	public void setCounts(BigDecimal counts) {
		this.counts = counts;
	}

	

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	

	public String getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public BigDecimal getDefaultDays() {
		return this.defaultDays;
	}

	public void setDefaultDays(BigDecimal defaultDays) {
		this.defaultDays = defaultDays;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDischargeAuthority() {
		return this.dischargeAuthority;
	}

	public void setDischargeAuthority(BigDecimal dischargeAuthority) {
		this.dischargeAuthority = dischargeAuthority;
	}

	public String getDischargeComment() {
		return this.dischargeComment;
	}

	public void setDischargeComment(String dischargeComment) {
		this.dischargeComment = dischargeComment;
	}

	

	public String getDischargeReason() {
		return this.dischargeReason;
	}

	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public String getBreachAmend() {
		return breachAmend;
	}

	public void setBreachAmend(String breachAmend) {
		this.breachAmend = breachAmend;
	}

	public String getJurisCode() {
		return jurisCode;
	}

	public void setJurisCode(String jurisCode) {
		this.jurisCode = jurisCode;
	}

	public String getSentenceType() {
		return sentenceType;
	}

	public void setSentenceType(String sentenceType) {
		this.sentenceType = sentenceType;
	}

	public String getSentenceCalcTypeDesc() {
		return sentenceCalcTypeDesc;
	}

	public void setSentenceCalcTypeDesc(String sentenceCalcTypeDesc) {
		this.sentenceCalcTypeDesc = sentenceCalcTypeDesc;
	}

	public String getSentCalcType() {
		return sentCalcType;
	}

	public void setSentCalcType(String sentCalcType) {
		this.sentCalcType = sentCalcType;
	}

	public String getDtbfFlag() {
		return this.dtbfFlag;
	}

	public void setDtbfFlag(String dtbfFlag) {
		this.dtbfFlag = dtbfFlag;
	}

	
	public String getEksSentence() {
		return this.eksSentence;
	}

	public void setEksSentence(String eksSentence) {
		this.eksSentence = eksSentence;
	}

	

	public String getExtendedFlag() {
		return this.extendedFlag;
	}

	public void setExtendedFlag(String extendedFlag) {
		this.extendedFlag = extendedFlag;
	}

	public String getExtendingAuthority() {
		return this.extendingAuthority;
	}

	public void setExtendingAuthority(String extendingAuthority) {
		this.extendingAuthority = extendingAuthority;
	}

	public BigDecimal getFineAmount() {
		return this.fineAmount;
	}

	public void setFineAmount(BigDecimal fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getFineCommentText() {
		return this.fineCommentText;
	}

	public void setFineCommentText(String fineCommentText) {
		this.fineCommentText = fineCommentText;
	}

	public String getGapFlag() {
		return this.gapFlag;
	}

	public void setGapFlag(String gapFlag) {
		this.gapFlag = gapFlag;
	}

	public BigDecimal getGoodConductDays() {
		return this.goodConductDays;
	}

	public void setGoodConductDays(BigDecimal goodConductDays) {
		this.goodConductDays = goodConductDays;
	}

	public String getInstallmentDetails() {
		return this.installmentDetails;
	}

	public void setInstallmentDetails(String installmentDetails) {
		this.installmentDetails = installmentDetails;
	}

	public String getJurisdictionCode() {
		return this.jurisdictionCode;
	}

	public void setJurisdictionCode(String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}

	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	

	

	
	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSentCalcNeededFlag() {
		return this.sentCalcNeededFlag;
	}

	public void setSentCalcNeededFlag(String sentCalcNeededFlag) {
		this.sentCalcNeededFlag = sentCalcNeededFlag;
	}

	public String getSentClosCommentText() {
		return this.sentClosCommentText;
	}

	public void setSentClosCommentText(String sentClosCommentText) {
		this.sentClosCommentText = sentClosCommentText;
	}

	public String getSentClosReasonCode() {
		return this.sentClosReasonCode;
	}

	public void setSentClosReasonCode(String sentClosReasonCode) {
		this.sentClosReasonCode = sentClosReasonCode;
	}

	
	public String getSentenceCalcType() {
		return this.sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public Object getSentenceDate() {
		return this.sentenceDate;
	}

	
	

	
	public String getSentenceStatus() {
		return this.sentenceStatus;
	}

	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	

	public String getSupervisingAgyLocId() {
		return this.supervisingAgyLocId;
	}

	public void setSupervisingAgyLocId(String supervisingAgyLocId) {
		this.supervisingAgyLocId = supervisingAgyLocId;
	}

	

	public String getTermChangedFlag() {
		return this.termChangedFlag;
	}

	public void setTermChangedFlag(String termChangedFlag) {
		this.termChangedFlag = termChangedFlag;
	}

	public BigDecimal getTotalCompensation() {
		return this.totalCompensation;
	}

	public void setTotalCompensation(BigDecimal totalCompensation) {
		this.totalCompensation = totalCompensation;
	}

	public BigDecimal getTotalFine() {
		return this.totalFine;
	}

	public void setTotalFine(BigDecimal totalFine) {
		this.totalFine = totalFine;
	}

	public Date getNonProbationStartDate() {
		return nonProbationStartDate;
	}

	public void setNonProbationStartDate(Date nonProbationStartDate) {
		this.nonProbationStartDate = nonProbationStartDate;
	}

	public Date getSentenceExpiryDate() {
		return sentenceExpiryDate;
	}

	public void setSentenceExpiryDate(Date sentenceExpiryDate) {
		this.sentenceExpiryDate = sentenceExpiryDate;
	}

	public Date getSentenceExpiryTime() {
		return sentenceExpiryTime;
	}

	public void setSentenceExpiryTime(Date sentenceExpiryTime) {
		this.sentenceExpiryTime = sentenceExpiryTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getSupervisionExpiryDate() {
		return supervisionExpiryDate;
	}

	public void setSupervisionExpiryDate(Date supervisionExpiryDate) {
		this.supervisionExpiryDate = supervisionExpiryDate;
	}

	public void setSentenceDate(Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExtendedDate() {
		return extendedDate;
	}

	public void setExtendedDate(Date extendedDate) {
		this.extendedDate = extendedDate;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public Date getProbableReleaseDate() {
		return probableReleaseDate;
	}

	public void setProbableReleaseDate(Date probableReleaseDate) {
		this.probableReleaseDate = probableReleaseDate;
	}

	public Date getProbableReleaseTime() {
		return probableReleaseTime;
	}

	public void setProbableReleaseTime(Date probableReleaseTime) {
		this.probableReleaseTime = probableReleaseTime;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getReportDueDate() {
		return reportDueDate;
	}

	public void setReportDueDate(Date reportDueDate) {
		this.reportDueDate = reportDueDate;
	}

	public Date getRevocationDate() {
		return revocationDate;
	}

	public void setRevocationDate(Date revocationDate) {
		this.revocationDate = revocationDate;
	}

	public Date getSentClosedDate() {
		return sentClosedDate;
	}

	public void setSentClosedDate(Date sentClosedDate) {
		this.sentClosedDate = sentClosedDate;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(final String staffName) {
		this.staffName = staffName;
	}

}
