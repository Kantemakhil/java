package net.syscon.s4.common.beans.dao;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordGroup extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("oid")
	private String oid;
	
	

	/**
	*
	*@return 
	*/
	public String getId(){
		return id;
	}

	/**
	*
	*@param id 
	*/
	public void setId(String id){
		this.id = id;
	}

	/**
	*
	*@return 
	*/
	public String getName(){
		return name;
	}

	/**
	*
	*@param name 
	*/
	public void setName(String name){
		this.name = name;
	}
	
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

}