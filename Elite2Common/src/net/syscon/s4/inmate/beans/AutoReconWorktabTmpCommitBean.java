package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AutoReconWorktabTmpCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<AutoReconWorktabTmp> insertList;
	@JsonProperty("deleteList")
	private List<AutoReconWorktabTmp> deleteList;
	@JsonProperty("updateList")
	private List<AutoReconWorktabTmp> updateList;

	/**
	 * @return the insertList
	 */
	public List<AutoReconWorktabTmp> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<AutoReconWorktabTmp> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<AutoReconWorktabTmp> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<AutoReconWorktabTmp> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<AutoReconWorktabTmp> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<AutoReconWorktabTmp> updateList) {
		this.updateList = updateList;
	}
}
