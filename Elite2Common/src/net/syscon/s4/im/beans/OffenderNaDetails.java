package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_NA_DETAILS database table.
 * 
 */
public class OffenderNaDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("authorizedStaff")
	private String authorizedStaff;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("nsEffectiveDate")
	private Date nsEffectiveDate;

	@JsonProperty("nsExpiryDate")
	private Date nsExpiryDate;

	@JsonProperty("nsLevelCode")
	private String nsLevelCode;

	@JsonProperty("nsOffenderBookId")
	private BigDecimal nsOffenderBookId;

	@JsonProperty("nsReasonCode")
	private String nsReasonCode;

	@JsonProperty("nsType")
	private String nsType;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("recipNsReasonCode")
	private String recipNsReasonCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("nsOffenderId")
	private Long nsOffenderId;

	@JsonProperty("typeSeq")
	private Long typeSeq;

	@JsonProperty("activeFlag")
	private String activeFlag;
	
	public OffenderNaDetails() {
		// OffenderNaDetails
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	public Long getNsOffenderId() {
		return nsOffenderId;
	}

	public void setNsOffenderId(final Long nsOffenderId) {
		this.nsOffenderId = nsOffenderId;
	}

	public Long getTypeSeq() {
		return typeSeq;
	}

	public void setTypeSeq(final Long typeSeq) {
		this.typeSeq = typeSeq;
	}


	public String getAuthorizedStaff() {
		return this.authorizedStaff;
	}

	public void setAuthorizedStaff(final String authorizedStaff) {
		this.authorizedStaff = authorizedStaff;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getNsEffectiveDate() {
		return this.nsEffectiveDate;
	}

	public void setNsEffectiveDate(final Date nsEffectiveDate) {
		this.nsEffectiveDate = nsEffectiveDate;
	}

	public Date getNsExpiryDate() {
		return this.nsExpiryDate;
	}

	public void setNsExpiryDate(final Date nsExpiryDate) {
		this.nsExpiryDate = nsExpiryDate;
	}

	public String getNsLevelCode() {
		return this.nsLevelCode;
	}

	public void setNsLevelCode(final String nsLevelCode) {
		this.nsLevelCode = nsLevelCode;
	}

	public BigDecimal getNsOffenderBookId() {
		return this.nsOffenderBookId;
	}

	public void setNsOffenderBookId(final BigDecimal nsOffenderBookId) {
		this.nsOffenderBookId = nsOffenderBookId;
	}

	public String getNsReasonCode() {
		return this.nsReasonCode;
	}

	public void setNsReasonCode(final String nsReasonCode) {
		this.nsReasonCode = nsReasonCode;
	}

	public String getNsType() {
		return this.nsType;
	}

	public void setNsType(final String nsType) {
		this.nsType = nsType;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getRecipNsReasonCode() {
		return this.recipNsReasonCode;
	}

	public void setRecipNsReasonCode(final String recipNsReasonCode) {
		this.recipNsReasonCode = recipNsReasonCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

}
