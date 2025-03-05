package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the FREEZE_DISBURSEMENTS database table.
 * 
 */
public class FreezeDisbursements implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("freezeFlag")
	private String freezeFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("accountCode")
	private Long accountCode;

	@JsonProperty("txnType")
	private String txnType;
	
	@JsonProperty("subAccountType")
	private String subAccountType;
	
	@JsonProperty("reg")
	private String reg;
	
	@JsonProperty("txnDescription")
	private String txnDescription;
	
	@JsonProperty("accountName")
	private String accountName;
	
	@JsonProperty("txnUsage")
	private String txnUsage;
	

	
	
	public Long getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(final Long accountCode) {
		this.accountCode = accountCode;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

	public FreezeDisbursements() {
		// FreezeDisbursements
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public Object getCreateDatetime() {
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

	public String getFreezeFlag() {
		return this.freezeFlag;
	}

	public void setFreezeFlag(final String freezeFlag) {
		this.freezeFlag = freezeFlag;
	}

	public Object getModifyDatetime() {
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
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
	 * @return the reg
	 */
	public String getReg() {
		return reg;
	}

	/**
	 * @param reg the reg to set
	 */
	public void setReg(final String reg) {
		this.reg = reg;
	}

	/**
	 * @return the txnDescription
	 */
	public String getTxnDescription() {
		return txnDescription;
	}

	/**
	 * @param txnDescription the txnDescription to set
	 */
	public void setTxnDescription(final String txnDescription) {
		this.txnDescription = txnDescription;
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
	 * @return the txnUsage
	 */
	public String getTxnUsage() {
		return txnUsage;
	}

	/**
	 * @param txnUsage the txnUsage to set
	 */
	public void setTxnUsage(final String txnUsage) {
		this.txnUsage = txnUsage;
	}

	

}
