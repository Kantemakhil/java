package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SystemProfiles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("oldTableName")
	private String oldTableName;

	@JsonProperty("profileValue")
	private String profileValue;

	@JsonProperty("profileValue2")
	private String profileValue2;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("profileType")
	@NotNull
	@Size(max = 12)
	private String profileType;

	@JsonProperty("profileCode")
	@NotNull
	@Size(max = 12)
	private String profileCode;

	/**
	 * Creates new SystemProfiles class Object
	 */
	public SystemProfiles() {
		// SystemProfiles
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
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
	 * @return the oldTableName
	 */
	public String getOldTableName() {
		return oldTableName;
	}

	/**
	 * @param oldTableName
	 *            the oldTableName to set
	 */
	public void setOldTableName(final String oldTableName) {
		this.oldTableName = oldTableName;
	}

	/**
	 * @return the profileValue
	 */
	public String getProfileValue() {
		return profileValue;
	}

	/**
	 * @param profileValue
	 *            the profileValue to set
	 */
	public void setProfileValue(final String profileValue) {
		this.profileValue = profileValue;
	}

	/**
	 * @return the profileValue2
	 */
	public String getProfileValue2() {
		return profileValue2;
	}

	/**
	 * @param profileValue2
	 *            the profileValue2 to set
	 */
	public void setProfileValue2(final String profileValue2) {
		this.profileValue2 = profileValue2;
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
	 * @return the profileType
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 * @param profileType
	 *            the profileType to set
	 */
	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

	/**
	 * @return the profileCode
	 */
	public String getProfileCode() {
		return profileCode;
	}

	/**
	 * @param profileCode
	 *            the profileCode to set
	 */
	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}