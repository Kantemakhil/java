/**
 * 
 */
package net.syscon.s4.inst.movementexternal.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * @author Arkinsoft
 *
 */
public class VTransferWaitingLists2CommitBean  extends BaseModel {
	private List<VTransferWaitingLists2> insertList;
	private List<VTransferWaitingLists2> deleteList;
	private List<VTransferWaitingLists2> updateList;
	/**
	 * @return the insertList
	 */
	public List<VTransferWaitingLists2> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<VTransferWaitingLists2> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<VTransferWaitingLists2> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<VTransferWaitingLists2> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<VTransferWaitingLists2> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<VTransferWaitingLists2> updateList) {
		this.updateList = updateList;
	}

}
