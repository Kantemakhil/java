package net.syscon.s4.cm.programsservices.maintenance;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VProgramModulesCommitBean extends BaseModel implements Serializable{

	@JsonProperty("insertList")
	private List<VProgramModules> insertList;
	@JsonProperty("deleteList")
	private List<VProgramModules> deleteList;
	@JsonProperty("updateList")
	private List<VProgramModules> updateList;

	public List<VProgramModules> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VProgramModules> insertList) {
		this.insertList = insertList;
	}

	public List<VProgramModules> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<VProgramModules> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VProgramModules> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VProgramModules> updateList) {
		this.updateList = updateList;
	}

}
