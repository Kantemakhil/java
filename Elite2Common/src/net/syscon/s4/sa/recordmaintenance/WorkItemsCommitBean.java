package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class WorkItemsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<WorkItems> insertList;
	private List<WorkItems> deleteList;
	private List<WorkItems> updateList;
	
	public List<WorkItems> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<WorkItems> insertList) {
		this.insertList = insertList;
	}
	public List<WorkItems> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<WorkItems> deleteList) {
		this.deleteList = deleteList;
	}
	public List<WorkItems> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<WorkItems> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
