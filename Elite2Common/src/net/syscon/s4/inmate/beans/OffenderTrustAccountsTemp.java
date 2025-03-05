package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderTrustAccountsTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountClosedFlag")
	private String accountClosedFlag;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("currentBalance")
	private BigDecimal currentBalance;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("holdBalance")
	private BigDecimal holdBalance;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("movementDate")
	private Date movementDate;
	@JsonProperty("notifyDate")
	private Date notifyDate;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("offenderId")
	private Long offenderId;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("sessionId")
	private Long sessionId;
	@JsonProperty("totalAmount")
	private BigDecimal totalAmount;
	@JsonProperty("cbntAccountClosedFlag")
	private String cbntAccountClosedFlag;
	@JsonProperty("cgnbtModifyUserId")
	private String cgnbtModifyUserId;
	@JsonProperty("caseloadType")
	private String caseloadType;
	@JsonProperty("sessionIdTemp")
	private Long sessionIdTemp;
	@JsonProperty("createDateTimeTemp")
	private Date createDateTimeTemp;
	@JsonProperty("modifyDateTimeTemp")
	private Date modifyDateTimeTemp;
	@JsonProperty("lastNameTemp")
	private String lastNameTemp;
	@JsonProperty("sealFlagTemp")
	private String sealFlagTemp;
	@JsonProperty("isValid")
	private Integer isValid;
	@JsonProperty("selectionMethod")
	private String selectionMethod;
	
	
	
	

	public String getSelectionMethod() {
		return selectionMethod;
	}

	public void setSelectionMethod(String selectionMethod) {
		this.selectionMethod = selectionMethod;
	}

	/**
	 * @return the OffenderTrustAccountsTemp
	 */
	public OffenderTrustAccountsTemp() {
		// OffenderTrustAccountsTemp
	}

	/**
	 * @return the isValid
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * @param isValid
	 *            the isValid to set
	 */
	public void setIsValid(final Integer isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the sealFlagTemp
	 */
	public String getSealFlagTemp() {
		return sealFlagTemp;
	}

	/**
	 * @param sealFlagTemp
	 *            the sealFlagTemp to set
	 */
	public void setSealFlagTemp(final String sealFlagTemp) {
		this.sealFlagTemp = sealFlagTemp;
	}

	/**
	 * @return the sessionIdTemp
	 */
	public Long getSessionIdTemp() {
		return sessionIdTemp;
	}

	/**
	 * @param sessionIdTemp
	 *            the sessionIdTemp to set
	 */
	public void setSessionIdTemp(final Long sessionIdTemp) {
		this.sessionIdTemp = sessionIdTemp;
	}

	/**
	 * @return the createDateTimeTemp
	 */
	public Date getCreateDateTimeTemp() {
		return createDateTimeTemp;
	}

	/**
	 * @param createDateTimeTemp
	 *            the createDateTimeTemp to set
	 */
	public void setCreateDateTimeTemp(final Date createDateTimeTemp) {
		this.createDateTimeTemp = createDateTimeTemp;
	}

	/**
	 * @return the modifyDateTimeTemp
	 */
	public Date getModifyDateTimeTemp() {
		return modifyDateTimeTemp;
	}

	/**
	 * @param modifyDateTimeTemp
	 *            the modifyDateTimeTemp to set
	 */
	public void setModifyDateTimeTemp(final Date modifyDateTimeTemp) {
		this.modifyDateTimeTemp = modifyDateTimeTemp;
	}

	/**
	 * @return the lastNameTemp
	 */
	public String getLastNameTemp() {
		return lastNameTemp;
	}

	/**
	 * @param lastNameTemp
	 *            the lastNameTemp to set
	 */
	public void setLastNameTemp(final String lastNameTemp) {
		this.lastNameTemp = lastNameTemp;
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
	 * @return the cbntAccountClosedFlag
	 */
	public String getCbntAccountClosedFlag() {
		return cbntAccountClosedFlag;
	}

	/**
	 * @return the cgnbtModifyUserId
	 */
	public String getCgnbtModifyUserId() {
		return cgnbtModifyUserId;
	}

	/**
	 * @param cgnbtModifyUserId
	 *            the cgnbtModifyUserId to set
	 */
	public void setCgnbtModifyUserId(final String cgnbtModifyUserId) {
		this.cgnbtModifyUserId = cgnbtModifyUserId;
	}

	/**
	 * @param cbntAccountClosedFlag
	 *            the cbntAccountClosedFlag to set
	 */
	public void setCbntAccountClosedFlag(final String cbntAccountClosedFlag) {
		this.cbntAccountClosedFlag = cbntAccountClosedFlag;
	}

	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(final BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the holdBalance
	 */
	public BigDecimal getHoldBalance() {
		return holdBalance;
	}

	/**
	 * @param holdBalance
	 *            the holdBalance to set
	 */
	public void setHoldBalance(final BigDecimal holdBalance) {
		this.holdBalance = holdBalance;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
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
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the movementDate
	 */
	public Date getMovementDate() {
		return movementDate;
	}

	/**
	 * @param movementDate
	 *            the movementDate to set
	 */
	public void setMovementDate(final Date movementDate) {
		this.movementDate = movementDate;
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
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
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
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(final Long sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
