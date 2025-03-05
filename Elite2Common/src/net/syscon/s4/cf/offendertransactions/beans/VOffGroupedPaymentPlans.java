package net.syscon.s4.cf.offendertransactions.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the V_OFF_GROUPED_PAYMENT_PLANS database table.
 * 
 */
public class VOffGroupedPaymentPlans extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("groupId")
	private BigDecimal groupId;
	@JsonProperty("informationNumber")
	private String informationNumber;
	@JsonProperty("minDeductionId")
	private BigDecimal minDeductionId;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("paymentClosedFlag")
	private String paymentClosedFlag;
	@JsonProperty("paymentPlanId")
	private BigDecimal paymentPlanId;
	@JsonProperty("balOwing")
	private BigDecimal balOwing;
	@JsonProperty("dueDate")
	private Date dueDate;
	@JsonProperty("amount")
	private BigDecimal amount;
	@JsonProperty("arrears")
	private BigDecimal arrears;
	@JsonProperty("daysLate")
	private BigDecimal daysLate;
	@JsonProperty("reason")
	private String reason;
	@JsonProperty("minNextPaymentDate")
	private Date minNextPaymentDate;
	@JsonProperty("maxNextPaymentDate")
	private Date maxNextPaymentDate;
	@JsonProperty("minNextPaymentDateTwo")
	private Date minNextPaymentDateTwo;
	@JsonProperty("tempDate")
	private Date tempDate;

	public VOffGroupedPaymentPlans() {
		// VOffGroupedPaymentPlans
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

	public BigDecimal getMinDeductionId() {
		return this.minDeductionId;
	}

	public void setMinDeductionId(BigDecimal minDeductionId) {
		this.minDeductionId = minDeductionId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getArrears() {
		return arrears;
	}

	public void setArrears(BigDecimal arrears) {
		this.arrears = arrears;
	}

	public BigDecimal getDaysLate() {
		return daysLate;
	}

	public void setDaysLate(BigDecimal daysLate) {
		this.daysLate = daysLate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setBalOwing(BigDecimal balOwing) {
		this.balOwing = balOwing;
	}

	public BigDecimal getBalOwing() {
		return balOwing;
	}

	public Date getMinNextPaymentDate() {
		return minNextPaymentDate;
	}

	public void setMinNextPaymentDate(Date minNextPaymentDate) {
		this.minNextPaymentDate = minNextPaymentDate;
	}

	public Date getMaxNextPaymentDate() {
		return maxNextPaymentDate;
	}

	public void setMaxNextPaymentDate(Date maxNextPaymentDate) {
		this.maxNextPaymentDate = maxNextPaymentDate;
	}

	public Date getMinNextPaymentDateTwo() {
		return minNextPaymentDateTwo;
	}

	public void setMinNextPaymentDateTwo(Date minNextPaymentDateTwo) {
		this.minNextPaymentDateTwo = minNextPaymentDateTwo;
	}

	public Date getTempDate() {
		return tempDate;
	}

	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}

}
