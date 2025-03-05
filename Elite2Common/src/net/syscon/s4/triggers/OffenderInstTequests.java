package net.syscon.s4.triggers;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderInstTequests extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long offInstReqId;
	private Long offenderBookingId;
	private Date requestDate;
	private String requestType;
	private String requestStatus;
	private String requestReason;
	private String requestedByType;
	private Integer requestedById;
	private String requestedAgencyId;
	private String requestComment;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;
	private Date reviewdate;
	private String internalstatus;
	private String intStsReasonCode;

	public Date getReviewdate() {
		return reviewdate;
	}

	public void setReviewdate(Date reviewdate) {
		this.reviewdate = reviewdate;
	}

	public String getInternalstatus() {
		return internalstatus;
	}

	public void setInternalstatus(String internalstatus) {
		this.internalstatus = internalstatus;
	}

	public String getIntStsReasonCode() {
		return intStsReasonCode;
	}

	public void setIntStsReasonCode(String intStsReasonCode) {
		this.intStsReasonCode = intStsReasonCode;
	}

	public Long getOffInstReqId() {
		return offInstReqId;
	}

	public void setOffInstReqId(Long offInstReqId) {
		this.offInstReqId = offInstReqId;
	}

	public Long getOffenderBookingId() {
		return offenderBookingId;
	}

	public void setOffenderBookingId(Long offenderBookingId) {
		this.offenderBookingId = offenderBookingId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRequestReason() {
		return requestReason;
	}

	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
	}

	public String getRequestedByType() {
		return requestedByType;
	}

	public void setRequestedByType(String requestedByType) {
		this.requestedByType = requestedByType;
	}

	public Integer getRequestedById() {
		return requestedById;
	}

	public void setRequestedById(Integer requestedById) {
		this.requestedById = requestedById;
	}

	public String getRequestedAgencyId() {
		return requestedAgencyId;
	}

	public void setRequestedAgencyId(String requestedAgencyId) {
		this.requestedAgencyId = requestedAgencyId;
	}

	public String getRequestComment() {
		return requestComment;
	}

	public void setRequestComment(String requestComment) {
		this.requestComment = requestComment;
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
