package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CheckListTypes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("checkListCategory")
	private String checkListCategory;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("checkListCode")
	private String checkListCode;

	@JsonProperty("userConfirmedFlag")
	private String userConfirmedFlag;

	@JsonProperty("listSeq")
	private Long listSeq;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	/**
	 * @return the checkListCategory
	 */
	public String getCheckListCategory() {
		return checkListCategory;
	}

	/**
	 * @param checkListCategory the checkListCategory to set
	 */
	public void setCheckListCategory(final String checkListCategory) {
		this.checkListCategory = checkListCategory;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the checkListCode
	 */
	public String getCheckListCode() {
		return checkListCode;
	}

	/**
	 * @param checkListCode the checkListCode to set
	 */
	public void setCheckListCode(final String checkListCode) {
		this.checkListCode = checkListCode;
	}

	/**
	 * @return the userConfirmedFlag
	 */
	public String getUserConfirmedFlag() {
		return userConfirmedFlag;
	}

	/**
	 * @param userConfirmedFlag the userConfirmedFlag to set
	 */
	public void setUserConfirmedFlag(final String userConfirmedFlag) {
		this.userConfirmedFlag = userConfirmedFlag;
	}

	/**
	 * @return the listSeq
	 */
	public Long getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(final Long listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the updateAllowedFlag
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 * @param updateAllowedFlag the updateAllowedFlag to set
	 */
	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
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
	 * @param modifyDatetime the modifyDatetime to set
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
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
}
