package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class ActionApiCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ActionApi> insertList;
	private List<ActionApi> deleteList;
	private List<ActionApi> updateList;
	
	public List<ActionApi> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<ActionApi> insertList) {
		this.insertList = insertList;
	}
	public List<ActionApi> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<ActionApi> deleteList) {
		this.deleteList = deleteList;
	}
	public List<ActionApi> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<ActionApi> updateList) {
		this.updateList = updateList;
	}

}
