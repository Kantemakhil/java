package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderHwdCharge extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("hwdChargeId")
	private long hwdChargeId;

	@JsonProperty("chargeCode")
	private String chargeCode;

	@JsonProperty("chargeComment")
	private String chargeComment;

	@JsonProperty("chargeStatus")
	private String chargeStatus;

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

	@JsonProperty("triedUntriedCode")
	private String triedUntriedCode;

	@JsonProperty("offenderHwd")
	private OffenderHwd offenderHwd;

	/**
	 *
	 * @return
	 */
	public long getHwdChargeId() {
		return hwdChargeId;
	}

	/**
	 *
	 * @param hwdChargeId
	 */
	public void setHwdChargeId(long hwdChargeId) {
		this.hwdChargeId = hwdChargeId;
	}

	/**
	 *
	 * @return
	 */
	public String getChargeCode() {
		return chargeCode;
	}

	/**
	 *
	 * @param chargeCode
	 */
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	/**
	 *
	 * @return
	 */
	public String getChargeComment() {
		return chargeComment;
	}

	/**
	 *
	 * @param chargeComment
	 */
	public void setChargeComment(String chargeComment) {
		this.chargeComment = chargeComment;
	}

	/**
	 *
	 * @return
	 */
	public String getChargeStatus() {
		return chargeStatus;
	}

	/**
	 *
	 * @param chargeStatus
	 */
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getTriedUntriedCode() {
		return triedUntriedCode;
	}

	/**
	 *
	 * @param triedUntriedCode
	 */
	public void setTriedUntriedCode(String triedUntriedCode) {
		this.triedUntriedCode = triedUntriedCode;
	}

	/**
	 *
	 * @return
	 */
	public OffenderHwd getOffenderHwd() {
		return offenderHwd;
	}

	/**
	 *
	 * @param offenderHwd
	 */
	public void setOffenderHwd(OffenderHwd offenderHwd) {
		this.offenderHwd = offenderHwd;
	}

}