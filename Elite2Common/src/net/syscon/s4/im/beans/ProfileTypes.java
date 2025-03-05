package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.ProfileCategory;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ProfileTypes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("profileType")
	@NotNull
	@Size(max = 12)
	private String profileType;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("codeValueType")
	@NotNull
	@Size(max = 12)
	private String codeValueType;

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

	@JsonProperty("code")
	@Size(max = 40)
	private String code;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("listSeq")
	@NotNull
	private BigDecimal listSeq;

	@JsonProperty("mandatoryFlag")
	@NotNull
	@Size(max = 1)
	private String mandatoryFlag;

	@JsonProperty("modifiedDate")
	@NotNull
	private Date modifiedDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("updatedAllowedFlag")
	@NotNull
	@Size(max = 1)
	private String updatedAllowedFlag;

	@JsonProperty("profileCategoryBean")
	private ProfileCategory profileCategoryBean;

	@JsonProperty("lvTemp")
	private String lvTemp;

	@JsonProperty("profileCategory")
	private String profileCategory;
	
	@JsonProperty("canDisplay")
	 private Boolean canDisplay;
	
	

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public ProfileTypes() {
		// ProfileCodes
	}

	/**
	 * @return the profileCategory
	 */
	public String getProfileCategory() {
		return profileCategory;
	}

	/**
	 *
	 * @param profileCategory
	 */
	public void setProfileCategory(final String profileCategory) {
		this.profileCategory = profileCategory;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the lvTemp
	 */
	public String getLvTemp() {
		return lvTemp;
	}

	/**
	 * @param lvTemp
	 *            the lvTemp to set
	 */
	public void setLvTemp(final String lvTemp) {
		this.lvTemp = lvTemp;
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
	public String getCodeValueType() {
		return codeValueType;
	}

	/**
	 *
	 * @param codeValueType
	 */
	public void setCodeValueType(final String codeValueType) {
		this.codeValueType = codeValueType;
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
	public String getMandatoryFlag() {
		return mandatoryFlag;
	}

	/**
	 *
	 * @param mandatoryFlag
	 */
	public void setMandatoryFlag(final String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
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
	public String getUpdatedAllowedFlag() {
		return updatedAllowedFlag;
	}

	/**
	 *
	 * @param updatedAllowedFlag
	 */
	public void setUpdatedAllowedFlag(final String updatedAllowedFlag) {
		this.updatedAllowedFlag = updatedAllowedFlag;
	}

	/**
	 *
	 * @return
	 */
	public ProfileCategory getProfileCategoryBean() {
		return profileCategoryBean;
	}

	/**
	 *
	 * @param profileCategoryBean
	 */
	public void setProfileCategoryBean(final ProfileCategory profileCategoryBean) {
		this.profileCategoryBean = profileCategoryBean;
	}

}