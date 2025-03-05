package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimContactPreferencesCommitBean extends BaseModel implements Serializable {
	@JsonProperty("insertList")
	private List<VictimContactPreferences> insertList;
	@JsonProperty("updateList")
	private List<VictimContactPreferences> updateList;

	// generate setters and getters
	public List<VictimContactPreferences> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VictimContactPreferences> insertList) {
		this.insertList = insertList;
	}

	public List<VictimContactPreferences> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VictimContactPreferences> updateList) {
		this.updateList = updateList;
	}


	@Override
	public String toString() {
		return "VictimContactPreferencesCommitBean [insertList=" + insertList + ", updateList=" + updateList+ "]";
	}

}