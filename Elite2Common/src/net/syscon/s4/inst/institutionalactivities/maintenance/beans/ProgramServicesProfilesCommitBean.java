package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ProgramServicesProfilesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<ProgramServicesProfiles> insertList;
	
	@JsonProperty("deleteList")
	private List<ProgramServicesProfiles> deleteList;

	@JsonProperty("updateList")
	private List<ProgramServicesProfiles> updateList;

	public List<ProgramServicesProfiles> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<ProgramServicesProfiles> insertList) {
		this.insertList = insertList;
	}

	public List<ProgramServicesProfiles> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<ProgramServicesProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ProgramServicesProfiles> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<ProgramServicesProfiles> updateList) {
		this.updateList = updateList;
	}
	
}
