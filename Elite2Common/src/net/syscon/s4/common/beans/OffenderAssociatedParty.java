package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAssociatedParty extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("associatedPartyId")
	private long associatedPartyId;

	@JsonProperty("age")
	private BigDecimal age;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("ethnicity")
	private String ethnicity;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("partyId")
	private String partyId;

	@JsonProperty("partyType")
	private String partyType;

	@JsonProperty("relationshipCode")
	private String relationshipCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("victimEmailAddr")
	private String victimEmailAddr;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	@JsonProperty("offenderCas")
	private OffenderCas offenderCas;

	@JsonProperty("offenderAssocPartyNotes")
	private List<OffenderAssocPartyNote> offenderAssocPartyNotes;

	@JsonProperty("offenderAssocPrtyContacts")
	private List<OffenderAssocPrtyContact> offenderAssocPrtyContacts;

	@JsonProperty("offenderAssocPNotifs")
	private List<OffenderAssocPNotif> offenderAssocPNotifs;

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
	public BigDecimal getAge() {
		return age;
	}

	/**
	 *
	 * @param age
	 */
	public void setAge(BigDecimal age) {
		this.age = age;
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
	public String getEthnicity() {
		return ethnicity;
	}

	/**
	 *
	 * @param ethnicity
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
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
	public String getPartyId() {
		return partyId;
	}

	/**
	 *
	 * @param partyId
	 */
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	/**
	 *
	 * @return
	 */
	public String getPartyType() {
		return partyType;
	}

	/**
	 *
	 * @param partyType
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	/**
	 *
	 * @return
	 */
	public String getRelationshipCode() {
		return relationshipCode;
	}

	/**
	 *
	 * @param relationshipCode
	 */
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
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
	public String getVictimEmailAddr() {
		return victimEmailAddr;
	}

	/**
	 *
	 * @param victimEmailAddr
	 */
	public void setVictimEmailAddr(String victimEmailAddr) {
		this.victimEmailAddr = victimEmailAddr;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 *
	 * @param offenderBooking
	 */
	public void setOffenderBooking(OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
	}

	/**
	 *
	 * @return
	 */
	public OffenderCas getOffenderCas() {
		return offenderCas;
	}

	/**
	 *
	 * @param offenderCas
	 */
	public void setOffenderCas(OffenderCas offenderCas) {
		this.offenderCas = offenderCas;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAssocPartyNote> getOffenderAssocPartyNotes() {
		return offenderAssocPartyNotes;
	}

	/**
	 *
	 * @param offenderAssocPartyNotes
	 */
	public void setOffenderAssocPartyNotes(List<OffenderAssocPartyNote> offenderAssocPartyNotes) {
		this.offenderAssocPartyNotes = offenderAssocPartyNotes;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAssocPrtyContact> getOffenderAssocPrtyContacts() {
		return offenderAssocPrtyContacts;
	}

	/**
	 *
	 * @param offenderAssocPrtyContacts
	 */
	public void setOffenderAssocPrtyContacts(List<OffenderAssocPrtyContact> offenderAssocPrtyContacts) {
		this.offenderAssocPrtyContacts = offenderAssocPrtyContacts;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAssocPNotif> getOffenderAssocPNotifs() {
		return offenderAssocPNotifs;
	}

	/**
	 *
	 * @param offenderAssocPNotifs
	 */
	public void setOffenderAssocPNotifs(List<OffenderAssocPNotif> offenderAssocPNotifs) {
		this.offenderAssocPNotifs = offenderAssocPNotifs;
	}

}