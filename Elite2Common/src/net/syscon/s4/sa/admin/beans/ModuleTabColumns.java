package net.syscon.s4.sa.admin.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ModuleTabColumns extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("columnName")
	private String columnName;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("domain")
	private String domain;
	
	@JsonProperty("lovItemName")
	private String lovItemName;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("moduleTabId")
	private Long moduleTabId;
	
	@JsonProperty("moduleTabSeq")
	private Long moduleTabSeq;
	
	@JsonProperty("refTables")
	private String refTables;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("setupModule")
	private String setupModule;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLovItemName() {
		return lovItemName;
	}

	public void setLovItemName(String lovItemName) {
		this.lovItemName = lovItemName;
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

	public Long getModuleTabId() {
		return moduleTabId;
	}

	public void setModuleTabId(Long moduleTabId) {
		this.moduleTabId = moduleTabId;
	}

	public Long getModuleTabSeq() {
		return moduleTabSeq;
	}

	public void setModuleTabSeq(Long moduleTabSeq) {
		this.moduleTabSeq = moduleTabSeq;
	}

	public String getRefTables() {
		return refTables;
	}

	public void setRefTables(String refTables) {
		this.refTables = refTables;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSetupModule() {
		return setupModule;
	}

	public void setSetupModule(String setupModule) {
		this.setupModule = setupModule;
	}
	
	
	
}
