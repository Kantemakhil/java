package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailUser extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1572997295768387528L;
	
	private String key;
	private String keyDetails;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyDetails() {
		return keyDetails;
	}
	public void setKeyDetails(String keyDetails) {
		this.keyDetails = keyDetails;
	}
	
	

}
