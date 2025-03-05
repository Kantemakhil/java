package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderWeaponsCommitBean extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<OffenderWeapons> insertList;

	@JsonProperty("deleteList")
	private List<OffenderWeapons> deleteList;

	@JsonProperty("updateList")
	private List<OffenderWeapons> updateList;
	
	public List<OffenderWeapons> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderWeapons> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderWeapons> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderWeapons> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderWeapons> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderWeapons> updateList) {
		this.updateList = updateList;
	}

	
}
