package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

public class OffenderHwdCharges implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long hwdId;
	private Long hwdChargeId;
	private Long caseId;
	private String chargeCode;
	private String chargeComment;
	private String triedUntriedCode;
	private String chargeStatus;
	private String createUserId;
	private Date createDatetime;
	private String modifyUserId;
	private Date modifyDatetime;
	private String sealFlag;
	private String caseIdValue;
	
	public String getCaseIdValue() {
		return caseIdValue;
	}

	public void setCaseIdValue(final String caseIdValue) {
		this.caseIdValue = caseIdValue;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(final Long caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the hwdId
	 */
	public Long getHwdId() {
		return hwdId;
	}

	/**
	 * @param hwdId
	 *            the hwdId to set
	 */
	public void setHwdId(final Long hwdId) {
		this.hwdId = hwdId;
	}

	/**
	 * @return the hwdChargeId
	 */
	public Long getHwdChargeId() {
		return hwdChargeId;
	}

	/**
	 * @param hwdChargeId
	 *            the hwdChargeId to set
	 */
	public void setHwdChargeId(final Long hwdChargeId) {
		this.hwdChargeId = hwdChargeId;
	}

	/**
	 * @return the chargeCode
	 */
	public String getChargeCode() {
		return chargeCode;
	}

	/**
	 * @param chargeCode
	 *            the chargeCode to set
	 */
	public void setChargeCode(final String chargeCode) {
		this.chargeCode = chargeCode;
	}

	/**
	 * @return the chargeComment
	 */
	public String getChargeComment() {
		return chargeComment;
	}

	/**
	 * @param chargeComment
	 *            the chargeComment to set
	 */
	public void setChargeComment(final String chargeComment) {
		this.chargeComment = chargeComment;
	}

	/**
	 * @return the triedUntriedCode
	 */
	public String getTriedUntriedCode() {
		return triedUntriedCode;
	}

	/**
	 * @param triedUntriedCode
	 *            the triedUntriedCode to set
	 */
	public void setTriedUntriedCode(final String triedUntriedCode) {
		this.triedUntriedCode = triedUntriedCode;
	}

	/**
	 * @return the chargeStatus
	 */
	public String getChargeStatus() {
		return chargeStatus;
	}

	/**
	 * @param chargeStatus
	 *            the chargeStatus to set
	 */
	public void setChargeStatus(final String chargeStatus) {
		this.chargeStatus = chargeStatus;
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

}
