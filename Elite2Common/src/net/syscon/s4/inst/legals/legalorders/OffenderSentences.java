package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentences extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("sentenceSeq")
	private long sentenceSeq;
	@JsonProperty("orderId")
	private long orderId;
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	@JsonProperty("sentenceStatus")
	private String sentenceStatus;
	@JsonProperty("consecToSentenceSeq")
	private long consecToSentenceSeq;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("terminationReason")
	private String terminationReason;
	@JsonProperty("noOfUnexcusedAbsence")
	private long noOfUnexcusedAbsence;
	@JsonProperty("caseId")
	private long caseId;
	@JsonProperty("etdCalculatedDate")
	private Date etdCalculatedDate;
	@JsonProperty("mtdCalculatedDate")
	private Date mtdCalculatedDate;
	@JsonProperty("ltdCalculatedDate")
	private Date ltdCalculatedDate;
	@JsonProperty("ardCalculatedDate")
	private Date ardCalculatedDate;
	@JsonProperty("crdCalculatedDate")
	private Date crdCalculatedDate;
	@JsonProperty("pedCalculatedDate")
	private Date pedCalculatedDate;
	@JsonProperty("apdCalculatedDate")
	private Date apdCalculatedDate;
	@JsonProperty("npdCalculatedDate")
	private Date npdCalculatedDate;
	@JsonProperty("ledCalculatedDate")
	private Date ledCalculatedDate;
	@JsonProperty("sedCalculatedDate")
	private Date sedCalculatedDate;
	@JsonProperty("prrdCalculatedDate")
	private Date prrdCalculatedDate;
	@JsonProperty("tariffCalculatedDate")
	private Date tariffCalculatedDate;
	@JsonProperty("aggSentenceSeq")
	private long aggSentenceSeq;
	@JsonProperty("sentenceCategory")
	private String sentenceCategory;
	@JsonProperty("fineAmount")
	private BigDecimal fineAmount;
	@JsonProperty("hdcedCalculatedDate")
	private Date hdcedCalculatedDate;
	@JsonProperty("sentenceText")
	private String sentenceText;
	@JsonProperty("revokedDate")
	private Date revokedDate;
	@JsonProperty("revokedStaffId")
	private long revokedStaffId;
	@JsonProperty("breachLevel")
	private long breachLevel;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("terminationDate")
	private Date terminationDate;
	@JsonProperty("aggregateTerm")
	private long aggregateTerm;
	@JsonProperty("aggregateAdjustDays")
	private long aggregateAdjustDays;
	@JsonProperty("sentenceLevel")
	private String sentenceLevel;
	@JsonProperty("extendedDays")
	private long extendedDays;
	@JsonProperty("counts")
	private long counts;
	@JsonProperty("dischargeDate")
	private Date dischargeDate;
	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;
	@JsonProperty("statusUpdateComment")
	private String statusUpdateComment;
	@JsonProperty("statusUpdateDate")
	private Date statusUpdateDate;
	@JsonProperty("statusUpdateStaffId")
	private long statusUpdateStaffId;
	@JsonProperty("chargeSeq")
	private long chargeSeq;
	@JsonProperty("supervisionExpiryDate")
	private Date supervisionExpiryDate;
	@JsonProperty("lineSeq")
	private long lineSeq;
	@JsonProperty("nomsentdetailref")
	private long nomsentdetailref;
	@JsonProperty("nomconstosentdetailref")
	private long nomconstosentdetailref;
	@JsonProperty("nomconsfromsentdetailref")
	private long nomconsfromsentdetailref;
	@JsonProperty("nomconcwithsentdetailref")
	private long nomconcwithsentdetailref;
	@JsonProperty("workflowId")
	private long workflowId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("pFlag")
	private String pFlag;
	@JsonProperty("pCount")
	private long pCount;
	
	@JsonProperty("nbtVer")
	private BigDecimal nbtVer;
	@JsonProperty("nbtJurOrder")
	private String nbtJurOrder;
	@JsonProperty("nbtSentOrdType")
	private long nbtSentOrdType;
	@JsonProperty("nbtOmType")
	private String nbtOmType;
	@JsonProperty("nbtSentenceStatus")
	private long nbtSentenceStatus;
	@JsonProperty("nbtApply")
	private long nbtApply;
	@JsonProperty("jurisdictionCode")
	private String jurisdictionCode;
	@JsonProperty("orderCode")
	private String orderCode;
	@JsonProperty("orderType")
	private String orderType;
	@JsonProperty("nbtSentCalcType")
	private String nbtSentCalcType;
	
	
	
	public String getNbtSentCalcType() {
		return nbtSentCalcType;
	}

	public void setNbtSentCalcType(String nbtSentCalcType) {
		this.nbtSentCalcType = nbtSentCalcType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getJurisdictionCode() {
		return jurisdictionCode;
	}

	public void setJurisdictionCode(String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}

	public BigDecimal getNbtVer() {
		return nbtVer;
	}

	public void setNbtVer(BigDecimal nbtVer) {
		this.nbtVer = nbtVer;
	}

	public String getNbtJurOrder() {
		return nbtJurOrder;
	}

	public void setNbtJurOrder(String nbtJurOrder) {
		this.nbtJurOrder = nbtJurOrder;
	}

	public long getNbtSentOrdType() {
		return nbtSentOrdType;
	}

	public void setNbtSentOrdType(long nbtSentOrdType) {
		this.nbtSentOrdType = nbtSentOrdType;
	}

	public String getNbtOmType() {
		return nbtOmType;
	}

	public void setNbtOmType(String nbtOmType) {
		this.nbtOmType = nbtOmType;
	}

	public long getNbtSentenceStatus() {
		return nbtSentenceStatus;
	}

	public void setNbtSentenceStatus(long nbtSentenceStatus) {
		this.nbtSentenceStatus = nbtSentenceStatus;
	}

	public long getNbtApply() {
		return nbtApply;
	}

	public void setNbtApply(long nbtApply) {
		this.nbtApply = nbtApply;
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceStatus() {
		return sentenceStatus;
	}

	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public long getConsecToSentenceSeq() {
		return consecToSentenceSeq;
	}

	public void setConsecToSentenceSeq(long consecToSentenceSeq) {
		this.consecToSentenceSeq = consecToSentenceSeq;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getTerminationReason() {
		return terminationReason;
	}

	public void setTerminationReason(String terminationReason) {
		this.terminationReason = terminationReason;
	}

	public long getNoOfUnexcusedAbsence() {
		return noOfUnexcusedAbsence;
	}

	public void setNoOfUnexcusedAbsence(long noOfUnexcusedAbsence) {
		this.noOfUnexcusedAbsence = noOfUnexcusedAbsence;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public Date getEtdCalculatedDate() {
		return etdCalculatedDate;
	}

	public void setEtdCalculatedDate(Date etdCalculatedDate) {
		this.etdCalculatedDate = etdCalculatedDate;
	}

	public Date getMtdCalculatedDate() {
		return mtdCalculatedDate;
	}

	public void setMtdCalculatedDate(Date mtdCalculatedDate) {
		this.mtdCalculatedDate = mtdCalculatedDate;
	}

	public Date getLtdCalculatedDate() {
		return ltdCalculatedDate;
	}

	public void setLtdCalculatedDate(Date ltdCalculatedDate) {
		this.ltdCalculatedDate = ltdCalculatedDate;
	}

	public Date getArdCalculatedDate() {
		return ardCalculatedDate;
	}

	public void setArdCalculatedDate(Date ardCalculatedDate) {
		this.ardCalculatedDate = ardCalculatedDate;
	}

	public Date getCrdCalculatedDate() {
		return crdCalculatedDate;
	}

	public void setCrdCalculatedDate(Date crdCalculatedDate) {
		this.crdCalculatedDate = crdCalculatedDate;
	}

	public Date getPedCalculatedDate() {
		return pedCalculatedDate;
	}

	public void setPedCalculatedDate(Date pedCalculatedDate) {
		this.pedCalculatedDate = pedCalculatedDate;
	}

	public Date getApdCalculatedDate() {
		return apdCalculatedDate;
	}

	public void setApdCalculatedDate(Date apdCalculatedDate) {
		this.apdCalculatedDate = apdCalculatedDate;
	}

	public Date getNpdCalculatedDate() {
		return npdCalculatedDate;
	}

	public void setNpdCalculatedDate(Date npdCalculatedDate) {
		this.npdCalculatedDate = npdCalculatedDate;
	}

	public Date getLedCalculatedDate() {
		return ledCalculatedDate;
	}

	public void setLedCalculatedDate(Date ledCalculatedDate) {
		this.ledCalculatedDate = ledCalculatedDate;
	}

	public Date getSedCalculatedDate() {
		return sedCalculatedDate;
	}

	public void setSedCalculatedDate(Date sedCalculatedDate) {
		this.sedCalculatedDate = sedCalculatedDate;
	}

	public Date getPrrdCalculatedDate() {
		return prrdCalculatedDate;
	}

	public void setPrrdCalculatedDate(Date prrdCalculatedDate) {
		this.prrdCalculatedDate = prrdCalculatedDate;
	}

	public Date getTariffCalculatedDate() {
		return tariffCalculatedDate;
	}

	public void setTariffCalculatedDate(Date tariffCalculatedDate) {
		this.tariffCalculatedDate = tariffCalculatedDate;
	}

	public long getAggSentenceSeq() {
		return aggSentenceSeq;
	}

	public void setAggSentenceSeq(long aggSentenceSeq) {
		this.aggSentenceSeq = aggSentenceSeq;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public BigDecimal getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(BigDecimal fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Date getHdcedCalculatedDate() {
		return hdcedCalculatedDate;
	}

	public void setHdcedCalculatedDate(Date hdcedCalculatedDate) {
		this.hdcedCalculatedDate = hdcedCalculatedDate;
	}

	public String getSentenceText() {
		return sentenceText;
	}

	public void setSentenceText(String sentenceText) {
		this.sentenceText = sentenceText;
	}

	public Date getRevokedDate() {
		return revokedDate;
	}

	public void setRevokedDate(Date revokedDate) {
		this.revokedDate = revokedDate;
	}

	public long getRevokedStaffId() {
		return revokedStaffId;
	}

	public void setRevokedStaffId(long revokedStaffId) {
		this.revokedStaffId = revokedStaffId;
	}

	public long getBreachLevel() {
		return breachLevel;
	}

	public void setBreachLevel(long breachLevel) {
		this.breachLevel = breachLevel;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public long getAggregateTerm() {
		return aggregateTerm;
	}

	public void setAggregateTerm(long aggregateTerm) {
		this.aggregateTerm = aggregateTerm;
	}

	public long getAggregateAdjustDays() {
		return aggregateAdjustDays;
	}

	public void setAggregateAdjustDays(long aggregateAdjustDays) {
		this.aggregateAdjustDays = aggregateAdjustDays;
	}

	public String getSentenceLevel() {
		return sentenceLevel;
	}

	public void setSentenceLevel(String sentenceLevel) {
		this.sentenceLevel = sentenceLevel;
	}

	public long getExtendedDays() {
		return extendedDays;
	}

	public void setExtendedDays(long extendedDays) {
		this.extendedDays = extendedDays;
	}

	public long getCounts() {
		return counts;
	}

	public void setCounts(long counts) {
		this.counts = counts;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public String getStatusUpdateComment() {
		return statusUpdateComment;
	}

	public void setStatusUpdateComment(String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public long getStatusUpdateStaffId() {
		return statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(long statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public long getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(long chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public Date getSupervisionExpiryDate() {
		return supervisionExpiryDate;
	}

	public void setSupervisionExpiryDate(Date supervisionExpiryDate) {
		this.supervisionExpiryDate = supervisionExpiryDate;
	}

	public long getLineSeq() {
		return lineSeq;
	}

	public void setLineSeq(long lineSeq) {
		this.lineSeq = lineSeq;
	}

	public long getNomsentdetailref() {
		return nomsentdetailref;
	}

	public void setNomsentdetailref(long nomsentdetailref) {
		this.nomsentdetailref = nomsentdetailref;
	}

	public long getNomconstosentdetailref() {
		return nomconstosentdetailref;
	}

	public void setNomconstosentdetailref(long nomconstosentdetailref) {
		this.nomconstosentdetailref = nomconstosentdetailref;
	}

	public long getNomconsfromsentdetailref() {
		return nomconsfromsentdetailref;
	}

	public void setNomconsfromsentdetailref(long nomconsfromsentdetailref) {
		this.nomconsfromsentdetailref = nomconsfromsentdetailref;
	}

	public long getNomconcwithsentdetailref() {
		return nomconcwithsentdetailref;
	}

	public void setNomconcwithsentdetailref(long nomconcwithsentdetailref) {
		this.nomconcwithsentdetailref = nomconcwithsentdetailref;
	}

	public long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(long workflowId) {
		this.workflowId = workflowId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getpFlag() {
		return pFlag;
	}

	public void setpFlag(String pFlag) {
		this.pFlag = pFlag;
	}

	public long getpCount() {
		return pCount;
	}

	public void setpCount(long pCount) {
		this.pCount = pCount;
	}

	@Override
	public String toString() {
		return "OffenderSentences [offenderBookId=" + offenderBookId + ", sentenceSeq=" + sentenceSeq + ", orderId="
				+ orderId + ", sentenceCalcType=" + sentenceCalcType + ", sentenceStatus=" + sentenceStatus
				+ ", consecToSentenceSeq=" + consecToSentenceSeq + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", commentText=" + commentText + ", terminationReason=" + terminationReason
				+ ", noOfUnexcusedAbsence=" + noOfUnexcusedAbsence + ", caseId=" + caseId + ", etdCalculatedDate="
				+ etdCalculatedDate + ", mtdCalculatedDate=" + mtdCalculatedDate + ", ltdCalculatedDate="
				+ ltdCalculatedDate + ", ardCalculatedDate=" + ardCalculatedDate + ", crdCalculatedDate="
				+ crdCalculatedDate + ", pedCalculatedDate=" + pedCalculatedDate + ", apdCalculatedDate="
				+ apdCalculatedDate + ", npdCalculatedDate=" + npdCalculatedDate + ", ledCalculatedDate="
				+ ledCalculatedDate + ", sedCalculatedDate=" + sedCalculatedDate + ", prrdCalculatedDate="
				+ prrdCalculatedDate + ", tariffCalculatedDate=" + tariffCalculatedDate + ", aggSentenceSeq="
				+ aggSentenceSeq + ", sentenceCategory=" + sentenceCategory + ", fineAmount=" + fineAmount
				+ ", hdcedCalculatedDate=" + hdcedCalculatedDate + ", sentenceText=" + sentenceText + ", revokedDate="
				+ revokedDate + ", revokedStaffId=" + revokedStaffId + ", breachLevel=" + breachLevel
				+ ", createDatetime=" + createDatetime + ", createUserId=" + createUserId + ", modifyDatetime="
				+ modifyDatetime + ", modifyUserId=" + modifyUserId + ", terminationDate=" + terminationDate
				+ ", aggregateTerm=" + aggregateTerm + ", aggregateAdjustDays=" + aggregateAdjustDays
				+ ", sentenceLevel=" + sentenceLevel + ", extendedDays=" + extendedDays + ", counts=" + counts
				+ ", dischargeDate=" + dischargeDate + ", statusUpdateReason=" + statusUpdateReason
				+ ", statusUpdateComment=" + statusUpdateComment + ", statusUpdateDate=" + statusUpdateDate
				+ ", statusUpdateStaffId=" + statusUpdateStaffId + ", chargeSeq=" + chargeSeq
				+ ", supervisionExpiryDate=" + supervisionExpiryDate + ", lineSeq=" + lineSeq + ", nomsentdetailref="
				+ nomsentdetailref + ", nomconstosentdetailref=" + nomconstosentdetailref
				+ ", nomconsfromsentdetailref=" + nomconsfromsentdetailref + ", nomconcwithsentdetailref="
				+ nomconcwithsentdetailref + ", workflowId=" + workflowId + ", sealFlag=" + sealFlag + ", pFlag="
				+ pFlag + ", pCount=" + pCount + "]";
	}

}
