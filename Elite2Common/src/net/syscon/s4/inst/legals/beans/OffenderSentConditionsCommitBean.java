package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentConditionsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<OffenderSentConditions> insertList;
	private List<OffenderSentConditions> deleteList;
	private List<OffenderSentConditions> updateList;
	private List<OffenderSentConditions> typedeleteList;
	
	public List<OffenderSentConditions> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderSentConditions> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderSentConditions> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderSentConditions> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderSentConditions> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderSentConditions> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<OffenderSentConditions> getTypedeleteList() {
		return typedeleteList;
	}
	public void setTypedeleteList(List<OffenderSentConditions> typedeleteList) {
		this.typedeleteList = typedeleteList;
	}
	
	

}
