package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ExternalInvestigationOffensesCommitBean extends BaseModel implements Serializable  {
	
	@JsonProperty("insertList")
	private List<ExternalInvestigationOffenses> insertList;
	
	@JsonProperty("updateList")
	private List<ExternalInvestigationOffenses> updateList;

	@JsonProperty("deleteList")
	private List<ExternalInvestigationOffenses> deleteList;
	
	@JsonProperty("loginUserName")
	private String loginUserName;

	public List<ExternalInvestigationOffenses> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<ExternalInvestigationOffenses> insertList) {
		this.insertList = insertList;
	}

	public List<ExternalInvestigationOffenses> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<ExternalInvestigationOffenses> updateList) {
		this.updateList = updateList;
	}

	public List<ExternalInvestigationOffenses> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<ExternalInvestigationOffenses> deleteList) {
		this.deleteList = deleteList;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	@Override
	public String toString() {
		return "OcucieidCommitBean [insertList=" + insertList + ", updateList=" + updateList + ", deleteList="
				+ deleteList + ", loginUserName=" + loginUserName + "]";
	}
	

	

}
