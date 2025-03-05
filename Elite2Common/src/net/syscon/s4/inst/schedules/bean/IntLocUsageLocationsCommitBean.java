package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class IntLocUsageLocationsCommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<IntLocUsageLocations> insertList;

	@JsonProperty("deleteList")
	private List<IntLocUsageLocations> deleteList;

	@JsonProperty("updateList")
	private List<IntLocUsageLocations> updateList;

	/**
	 * @return the insertList
	 */
	public List<IntLocUsageLocations> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(final List<IntLocUsageLocations> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<IntLocUsageLocations> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(final List<IntLocUsageLocations> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<IntLocUsageLocations> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(final List<IntLocUsageLocations> updateList) {
		this.updateList = updateList;
	}



}
