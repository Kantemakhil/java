package net.syscon.s4.cm.teamsworkflow.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TagWorkflowBrowseQueueCommitBean extends BaseModel {
	@JsonProperty("insertList")
	private List<TagWorkflowBrowseQueue> insertList;
	@JsonProperty("deleteList")
	private List<TagWorkflowBrowseQueue> deleteList;
	@JsonProperty("updateList")
	private List<TagWorkflowBrowseQueue> updateList;

	/**
	 * Creates new OffenderAlerts class Object
	 */
	public TagWorkflowBrowseQueueCommitBean() {
		// TagWorkflowBrowseQueueCommitBean
	}

	public void setInsertList(List<TagWorkflowBrowseQueue> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<TagWorkflowBrowseQueue> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<TagWorkflowBrowseQueue> deleteList) {
		this.deleteList = deleteList;
	}

	public List<TagWorkflowBrowseQueue> getInsertList() {
		return insertList;
	}

	public List<TagWorkflowBrowseQueue> getUpdateList() {
		return updateList;
	}

	public List<TagWorkflowBrowseQueue> getDeleteList() {
		return deleteList;
	}

}
