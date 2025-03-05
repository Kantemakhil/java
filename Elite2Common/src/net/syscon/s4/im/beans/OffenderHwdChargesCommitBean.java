package net.syscon.s4.im.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.beans.OffenderHwdCharges;

public class OffenderHwdChargesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OffenderHwdCharges> insertList;
	private List<OffenderHwdCharges> deleteList;
	private List<OffenderHwdCharges> updateList;

	/**
	 * @return the insertList
	 */
	public List<OffenderHwdCharges> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<OffenderHwdCharges> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderHwdCharges> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<OffenderHwdCharges> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderHwdCharges> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<OffenderHwdCharges> updateList) {
		this.updateList = updateList;
	}

}
