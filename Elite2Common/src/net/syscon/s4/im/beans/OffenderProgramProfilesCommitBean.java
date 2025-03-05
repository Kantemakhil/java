package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderProgramProfilesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderProgramProfiles> insertList;
	@JsonProperty("deleteList")
	private List<OffenderProgramProfiles> deleteList;
	@JsonProperty("updateList")
	private List<OffenderProgramProfiles> updateList;
	@JsonProperty("updateWaitList")
	private List<OffenderProgramProfiles> updateWaitList;

	public List<OffenderProgramProfiles> getUpdateWaitList() {
		return updateWaitList;
	}

	public void setUpdateWaitList(List<OffenderProgramProfiles> updateWaitList) {
		this.updateWaitList = updateWaitList;
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderProgramProfiles> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<OffenderProgramProfiles> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderProgramProfiles> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<OffenderProgramProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderProgramProfiles> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<OffenderProgramProfiles> updateList) {
		this.updateList = updateList;
	}

}
