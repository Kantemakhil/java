package net.syscon.s4.cf.offendertransactions.beans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the PAYMENT_PLAN_TRANSACTIONS database table.
 * 
 */
public class PaymentPlanTransaction extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal groupId;

	private String informationNumber;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private BigDecimal transactionAmount;

	private Date transactionDate;

	private long paymentPlanId;

	private long paymentPlanSeq;

	private Date paymentDate;

	private long transactionSeq;
	
	private String description;
	
	private String code;
	private BigDecimal offenderBookId;
	private BigDecimal offenderId;
	
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public PaymentPlanTransaction() {
	}

	public long getPaymentPlanId() {
		return paymentPlanId;
	}

	public void setPaymentPlanId(long paymentPlanId) {
		this.paymentPlanId = paymentPlanId;
	}

	public long getPaymentPlanSeq() {
		return paymentPlanSeq;
	}

	public void setPaymentPlanSeq(long paymentPlanSeq) {
		this.paymentPlanSeq = paymentPlanSeq;
	}




	public long getTransactionSeq() {
		return transactionSeq;
	}

	public void setTransactionSeq(long transactionSeq) {
		this.transactionSeq = transactionSeq;
	}




	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}




}

