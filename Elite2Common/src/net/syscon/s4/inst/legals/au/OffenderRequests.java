package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the OFFENDER_REQUESTS database table.
 * 
 */
public class OffenderRequests implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseloadType;

	private String commentText;

	private Date courtDeliveryDate;

	private Date createDatetime;

	private String createUserId;

	private Date creationDate;

	private String creationUser;

	private String dateToBeFixed;

	private String description;

	private BigDecimal dischargeAuthority;

	private String dischargeComment;

	private Date dischargeDate;

	private String dischargeReason;

	private Date modifyDatetime;

	private String modifyUserId;

	private String orderCode;

	private String orderType;

	private Date reportDueDate;

	private String requestStatus;

	private String sealFlag;

	private Date sentenceExpiryDate;

	private Date startDate;

	private Date transcriptDueDate;

	private BigDecimal offenderBookId;

	private long chargeSeq;

	private long requestSeq;
	
	private String remandAgyLocId;
	
	private BigDecimal caseId;
	
	private String status;
	
	private BigDecimal eventId;
	
	private String caseInfoNumber;
	
	private String rowId;
	
	private String agyLocId;
	
	private Date systemDischargeDate;
	
	private String completeStatus;
	
	private String completionAuthor;
	
	private Date completionDate;
	
	private String reviewerUser;
	
	private Date reviewDate;
	
	private String reviewStatus;
	
	private String statusUpdateComment;
	
	private Date statusUpdateDate;
	
	private String statusUpdateReason;
	
	private Integer statusUpdateStaffId;
	
	private Date systemCreationDate;

	public OffenderRequests() {
		// OffenderRequests
	}
	
	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(long chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public long getRequestSeq() {
		return requestSeq;
	}

	public void setRequestSeq(long requestSeq) {
		this.requestSeq = requestSeq;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCourtDeliveryDate() {
		return this.courtDeliveryDate;
	}

	public void setCourtDeliveryDate(Date courtDeliveryDate) {
		this.courtDeliveryDate = courtDeliveryDate;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public String getDateToBeFixed() {
		return this.dateToBeFixed;
	}

	public void setDateToBeFixed(String dateToBeFixed) {
		this.dateToBeFixed = dateToBeFixed;
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

	public Date getDischargeDate() {
		return this.dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getDischargeReason() {
		return this.dischargeReason;
	}

	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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

	public Date getReportDueDate() {
		return this.reportDueDate;
	}

	public void setReportDueDate(Date reportDueDate) {
		this.reportDueDate = reportDueDate;
	}

	public String getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getSentenceExpiryDate() {
		return this.sentenceExpiryDate;
	}

	public void setSentenceExpiryDate(Date sentenceExpiryDate) {
		this.sentenceExpiryDate = sentenceExpiryDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getTranscriptDueDate() {
		return this.transcriptDueDate;
	}

	public void setTranscriptDueDate(Date transcriptDueDate) {
		this.transcriptDueDate = transcriptDueDate;
	}

	public String getRemandAgyLocId() {
		return remandAgyLocId;
	}

	public void setRemandAgyLocId(String remandAgyLocId) {
		this.remandAgyLocId = remandAgyLocId;
	}

	public BigDecimal getCaseId() {
		return caseId;
	}

	public void setCaseId(BigDecimal caseId) {
		this.caseId = caseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Date getSystemDischargeDate() {
		return systemDischargeDate;
	}

	public void setSystemDischargeDate(Date systemDischargeDate) {
		this.systemDischargeDate = systemDischargeDate;
	}

	public String getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}

	public String getCompletionAuthor() {
		return completionAuthor;
	}

	public void setCompletionAuthor(String completionAuthor) {
		this.completionAuthor = completionAuthor;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getReviewerUser() {
		return reviewerUser;
	}

	public void setReviewerUser(String reviewerUser) {
		this.reviewerUser = reviewerUser;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
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

	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public Integer getStatusUpdateStaffId() {
		return statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(Integer statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public Date getSystemCreationDate() {
		return systemCreationDate;
	}

	public void setSystemCreationDate(Date systemCreationDate) {
		this.systemCreationDate = systemCreationDate;
	}

}
