package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class QmActivitiesIns extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal activityInsId;
	private Date startTime;
	private Date endTime;
	private String qmActInsStatusDomain;
	private String qmActInsStatusCode;
	private BigDecimal teamId;
	Integer staffId;
	private BigDecimal processInsId;
	private BigDecimal activityId;
	private String sealFlag;
	private String createUserId;
	private Date createDatetime;
	private String modifyUserId;
	private Date modifyDatetime;

	public BigDecimal getActivityInsId() {
		return activityInsId;
	}

	public void setActivityInsId(BigDecimal activityInsId) {
		this.activityInsId = activityInsId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getQmActInsStatusDomain() {
		return qmActInsStatusDomain;
	}

	public void setQmActInsStatusDomain(String qmActInsStatusDomain) {
		this.qmActInsStatusDomain = qmActInsStatusDomain;
	}

	public String getQmActInsStatusCode() {
		return qmActInsStatusCode;
	}

	public void setQmActInsStatusCode(String qmActInsStatusCode) {
		this.qmActInsStatusCode = qmActInsStatusCode;
	}

	public BigDecimal getTeamId() {
		return teamId;
	}

	public void setTeamId(BigDecimal teamId) {
		this.teamId = teamId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getProcessInsId() {
		return processInsId;
	}

	public void setProcessInsId(BigDecimal processInsId) {
		this.processInsId = processInsId;
	}

	public BigDecimal getActivityId() {
		return activityId;
	}

	public void setActivityId(BigDecimal activityId) {
		this.activityId = activityId;
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
