package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class BpmnProcessCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<BpmnProcess> insertList;
	private List<BpmnProcess> deleteList;
	private List<BpmnProcess> updateList;
	private String sourceModule;
	
	public List<BpmnProcess> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<BpmnProcess> insertList) {
		this.insertList = insertList;
	}
	public List<BpmnProcess> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<BpmnProcess> deleteList) {
		this.deleteList = deleteList;
	}
	public List<BpmnProcess> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<BpmnProcess> updateList) {
		this.updateList = updateList;
	}
	public String getSourceModule() {
		return sourceModule;
	}
	public void setSourceModule(String sourceModule) {
		this.sourceModule = sourceModule;
	}
	
}
