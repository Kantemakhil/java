package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderPaymentSchedules extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;

	@JsonProperty("groupId")
	private BigDecimal groupId;

	@JsonProperty("informationNumber")
	private String informationNumber;

	@JsonProperty("paidAmount")
	private BigDecimal paidAmount;

	@JsonProperty("paidRecursiveAmount")
	private BigDecimal paidRecursiveAmount;

	@JsonProperty("paymentAmount")
	private BigDecimal paymentAmount;

	@JsonProperty("paymentDate")
	private Date paymentDate;

	@JsonProperty("minPaymentDate")
	private Date minPaymentDate;

	@JsonProperty("maxPaymentDate")
	private Date maxPaymentDate;

	@JsonProperty("paymentPlanId")
	private long paymentPlanId;

	@JsonProperty("paymentPlanSeq")
	private long paymentPlanSeq;

	@JsonProperty("recursiveAmount")
	private BigDecimal recursiveAmount;

	@JsonProperty("sealFlag")
	private String sealFlag;

	public OffenderPaymentSchedules() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the offenderDeductionId
	 */
	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}

	/**
	 * @param offenderDeductionId the offenderDeductionId to set
	 */
	public void setOffenderDeductionId(Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	/**
	 * @return the groupId
	 */
	public BigDecimal getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the informationNumber
	 */
	public String getInformationNumber() {
		return informationNumber;
	}

	/**
	 * @param informationNumber the informationNumber to set
	 */
	public void setInformationNumber(String informationNumber) {
		this.informationNumber = informationNumber;
	}

	/**
	 * @return the paidAmount
	 */
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the paidRecursiveAmount
	 */
	public BigDecimal getPaidRecursiveAmount() {
		return paidRecursiveAmount;
	}

	/**
	 * @param paidRecursiveAmount the paidRecursiveAmount to set
	 */
	public void setPaidRecursiveAmount(BigDecimal paidRecursiveAmount) {
		this.paidRecursiveAmount = paidRecursiveAmount;
	}

	/**
	 * @return the paymentAmount
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the paymentPlanId
	 */
	public long getPaymentPlanId() {
		return paymentPlanId;
	}

	/**
	 * @param paymentPlanId the paymentPlanId to set
	 */
	public void setPaymentPlanId(long paymentPlanId) {
		this.paymentPlanId = paymentPlanId;
	}

	/**
	 * @return the paymentPlanSeq
	 */
	public long getPaymentPlanSeq() {
		return paymentPlanSeq;
	}

	/**
	 * @param paymentPlanSeq the paymentPlanSeq to set
	 */
	public void setPaymentPlanSeq(long paymentPlanSeq) {
		this.paymentPlanSeq = paymentPlanSeq;
	}

	/**
	 * @return the recursiveAmount
	 */
	public BigDecimal getRecursiveAmount() {
		return recursiveAmount;
	}

	/**
	 * @param recursiveAmount the recursiveAmount to set
	 */
	public void setRecursiveAmount(BigDecimal recursiveAmount) {
		this.recursiveAmount = recursiveAmount;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the minPaymentDate
	 */
	public Date getMinPaymentDate() {
		return minPaymentDate;
	}

	/**
	 * @param minPaymentDate the minPaymentDate to set
	 */
	public void setMinPaymentDate(Date minPaymentDate) {
		this.minPaymentDate = minPaymentDate;
	}

	/**
	 * @return the maxPaymentDate
	 */
	public Date getMaxPaymentDate() {
		return maxPaymentDate;
	}

	/**
	 * @param maxPaymentDate the maxPaymentDate to set
	 */
	public void setMaxPaymentDate(Date maxPaymentDate) {
		this.maxPaymentDate = maxPaymentDate;
	}

}
