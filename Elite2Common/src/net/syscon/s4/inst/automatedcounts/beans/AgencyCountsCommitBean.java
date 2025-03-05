package net.syscon.s4.inst.automatedcounts.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyCountsCommitBean  extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyCounts> insertList;
	@JsonProperty("deleteList")
	private List<AgencyCounts> deleteList;
	@JsonProperty("updateList")
	private List<AgencyCounts> updateList;
	/**
	 * @return the insertList
	 */
	public List<AgencyCounts> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<AgencyCounts> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<AgencyCounts> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<AgencyCounts> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<AgencyCounts> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<AgencyCounts> updateList) {
		this.updateList = updateList;
	}

	
}
