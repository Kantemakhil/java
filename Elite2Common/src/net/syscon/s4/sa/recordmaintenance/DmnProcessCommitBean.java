package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class DmnProcessCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<DmnProcess> insertList;
	private List<DmnProcess> deleteList;
	private List<DmnProcess> updateList;
	private String sourceModule;
	
	public List<DmnProcess> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<DmnProcess> insertList) {
		this.insertList = insertList;
	}
	public List<DmnProcess> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<DmnProcess> deleteList) {
		this.deleteList = deleteList;
	}
	public List<DmnProcess> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<DmnProcess> updateList) {
		this.updateList = updateList;
	}
	public String getSourceModule() {
		return sourceModule;
	}
	public void setSourceModule(String sourceModule) {
		this.sourceModule = sourceModule;
	}
	
}
