package net.syscon.s4.im.beans;

import java.io.Serializable;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
/**
 * 
 * class IwpTemplates
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class IwpTemplates  extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonProperty("templateName")
	private String templateName;
	@JsonProperty("templateId")
	private BigDecimal templateId;
	@JsonProperty("location")
	private String location;
	@JsonProperty("description")
	private String description;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("templateBody")
	private byte[] templateBody;
	@JsonProperty("templateContent")
	private byte[] templateContent;
	@JsonProperty("lockPassword")
	private String lockPassword;
	@JsonProperty("objectType")
	private String objectType;
	@JsonProperty("dateCreated")
	private Date dateCreated;
	@JsonProperty("userCreated")
	private String userCreated;
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
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("code")
	private String code;
	@JsonProperty("edrmsRecordNo")
	private String edrmsRecordNo;
	@JsonProperty("edrmsFolder")
	private String edrmsFolder;
	@JsonProperty("templateType")
	private String templateType;
	@JsonProperty("fileExtension")
	private String fileExtension;
	@JsonProperty("isTemplate")
	private String isTemplate;
	@JsonProperty("roleCode")
	private String roleCode;
	
	@JsonProperty("signatureAccess")
	private String signatureAccess;
	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	@JsonProperty("blockName")
	private String blockName;
	@JsonProperty("blockDescription")
	private String blockDescription;
	@JsonProperty("relCount")
	private Integer relCount;
	@JsonProperty("contextRuleCount")
	private Integer contextRuleCount;
	
	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;
	@JsonProperty("activeCheckFlag")
	private String activeCheckFlag;
	@JsonProperty("iwpDocCount")
	private Integer iwpDocCount;
	
	
	public String getEdrmsRecordNo() {
		return edrmsRecordNo;
	}

	public void setEdrmsRecordNo(String edrmsRecordNo) {
		this.edrmsRecordNo = edrmsRecordNo;
	}

	public String getEdrmsFolder() {
		return edrmsFolder;
	}

	public void setEdrmsFolder(String edrmsFolder) {
		this.edrmsFolder = edrmsFolder;
	}

	public IwpTemplates(){
		//IwpTemplates
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(final String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the templateId
	 */
	public BigDecimal getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(final BigDecimal templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
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
	 * @return the templateBody
	 */
	public byte[] getTemplateBody() {
		return templateBody;
	}

	/**
	 * @param templateBody the templateBody to set
	 */
	public void setTemplateBody(final byte[] templateBody) {
		this.templateBody = templateBody;
	}

	/**
	 * @return the lockPassword
	 */
	public String getLockPassword() {
		return lockPassword;
	}

	/**
	 * @param lockPassword the lockPassword to set
	 */
	public void setLockPassword(final String lockPassword) {
		this.lockPassword = lockPassword;
	}

	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(final String objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the userCreated
	 */
	public String getUserCreated() {
		return userCreated;
	}

	/**
	 * @param userCreated the userCreated to set
	 */
	public void setUserCreated(final String userCreated) {
		this.userCreated = userCreated;
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
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(byte[] templateContent) {
		this.templateContent = templateContent;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getBlockDescription() {
		return blockDescription;
	}

	public void setBlockDescription(String blockDescription) {
		this.blockDescription = blockDescription;
	}

	public Integer getRelCount() {
		return relCount;
	}

	public void setRelCount(Integer relCount) {
		this.relCount = relCount;
	}
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public Integer getContextRuleCount() {
		return contextRuleCount;
	}

	public void setContextRuleCount(Integer contextRuleCount) {
		this.contextRuleCount = contextRuleCount;
	}

	public String getActiveCheckFlag() {
		return activeCheckFlag;
	}

	public void setActiveCheckFlag(String activeCheckFlag) {
		this.activeCheckFlag = activeCheckFlag;
	}

	public Integer getIwpDocCount() {
		return iwpDocCount;
	}

	public void setIwpDocCount(Integer iwpDocCount) {
		this.iwpDocCount = iwpDocCount;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getSignatureAccess() {
		return signatureAccess;
	}

	public void setSignatureAccess(String signatureAccess) {
		this.signatureAccess = signatureAccess;
	}
	
	
}
