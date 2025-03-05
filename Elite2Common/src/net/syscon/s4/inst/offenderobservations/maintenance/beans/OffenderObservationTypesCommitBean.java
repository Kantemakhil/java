package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationTypesCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderObservationTypes> insertList;

	@JsonProperty("deleteList")
	private List<OffenderObservationTypes> deleteList;

	@JsonProperty("updateList")
	private List<OffenderObservationTypes> updateList;

	public List<OffenderObservationTypes> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderObservationTypes> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderObservationTypes> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderObservationTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderObservationTypes> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderObservationTypes> updateList) {
		this.updateList = updateList;
	}
	
}
