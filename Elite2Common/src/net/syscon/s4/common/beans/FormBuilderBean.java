package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@GlobalValidation(message = "atleast.one.mandatory")
public class FormBuilderBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("formId")
	private Integer formId;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("formName")
	private String formName;
	
	@JsonProperty("formJson")
	private String formJson;

	@JsonProperty("formIdentifier")
	private String formIdentifier;
	
	@JsonProperty("sealFlag")
	private String sealFlag;

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormJson() {
		return formJson;
	}

	public void setFormJson(String formJson) {
		this.formJson = formJson;
	}

	public String getFormIdentifier() {
		return formIdentifier;
	}

	public void setFormIdentifier(String formIdentifier) {
		this.formIdentifier = formIdentifier;
	}
		
}