package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class HelpMedia extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String moduleName;
	private String helpType;
	private String helpLink;
	private String helpDesc;
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
	public String getHelpLink() {
		return helpLink;
	}
	public void setHelpLink(String helpLink) {
		this.helpLink = helpLink;
	}
	public String getHelpDesc() {
		return helpDesc;
	}
	public void setHelpDesc(String helpDesc) {
		this.helpDesc = helpDesc;
	}
	
	
	

}
