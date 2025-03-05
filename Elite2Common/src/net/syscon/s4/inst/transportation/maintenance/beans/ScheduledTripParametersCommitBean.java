package net.syscon.s4.inst.transportation.maintenance.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class ScheduledTripParametersCommitBean extends BaseModel {

	/**
	 * ScheduledTripParametersCommitBean
	 */
	private static final long serialVersionUID = 1L;
	private List<ScheduledTripParameters> insertList;
	private List<ScheduledTripParameters> deleteList;
	private List<ScheduledTripParameters> updateList;

	public void setInsertList(List<ScheduledTripParameters> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<ScheduledTripParameters> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<ScheduledTripParameters> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ScheduledTripParameters> getInsertList() {
		return insertList;
	}

	public List<ScheduledTripParameters> getUpdateList() {
		return updateList;
	}

	public List<ScheduledTripParameters> getDeleteList() {
		return deleteList;
	}

}
