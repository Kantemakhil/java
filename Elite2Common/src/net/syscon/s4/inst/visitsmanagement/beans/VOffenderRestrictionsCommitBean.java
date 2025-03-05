package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * 
 * class VOffenderRestrictionsCommitBean
 *
 */
public class VOffenderRestrictionsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VOffenderRestrictions> insertList;

	@JsonProperty("deleteList")
	private List<VOffenderRestrictions> deleteList;

	@JsonProperty("updateList")
	private List<VOffenderRestrictions> updateList;

	/**
	 * @return the insertList
	 */
	public List<VOffenderRestrictions> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */

	public void setInsertList(final List<VOffenderRestrictions> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<VOffenderRestrictions> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */

	public void setDeleteList(final List<VOffenderRestrictions> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<VOffenderRestrictions> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */

	public void setUpdateList(final List<VOffenderRestrictions> updateList) {
		this.updateList = updateList;
	}

}
