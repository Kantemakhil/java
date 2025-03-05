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
public class OffenderTrustAccounts  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountClosedFlag")
	private String accountClosedFlag;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("currentBalance")
	private Double currentBalance;

	@JsonProperty("holdBalance")
	private Double holdBalance;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("notifyDate")
	private Date notifyDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("nbtTxnId")
	private Integer nbtTxnId;
	
	@JsonProperty("offenderIdDisplay")
	@Size(max = 10)
	private String offenderIdDisplay;
	
	@JsonProperty("firstName")
	@Size(max = 35)
	private String firstName;
	
	@JsonProperty("lastName")
	@NotNull
	private String lastName;

	/**
	 * Creates new OffenderAlerts class Date
	 */
	public OffenderTrustAccounts() {
		// OffenderTrustAccounts
	}

	/**
	 * @return the accountClosedFlag
	 */
	public String getAccountClosedFlag() {
		return accountClosedFlag;
	}

	/**
	 * @param accountClosedFlag
	 *            the accountClosedFlag to set
	 */
	public void setAccountClosedFlag(final String accountClosedFlag) {
		this.accountClosedFlag = accountClosedFlag;
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
	public Double getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * @param currentBalance
	 *            the currentBalance to set
	 */
	public void setCurrentBalance(final Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**
	 * @return the holdBalance
	 */
	public Double getHoldBalance() {
		return holdBalance;
	}

	/**
	 * @param holdBalance
	 *            the holdBalance to set
	 */
	public void setHoldBalance(final Double holdBalance) {
		this.holdBalance = holdBalance;
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
	 * @return the notifyDate
	 */
	public Date getNotifyDate() {
		return notifyDate;
	}

	/**
	 * @param notifyDate
	 *            the notifyDate to set
	 */
	public void setNotifyDate(final Date notifyDate) {
		this.notifyDate = notifyDate;
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
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the nbtTxnId
	 */
	public Integer getNbtTxnId() {
		return nbtTxnId;
	}

	/**
	 * @param nbtTxnId the nbtTxnId to set
	 */
	public void setNbtTxnId(final Integer nbtTxnId) {
		this.nbtTxnId = nbtTxnId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
