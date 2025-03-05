package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ProfileCodes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("description")
	@Size(max = 40)
	private String description;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("listSeq")
	@NotNull
	private BigDecimal listSeq;

	@JsonProperty("modifiedDate")
	@NotNull
	private Date modifiedDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("updateAllowedFlag")
	@NotNull
	@Size(max = 1)
	private String updateAllowedFlag;

	@JsonProperty("userId")
	@NotNull
	@Size(max = 32)
	private String userId;

	@JsonProperty("profileType")
	@NotNull
	@Size(max = 12)
	private String profileType;

	@JsonProperty("profileCode")
	@NotNull
	@Size(max = 12)
	private String profileCode;
	
	@JsonProperty("profileValue")
	@Size(max = 40)
	private String profileValue;
	
	@JsonProperty("profileValue2")
	@Size(max = 12)
	private String profileValue2;
	
	@JsonProperty("oldTableName")
	@Size(max = 50)
	private String oldTableName;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;


	public ProfileCodes() {
		// ProfileCodes
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
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 */
	public void setDescription(final String description) {
		this.description = description;
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
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 *
	 * @param listSeq
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 *
	 * @param modifiedDate
	 */
	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 *
	 * @param updateAllowedFlag
	 */
	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 *
	 * @param userId
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 *
	 * @return
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 *
	 * @param profileType
	 */
	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

	/**
	 *
	 * @return
	 */
	public String getProfileCode() {
		return profileCode;
	}

	/**
	 *
	 * @param profileCode
	 */
	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * @return the profileValue
	 */
	public String getProfileValue() {
		return profileValue;
	}

	/**
	 * @param profileValue the profileValue to set
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
	 * @param profileValue2 the profileValue2 to set
	 */
	public void setProfileValue2(final String profileValue2) {
		this.profileValue2 = profileValue2;
	}

	/**
	 * @return the oldTableName
	 */
	public String getOldTableName() {
		return oldTableName;
	}

	/**
	 * @param oldTableName the oldTableName to set
	 */
	public void setOldTableName(final String oldTableName) {
		this.oldTableName = oldTableName;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}