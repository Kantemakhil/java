package net.syscon.s4.common.beans.trim;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordResponseStatus {

	@JsonProperty("ErrorCode")
	private String errorCode;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Errors")
	private List<Errors> errors;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Errors> getErrors() {
		return errors;
	}

	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecordResponseStatus [errorCode=");
		builder.append(errorCode);
		builder.append(", message=");
		builder.append(message);
		builder.append(", errors=");
		builder.append(errors);
		builder.append("]");
		return builder.toString();
	}



}
