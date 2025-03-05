package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OicOffenceIndicatorsCommitBean extends BaseModel implements Serializable{
	private List<OicOffenceIndicators> insertList;
	private List<OicOffenceIndicators> deleteList;
	private List<OicOffenceIndicators> updateList;
	public List<OicOffenceIndicators> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OicOffenceIndicators> insertList) {
		this.insertList = insertList;
	}
	public List<OicOffenceIndicators> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OicOffenceIndicators> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OicOffenceIndicators> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OicOffenceIndicators> updateList) {
		this.updateList = updateList;
	}
	

}
