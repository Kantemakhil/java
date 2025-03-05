package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CaseloadCurrentAccounts extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private BigDecimal accountCode;

	@JsonProperty("accountPartyType")
	private String accountPartyType;

	@JsonProperty("accountPeriodId")
	private BigDecimal accountPeriodId;

	@JsonProperty("bankAccountNumber")
	private String bankAccountNumber;

	@JsonProperty("bankAccountType")
	private String bankAccountType;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("currentBalance")
	private BigDecimal currentBalance;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;

	@JsonProperty("payeePersonId")
	private BigDecimal payeePersonId;

	@JsonProperty("routingNumber")
	private BigDecimal routingNumber;

	@JsonProperty("globalCaseloadType")
	private String globalCaseloadType;

	@JsonProperty("dspTxnPostingType")
	private String dspTxnPostingType;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("caseloadCurrentAccountId")
	private Long caseloadCurrentAccountId;
	
	public Long getCaseloadCurrentAccountId() {
		return caseloadCurrentAccountId;
	}

	public void setCaseloadCurrentAccountId(Long caseloadCurrentAccountId) {
		this.caseloadCurrentAccountId = caseloadCurrentAccountId;
	}

	/**
	 * Creates new CaseloadCurrentAccounts class Object
	 */
	public CaseloadCurrentAccounts() {
		// CaseloadCurrentAccounts
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
	 * @return the accountCode
	 */
	public BigDecimal getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode
	 *            the accountCode to set
	 */
	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the accountPeriodId
	 */
	public BigDecimal getAccountPeriodId() {
		return accountPeriodId;
	}

	/**
	 * @param accountPeriodId
	 *            the accountPeriodId to set
	 */
	public void setAccountPeriodId(final BigDecimal accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	/**
	 * @return the currentBalance
	 */
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * @param currentBalance
	 *            the currentBalance to set
	 */
	public void setCurrentBalance(final BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**
	 * @return the bankAccountType
	 */
	public String getBankAccountType() {
		return bankAccountType;
	}

	/**
	 * @param bankAccountType
	 *            the bankAccountType to set
	 */
	public void setBankAccountType(final String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber
	 *            the bankAccountNumber to set
	 */
	public void setBankAccountNumber(final String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the accountPartyType
	 */
	public String getAccountPartyType() {
		return accountPartyType;
	}

	/**
	 * @param accountPartyType
	 *            the accountPartyType to set
	 */
	public void setAccountPartyType(final String accountPartyType) {
		this.accountPartyType = accountPartyType;
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
	public void setPayeePersonId(final BigDecimal payeePersonId) {
		this.payeePersonId = payeePersonId;
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
	public void setPayeeCorporateId(final BigDecimal payeeCorporateId) {
		this.payeeCorporateId = payeeCorporateId;
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
	 * @return the routingNumber
	 */
	public BigDecimal getRoutingNumber() {
		return routingNumber;
	}

	/**
	 * @param routingNumber
	 *            the routingNumber to set
	 */
	public void setRoutingNumber(final BigDecimal routingNumber) {
		this.routingNumber = routingNumber;
	}

	/**
	 * @return the globalCaseloadType
	 */
	public String getGlobalCaseloadType() {
		return globalCaseloadType;
	}

	/**
	 * @param globalCaseloadType
	 *            the globalCaseloadType to set
	 */
	public void setGlobalCaseloadType(String globalCaseloadType) {
		this.globalCaseloadType = globalCaseloadType;
	}

	/**
	 * @return the dspTxnPostingType
	 */
	public String getDspTxnPostingType() {
		return dspTxnPostingType;
	}

	/**
	 * @param dspTxnPostingType
	 *            the dspTxnPostingType to set
	 */
	public void setDspTxnPostingType(String dspTxnPostingType) {
		this.dspTxnPostingType = dspTxnPostingType;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
