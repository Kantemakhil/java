package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the IWP_PARAMETER_MAPPINGS database table.
 * 
 */
public class IwpParameterMappings implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long mappingId;

	private Date createDatetime;

	private String createUserId;

	private String documentContextFlag;

	private String fieldName;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	@JsonProperty("templateModuleId")
	private BigDecimal templateModuleId;
	private String bookmarkName;
	private String parameterName;
	private String dataType;

	public IwpParameterMappings() {
		// IwpParameterMappings
	}

	public long getMappingId() {
		return this.mappingId;
	}

	public void setMappingId(long mappingId) {
		this.mappingId = mappingId;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDocumentContextFlag() {
		return this.documentContextFlag;
	}

	public void setDocumentContextFlag(String documentContextFlag) {
		this.documentContextFlag = documentContextFlag;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getTemplateModuleId() {
		return templateModuleId;
	}

	public void setTemplateModuleId(BigDecimal templateModuleId) {
		this.templateModuleId = templateModuleId;
	}

	public String getBookmarkName() {
		return bookmarkName;
	}

	public void setBookmarkName(String bookmarkName) {
		this.bookmarkName = bookmarkName;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
