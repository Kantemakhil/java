package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VQmPai extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long processInsId;
	private String detail;
	private Date startTime;
	private String qmProcInsStatusDomain;
	private String qmProcInsStatusCode;
	private Long processId;
	private Long compositionId;
	private Long objectInsId;
	private String value;
	private String lastName;
	private String firstName;
	private String offenderIdDisplay;
	private Long objectId;
	private Long activityInsId;
	private Date endTime;
	private String qmActInsStatusDomain;
	private String qmActInsStatusCode;
	private Long teamId;
	private Integer staffId;
	private Long activityId;
	private String managingAgyLocId;
	private String eventTypeAgyLocId;
	private Long manActTeamId;

	public Long getProcessInsId() {
		return processInsId;
	}

	public void setProcessInsId(Long processInsId) {
		this.processInsId = processInsId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getQmProcInsStatusDomain() {
		return qmProcInsStatusDomain;
	}

	public void setQmProcInsStatusDomain(String qmProcInsStatusDomain) {
		this.qmProcInsStatusDomain = qmProcInsStatusDomain;
	}

	public String getQmProcInsStatusCode() {
		return qmProcInsStatusCode;
	}

	public void setQmProcInsStatusCode(String qmProcInsStatusCode) {
		this.qmProcInsStatusCode = qmProcInsStatusCode;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public Long getCompositionId() {
		return compositionId;
	}

	public void setCompositionId(Long compositionId) {
		this.compositionId = compositionId;
	}

	public Long getObjectInsId() {
		return objectInsId;
	}

	public void setObjectInsId(Long objectInsId) {
		this.objectInsId = objectInsId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getActivityInsId() {
		return activityInsId;
	}

	public void setActivityInsId(Long activityInsId) {
		this.activityInsId = activityInsId;
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

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getManagingAgyLocId() {
		return managingAgyLocId;
	}

	public void setManagingAgyLocId(String managingAgyLocId) {
		this.managingAgyLocId = managingAgyLocId;
	}

	public String getEventTypeAgyLocId() {
		return eventTypeAgyLocId;
	}

	public void setEventTypeAgyLocId(String eventTypeAgyLocId) {
		this.eventTypeAgyLocId = eventTypeAgyLocId;
	}

	public Long getManActTeamId() {
		return manActTeamId;
	}

	public void setManActTeamId(Long manActTeamId) {
		this.manActTeamId = manActTeamId;
	}

}
