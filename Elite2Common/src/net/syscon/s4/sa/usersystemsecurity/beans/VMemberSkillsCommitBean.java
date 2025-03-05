package net.syscon.s4.sa.usersystemsecurity.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VMemberSkillsCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<VMemberSkills> insertList;
	@JsonProperty("deleteList")
	private List<VMemberSkills> deleteList;
	@JsonProperty("updateList")
	private List<VMemberSkills> updateList;
	public List<VMemberSkills> getInsertList() {
		return insertList;
	}
	/**
	 * Creates new VMemberSkillsCommitBean class Object
	 */
	public VMemberSkillsCommitBean() {
		// SentenceAdjustmentCommitBean
	}

	public void setInsertList(List<VMemberSkills> insertList) {
		this.insertList = insertList;
	}
	public List<VMemberSkills> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VMemberSkills> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VMemberSkills> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VMemberSkills> updateList) {
		this.updateList = updateList;
	}
	
}
