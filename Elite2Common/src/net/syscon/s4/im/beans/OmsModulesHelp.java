package net.syscon.s4.im.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OmsModulesHelp extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("moduleName")
	@NotNull
	@Size(max = 20)
	private String moduleName;
	
	@JsonProperty("helpType")
	private String helpType;
	
	@JsonProperty("helpUrl")
	private String helpUrl;
	
	@JsonProperty("helpDesc")
	private String helpDesc;
	
	@JsonProperty("rowId")
	private Integer rowId;
	
	private String createUserId;
	private String modifyUserId;


	

	public Integer getRowId() {
		return rowId;
	}




	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}




	public OmsModulesHelp() {
		// OmsModules
	}




	public String getModuleName() {
		return moduleName;
	}




	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}




	public String getHelpType() {
		return helpType;
	}




	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}




	public String getHelpUrl() {
		return helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	public String getHelpDesc() {
		return helpDesc;
	}

	public void setHelpDesc(String helpDesc) {
		this.helpDesc = helpDesc;
	}




	public String getCreateUserId() {
		return createUserId;
	}




	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}




	public String getModifyUserId() {
		return modifyUserId;
	}




	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	/**
	 * @return the code
	 */
	
}