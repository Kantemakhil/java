package net.syscon.s4.community_supervision_tiers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class WlOfficerNonOffSpecificTasks extends BaseModel implements Serializable {
	private Integer staffId;
	private String agyLocId;
	private Date fromDate;
	private String staffPosition;
	private String staffRole;
	private String workloadTaskType;
	private Integer units;
	private String activeFlag;
	private String sealFlag;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private Date createDatetime;
	private String defaultTask;
	private BigDecimal availableUnits;
	private Long staffLocRoleId;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getStaffPosition() {
		return staffPosition;
	}

	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

	public String getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	public String getWorkloadTaskType() {
		return workloadTaskType;
	}

	public void setWorkloadTaskType(String workloadTaskType) {
		this.workloadTaskType = workloadTaskType;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getDefaultTask() {
		return defaultTask;
	}

	public void setDefaultTask(String defaultTask) {
		this.defaultTask = defaultTask;
	}

	public BigDecimal getAvailableUnits() {
		return availableUnits;
	}

	public void setAvailableUnits(BigDecimal availableUnits) {
		this.availableUnits = availableUnits;
	}

	public Long getStaffLocRoleId() {
		return staffLocRoleId;
	}

	public void setStaffLocRoleId(Long staffLocRoleId) {
		this.staffLocRoleId = staffLocRoleId;
	}
}
