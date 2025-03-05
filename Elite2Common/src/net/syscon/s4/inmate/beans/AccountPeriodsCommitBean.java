package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountPeriodsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AccountPeriods> insertList;

	@JsonProperty("deleteList")
	private List<AccountPeriods> deleteList;

	@JsonProperty("updateList")
	private List<AccountPeriods> updateList;

	/**
	 * Creates new AccountPeriodsCommitBean class Object
	 */
	public AccountPeriodsCommitBean() {
		// TODO: AccountPeriodsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<AccountPeriods> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<AccountPeriods> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<AccountPeriods> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<AccountPeriods> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<AccountPeriods> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<AccountPeriods> updateList) {
		this.updateList = updateList;
	}

}
