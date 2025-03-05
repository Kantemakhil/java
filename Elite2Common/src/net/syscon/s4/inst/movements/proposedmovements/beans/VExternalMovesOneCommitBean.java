package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VExternalMovesOneCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private List<VExternalMovesOne> insertList;
	private List<VExternalMovesOne> deleteList;
	private List<VExternalMovesOne> updateList;

	public List<VExternalMovesOne> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VExternalMovesOne> insertList) {
		this.insertList = insertList;
	}

	public List<VExternalMovesOne> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<VExternalMovesOne> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VExternalMovesOne> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VExternalMovesOne> updateList) {
		this.updateList = updateList;
	} 
}
