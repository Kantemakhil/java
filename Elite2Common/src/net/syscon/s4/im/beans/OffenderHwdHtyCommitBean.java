package net.syscon.s4.im.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderHwdHty;

public class OffenderHwdHtyCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OffenderHwdHty> insertList;
	private List<OffenderHwdHty> deleteList;
	private List<OffenderHwdHty> updateList;

	/**
	 * @return the insertList
	 */
	public List<OffenderHwdHty> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<OffenderHwdHty> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderHwdHty> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<OffenderHwdHty> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderHwdHty> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<OffenderHwdHty> updateList) {
		this.updateList = updateList;
	}

}
