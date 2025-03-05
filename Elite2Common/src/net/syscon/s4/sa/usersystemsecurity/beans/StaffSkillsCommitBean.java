package net.syscon.s4.sa.usersystemsecurity.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StaffSkillsCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<StaffSkills> insertList;
	@JsonProperty("deleteList")
	private List<StaffSkills> deleteList;
	@JsonProperty("updateList")
	private List<StaffSkills> updateList;
	/**
	 * Creates new StaffSkillsCommitBean class Object
	 */
	public StaffSkillsCommitBean() {
		// StaffSkillsCommitBean
	}

	public List<StaffSkills> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<StaffSkills> insertList) {
		this.insertList = insertList;
	}
	public List<StaffSkills> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<StaffSkills> deleteList) {
		this.deleteList = deleteList;
	}
	public List<StaffSkills> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<StaffSkills> updateList) {
		this.updateList = updateList;
	}
	
}
