package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VOffenderVisitDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date endTime;

	private String firstName;

	private String internalLocationDesc;

	private String lastName;

	private String livingUnitDesc;

	private BigDecimal offenderBookId;

	private String offenderIdDisplay;

	private BigDecimal offenderVisitId;

	private Date startTime;

	private Date visitDate;

	private String visitStatus;

	private String visitType;

	private String caseloadId;
	
	private String dayOfTheWeek;
	
	private Date fromDate;
	
	private Date toDate;
	
	private String facility;
	
	private String timeSlotSeq;
	
	private String visitLocation;
	 
	private String completionStatus;
	
	private String outcomeReasonCode;
	
	private String commentText;
	
	
	private List<String> facilityList;
	
	private BigDecimal visitInternalLocationId;
	
	public VOffenderVisitDetails() {
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInternalLocationDesc() {
		return this.internalLocationDesc;
	}

	public void setInternalLocationDesc(String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLivingUnitDesc() {
		return this.livingUnitDesc;
	}

	public void setLivingUnitDesc(String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public BigDecimal getOffenderVisitId() {
		return this.offenderVisitId;
	}

	public void setOffenderVisitId(BigDecimal offenderVisitId) {
		this.offenderVisitId = offenderVisitId;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getVisitDate() {
		return this.visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitStatus() {
		return this.visitStatus;
	}

	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}

	public String getVisitType() {
		return this.visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public String getFacility() {
		return facility;
	}

	public String getTimeSlotSeq() {
		return timeSlotSeq;
	}

	public String getVisitLocation() {
		return visitLocation;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public List<String> getFacilityList() {
		return facilityList;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public void setTimeSlotSeq(String timeSlotSeq) {
		this.timeSlotSeq = timeSlotSeq;
	}

	public void setVisitLocation(String visitLocation) {
		this.visitLocation = visitLocation;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public void setFacilityList(List<String> facilityList) {
		this.facilityList = facilityList;
	}

	public BigDecimal getVisitInternalLocationId() {
		return visitInternalLocationId;
	}

	public void setVisitInternalLocationId(BigDecimal visitInternalLocationId) {
		this.visitInternalLocationId = visitInternalLocationId;
	}

	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
}
