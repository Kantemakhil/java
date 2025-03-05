package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Sentences  extends BaseModel implements Serializable {

private static final long serialVersionUID = 1L;
	
	@JsonProperty("caseId")
	private Long caseId;
	
	@JsonProperty("orderId")
	private String orderId;
	
	@JsonProperty("line")
	private Long line;
	
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	
	@JsonProperty("sentenceType")
	private String sentenceType;
	
	@JsonProperty("fineAmount")
	private BigDecimal fineAmount;
		
	@JsonProperty("consecutiveToLine")
	private Integer consecutiveToLine;
	
	@JsonProperty("sentenceDate")
	private Date sentenceDate;
	
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	
	@JsonProperty("nonProbationStartDate")
	private Date nonProbationStartDate;
	
	@JsonProperty("probableReleaseDate")
	private Date probableReleaseDate;
	
	@JsonProperty("defaultDays")
	private double defaultDays;
	
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("sentenceLevel")
	private String sentenceLevel;
	
	@JsonProperty("endDate")
	private Date endDate;
		
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;
	
	@JsonProperty("statusUpdateDate")
	private Date statusUpdateDate;
	
	@JsonProperty("statusUpdateStaffId")
	private Integer statusUpdateStaffId;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("offenderSentConditionId")
	private Integer offenderSentConditionId;
	
	@JsonProperty("caseInfoNumber")
	private String caseInfoNumber;
	
	@JsonProperty("supervisionExpiryDate")
	private Date supervisionExpiryDate;
	
	@JsonProperty("offensesOnSentenceList")
	private List<OffensesOutcome> offensesOnSentenceList;
	
	@JsonProperty("commitFlag")
	private String commitFlag;

	@JsonProperty("sentenceTermList")
	private List<Terms> sentenceTermList;
	
	@JsonProperty("periodsList")
	private List<Terms> periodsList;
	
	@JsonProperty("offensesOnOrdersList")
	private List<OffensesOutcome> offensesOnOrdersList;

	private String pAccess;
	private String offenderIdDisplay;
	
	private String orderCode;
	private String orderType;
	
	private SentenceCalculation sentenceCalculation;
	
	
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

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getpAccess() {
		return pAccess;
	}

	public void setpAccess(String pAccess) {
		this.pAccess = pAccess;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getLine() {
		return line;
	}

	public void setLine(Long line) {
		this.line = line;
	}

	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	
	public Integer getConsecutiveToLine() {
		return consecutiveToLine;
	}

	public void setConsecutiveToLine(Integer consecutiveToLine) {
		this.consecutiveToLine = consecutiveToLine;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceType() {
		return sentenceType;
	}

	public void setSentenceType(String sentenceType) {
		this.sentenceType = sentenceType;
	}

	public BigDecimal getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(BigDecimal fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Date getSentenceDate() {
		return sentenceDate;
	}

	public void setSentenceDate(Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSentenceLevel() {
		return sentenceLevel;
	}

	public void setSentenceLevel(String sentenceLevel) {
		this.sentenceLevel = sentenceLevel;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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

	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public Integer getStatusUpdateStaffId() {
		return statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(Integer statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	public void setOffenderSentConditionId(Integer offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public List<OffensesOutcome> getOffensesOnSentenceList() {
		return offensesOnSentenceList;
	}

	public void setOffensesOnSentenceList(List<OffensesOutcome> offensesOnSentenceList) {
		this.offensesOnSentenceList = offensesOnSentenceList;
	}

	public String getCommitFlag() {
		return commitFlag;
	}

	public void setCommitFlag(String commitFlag) {
		this.commitFlag = commitFlag;
	}

	public List<Terms> getSentenceTermList() {
		return sentenceTermList;
	}

	public void setSentenceTermList(List<Terms> sentenceTermList) {
		this.sentenceTermList = sentenceTermList;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getNonProbationStartDate() {
		return nonProbationStartDate;
	}

	public void setNonProbationStartDate(Date nonProbationStartDate) {
		this.nonProbationStartDate = nonProbationStartDate;
	}

	public Date getProbableReleaseDate() {
		return probableReleaseDate;
	}

	public void setProbableReleaseDate(Date probableReleaseDate) {
		this.probableReleaseDate = probableReleaseDate;
	}
	
	public double getDefaultDays() {
		return defaultDays;
	}

	public void setDefaultDays(double defaultDays) {
		this.defaultDays = defaultDays;
	}
	
	public Date getSupervisionExpiryDate() {
		return supervisionExpiryDate;
	}

	public void setSupervisionExpiryDate(Date supervisionExpiryDate) {
		this.supervisionExpiryDate = supervisionExpiryDate;
	}
	
	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public List<Terms> getPeriodsList() {
		return periodsList;
	}

	public void setPeriodsList(List<Terms> periodsList) {
		this.periodsList = periodsList;
	}

	public List<OffensesOutcome> getOffensesOnOrdersList() {
		return offensesOnOrdersList;
	}

	public void setOffensesOnOrdersList(List<OffensesOutcome> offensesOnOrdersList) {
		this.offensesOnOrdersList = offensesOnOrdersList;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public SentenceCalculation getSentenceCalculation() {
		return sentenceCalculation;
	}

	public void setSentenceCalculation(SentenceCalculation sentenceCalculation) {
		this.sentenceCalculation = sentenceCalculation;
	}
	
	
}
