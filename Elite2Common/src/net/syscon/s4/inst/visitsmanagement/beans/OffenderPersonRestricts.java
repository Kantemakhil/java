package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderPersonRestricts extends BaseModel implements Serializable {

	@JsonProperty("offenderContactPersonId")
	private Integer offenderContactPersonId;
	@JsonProperty("offenderPersonRestrictId")
	private Integer offenderPersonRestrictId;
	@JsonProperty("restrictionType")
	private String restrictionType;
	@JsonProperty("restrictionEffectiveDate")
	private Date restrictionEffectiveDate;
	@JsonProperty("restrictionExpiryDate")
	private Date restrictionExpiryDate;
	@JsonProperty("authorizedStaffId")
	private Integer authorizedStaffId;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("enteredStaffId")
	private Integer enteredStaffId;
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
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("description")
	private String description;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("personId")
	private Long personId;

	@JsonProperty("stringEnteredStaffId")
	private String stringEnteredStaffId;

	/**
	 * @return the offenderContactPersonId
	 */
	public Integer getOffenderContactPersonId() {
		return offenderContactPersonId;
	}

	/**
	 * @param offenderContactPersonId the offenderContactPersonId to set
	 */
	public void setOffenderContactPersonId(Integer offenderContactPersonId) {
		this.offenderContactPersonId = offenderContactPersonId;
	}

	/**
	 * @return the offenderPersonRestrictId
	 */
	public Integer getOffenderPersonRestrictId() {
		return offenderPersonRestrictId;
	}

	/**
	 * @param offenderPersonRestrictId the offenderPersonRestrictId to set
	 */
	public void setOffenderPersonRestrictId(Integer offenderPersonRestrictId) {
		this.offenderPersonRestrictId = offenderPersonRestrictId;
	}

	/**
	 * @return the restrictionType
	 */
	public String getRestrictionType() {
		return restrictionType;
	}

	/**
	 * @param restrictionType the restrictionType to set
	 */
	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}

	/**
	 * @return the restrictionEffectiveDate
	 */
	public Date getRestrictionEffectiveDate() {
		return restrictionEffectiveDate;
	}

	/**
	 * @param restrictionEffectiveDate the restrictionEffectiveDate to set
	 */
	public void setRestrictionEffectiveDate(Date restrictionEffectiveDate) {
		this.restrictionEffectiveDate = restrictionEffectiveDate;
	}

	/**
	 * @return the restrictionExpiryDate
	 */
	public Date getRestrictionExpiryDate() {
		return restrictionExpiryDate;
	}

	/**
	 * @param restrictionExpiryDate the restrictionExpiryDate to set
	 */
	public void setRestrictionExpiryDate(Date restrictionExpiryDate) {
		this.restrictionExpiryDate = restrictionExpiryDate;
	}

	/**
	 * @return the authorizedStaffId
	 */
	public Integer getAuthorizedStaffId() {
		return authorizedStaffId;
	}

	/**
	 * @param authorizedStaffId the authorizedStaffId to set
	 */
	public void setAuthorizedStaffId(Integer authorizedStaffId) {
		this.authorizedStaffId = authorizedStaffId;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the enteredStaffId
	 */
	public Integer getEnteredStaffId() {
		return enteredStaffId;
	}

	/**
	 * @param enteredStaffId the enteredStaffId to set
	 */
	public void setEnteredStaffId(Integer enteredStaffId) {
		this.enteredStaffId = enteredStaffId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getStringEnteredStaffId() {
		return stringEnteredStaffId;
	}

	public void setStringEnteredStaffId(String stringEnteredStaffId) {
		this.stringEnteredStaffId = stringEnteredStaffId;
	}
}