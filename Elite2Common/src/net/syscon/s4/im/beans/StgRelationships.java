package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the STG_RELATIONSHIPS database table.
 * 
 */
public class StgRelationships extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;
	
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
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("relatedStgId")
	private BigDecimal relatedStgId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("stgId")
	private Long stgId;
	
	@JsonProperty("relationshipSeq")
	private Long relationshipSeq;
	
	@JsonProperty("relationshipType")
	private String relationshipType;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("description")
	private String description;

	public StgRelationships() {
		// StgRelationships
	}

	public Long getStgId() {
		return stgId;
	}

	public void setStgId(final Long stgId) {
		this.stgId = stgId;
	}

	public Long getRelationshipSeq() {
		return relationshipSeq;
	}

	public void setRelationshipSeq(final Long relationshipSeq) {
		this.relationshipSeq = relationshipSeq;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(final String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
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

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiredBy() {
		return this.expiredBy;
	}

	public void setExpiredBy(final String expiredBy) {
		this.expiredBy = expiredBy;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getReason() {
		return this.reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public BigDecimal getRelatedStgId() {
		return this.relatedStgId;
	}

	public void setRelatedStgId(final BigDecimal relatedStgId) {
		this.relatedStgId = relatedStgId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
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
	public void setDescription(final String description) {
		this.description = description;
	}

}
