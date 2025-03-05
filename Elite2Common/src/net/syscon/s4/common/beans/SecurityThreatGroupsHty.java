package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the INTEREST_TRANSACTION_TYPES database table.
 * 
 */
public class SecurityThreatGroupsHty implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("trustAccountCode")
	private Long trustAccountCode;
	@JsonProperty("txnType")
	private String txnType;	

	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("subAccountType")
	private String subAccountType;
	
	@JsonProperty("accountName")
	private String accountName;
	
	@JsonProperty("stgId")
	private BigDecimal stgId;
	
	@JsonProperty("toPStgId")
	private Integer toPStgId;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("action")
	private String action;
	
	@JsonProperty("toStgLevel")
	private String toStgLevel;

	@JsonProperty("htySeq")
	private BigDecimal htySeq;
	
	@JsonProperty("fromPStgId")
	private Integer fromPStgId;
	@JsonProperty("fromStgLevel")
	private String fromStgLevel;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	
	
	public Integer getFromPStgId() {
		return fromPStgId;
	}

	public void setFromPStgId(final Integer integer) {
		this.fromPStgId = integer;
	}

	public String getFromStgLevel() {
		return fromStgLevel;
	}

	public void setFromStgLevel(final String fromStgLevel) {
		this.fromStgLevel = fromStgLevel;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(final Integer integer) {
		this.listSeq = integer;
	}

	public String getAction() {
		return action;
	}

	public void setAction(final String action) {
		this.action = action;
	}

	public String getToStgLevel() {
		return toStgLevel;
	}

	public void setToStgLevel(final String toStgLevel) {
		this.toStgLevel = toStgLevel;
	}

	public BigDecimal getHtySeq() {
		return htySeq;
	}

	public void setHtySeq(final BigDecimal hytSeq) {
		this.htySeq = hytSeq;
	}

	/**
	 * Creates new InterestTransactionTypes class Object
	 */
	public SecurityThreatGroupsHty() {
		// InterestTransactionTypes
	}
	
	public String getSubAccountType() {
		return subAccountType;
	}
	public void setSubAccountType(final String subAccountType) {
		this.subAccountType = subAccountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(final String accountName) {
		this.accountName = accountName;
	}
	
	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
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
	 * @return the trustAccountCode
	 */
	public Long getTrustAccountCode() {
		return trustAccountCode;
	}

	/**
	 * @param trustAccountCode
	 *            the trustAccountCode to set
	 */
	public void setTrustAccountCode(final Long trustAccountCode) {
		this.trustAccountCode = trustAccountCode;
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

	public BigDecimal getStgId() {
		return stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

	public Integer getToPStgId() {
		return toPStgId;
	}

	public void setToPStgId(final Integer integer) {
		this.toPStgId = integer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

}
