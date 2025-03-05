package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;


public class MergeTransactionProcessesCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MergeTransactionProcesses> insertList;
	private List<MergeTransactionProcesses> deleteList;
	private List<MergeTransactionProcesses> updateList;

	public List<MergeTransactionProcesses> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<MergeTransactionProcesses> insertList) {
		this.insertList = insertList;
	}

	public List<MergeTransactionProcesses> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<MergeTransactionProcesses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<MergeTransactionProcesses> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<MergeTransactionProcesses> updateList) {
		this.updateList = updateList;
	}

}
