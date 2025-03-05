package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 *class IwpTemplateModules
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IwpTemplateModules extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("templateModuleId")
	private BigDecimal templateModuleId;
	@JsonProperty("templateId")
	private BigDecimal templateId;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("blockDescription")
	private String blockDescription;
	@JsonProperty("blockName")
	private String blockName;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("moduleName")
	private String moduleName;
	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("iwpDocCount")
	private int iwpDocCount;
	public IwpTemplateModules() {
		// IwpTemplateModules
	}

	/**
	 * @return the templateModuleId
	 */
	public BigDecimal getTemplateModuleId() {
		return templateModuleId;
	}

	/**
	 * @param templateModuleId
	 *            the templateModuleId to set
	 */
	public void setTemplateModuleId(final BigDecimal templateModuleId) {
		this.templateModuleId = templateModuleId;
	}

	/**
	 * @return the templateId
	 */
	public BigDecimal getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(final BigDecimal templateId) {
		this.templateId = templateId;
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
	 * @return the blockDescription
	 */
	public String getBlockDescription() {
		return blockDescription;
	}

	/**
	 * @param blockDescription
	 *            the blockDescription to set
	 */
	public void setBlockDescription(final String blockDescription) {
		this.blockDescription = blockDescription;
	}

	/**
	 * @return the blockName
	 */
	public String getBlockName() {
		return blockName;
	}

	/**
	 * @param blockName
	 *            the blockName to set
	 */
	public void setBlockName(final String blockName) {
		this.blockName = blockName;
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
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
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

	public int getIwpDocCount() {
		return iwpDocCount;
	}

	public void setIwpDocCount(int iwpDocCount) {
		this.iwpDocCount = iwpDocCount;
	}

}
