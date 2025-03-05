package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderPostDatedTxns extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("pendTxnId")
	private Long pendTxnId;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("payeeCorporateId")
	private BigDecimal payeeCorporateId;
	@JsonProperty("payeeNameText")
	private String payeeNameText;
	@JsonProperty("payeePersonId")
	private BigDecimal payeePersonId;
	@JsonProperty("payeeType")
	private String payeeType;
	@JsonProperty("releaseDate")
	private Date releaseDate;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("txnEntryAmount")
	private BigDecimal txnEntryAmount;
	@JsonProperty("txnEntryDesc")
	private String txnEntryDesc;
	@JsonProperty("txnType")
	private String txnType;

	/**
	 * Creates new OffenderPostDatedTxns class Object
	 */
	public OffenderPostDatedTxns() {
		// OffenderPostDatedTxns
	}

	/**
	 * @return the pendTxnId
	 */
	public Long getPendTxnId() {
		return pendTxnId;
	}

	/**
	 * @param pendTxnId
	 *            the pendTxnId to set
	 */
	public void setPendTxnId(final Long pendTxnId) {
		this.pendTxnId = pendTxnId;
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
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
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
	public void setOffenderId(final BigDecimal offenderId) {
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
	public void setPayeeCorporateId(final BigDecimal payeeCorporateId) {
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
	public void setPayeeNameText(final String payeeNameText) {
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
	public void setPayeePersonId(final BigDecimal payeePersonId) {
		this.payeePersonId = payeePersonId;
	}

	/**
	 * @return the payeeType
	 */
	public String getPayeeType() {
		return payeeType;
	}

	/**
	 * @param payeeType
	 *            the payeeType to set
	 */
	public void setPayeeType(final String payeeType) {
		this.payeeType = payeeType;
	}

	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 *            the releaseDate to set
	 */
	public void setReleaseDate(final Date releaseDate) {
		this.releaseDate = releaseDate;
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
	 * @return the txnEntryAmount
	 */
	public BigDecimal getTxnEntryAmount() {
		return txnEntryAmount;
	}

	/**
	 * @param txnEntryAmount
	 *            the txnEntryAmount to set
	 */
	public void setTxnEntryAmount(final BigDecimal txnEntryAmount) {
		this.txnEntryAmount = txnEntryAmount;
	}

	/**
	 * @return the txnEntryDesc
	 */
	public String getTxnEntryDesc() {
		return txnEntryDesc;
	}

	/**
	 * @param txnEntryDesc
	 *            the txnEntryDesc to set
	 */
	public void setTxnEntryDesc(final String txnEntryDesc) {
		this.txnEntryDesc = txnEntryDesc;
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

}
