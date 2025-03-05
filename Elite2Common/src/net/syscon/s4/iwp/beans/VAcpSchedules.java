package net.syscon.s4.iwp.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VAcpSchedules  extends BaseModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("phaseInstanceId")
	private Long phaseInstanceId;
	
	@JsonProperty("phaseListSeq")
	private Long phaseListSeq;
	
	
	@JsonProperty("phaseSessionLength")
	private Long phaseSessionLength;
	
	
	@JsonProperty("phaseInstanceDesc")
	private String phaseInstanceDesc;
	
	@JsonProperty("moduleInstanceId")
	private BigDecimal moduleInstanceId;
	
	@JsonProperty("moduleListSeq")
	private BigDecimal moduleListSeq;
	
	@JsonProperty("moduleInstanceDesc")
	private String moduleInstanceDesc;
	
	@JsonProperty("crsSchId")
	private Long crsSchId;
	
	@JsonProperty("scheduleDate")
	private Date scheduleDate;
	
	@JsonProperty("startTime")
	private Date startTime;
	
	@JsonProperty("endTime")
	private Date endTime;
	
	@JsonProperty("sessionNo")
	private Long sessionNo;
	
	@JsonProperty("catchUpSessionFlag")
	private String catchUpSessionFlag;
	
	@JsonProperty("internalLocationDesc")
	private String internalLocationDesc;
	
	@JsonProperty("scheduleStatus")
	private String scheduleStatus;
	
	@JsonProperty("scheduleStatusTemp")
	private String scheduleStatusTemp;
	
	@JsonProperty("programId")
	private Long programId;
	
	@JsonProperty("programCode")
	private String programCode;
	
	@JsonProperty("programDesc")
	private String programDesc;
	
	@JsonProperty("programInstanceId")
	private Long programInstanceId;
	
	@JsonProperty("programInstanceCode")
	private String programInstanceCode;
	
	@JsonProperty("programInstanceDesc")
	private String programInstanceDesc;
	
	@JsonProperty("phaseCode")
	private String phaseCode;
	
	@JsonProperty("phaseDescription")
	private String phaseDescription;
	
	@JsonProperty("phaseProviderPartyClass")
	private String phaseProviderPartyClass;
	
	@JsonProperty("phaseProviderPartyId")
	private Long phaseProviderPartyId;
	
	@JsonProperty("phaseProviderPartyCode")
	private String phaseProviderPartyCode;
	
	@JsonProperty("phaseProviderName")
	private String phaseProviderName;
	
	@JsonProperty("phaseInstanceCode")
	private String phaseInstanceCode;
	
	@JsonProperty("weekDay")
	private String weekDay;

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public Long getPhaseInstanceId() {
		return phaseInstanceId;
	}

	public void setPhaseInstanceId(Long phaseInstanceId) {
		this.phaseInstanceId = phaseInstanceId;
	}

	public Long getPhaseListSeq() {
		return phaseListSeq;
	}

	public void setPhaseListSeq(Long phaseListSeq) {
		this.phaseListSeq = phaseListSeq;
	}

	public Long getPhaseSessionLength() {
		return phaseSessionLength;
	}

	public void setPhaseSessionLength(Long phaseSessionLength) {
		this.phaseSessionLength = phaseSessionLength;
	}

	public String getPhaseInstanceDesc() {
		return phaseInstanceDesc;
	}

	public void setPhaseInstanceDesc(String phaseInstanceDesc) {
		this.phaseInstanceDesc = phaseInstanceDesc;
	}

	public BigDecimal getModuleInstanceId() {
		return moduleInstanceId;
	}

	public void setModuleInstanceId(BigDecimal moduleInstanceId) {
		this.moduleInstanceId = moduleInstanceId;
	}

	public BigDecimal getModuleListSeq() {
		return moduleListSeq;
	}

	public void setModuleListSeq(BigDecimal moduleListSeq) {
		this.moduleListSeq = moduleListSeq;
	}

	public String getModuleInstanceDesc() {
		return moduleInstanceDesc;
	}

	public void setModuleInstanceDesc(String moduleInstanceDesc) {
		this.moduleInstanceDesc = moduleInstanceDesc;
	}

	public Long getCrsSchId() {
		return crsSchId;
	}

	public void setCrsSchId(Long crsSchId) {
		this.crsSchId = crsSchId;
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

	public String getCatchUpSessionFlag() {
		return catchUpSessionFlag;
	}

	public void setCatchUpSessionFlag(String catchUpSessionFlag) {
		this.catchUpSessionFlag = catchUpSessionFlag;
	}

	public String getInternalLocationDesc() {
		return internalLocationDesc;
	}

	public void setInternalLocationDesc(String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getScheduleStatusTemp() {
		return scheduleStatusTemp;
	}

	public void setScheduleStatusTemp(String scheduleStatusTemp) {
		this.scheduleStatusTemp = scheduleStatusTemp;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getProgramDesc() {
		return programDesc;
	}

	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	public Long getProgramInstanceId() {
		return programInstanceId;
	}

	public void setProgramInstanceId(Long programInstanceId) {
		this.programInstanceId = programInstanceId;
	}

	public String getProgramInstanceCode() {
		return programInstanceCode;
	}

	public void setProgramInstanceCode(String programInstanceCode) {
		this.programInstanceCode = programInstanceCode;
	}

	public String getProgramInstanceDesc() {
		return programInstanceDesc;
	}

	public void setProgramInstanceDesc(String programInstanceDesc) {
		this.programInstanceDesc = programInstanceDesc;
	}

	public String getPhaseCode() {
		return phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public String getPhaseDescription() {
		return phaseDescription;
	}

	public void setPhaseDescription(String phaseDescription) {
		this.phaseDescription = phaseDescription;
	}

	public String getPhaseProviderPartyClass() {
		return phaseProviderPartyClass;
	}

	public void setPhaseProviderPartyClass(String phaseProviderPartyClass) {
		this.phaseProviderPartyClass = phaseProviderPartyClass;
	}

	public Long getPhaseProviderPartyId() {
		return phaseProviderPartyId;
	}

	public void setPhaseProviderPartyId(Long phaseProviderPartyId) {
		this.phaseProviderPartyId = phaseProviderPartyId;
	}

	public String getPhaseProviderPartyCode() {
		return phaseProviderPartyCode;
	}

	public void setPhaseProviderPartyCode(String phaseProviderPartyCode) {
		this.phaseProviderPartyCode = phaseProviderPartyCode;
	}

	public String getPhaseProviderName() {
		return phaseProviderName;
	}

	public void setPhaseProviderName(String phaseProviderName) {
		this.phaseProviderName = phaseProviderName;
	}

	public String getPhaseInstanceCode() {
		return phaseInstanceCode;
	}

	public void setPhaseInstanceCode(String phaseInstanceCode) {
		this.phaseInstanceCode = phaseInstanceCode;
	}
	
	
	
	
	

}
