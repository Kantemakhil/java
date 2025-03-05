package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VOffenderSentCondActs implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String recordSource	;
	
	private String conditionStatus	;
	
	private BigDecimal eventId	;
	
	private String courtAgyLocId	;
	
	private String courtName	;
	
	private BigDecimal orderId	;
	
	private BigDecimal offenderBookId	;
	
	private BigDecimal sentenceSeq	;
	
	private String sentenceCalcType	;
	
	private String sentenceCategory	;
	
	private String sentenceStatus	;
	
	private Date sentenceStartDate;
	
	private Date sentenceEndDate;
	
	private String sentenceStatusDesc;
	
	private String sentenceProgramMethod	;
	
	private String sentenceDesc	;
	
	private BigDecimal offenderSentConditionId	;
	
	private Date conditionStartDate;
	
	private Date conditionEndDate	;
	
	private String commConditionType	;
	
	private String commConditionCode	;
	
	private String commProgramMethod	;
	
	private BigDecimal length;	
	
	private String lengthUnit	;
	
	private String conditionLength	;
	
	private BigDecimal creditedUnits	;
	
	private String activityDesc	;
	
	private String activityCode	;
	
	private String conditionDesc	;
	
	private String sentCondDesc	;
	
	private BigDecimal programId	;
	
	private String programCategory	;
	
	private BigDecimal checkSum	;
	
	private String programActivityStatus	;
	
	private String caseInfoNumber	;
	
	private BigDecimal remaining;
	
	@JsonProperty("formInfoJson")
	private String formInfoJson;
	
	@JsonProperty("formInfoJsonBlob")
	private byte[] formInfoJsonBlob;
	
	@JsonProperty("formIdentifier")
	private String formIdentifier;
	
	@JsonProperty("formIdentifierBlob")
	private byte[] formIdentifierBlob;
	
	private String orderType;
	
	@JsonProperty("sentenceSeqNo")
	private String sentenceSeqNo;
	
	
	
	
	public BigDecimal getRemaining() {
		return remaining;
	}
	public void setRemaining(BigDecimal remaining) {
		this.remaining = remaining;
	}
	public String getRecordSource() {
		return recordSource;
	}
	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}
	public String getConditionStatus() {
		return conditionStatus;
	}
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}
	public BigDecimal getEventId() {
		return eventId;
	}
	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}
	public String getCourtAgyLocId() {
		return courtAgyLocId;
	}
	public void setCourtAgyLocId(String courtAgyLocId) {
		this.courtAgyLocId = courtAgyLocId;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public BigDecimal getOrderId() {
		return orderId;
	}
	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
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
	public String getSentenceStatus() {
		return sentenceStatus;
	}
	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}
	public Date getSentenceStartDate() {
		return sentenceStartDate;
	}
	public void setSentenceStartDate(Date sentenceStartDate) {
		this.sentenceStartDate = sentenceStartDate;
	}
	public Date getSentenceEndDate() {
		return sentenceEndDate;
	}
	public void setSentenceEndDate(Date sentenceEndDate) {
		this.sentenceEndDate = sentenceEndDate;
	}
	public String getSentenceStatusDesc() {
		return sentenceStatusDesc;
	}
	public void setSentenceStatusDesc(String sentenceStatusDesc) {
		this.sentenceStatusDesc = sentenceStatusDesc;
	}
	public String getSentenceProgramMethod() {
		return sentenceProgramMethod;
	}
	public void setSentenceProgramMethod(String sentenceProgramMethod) {
		this.sentenceProgramMethod = sentenceProgramMethod;
	}
	public String getSentenceDesc() {
		return sentenceDesc;
	}
	public void setSentenceDesc(String sentenceDesc) {
		this.sentenceDesc = sentenceDesc;
	}
	public BigDecimal getOffenderSentConditionId() {
		return offenderSentConditionId;
	}
	public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}
	public Date getConditionStartDate() {
		return conditionStartDate;
	}
	public void setConditionStartDate(Date conditionStartDate) {
		this.conditionStartDate = conditionStartDate;
	}
	public Date getConditionEndDate() {
		return conditionEndDate;
	}
	public void setConditionEndDate(Date conditionEndDate) {
		this.conditionEndDate = conditionEndDate;
	}
	public String getCommConditionType() {
		return commConditionType;
	}
	public void setCommConditionType(String commConditionType) {
		this.commConditionType = commConditionType;
	}
	public String getCommConditionCode() {
		return commConditionCode;
	}
	public void setCommConditionCode(String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}
	public String getCommProgramMethod() {
		return commProgramMethod;
	}
	public void setCommProgramMethod(String commProgramMethod) {
		this.commProgramMethod = commProgramMethod;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public String getLengthUnit() {
		return lengthUnit;
	}
	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}
	public String getConditionLength() {
		return conditionLength;
	}
	public void setConditionLength(String conditionLength) {
		this.conditionLength = conditionLength;
	}
	public BigDecimal getCreditedUnits() {
		return creditedUnits;
	}
	public void setCreditedUnits(BigDecimal creditedUnits) {
		this.creditedUnits = creditedUnits;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getConditionDesc() {
		return conditionDesc;
	}
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}
	public String getSentCondDesc() {
		return sentCondDesc;
	}
	public void setSentCondDesc(String sentCondDesc) {
		this.sentCondDesc = sentCondDesc;
	}
	public BigDecimal getProgramId() {
		return programId;
	}
	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}
	public String getProgramCategory() {
		return programCategory;
	}
	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}
	public BigDecimal getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(BigDecimal checkSum) {
		this.checkSum = checkSum;
	}
	public String getProgramActivityStatus() {
		return programActivityStatus;
	}
	public void setProgramActivityStatus(String programActivityStatus) {
		this.programActivityStatus = programActivityStatus;
	}
	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}
	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}
	public String getFormInfoJson() {
		return formInfoJson;
	}
	public void setFormInfoJson(String formInfoJson) {
		this.formInfoJson = formInfoJson;
	}
	public byte[] getFormInfoJsonBlob() {
		return formInfoJsonBlob;
	}
	public void setFormInfoJsonBlob(byte[] formInfoJsonBlob) {
		this.formInfoJsonBlob = formInfoJsonBlob;
	}
	public String getFormIdentifier() {
		return formIdentifier;
	}
	public void setFormIdentifier(String formIdentifier) {
		this.formIdentifier = formIdentifier;
	}
	public byte[] getFormIdentifierBlob() {
		return formIdentifierBlob;
	}
	public void setFormIdentifierBlob(byte[] formIdentifierBlob) {
		this.formIdentifierBlob = formIdentifierBlob;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getSentenceSeqNo() {
		return sentenceSeqNo;
	}
	public void setSentenceSeqNo(String sentenceSeqNo) {
		this.sentenceSeqNo = sentenceSeqNo;
	}
	
	

}
