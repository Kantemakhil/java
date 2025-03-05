package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderHealthProblemsCommitBean extends BaseModel implements Serializable{
	private List<OffenderHealthProblems> insertList;
	private List<OffenderHealthProblems> deleteList;
	private List<OffenderHealthProblems> updateList;
	public List<OffenderHealthProblems> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<OffenderHealthProblems> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderHealthProblems> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<OffenderHealthProblems> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderHealthProblems> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<OffenderHealthProblems> updateList) {
		this.updateList = updateList;
	}
	
}
