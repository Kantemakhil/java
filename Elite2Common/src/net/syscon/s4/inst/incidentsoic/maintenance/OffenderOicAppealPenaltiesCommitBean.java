package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderOicAppealPenaltiesCommitBean extends BaseModel implements Serializable {
	
	@JsonProperty("insertList")
	private List<OffenderOicAppealPenalties> insertList;
	@JsonProperty("deleteList")
	private List<OffenderOicAppealPenalties> deleteList;
	@JsonProperty("updateList")
	private List<OffenderOicAppealPenalties> updateList;
	public List<OffenderOicAppealPenalties> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderOicAppealPenalties> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderOicAppealPenalties> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderOicAppealPenalties> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderOicAppealPenalties> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderOicAppealPenalties> updateList) {
		this.updateList = updateList;
	}
	
	

}
