package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;

public class CommunityConditionsCommitBean extends BaseModel{
	@JsonProperty("insertList")
	private List<CommunityConditions> insertList;

	@JsonProperty("deleteList")
	private List<CommunityConditions> deleteList;

	@JsonProperty("updateList")
	private List<CommunityConditions> updateList;
	
	@JsonProperty("reportInsertList")
	private List<CommunityConditions> reportInsertList;

	public List<CommunityConditions> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<CommunityConditions> insertList) {
		this.insertList = insertList;
	}

	public List<CommunityConditions> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<CommunityConditions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CommunityConditions> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<CommunityConditions> updateList) {
		this.updateList = updateList;
	}

	public List<CommunityConditions> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<CommunityConditions> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
}
