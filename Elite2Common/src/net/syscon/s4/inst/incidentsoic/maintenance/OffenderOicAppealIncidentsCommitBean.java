package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderOicAppealIncidentsCommitBean extends BaseModel implements Serializable {
	
	@JsonProperty("insertList")
	private List<OffenderOicAppealIncidents> insertList;
	@JsonProperty("deleteList")
	private List<OffenderOicAppealIncidents> deleteList;
	@JsonProperty("updateList")
	private List<OffenderOicAppealIncidents> updateList;
	
	public List<OffenderOicAppealIncidents> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderOicAppealIncidents> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderOicAppealIncidents> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderOicAppealIncidents> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderOicAppealIncidents> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderOicAppealIncidents> updateList) {
		this.updateList = updateList;
	}
	
	

}
