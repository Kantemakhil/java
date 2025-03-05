package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StgRelationships extends BaseModel {

	@JsonProperty("stgId")
	private BigDecimal stgId;
	@JsonProperty("relationshipSeq")
	private BigDecimal relationshipSeq;
	@JsonProperty("relationshipType")
	private String relationshipType;
	@JsonProperty("relatedStgId")
	private BigDecimal relatedStgId;
	@JsonProperty("reason")
	private String reason;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty(" activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("expiredBy")
	private String expiredBy;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("nbtRelatedStgId")
	private Object nbtRelatedStgId;
	@JsonProperty("butViewMembers")
	private Object butViewMembers;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("scheduledTripId")
	private Long scheduledTripId;
	@JsonProperty("moduleName")
	private String moduleName;

	public void setStgId(BigDecimal stgId) {
		this.stgId = stgId;
	}

	public void setRelationshipSeq(BigDecimal relationshipSeq) {
		this.relationshipSeq = relationshipSeq;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public void setRelatedStgId(BigDecimal relatedStgId) {
		this.relatedStgId = relatedStgId;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setExpiredBy(String expiredBy) {
		this.expiredBy = expiredBy;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getNbtRelatedStgId() {
		return this.nbtRelatedStgId;
	}

	public void setNbtRelatedStgId(final Object nbtRelatedStgId) {
		this.nbtRelatedStgId = nbtRelatedStgId;
	}

	public Object getRelatedStgId() {
		return this.relatedStgId;
	}

	public Object getButViewMembers() {
		return this.butViewMembers;
	}

	public void setButViewMembers(final Object butViewMembers) {
		this.butViewMembers = butViewMembers;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
