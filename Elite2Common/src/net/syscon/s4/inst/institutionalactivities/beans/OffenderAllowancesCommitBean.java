package net.syscon.s4.inst.institutionalactivities.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderAllowancesCommitBean extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderAllowances> insertList;
	private List<OffenderAllowances> deleteList;
	private List<OffenderAllowances> updateList;
	public List<OffenderAllowances> getInsertList() {
		return insertList;
	}
	public List<OffenderAllowances> getDeleteList() {
		return deleteList;
	}
	public List<OffenderAllowances> getUpdateList() {
		return updateList;
	}
	public void setInsertList(List<OffenderAllowances> insertList) {
		this.insertList = insertList;
	}
	public void setDeleteList(List<OffenderAllowances> deleteList) {
		this.deleteList = deleteList;
	}
	public void setUpdateList(List<OffenderAllowances> updateList) {
		this.updateList = updateList;
	}
	
	
}
