package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCondTransferCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderCondTransfer> insertList;
	@JsonProperty("deleteList")
	private List<OffenderCondTransfer> deleteList;
	@JsonProperty("updateList")
	private List<OffenderCondTransfer> updateList;
	public List<OffenderCondTransfer> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderCondTransfer> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderCondTransfer> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderCondTransfer> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderCondTransfer> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderCondTransfer> updateList) {
		this.updateList = updateList;
	}
	
}
