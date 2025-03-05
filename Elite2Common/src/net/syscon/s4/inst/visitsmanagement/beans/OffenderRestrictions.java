package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderRestrictions
 * 
 */
public class OffenderRestrictions extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderRestrictionId")
	private long offenderRestrictionId;
	@JsonProperty("authorisedStaffId")
	private BigDecimal authorisedStaffId;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	@JsonProperty("enteredStaffId")
	private BigDecimal enteredStaffId;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("restrictionType")
	private String restrictionType;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("visitDate") 
	private Date visitDate;
	@JsonProperty("personId") 
	private Integer personId;
	@JsonProperty("restrictionDesc")
	private String restrictionDesc;
	@JsonProperty("username")
	private String username;

	public OffenderRestrictions() {
		// OffenderRestrictions
	}

	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(final Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the pesonId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param pesonId the pesonId to set
	 */
	public void setPersonId(final Integer personId) {
		this.personId = personId;
	}

	public long getOffenderRestrictionId() {
		return this.offenderRestrictionId;
	}

	public void setOffenderRestrictionId(final long offenderRestrictionId) {
		this.offenderRestrictionId = offenderRestrictionId;
	}

	public BigDecimal getAuthorisedStaffId() {
		return this.authorisedStaffId;
	}

	public void setAuthorisedStaffId(final BigDecimal authorisedStaffId) {
		this.authorisedStaffId = authorisedStaffId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getEnteredStaffId() {
		return this.enteredStaffId;
	}

	public void setEnteredStaffId(final BigDecimal enteredStaffId) {
		this.enteredStaffId = enteredStaffId;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getRestrictionType() {
		return this.restrictionType;
	}

	public void setRestrictionType(final String restrictionType) {
		this.restrictionType = restrictionType;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the restrictionDesc
	 */
	public String getRestrictionDesc() {
		return restrictionDesc;
	}

	/**OffenderRestrictions
	 * @param restrictionDesc the restrictionDesc to set
	 */
	public void setRestrictionDesc(String restrictionDesc) {
		this.restrictionDesc = restrictionDesc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
