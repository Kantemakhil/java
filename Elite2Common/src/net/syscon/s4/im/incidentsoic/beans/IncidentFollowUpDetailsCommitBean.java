package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class IncidentFollowUpDetailsCommitBean extends BaseModel implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<IncidentFollowUpDetails> insertList;

	@JsonProperty("deleteList")
	private List<IncidentFollowUpDetails> deleteList;

	@JsonProperty("updateList")
	private List<IncidentFollowUpDetails> updateList;

	public List<IncidentFollowUpDetails> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<IncidentFollowUpDetails> insertList) {
		this.insertList = insertList;
	}

	public List<IncidentFollowUpDetails> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<IncidentFollowUpDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<IncidentFollowUpDetails> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<IncidentFollowUpDetails> updateList) {
		this.updateList = updateList;
	}
	
}
