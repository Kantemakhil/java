package net.syscon.s4.cm.teamsworkflow.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class WorkCommitBean extends BaseModel{
	@JsonProperty("insertList")
	private List<Work> insertList;
	@JsonProperty("deleteList")
	private List<Work> deleteList;
	@JsonProperty("updateList")
	private List<Work> updateList;

	/**
	 * Creates new WorkCommitBean class Object
	 */
	public WorkCommitBean() {
		// WorkCommitBean
	}

	public void setInsertList(List<Work> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<Work> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<Work> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Work> getInsertList() {
		return insertList;
	}

	public List<Work> getUpdateList() {
		return updateList;
	}

	public List<Work> getDeleteList() {
		return deleteList;
	}

}
