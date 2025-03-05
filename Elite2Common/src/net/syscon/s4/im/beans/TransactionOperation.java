package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class TransactionOperation implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("bankCrAccountCode")
	private BigDecimal bankCrAccountCode;

	@JsonProperty("bankDrAccountCode")
	private BigDecimal bankDrAccountCode;

	@JsonProperty("chequePayeeType")
	private String chequePayeeType;

	@JsonProperty("chequeProductionFlag")
	private String chequeProductionFlag;

	@JsonProperty("crAccountCode")
	private BigDecimal crAccountCode;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("drAccountCode")
	private BigDecimal drAccountCode;

	@JsonProperty("invalidAccountsFlag")
	private String invalidAccountsFlag;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("receiptProductionFlag")
	private String receiptProductionFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("txnItemCode")
	private String txnItemCode;

	@JsonProperty("txnOperationType")
	private String txnOperationType;

	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("txnType")
	private String txnType;

	@JsonProperty("txnOperationSeq")
	private Long txnOperationSeq;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("txnUsage")
	private String txnUsage;

	@JsonProperty("code")
	private String code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("drAccountCodeTemp")
	private String drAccountCodeTemp;

	@JsonProperty("crAccountCodeTemp")
	private String crAccountCodeTemp;

	@JsonProperty("bankDrAccountCodeTemp")
	private String bankDrAccountCodeTemp;

	@JsonProperty("bankCrAccountCodeTemp")
	private String bankCrAccountCodeTemp;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("txnPostingType")
	private String txnPostingType;

	/**
	 * Creates new TransactionOperation class Object
	 */
	public TransactionOperation() {
		// TransactionOperation
	}

	/**
	 * @return the bankDrAccountCodeTemp
	 */
	public String getBankDrAccountCodeTemp() {
		return bankDrAccountCodeTemp;
	}

	/**
	 * @param bankDrAccountCodeTemp
	 *            the bankDrAccountCodeTemp to set
	 */
	public void setBankDrAccountCodeTemp(String bankDrAccountCodeTemp) {
		this.bankDrAccountCodeTemp = bankDrAccountCodeTemp;
	}

	/**
	 * @return the bankCrAccountCodeTemp
	 */
	public String getBankCrAccountCodeTemp() {
		return bankCrAccountCodeTemp;
	}

	/**
	 * @param bankCrAccountCodeTemp
	 *            the bankCrAccountCodeTemp to set
	 */
	public void setBankCrAccountCodeTemp(String bankCrAccountCodeTemp) {
		this.bankCrAccountCodeTemp = bankCrAccountCodeTemp;
	}

	/**
	 * @return the crAccountCodeTemp
	 */
	public String getCrAccountCodeTemp() {
		return crAccountCodeTemp;
	}

	/**
	 * @param crAccountCodeTemp
	 *            the crAccountCodeTemp to set
	 */
	public void setCrAccountCodeTemp(String crAccountCodeTemp) {
		this.crAccountCodeTemp = crAccountCodeTemp;
	}

	/**
	 * @return the drAccountCodeTemp
	 */
	public String getDrAccountCodeTemp() {
		return drAccountCodeTemp;
	}

	/**
	 * @param drAccountCodeTemp
	 *            the drAccountCodeTemp to set
	 */
	public void setDrAccountCodeTemp(String drAccountCodeTemp) {
		this.drAccountCodeTemp = drAccountCodeTemp;
	}

	/**
	 * @return the bankCrAccountCode
	 */
	public BigDecimal getBankCrAccountCode() {
		return bankCrAccountCode;
	}

	/**
	 * @param bankCrAccountCode
	 *            the bankCrAccountCode to set
	 */
	public void setBankCrAccountCode(final BigDecimal bankCrAccountCode) {
		this.bankCrAccountCode = bankCrAccountCode;
	}

	/**
	 * @return the bankDrAccountCode
	 */
	public BigDecimal getBankDrAccountCode() {
		return bankDrAccountCode;
	}

	/**
	 * @param bankDrAccountCode
	 *            the bankDrAccountCode to set
	 */
	public void setBankDrAccountCode(final BigDecimal bankDrAccountCode) {
		this.bankDrAccountCode = bankDrAccountCode;
	}

	/**
	 * @return the chequePayeeType
	 */
	public String getChequePayeeType() {
		return chequePayeeType;
	}

	/**
	 * @param chequePayeeType
	 *            the chequePayeeType to set
	 */
	public void setChequePayeeType(final String chequePayeeType) {
		this.chequePayeeType = chequePayeeType;
	}

	/**
	 * @return the chequeProductionFlag
	 */
	public String getChequeProductionFlag() {
		return chequeProductionFlag;
	}

	/**
	 * @param chequeProductionFlag
	 *            the chequeProductionFlag to set
	 */
	public void setChequeProductionFlag(final String chequeProductionFlag) {
		this.chequeProductionFlag = chequeProductionFlag;
	}

	/**
	 * @return the crAccountCode
	 */
	public BigDecimal getCrAccountCode() {
		return crAccountCode;
	}

	/**
	 * @param crAccountCode
	 *            the crAccountCode to set
	 */
	public void setCrAccountCode(final BigDecimal crAccountCode) {
		this.crAccountCode = crAccountCode;
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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the drAccountCode
	 */
	public BigDecimal getDrAccountCode() {
		return drAccountCode;
	}

	/**
	 * @param drAccountCode
	 *            the drAccountCode to set
	 */
	public void setDrAccountCode(final BigDecimal drAccountCode) {
		this.drAccountCode = drAccountCode;
	}

	/**
	 * @return the invalidAccountsFlag
	 */
	public String getInvalidAccountsFlag() {
		return invalidAccountsFlag;
	}

	/**
	 * @param invalidAccountsFlag
	 *            the invalidAccountsFlag to set
	 */
	public void setInvalidAccountsFlag(final String invalidAccountsFlag) {
		this.invalidAccountsFlag = invalidAccountsFlag;
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
	public void setListSeq(final BigDecimal listSeq) {
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
	public void setModifyDate(final Date modifyDate) {
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
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the receiptProductionFlag
	 */
	public String getReceiptProductionFlag() {
		return receiptProductionFlag;
	}

	/**
	 * @param receiptProductionFlag
	 *            the receiptProductionFlag to set
	 */
	public void setReceiptProductionFlag(final String receiptProductionFlag) {
		this.receiptProductionFlag = receiptProductionFlag;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the txnItemCode
	 */
	public String getTxnItemCode() {
		return txnItemCode;
	}

	/**
	 * @param txnItemCode
	 *            the txnItemCode to set
	 */
	public void setTxnItemCode(final String txnItemCode) {
		this.txnItemCode = txnItemCode;
	}

	/**
	 * @return the txnOperationType
	 */
	public String getTxnOperationType() {
		return txnOperationType;
	}

	/**
	 * @param txnOperationType
	 *            the txnOperationType to set
	 */
	public void setTxnOperationType(final String txnOperationType) {
		this.txnOperationType = txnOperationType;
	}

	/**
	 * @return the updateAllowedFlag
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 * @param updateAllowedFlag
	 *            the updateAllowedFlag to set
	 */
	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	/**
	 * @return the txnOperationSeq
	 */
	public Long getTxnOperationSeq() {
		return txnOperationSeq;
	}

	/**
	 * @param txnOperationSeq
	 *            the txnOperationSeq to set
	 */
	public void setTxnOperationSeq(final Long txnOperationSeq) {
		this.txnOperationSeq = txnOperationSeq;
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
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTxnUsage() {
		return txnUsage;
	}

	public void setTxnUsage(String txnUsage) {
		this.txnUsage = txnUsage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}
	
	public String getTxnPostingType() {
		return txnPostingType;
	}

	public void setTxnPostingType(String txnPostingType) {
		this.txnPostingType = txnPostingType;
	}

}
