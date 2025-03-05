package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VCoursePhases extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String activeFlag;
	private Date expiryDate;
	private String commentText;
	private String providerPartyClass;
	private Long providerPartyId;
	private String providerPartyCode;
	private Long servicesAddressId;
	private Long placementCorporateId;
	private Long internalLocationId;
	private String internalLocationDesc;
	private Long coursePhaseId;
	private String coursePhaseName;
	private Long parentCrsActyId;
	private Long programPhaseId;
	private String caseloadType;
	private String caseloadTypeDesc;
	private Integer capacity;
	private Date scheduleStartDate;
	private Date scheduleEndDate;

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

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getProviderPartyClass() {
		return providerPartyClass;
	}

	public void setProviderPartyClass(String providerPartyClass) {
		this.providerPartyClass = providerPartyClass;
	}

	public Long getProviderPartyId() {
		return providerPartyId;
	}

	public void setProviderPartyId(Long providerPartyId) {
		this.providerPartyId = providerPartyId;
	}

	public String getProviderPartyCode() {
		return providerPartyCode;
	}

	public void setProviderPartyCode(String providerPartyCode) {
		this.providerPartyCode = providerPartyCode;
	}

	public Long getServicesAddressId() {
		return servicesAddressId;
	}

	public void setServicesAddressId(Long servicesAddressId) {
		this.servicesAddressId = servicesAddressId;
	}

	public Long getPlacementCorporateId() {
		return placementCorporateId;
	}

	public void setPlacementCorporateId(Long placementCorporateId) {
		this.placementCorporateId = placementCorporateId;
	}

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getInternalLocationDesc() {
		return internalLocationDesc;
	}

	public void setInternalLocationDesc(String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	public Long getCoursePhaseId() {
		return coursePhaseId;
	}

	public void setCoursePhaseId(Long coursePhaseId) {
		this.coursePhaseId = coursePhaseId;
	}

	public String getCoursePhaseName() {
		return coursePhaseName;
	}

	public void setCoursePhaseName(String coursePhaseName) {
		this.coursePhaseName = coursePhaseName;
	}

	public Long getParentCrsActyId() {
		return parentCrsActyId;
	}

	public void setParentCrsActyId(Long parentCrsActyId) {
		this.parentCrsActyId = parentCrsActyId;
	}

	public Long getProgramPhaseId() {
		return programPhaseId;
	}

	public void setProgramPhaseId(Long programPhaseId) {
		this.programPhaseId = programPhaseId;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCaseloadTypeDesc() {
		return caseloadTypeDesc;
	}

	public void setCaseloadTypeDesc(String caseloadTypeDesc) {
		this.caseloadTypeDesc = caseloadTypeDesc;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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
