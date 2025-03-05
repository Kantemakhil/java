package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderSentenceEventsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private List<VOffenderSentenceEvents> insertList;
	private List<VOffenderSentenceEvents> deleteList;
	private List<VOffenderSentenceEvents> updateList;
	public List<VOffenderSentenceEvents> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VOffenderSentenceEvents> insertList) {
		this.insertList = insertList;
	}
	public List<VOffenderSentenceEvents> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VOffenderSentenceEvents> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VOffenderSentenceEvents> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VOffenderSentenceEvents> updateList) {
		this.updateList = updateList;
	}

}
