package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimContactLogsCommitBean extends BaseModel implements Serializable {
	@JsonProperty("insertList")
	private List<VictimContactLogs> insertList;
	@JsonProperty("updateList")
	private List<VictimContactLogs> updateList;
	@JsonProperty("deleteList")
	private List<VictimContactLogs> deleteList;

	// generate setters and getters
	public List<VictimContactLogs> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VictimContactLogs> insertList) {
		this.insertList = insertList;
	}

	public List<VictimContactLogs> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VictimContactLogs> updateList) {
		this.updateList = updateList;
	}

	public List<VictimContactLogs> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<VictimContactLogs> deleteList) {
		this.deleteList = deleteList;
	}

	@Override
	public String toString() {
		return "VictimContactLogsCommitBean [insertList=" + insertList + ", updateList=" + updateList + ", deleteList="
				+ deleteList + "]";
	}

}