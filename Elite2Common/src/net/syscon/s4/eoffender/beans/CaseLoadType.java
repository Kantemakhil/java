package net.syscon.s4.eoffender.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CaseLoadType extends BaseModel implements Serializable {

	@JsonProperty("workingCaseLoad")
	private String workingCaseLoad;
	
	@JsonProperty("caseLoadType")
	private String caseLoadType;

	public String getWorkingCaseLoad() {
		return workingCaseLoad;
	}

	public void setWorkingCaseLoad(String workingCaseLoad) {
		this.workingCaseLoad = workingCaseLoad;
	}

	public String getCaseLoadType() {
		return caseLoadType;
	}

	public void setCaseLoadType(String caseLoadType) {
		this.caseLoadType = caseLoadType;
	}
	
	

}
