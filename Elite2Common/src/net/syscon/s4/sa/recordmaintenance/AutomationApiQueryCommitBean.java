package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class AutomationApiQueryCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<AutomationApiQuery> insertList;
	private List<AutomationApiQuery> deleteList;
	private List<AutomationApiQuery> updateList;
	
	public List<AutomationApiQuery> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<AutomationApiQuery> insertList) {
		this.insertList = insertList;
	}
	public List<AutomationApiQuery> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<AutomationApiQuery> deleteList) {
		this.deleteList = deleteList;
	}
	public List<AutomationApiQuery> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<AutomationApiQuery> updateList) {
		this.updateList = updateList;
	}
	
	

}
