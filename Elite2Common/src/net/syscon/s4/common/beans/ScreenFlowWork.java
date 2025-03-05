package net.syscon.s4.common.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@GlobalValidation(message = "atleast.one.mandatory")
public class ScreenFlowWork extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("workFlowCode")
	private String workFlowCode;
	
	@JsonProperty("moduleName")
	private String	moduleName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("workSeq")
	private Integer workSeq;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	
	@JsonProperty("caseLoadType")
	private String caseLoadType;
	
	@JsonProperty("toolTip")
	private String toolTip;

	public String getWorkFlowCode() {
		return workFlowCode;
	}


	public void setWorkFlowCode(String workFlowCode) {
		this.workFlowCode = workFlowCode;
	}


	public String getModuleName() {
		return moduleName;
	}


	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	public String getdescription() {
		return description;
	}


	public void setdescription(String description) {
		this.description = description;
	}


	public Integer getWorkSeq() {
		return workSeq;
	}


	public void setWorkSeq(Integer workSeq) {
		this.workSeq = workSeq;
	}


	public Date getModifyDatetime() {
		return modifyDatetime;
	}


	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}


	public String getCaseLoadType() {
		return caseLoadType;
	}


	public void setCaseLoadType(String caseLoadType) {
		this.caseLoadType = caseLoadType;
	}


	public String getToolTip() {
		return toolTip;
	}


	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	

	

}


