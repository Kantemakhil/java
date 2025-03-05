package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class ModuleTabColumnsCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<ModuleTabColumns> insertList;
	private List<ModuleTabColumns> deleteList;
	private List<ModuleTabColumns> updateList;
	public List<ModuleTabColumns> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<ModuleTabColumns> insertList) {
		this.insertList = insertList;
	}
	public List<ModuleTabColumns> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<ModuleTabColumns> deleteList) {
		this.deleteList = deleteList;
	}
	public List<ModuleTabColumns> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<ModuleTabColumns> updateList) {
		this.updateList = updateList;
	}

	
}
