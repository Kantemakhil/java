package net.syscon.s4.cm.weeklyactivityplans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class WeeklyActivityPlansHtyCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<WeeklyActivityPlansHty> insertList;
	private List<WeeklyActivityPlansHty> deleteList;
	private List<WeeklyActivityPlansHty> updateList;
	public List<WeeklyActivityPlansHty> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<WeeklyActivityPlansHty> insertList) {
		this.insertList = insertList;
	}
	public List<WeeklyActivityPlansHty> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<WeeklyActivityPlansHty> deleteList) {
		this.deleteList = deleteList;
	}
	public List<WeeklyActivityPlansHty> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<WeeklyActivityPlansHty> updateList) {
		this.updateList = updateList;
	}
	
}
