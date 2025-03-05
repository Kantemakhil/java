package net.syscon.s4.inst.legals.au;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class PeriodicDetentionStatusesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	
	private List<PeriodicDetentionStatuses> insertList;
	private List<PeriodicDetentionStatuses> deleteList;
	private List<PeriodicDetentionStatuses> updateList;
	public List<PeriodicDetentionStatuses> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<PeriodicDetentionStatuses> insertList) {
		this.insertList = insertList;
	}
	public List<PeriodicDetentionStatuses> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<PeriodicDetentionStatuses> deleteList) {
		this.deleteList = deleteList;
	}
	public List<PeriodicDetentionStatuses> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<PeriodicDetentionStatuses> updateList) {
		this.updateList = updateList;
	}
	
	
	

}
