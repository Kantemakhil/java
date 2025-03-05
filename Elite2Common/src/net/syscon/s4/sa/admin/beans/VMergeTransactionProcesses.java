package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the V_MERGE_TRANSACTION_PROCESSES database table.
 * 
 */
public class VMergeTransactionProcesses extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("beginDate")
	private Date beginDate;
	@JsonProperty("beginTime")
	private Date beginTime;
	@JsonProperty("defaultOnFlag")
	private String defaultOnFlag;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("mandatoryOnFlag")
	private String mandatoryOnFlag;
	@JsonProperty("mergeTransactionId")
	private BigDecimal mergeTransactionId;
	@JsonProperty("processDescription")
	private String processDescription;
	@JsonProperty("processId")
	private BigDecimal processId;
	@JsonProperty("processName")
	private String processName;
	@JsonProperty("timeframeFlag")
	private String timeframeFlag;
	@JsonProperty("transactionType")
	private String transactionType;
	@JsonProperty("transferFlag")
	private String transferFlag;
	@JsonProperty("pFromOffBookId")
	private long pFromOffBookId;
	@JsonProperty("bookingStartdate")
	private Date bookingStartdate;
	@JsonProperty("bookingEndDate")
	private Date bookingEndDate;
	@JsonProperty("isProcessTimeRequired")
	private String isProcessTimeRequired;
	@JsonProperty("pFromRootOffId")
	private BigDecimal pFromRootOffId;
	@JsonProperty("prevBkgEndDate")
	private Date prevBkgEndDate;
	@JsonProperty("nextBkgStartDate")
	private Date nextBkgStartDate;

	public VMergeTransactionProcesses() {
	}

	/**
	 * @return the defaultOnFlag
	 */
	public String getDefaultOnFlag() {
		return this.defaultOnFlag;
	}

	/**
	 * @param defaultOnFlag
	 *            the defaultOnFlag to set
	 */
	public void setDefaultOnFlag(final String defaultOnFlag) {
		this.defaultOnFlag = defaultOnFlag;
	}

	/**
	 * @return the mandatoryOnFlag
	 */
	public String getMandatoryOnFlag() {
		return this.mandatoryOnFlag;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(final Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime
	 *            the beginTime to set
	 */
	public void setBeginTime(final Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @param mandatoryOnFlag
	 *            the mandatoryOnFlag to set
	 */
	public void setMandatoryOnFlag(final String mandatoryOnFlag) {
		this.mandatoryOnFlag = mandatoryOnFlag;
	}

	/**
	 * @return the mergeTransactionId
	 */
	public BigDecimal getMergeTransactionId() {
		return this.mergeTransactionId;
	}

	/**
	 * @param mergeTransactionId
	 *            the mergeTransactionId to set
	 */
	public void setMergeTransactionId(final BigDecimal mergeTransactionId) {
		this.mergeTransactionId = mergeTransactionId;
	}

	/**
	 * @return the processDescription
	 */
	public String getProcessDescription() {
		return this.processDescription;
	}

	/**
	 * @param processDescription
	 *            the processDescription to set
	 */
	public void setProcessDescription(final String processDescription) {
		this.processDescription = processDescription;
	}

	/**
	 * @return the processId
	 */
	public BigDecimal getProcessId() {
		return this.processId;
	}

	/**
	 * @param processId
	 *            the processId to set
	 */
	public void setProcessId(final BigDecimal processId) {
		this.processId = processId;
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return this.processName;
	}

	/**
	 * @param processName
	 *            the processName to set
	 */
	public void setProcessName(final String processName) {
		this.processName = processName;
	}

	/**
	 * @return the timeframeFlag
	 */
	public String getTimeframeFlag() {
		return this.timeframeFlag;
	}

	/**
	 * @param timeframeFlag
	 *            the timeframeFlag to set
	 */
	public void setTimeframeFlag(final String timeframeFlag) {
		this.timeframeFlag = timeframeFlag;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return this.transactionType;
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(final String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the transferFlag
	 */
	public String getTransferFlag() {
		return this.transferFlag;
	}

	/**
	 * @param transferFlag
	 *            the transferFlag to set
	 */
	public void setTransferFlag(final String transferFlag) {
		this.transferFlag = transferFlag;
	}

	public Date getBookingStartdate() {
		return bookingStartdate;
	}

	public void setBookingStartdate(Date bookingStartdate) {
		this.bookingStartdate = bookingStartdate;
	}

	public Date getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public String getIsProcessTimeRequired() {
		return isProcessTimeRequired;
	}

	public void setIsProcessTimeRequired(String isProcessTimeRequired) {
		this.isProcessTimeRequired = isProcessTimeRequired;
	}

	public long getpFromOffBookId() {
		return pFromOffBookId;
	}

	public void setpFromOffBookId(long pFromOffBookId) {
		this.pFromOffBookId = pFromOffBookId;
	}

	public BigDecimal getpFromRootOffId() {
		return pFromRootOffId;
	}

	public void setpFromRootOffId(BigDecimal pFromRootOffId) {
		this.pFromRootOffId = pFromRootOffId;
	}

	public Date getPrevBkgEndDate() {
		return prevBkgEndDate;
	}

	public void setPrevBkgEndDate(Date prevBkgEndDate) {
		this.prevBkgEndDate = prevBkgEndDate;
	}

	public Date getNextBkgStartDate() {
		return nextBkgStartDate;
	}

	public void setNextBkgStartDate(Date nextBkgStartDate) {
		this.nextBkgStartDate = nextBkgStartDate;
	}

}
