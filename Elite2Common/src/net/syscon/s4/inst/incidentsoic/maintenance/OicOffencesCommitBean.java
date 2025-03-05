package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OicOffences;

public class OicOffencesCommitBean extends BaseModel implements Serializable{
	private List<OicOffences> insertList;
	private List<OicOffences> deleteList;
	private List<OicOffences> updateList;
	public List<OicOffences> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OicOffences> insertList) {
		this.insertList = insertList;
	}
	public List<OicOffences> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OicOffences> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OicOffences> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OicOffences> updateList) {
		this.updateList = updateList;
	}
	
}
