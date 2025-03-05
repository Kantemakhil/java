package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadTransactionTypesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CaseloadTransactionTypes> insertList;

	@JsonProperty("deleteList")
	private List<CaseloadTransactionTypes> deleteList;

	@JsonProperty("updateList")
	private List<CaseloadTransactionTypes> updateList;

	/**
	 * Creates new CaseloadTransactionTypesCommitBean class Object
	 */
	public CaseloadTransactionTypesCommitBean() {
		// TODO: CaseloadTransactionTypesCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<CaseloadTransactionTypes> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<CaseloadTransactionTypes> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<CaseloadTransactionTypes> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<CaseloadTransactionTypes> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<CaseloadTransactionTypes> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<CaseloadTransactionTypes> updateList) {
		this.updateList = updateList;
	}

}
