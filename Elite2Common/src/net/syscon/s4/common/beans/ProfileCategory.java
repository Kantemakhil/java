package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.ProfileTypes;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileCategory extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("profileCategoryData")
	private String profileCategoryData;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("recheckFlag")
	private String recheckFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("profileTypes")
	private List<ProfileTypes> profileTypes;

	@JsonProperty("profileCategory")
	private String profileCategory;

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
	 *
	 * @return
	 */
	public String getProfileCategoryData() {
		return profileCategoryData;
	}

	/**
	 *
	 * @param profileCategoryData
	 */
	public void setProfileCategoryData(final String profileCategoryData) {
		this.profileCategoryData = profileCategoryData;
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
	public String getRecheckFlag() {
		return recheckFlag;
	}

	/**
	 *
	 * @param recheckFlag
	 */
	public void setRecheckFlag(final String recheckFlag) {
		this.recheckFlag = recheckFlag;
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
	public List<ProfileTypes> getProfileTypes() {
		return profileTypes;
	}

	/**
	 *
	 * @param profileTypes
	 */
	public void setProfileTypes(final List<ProfileTypes> profileTypes) {
		this.profileTypes = profileTypes;
	}

}