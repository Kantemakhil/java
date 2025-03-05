package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderInternalStatusesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderInternalStatuses> insertList;
	@JsonProperty("deleteList")
	private List<OffenderInternalStatuses> deleteList;
	@JsonProperty("updateList")
	private List<OffenderInternalStatuses> updateList;

	public void setInsertList(List<OffenderInternalStatuses> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderInternalStatuses> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderInternalStatuses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderInternalStatuses> getInsertList() {
		return insertList;
	}

	public List<OffenderInternalStatuses> getUpdateList() {
		return updateList;
	}

	public List<OffenderInternalStatuses> getDeleteList() {
		return deleteList;
	}

}
