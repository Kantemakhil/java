package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ModulePrivileges extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("moduleName")
	@NotNull
	@Size(max = 20)
	private String moduleName;

	@JsonProperty("roleId")
	@NotNull
	private Long roleId;

	@JsonProperty("accessPrivilege")
	@NotNull
	@Size(max = 1)
	private String accessPrivilege;

	@JsonProperty("verificationFlag")
	@Size(max = 1)
	private String verificationFlag;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("moduleDescription")
	private String moduleDescription;
	
	@JsonProperty("moduleType")
	private String moduleType;
	
	@JsonProperty("dspRoleName")
	private String dspRoleName;
	

	/**
	 * Creates new ModulePrivileges class Object
	 */
	public ModulePrivileges() {
		// ModulePrivileges
	}

	/**
	 * @return moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(final Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return accessPrivilege
	 */
	public String getAccessPrivilege() {
		return this.accessPrivilege;
	}

	/**
	 * @param accessPrivilege
	 *            the accessPrivilege to set
	 */
	public void setAccessPrivilege(final String accessPrivilege) {
		this.accessPrivilege = accessPrivilege;
	}

	/**
	 * @return createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return verificationFlag
	 */
	public String getVerificationFlag() {
		return this.verificationFlag;
	}

	/**
	 * @param verificationFlag
	 *            the verificationFlag to set
	 */
	public void setVerificationFlag(final String verificationFlag) {
		this.verificationFlag = verificationFlag;
	}

	/**
	 * @return createDatetime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return modifyDatetime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getDspRoleName() {
		return dspRoleName;
	}

	public void setDspRoleName(String dspRoleName) {
		this.dspRoleName = dspRoleName;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	
}
