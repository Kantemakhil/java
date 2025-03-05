package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class PersonProfiles
 */
public class PersonProfiles implements Serializable {

	@JsonProperty("personProfileId")
	private Integer personProfileId;
	@JsonProperty("personId")
	private Integer personId;
	@JsonProperty("profileType")
	private String profileType;
	@JsonProperty("profileCode")
	private String profileCode;
	@JsonProperty("displaySeq")
	private Integer displaySeq;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("profileComment")
	private String profileComment;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("nbtProfileType")
	private String nbtProfileType;
	@JsonProperty("nbtProfileCode")
	private String nbtProfileCode;
	@JsonProperty("codeValueType")
	private String codeValueType;

	/**
	 * @return the nbtProfileType
	 */
	public String getNbtProfileType() {
		return nbtProfileType;
	}

	/**
	 * @param nbtProfileType
	 *            the nbtProfileType to set
	 */
	public void setNbtProfileType(String nbtProfileType) {
		this.nbtProfileType = nbtProfileType;
	}

	/**
	 * @return the nbtProfileCode
	 */
	public String getNbtProfileCode() {
		return nbtProfileCode;
	}

	/**
	 * @param nbtProfileCode
	 *            the nbtProfileCode to set
	 */
	public void setNbtProfileCode(String nbtProfileCode) {
		this.nbtProfileCode = nbtProfileCode;
	}

	private boolean inserted;

	/**
	 * @param personProfileId
	 *            personProfileId to set
	 */
	public void setPersonProfileId(final Integer personProfileId) {
		this.personProfileId = personProfileId;
	}

	/**
	 * return thepersonProfileId
	 */
	public Integer getPersonProfileId() {
		return this.personProfileId;
	}

	/**
	 * @param personId
	 *            personId to set
	 */
	public void setPersonId(final Integer personId) {
		this.personId = personId;
	}

	/**
	 * return thepersonId
	 */
	public Integer getPersonId() {
		return this.personId;
	}

	/**
	 * @param profileType
	 *            profileType to set
	 */
	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

	/**
	 * return theprofileType
	 */
	public String getProfileType() {
		return this.profileType;
	}

	/**
	 * @param profileCode
	 *            profileCode to set
	 */
	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * return theprofileCode
	 */
	public String getProfileCode() {
		return this.profileCode;
	}

	/**
	 * @param displaySeq
	 *            displaySeq to set
	 */
	public void setDisplaySeq(final Integer displaySeq) {
		this.displaySeq = displaySeq;
	}

	/**
	 * return thedisplaySeq
	 */
	public Integer getDisplaySeq() {
		return this.displaySeq;
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
	 * @param profileComment
	 *            profileComment to set
	 */
	public void setProfileComment(final String profileComment) {
		this.profileComment = profileComment;
	}

	/**
	 * return theprofileComment
	 */
	public String getProfileComment() {
		return this.profileComment;
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

	public String getCodeValueType() {
		return codeValueType;
	}

	public void setCodeValueType(String codeValueType) {
		this.codeValueType = codeValueType;
	}

}