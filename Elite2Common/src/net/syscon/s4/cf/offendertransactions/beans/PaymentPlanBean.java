package net.syscon.s4.cf.offendertransactions.beans;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentPlanBean {
	
	private Date dueDate;
	
	private String infoNumber;
	
	private String code;
	
	private BigDecimal amountDues;
	


	private String amountDue;
	
	private String paymentFreq;
	
	public BigDecimal getAmountDues() {
		return amountDues;
	}

	public void setAmountDues(BigDecimal amountDues) {
		this.amountDues = amountDues;
	}

	public String getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getInfoNumber() {
		return infoNumber;
	}

	public void setInfoNumber(String infoNumber) {
		this.infoNumber = infoNumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getPaymentFreq() {
		return paymentFreq;
	}

	public void setPaymentFreq(String paymentFreq) {
		this.paymentFreq = paymentFreq;
	}


	
	
	

}
