package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;

public class AssignmentTransfersCommitBean  extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@JsonProperty("insertList")
	private List<AssignmentTransfers> insertList;
	@JsonProperty("deleteList ")
	private List<AssignmentTransfers> deleteList;
	@JsonProperty("updateList")
	private List<AssignmentTransfers> updateList;
	/**
	 * @return the insertList
	 */
	List<AssignmentTransfers> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	void setInsertList(final List<AssignmentTransfers> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	List<AssignmentTransfers> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	void setDeleteList(final List<AssignmentTransfers> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	List<AssignmentTransfers> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	void setUpdateList(final List<AssignmentTransfers> updateList) {
		this.updateList = updateList;
	}
}
