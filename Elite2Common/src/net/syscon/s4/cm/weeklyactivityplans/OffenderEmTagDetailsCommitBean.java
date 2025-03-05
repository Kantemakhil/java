package net.syscon.s4.cm.weeklyactivityplans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderEmTagDetailsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderEmTagDetails> insertList;
	private List<OffenderEmTagDetails> deleteList;
	private List<OffenderEmTagDetails> updateList;
	public List<OffenderEmTagDetails> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderEmTagDetails> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderEmTagDetails> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderEmTagDetails> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderEmTagDetails> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderEmTagDetails> updateList) {
		this.updateList = updateList;
	}
	
}
