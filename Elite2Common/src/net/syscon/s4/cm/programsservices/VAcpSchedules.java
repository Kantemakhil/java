package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VAcpSchedules extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("catchUpSessionFlag")
	private String catchUpSessionFlag;
	
	@JsonProperty("crsSchId")
	private Long crsSchId;
	
	@JsonProperty("endTime")
	private Date endTime;
	
	@JsonProperty("internalLocationDesc")
	private String internalLocationDesc;
	
	@JsonProperty("moduleInstanceDesc")
	private String moduleInstanceDesc;
	
	@JsonProperty("moduleInstanceId")
	private Long moduleInstanceId;
	
	@JsonProperty("moduleListSeq")
	private Long moduleListSeq;
	
	@JsonProperty("phaseCode")
	private String phaseCode;
	
	@JsonProperty("phaseDescription")
	private String phaseDescription;
	
	@JsonProperty("phaseInstanceCode")
	private String phaseInstanceCode;
	
	@JsonProperty("phaseInstanceDesc")
	private String phaseInstanceDesc;
	
	@JsonProperty("phaseInstanceId")
	private Long phaseInstanceId;
	
	@JsonProperty("phaseListSeq")
	private Long phaseListSeq;
	
	@JsonProperty("phaseProviderName")
	private String phaseProviderName;
	
	@JsonProperty("phaseProviderPartyClass")
	private String phaseProviderPartyClass;
	
	@JsonProperty("phaseProviderPartyCode")
	private String phaseProviderPartyCode;
	
	@JsonProperty("phaseProviderPartyId")
	private Long phaseProviderPartyId;
	
	@JsonProperty("phaseSessionLength")
	private Long phaseSessionLength;
	
	@JsonProperty("programCode")
	private String programCode;
	
	@JsonProperty("programDesc")
	private String programDesc;
	
	@JsonProperty("programId")
	private Long programId;
	
	@JsonProperty("programInstanceCode")
	private String programInstanceCode;
	
	@JsonProperty("programInstanceDesc")
	private String programInstanceDesc;
	
	@JsonProperty("programInstanceId")
	private Long programInstanceId;
	
	@JsonProperty("scheduleDate")
	private Date scheduleDate;
	
	@JsonProperty("scheduleStatus")
	private String scheduleStatus;
	
	@JsonProperty("sessionNo")
	private Long sessionNo;
	
	@JsonProperty("startTime")
	private Date startTime;

    @JsonProperty("weekDay")
    private String weekDay;

	@JsonProperty("pModuleFrom")
	private Long pModuleFrom;

	@JsonProperty("pModuleTo")
	private Long pModuleTo;

	@JsonProperty("nbtSelect")
	private String nbtSelect;
	
	@JsonProperty("pCrsActyId")
       private Long pCrsActyId; 
	

	public Long getpCrsActyId() {
		return pCrsActyId;
	}

	public void setpCrsActyId(Long pCrsActyId) {
		this.pCrsActyId = pCrsActyId;
	}

	public String getCatchUpSessionFlag() {
		return catchUpSessionFlag;
	}

	public void setCatchUpSessionFlag(String catchUpSessionFlag) {
		this.catchUpSessionFlag = catchUpSessionFlag;
	}

	public Long getCrsSchId() {
		return crsSchId;
	}

	public void setCrsSchId(Long crsSchId) {
		this.crsSchId = crsSchId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInternalLocationDesc() {
		return internalLocationDesc;
	}

	public void setInternalLocationDesc(String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	public String getModuleInstanceDesc() {
		return moduleInstanceDesc;
	}

	public void setModuleInstanceDesc(String moduleInstanceDesc) {
		this.moduleInstanceDesc = moduleInstanceDesc;
	}

	public Long getModuleInstanceId() {
		return moduleInstanceId;
	}

	public void setModuleInstanceId(Long moduleInstanceId) {
		this.moduleInstanceId = moduleInstanceId;
	}

	public Long getModuleListSeq() {
		return moduleListSeq;
	}

	public void setModuleListSeq(Long moduleListSeq) {
		this.moduleListSeq = moduleListSeq;
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

	public String getPhaseInstanceCode() {
		return phaseInstanceCode;
	}

	public void setPhaseInstanceCode(String phaseInstanceCode) {
		this.phaseInstanceCode = phaseInstanceCode;
	}

	public String getPhaseInstanceDesc() {
		return phaseInstanceDesc;
	}

	public void setPhaseInstanceDesc(String phaseInstanceDesc) {
		this.phaseInstanceDesc = phaseInstanceDesc;
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

	public String getPhaseProviderName() {
		return phaseProviderName;
	}

	public void setPhaseProviderName(String phaseProviderName) {
		this.phaseProviderName = phaseProviderName;
	}

	public String getPhaseProviderPartyClass() {
		return phaseProviderPartyClass;
	}

	public void setPhaseProviderPartyClass(String phaseProviderPartyClass) {
		this.phaseProviderPartyClass = phaseProviderPartyClass;
	}

	public String getPhaseProviderPartyCode() {
		return phaseProviderPartyCode;
	}

	public void setPhaseProviderPartyCode(String phaseProviderPartyCode) {
		this.phaseProviderPartyCode = phaseProviderPartyCode;
	}

	public Long getPhaseProviderPartyId() {
		return phaseProviderPartyId;
	}

	public void setPhaseProviderPartyId(Long phaseProviderPartyId) {
		this.phaseProviderPartyId = phaseProviderPartyId;
	}

	public Long getPhaseSessionLength() {
		return phaseSessionLength;
	}

	public void setPhaseSessionLength(Long phaseSessionLength) {
		this.phaseSessionLength = phaseSessionLength;
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

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
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

	public Long getProgramInstanceId() {
		return programInstanceId;
	}

	public void setProgramInstanceId(Long programInstanceId) {
		this.programInstanceId = programInstanceId;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Long getSessionNo() {
		return sessionNo;
	}

	public void setSessionNo(Long sessionNo) {
		this.sessionNo = sessionNo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

	public Long getpModuleFrom() {
		return pModuleFrom;
	}

	public void setpModuleFrom(Long pModuleFrom) {
		this.pModuleFrom = pModuleFrom;
	}

	public Long getpModuleTo() {
		return pModuleTo;
	}

	public void setpModuleTo(Long pModuleTo) {
		this.pModuleTo = pModuleTo;
	}

	public String getNbtSelect() {
		return nbtSelect;
	}

	public void setNbtSelect(String nbtSelect) {
		this.nbtSelect = nbtSelect;
	}

}
