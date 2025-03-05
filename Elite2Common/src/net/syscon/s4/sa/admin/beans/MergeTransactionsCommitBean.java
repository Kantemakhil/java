package net.syscon.s4.sa.admin.beans;


import java.util.List;

import net.syscon.s4.common.beans.BaseModel;


public class MergeTransactionsCommitBean  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MergeTransactions> insertList;
	private List<MergeTransactions> deleteList;
	private List<MergeTransactions> updateList;

	public void setInsertList(List<MergeTransactions> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<MergeTransactions> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<MergeTransactions> deleteList){
		this.deleteList = deleteList;
	}

	public List<MergeTransactions> getInsertList(){
		return insertList;
	}

	public List<MergeTransactions> getUpdateList(){
		return updateList;
	}

	public List<MergeTransactions> getDeleteList(){
		return deleteList;
	}


}
