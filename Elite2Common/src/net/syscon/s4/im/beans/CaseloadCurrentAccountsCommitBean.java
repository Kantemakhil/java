package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * 
 * class CaseloadCurrentAccountsCommitBean
 *
 */
public class CaseloadCurrentAccountsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<CaseloadCurrentAccounts> insertList;
	@JsonProperty("deleteList")
	private List<CaseloadCurrentAccounts> deleteList;
	@JsonProperty("updateList")
	private List<CaseloadCurrentAccounts> updateList;

	public CaseloadCurrentAccountsCommitBean() {
		// CaseloadCurrentAccountsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<CaseloadCurrentAccounts> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<CaseloadCurrentAccounts> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<CaseloadCurrentAccounts> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<CaseloadCurrentAccounts> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<CaseloadCurrentAccounts> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<CaseloadCurrentAccounts> updateList) {
		this.updateList = updateList;
	}

}
