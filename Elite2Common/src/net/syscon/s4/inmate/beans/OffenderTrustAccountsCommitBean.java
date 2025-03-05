package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.OffenderTrustAccounts;

public class OffenderTrustAccountsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderTrustAccounts> insertList;
	@JsonProperty("deleteList")
	private List<OffenderTrustAccounts> deleteList;
	@JsonProperty("updateList")
	private List<OffenderTrustAccounts> updateList;
	/**
	 * @return the insertList
	 */
	public List<OffenderTrustAccounts> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<OffenderTrustAccounts> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<OffenderTrustAccounts> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<OffenderTrustAccounts> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<OffenderTrustAccounts> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<OffenderTrustAccounts> updateList) {
		this.updateList = updateList;
	}
}
