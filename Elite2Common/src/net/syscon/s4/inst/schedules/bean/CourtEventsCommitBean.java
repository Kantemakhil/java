package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class CourtEventsCommitBean
 *
 */
public class CourtEventsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CourtEvents> insertList;

	@JsonProperty("deleteList")
	private List<CourtEvents> deleteList;

	@JsonProperty("updateList")
	private List<CourtEvents> updateList;
	
	@JsonProperty("insertAndUpdateList")
	private List<CourtEvents> insertAndUpdateList;

	public List<CourtEvents> getInsertAndUpdateList() {
		return insertAndUpdateList;
	}

	public void setInsertAndUpdateList(List<CourtEvents> insertAndUpdateList) {
		this.insertAndUpdateList = insertAndUpdateList;
	}

	/**
	 * @return the insertList
	 */
	public List<CourtEvents> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(final List<CourtEvents> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<CourtEvents> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(final List<CourtEvents> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<CourtEvents> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(final List<CourtEvents> updateList) {
		this.updateList = updateList;
	}

	@Override
	public String toString() {
		return "CourtEventsCommitBean [insertList=" + insertList + ", deleteList=" + deleteList + ", updateList="
				+ updateList + "]";
	}



}
