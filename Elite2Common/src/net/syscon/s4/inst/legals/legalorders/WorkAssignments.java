package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the WORK_ASSIGNMENTS database table.
 * 
 */
public class WorkAssignments implements Serializable {
	private static final long serialVersionUID = 1L;

	private long workAssignmentId;

	private String caseloadId;

	private String commentText;

	private Date createDatetime;

	private String createUserId;

	private Date lastConfirmDate;

	private Date lastStatusModifyDate;

	private Date lastStatusModifyTime;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal offenderBookId;

	private BigDecimal offenderId;

	private String payRangeCode;

	private Date payRangeReviewDate;

	private String schAssignmentCode;

	private String sealFlag;

	private String workAssignStatus;

	private Date workAssignmentDate;

	private String workCode;

	private String workGroupId;
	
	private BigDecimal offenderBookIdRequest;
	
	private String orderCode;
	
	private long requestSeq;

	public WorkAssignments() {
		// WorkAssignment
	}

	public long getWorkAssignmentId() {
		return this.workAssignmentId;
	}

	public void setWorkAssignmentId(long workAssignmentId) {
		this.workAssignmentId = workAssignmentId;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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

	public Date getLastConfirmDate() {
		return this.lastConfirmDate;
	}

	public void setLastConfirmDate(Date lastConfirmDate) {
		this.lastConfirmDate = lastConfirmDate;
	}

	public Date getLastStatusModifyDate() {
		return this.lastStatusModifyDate;
	}

	public void setLastStatusModifyDate(Date lastStatusModifyDate) {
		this.lastStatusModifyDate = lastStatusModifyDate;
	}

	public Date getLastStatusModifyTime() {
		return this.lastStatusModifyTime;
	}

	public void setLastStatusModifyTime(Date lastStatusModifyTime) {
		this.lastStatusModifyTime = lastStatusModifyTime;
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

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getPayRangeCode() {
		return this.payRangeCode;
	}

	public void setPayRangeCode(String payRangeCode) {
		this.payRangeCode = payRangeCode;
	}

	public Date getPayRangeReviewDate() {
		return this.payRangeReviewDate;
	}

	public void setPayRangeReviewDate(Date payRangeReviewDate) {
		this.payRangeReviewDate = payRangeReviewDate;
	}

	public String getSchAssignmentCode() {
		return this.schAssignmentCode;
	}

	public void setSchAssignmentCode(String schAssignmentCode) {
		this.schAssignmentCode = schAssignmentCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getWorkAssignStatus() {
		return this.workAssignStatus;
	}

	public void setWorkAssignStatus(String workAssignStatus) {
		this.workAssignStatus = workAssignStatus;
	}

	public Date getWorkAssignmentDate() {
		return this.workAssignmentDate;
	}

	public void setWorkAssignmentDate(Date workAssignmentDate) {
		this.workAssignmentDate = workAssignmentDate;
	}

	public String getWorkCode() {
		return this.workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getWorkGroupId() {
		return this.workGroupId;
	}

	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}

	public BigDecimal getOffenderBookIdRequest() {
		return offenderBookIdRequest;
	}

	public void setOffenderBookIdRequest(BigDecimal offenderBookIdRequest) {
		this.offenderBookIdRequest = offenderBookIdRequest;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public long getRequestSeq() {
		return requestSeq;
	}

	public void setRequestSeq(long requestSeq) {
		this.requestSeq = requestSeq;
	}

}
