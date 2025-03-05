package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.BankChequeData;


/**
 * class BankChequeRegister
 * 
 */
public class BankChequeRegisters extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;


	@JsonProperty("chequePaidDate")
	private Date chequePaidDate;

	@JsonProperty("chequeStatus")
	private String chequeStatus;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("endTxnId")
	private BigDecimal endTxnId;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("originType")
	private String originType;

	@JsonProperty("rcnlClr")
	private String rcnlClr;

	@JsonProperty("reasonText")
	private String reasonText;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("startTxnId")
	private BigDecimal startTxnId;

	@JsonProperty("transactionDate")
	private Date transactionDate;

	@JsonProperty("txnId")
	private BigDecimal txnId;
	
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty("accountCode")
	private Long accountCode;

	@JsonProperty("chequeNumber")
	private Long chequeNumber;
	
	@JsonProperty("firstCheckNumber")
	private Integer firstCheckNumber;
	
	@JsonProperty("lastCheckNumber")
	private Integer lastCheckNumber;
	
	@JsonProperty("nextCheckNumber")
	private Integer nextCheckNumber;
	
	@JsonProperty("payeeNameText")
	private String payeeNameText;
	
	@JsonProperty("chequeFlag")
	private String chequeFlag;
	
	@JsonProperty("reconClearFlag")
	private String reconClearFlag;
	
	@JsonProperty("bankStatementDate")
	private Date bankStatementDate;
	
	@JsonProperty("referenceType")
	private String referenceType;
	
	@JsonProperty("referenceNo")
	private Integer referenceNo;
	
	

	public BankChequeRegisters() {
		//BankChequeRegister
	}


	public Date getChequePaidDate() {
		return this.chequePaidDate;
	}

	public void setChequePaidDate(final Date chequePaidDate) {
		this.chequePaidDate = chequePaidDate;
	}

	public String getChequeStatus() {
		return this.chequeStatus;
	}

	public void setChequeStatus(final String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
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

	public BigDecimal getEndTxnId() {
		return this.endTxnId;
	}

	public void setEndTxnId(final BigDecimal endTxnId) {
		this.endTxnId = endTxnId;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
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

	public String getOriginType() {
		return this.originType;
	}

	public void setOriginType(final String originType) {
		this.originType = originType;
	}

	public String getRcnlClr() {
		return this.rcnlClr;
	}

	public void setRcnlClr(final String rcnlClr) {
		this.rcnlClr = rcnlClr;
	}

	public String getReasonText() {
		return this.reasonText;
	}

	public void setReasonText(final String reasonText) {
		this.reasonText = reasonText;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getStartTxnId() {
		return this.startTxnId;
	}

	public void setStartTxnId(final BigDecimal startTxnId) {
		this.startTxnId = startTxnId;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(final Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getTxnId() {
		return this.txnId;
	}

	public void setTxnId(final BigDecimal txnId) {
		this.txnId = txnId;
	}


	/**
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}


	/**
	 * @param caseLoadId the caseLoadId to set
	 */
	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}


	/**
	 * @return the accountCode
	 */
	public Long getAccountCode() {
		return accountCode;
	}


	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final Long accountCode) {
		this.accountCode = accountCode;
	}


	/**
	 * @return the chequeNumber
	 */
	public Long getChequeNumber() {
		return chequeNumber;
	}


	/**
	 * @param chequeNumber the chequeNumber to set
	 */
	public void setChequeNumber(final Long chequeNumber) {
		this.chequeNumber = chequeNumber;
	}


	/**
	 * @return the firstCheckNumber
	 */
	public Integer getFirstCheckNumber() {
		return firstCheckNumber;
	}


	/**
	 * @param firstCheckNumber the firstCheckNumber to set
	 */
	public void setFirstCheckNumber(final Integer firstCheckNumber) {
		this.firstCheckNumber = firstCheckNumber;
	}


	/**
	 * @return the lastCheckNumber
	 */
	public Integer getLastCheckNumber() {
		return lastCheckNumber;
	}


	/**
	 * @param lastCheckNumber the lastCheckNumber to set
	 */
	public void setLastCheckNumber(final Integer lastCheckNumber) {
		this.lastCheckNumber = lastCheckNumber;
	}


	/**
	 * @return the nextCheckNumber
	 */
	public Integer getNextCheckNumber() {
		return nextCheckNumber;
	}


	/**
	 * @param nextCheckNumber the nextCheckNumber to set
	 */
	public void setNextCheckNumber(final Integer nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}


	/**
	 * @return the payeeNameText
	 */
	public String getPayeeNameText() {
		return payeeNameText;
	}


	/**
	 * @param payeeNameText the payeeNameText to set
	 */
	public void setPayeeNameText(final String payeeNameText) {
		this.payeeNameText = payeeNameText;
	}


	/**
	 * @return the chequeFlag
	 */
	public String getChequeFlag() {
		return chequeFlag;
	}


	/**
	 * @param chequeFlag the chequeFlag to set
	 */
	public void setChequeFlag(final String chequeFlag) {
		this.chequeFlag = chequeFlag;
	}


	/**
	 * @return the reconClearFlag
	 */
	public String getReconClearFlag() {
		return reconClearFlag;
	}


	/**
	 * @param reconClearFlag the reconClearFlag to set
	 */
	public void setReconClearFlag(String reconClearFlag) {
		this.reconClearFlag = reconClearFlag;
	}


	/**
	 * @return the bankStatementDate
	 */
	public Date getBankStatementDate() {
		return bankStatementDate;
	}


	/**
	 * @param bankStatementDate the bankStatementDate to set
	 */
	public void setBankStatementDate(Date bankStatementDate) {
		this.bankStatementDate = bankStatementDate;
	}


	/**
	 * @return the referenceType
	 */
	public String getReferenceType() {
		return referenceType;
	}


	/**
	 * @param referenceType the referenceType to set
	 */
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}


	/**
	 * @return the referenceNo
	 */
	public Integer getReferenceNo() {
		return referenceNo;
	}


	/**
	 * @param referenceNo the referenceNo to set
	 */
	public void setReferenceNo(Integer referenceNo) {
		this.referenceNo = referenceNo;
	}

}