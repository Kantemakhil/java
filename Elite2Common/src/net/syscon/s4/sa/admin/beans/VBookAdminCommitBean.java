package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VBookAdminCommitBean extends BaseModel implements Serializable {

	
private static final long serialVersionUID = 1L;
	
	private List<VBookAdmin> insertList;
	private List<VBookAdmin> deleteList;
	private List<VBookAdmin> updateList;
	/**
	 * @return the insertList
	 */
	public List<VBookAdmin> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<VBookAdmin> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<VBookAdmin> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<VBookAdmin> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<VBookAdmin> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<VBookAdmin> updateList) {
		this.updateList = updateList;
	}
	
	
	
}
