package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class LegalUpdateReasons extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("updateReasonCode")
	private String updateReasonCode;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("activeType")
	private String activeType;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("returnValue")
	private BigDecimal returnValue;
	@JsonProperty("deleteRecordCountData")
	private Integer deleteRecordCountData;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("reasonCategory")
	private String reasonCategory;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("code")
	private String code;
	@JsonProperty("nbtReasonCategory")
	private String nbtReasonCategory;
	@JsonProperty("nbtActiveType")
	private String nbtActiveType;
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	/**
	 * Creates new LegalUpdateReasons class Object
	 */
	public LegalUpdateReasons() {
		// LegalUpdateReasons
	}

	/**
	 * @return the updateReasonCode
	 */
	public String getUpdateReasonCode() {
		return updateReasonCode;
	}

	/**
	 * @param updateReasonCode
	 *            the updateReasonCode to set
	 */
	public void setUpdateReasonCode(final String updateReasonCode) {
		this.updateReasonCode = updateReasonCode;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the activeType
	 */
	public String getActiveType() {
		return activeType;
	}

	/**
	 * @param activeType
	 *            the activeType to set
	 */
	public void setActiveType(final String activeType) {
		this.activeType = activeType;
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
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the returnValue
	 */
	public BigDecimal getReturnValue() {
		return returnValue;
	}

	/**
	 * @param returnValue
	 *            the returnValue to set
	 */
	public void setReturnValue(final BigDecimal returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * @return the deleteRecordCountData
	 */
	public Integer getDeleteRecordCountData() {
		return deleteRecordCountData;
	}

	/**
	 * @param deleteRecordCountData
	 *            the deleteRecordCountData to set
	 */
	public void setDeleteRecordCountData(final Integer deleteRecordCountData) {
		this.deleteRecordCountData = deleteRecordCountData;
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
	 * @return the reasonCategory
	 */
	public String getReasonCategory() {
		return reasonCategory;
	}

	/**
	 * @param reasonCategory
	 *            the reasonCategory to set
	 */
	public void setReasonCategory(final String reasonCategory) {
		this.reasonCategory = reasonCategory;
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
	 * @return the nbtReasonCategory
	 */
	public String getNbtReasonCategory() {
		return nbtReasonCategory;
	}

	/**
	 * @param nbtReasonCategory
	 *            the nbtReasonCategory to set
	 */
	public void setNbtReasonCategory(final String nbtReasonCategory) {
		this.nbtReasonCategory = nbtReasonCategory;
	}

	/**
	 * @return the nbtActiveType
	 */
	public String getNbtActiveType() {
		return nbtActiveType;
	}

	/**
	 * @param nbtActiveType
	 *            the nbtActiveType to set
	 */
	public void setNbtActiveType(final String nbtActiveType) {
		this.nbtActiveType = nbtActiveType;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}
