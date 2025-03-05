package net.syscon.s4.common.beans.trim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {

	@JsonProperty("MIN")
	private MIN min;

	@JsonProperty("TemplateUri")
	private TemplateUri TemplateUri;

	@JsonProperty("ObjectId")
	private ObjectId objectId;
	
	@JsonProperty("ObjectType")
	private ObjectType objectType;
	
	
	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}

	public MIN getMin() {
		return min;
	}

	public void setMin(MIN min) {
		this.min = min;
	}

	public TemplateUri getTemplateUri() {
		return TemplateUri;
	}

	public void setTemplateUri(TemplateUri templateUri) {
		TemplateUri = templateUri;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fields [min=");
		builder.append(min);
		builder.append(", TemplateUri=");
		builder.append(TemplateUri);
		builder.append("]");
		return builder.toString();
	}

}
