package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("createUserId")
	private String createUserId;

	/**
	 *
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}