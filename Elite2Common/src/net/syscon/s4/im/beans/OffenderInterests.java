package net.syscon.s4.im.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderInterests {
	@JsonProperty("fromDate")
	private Date fromDate;
	@JsonProperty("toDate")
	private Date toDate;
	@JsonProperty("txnType")
	private String txnType;
	@JsonProperty("interestRate")
	private Integer interestRate;
	@JsonProperty("startTxnId")
	private Integer startTxnId;
	@JsonProperty("endTxnId")
	private Integer endTxnId;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	public OffenderInterests() {
	//	OffenderInterests
	}
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}
	/**
	 * @param txnType the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}
	/**
	 * @return the interestRate
	 */
	public Integer getInterestRate() {
		return interestRate;
	}
	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(final Integer interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * @return the startTxnId
	 */
	public Integer getStartTxnId() {
		return startTxnId;
	}
	/**
	 * @param startTxnId the startTxnId to set
	 */
	public void setStartTxnId(final Integer startTxnId) {
		this.startTxnId = startTxnId;
	}
	/**
	 * @return the endTxnId
	 */
	public Integer getEndTxnId() {
		return endTxnId;
	}
	/**
	 * @param endTxnId the endTxnId to set
	 */
	public void setEndTxnId(final Integer endTxnId) {
		this.endTxnId = endTxnId;
	}
	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}
	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * @param modifyUserId the modifyUserId to set
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
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	
}
