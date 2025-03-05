package net.syscon.s4.common.beans.trim;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MIN {

	@JsonProperty("__type")
	private String type;

	@JsonProperty("Value")
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MIN [type=");
		builder.append(type);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
