package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderSubAccountsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderSubAccounts> insertList;

	@JsonProperty("deleteList")
	private List<OffenderSubAccounts> deleteList;

	@JsonProperty("updateList")
	private List<OffenderSubAccounts> updateList;

	/**
	 * Creates new OffenderSubAccountsubAccountsCommitBean class Object
	 */
	public OffenderSubAccountsCommitBean() {
		// TODO: OffenderSubAccountsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderSubAccounts> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<OffenderSubAccounts> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderSubAccounts> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<OffenderSubAccounts> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderSubAccounts> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<OffenderSubAccounts> updateList) {
		this.updateList = updateList;
	}

}
