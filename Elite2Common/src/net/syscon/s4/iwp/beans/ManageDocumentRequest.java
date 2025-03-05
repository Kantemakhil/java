package net.syscon.s4.iwp.beans;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ManageDocumentRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("templateName")
	private String templateName;

	@JsonProperty("templateType")
	private String templateType;
	
	@JsonProperty("templateId")
	private Long templateId;
	
	@JsonProperty("docDetails")
	private DocDetails docDetails;
	
	
	@JsonProperty("objectData")
	private Object objectData;
	
	
	private String path;
	
	private String userName;
	
	
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public DocDetails getDocDetails() {
		return docDetails;
	}

	public void setDocDetails(DocDetails docDetails) {
		this.docDetails = docDetails;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EoffenderDocumentRequest [templateName=");
		builder.append(templateName);
		builder.append(", templateType=");
		builder.append(templateType);
		builder.append(", templateUri=");
		builder.append(templateId);
		builder.append(", docDetails=");
		builder.append(docDetails);
		builder.append("]");
		return builder.toString();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object getObjectData() {
		return objectData;
	}

	public void setObjectData(Object objectData) {
		this.objectData = objectData;
	}

	

	

}
