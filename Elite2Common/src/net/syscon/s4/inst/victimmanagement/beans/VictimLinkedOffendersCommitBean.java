package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimLinkedOffendersCommitBean extends BaseModel implements Serializable {
	@JsonProperty("insertList")
	private List<VictimLinkedOffenders> insertList;
	@JsonProperty("updateList")
	private List<VictimLinkedOffenders> updateList;
	@JsonProperty("deleteList")
	private List<VictimLinkedOffenders> deleteList;

	// generate setters and getters
	public List<VictimLinkedOffenders> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VictimLinkedOffenders> insertList) {
		this.insertList = insertList;
	}

	public List<VictimLinkedOffenders> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VictimLinkedOffenders> updateList) {
		this.updateList = updateList;
	}

	public List<VictimLinkedOffenders> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<VictimLinkedOffenders> deleteList) {
		this.deleteList = deleteList;
	}

	@Override
	public String toString() {
		return "VictimLinkedOffendersCommitBean [insertList=" + insertList + ", updateList=" + updateList
				+ ", deleteList=" + deleteList + "]";
	}

}