package net.syscon.s4.cm.teamsworkflow.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TeamMembersCommitBean extends BaseModel{
	@JsonProperty("insertList")
	private List<TeamMembers> insertList;
	@JsonProperty("deleteList")
	private List<TeamMembers> deleteList;
	@JsonProperty("updateList")
	private List<TeamMembers> updateList;

	/**
	 * Creates new TeamMembersCommitBean class Object
	 */
	public TeamMembersCommitBean() {
		// TeamMembersCommitBean
	}

	public void setInsertList(List<TeamMembers> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<TeamMembers> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<TeamMembers> deleteList) {
		this.deleteList = deleteList;
	}

	public List<TeamMembers> getInsertList() {
		return insertList;
	}

	public List<TeamMembers> getUpdateList() {
		return updateList;
	}

	public List<TeamMembers> getDeleteList() {
		return deleteList;
	}

}
