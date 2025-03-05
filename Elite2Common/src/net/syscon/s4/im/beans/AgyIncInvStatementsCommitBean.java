package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgyIncInvStatementsCommitBean extends BaseModel implements Serializable {
	@JsonProperty("insertList")
	private List<AgyIncInvStatements> insertList;
	@JsonProperty("deleteList")
	private List<AgyIncInvStatements> deleteList;
	@JsonProperty("updateList")
	private List<AgyIncInvStatements> updateList;

	/**
	 * Creates new Persons class Object
	 */
	public AgyIncInvStatementsCommitBean() {

		// AgyIncInvStatementsCommitBean
	}

	public void setInsertList(final List<AgyIncInvStatements> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgyIncInvStatements> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgyIncInvStatements> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgyIncInvStatements> getInsertList() {
		return insertList;
	}

	public List<AgyIncInvStatements> getUpdateList() {
		return updateList;
	}

	public List<AgyIncInvStatements> getDeleteList() {
		return deleteList;
	}

}
