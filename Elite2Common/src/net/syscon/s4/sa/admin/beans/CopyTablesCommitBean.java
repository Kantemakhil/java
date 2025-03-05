package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class CopyTablesCommitBean extends BaseModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private List<CopyTables> insertList;
	private List<CopyTables> deleteList;
	private List<CopyTables> updateList;
	/**
	 * @return the insertList
	 */
	public List<CopyTables> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<CopyTables> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<CopyTables> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<CopyTables> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<CopyTables> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<CopyTables> updateList) {
		this.updateList = updateList;
	}
	
	
	
}
