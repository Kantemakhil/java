package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OicSanctionLimitsCommitBean extends  BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OicSanctionLimits> insertList;
	private List<OicSanctionLimits> deleteList;
	private List<OicSanctionLimits> updateList;
	public List<OicSanctionLimits> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OicSanctionLimits> insertList) {
		this.insertList = insertList;
	}
	public List<OicSanctionLimits> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OicSanctionLimits> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OicSanctionLimits> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OicSanctionLimits> updateList) {
		this.updateList = updateList;
	}
	

}
