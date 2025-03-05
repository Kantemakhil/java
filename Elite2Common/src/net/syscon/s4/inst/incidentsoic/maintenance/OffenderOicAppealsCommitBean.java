package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderOicAppealsCommitBean extends BaseModel implements Serializable {
	
	@JsonProperty("insertList")
	private List<OffenderOicAppeals> insertList;
	@JsonProperty("deleteList")
	private List<OffenderOicAppeals> deleteList;
	@JsonProperty("updateList")
	private List<OffenderOicAppeals> updateList;
	
	public List<OffenderOicAppeals> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderOicAppeals> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderOicAppeals> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderOicAppeals> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderOicAppeals> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderOicAppeals> updateList) {
		this.updateList = updateList;
	}
	
	
	
	

}
