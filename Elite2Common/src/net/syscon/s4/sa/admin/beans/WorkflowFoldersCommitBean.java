package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.WorkFlowFolders;

public class WorkflowFoldersCommitBean extends BaseModel {

	private List<WorkFlowFolders> insertList;
	private List<WorkFlowFolders> deleteList;
	private List<WorkFlowFolders> updateList;
	public List<WorkFlowFolders> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<WorkFlowFolders> insertList) {
		this.insertList = insertList;
	}
	public List<WorkFlowFolders> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<WorkFlowFolders> deleteList) {
		this.deleteList = deleteList;
	}
	public List<WorkFlowFolders> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<WorkFlowFolders> updateList) {
		this.updateList = updateList;
	}
	 
	

}
