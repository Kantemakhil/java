package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class IwpBookmarksCommitBean extends BaseModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private List<IwpBookmarks> insertList;
	private List<IwpBookmarks> deleteList;
	private List<IwpBookmarks> updateList;
	/**
	 * @return the insertList
	 */
	public List<IwpBookmarks> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<IwpBookmarks> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<IwpBookmarks> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<IwpBookmarks> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<IwpBookmarks> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<IwpBookmarks> updateList) {
		this.updateList = updateList;
	}
	
	
	
}
