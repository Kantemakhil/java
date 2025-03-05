package net.syscon.s4.pkgs;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class QmConActivityTeams extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long manActTeamId;
	private Long compositionId;
	private Long activityId;
	private Long teamId;
	private BigDecimal allocatedTime;
	private String activeFlag;
	private Date expiryDate;
	private String sealFlag;
	private String createUserId;
	private Date createDatetime;
	private String modifyUserId;
	private Date modifyDatetime;

	public Long getManActTeamId() {
		return manActTeamId;
	}

	public void setManActTeamId(Long manActTeamId) {
		this.manActTeamId = manActTeamId;
	}

	public Long getCompositionId() {
		return compositionId;
	}

	public void setCompositionId(Long compositionId) {
		this.compositionId = compositionId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public BigDecimal getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(BigDecimal allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

}
