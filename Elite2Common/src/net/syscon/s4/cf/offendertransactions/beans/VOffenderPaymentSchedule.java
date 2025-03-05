package net.syscon.s4.cf.offendertransactions.beans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_OFFENDER_PAYMENT_SCHEDULES database table.
 * 
 */
public class VOffenderPaymentSchedule  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal groupId;

	private String informationNumber;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal offenderDeductionId;

	private BigDecimal paidAmount;

	private BigDecimal paidRecursiveAmount;

	private BigDecimal paymentAmount;

	private String paymentClosedFlag;

	private Date paymentDate;

	private BigDecimal paymentPlanId;

	private BigDecimal paymentPlanSeq;

	private BigDecimal recursiveAmount;
	
	private BigDecimal totalArrears;
	

	public BigDecimal getTotalArrears() {
		return totalArrears;
	}

	public void setTotalArrears(BigDecimal totalArrears) {
		this.totalArrears = totalArrears;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public VOffenderPaymentSchedule() {
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getInformationNumber() {
		return this.informationNumber;
	}

	public void setInformationNumber(String informationNumber) {
		this.informationNumber = informationNumber;
	}




	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getOffenderDeductionId() {
		return this.offenderDeductionId;
	}

	public void setOffenderDeductionId(BigDecimal offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getPaidRecursiveAmount() {
		return this.paidRecursiveAmount;
	}

	public void setPaidRecursiveAmount(BigDecimal paidRecursiveAmount) {
		this.paidRecursiveAmount = paidRecursiveAmount;
	}

	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentClosedFlag() {
		return this.paymentClosedFlag;
	}

	public void setPaymentClosedFlag(String paymentClosedFlag) {
		this.paymentClosedFlag = paymentClosedFlag;
	}



	public BigDecimal getPaymentPlanId() {
		return this.paymentPlanId;
	}

	public void setPaymentPlanId(BigDecimal paymentPlanId) {
		this.paymentPlanId = paymentPlanId;
	}

	public BigDecimal getPaymentPlanSeq() {
		return this.paymentPlanSeq;
	}

	public void setPaymentPlanSeq(BigDecimal paymentPlanSeq) {
		this.paymentPlanSeq = paymentPlanSeq;
	}

	public BigDecimal getRecursiveAmount() {
		return this.recursiveAmount;
	}

	public void setRecursiveAmount(BigDecimal recursiveAmount) {
		this.recursiveAmount = recursiveAmount;
	}

}
