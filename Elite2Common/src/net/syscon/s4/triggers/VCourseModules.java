package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VCourseModules extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long servicesAddressId;
	private Long internalLocationId;
	private String commentText;
	private String caseloadType;
	private Integer noOfSessions;
	private Integer sessionLength;
	private Long courseModuleId;
	private Long coursePhaseId;
	private Long programModuleId;
	private String moduleCourseActivityType;
	private Integer listSeq;
	private Date scheduleStartDate;
	private Date scheduleEndDate;
	private Long programPhaseId;

	
	public Long getProgramPhaseId() {
		return programPhaseId;
	}

	public void setProgramPhaseId(Long programPhaseId) {
		this.programPhaseId = programPhaseId;
	}

	public Long getServicesAddressId() {
		return servicesAddressId;
	}

	public void setServicesAddressId(Long servicesAddressId) {
		this.servicesAddressId = servicesAddressId;
	}

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public Integer getNoOfSessions() {
		return noOfSessions;
	}

	public void setNoOfSessions(Integer noOfSessions) {
		this.noOfSessions = noOfSessions;
	}

	public Integer getSessionLength() {
		return sessionLength;
	}

	public void setSessionLength(Integer sessionLength) {
		this.sessionLength = sessionLength;
	}

	public Long getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(Long courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	public Long getCoursePhaseId() {
		return coursePhaseId;
	}

	public void setCoursePhaseId(Long coursePhaseId) {
		this.coursePhaseId = coursePhaseId;
	}

	public Long getProgramModuleId() {
		return programModuleId;
	}

	public void setProgramModuleId(Long programModuleId) {
		this.programModuleId = programModuleId;
	}

	public String getModuleCourseActivityType() {
		return moduleCourseActivityType;
	}

	public void setModuleCourseActivityType(String moduleCourseActivityType) {
		this.moduleCourseActivityType = moduleCourseActivityType;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

}
