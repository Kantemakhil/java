package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenseType  extends BaseModel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param offenseDescription
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param offenseType
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
