package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionPayeesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<TransactionPayees> insertList;

	@JsonProperty("deleteList")
	private List<TransactionPayees> deleteList;

	@JsonProperty("updateList")
	private List<TransactionPayees> updateList;

	/**
	 * Creates new TransactionPayeesCommitBean class Object
	 */
	public TransactionPayeesCommitBean() {
		// TransactionPayeesCommitBean
	}

	public void setInsertList(final List<TransactionPayees> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<TransactionPayees> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<TransactionPayees> deleteList) {
		this.deleteList = deleteList;
	}

	public List<TransactionPayees> getInsertList() {
		return insertList;
	}

	public List<TransactionPayees> getUpdateList() {
		return updateList;
	}

	public List<TransactionPayees> getDeleteList() {
		return deleteList;
	}

}
