package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VQmAt extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long processId;
	private Long activityId;
	private String name;
	private String nameDesc;
	private String description;
	private Integer sequence;
	private BigDecimal allocatedTime;
	private String activeFlag;
	private Date expiryDate;
	private Long manActTeamId;
	private Long compositionId;
	private Long teamId;
	private String teamDesc;
	private Long qmcaAllocatedTime;
	private Date qmcaExpiryDate;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDesc() {
		return nameDesc;
	}

	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamDesc() {
		return teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}

	public Long getQmcaAllocatedTime() {
		return qmcaAllocatedTime;
	}

	public void setQmcaAllocatedTime(Long qmcaAllocatedTime) {
		this.qmcaAllocatedTime = qmcaAllocatedTime;
	}

	public Date getQmcaExpiryDate() {
		return qmcaExpiryDate;
	}

	public void setQmcaExpiryDate(Date qmcaExpiryDate) {
		this.qmcaExpiryDate = qmcaExpiryDate;
	}

}
