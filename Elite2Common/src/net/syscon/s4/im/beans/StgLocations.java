package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the STG_LOCATIONS database table.
 * 
 */
public class StgLocations extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("cityCode")
	private String cityCode;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("country")
	private String country;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("provState")
	private String provState;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("stgId")
	private Long stgId;
	@JsonProperty("locationSeq")
	private Long locationSeq;

	/**
	 * Creates new StgLocations class Object
	 */
	public StgLocations() {
		// StgLocations
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(final String cityCode) {
		this.cityCode = cityCode;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
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
	 * @return the provState
	 */
	public String getProvState() {
		return provState;
	}

	/**
	 * @param provState
	 *            the provState to set
	 */
	public void setProvState(final String provState) {
		this.provState = provState;
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
	 * @return the stgId
	 */
	public Long getStgId() {
		return stgId;
	}

	/**
	 * @param stgId
	 *            the stgId to set
	 */
	public void setStgId(final Long stgId) {
		this.stgId = stgId;
	}

	/**
	 * @return the locationSeq
	 */
	public Long getLocationSeq() {
		return locationSeq;
	}

	/**
	 * @param locationSeq
	 *            the locationSeq to set
	 */
	public void setLocationSeq(final Long locationSeq) {
		this.locationSeq = locationSeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
