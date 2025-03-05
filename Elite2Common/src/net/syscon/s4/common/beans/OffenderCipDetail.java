package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCipDetail extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("authByPerCode")
	private String authByPerCode;

	@JsonProperty("authByPerName")
	private String authByPerName;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("duration")
	private BigDecimal duration;

	@JsonProperty("durationType")
	private String durationType;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("effectiveTime")
	private Date effectiveTime;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("expiryTime")
	private Date expiryTime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("placementReasonCode")
	private String placementReasonCode;

	@JsonProperty("placementType")
	private String placementType;

	@JsonProperty("relByPerCode")
	private String relByPerCode;

	@JsonProperty("relByPerName")
	private String relByPerName;

	@JsonProperty("releaseDate")
	private Date releaseDate;

	@JsonProperty("releaseTime")
	private Date releaseTime;

	@JsonProperty("reqByPerCode")
	private String reqByPerCode;

	@JsonProperty("reviewDate")
	private Date reviewDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("placementSeq")
	private long placementSeq;

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getAuthByPerCode() {
		return authByPerCode;
	}

	/**
	 *
	 * @param authByPerCode
	 */
	public void setAuthByPerCode(String authByPerCode) {
		this.authByPerCode = authByPerCode;
	}

	/**
	 *
	 * @return
	 */
	public String getAuthByPerName() {
		return authByPerName;
	}

	/**
	 *
	 * @param authByPerName
	 */
	public void setAuthByPerName(String authByPerName) {
		this.authByPerName = authByPerName;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public BigDecimal getDuration() {
		return duration;
	}

	/**
	 *
	 * @param duration
	 */
	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	/**
	 *
	 * @return
	 */
	public String getDurationType() {
		return durationType;
	}

	/**
	 *
	 * @param durationType
	 */
	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	/**
	 *
	 * @return
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 *
	 * @param effectiveDate
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 *
	 * @param effectiveTime
	 */
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryTime() {
		return expiryTime;
	}

	/**
	 *
	 * @param expiryTime
	 */
	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
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
	public String getPlacementReasonCode() {
		return placementReasonCode;
	}

	/**
	 *
	 * @param placementReasonCode
	 */
	public void setPlacementReasonCode(String placementReasonCode) {
		this.placementReasonCode = placementReasonCode;
	}

	/**
	 *
	 * @return
	 */
	public String getPlacementType() {
		return placementType;
	}

	/**
	 *
	 * @param placementType
	 */
	public void setPlacementType(String placementType) {
		this.placementType = placementType;
	}

	/**
	 *
	 * @return
	 */
	public String getRelByPerCode() {
		return relByPerCode;
	}

	/**
	 *
	 * @param relByPerCode
	 */
	public void setRelByPerCode(String relByPerCode) {
		this.relByPerCode = relByPerCode;
	}

	/**
	 *
	 * @return
	 */
	public String getRelByPerName() {
		return relByPerName;
	}

	/**
	 *
	 * @param relByPerName
	 */
	public void setRelByPerName(String relByPerName) {
		this.relByPerName = relByPerName;
	}

	/**
	 *
	 * @return
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 *
	 * @param releaseDate
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getReleaseTime() {
		return releaseTime;
	}

	/**
	 *
	 * @param releaseTime
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 *
	 * @return
	 */
	public String getReqByPerCode() {
		return reqByPerCode;
	}

	/**
	 *
	 * @param reqByPerCode
	 */
	public void setReqByPerCode(String reqByPerCode) {
		this.reqByPerCode = reqByPerCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getReviewDate() {
		return reviewDate;
	}

	/**
	 *
	 * @param reviewDate
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
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
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getPlacementSeq() {
		return placementSeq;
	}

	/**
	 *
	 * @param placementSeq
	 */
	public void setPlacementSeq(long placementSeq) {
		this.placementSeq = placementSeq;
	}

}