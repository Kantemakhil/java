package net.syscon.s4.sa.admin.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ModuleTables extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("moduleTabId")
	private Long moduleTabId;
	
	@JsonProperty("objectName")
	private String objectName;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("auditFlag")
	private String auditFlag;
	
	@JsonProperty("moduleBlock")
	private String moduleBlock;
	
	@JsonProperty("disableAuditFlag")
	private String disableAuditFlag;
	
	@JsonProperty("warnMessage")
	private String warnMessage;
	
	@JsonProperty("tableDescription")
	private String tableDescription;
	
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Long getModuleTabId() {
		return moduleTabId;
	}
	public void setModuleTabId(Long moduleTabId) {
		this.moduleTabId = moduleTabId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public String getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}
	public String getModuleBlock() {
		return moduleBlock;
	}
	public void setModuleBlock(String moduleBlock) {
		this.moduleBlock = moduleBlock;
	}
	public String getDisableAuditFlag() {
		return disableAuditFlag;
	}
	public void setDisableAuditFlag(String disableAuditFlag) {
		this.disableAuditFlag = disableAuditFlag;
	}
	public String getWarnMessage() {
		return warnMessage;
	}
	public String getTableDescription() {
		return tableDescription;
	}
	public void setTableDescription(String tableDescription) {
		this.tableDescription = tableDescription;
	}
	public void setWarnMessage(String warnMessage) {
		this.warnMessage = warnMessage;
	}
	
	
}
