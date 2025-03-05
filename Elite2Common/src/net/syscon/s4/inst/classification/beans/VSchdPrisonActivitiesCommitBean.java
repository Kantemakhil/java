package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VSchdPrisonActivitiesCommitBean extends BaseModel  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VSchdPrisonActivities> insertList;
	private List<VSchdPrisonActivities> deleteList;
	private List<VSchdPrisonActivities> updateList;
	public List<VSchdPrisonActivities> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<VSchdPrisonActivities> insertList) {
		this.insertList = insertList;
	}
	public List<VSchdPrisonActivities> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<VSchdPrisonActivities> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VSchdPrisonActivities> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<VSchdPrisonActivities> updateList) {
		this.updateList = updateList;
	}

	

}
