package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.syscon.s4.common.validators.GlobalValidation;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@GlobalValidation(message = "atleast.one.mandatory")
public class RelatedScreens extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("accessModuleName")
	private String accessModuleName;
	
	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccessModuleName() {
		return accessModuleName;
	}

	public void setAccessModuleName(String accessModuleName) {
		this.accessModuleName = accessModuleName;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
