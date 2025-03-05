package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class TransactionTypes
 */
@XmlRootElement
public class TransactionTypes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("txnType")
	private String txnType;
	@JsonProperty("description")
	private String description;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("txnUsage")
	private String txnUsage;
	@JsonProperty("allCaseloadFlag")
	private String allCaseloadFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;
	@JsonProperty("manualInvoiceFlag")
	private String manualInvoiceFlag;
	@JsonProperty("creditObligationType")
	private String creditObligationType;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("grossNetFlag")
	private String grossNetFlag;
	@JsonProperty("caseloadType")
	private String caseloadType;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("days")
	private Integer days;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("code")
	private String code;
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	
	@JsonProperty("accountCode")
	private Integer accountCode;
	@JsonProperty("subAccountType")
	private String subAccountType;
	@JsonProperty("accountName")
	private String accountName;
	
	@JsonProperty("receiptTxnType")
	private String receiptTxnType;
	
	@JsonProperty("jnOperation")
	private String jnOperation;

	@JsonProperty("jnOracleUser")
	private String jnOracleUser;
	
	@JsonProperty("jnDatetime")
	private Date jnDatetime;
	
	@JsonProperty("jnNotes")
	private String jnNotes;
	
	@JsonProperty("jnAppln")
	private String jnAppln;
	
	@JsonProperty("jnSession")
	private BigDecimal jnSession;

	

	/**
	 * @return the canDisplay
	 */
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay
	 *            the canDisplay to set
	 */
	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the txnUsage
	 */
	public String getTxnUsage() {
		return txnUsage;
	}

	/**
	 * @param txnUsage
	 *            the txnUsage to set
	 */
	public void setTxnUsage(final String txnUsage) {
		this.txnUsage = txnUsage;
	}

	/**
	 * @return the allCaseloadFlag
	 */
	public String getAllCaseloadFlag() {
		return allCaseloadFlag;
	}

	/**
	 * @param allCaseloadFlag
	 *            the allCaseloadFlag to set
	 */
	public void setAllCaseloadFlag(final String allCaseloadFlag) {
		this.allCaseloadFlag = allCaseloadFlag;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
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
	 * @return the manualInvoiceFlag
	 */
	public String getManualInvoiceFlag() {
		return manualInvoiceFlag;
	}

	/**
	 * @param manualInvoiceFlag
	 *            the manualInvoiceFlag to set
	 */
	public void setManualInvoiceFlag(final String manualInvoiceFlag) {
		this.manualInvoiceFlag = manualInvoiceFlag;
	}

	/**
	 * @return the creditObligationType
	 */
	public String getCreditObligationType() {
		return creditObligationType;
	}

	/**
	 * @param creditObligationType
	 *            the creditObligationType to set
	 */
	public void setCreditObligationType(final String creditObligationType) {
		this.creditObligationType = creditObligationType;
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
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the grossNetFlag
	 */
	public String getGrossNetFlag() {
		return grossNetFlag;
	}

	/**
	 * @param grossNetFlag
	 *            the grossNetFlag to set
	 */
	public void setGrossNetFlag(final String grossNetFlag) {
		this.grossNetFlag = grossNetFlag;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
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
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(final Integer days) {
		this.days = days;
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
	 * @return the accountCode
	 */
	public Integer getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final Integer accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the subAccountType
	 */
	public String getSubAccountType() {
		return subAccountType;
	}

	/**
	 * @param subAccountType the subAccountType to set
	 */
	public void setSubAccountType(final String subAccountType) {
		this.subAccountType = subAccountType;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(final String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the receiptTxnType
	 */
	public String getReceiptTxnType() {
		return receiptTxnType;
	}

	/**
	 * @param receiptTxnType the receiptTxnType to set
	 */
	public void setReceiptTxnType(final String receiptTxnType) {
		this.receiptTxnType = receiptTxnType;
	}

	public String getJnOperation() {
		return jnOperation;
	}

	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}

	public String getJnOracleUser() {
		return jnOracleUser;
	}

	public void setJnOracleUser(String jnOracleUser) {
		this.jnOracleUser = jnOracleUser;
	}

	public Date getJnDatetime() {
		return jnDatetime;
	}

	public void setJnDatetime(Date jnDatetime) {
		this.jnDatetime = jnDatetime;
	}

	public String getJnNotes() {
		return jnNotes;
	}

	public void setJnNotes(String jnNotes) {
		this.jnNotes = jnNotes;
	}

	public String getJnAppln() {
		return jnAppln;
	}

	public void setJnAppln(String jnAppln) {
		this.jnAppln = jnAppln;
	}

	public BigDecimal getJnSession() {
		return jnSession;
	}

	public void setJnSession(BigDecimal jnSession) {
		this.jnSession = jnSession;
	}

	
}