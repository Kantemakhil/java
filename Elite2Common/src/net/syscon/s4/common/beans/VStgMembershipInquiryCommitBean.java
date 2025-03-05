package net.syscon.s4.common.beans;

import java.util.List;

public class VStgMembershipInquiryCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VStgMembershipInquiry> insertList;
	private List<VStgMembershipInquiry> deleteList;
	private List<VStgMembershipInquiry> updateList;

	public VStgMembershipInquiryCommitBean() {
		// VStgMembershipInquiryCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<VStgMembershipInquiry> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<VStgMembershipInquiry> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<VStgMembershipInquiry> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<VStgMembershipInquiry> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<VStgMembershipInquiry> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<VStgMembershipInquiry> updateList) {
		this.updateList = updateList;
	}

}
