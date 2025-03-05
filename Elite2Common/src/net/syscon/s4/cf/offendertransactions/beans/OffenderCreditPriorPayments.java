package net.syscon.s4.cf.offendertransactions.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;



/**
 * The persistent class for the OFFENDER_CREDIT_PRIOR_PAYMENTS database table.
 * 
 */
public class OffenderCreditPriorPayments extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseloadId;

	private String commentText;

	private Date createDatetime;

	private String createUserId;

	private String location;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal offenderId;

	private BigDecimal paymentAmount;

	private Date paymentDate;

	private String sealFlag;


	private long txnId;

	private long txnEntrySeq;

	public OffenderCreditPriorPayments() {
	}

	public long getTxnId() {
		return txnId;
	}

	public void setTxnId(long txnId) {
		this.txnId = txnId;
	}

	public long getTxnEntrySeq() {
		return txnEntrySeq;
	}

	public void setTxnEntrySeq(long txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}



}
