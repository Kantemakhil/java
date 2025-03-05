package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;


public class VHousingMovesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<VHousingMoves> insertList;
	private List<VHousingMoves> deleteList;
	private List<VHousingMoves> updateList;

	public void setInsertList(List<VHousingMoves> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<VHousingMoves> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<VHousingMoves> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VHousingMoves> getInsertList() {
		return insertList;
	}

	public List<VHousingMoves> getUpdateList() {
		return updateList;
	}

	public List<VHousingMoves> getDeleteList() {
		return deleteList;
	}

}
