package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;

public class LockedModulesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<LockedModules> insertList;

	@JsonProperty("deleteList")
	private List<LockedModules> deleteList;

	@JsonProperty("updateList")
	private List<LockedModules> updateList;

	public List<LockedModules> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<LockedModules> insertList) {
		this.insertList = insertList;
	}

	public List<LockedModules> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<LockedModules> deleteList) {
		this.deleteList = deleteList;
	}

	public List<LockedModules> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<LockedModules> updateList) {
		this.updateList = updateList;
	}

}
