package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderProposedMvmntsCommitBean extends BaseModel implements Serializable{
	
	private List<OffenderProposedMvmnts> insertList;
	private List<OffenderProposedMvmnts> deleteList;
	private List<OffenderProposedMvmnts> updateList;
	
	public List<OffenderProposedMvmnts> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderProposedMvmnts> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderProposedMvmnts> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderProposedMvmnts> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderProposedMvmnts> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderProposedMvmnts> updateList) {
		this.updateList = updateList;
	}
	
	
}
