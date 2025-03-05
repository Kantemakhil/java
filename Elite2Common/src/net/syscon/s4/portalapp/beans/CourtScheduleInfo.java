package net.syscon.s4.portalapp.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourtScheduleInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5159557926079198909L;
	
	private String personId;
	private String offenderBookId;
	private String bookingNumber;
	
	private BigDecimal requestId;
	private String requestStatus;
	private String rejectReason;
	
	private Date movementDate;
	private String movementTime;
	private String movementReason;
	private String toCourt;
	private String judgeName;
	private String comment;
	private Date movementStartTime;
	private BigDecimal scheduleId;
	private BigDecimal caseId;
	
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(String offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	
	public BigDecimal getRequestId() {
		return requestId;
	}
	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	public String getMovementReason() {
		return movementReason;
	}
	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}
	public String getToCourt() {
		return toCourt;
	}
	public void setToCourt(String toCourt) {
		this.toCourt = toCourt;
	}
	
	public String getJudgeName() {
		return judgeName;
	}
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getMovementDate() {
		return movementDate;
	}
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}
	public String getMovementTime() {
		return movementTime;
	}
	public void setMovementTime(String movementTime) {
		this.movementTime = movementTime;
	}
	public Date getMovementStartTime() {
		return movementStartTime;
	}
	public void setMovementStartTime(Date movementStartTime) {
		this.movementStartTime = movementStartTime;
	}
	public BigDecimal getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(BigDecimal scheduleId) {
		this.scheduleId = scheduleId;
	}
	public BigDecimal getCaseId() {
		return caseId;
	}
	public void setCaseId(BigDecimal caseId) {
		this.caseId = caseId;
	}
	
}
