package net.syscon.s4.im.beans;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class ImageOriginals
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageOriginals extends BaseModel {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("imageId")
	private Integer imageId;

	@JsonProperty("imageOriginal")
	private byte[] imageOriginal;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDatetime")
	private Timestamp createDatetime;

	@JsonProperty("modifyDatetime")
	private Timestamp modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("imageFull")
	private byte[] imageFull;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	/**
	 * @param imageId
	 *            imageId to set
	 */
	public void setImageId(final Integer imageId) {
		this.imageId = imageId;
	}

	/**
	 * return the imageId
	 */
	public Integer getImageId() {
		return this.imageId;
	}

	/**
	 * @param imageOriginal
	 *            imageOriginal to set
	 */
	public void setImageOriginal(final byte[] imageOriginal) {
		this.imageOriginal = imageOriginal;
	}

	/**
	 * return the imageOriginal
	 */
	public byte[] getImageOriginal() {
		return this.imageOriginal;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return the createDatetime
	 */
	public Timestamp getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Timestamp modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return the modifyDatetime
	 */
	public Timestamp getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param imageFull
	 *            imageFull to set
	 */
	public void setImageFull(final byte[] imageFull) {
		this.imageFull = imageFull;
	}

	/**
	 * return the imageFull
	 */
	public byte[] getImageFull() {
		return this.imageFull;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

}