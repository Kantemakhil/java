package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CourseSchedules extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("crsSchId")
	private Long crsSchId;

	@JsonProperty("crsActyId")
	private Long crsActyId;

	@JsonProperty("weekday")
	private String weekday;

	@JsonProperty("scheduleDate")
	private Date scheduleDate;

	@JsonProperty("startTime")
	private Date startTime;

	@JsonProperty("endTime")
	private Date endTime;

	@JsonProperty("sessionNo")
	private Long sessionNo;

	@JsonProperty("details")
	private String details;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	
	@JsonProperty("catchUpCrsSchId")
	private Long catchUpCrsSchId;

	@JsonProperty("videoReferenceId")
	private String videoReferenceId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("cancelledFlag")
	private String cancelledFlag;
	
	@JsonProperty("occurrenceCode")
	private String occurrenceCode;
	
	@JsonProperty("moduleDesc")
	private String moduleDesc;
	
	@JsonProperty("providerDesc")
	private String providerDesc;
	
	@JsonProperty("reviewDate")
	private Date reviewDate;
	
	@JsonProperty("providerPartyClass")
    private String providerPartyClass;
	
	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getProviderDesc() {
		return providerDesc;
	}

	public void setProviderDesc(String providerDesc) {
		this.providerDesc = providerDesc;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public String getOccurrenceCode() {
		return occurrenceCode;
	}

	public void setOccurrenceCode(String occurrenceCode) {
		this.occurrenceCode = occurrenceCode;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getPhaseDesc() {
		return phaseDesc;
	}

	public void setPhaseDesc(String phaseDesc) {
		this.phaseDesc = phaseDesc;
	}

	public String getCrsSchIdcrsSchId() {
		return crsSchIdcrsSchId;
	}

	public void setCrsSchIdcrsSchId(String crsSchIdcrsSchId) {
		this.crsSchIdcrsSchId = crsSchIdcrsSchId;
	}

	public BigDecimal getProgramInstanceId() {
		return programInstanceId;
	}

	public void setProgramInstanceId(BigDecimal programInstanceId) {
		this.programInstanceId = programInstanceId;
	}

	public BigDecimal getProgramId() {
		return programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public String getProgramDesc() {
		return programDesc;
	}

	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@JsonProperty("providerCode")
	private String providerCode;
	
	@JsonProperty("serviceId")
	private String serviceId;
	
	
	@JsonProperty("phaseDesc")
	private String phaseDesc;
	
	
	@JsonProperty("crsSchIdcrsSchId")
	private String crsSchIdcrsSchId;
	
	@JsonProperty("programInstanceId")
	private BigDecimal programInstanceId;
	
	@JsonProperty("programId")
	private BigDecimal programId;
	
	@JsonProperty("programDesc")
	private String programDesc;
	
	@JsonProperty("scheduleStatus")
	private String scheduleStatus;

	
	@JsonProperty("phaseId")
	private String phaseId;
	
	@JsonProperty("catchUpSessionFlag")
	private String catchUpSessionFlag;
	
	public String getCatchUpSessionFlag() {
		return catchUpSessionFlag;
	}

	public void setCatchUpSessionFlag(String catchUpSessionFlag) {
		this.catchUpSessionFlag = catchUpSessionFlag;
	}

	public CourseSchedules() {
		super();
	}

	public Long getCrsSchId() {
		return crsSchId;
	}

	public void setCrsSchId(Long crsSchId) {
		this.crsSchId = crsSchId;
	}

	public Long getCrsActyId() {
		return crsActyId;
	}

	public void setCrsActyId(Long crsActyId) {
		this.crsActyId = crsActyId;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
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

	public Long getSessionNo() {
		return sessionNo;
	}

	public void setSessionNo(Long sessionNo) {
		this.sessionNo = sessionNo;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Long getCatchUpCrsSchId() {
		return catchUpCrsSchId;
	}

	public void setCatchUpCrsSchId(Long catchUpCrsSchId) {
		this.catchUpCrsSchId = catchUpCrsSchId;
	}

	public String getVideoReferenceId() {
		return videoReferenceId;
	}

	public void setVideoReferenceId(String videoReferenceId) {
		this.videoReferenceId = videoReferenceId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getCancelledFlag() {
		return cancelledFlag;
	}

	public void setCancelledFlag(String cancelledFlag) {
		this.cancelledFlag = cancelledFlag;
	}

	public String getProviderPartyClass() {
		return providerPartyClass;
	}

	public void setProviderPartyClass(String providerPartyClass) {
		this.providerPartyClass = providerPartyClass;
	}


}