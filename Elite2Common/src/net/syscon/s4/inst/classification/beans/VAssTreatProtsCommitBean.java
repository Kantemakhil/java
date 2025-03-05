package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VAssTreatProtsCommitBean extends BaseModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VAssTreatProts> insertList;
	private List<VAssTreatProts> deleteList;
	private List<VAssTreatProts> updateList;
	public List<VAssTreatProts> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VAssTreatProts> insertList) {
		this.insertList = insertList;
	}
	public List<VAssTreatProts> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VAssTreatProts> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VAssTreatProts> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VAssTreatProts> updateList) {
		this.updateList = updateList;
	}
	
}
