package net.syscon.s4.common.beans.trim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordClassification {

	@JsonProperty("ClassificationTitle")
	private ClassificationTitle classificationTitle;

	@JsonProperty("TrimType")
	private String trimType;

	@JsonProperty("Uri")
	private String uri;

	public ClassificationTitle getClassificationTitle() {
		return classificationTitle;
	}

	public void setClassificationTitle(ClassificationTitle classificationTitle) {
		this.classificationTitle = classificationTitle;
	}

	public String getTrimType() {
		return trimType;
	}

	public void setTrimType(String trimType) {
		this.trimType = trimType;
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
		builder.append("RecordClassification [classificationTitle=");
		builder.append(classificationTitle);
		builder.append(", trimType=");
		builder.append(trimType);
		builder.append(", uri=");
		builder.append(uri);
		builder.append("]");
		return builder.toString();
	}

}
