package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class AutomationQueryParametersCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<AutomationQueryParameters> insertList;
	private List<AutomationQueryParameters> deleteList;
	private List<AutomationQueryParameters> updateList;
	
	public List<AutomationQueryParameters> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<AutomationQueryParameters> insertList) {
		this.insertList = insertList;
	}
	public List<AutomationQueryParameters> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<AutomationQueryParameters> deleteList) {
		this.deleteList = deleteList;
	}
	public List<AutomationQueryParameters> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<AutomationQueryParameters> updateList) {
		this.updateList = updateList;
	}
	
	
}
