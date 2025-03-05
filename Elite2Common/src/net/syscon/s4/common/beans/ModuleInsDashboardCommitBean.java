package net.syscon.s4.common.beans;

import java.util.List;

public class ModuleInsDashboardCommitBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	private List<ModuleInsDashboardBean> insertList;
	private List<ModuleInsDashboardBean> deleteList;
	private List<ModuleInsDashboardBean> updateList;
	
	public List<ModuleInsDashboardBean> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<ModuleInsDashboardBean> insertList) {
		this.insertList = insertList;
	}
	public List<ModuleInsDashboardBean> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<ModuleInsDashboardBean> deleteList) {
		this.deleteList = deleteList;
	}
	public List<ModuleInsDashboardBean> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<ModuleInsDashboardBean> updateList) {
		this.updateList = updateList;
	}
	
}
