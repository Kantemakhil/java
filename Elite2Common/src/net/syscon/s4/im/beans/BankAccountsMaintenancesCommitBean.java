package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccountsMaintenancesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<BankAccountsMaintenances> insertList;
	private List<BankAccountsMaintenances> deleteList;
	private List<BankAccountsMaintenances> updateList;

	public void setInsertList(final List<BankAccountsMaintenances> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<BankAccountsMaintenances> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<BankAccountsMaintenances> deleteList) {
		this.deleteList = deleteList;
	}

	public List<BankAccountsMaintenances> getInsertList() {
		return insertList;
	}

	public List<BankAccountsMaintenances> getUpdateList() {
		return updateList;
	}

	public List<BankAccountsMaintenances> getDeleteList() {
		return deleteList;
	}

}
