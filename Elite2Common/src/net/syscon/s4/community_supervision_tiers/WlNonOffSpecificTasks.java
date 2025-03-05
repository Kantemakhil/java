package net.syscon.s4.community_supervision_tiers;

import java.io.Serializable;
import java.util.Date;

public class WlNonOffSpecificTasks implements Serializable{

	private String agyLocId;
	private String workloadTaskType;
	private Integer units;
	private String staffPosition;
	private String sealFlag;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private Date createDatetime;
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
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
	
	public String getStaffPosition() {
		return staffPosition;
	}
	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
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
}
