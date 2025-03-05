package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the OFFENDER_WORKS database table.
 * 
 */
public class OffenderWorks extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long offenderWorkId;

	private String caseloadId;

	private Date clockInDatetime;

	private Date clockOutDatetime;

	private String commentText;

	private String compensationCode;

	private String confirmFlag;

	private Date createDatetime;

	private String createUserId;

	private String firstName;

	private String generatedFlag;

	private String lastName;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal numberOfUnits;

	private BigDecimal offenderBookId;

	private BigDecimal offenderId;

	private Date payEndDate;

	private String payRangeCode;

	private Date payStartDate;

	private BigDecimal payrollAmount;

	private BigDecimal payrollBatchId;

	private String performanceCode;

	private Date postedDate;

	private String processedFlag;

	private String rejectFlag;

	private String rejectReasonText;

	private String schCompensationCode;

	private Date schEndTime;

	private BigDecimal schNumberOfUnits;

	private String schPerformanceCode;

	private Date schStartTime;

	private String sealFlag;

	private String trustCaseloadId;

	private BigDecimal txnEntrySeq;

	private BigDecimal txnId;

	private String unitCode;

	private BigDecimal workAssignmentId;

	private String workCode;

	private BigDecimal workRate;

	private String workType;

	public OffenderWorks() {
		// OffenderWork
	}

	public Long getOffenderWorkId() {
		return this.offenderWorkId;
	}

	public void setOffenderWorkId(final Long offenderWorkId) {
		this.offenderWorkId = offenderWorkId;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Date getClockInDatetime() {
		return this.clockInDatetime;
	}

	public void setClockInDatetime(final Date clockInDatetime) {
		this.clockInDatetime = clockInDatetime;
	}

	public Date getClockOutDatetime() {
		return this.clockOutDatetime;
	}

	public void setClockOutDatetime(final Date clockOutDatetime) {
		this.clockOutDatetime = clockOutDatetime;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getCompensationCode() {
		return this.compensationCode;
	}

	public void setCompensationCode(final String compensationCode) {
		this.compensationCode = compensationCode;
	}

	public String getConfirmFlag() {
		return this.confirmFlag;
	}

	public void setConfirmFlag(final String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getGeneratedFlag() {
		return this.generatedFlag;
	}

	public void setGeneratedFlag(final String generatedFlag) {
		this.generatedFlag = generatedFlag;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getNumberOfUnits() {
		return this.numberOfUnits;
	}

	public void setNumberOfUnits(final BigDecimal numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public Date getPayEndDate() {
		return this.payEndDate;
	}

	public void setPayEndDate(final Date payEndDate) {
		this.payEndDate = payEndDate;
	}

	public String getPayRangeCode() {
		return this.payRangeCode;
	}

	public void setPayRangeCode(final String payRangeCode) {
		this.payRangeCode = payRangeCode;
	}

	public Date getPayStartDate() {
		return this.payStartDate;
	}

	public void setPayStartDate(final Date payStartDate) {
		this.payStartDate = payStartDate;
	}

	public BigDecimal getPayrollAmount() {
		return this.payrollAmount;
	}

	public void setPayrollAmount(final BigDecimal payrollAmount) {
		this.payrollAmount = payrollAmount;
	}

	public BigDecimal getPayrollBatchId() {
		return this.payrollBatchId;
	}

	public void setPayrollBatchId(final BigDecimal payrollBatchId) {
		this.payrollBatchId = payrollBatchId;
	}

	public String getPerformanceCode() {
		return this.performanceCode;
	}

	public void setPerformanceCode(final String performanceCode) {
		this.performanceCode = performanceCode;
	}

	public Date getPostedDate() {
		return this.postedDate;
	}

	public void setPostedDate(final Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getProcessedFlag() {
		return this.processedFlag;
	}

	public void setProcessedFlag(final String processedFlag) {
		this.processedFlag = processedFlag;
	}

	public String getRejectFlag() {
		return this.rejectFlag;
	}

	public void setRejectFlag(final String rejectFlag) {
		this.rejectFlag = rejectFlag;
	}

	public String getRejectReasonText() {
		return this.rejectReasonText;
	}

	public void setRejectReasonText(final String rejectReasonText) {
		this.rejectReasonText = rejectReasonText;
	}

	public String getSchCompensationCode() {
		return this.schCompensationCode;
	}

	public void setSchCompensationCode(final String schCompensationCode) {
		this.schCompensationCode = schCompensationCode;
	}

	public Date getSchEndTime() {
		return this.schEndTime;
	}

	public void setSchEndTime(final Date schEndTime) {
		this.schEndTime = schEndTime;
	}

	public BigDecimal getSchNumberOfUnits() {
		return this.schNumberOfUnits;
	}

	public void setSchNumberOfUnits(final BigDecimal schNumberOfUnits) {
		this.schNumberOfUnits = schNumberOfUnits;
	}

	public String getSchPerformanceCode() {
		return this.schPerformanceCode;
	}

	public void setSchPerformanceCode(final String schPerformanceCode) {
		this.schPerformanceCode = schPerformanceCode;
	}

	public Date getSchStartTime() {
		return this.schStartTime;
	}

	public void setSchStartTime(final Date schStartTime) {
		this.schStartTime = schStartTime;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getTrustCaseloadId() {
		return this.trustCaseloadId;
	}

	public void setTrustCaseloadId(final String trustCaseloadId) {
		this.trustCaseloadId = trustCaseloadId;
	}

	public BigDecimal getTxnEntrySeq() {
		return this.txnEntrySeq;
	}

	public void setTxnEntrySeq(final BigDecimal txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	public BigDecimal getTxnId() {
		return this.txnId;
	}

	public void setTxnId(final BigDecimal txnId) {
		this.txnId = txnId;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}

	public BigDecimal getWorkAssignmentId() {
		return this.workAssignmentId;
	}

	public void setWorkAssignmentId(final BigDecimal workAssignmentId) {
		this.workAssignmentId = workAssignmentId;
	}

	public String getWorkCode() {
		return this.workCode;
	}

	public void setWorkCode(final String workCode) {
		this.workCode = workCode;
	}

	public BigDecimal getWorkRate() {
		return this.workRate;
	}

	public void setWorkRate(final BigDecimal workRate) {
		this.workRate = workRate;
	}

	public String getWorkType() {
		return this.workType;
	}

	public void setWorkType(final String workType) {
		this.workType = workType;
	}

}
