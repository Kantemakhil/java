package net.syscon.s4.eoffender.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 *
 * class Document
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("offenderDisplayId")
	private String offenderDisplayId;
	
	@JsonProperty("offenderBookingNo")
	private String offenderBookingNo;
	
	@JsonProperty("offDOB")
	private String offDOB;

	@JsonProperty("documentId")
	private String documentId;
	
	@JsonProperty("temproaryDocumentId")
	private String temproaryDocumentId;

	@JsonProperty("documentType")
	private String documentType;

	@JsonProperty("documentName")
	private String documentName;

	@JsonProperty("documentAuthor")
	private String documentAuthor;

	@JsonProperty("templateId")
	private String templateId;

	@JsonProperty("createdDate")
	private String createdDate;
	
	@JsonProperty("modifiedDate")
	private String modifiedDate;

	@JsonProperty("status")
	private String status;

	@JsonProperty("action")
	private String action;

	@JsonProperty("fileName")
	private String fileName;

	@JsonProperty("file")
	private byte[] fileByteArray;

	@JsonProperty("type")
	private String type;

	@JsonProperty("metaDataLabel")
	private String metaDataLabel;

	@JsonProperty("image")
	private String image;


	@JsonProperty("uri")
	private String uri;
	
	@JsonProperty("templateUri")
	private String templateUri;

	@JsonProperty("templateName")
	private String templateName;
	
	@JsonProperty("trimUser")
	private String trimUser;
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("timeStamp")
	private Long timeStamp;
	
	@JsonProperty("fileExt")
	private String fileExt;
	
	@JsonProperty("createdDateAsDate")
	private Date createdDateAsDate;
	
	@JsonProperty("modifiedDateAsDate")
	private Date modifiedDateAsDate;

	@JsonProperty("signatureAccess")
	private String signatureAccess;
	
	
	@JsonProperty("signedDoc")
	private byte[] signedDoc;
	
	@JsonProperty("objectType")
	private String objectType;
	
	@JsonProperty("commentText")
	private String commentText;
	
	private String moduleDescription;
	public Date getCreatedDateAsDate() {
		return createdDateAsDate;
	}

	public void setCreatedDateAsDate(Date createdDateAsDate) {
		this.createdDateAsDate = createdDateAsDate;
	}

	public Date getModifiedDateAsDate() {
		return modifiedDateAsDate;
	}

	public void setModifiedDateAsDate(Date modifiedDateAsDate) {
		this.modifiedDateAsDate = modifiedDateAsDate;
	}

	private boolean isCheckOut = false;
	
	private Metadata metadata;


	public String getTemplateUri() {
		return templateUri;
	}

	public void setTemplateUri(String templateUri) {
		this.templateUri = templateUri;
	}

	public Document() {
		// Documents
	}

	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(final String documentId) {
		this.documentId = documentId;
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

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentAuthor() {
		return documentAuthor;
	}

	public void setDocumentAuthor(String documentAuthor) {
		this.documentAuthor = documentAuthor;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileByteArray() {
		return fileByteArray;
	}

	public void setFileByteArray(byte[] fileByteArray) {
		this.fileByteArray = fileByteArray;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMetaDataLabel() {
		return metaDataLabel;
	}

	public void setMetaDataLabel(String metaDataLabel) {
		this.metaDataLabel = metaDataLabel;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	
	public String getTrimUser() {
		return trimUser;
	}

	public void setTrimUser(String trimUser) {
		this.trimUser = trimUser;
	}
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getTemproaryDocumentId() {
		return temproaryDocumentId;
	}

	public void setTemproaryDocumentId(String temproaryDocumentId) {
		this.temproaryDocumentId = temproaryDocumentId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Document [offenderBookId=");
		builder.append(offenderBookId);
		builder.append(", documentId=");
		builder.append(documentId);
		builder.append(", documentType=");
		builder.append(documentType);
		builder.append(", documentName=");
		builder.append(documentName);
		builder.append(", documentAuthor=");
		builder.append(documentAuthor);
		builder.append(", templateId=");
		builder.append(templateId);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", action=");
		builder.append(action);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", fileByteArray=");
		builder.append(Arrays.toString(fileByteArray));
		builder.append(", type=");
		builder.append(type);
		builder.append(", metaDataLabel=");
		builder.append(metaDataLabel);
		builder.append(", image=");
		builder.append(image);
		builder.append(", uri=");
		builder.append(uri);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append(", trimUser=");
		builder.append(trimUser);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getOffenderDisplayId() {
		return offenderDisplayId;
	}

	public void setOffenderDisplayId(String offenderDisplayId) {
		this.offenderDisplayId = offenderDisplayId;
	}

	public String getOffDOB() {
		return offDOB;
	}

	public void setOffDOB(String offDOB) {
		this.offDOB = offDOB;
	}

	public String getOffenderBookingNo() {
		return offenderBookingNo;
	}

	public void setOffenderBookingNo(String offenderBookingNo) {
		this.offenderBookingNo = offenderBookingNo;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public boolean isCheckOut() {
		return isCheckOut;
	}

	public void setCheckOut(boolean isCheckOut) {
		this.isCheckOut = isCheckOut;
	}

	public String getSignatureAccess() {
		return signatureAccess;
	}

	public void setSignatureAccess(String signatureAccess) {
		this.signatureAccess = signatureAccess;
	}

	public byte[] getSignedDoc() {
		return signedDoc;
	}

	public void setSignedDoc(byte[] signedDoc) {
		this.signedDoc = signedDoc;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	
	

}
