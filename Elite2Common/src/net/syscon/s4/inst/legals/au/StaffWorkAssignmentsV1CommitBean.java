package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StaffWorkAssignmentsV1CommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<StaffWorkAssignmentsV1> insertList;
	@JsonProperty("deleteList ")
	private List<StaffWorkAssignmentsV1> deleteList;
	@JsonProperty("updateList")
	private List<StaffWorkAssignmentsV1> updateList;

	/**
	 * @return the insertList
	 */
	public List<StaffWorkAssignmentsV1> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<StaffWorkAssignmentsV1> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<StaffWorkAssignmentsV1> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<StaffWorkAssignmentsV1> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<StaffWorkAssignmentsV1> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<StaffWorkAssignmentsV1> updateList) {
		this.updateList = updateList;
	}

}
