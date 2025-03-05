package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderRestrictionsCommitBean  extends BaseModel implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderRestrictions> insertList;

	@JsonProperty("deleteList")
	private List<OffenderRestrictions> deleteList;

	@JsonProperty("updateList")
	private List<OffenderRestrictions> updateList;

	/**
	 * @return the insertList
	 */
	public List<OffenderRestrictions> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<OffenderRestrictions> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderRestrictions> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<OffenderRestrictions> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderRestrictions> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<OffenderRestrictions> updateList) {
		this.updateList = updateList;
	}

}
