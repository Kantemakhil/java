package net.syscon.s4.eoffender.beans;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EoffenderDocumentRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("templateName")
	private String templateName;

	@JsonProperty("templateType")
	private String templateType;
	
	@JsonProperty("templateUri")
	private String templateUri;
	
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	@JsonProperty("eoffenderDetails")
	private EoffenderDetails eoffenderDetails;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public EoffenderDetails getEoffenderDetails() {
		return eoffenderDetails;
	}

	public void setEoffenderDetails(EoffenderDetails eoffenderDetails) {
		this.eoffenderDetails = eoffenderDetails;
	}

	public String getTemplateUri() {
		return templateUri;
	}

	public void setTemplateUri(String templateUri) {
		this.templateUri = templateUri;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EoffenderDocumentRequest [templateName=");
		builder.append(templateName);
		builder.append(", templateType=");
		builder.append(templateType);
		builder.append(", templateUri=");
		builder.append(templateUri);
		builder.append(", eoffenderDetails=");
		builder.append(eoffenderDetails);
		builder.append("]");
		return builder.toString();
	}	

}
