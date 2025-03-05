package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAssocPNotif extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("notificationCode")
	private String notificationCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderAssociatedParty")
	private OffenderAssociatedParty offenderAssociatedParty;

	@JsonProperty("associatedPartyId")
	private long associatedPartyId;

	@JsonProperty("notificationSeq")
	private long notificationSeq;

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
	public String getNotificationCode() {
		return notificationCode;
	}

	/**
	 *
	 * @param notificationCode
	 */
	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
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
	public OffenderAssociatedParty getOffenderAssociatedParty() {
		return offenderAssociatedParty;
	}

	/**
	 *
	 * @param offenderAssociatedParty
	 */
	public void setOffenderAssociatedParty(OffenderAssociatedParty offenderAssociatedParty) {
		this.offenderAssociatedParty = offenderAssociatedParty;
	}

	/**
	 *
	 * @return
	 */
	public long getAssociatedPartyId() {
		return associatedPartyId;
	}

	/**
	 *
	 * @param associatedPartyId
	 */
	public void setAssociatedPartyId(long associatedPartyId) {
		this.associatedPartyId = associatedPartyId;
	}

	/**
	 *
	 * @return
	 */
	public long getNotificationSeq() {
		return notificationSeq;
	}

	/**
	 *
	 * @param notificationSeq
	 */
	public void setNotificationSeq(long notificationSeq) {
		this.notificationSeq = notificationSeq;
	}

}