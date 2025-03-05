package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderRequestsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderRequests> insertList;
	@JsonProperty("deleteList ")
	private List<OffenderRequests> deleteList;
	@JsonProperty("updateList")
	private List<OffenderRequests> updateList;

	public void setInsertList(List<OffenderRequests> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderRequests> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderRequests> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderRequests> getInsertList() {
		return insertList;
	}

	public List<OffenderRequests> getUpdateList() {
		return updateList;
	}

	public List<OffenderRequests> getDeleteList() {
		return deleteList;
	}

}
