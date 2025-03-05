package net.syscon.s4.cm.programsservices.maintenance;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.VProgramPhases;

public class VProgramPhasesCommitBean extends BaseModel implements Serializable{
	@JsonProperty("insertList")
	private List<VProgramPhases> insertList;
	@JsonProperty("deleteList")
	private List<VProgramPhases> deleteList;
	@JsonProperty("updateList")
	private List<VProgramPhases> updateList;
	/**
	 * @return the insertList
	 */
	public List<VProgramPhases> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<VProgramPhases> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<VProgramPhases> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<VProgramPhases> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<VProgramPhases> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<VProgramPhases> updateList) {
		this.updateList = updateList;
	}

}
