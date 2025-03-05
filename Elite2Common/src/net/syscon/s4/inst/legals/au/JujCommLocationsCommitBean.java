package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class JujCommLocationsCommitBean extends BaseModel implements Serializable {

	public static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<JujCommLocations> insertList;
	@JsonProperty("deleteList ")
	private List<JujCommLocations> deleteList;
	@JsonProperty("updateList")
	private List<JujCommLocations> updateList;
	/**
	 * @return the insertList
	 */
	public List<JujCommLocations> getInsertList() {
		return insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<JujCommLocations> getDeleteList() {
		return deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<JujCommLocations> getUpdateList() {
		return updateList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<JujCommLocations> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<JujCommLocations> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<JujCommLocations> updateList) {
		this.updateList = updateList;
	}
}
