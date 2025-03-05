package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationZonesCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderObservationZones> insertList;

	@JsonProperty("deleteList")
	private List<OffenderObservationZones> deleteList;

	@JsonProperty("updateList")
	private List<OffenderObservationZones> updateList;

	public List<OffenderObservationZones> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderObservationZones> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderObservationZones> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderObservationZones> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderObservationZones> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderObservationZones> updateList) {
		this.updateList = updateList;
	}
	
}
