package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionTypesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<TransactionTypes> insertList;

	@JsonProperty("deleteList")
	private List<TransactionTypes> deleteList;

	@JsonProperty("updateList")
	private List<TransactionTypes> updateList;

	/**
	 * Creates new TransactionTypesCommitBean class Object
	 */
	public TransactionTypesCommitBean() {
		// TransactionTypesCommitBean
	}

	public void setInsertList(final List<TransactionTypes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<TransactionTypes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<TransactionTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<TransactionTypes> getInsertList() {
		return insertList;
	}

	public List<TransactionTypes> getUpdateList() {
		return updateList;
	}

	public List<TransactionTypes> getDeleteList() {
		return deleteList;
	}

}
