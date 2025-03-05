package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderClothingItem extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderClothingItemId")
	private long offenderClothingItemId;

	@JsonProperty("clothesSizeCode")
	private String clothesSizeCode;

	@JsonProperty("clothesType")
	private String clothesType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("issuedQuality")
	private String issuedQuality;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("qualityRsnCode")
	private String qualityRsnCode;

	@JsonProperty("quantity")
	private BigDecimal quantity;

	@JsonProperty("returnDate")
	private Date returnDate;

	@JsonProperty("returnFlag")
	private String returnFlag;

	@JsonProperty("returnQuality")
	private String returnQuality;

	@JsonProperty("returnQuantity")
	private BigDecimal returnQuantity;

	@JsonProperty("returnTime")
	private Date returnTime;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("secondaryColumnComment")
	private String secondaryColumnComment;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderClothingIssue")
	private OffenderClothingIssue offenderClothingIssue;

	/**
	 *
	 * @return
	 */
	public long getOffenderClothingItemId() {
		return offenderClothingItemId;
	}

	/**
	 *
	 * @param offenderClothingItemId
	 */
	public void setOffenderClothingItemId(long offenderClothingItemId) {
		this.offenderClothingItemId = offenderClothingItemId;
	}

	/**
	 *
	 * @return
	 */
	public String getClothesSizeCode() {
		return clothesSizeCode;
	}

	/**
	 *
	 * @param clothesSizeCode
	 */
	public void setClothesSizeCode(String clothesSizeCode) {
		this.clothesSizeCode = clothesSizeCode;
	}

	/**
	 *
	 * @return
	 */
	public String getClothesType() {
		return clothesType;
	}

	/**
	 *
	 * @param clothesType
	 */
	public void setClothesType(String clothesType) {
		this.clothesType = clothesType;
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
	public String getIssuedQuality() {
		return issuedQuality;
	}

	/**
	 *
	 * @param issuedQuality
	 */
	public void setIssuedQuality(String issuedQuality) {
		this.issuedQuality = issuedQuality;
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
	public String getQualityRsnCode() {
		return qualityRsnCode;
	}

	/**
	 *
	 * @param qualityRsnCode
	 */
	public void setQualityRsnCode(String qualityRsnCode) {
		this.qualityRsnCode = qualityRsnCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 *
	 * @param quantity
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 *
	 * @return
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 *
	 * @param returnDate
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 *
	 * @return
	 */
	public String getReturnFlag() {
		return returnFlag;
	}

	/**
	 *
	 * @param returnFlag
	 */
	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getReturnQuality() {
		return returnQuality;
	}

	/**
	 *
	 * @param returnQuality
	 */
	public void setReturnQuality(String returnQuality) {
		this.returnQuality = returnQuality;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getReturnQuantity() {
		return returnQuantity;
	}

	/**
	 *
	 * @param returnQuantity
	 */
	public void setReturnQuantity(BigDecimal returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	/**
	 *
	 * @return
	 */
	public Date getReturnTime() {
		return returnTime;
	}

	/**
	 *
	 * @param returnTime
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
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
	public String getSecondaryColumnComment() {
		return secondaryColumnComment;
	}

	/**
	 *
	 * @param secondaryColumnComment
	 */
	public void setSecondaryColumnComment(String secondaryColumnComment) {
		this.secondaryColumnComment = secondaryColumnComment;
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
	public OffenderClothingIssue getOffenderClothingIssue() {
		return offenderClothingIssue;
	}

	/**
	 *
	 * @param offenderClothingIssue
	 */
	public void setOffenderClothingIssue(OffenderClothingIssue offenderClothingIssue) {
		this.offenderClothingIssue = offenderClothingIssue;
	}

}