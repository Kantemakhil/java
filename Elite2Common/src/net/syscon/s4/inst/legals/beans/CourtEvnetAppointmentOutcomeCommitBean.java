package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CourtEvnetAppointmentOutcomeCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CourtEvnetAppointmentOutcome> insertList;
	
	@JsonProperty("updateList")
	private List<CourtEvnetAppointmentOutcome> updateList;
	
	@JsonProperty("deleteList")
	private List<CourtEvnetAppointmentOutcome> deleteList;

	public List<CourtEvnetAppointmentOutcome> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<CourtEvnetAppointmentOutcome> insertList) {
		this.insertList = insertList;
	}

	public List<CourtEvnetAppointmentOutcome> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<CourtEvnetAppointmentOutcome> updateList) {
		this.updateList = updateList;
	}

	public List<CourtEvnetAppointmentOutcome> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<CourtEvnetAppointmentOutcome> deleteList) {
		this.deleteList = deleteList;
	}
	
}
