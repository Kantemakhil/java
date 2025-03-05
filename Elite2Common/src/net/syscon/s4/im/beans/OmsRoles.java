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
public class OmsRoles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("roleId")
	@NotNull
	private Integer roleId;

	@JsonProperty("roleName")
	@NotNull
	@Size(max = 30)
	private String roleName;

	@JsonProperty("roleSeq")
	private Integer roleSeq;

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
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("roleCode")
	@NotNull
	@Size(max = 30)
	private String roleCode;

	@JsonProperty("parentRoleCode")
	@Size(max = 30)
	private String parentRoleCode;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("accessPrivilege")
	private String accessPrivilege;

	@JsonProperty("verificationFlag")
	private String verificationFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;

	@JsonProperty("seqOne")
	private BigDecimal seqOne;
	/**
	 * Creates new OmsRoles class Object
	 */
	public OmsRoles() {
		// OmsRoles
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleSeq
	 */
	public Integer getRoleSeq() {
		return roleSeq;
	}

	/**
	 * @param roleSeq
	 *            the roleSeq to set
	 */
	public void setRoleSeq(Integer roleSeq) {
		this.roleSeq = roleSeq;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode
	 *            the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the parentRoleCode
	 */
	public String getParentRoleCode() {
		return parentRoleCode;
	}

	/**
	 * @param parentRoleCode
	 *            the parentRoleCode to set
	 */
	public void setParentRoleCode(String parentRoleCode) {
		this.parentRoleCode = parentRoleCode;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealflag
	 *            the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the accessPrivilege
	 */
	public String getAccessPrivilege() {
		return accessPrivilege;
	}

	/**
	 * @param accessPrivilege
	 *            the accessPrivilege to set
	 */
	public void setAccessPrivilege(String accessPrivilege) {
		this.accessPrivilege = accessPrivilege;
	}

	/**
	 * @return the verificationFlag
	 */
	public String getVerificationFlag() {
		return verificationFlag;
	}

	/**
	 * @param verificationFlag
	 *            the verificationFlag to set
	 */
	public void setVerificationFlag(String verificationFlag) {
		this.verificationFlag = verificationFlag;
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
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getSeqOne() {
		return seqOne;
	}

	public void setSeqOne(BigDecimal seqOne) {
		this.seqOne = seqOne;
	}

}