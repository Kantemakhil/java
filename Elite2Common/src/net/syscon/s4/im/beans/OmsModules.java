package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OmsModules extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("moduleName")
	@NotNull
	@Size(max = 20)
	private String moduleName;

	@JsonProperty("applnCode")
	private String applnCode;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("defaultCopy")
	private BigDecimal defaultCopy;

	@JsonProperty("description")
	private String description;

	@JsonProperty("helpDirectory")
	private String helpDirectory;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("moduleType")
	private String moduleType;

	@JsonProperty("outputType")
	private String outputType;

	@JsonProperty("previewFlag")
	private String previewFlag;

	@JsonProperty("printFormatCode")
	private String printFormatCode;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("vFormDesc")
	private String vFormDesc;
	
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	
	@JsonProperty("dynamic_form")
	private String dynamicForm;
	
	@JsonProperty("accessModuleName")
	private String accessModuleName;
	
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

	@JsonProperty("listSeq")
	private Integer listSeq;

	public OmsModules() {
		// OmsModules
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 *
	 * @return
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 *
	 * @param moduleName
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 *
	 * @return
	 */
	public String getApplnCode() {
		return applnCode;
	}

	/**
	 *
	 * @param applnCode
	 */
	public void setApplnCode(final String applnCode) {
		this.applnCode = applnCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getDefaultCopy() {
		return defaultCopy;
	}

	/**
	 *
	 * @param defaultCopy
	 */
	public void setDefaultCopy(final BigDecimal defaultCopy) {
		this.defaultCopy = defaultCopy;
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
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 *
	 * @return
	 */
	public String getHelpDirectory() {
		return helpDirectory;
	}

	/**
	 *
	 * @param helpDirectory
	 */
	public void setHelpDirectory(final String helpDirectory) {
		this.helpDirectory = helpDirectory;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getModuleType() {
		return moduleType;
	}

	/**
	 *
	 * @param moduleType
	 */
	public void setModuleType(final String moduleType) {
		this.moduleType = moduleType;
	}

	/**
	 *
	 * @return
	 */
	public String getOutputType() {
		return outputType;
	}

	/**
	 *
	 * @param outputType
	 */
	public void setOutputType(final String outputType) {
		this.outputType = outputType;
	}

	/**
	 *
	 * @return
	 */
	public String getPreviewFlag() {
		return previewFlag;
	}

	/**
	 *
	 * @param previewFlag
	 */
	public void setPreviewFlag(final String previewFlag) {
		this.previewFlag = previewFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getPrintFormatCode() {
		return printFormatCode;
	}

	/**
	 *
	 * @param printFormatCode
	 */
	public void setPrintFormatCode(final String printFormatCode) {
		this.printFormatCode = printFormatCode;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(String dynamicForm) {
		this.dynamicForm = dynamicForm;
	}

}