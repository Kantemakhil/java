package net.syscon.s4.inst.workflow.managingteams.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TagWorkflowAdmQueryTeamTasksCommitBean extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<TagWorkflowAdmQueryTeamTasks> insertList;
	@JsonProperty("deleteList")
	private List<TagWorkflowAdmQueryTeamTasks> deleteList;
	@JsonProperty("updateList")
	private List<TagWorkflowAdmQueryTeamTasks> updateList;
	
	public List<TagWorkflowAdmQueryTeamTasks> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<TagWorkflowAdmQueryTeamTasks> insertList) {
		this.insertList = insertList;
	}
	public List<TagWorkflowAdmQueryTeamTasks> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<TagWorkflowAdmQueryTeamTasks> deleteList) {
		this.deleteList = deleteList;
	}
	public List<TagWorkflowAdmQueryTeamTasks> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<TagWorkflowAdmQueryTeamTasks> updateList) {
		this.updateList = updateList;
	}



}
