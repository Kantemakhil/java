package net.syscon.s4.im.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class OffenderDemogrants
 */
public class OffenderDemogrants {

	@JsonProperty("demograntId")
	private Integer demograntId;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("amount")
	private Integer amount;
	@JsonProperty("demograntDate")
	private Date demograntDate;
	@JsonProperty("startTxnId")
	private Integer startTxnId;
	@JsonProperty("endTxnId")
	private Integer endTxnId;
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
	private boolean inserted;

	/**
	 * @param demograntId
	 *            demograntId to set
	 */
	public void setDemograntId(final Integer demograntId) {
		this.demograntId = demograntId;
	}

	/**
	 * return thedemograntId
	 */
	public Integer getDemograntId() {
		return this.demograntId;
	}

	/**
	 * @param caseloadId
	 *            caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * return thecaseloadId
	 */
	public String getCaseloadId() {
		return this.caseloadId;
	}

	/**
	 * @param amount
	 *            amount to set
	 */
	public void setAmount(final Integer amount) {
		this.amount = amount;
	}

	/**
	 * return theamount
	 */
	public Integer getAmount() {
		return this.amount;
	}

	/**
	 * @param demograntDate
	 *            demograntDate to set
	 */
	public void setDemograntDate(final Date demograntDate) {
		this.demograntDate = demograntDate;
	}

	/**
	 * return thedemograntDate
	 */
	public Date getDemograntDate() {
		return this.demograntDate;
	}

	/**
	 * @param startTxnId
	 *            startTxnId to set
	 */
	public void setStartTxnId(final Integer startTxnId) {
		this.startTxnId = startTxnId;
	}

	/**
	 * return thestartTxnId
	 */
	public Integer getStartTxnId() {
		return this.startTxnId;
	}

	/**
	 * @param endTxnId
	 *            endTxnId to set
	 */
	public void setEndTxnId(final Integer endTxnId) {
		this.endTxnId = endTxnId;
	}

	/**
	 * return theendTxnId
	 */
	public Integer getEndTxnId() {
		return this.endTxnId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

}