package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VStgLocationPresenceCommitBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<VStgLocationPresence> insertList;
	@JsonProperty("deleteList")
	private List<VStgLocationPresence> deleteList;
	@JsonProperty("updateList")
	private List<VStgLocationPresence> updateList;

	public VStgLocationPresenceCommitBean() {
		// VStgLocationPresenceCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<VStgLocationPresence> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<VStgLocationPresence> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<VStgLocationPresence> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<VStgLocationPresence> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<VStgLocationPresence> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<VStgLocationPresence> updateList) {
		this.updateList = updateList;
	}

}
