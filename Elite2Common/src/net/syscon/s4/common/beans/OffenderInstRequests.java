package net.syscon.s4.common.beans;

import java.math.BigDecimal;
import java.util.Date;

public class OffenderInstRequests {

	BigDecimal offInstReqId;
	BigDecimal offenderBookingId;
	Date requestDate;
	String requestType;
	String requestStatus;
	String requestReason;
	String requestedByType;
	BigDecimal requestedById;
	String requestedAgencyId;
	String requestComment;
	Date createDatetime;
	String createUserId;
	Date modifyDatetime;
	String modifyUserId;
	public BigDecimal getOffInstReqId() {
		return offInstReqId;
	}
	public void setOffInstReqId(BigDecimal offInstReqId) {
		this.offInstReqId = offInstReqId;
	}
	public BigDecimal getOffenderBookingId() {
		return offenderBookingId;
	}
	public void setOffenderBookingId(BigDecimal offenderBookingId) {
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
	public BigDecimal getRequestedById() {
		return requestedById;
	}
	public void setRequestedById(BigDecimal requestedById) {
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
	String sealFlag;
	
	

}
