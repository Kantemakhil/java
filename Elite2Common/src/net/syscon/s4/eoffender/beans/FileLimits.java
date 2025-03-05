package net.syscon.s4.eoffender.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class FileLimits extends BaseModel implements Serializable {
	
	
	
	@JsonProperty("profileCode")
	private String profileCode;
	
	@JsonProperty("profileValue")
	private String profileValue;


	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getProfileValue() {
		return profileValue;
	}

	public void setProfileValue(String profileValue) {
		this.profileValue = profileValue;
	}

	
	
}
