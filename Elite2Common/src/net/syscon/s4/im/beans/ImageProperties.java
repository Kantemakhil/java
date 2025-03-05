package net.syscon.s4.im.beans;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class ImageProperties
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageProperties extends BaseModel {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("imagePropertyId")
	private Integer imagePropertyId;

	@JsonProperty("imageId")
	private Integer imageId;

	@JsonProperty("property")
	private String property;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("imageDes")
	private List<String> imageDes;

	/**
	 * @param imagePropertyId
	 *            imagePropertyId to set
	 */
	public void setImagePropertyId(final Integer imagePropertyId) {
		this.imagePropertyId = imagePropertyId;
	}

	/**
	 * return the imagePropertyId
	 */
	public Integer getImagePropertyId() {
		return this.imagePropertyId;
	}

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
	 * @param property
	 *            property to set
	 */
	public void setProperty(final String property) {
		this.property = property;
	}

	/**
	 * return the property
	 */
	public String getProperty() {
		return this.property;
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
	public Date getCreateDatetime() {
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
	public Date getModifyDatetime() {
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

	/**
	 * @return the imageDes
	 */
	public List<String> getImageDes() {
		return imageDes;
	}

	/**
	 * @param imageDes the imageDes to set
	 */
	public void setImageDes(final List<String> imageDes) {
		this.imageDes = imageDes;
	}

}