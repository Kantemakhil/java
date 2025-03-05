package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VAssOffNeedsCommitBean extends BaseModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VAssOffNeeds> insertList;
	private List<VAssOffNeeds> deleteList;
	private List<VAssOffNeeds> updateList;
	public List<VAssOffNeeds> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VAssOffNeeds> insertList) {
		this.insertList = insertList;
	}
	public List<VAssOffNeeds> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VAssOffNeeds> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VAssOffNeeds> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VAssOffNeeds> updateList) {
		this.updateList = updateList;
	}
	
}
