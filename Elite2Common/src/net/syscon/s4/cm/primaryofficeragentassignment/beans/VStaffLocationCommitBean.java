package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.util.List;



public class VStaffLocationCommitBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<VStaffLocation> insertList;
	private List<VStaffLocation> deleteList;
	private List<VStaffLocation> updateList;
	public List<VStaffLocation> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VStaffLocation> insertList) {
		this.insertList = insertList;
	}
	public List<VStaffLocation> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VStaffLocation> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VStaffLocation> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VStaffLocation> updateList) {
		this.updateList = updateList;
	}

}
