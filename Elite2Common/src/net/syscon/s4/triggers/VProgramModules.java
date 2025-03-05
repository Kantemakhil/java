package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.BaseModel;

public class VProgramModules extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long programModuleId;
	private Integer listSeq;
	private String description;
	private Long programPhaseId;
	private Integer noOfSessions;
	private Integer sessionLength;
	private String startFlag;
	private String activeFlag;
	private Long crsActyId;

	public Long getProgramModuleId() {
		return programModuleId;
	}

	public void setProgramModuleId(Long programModuleId) {
		this.programModuleId = programModuleId;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getProgramPhaseId() {
		return programPhaseId;
	}

	public void setProgramPhaseId(Long programPhaseId) {
		this.programPhaseId = programPhaseId;
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

	public String getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getCrsActyId() {
		return crsActyId;
	}

	public void setCrsActyId(Long crsActyId) {
		this.crsActyId = crsActyId;
	}

}
