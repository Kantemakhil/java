package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionOperationCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<TransactionOperation> insertList;

	@JsonProperty("deleteList")
	private List<TransactionOperation> deleteList;

	@JsonProperty("updateList")
	private List<TransactionOperation> updateList;

	/**
	 * Creates new TransactionOperationCommitBean class Object
	 */
	public TransactionOperationCommitBean() {
		// TransactionOperationCommitBean
	}

	public void setInsertList(final List<TransactionOperation> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<TransactionOperation> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<TransactionOperation> deleteList) {
		this.deleteList = deleteList;
	}

	public List<TransactionOperation> getInsertList() {
		return insertList;
	}

	public List<TransactionOperation> getUpdateList() {
		return updateList;
	}

	public List<TransactionOperation> getDeleteList() {
		return deleteList;
	}

}
