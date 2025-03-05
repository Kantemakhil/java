package net.syscon.s4.sa.audit;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.WorkFlowFolders;

public class JournalTableViewCommitBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<JournalTableView> insertList;
	private List<JournalTableView> deleteList;
	private List<JournalTableView> updateList;

	public List<JournalTableView> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<JournalTableView> insertList) {
		this.insertList = insertList;
	}

	public List<JournalTableView> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<JournalTableView> deleteList) {
		this.deleteList = deleteList;
	}

	public List<JournalTableView> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<JournalTableView> updateList) {
		this.updateList = updateList;
	}

}
