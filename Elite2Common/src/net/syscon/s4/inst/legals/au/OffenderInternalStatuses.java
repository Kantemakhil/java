package net.syscon.s4.inst.legals.au;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderInternalStatuses {
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("internalStatusSeq")
	private Integer internalStatusSeq;
	@JsonProperty("internalStatus")
	private String internalStatus;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("intStsReasonCode")
	private String intStsReasonCode;
	@JsonProperty("recordStatus")
	private String recordStatus;
	@JsonProperty("isRequestedPartyType")
	private String isRequestedPartyType;
	@JsonProperty("durationCode")
	private String durationCode;
	@JsonProperty("effectiveTime")
	private Date effectiveTime;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("createDate")
	private Date createDate;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("authorizedPersonText")
	private String authorizedPersonText;
	@JsonProperty("revokedBy")
	private Integer revokedBy;
	@JsonProperty("authPersonPostCode")
	private String authPersonPostCode;
	@JsonProperty("authorizedPersonName")
	private String authorizedPersonName;
	@JsonProperty("revokedPersonPostCode")
	private String revokedPersonPostCode;
	@JsonProperty("revokedPersonName")
	private String revokedPersonName;
	@JsonProperty("reviewDate")
	private Date reviewDate;
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
	@JsonProperty("revokedDate")
	private Date revokedDate;
	@JsonProperty("cipDirection")
	private String cipDirection;
	@JsonProperty("revokeDirection")
	private String revokeDirection;
	@JsonProperty("isReqPartyTypeDom")
	private String isReqPartyTypeDom;
	@JsonProperty("revPerPostCodeDom")
	private String revPerPostCodeDom;
	@JsonProperty("authPerPostCodeDom")
	private String authPerPostCodeDom;
	@JsonProperty("intStsRsnCodeDom")
	private String intStsRsnCodeDom;
	@JsonProperty("recordStatusDom")
	private String recordStatusDom;
	@JsonProperty("internalStatusDom")
	private String internalStatusDom;
	@JsonProperty("signedFlag")
	private String signedFlag;
	@JsonProperty("cipCauseCode")
	private String cipCauseCode;
	@JsonProperty("verifiedFlag")
	private String verifiedFlag;
	@JsonProperty("maxMovementDate")
	private Date maxMovementDate;

	/**
	 * Creates new OffenderInternalStatuses class Object
	 */
	public OffenderInternalStatuses() {
		// Super();
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the internalStatusSeq
	 */
	public Integer getInternalStatusSeq() {
		return internalStatusSeq;
	}

	/**
	 * @param internalStatusSeq
	 *            the internalStatusSeq to set
	 */
	public void setInternalStatusSeq(final Integer internalStatusSeq) {
		this.internalStatusSeq = internalStatusSeq;
	}

	/**
	 * @return the internalStatus
	 */
	public String getInternalStatus() {
		return internalStatus;
	}

	/**
	 * @param internalStatus
	 *            the internalStatus to set
	 */
	public void setInternalStatus(final String internalStatus) {
		this.internalStatus = internalStatus;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the intStsReasonCode
	 */
	public String getIntStsReasonCode() {
		return intStsReasonCode;
	}

	/**
	 * @param intStsReasonCode
	 *            the intStsReasonCode to set
	 */
	public void setIntStsReasonCode(final String intStsReasonCode) {
		this.intStsReasonCode = intStsReasonCode;
	}

	/**
	 * @return the recordStatus
	 */
	public String getRecordStatus() {
		return recordStatus;
	}

	/**
	 * @param recordStatus
	 *            the recordStatus to set
	 */
	public void setRecordStatus(final String recordStatus) {
		this.recordStatus = recordStatus;
	}

	/**
	 * @return the isRequestedPartyType
	 */
	public String getIsRequestedPartyType() {
		return isRequestedPartyType;
	}

	/**
	 * @param isRequestedPartyType
	 *            the isRequestedPartyType to set
	 */
	public void setIsRequestedPartyType(final String isRequestedPartyType) {
		this.isRequestedPartyType = isRequestedPartyType;
	}

	/**
	 * @return the durationCode
	 */
	public String getDurationCode() {
		return durationCode;
	}

	/**
	 * @param durationCode
	 *            the durationCode to set
	 */
	public void setDurationCode(final String durationCode) {
		this.durationCode = durationCode;
	}

	/**
	 * @return the effectiveTime
	 */
	public Date getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * @param effectiveTime
	 *            the effectiveTime to set
	 */
	public void setEffectiveTime(final Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the authorizedPersonText
	 */
	public String getAuthorizedPersonText() {
		return authorizedPersonText;
	}

	/**
	 * @param authorizedPersonText
	 *            the authorizedPersonText to set
	 */
	public void setAuthorizedPersonText(final String authorizedPersonText) {
		this.authorizedPersonText = authorizedPersonText;
	}

	/**
	 * @return the revokedBy
	 */
	public Integer getRevokedBy() {
		return revokedBy;
	}

	/**
	 * @param revokedBy
	 *            the revokedBy to set
	 */
	public void setRevokedBy(final Integer revokedBy) {
		this.revokedBy = revokedBy;
	}

	/**
	 * @return the authPersonPostCode
	 */
	public String getAuthPersonPostCode() {
		return authPersonPostCode;
	}

	/**
	 * @param authPersonPostCode
	 *            the authPersonPostCode to set
	 */
	public void setAuthPersonPostCode(final String authPersonPostCode) {
		this.authPersonPostCode = authPersonPostCode;
	}

	/**
	 * @return the authorizedPersonName
	 */
	public String getAuthorizedPersonName() {
		return authorizedPersonName;
	}

	/**
	 * @param authorizedPersonName
	 *            the authorizedPersonName to set
	 */
	public void setAuthorizedPersonName(final String authorizedPersonName) {
		this.authorizedPersonName = authorizedPersonName;
	}

	/**
	 * @return the revokedPersonPostCode
	 */
	public String getRevokedPersonPostCode() {
		return revokedPersonPostCode;
	}

	/**
	 * @param revokedPersonPostCode
	 *            the revokedPersonPostCode to set
	 */
	public void setRevokedPersonPostCode(final String revokedPersonPostCode) {
		this.revokedPersonPostCode = revokedPersonPostCode;
	}

	/**
	 * @return the revokedPersonName
	 */
	public String getRevokedPersonName() {
		return revokedPersonName;
	}

	/**
	 * @param revokedPersonName
	 *            the revokedPersonName to set
	 */
	public void setRevokedPersonName(final String revokedPersonName) {
		this.revokedPersonName = revokedPersonName;
	}

	/**
	 * @return the reviewDate
	 */
	public Date getReviewDate() {
		return reviewDate;
	}

	/**
	 * @param reviewDate
	 *            the reviewDate to set
	 */
	public void setReviewDate(final Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the revokedDate
	 */
	public Date getRevokedDate() {
		return revokedDate;
	}

	/**
	 * @param revokedDate
	 *            the revokedDate to set
	 */
	public void setRevokedDate(final Date revokedDate) {
		this.revokedDate = revokedDate;
	}

	/**
	 * @return the cipDirection
	 */
	public String getCipDirection() {
		return cipDirection;
	}

	/**
	 * @param cipDirection
	 *            the cipDirection to set
	 */
	public void setCipDirection(final String cipDirection) {
		this.cipDirection = cipDirection;
	}

	/**
	 * @return the revokeDirection
	 */
	public String getRevokeDirection() {
		return revokeDirection;
	}

	/**
	 * @param revokeDirection
	 *            the revokeDirection to set
	 */
	public void setRevokeDirection(final String revokeDirection) {
		this.revokeDirection = revokeDirection;
	}

	/**
	 * @return the isReqPartyTypeDom
	 */
	public String getIsReqPartyTypeDom() {
		return isReqPartyTypeDom;
	}

	/**
	 * @param isReqPartyTypeDom
	 *            the isReqPartyTypeDom to set
	 */
	public void setIsReqPartyTypeDom(final String isReqPartyTypeDom) {
		this.isReqPartyTypeDom = isReqPartyTypeDom;
	}

	/**
	 * @return the revPerPostCodeDom
	 */
	public String getRevPerPostCodeDom() {
		return revPerPostCodeDom;
	}

	/**
	 * @param revPerPostCodeDom
	 *            the revPerPostCodeDom to set
	 */
	public void setRevPerPostCodeDom(final String revPerPostCodeDom) {
		this.revPerPostCodeDom = revPerPostCodeDom;
	}

	/**
	 * @return the authPerPostCodeDom
	 */
	public String getAuthPerPostCodeDom() {
		return authPerPostCodeDom;
	}

	/**
	 * @param authPerPostCodeDom
	 *            the authPerPostCodeDom to set
	 */
	public void setAuthPerPostCodeDom(final String authPerPostCodeDom) {
		this.authPerPostCodeDom = authPerPostCodeDom;
	}

	/**
	 * @return the intStsRsnCodeDom
	 */
	public String getIntStsRsnCodeDom() {
		return intStsRsnCodeDom;
	}

	/**
	 * @param intStsRsnCodeDom
	 *            the intStsRsnCodeDom to set
	 */
	public void setIntStsRsnCodeDom(final String intStsRsnCodeDom) {
		this.intStsRsnCodeDom = intStsRsnCodeDom;
	}

	/**
	 * @return the recordStatusDom
	 */
	public String getRecordStatusDom() {
		return recordStatusDom;
	}

	/**
	 * @param recordStatusDom
	 *            the recordStatusDom to set
	 */
	public void setRecordStatusDom(final String recordStatusDom) {
		this.recordStatusDom = recordStatusDom;
	}

	/**
	 * @return the internalStatusDom
	 */
	public String getInternalStatusDom() {
		return internalStatusDom;
	}

	/**
	 * @param internalStatusDom
	 *            the internalStatusDom to set
	 */
	public void setInternalStatusDom(final String internalStatusDom) {
		this.internalStatusDom = internalStatusDom;
	}

	/**
	 * @return the signedFlag
	 */
	public String getSignedFlag() {
		return signedFlag;
	}

	/**
	 * @param signedFlag
	 *            the signedFlag to set
	 */
	public void setSignedFlag(final String signedFlag) {
		this.signedFlag = signedFlag;
	}

	/**
	 * @return the cipCauseCode
	 */
	public String getCipCauseCode() {
		return cipCauseCode;
	}

	/**
	 * @param cipCauseCode
	 *            the cipCauseCode to set
	 */
	public void setCipCauseCode(final String cipCauseCode) {
		this.cipCauseCode = cipCauseCode;
	}

	/**
	 * @return the verifiedFlag
	 */
	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	/**
	 * @param verifiedFlag
	 *            the verifiedFlag to set
	 */
	public void setVerifiedFlag(String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	/**
	 * @return the maxMovementDate
	 */
	public Date getMaxMovementDate() {
		return maxMovementDate;
	}

	/**
	 * @param maxMovementDate
	 *            the maxMovementDate to set
	 */
	public void setMaxMovementDate(Date maxMovementDate) {
		this.maxMovementDate = maxMovementDate;
	}
}
