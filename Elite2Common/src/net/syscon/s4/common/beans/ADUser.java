package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ADUser extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1326956503404258161L;
	private String userId;
	private String password;
	private String decryptPassword;
	
	public String getDecryptPassword() {
		return decryptPassword;
	}
	public void setDecryptPassword(String decryptPassword) {
		this.decryptPassword = decryptPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
