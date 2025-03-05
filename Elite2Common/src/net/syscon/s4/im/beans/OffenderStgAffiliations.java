package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderStgAffiliations extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("appealDate")
	private Date appealDate;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("expiredBy")
	private String expiredBy;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("expiryReasonCode")
	private String expiryReasonCode;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("notifiedBy")
	private String notifiedBy;

	@JsonProperty("notifiedDate")
	private Date notifiedDate;

	@JsonProperty("reasonCode")
	private String reasonCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("stgId")
	private BigDecimal stgId;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("stgSeq")
	private BigDecimal stgSeq;

	@JsonProperty("description")
	private String description;

	@JsonProperty("notifiedByTemp")
	private String notifiedByTemp;
	public OffenderStgAffiliations() {
		// OffenderStgAffiliations
	}
	
	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getAppealDate() {
		return appealDate;
	}

	public void setAppealDate(final Date appealDate) {
		this.appealDate = appealDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiredBy() {
		return expiredBy;
	}

	public void setExpiredBy(final String expiredBy) {
		this.expiredBy = expiredBy;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExpiryReasonCode() {
		return expiryReasonCode;
	}

	public void setExpiryReasonCode(final String expiryReasonCode) {
		this.expiryReasonCode = expiryReasonCode;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNotifiedBy() {
		return notifiedBy;
	}

	public void setNotifiedBy(final String notifiedBy) {
		this.notifiedBy = notifiedBy;
	}

	public Date getNotifiedDate() {
		return notifiedDate;
	}

	public void setNotifiedDate(final Date notifiedDate) {
		this.notifiedDate = notifiedDate;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public String getNotifiedByTemp() {
		return notifiedByTemp;
	}

	public void setNotifiedByTemp(String notifiedByTemp) {
		this.notifiedByTemp = notifiedByTemp;
	}

	public void setReasonCode(final String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getStgId() {
		return stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getStgSeq() {
		return stgSeq;
	}

	public void setStgSeq(final BigDecimal stgSeq) {
		this.stgSeq = stgSeq;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
