package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.ImageProperty;

@XmlRootElement
public class Images extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("imageId")
	@NotNull
	private Long imageId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("captureDate")
	@NotNull
	private Date captureDate;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("imageObjectId")
	@NotNull
	private BigDecimal imageObjectId;
	
	@JsonProperty("previousImageId")
	@NotNull
	private Long previousImageId;


	@JsonProperty("imageObjectSeq")
	private BigDecimal imageObjectSeq;

	@JsonProperty("imageObjectType")
	@NotNull
	@Size(max = 12)
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

	@JsonProperty("imageOriginals")
	private ImageOriginals imageOriginals;

	@JsonProperty("imageProperties")
	private List<ImageProperty> imageProperties;
	@JsonProperty("cou")
	private String cou;
	@JsonProperty("screenName")
	private String screenName;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("modelName")
	private String modelName;
	
	@JsonProperty("parentForm")
	private String parentForm;

	/**
	 * Creates new Images class Object
	 */
	public Images() {
		// Images
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	
	public Long getPreviousImageId() {
		return previousImageId;
	}

	public void setPreviousImageId(Long previousImageId) {
		this.previousImageId = previousImageId;
	}
	/**
	 * @param screenName
	 *            the screenName to set
	 */
	public void setScreenName(final String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the imageId
	 */
	public Long getImageId() {
		return imageId;
	}

	/**
	 * @param imageId
	 *            the imageId to set
	 */
	public void setImageId(final Long imageId) {
		this.imageId = imageId;
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

	/**
	 * @return the captureDate
	 */
	public Date getCaptureDate() {
		return captureDate;
	}

	/**
	 * @param captureDate
	 *            the captureDate to set
	 */
	public void setCaptureDate(final Date captureDate) {
		this.captureDate = captureDate;
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
	 * @return the imageObjectId
	 */
	public BigDecimal getImageObjectId() {
		return imageObjectId;
	}

	/**
	 * @param imageObjectId
	 *            the imageObjectId to set
	 */
	public void setImageObjectId(final BigDecimal imageObjectId) {
		this.imageObjectId = imageObjectId;
	}

	/**
	 * @return the imageObjectSeq
	 */
	public BigDecimal getImageObjectSeq() {
		return imageObjectSeq;
	}

	/**
	 * @param imageObjectSeq
	 *            the imageObjectSeq to set
	 */
	public void setImageObjectSeq(final BigDecimal imageObjectSeq) {
		this.imageObjectSeq = imageObjectSeq;
	}

	/**
	 * @return the imageObjectType
	 */
	public String getImageObjectType() {
		return imageObjectType;
	}

	/**
	 * @param imageObjectType
	 *            the imageObjectType to set
	 */
	public void setImageObjectType(final String imageObjectType) {
		this.imageObjectType = imageObjectType;
	}

	/**
	 * @return the imageThumbnail
	 */
	public byte[] getImageThumbnail() {
		return imageThumbnail;
	}

	/**
	 * @param imageThumbnail
	 *            the imageThumbnail to set
	 */
	public void setImageThumbnail(final byte[] imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	/**
	 * @return the imageViewType
	 */
	public String getImageViewType() {
		return imageViewType;
	}

	/**
	 * @param imageViewType
	 *            the imageViewType to set
	 */
	public void setImageViewType(final String imageViewType) {
		this.imageViewType = imageViewType;
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
	 * @return the orientationType
	 */
	public String getOrientationType() {
		return orientationType;
	}

	/**
	 * @param orientationType
	 *            the orientationType to set
	 */
	public void setOrientationType(final String orientationType) {
		this.orientationType = orientationType;
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
	 * @return the imageOriginal
	 */
	public ImageOriginals getImageOriginals() {
		return imageOriginals;
	}

	/**
	 * @param imageOriginal
	 *            the imageOriginal to set
	 */
	public void setImageOriginals(final ImageOriginals imageOriginals) {
		this.imageOriginals = imageOriginals;
	}

	/**
	 * @return the imageProperties
	 */
	public List<ImageProperty> getImageProperties() {
		return imageProperties;
	}

	/**
	 * @param imageProperties
	 *            the imageProperties to set
	 */
	public void setImageProperties(final List<ImageProperty> imageProperties) {
		this.imageProperties = imageProperties;
	}

	/**
	 * @return the cou
	 */
	public String getCou() {
		return cou;
	}

	/**
	 * @param cou
	 *            the cou to set
	 */
	public void setCou(final String cou) {
		this.cou = cou;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getParentForm() {
		return parentForm;
	}

	public void setParentForm(String parentForm) {
		this.parentForm = parentForm;
	}

}