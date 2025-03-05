package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadCurrentAccountsBaseCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<CaseloadCurrentAccountsBase> insertList;
	private List<CaseloadCurrentAccountsBase> deleteList;
	private List<CaseloadCurrentAccountsBase> updateList;

	public void setInsertList(final List<CaseloadCurrentAccountsBase> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CaseloadCurrentAccountsBase> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CaseloadCurrentAccountsBase> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CaseloadCurrentAccountsBase> getInsertList() {
		return insertList;
	}

	public List<CaseloadCurrentAccountsBase> getUpdateList() {
		return updateList;
	}

	public List<CaseloadCurrentAccountsBase> getDeleteList() {
		return deleteList;
	}

}
