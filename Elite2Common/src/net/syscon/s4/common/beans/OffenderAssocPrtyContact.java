package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAssocPrtyContact extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("notificationFlag")
	private String notificationFlag;

	@JsonProperty("personId")
	private java.math.BigDecimal personId;

	@JsonProperty("primaryContactFlag")
	private String primaryContactFlag;

	@JsonProperty("relationship")
	private String relationship;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderAssociatedParty")
	private OffenderAssociatedParty offenderAssociatedParty;

	@JsonProperty("offenderAssocPCntNotifs")
	private List<OffenderAssocPCntNotif> offenderAssocPCntNotifs;

	@JsonProperty("associatedPartyId")
	private long associatedPartyId;

	@JsonProperty("partySeq")
	private long partySeq;

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
	public String getNotificationFlag() {
		return notificationFlag;
	}

	/**
	 *
	 * @param notificationFlag
	 */
	public void setNotificationFlag(String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}

	/**
	 *
	 * @return
	 */
	public java.math.BigDecimal getPersonId() {
		return personId;
	}

	/**
	 *
	 * @param personId
	 */
	public void setPersonId(java.math.BigDecimal personId) {
		this.personId = personId;
	}

	/**
	 *
	 * @return
	 */
	public String getPrimaryContactFlag() {
		return primaryContactFlag;
	}

	/**
	 *
	 * @param primaryContactFlag
	 */
	public void setPrimaryContactFlag(String primaryContactFlag) {
		this.primaryContactFlag = primaryContactFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 *
	 * @param relationship
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
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
	public List<OffenderAssocPCntNotif> getOffenderAssocPCntNotifs() {
		return offenderAssocPCntNotifs;
	}

	/**
	 *
	 * @param offenderAssocPCntNotifs
	 */
	public void setOffenderAssocPCntNotifs(List<OffenderAssocPCntNotif> offenderAssocPCntNotifs) {
		this.offenderAssocPCntNotifs = offenderAssocPCntNotifs;
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
	public long getPartySeq() {
		return partySeq;
	}

	/**
	 *
	 * @param partySeq
	 */
	public void setPartySeq(long partySeq) {
		this.partySeq = partySeq;
	}

}