package net.syscon.s4.im.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderHwd;

public class OffenderHwdCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OffenderHwd> insertList;
	private List<OffenderHwd> deleteList;
	private List<OffenderHwd> updateList;

	/**
	 * @return the insertList
	 */
	public List<OffenderHwd> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<OffenderHwd> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderHwd> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<OffenderHwd> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderHwd> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<OffenderHwd> updateList) {
		this.updateList = updateList;
	}

}
