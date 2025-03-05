package net.syscon.s4.inst.visitsmanagement.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VisitCycleLimitsCommitBean extends BaseModel{

	 private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VisitCycleLimits> insertList;
	@JsonProperty("deleteList ")
	private List<VisitCycleLimits> deleteList;
	@JsonProperty("updateList")
	private List<VisitCycleLimits> updateList;

	public void setInsertList(final List<VisitCycleLimits> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VisitCycleLimits> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VisitCycleLimits> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VisitCycleLimits> getInsertList() {
		return insertList;
	}

	public List<VisitCycleLimits> getUpdateList() {
		return updateList;
	}

	public List<VisitCycleLimits> getDeleteList() {
		return deleteList;
	}

}
