package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TagSearchGetOffenderRecordsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<TagSearchGetOffenderRecords> insertList;

	@JsonProperty("deleteList")
	private List<TagSearchGetOffenderRecords> deleteList;

	@JsonProperty("updateList")
	private List<TagSearchGetOffenderRecords> updateList;
	

	public List<TagSearchGetOffenderRecords> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<TagSearchGetOffenderRecords> insertList) {
		this.insertList = insertList;
	}

	public List<TagSearchGetOffenderRecords> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<TagSearchGetOffenderRecords> deleteList) {
		this.deleteList = deleteList;
	}

	public List<TagSearchGetOffenderRecords> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<TagSearchGetOffenderRecords> updateList) {
		this.updateList = updateList;
	}

}
