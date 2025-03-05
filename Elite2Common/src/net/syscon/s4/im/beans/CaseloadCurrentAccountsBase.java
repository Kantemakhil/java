package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadCurrentAccountsBase extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountPartyType")
	private String accountPartyType;
	@JsonProperty("bankAccountNumber")
	private String bankAccountNumber;
	@JsonProperty("bankAccountType")
	private String bankAccountType;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("currentBalance")
	private BigDecimal currentBalance;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;
	@JsonProperty("payeePersonId")
	private BigDecimal payeePersonId;
	@JsonProperty("routingNumber")
	private BigDecimal routingNumber;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("caseloadType")
	private String caseloadType;
	@JsonProperty("accountCode")
	private Long accountCode;
	@JsonProperty("accountPeriodId")
	private Long accountPeriodId;
	@JsonProperty("corporateName")
	private String corporateName;

	public CaseloadCurrentAccountsBase() {
		// CaseloadCurrentAccountsBase
	}

	/**
	 * @return the corporateName
	 */
	public String getCorporateName() {
		return corporateName;
	}

	/**
	 * @param corporateName
	 *            the corporateName to set
	 */
	public void setCorporateName(final String corporateName) {
		this.corporateName = corporateName;
	}

	/**
	 * @return the accountPeriodId
	 */
	public Long getAccountPeriodId() {
		return accountPeriodId;
	}

	/**
	 * @param accountPeriodId
	 *            the accountPeriodId to set
	 */
	public void setAccountPeriodId(final Long accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
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
	public Long getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode
	 *            the accountCode to set
	 */
	public void setAccountCode(final Long accountCode) {
		this.accountCode = accountCode;
	}
}
