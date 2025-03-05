package net.syscon.s4.sa.admin.beans;

import java.util.List;

public class VMergeTransactionProcessesCommitBean {
	
	private List<VMergeTransactionProcesses> insertList;
	private List<VMergeTransactionProcesses> deleteList;
	private List<VMergeTransactionProcesses> updateList;
	public List<VMergeTransactionProcesses> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VMergeTransactionProcesses> insertList) {
		this.insertList = insertList;
	}
	public List<VMergeTransactionProcesses> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VMergeTransactionProcesses> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VMergeTransactionProcesses> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VMergeTransactionProcesses> updateList) {
		this.updateList = updateList;
	}

	
}
