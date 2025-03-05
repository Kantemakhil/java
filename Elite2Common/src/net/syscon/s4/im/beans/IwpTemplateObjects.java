package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * 
 * class IwpTemplateObjects
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IwpTemplateObjects extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("iwpTemplateObjectId")
	private BigDecimal iwpTemplateObjectId;
	@JsonProperty("templateId")
	private BigDecimal templateId;
	@JsonProperty("templateName")
	private String templateName;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("objectCode")
	private String objectCode;
	@JsonProperty("objectType")
	private String objectType;
	@JsonProperty("sealFlag")
	private String sealFlag;

	public IwpTemplateObjects() {
		// IwpTemplateObjects
	}

	/**
	 * @return the iwpTemplateObjectId
	 */
	public BigDecimal getIwpTemplateObjectId() {
		return iwpTemplateObjectId;
	}

	/**
	 * @param iwpTemplateObjectId
	 *            the iwpTemplateObjectId to set
	 */
	public void setIwpTemplateObjectId(final BigDecimal iwpTemplateObjectId) {
		this.iwpTemplateObjectId = iwpTemplateObjectId;
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
	 * @return the objectCode
	 */
	public String getObjectCode() {
		return objectCode;
	}

	/**
	 * @param objectCode
	 *            the objectCode to set
	 */
	public void setObjectCode(final String objectCode) {
		this.objectCode = objectCode;
	}

	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType
	 *            the objectType to set
	 */
	public void setObjectType(final String objectType) {
		this.objectType = objectType;
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
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName
	 *            the templateName to set
	 */
	public void setTemplateName(final String templateName) {
		this.templateName = templateName;
	}

}
