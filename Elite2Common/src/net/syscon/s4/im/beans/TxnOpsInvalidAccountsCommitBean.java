package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnOpsInvalidAccountsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<TxnOpsInvalidAccounts> insertList;

	@JsonProperty("deleteList")
	private List<TxnOpsInvalidAccounts> deleteList;

	@JsonProperty("updateList")
	private List<TxnOpsInvalidAccounts> updateList;

	/**
	 * Creates new TxnOpsInvalidAccountsCommitBean class Object
	 */
	public TxnOpsInvalidAccountsCommitBean() {
		// TxnOpsInvalidAccountsCommitBean
	}

	public void setInsertList(final List<TxnOpsInvalidAccounts> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<TxnOpsInvalidAccounts> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<TxnOpsInvalidAccounts> deleteList) {
		this.deleteList = deleteList;
	}

	public List<TxnOpsInvalidAccounts> getInsertList() {
		return insertList;
	}

	public List<TxnOpsInvalidAccounts> getUpdateList() {
		return updateList;
	}

	public List<TxnOpsInvalidAccounts> getDeleteList() {
		return deleteList;
	}

}
