package net.syscon.s4.cm.primaryofficeragentassignment.beans;


import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderBookings;

public class VOmTeamMembersCommitBean extends BaseModel implements Serializable {
	
	private List<VOmTeamMembers> insertList;
	private List<VOmTeamMembers> deleteList;
	private List<VOmTeamMembers> updateList;
	
	private List<OffenderBookings> offbkg1UpdatetList;
	private List<ExtOwnershipTransfer> extotUpdatetList;
	
	
	/**
	 * @return the insertList
	 */
	public List<VOmTeamMembers> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<VOmTeamMembers> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<VOmTeamMembers> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<VOmTeamMembers> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<VOmTeamMembers> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<VOmTeamMembers> updateList) {
		this.updateList = updateList;
	}
	/**
	 * @return the offbkg1UpdatetList
	 */
	public List<OffenderBookings> getOffbkg1UpdatetList() {
		return offbkg1UpdatetList;
	}
	/**
	 * @param offbkg1UpdatetList the offbkg1UpdatetList to set
	 */
	public void setOffbkg1UpdatetList(final List<OffenderBookings> offbkg1UpdatetList) {
		this.offbkg1UpdatetList = offbkg1UpdatetList;
	}
	/**
	 * @return the extotUpdatetList
	 */
	public List<ExtOwnershipTransfer> getExtotUpdatetList() {
		return extotUpdatetList;
	}
	/**
	 * @param extotUpdatetList the extotUpdatetList to set
	 */
	public void setExtotUpdatetList(final List<ExtOwnershipTransfer> extotUpdatetList) {
		this.extotUpdatetList = extotUpdatetList;
	}
	

}
