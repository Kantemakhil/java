package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Images extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("imageId")
	private long imageId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("captureDate")
	private Date captureDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("imageObjectId")
	private BigDecimal imageObjectId;

	@JsonProperty("imageObjectSeq")
	private BigDecimal imageObjectSeq;

	@JsonProperty("imageObjectType")
	private String imageObjectType;

	@JsonProperty("imageThumbnail")
	private byte[] imageThumbnail;

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

	@JsonProperty("imageOriginal")
	private ImageOriginal imageOriginal;

	@JsonProperty("imageProperties")
	private List<ImageProperty> imageProperties;


	/**
	 *
	 * @return
	 */
	public long getImageId() {
		return imageId;
	}

	/**
	 *
	 * @param imageId
	 */
	public void setImageId(long imageId) {
		this.imageId = imageId;
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
	public Date getCaptureDate() {
		return captureDate;
	}

	/**
	 *
	 * @param captureDate
	 */
	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
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
	public byte[] getImageThumbnail() {
		return imageThumbnail;
	}

	/**
	 *
	 * @param imageThumbnail
	 */
	public void setImageThumbnail(byte[] imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
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
	public ImageOriginal getImageOriginal() {
		return imageOriginal;
	}

	/**
	 *
	 * @param imageOriginal
	 */
	public void setImageOriginal(ImageOriginal imageOriginal) {
		this.imageOriginal = imageOriginal;
	}

	/**
	 *
	 * @return
	 */
	public List<ImageProperty> getImageProperties() {
		return imageProperties;
	}

	/**
	 *
	 * @param imageProperties
	 */
	public void setImageProperties(List<ImageProperty> imageProperties) {
		this.imageProperties = imageProperties;
	}

	

}