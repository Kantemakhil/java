package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.util.List;

 

public class VAssignedOffendersCommitBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<VAssignedOffenders> insertList;
	private List<VAssignedOffenders> deleteList;
	private List<VAssignedOffenders> updateList;
	public List<VAssignedOffenders> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VAssignedOffenders> insertList) {
		this.insertList = insertList;
	}
	public List<VAssignedOffenders> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VAssignedOffenders> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VAssignedOffenders> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VAssignedOffenders> updateList) {
		this.updateList = updateList;
	}
	

}
