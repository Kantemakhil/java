package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderImage extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderImageId")
	private long offenderImageId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("captureDatetime")
	private Date captureDatetime;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("fullSizeImage")
	private byte[] fullSizeImage;

	@JsonProperty("imageObjectId")
	private BigDecimal imageObjectId;

	@JsonProperty("imageObjectSeq")
	private BigDecimal imageObjectSeq;

	@JsonProperty("imageObjectType")
	private String imageObjectType;

	@JsonProperty("imageViewType")
	private String imageViewType;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("orientationType")
	private String orientationType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("thumbnailImage")
	private byte[] thumbnailImage;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	/**
	 *
	 * @return
	 */
	public long getOffenderImageId() {
		return offenderImageId;
	}

	/**
	 *
	 * @param offenderImageId
	 */
	public void setOffenderImageId(long offenderImageId) {
		this.offenderImageId = offenderImageId;
	}

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getCaptureDatetime() {
		return captureDatetime;
	}

	/**
	 *
	 * @param captureDatetime
	 */
	public void setCaptureDatetime(Date captureDatetime) {
		this.captureDatetime = captureDatetime;
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
	public byte[] getFullSizeImage() {
		return fullSizeImage;
	}

	/**
	 *
	 * @param fullSizeImage
	 */
	public void setFullSizeImage(byte[] fullSizeImage) {
		this.fullSizeImage = fullSizeImage;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getImageObjectId() {
		return imageObjectId;
	}

	/**
	 *
	 * @param imageObjectId
	 */
	public void setImageObjectId(BigDecimal imageObjectId) {
		this.imageObjectId = imageObjectId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getImageObjectSeq() {
		return imageObjectSeq;
	}

	/**
	 *
	 * @param imageObjectSeq
	 */
	public void setImageObjectSeq(BigDecimal imageObjectSeq) {
		this.imageObjectSeq = imageObjectSeq;
	}

	/**
	 *
	 * @return
	 */
	public String getImageObjectType() {
		return imageObjectType;
	}

	/**
	 *
	 * @param imageObjectType
	 */
	public void setImageObjectType(String imageObjectType) {
		this.imageObjectType = imageObjectType;
	}

	/**
	 *
	 * @return
	 */
	public String getImageViewType() {
		return imageViewType;
	}

	/**
	 *
	 * @param imageViewType
	 */
	public void setImageViewType(String imageViewType) {
		this.imageViewType = imageViewType;
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
	public String getOrientationType() {
		return orientationType;
	}

	/**
	 *
	 * @param orientationType
	 */
	public void setOrientationType(String orientationType) {
		this.orientationType = orientationType;
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
	public byte[] getThumbnailImage() {
		return thumbnailImage;
	}

	/**
	 *
	 * @param thumbnailImage
	 */
	public void setThumbnailImage(byte[] thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
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

}