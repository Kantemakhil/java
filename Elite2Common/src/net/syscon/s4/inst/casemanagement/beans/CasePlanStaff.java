package net.syscon.s4.inst.casemanagement.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CasePlanStaff extends BaseModel {

	@JsonProperty("casePlanStaffRoleId")
	private Integer casePlanStaffRoleId;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("casePlanId")
	@NotNull
	private Integer casePlanId;

	@JsonProperty("staffId")
	private String staffId;

	@JsonProperty("casePlanRole")
	private String casePlanRole;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("cnOfficer")
	private String cnOfficer;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("staffName")
	private String staffName;
	
	@JsonProperty("cpOwner")
	private String cpOwner;
	
	@JsonProperty("operation")
	private String operation;

	public Integer getCasePlanStaffRoleId() {
		return casePlanStaffRoleId;
	}

	public void setCasePlanStaffRoleId(Integer casePlanStaffRoleId) {
		this.casePlanStaffRoleId = casePlanStaffRoleId;
	}

	public String getStaffName() {
		return staffName;
	}

	public String getCasePlanRole() {
		return casePlanRole;
	}

	public void setCasePlanRole(String casePlanRole) {
		this.casePlanRole = casePlanRole;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCnOfficer() {
		return cnOfficer;
	}

	public void setCnOfficer(String cnOfficer) {
		this.cnOfficer = cnOfficer;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getCasePlanId() {
		return casePlanId;
	}

	public void setCasePlanId(Integer casePlanId) {
		this.casePlanId = casePlanId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	public String getCpOwner() {
		return cpOwner;
	}

	public void setCpOwner(String cpOwner) {
		this.cpOwner = cpOwner;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "CasePlanStaff [casePlanStaffRoleId=" + casePlanStaffRoleId + ", offenderBookId=" + offenderBookId
				+ ", casePlanId=" + casePlanId + ", staffId=" + staffId + ", casePlanRole=" + casePlanRole
				+ ", activeFlag=" + activeFlag + ", startDate=" + startDate + ", endDate=" + endDate + ", primaryFlag="
				+ cnOfficer + ", createDatetime=" + createDatetime + ", createUserId=" + createUserId
				+ ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag
				+ ", staffName=" + staffName + ", cpOwner=" + cpOwner + "]";
	}

}
