package net.syscon.s4.sa.admin.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class RoleInaccessibleRefCodes extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("domain")
	private String domain;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("roleId")
	private Long roleId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	
}
