package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class AddressUsages
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressUsages extends BaseModel implements Serializable {

	private static final Long serialVersionUID = 1L;

	@JsonProperty("addressId")
	@NotNull
	private Long addressId;

	@JsonProperty("addressUsage")
	@NotNull
	@Size(max = 12)
	private String addressUsage;

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

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("activeFlagTemp")
	private boolean activeFlagTemp;

	@JsonProperty("addressUsageTemp")
	@NotNull
	@Size(max = 12)
	private String addressUsageTemp;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	/**
	 *
	 * @return
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 *
	 * @param addressId
	 */
	public void setAddressId(final Long addressId) {
		this.addressId = addressId;
	}

	/**
	 *
	 * @return
	 */
	public String getAddressUsage() {
		return addressUsage;
	}

	/**
	 *
	 * @param addressUsage
	 */
	public void setAddressUsage(final String addressUsage) {
		this.addressUsage = addressUsage;
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

	public boolean isActiveFlagTemp() {
		return activeFlagTemp;
	}

	public void setActiveFlagTemp(final boolean activeFlagTemp) {
		this.activeFlagTemp = activeFlagTemp;
	}

	/**
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the addressUsageTemp
	 */
	public String getAddressUsageTemp() {
		return addressUsageTemp;
	}

	/**
	 * @param addressUsageTemp the addressUsageTemp to set
	 */
	public void setAddressUsageTemp(final String addressUsageTemp) {
		this.addressUsageTemp = addressUsageTemp;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
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
	
}