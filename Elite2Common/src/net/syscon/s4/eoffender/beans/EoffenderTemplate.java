package net.syscon.s4.eoffender.beans;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EoffenderTemplate {

	@JsonProperty("code")
	private String code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("isGenTemplate")
	private String isGenTemplate;
	
	@JsonProperty("templateId")
	private BigDecimal templateId;
	
	@JsonProperty("uri")
	private String uri;	
	
	public String getCode() {
		return code;
	}

	public BigDecimal getTemplateId() {
		return templateId;
	}

	public void setTemplateId(BigDecimal templateId) {
		this.templateId = templateId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EoffenderTemplate other = (EoffenderTemplate) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getIsGenTemplate() {
		return isGenTemplate;
	}

	public void setIsGenTemplate(String isGenTemplate) {
		this.isGenTemplate = isGenTemplate;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EoffenderTemplate [code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append(", isFolder=");
		builder.append(isGenTemplate);
		builder.append(", templateId=");
		builder.append(templateId);
		builder.append(", uri=");
		builder.append(uri);
		builder.append("]");
		return builder.toString();
	}
	
	

	
}
