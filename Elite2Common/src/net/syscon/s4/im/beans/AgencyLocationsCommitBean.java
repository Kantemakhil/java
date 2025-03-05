package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class AgencyLocationsCommitBean
 *
 */
public class AgencyLocationsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyLocations> insertList;

	@JsonProperty("deleteList")
	private List<AgencyLocations> deleteList;

	@JsonProperty("updateList")
	private List<AgencyLocations> updateList;

	/**
	 * @return the insertList
	 */
	public List<AgencyLocations> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(List<AgencyLocations> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<AgencyLocations> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(List<AgencyLocations> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<AgencyLocations> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(List<AgencyLocations> updateList) {
		this.updateList = updateList;
	}


}
