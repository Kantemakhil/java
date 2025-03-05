package net.syscon.s4.cf.offendertransactions.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the V_PAYMENT_PLAN_HISTORIES database table.
 * 
 */
public class VPaymentPlanHistories extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date endDate;

	private String frequency;

	private String groupCode;

	private BigDecimal groupId;

	private String informationNumber;

	private BigDecimal offenderId;

	private BigDecimal paidAmount;

	private BigDecimal paymentAmount;

	private Date paymentClosedDate;

	private Date paymentDate;

	private BigDecimal paymentPlanId;

	private BigDecimal paymentPlanSeq;

	private Date startDate;

	private Date transactionDate;

	public VPaymentPlanHistories() {
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(final String frequency) {
		this.frequency = frequency;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(final String groupCode) {
		this.groupCode = groupCode;
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(final BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getInformationNumber() {
		return this.informationNumber;
	}

	public void setInformationNumber(final String informationNumber) {
		this.informationNumber = informationNumber;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(final BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(final BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getPaymentPlanId() {
		return this.paymentPlanId;
	}

	public void setPaymentPlanId(final BigDecimal paymentPlanId) {
		this.paymentPlanId = paymentPlanId;
	}

	public BigDecimal getPaymentPlanSeq() {
		return this.paymentPlanSeq;
	}

	public void setPaymentPlanSeq(final BigDecimal paymentPlanSeq) {
		this.paymentPlanSeq = paymentPlanSeq;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public Date getPaymentClosedDate() {
		return paymentClosedDate;
	}

	public void setPaymentClosedDate(final Date paymentClosedDate) {
		this.paymentClosedDate = paymentClosedDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(final Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
