package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class BankChequeData extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("txnId")
	@NotNull
	private Long txnId;

	@JsonProperty("chequeDate")
	@NotNull
	private Date chequeDate;

	@JsonProperty("chequeFlag")
	private String chequeFlag;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("endTxnId")
	private BigDecimal endTxnId;

	@JsonProperty("modifyDate")
	@NotNull
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;

	@JsonProperty("payeeNameText")
	private String payeeNameText;

	@JsonProperty("payeePersonId")
	private BigDecimal payeePersonId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("singleEntryFlag")
	private String singleEntryFlag;

	@JsonProperty("startTxnId")
	private BigDecimal startTxnId;

	@JsonProperty("txnEntryAmount")
	@NotNull
	private BigDecimal txnEntryAmount;

	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;

	@JsonProperty("bankAccountCode")
	@NotNull
	private Long bankAccountCode;

	@JsonProperty("caseloadId")
	@NotNull
	@Size(max = 6)
	private String caseloadId;
	
	@JsonProperty("closedFlag")
	private String closedFlag;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("txnType")
	private String txnType;
	
	@JsonProperty("checkAmnt")
	private Double checkAmnt;
	
	@JsonProperty("corporateName")
	private String corporateName;
	
	@JsonProperty("offenderPayee")
	private BigDecimal offenderPayee;
	
	
	
	public BigDecimal getOffenderPayee() {
		return offenderPayee;
	}

	public void setOffenderPayee(BigDecimal offenderPayee) {
		this.offenderPayee = offenderPayee;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public Double getCheckAmnt() {
		return checkAmnt;
	}

	public void setCheckAmnt(Double checkAmnt) {
		this.checkAmnt = checkAmnt;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}



	/**
	 * Creates new BankChequeData class Object
	 */
	public BankChequeData() {
		// BankChequeData
	}

	/**
	 * @return the txnId
	 */
	public Long getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId
	 *            the txnId to set
	 */
	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the chequeDate
	 */
	public Date getChequeDate() {
		return chequeDate;
	}

	/**
	 * @param chequeDate
	 *            the chequeDate to set
	 */
	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	/**
	 * @return the chequeFlag
	 */
	public String getChequeFlag() {
		return chequeFlag;
	}

	/**
	 * @param chequeFlag
	 *            the chequeFlag to set
	 */
	public void setChequeFlag(String chequeFlag) {
		this.chequeFlag = chequeFlag;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the endTxnId
	 */
	public BigDecimal getEndTxnId() {
		return endTxnId;
	}

	/**
	 * @param endTxnId
	 *            the endTxnId to set
	 */
	public void setEndTxnId(BigDecimal endTxnId) {
		this.endTxnId = endTxnId;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the payeeCorporateId
	 */
	public BigDecimal getPayeeCorporateId() {
		return payeeCorporateId;
	}

	/**
	 * @param payeeCorporateId
	 *            the payeeCorporateId to set
	 */
	public void setPayeeCorporateId(BigDecimal payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
	}

	/**
	 * @return the payeeNameText
	 */
	public String getPayeeNameText() {
		return payeeNameText;
	}

	/**
	 * @param payeeNameText
	 *            the payeeNameText to set
	 */
	public void setPayeeNameText(String payeeNameText) {
		this.payeeNameText = payeeNameText;
	}

	/**
	 * @return the payeePersonId
	 */
	public BigDecimal getPayeePersonId() {
		return payeePersonId;
	}

	/**
	 * @param payeePersonId
	 *            the payeePersonId to set
	 */
	public void setPayeePersonId(BigDecimal payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the singleEntryFlag
	 */
	public String getSingleEntryFlag() {
		return singleEntryFlag;
	}

	/**
	 * @param singleEntryFlag
	 *            the singleEntryFlag to set
	 */
	public void setSingleEntryFlag(String singleEntryFlag) {
		this.singleEntryFlag = singleEntryFlag;
	}

	/**
	 * @return the startTxnId
	 */
	public BigDecimal getStartTxnId() {
		return startTxnId;
	}

	/**
	 * @param startTxnId
	 *            the startTxnId to set
	 */
	public void setStartTxnId(BigDecimal startTxnId) {
		this.startTxnId = startTxnId;
	}

	/**
	 * @return the txnEntryAmount
	 */
	public BigDecimal getTxnEntryAmount() {
		return txnEntryAmount;
	}

	/**
	 * @param txnEntryAmount
	 *            the txnEntryAmount to set
	 */
	public void setTxnEntryAmount(BigDecimal txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

	/**
	 * @return the txnEntryDate
	 */
	public Date getTxnEntryDate() {
		return txnEntryDate;
	}

	/**
	 * @param txnEntryDate
	 *            the txnEntryDate to set
	 */
	public void setTxnEntryDate(Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the bankAccountCode
	 */
	public Long getBankAccountCode() {
		return bankAccountCode;
	}

	/**
	 * @param bankAccountCode
	 *            the bankAccountCode to set
	 */
	public void setBankAccountCode(Long bankAccountCode) {
		this.bankAccountCode = bankAccountCode;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the closedFlag
	 */
	public String getClosedFlag() {
		return closedFlag;
	}

	/**
	 * @param closedFlag the closedFlag to set
	 */
	public void setClosedFlag(final String closedFlag) {
		this.closedFlag = closedFlag;
	}

}
