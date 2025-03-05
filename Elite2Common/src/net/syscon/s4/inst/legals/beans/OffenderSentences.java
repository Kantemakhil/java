package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_SENTENCES database table.
 * 
 */
public class OffenderSentences extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("aggSentenceSeq")
	private BigDecimal aggSentenceSeq;
	@JsonProperty("aggregateAdjustDays")
	private BigDecimal aggregateAdjustDays;
	@JsonProperty("aggregateTerm")
	private BigDecimal aggregateTerm;
	@JsonProperty("apdCalculatedDate")
	private Date apdCalculatedDate;
	@JsonProperty("ardCalculatedDate")
	private Date ardCalculatedDate;
	@JsonProperty("breachLevel")
	private BigDecimal breachLevel;
	@JsonProperty("caseId")
	private BigDecimal caseId;
	@JsonProperty("chargeSeq")
	private BigDecimal chargeSeq;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("consecToSentenceSeq")
	private BigDecimal consecToSentenceSeq;
	@JsonProperty("counts")
	private BigDecimal counts;
	@JsonProperty("crdCalculatedDate")
	private Date crdCalculatedDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("dischargeDate")
	private Date dischargeDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("etdCalculatedDate")
	private Date etdCalculatedDate;
	@JsonProperty("extendedDays")
	private BigDecimal extendedDays;
	@JsonProperty("fineAmount")
	private BigDecimal fineAmount;
	@JsonProperty("hdcedCalculatedDate")
	private Date hdcedCalculatedDate;
	@JsonProperty("ledCalculatedDate")
	private Date ledCalculatedDate;
	@JsonProperty("lineSeq")
	private BigDecimal lineSeq;
	@JsonProperty("ltdCalculatedDate")
	private Date ltdCalculatedDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("mtdCalculatedDate")
	private Date mtdCalculatedDate;
	@JsonProperty("noOfUnexcusedAbsence")
	private BigDecimal noOfUnexcusedAbsence;
	@JsonProperty("nomconcwithsentdetailref")
	private BigDecimal nomconcwithsentdetailref;
	@JsonProperty("nomconsfromsentdetailref")
	private BigDecimal nomconsfromsentdetailref;
	@JsonProperty("nomconstosentdetailref")
	private BigDecimal nomconstosentdetailref;
	@JsonProperty("nomsentdetailref")
	private BigDecimal nomsentdetailref;
	@JsonProperty("npdCalculatedDate")
	private Date npdCalculatedDate;
	@JsonProperty("orderId")
	private BigDecimal orderId;
	@JsonProperty("pedCalculatedDate")
	private Date pedCalculatedDate;
	@JsonProperty("prrdCalculatedDate")
	private Date prrdCalculatedDate;
	@JsonProperty("revokedDate")
	private Date revokedDate;
	@JsonProperty("revokedStaffId")
	private BigDecimal revokedStaffId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("sedCalculatedDate")
	private Date sedCalculatedDate;
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	@JsonProperty("sentenceCategory")
	private String sentenceCategory;
	@JsonProperty("sentenceLevel")
	private String sentenceLevel;
	@JsonProperty("sentenceStatus")
	private String sentenceStatus;
	@JsonProperty("sentenceText")
	private String sentenceText;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("statusUpdateComment")
	private String statusUpdateComment;
	@JsonProperty("statusUpdateDate")
	private Date statusUpdateDate;
	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;
	@JsonProperty("statusUpdateStaffId")
	private BigDecimal statusUpdateStaffId;
	@JsonProperty("supervisionExpiryDate")
	private Date supervisionExpiryDate;
	@JsonProperty("tariffCalculatedDate")
	private Date tariffCalculatedDate;
	@JsonProperty("terminationDate")
	private Date terminationDate;
	@JsonProperty("terminationReason")
	private String terminationReason;
	@JsonProperty("workflowId")
	private BigDecimal workflowId;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("sentenceSeq")
	private long sentenceSeq;
	@JsonProperty("version")
	private BigDecimal version;
	@JsonProperty("breachAmend")
	private String breachAmend;
	@JsonProperty("jurisCode")
	private String jurisCode;
	@JsonProperty("sentenceType")
	private String sentenceType;
	@JsonProperty("orderType")
	private String orderType;
	@JsonProperty("orderCode")
	private String orderCode;
	@JsonProperty("sentenceCalcTypeDesc")
	private String sentenceCalcTypeDesc;
	@JsonProperty("registrationDate")
	private Date registrationDate;
	@JsonProperty("sentenceExpiryDate")
	private Date sentenceExpiryDate;
	@JsonProperty("description")
	private String description;
	@JsonProperty("extendedDate")
	private Date extendedDate;
	@JsonProperty("extendedFlag")
	private String extendedFlag;
	@JsonProperty("extendingAuthority")
	private String extendingAuthority;
	@JsonProperty("installmentDetails")
	private String installmentDetails;
	@JsonProperty("totalCompensation")
	private BigDecimal totalCompensation;
	@JsonProperty("totalFine")
	private BigDecimal totalFine;
	@JsonProperty("sentCalcType")
	private String sentCalcType;
	@JsonProperty("nonProbationStartDate")
	private Date nonProbationStartDate;
	@JsonProperty("sentenceCalcDesc")
	private String sentenceCalcDesc;
	@JsonProperty("workHours")
	private Long workHours;
	
	public String getSentenceCalcDesc() {
		return sentenceCalcDesc;
	}

	public void setSentenceCalcDesc(final String sentenceCalcDesc) {
		this.sentenceCalcDesc = sentenceCalcDesc;
	}

	public Long getWorkHours() {
		return workHours;
	}

	public void setWorkHours(final Long workHours) {
		this.workHours = workHours;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(final long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public OffenderSentences() {
		// OffenderSentences
	}

	public BigDecimal getAggSentenceSeq() {
		return this.aggSentenceSeq;
	}

	public void setAggSentenceSeq(final BigDecimal aggSentenceSeq) {
		this.aggSentenceSeq = aggSentenceSeq;
	}

	public BigDecimal getAggregateAdjustDays() {
		return this.aggregateAdjustDays;
	}

	public void setAggregateAdjustDays(final BigDecimal aggregateAdjustDays) {
		this.aggregateAdjustDays = aggregateAdjustDays;
	}

	public BigDecimal getAggregateTerm() {
		return this.aggregateTerm;
	}

	public void setAggregateTerm(final BigDecimal aggregateTerm) {
		this.aggregateTerm = aggregateTerm;
	}

	public Date getApdCalculatedDate() {
		return this.apdCalculatedDate;
	}

	public void setApdCalculatedDate(final Date apdCalculatedDate) {
		this.apdCalculatedDate = apdCalculatedDate;
	}

	public Date getArdCalculatedDate() {
		return this.ardCalculatedDate;
	}

	public void setArdCalculatedDate(final Date ardCalculatedDate) {
		this.ardCalculatedDate = ardCalculatedDate;
	}

	public BigDecimal getBreachLevel() {
		return this.breachLevel;
	}

	public void setBreachLevel(final BigDecimal breachLevel) {
		this.breachLevel = breachLevel;
	}

	public BigDecimal getCaseId() {
		return this.caseId;
	}

	public void setCaseId(final BigDecimal caseId) {
		this.caseId = caseId;
	}

	public BigDecimal getChargeSeq() {
		return this.chargeSeq;
	}

	public void setChargeSeq(final BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public BigDecimal getConsecToSentenceSeq() {
		return this.consecToSentenceSeq;
	}

	public void setConsecToSentenceSeq(final BigDecimal consecToSentenceSeq) {
		this.consecToSentenceSeq = consecToSentenceSeq;
	}

	public BigDecimal getCounts() {
		return this.counts;
	}

	public void setCounts(final BigDecimal counts) {
		this.counts = counts;
	}

	public Date getCrdCalculatedDate() {
		return this.crdCalculatedDate;
	}

	public void setCrdCalculatedDate(final Date crdCalculatedDate) {
		this.crdCalculatedDate = crdCalculatedDate;
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

	public Date getDischargeDate() {
		return this.dischargeDate;
	}

	public void setDischargeDate(final Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public Date getEtdCalculatedDate() {
		return this.etdCalculatedDate;
	}

	public void setEtdCalculatedDate(final Date etdCalculatedDate) {
		this.etdCalculatedDate = etdCalculatedDate;
	}

	public BigDecimal getExtendedDays() {
		return this.extendedDays;
	}

	public void setExtendedDays(final BigDecimal extendedDays) {
		this.extendedDays = extendedDays;
	}

	public String getSentCalcType() {
		return sentCalcType;
	}

	public void setSentCalcType(final String sentCalcType) {
		this.sentCalcType = sentCalcType;
	}

	public BigDecimal getFineAmount() {
		return this.fineAmount;
	}

	public void setFineAmount(final BigDecimal fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Date getHdcedCalculatedDate() {
		return this.hdcedCalculatedDate;
	}

	public void setHdcedCalculatedDate(final Date hdcedCalculatedDate) {
		this.hdcedCalculatedDate = hdcedCalculatedDate;
	}

	public Date getLedCalculatedDate() {
		return this.ledCalculatedDate;
	}

	public void setLedCalculatedDate(final Date ledCalculatedDate) {
		this.ledCalculatedDate = ledCalculatedDate;
	}

	public BigDecimal getLineSeq() {
		return this.lineSeq;
	}

	public void setLineSeq(final BigDecimal lineSeq) {
		this.lineSeq = lineSeq;
	}

	public Date getNonProbationStartDate() {
		return nonProbationStartDate;
	}

	public void setNonProbationStartDate(final Date nonProbationStartDate) {
		this.nonProbationStartDate = nonProbationStartDate;
	}

	public Date getLtdCalculatedDate() {
		return this.ltdCalculatedDate;
	}

	public void setLtdCalculatedDate(final Date ltdCalculatedDate) {
		this.ltdCalculatedDate = ltdCalculatedDate;
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

	public Date getMtdCalculatedDate() {
		return this.mtdCalculatedDate;
	}

	public void setMtdCalculatedDate(final Date mtdCalculatedDate) {
		this.mtdCalculatedDate = mtdCalculatedDate;
	}

	public BigDecimal getNoOfUnexcusedAbsence() {
		return this.noOfUnexcusedAbsence;
	}

	public void setNoOfUnexcusedAbsence(final BigDecimal noOfUnexcusedAbsence) {
		this.noOfUnexcusedAbsence = noOfUnexcusedAbsence;
	}

	public BigDecimal getNomconcwithsentdetailref() {
		return this.nomconcwithsentdetailref;
	}

	public void setNomconcwithsentdetailref(final BigDecimal nomconcwithsentdetailref) {
		this.nomconcwithsentdetailref = nomconcwithsentdetailref;
	}

	public BigDecimal getNomconsfromsentdetailref() {
		return this.nomconsfromsentdetailref;
	}

	public void setNomconsfromsentdetailref(final BigDecimal nomconsfromsentdetailref) {
		this.nomconsfromsentdetailref = nomconsfromsentdetailref;
	}

	public BigDecimal getNomconstosentdetailref() {
		return this.nomconstosentdetailref;
	}

	public void setNomconstosentdetailref(final BigDecimal nomconstosentdetailref) {
		this.nomconstosentdetailref = nomconstosentdetailref;
	}

	public BigDecimal getNomsentdetailref() {
		return this.nomsentdetailref;
	}

	public void setNomsentdetailref(final BigDecimal nomsentdetailref) {
		this.nomsentdetailref = nomsentdetailref;
	}

	public Date getNpdCalculatedDate() {
		return this.npdCalculatedDate;
	}

	public void setNpdCalculatedDate(final Date npdCalculatedDate) {
		this.npdCalculatedDate = npdCalculatedDate;
	}

	public BigDecimal getOrderId() {
		return this.orderId;
	}

	public void setOrderId(final BigDecimal orderId) {
		this.orderId = orderId;
	}

	public Date getPedCalculatedDate() {
		return this.pedCalculatedDate;
	}

	public void setPedCalculatedDate(final Date pedCalculatedDate) {
		this.pedCalculatedDate = pedCalculatedDate;
	}

	public Date getPrrdCalculatedDate() {
		return this.prrdCalculatedDate;
	}

	public void setPrrdCalculatedDate(final Date prrdCalculatedDate) {
		this.prrdCalculatedDate = prrdCalculatedDate;
	}

	public Date getRevokedDate() {
		return this.revokedDate;
	}

	public void setRevokedDate(final Date revokedDate) {
		this.revokedDate = revokedDate;
	}

	public BigDecimal getRevokedStaffId() {
		return this.revokedStaffId;
	}

	public void setRevokedStaffId(final BigDecimal revokedStaffId) {
		this.revokedStaffId = revokedStaffId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getSedCalculatedDate() {
		return this.sedCalculatedDate;
	}

	public void setSedCalculatedDate(final Date sedCalculatedDate) {
		this.sedCalculatedDate = sedCalculatedDate;
	}

	public String getSentenceCalcType() {
		return this.sentenceCalcType;
	}

	public void setSentenceCalcType(final String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceCategory() {
		return this.sentenceCategory;
	}

	public void setSentenceCategory(final String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceLevel() {
		return this.sentenceLevel;
	}

	public void setSentenceLevel(final String sentenceLevel) {
		this.sentenceLevel = sentenceLevel;
	}

	public String getSentenceStatus() {
		return this.sentenceStatus;
	}

	public void setSentenceStatus(final String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getSentenceText() {
		return this.sentenceText;
	}

	public void setSentenceText(final String sentenceText) {
		this.sentenceText = sentenceText;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public String getStatusUpdateComment() {
		return this.statusUpdateComment;
	}

	public void setStatusUpdateComment(final String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	public Date getStatusUpdateDate() {
		return this.statusUpdateDate;
	}

	public void setStatusUpdateDate(final Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public String getStatusUpdateReason() {
		return this.statusUpdateReason;
	}

	public void setStatusUpdateReason(final String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public BigDecimal getStatusUpdateStaffId() {
		return this.statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(final BigDecimal statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public Date getSupervisionExpiryDate() {
		return this.supervisionExpiryDate;
	}

	public void setSupervisionExpiryDate(final Date supervisionExpiryDate) {
		this.supervisionExpiryDate = supervisionExpiryDate;
	}

	public Date getTariffCalculatedDate() {
		return this.tariffCalculatedDate;
	}

	public void setTariffCalculatedDate(final Date tariffCalculatedDate) {
		this.tariffCalculatedDate = tariffCalculatedDate;
	}

	public Date getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(final Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTerminationReason() {
		return this.terminationReason;
	}

	public void setTerminationReason(final String terminationReason) {
		this.terminationReason = terminationReason;
	}

	public BigDecimal getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(final BigDecimal workflowId) {
		this.workflowId = workflowId;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(final BigDecimal version) {
		this.version = version;
	}

	public String getBreachAmend() {
		return breachAmend;
	}

	public void setBreachAmend(final String breachAmend) {
		this.breachAmend = breachAmend;
	}

	public String getJurisCode() {
		return jurisCode;
	}

	public void setJurisCode(final String jurisCode) {
		this.jurisCode = jurisCode;
	}

	public String getSentenceType() {
		return sentenceType;
	}

	public void setSentenceType(final String sentenceType) {
		this.sentenceType = sentenceType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(final String orderType) {
		this.orderType = orderType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(final String orderCode) {
		this.orderCode = orderCode;
	}

	public String getSentenceCalcTypeDesc() {
		return sentenceCalcTypeDesc;
	}

	public void setSentenceCalcTypeDesc(final String sentenceCalcTypeDesc) {
		this.sentenceCalcTypeDesc = sentenceCalcTypeDesc;
	}

	public Date getSentenceExpiryDate() {
		return sentenceExpiryDate;
	}

	public void setSentenceExpiryDate(final Date sentenceExpiryDate) {
		this.sentenceExpiryDate = sentenceExpiryDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(final Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getExtendedDate() {
		return extendedDate;
	}

	public void setExtendedDate(final Date extendedDate) {
		this.extendedDate = extendedDate;
	}

	public String getExtendedFlag() {
		return extendedFlag;
	}

	public void setExtendedFlag(final String extendedFlag) {
		this.extendedFlag = extendedFlag;
	}

	public String getExtendingAuthority() {
		return extendingAuthority;
	}

	public void setExtendingAuthority(final String extendingAuthority) {
		this.extendingAuthority = extendingAuthority;
	}

	public String getInstallmentDetails() {
		return installmentDetails;
	}

	public void setInstallmentDetails(final String installmentDetails) {
		this.installmentDetails = installmentDetails;
	}

	public BigDecimal getTotalCompensation() {
		return totalCompensation;
	}

	public void setTotalCompensation(final BigDecimal totalCompensation) {
		this.totalCompensation = totalCompensation;
	}

	public BigDecimal getTotalFine() {
		return totalFine;
	}

	public void setTotalFine(final BigDecimal totalFine) {
		this.totalFine = totalFine;
	}
}
