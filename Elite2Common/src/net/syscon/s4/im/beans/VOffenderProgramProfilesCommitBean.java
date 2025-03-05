package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderProgramProfilesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VOffenderProgramProfiles> insertList;

	@JsonProperty("deleteList")
	private List<VOffenderProgramProfiles> deleteList;

	@JsonProperty("updateList")
	private List<VOffenderProgramProfiles> updateList;

	/**
	 * @return the insertList
	 */
	public List<VOffenderProgramProfiles> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<VOffenderProgramProfiles> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<VOffenderProgramProfiles> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<VOffenderProgramProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<VOffenderProgramProfiles> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<VOffenderProgramProfiles> updateList) {
		this.updateList = updateList;
	}

}
