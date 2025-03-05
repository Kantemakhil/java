package net.syscon.s4.inst.careinplacement.beans;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class PlacementDurationsCommitBean extends BaseModel implements Serializable {

	/**	
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<PlacementDurations> insertList;
	
	@JsonProperty("deleteList")
	private List<PlacementDurations> deleteList;

	@JsonProperty("updateList")
	private List<PlacementDurations> updateList;



	/**
	 * @return the insertList
	 */
	public List<PlacementDurations> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<PlacementDurations> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<PlacementDurations> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<PlacementDurations> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<PlacementDurations> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<PlacementDurations> updateList) {
		this.updateList = updateList;
	}
	
	
	
}
