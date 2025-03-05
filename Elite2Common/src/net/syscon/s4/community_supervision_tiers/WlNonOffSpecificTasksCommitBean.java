package net.syscon.s4.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class WlNonOffSpecificTasksCommitBean extends BaseModel {

	private List<WlNonOffSpecificTasks> insertList;
	private List<WlNonOffSpecificTasks> updateList;
	private List<WlNonOffSpecificTasks> deleteList;

	private List<WlDefaultStaffUnits> startingUnitsInsertList;
	private List<WlDefaultStaffUnits> startingUnitsUpdateList;
	private List<WlDefaultStaffUnits> startingUnitsDeleteList;

	public List<WlNonOffSpecificTasks> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<WlNonOffSpecificTasks> insertList) {
		this.insertList = insertList;
	}

	public List<WlNonOffSpecificTasks> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<WlNonOffSpecificTasks> updateList) {
		this.updateList = updateList;
	}

	public List<WlNonOffSpecificTasks> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<WlNonOffSpecificTasks> deleteList) {
		this.deleteList = deleteList;
	}

	public List<WlDefaultStaffUnits> getStartingUnitsInsertList() {
		return startingUnitsInsertList;
	}

	public void setStartingUnitsInsertList(List<WlDefaultStaffUnits> startingUnitsInsertList) {
		this.startingUnitsInsertList = startingUnitsInsertList;
	}

	public List<WlDefaultStaffUnits> getStartingUnitsUpdateList() {
		return startingUnitsUpdateList;
	}

	public void setStartingUnitsUpdateList(List<WlDefaultStaffUnits> startingUnitsUpdateList) {
		this.startingUnitsUpdateList = startingUnitsUpdateList;
	}

	public List<WlDefaultStaffUnits> getStartingUnitsDeleteList() {
		return startingUnitsDeleteList;
	}

	public void setStartingUnitsDeleteList(List<WlDefaultStaffUnits> startingUnitsDeleteList) {
		this.startingUnitsDeleteList = startingUnitsDeleteList;
	}

}
