package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Type extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("typeOfCase")
	private String typeOfCase;
	
	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTypeOfCase() {
		return typeOfCase;
	}
	
	/**
	 * 
	 * @param typeOfCase
	 */
	public void setTypeOfCase(String typeOfCase) {
		this.typeOfCase = typeOfCase;
	}

	
}
