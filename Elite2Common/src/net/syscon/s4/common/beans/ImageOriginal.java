package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.Images;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageOriginal extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("imageId")
	private long imageId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("imageFull")
	private byte[] imageFull;

	@JsonProperty("imageOriginals")
	private byte[] imageOriginals;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("image")
	private Images image;

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
	public byte[] getImageFull() {
		return imageFull;
	}

	/**
	 *
	 * @param imageFull
	 */
	public void setImageFull(byte[] imageFull) {
		this.imageFull = imageFull;
	}

	/**
	 *
	 * @return
	 */
	public byte[] getImageOriginals() {
		return imageOriginals;
	}

	/**
	 *
	 * @param imageOriginals
	 */
	public void setImageOriginals(byte[] imageOriginals) {
		this.imageOriginals = imageOriginals;
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
	public Images getImage() {
		return image;
	}

	/**
	 *
	 * @param image
	 */
	public void setImage(Images image) {
		this.image = image;
	}

}