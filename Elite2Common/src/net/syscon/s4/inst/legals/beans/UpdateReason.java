package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class UpdateReason  extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("category")
	private String category;
	
	
	/***
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/***
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/***
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/***
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


}
