package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderReleaseDetailsCommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderReleaseDetails> insertList;

	@JsonProperty("deleteList")
	private List<OffenderReleaseDetails> deleteList;

	@JsonProperty("updateList")
	private List<OffenderReleaseDetails> updateList;

	/**
	 * @return the insertList
	 */
	public List<OffenderReleaseDetails> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(final List<OffenderReleaseDetails> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderReleaseDetails> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(final List<OffenderReleaseDetails> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderReleaseDetails> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(final List<OffenderReleaseDetails> updateList) {
		this.updateList = updateList;
	}



}
