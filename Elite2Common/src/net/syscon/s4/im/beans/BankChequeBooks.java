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
public class BankChequeBooks extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountNumber")
	@NotNull
	@Size(max = 25)
	private String accountNumber;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("firstCheckNumber")
	@NotNull
	private BigDecimal firstCheckNumber;

	@JsonProperty("lastCheckNumber")
	@NotNull
	private BigDecimal lastCheckNumber;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("nextCheckNumber")
	private BigDecimal nextCheckNumber;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;

	@JsonProperty("caseloadId")
	@NotNull
	@Size(max = 6)
	private String caseloadId;

	@JsonProperty("accountCode")
	@NotNull
	private Long accountCode;

	@JsonProperty("chequeBookSeq")
	@NotNull
	private Long chequeBookSeq;
	
	@JsonProperty("checkNumber")
	private Integer checkNumber;
	
	@JsonProperty("rowId")
	private Long rowId;

	/**
	 * Creates new BankChequeBooks class Object
	 */
	public BankChequeBooks() {
		// BankChequeBooks
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	 * @return the firstCheckNumber
	 */
	public BigDecimal getFirstCheckNumber() {
		return firstCheckNumber;
	}

	/**
	 * @param firstCheckNumber
	 *            the firstCheckNumber to set
	 */
	public void setFirstCheckNumber(BigDecimal firstCheckNumber) {
		this.firstCheckNumber = firstCheckNumber;
	}

	/**
	 * @return the lastCheckNumber
	 */
	public BigDecimal getLastCheckNumber() {
		return lastCheckNumber;
	}

	/**
	 * @param lastCheckNumber
	 *            the lastCheckNumber to set
	 */
	public void setLastCheckNumber(BigDecimal lastCheckNumber) {
		this.lastCheckNumber = lastCheckNumber;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
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
	 * @return the nextCheckNumber
	 */
	public BigDecimal getNextCheckNumber() {
		return nextCheckNumber;
	}

	/**
	 * @param nextCheckNumber
	 *            the nextCheckNumber to set
	 */
	public void setNextCheckNumber(BigDecimal nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
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
	 * @return the accountCode
	 */
	public Long getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode
	 *            the accountCode to set
	 */
	public void setAccountCode(Long accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the chequeBookSeq
	 */
	public Long getChequeBookSeq() {
		return chequeBookSeq;
	}

	/**
	 * @param chequeBookSeq
	 *            the chequeBookSeq to set
	 */
	public void setChequeBookSeq(Long chequeBookSeq) {
		this.chequeBookSeq = chequeBookSeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the checkNumber
	 */
	public Integer getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(final Integer checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * @return the rowId
	 */
	public Long getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(final Long rowId) {
		this.rowId = rowId;
	}

}
