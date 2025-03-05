package net.syscon.s4.cf.maintenance.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class FeeAccountsCommitBean  extends BaseModel{
	private List<FeeAccounts> insertList;
	private List<FeeAccounts> deleteList;
	private List<FeeAccounts> updateList;

	public void setInsertList(List<FeeAccounts> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<FeeAccounts> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<FeeAccounts> deleteList) {
		this.deleteList = deleteList;
	}

	public List<FeeAccounts> getInsertList() {
		return insertList;
	}

	public List<FeeAccounts> getUpdateList() {
		return updateList;
	}

	public List<FeeAccounts> getDeleteList() {
		return deleteList;
	}

}
