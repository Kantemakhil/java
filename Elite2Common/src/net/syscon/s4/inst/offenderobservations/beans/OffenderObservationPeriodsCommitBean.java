package net.syscon.s4.inst.offenderobservations.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationPeriodsCommitBean extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@JsonProperty("insertList")
	private List<OffenderObservationPeriods> insertList;

	@JsonProperty("deleteList")
	private List<OffenderObservationPeriods> deleteList;

	@JsonProperty("updateList")
	private List<OffenderObservationPeriods> updateList;

	public List<OffenderObservationPeriods> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderObservationPeriods> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderObservationPeriods> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderObservationPeriods> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderObservationPeriods> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderObservationPeriods> updateList) {
		this.updateList = updateList;
	}

	
}
