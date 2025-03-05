package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModuleInsDashboardBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("module")
	private String module;
	
	@JsonProperty("dashboard")
	private String dashboard;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("offenderSpecificFlag")
	private String offenderSpecificFlag;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("code")
	private String code;

	@JsonProperty("canDisplay")
	private Boolean canDisplay=true;

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDashboard() {
		return dashboard;
	}

	public void setDashboard(String dashboard) {
		this.dashboard = dashboard;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getOffenderSpecificFlag() {
		return offenderSpecificFlag;
	}

	public void setOffenderSpecificFlag(String offenderSpecificFlag) {
		this.offenderSpecificFlag = offenderSpecificFlag;
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

	public String getCode() {
		this.code = this.module;
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
