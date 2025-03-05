package net.syscon.s4.legalorders;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderFileTransactionsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderFileTransactions> insertList;
	private List<OffenderFileTransactions> deleteList;
	private List<OffenderFileTransactions> updateList;

	public List<OffenderFileTransactions> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderFileTransactions> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderFileTransactions> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderFileTransactions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderFileTransactions> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderFileTransactions> updateList) {
		this.updateList = updateList;
	}

}
