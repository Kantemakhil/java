package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.OmsModules;

public class OmsModulesCommitBean extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	private List<OmsModules> insertList;
	private List<OmsModules> deleteList;
	private List<OmsModules> updateList;
	public List<OmsModules> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OmsModules> insertList) {
		this.insertList = insertList;
	}
	public List<OmsModules> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OmsModules> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OmsModules> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OmsModules> updateList) {
		this.updateList = updateList;
	}

	
}
