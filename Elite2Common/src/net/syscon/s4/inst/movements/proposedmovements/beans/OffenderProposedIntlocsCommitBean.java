package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderProposedIntlocsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<OffenderProposedIntlocs> insertList;
	private List<OffenderProposedIntlocs> deleteList;
	private List<OffenderProposedIntlocs> updateList;

	public List<OffenderProposedIntlocs> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderProposedIntlocs> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderProposedIntlocs> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderProposedIntlocs> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderProposedIntlocs> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderProposedIntlocs> updateList) {
		this.updateList = updateList;
	} 

}
