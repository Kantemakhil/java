package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class InterestedPartiesCommitBean extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<InterestedParties> insertList;

	@JsonProperty("updateList")
	private List<InterestedParties> updateList;
	
	@JsonProperty("deleteList")
	private List<InterestedParties> deleteList;

	public List<InterestedParties> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<InterestedParties> insertList) {
		this.insertList = insertList;
	}

	public List<InterestedParties> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<InterestedParties> updateList) {
		this.updateList = updateList;
	}

	public List<InterestedParties> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<InterestedParties> deleteList) {
		this.deleteList = deleteList;
	}
}
