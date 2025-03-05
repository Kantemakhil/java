package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderProceedingSents  extends BaseModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	
	private BigDecimal offenderProceedingId	;
	private BigDecimal offenderBookId	;
	private String proceedingType	;
	private String proceedingAgyLocId	;
	private String commentText	;
	private String proceedingStatus	;
	private Date outcomeDate	;
	private String proceedingSentenceFlag	;
	private BigDecimal sentenceSeq	;
	private String sentenceCalcType;
	private String sentenceCategory	;
	private String sentenceCategoryDesc;
	private String sentenceDesc	;
	private BigDecimal noOfUnexcusedAbsence	;
	private Date startDate	;
	private Date endDate	;
	private BigDecimal caseId;
	private String caseInfoPrefix;
	private String caseInfoNumber;
	private String caseCourtDesc;
	private BigDecimal eventId;
	
	private String resultCode;
	private String resltDescription;
	private String dispositionCode;
	private Date createDatetime	;
	private String createUserId	;
	private Date modifyDatetime	;
	private String modifyUserId	;
	private String sealFlag;
	
	public BigDecimal getOffenderProceedingId() {
		return offenderProceedingId;
	}
	public void setOffenderProceedingId(BigDecimal offenderProceedingId) {
		this.offenderProceedingId = offenderProceedingId;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getProceedingType() {
		return proceedingType;
	}
	public void setProceedingType(String proceedingType) {
		this.proceedingType = proceedingType;
	}
	public String getProceedingAgyLocId() {
		return proceedingAgyLocId;
	}
	public void setProceedingAgyLocId(String proceedingAgyLocId) {
		this.proceedingAgyLocId = proceedingAgyLocId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getProceedingStatus() {
		return proceedingStatus;
	}
	public void setProceedingStatus(String proceedingStatus) {
		this.proceedingStatus = proceedingStatus;
	}
	public Date getOutcomeDate() {
		return outcomeDate;
	}
	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}
	public String getProceedingSentenceFlag() {
		return proceedingSentenceFlag;
	}
	public void setProceedingSentenceFlag(String proceedingSentenceFlag) {
		this.proceedingSentenceFlag = proceedingSentenceFlag;
	}
	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	public String getSentenceCalcType() {
		return sentenceCalcType;
	}
	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}
	public String getSentenceCategory() {
		return sentenceCategory;
	}
	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}
	public String getSentenceCategoryDesc() {
		return sentenceCategoryDesc;
	}
	public void setSentenceCategoryDesc(String sentenceCategoryDesc) {
		this.sentenceCategoryDesc = sentenceCategoryDesc;
	}
	public String getSentenceDesc() {
		return sentenceDesc;
	}
	public void setSentenceDesc(String sentenceDesc) {
		this.sentenceDesc = sentenceDesc;
	}
	public BigDecimal getNoOfUnexcusedAbsence() {
		return noOfUnexcusedAbsence;
	}
	public void setNoOfUnexcusedAbsence(BigDecimal noOfUnexcusedAbsence) {
		this.noOfUnexcusedAbsence = noOfUnexcusedAbsence;
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
	public BigDecimal getCaseId() {
		return caseId;
	}
	public void setCaseId(BigDecimal caseId) {
		this.caseId = caseId;
	}
	public String getCaseInfoPrefix() {
		return caseInfoPrefix;
	}
	public void setCaseInfoPrefix(String caseInfoPrefix) {
		this.caseInfoPrefix = caseInfoPrefix;
	}
	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}
	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}
	public String getCaseCourtDesc() {
		return caseCourtDesc;
	}
	public void setCaseCourtDesc(String caseCourtDesc) {
		this.caseCourtDesc = caseCourtDesc;
	}
	public BigDecimal getEventId() {
		return eventId;
	}
	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResltDescription() {
		return resltDescription;
	}
	public void setResltDescription(String resltDescription) {
		this.resltDescription = resltDescription;
	}
	public String getDispositionCode() {
		return dispositionCode;
	}
	public void setDispositionCode(String dispositionCode) {
		this.dispositionCode = dispositionCode;
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
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	
}
