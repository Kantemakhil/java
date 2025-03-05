package net.syscon.s4.sa.admin.beans;


import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.WorkflowScreens;

public class WorkflowScreenCommitBean extends BaseModel{

	private static final long serialVersionUID = 1L;
	private List<WorkflowScreens> insertList;
	private List<WorkflowScreens> deleteList;
	private List<WorkflowScreens> updateList;
	public List<WorkflowScreens> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<WorkflowScreens> insertList) {
		this.insertList = insertList;
	}
	public List<WorkflowScreens> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<WorkflowScreens> deleteList) {
		this.deleteList = deleteList;
	}
	public List<WorkflowScreens> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<WorkflowScreens> updateList) {
		this.updateList = updateList;
	}

	

	
	

}
