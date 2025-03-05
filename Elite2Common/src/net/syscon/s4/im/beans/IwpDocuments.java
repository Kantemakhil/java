package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class IwpDocuments
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IwpDocuments extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("documentId")
	private BigDecimal documentId;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("documentName")
	private String documentName;
	@JsonProperty("templateId")
	private BigDecimal templateId;
	@JsonProperty("templateName")
	private String templateName;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("location")
	private String location;
	@JsonProperty("objectId")
	private String objectId;
	@JsonProperty("objectType")
	private String objectType;
	@JsonProperty("documentStatus")
	private String documentStatus;
	private byte[] TemplateBody;
	@JsonProperty("templateContent")
	private byte[] templateContent;
	private byte[] documentBody;
	@JsonProperty("documentContent")
	private byte[] documentContent;
	@JsonProperty("dateCreated")
	private Date dateCreated;
	@JsonProperty("dateModified")
	private Date dateModified;
	@JsonProperty("userCreated")
	private String userCreated;
	@JsonProperty("userModified")
	private String userModified;
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
	@JsonProperty("documentContext")
	private String documentContext;
	
	private String fileExtension;
	
	@JsonProperty("signatureAccess")
	private String signatureAccess;
	
	@JsonProperty("signedDoc")
	private byte[] signedDoc;
	
	private String moduleDescription;
	
	private String deleteReason;
	
	
	public byte[] getTemplateBody() {
		return TemplateBody;
	}

	public void setTemplateBody(byte[] templateBody) {
		TemplateBody = templateBody;
	}

	public byte[] getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(byte[] templateContent) {
		this.templateContent = templateContent;
	}

	public byte[] getSignedDoc() {
		return signedDoc;
	}

	public void setSignedDoc(byte[] signedDoc) {
		this.signedDoc = signedDoc;
	}

	public IwpDocuments() {
		// IwpDocuments
	}

	/**
	 * @return the documentId
	 */
	public BigDecimal getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(final BigDecimal documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(final String documentName) {
		this.documentName = documentName;
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
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
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
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(final String objectId) {
		this.objectId = objectId;
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
	 * @return the documentStatus
	 */
	public String getDocumentStatus() {
		return documentStatus;
	}

	/**
	 * @param documentStatus the documentStatus to set
	 */
	public void setDocumentStatus(final String documentStatus) {
		this.documentStatus = documentStatus;
	}

	/**
	 * @return the documentBody
	 */
	public byte[] getDocumentBody() {
		return documentBody;
	}

	/**
	 * @param documentBody the documentBody to set
	 */
	public void setDocumentBody(final byte[] documentBody) {
		this.documentBody = documentBody;
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
	 * @return the dateModified
	 */
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @param dateModified the dateModified to set
	 */
	public void setDateModified(final Date dateModified) {
		this.dateModified = dateModified;
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
	 * @return the userModified
	 */
	public String getUserModified() {
		return userModified;
	}

	/**
	 * @param userModified the userModified to set
	 */
	public void setUserModified(final String userModified) {
		this.userModified = userModified;
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
	 * @return the documentContext
	 */
	public String getDocumentContext() {
		return documentContext;
	}

	/**
	 * @param documentContext the documentContext to set
	 */
	public void setDocumentContext(final String documentContext) {
		this.documentContext = documentContext;
	}

	public byte[] getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(byte[] documentContent) {
		this.documentContent = documentContent;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}

	public String getSignatureAccess() {
		return signatureAccess;
	}

	public void setSignatureAccess(String signatureAccess) {
		this.signatureAccess = signatureAccess;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	

}
