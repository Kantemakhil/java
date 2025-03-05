package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimRecordsCommitBean extends BaseModel implements Serializable {
	@JsonProperty("insertList")
	private List<VictimRecords> insertList;
	@JsonProperty("updateList")
	private List<VictimRecords> updateList;
	

	// generate setters and getters
	public List<VictimRecords> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VictimRecords> insertList) {
		this.insertList = insertList;
	}

	public List<VictimRecords> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VictimRecords> updateList) {
		this.updateList = updateList;
	}


	@Override
	public String toString() {
		return "VictimRecordsCommitBean [insertList=" + insertList + ", updateList=" + updateList + "]";
	}

}