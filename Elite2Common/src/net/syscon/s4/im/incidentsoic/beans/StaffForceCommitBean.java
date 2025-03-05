package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffForceCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<StaffForce> insertList;

	@JsonProperty("deleteList")
	private List<StaffForce> deleteList;

	@JsonProperty("updateList")
	private List<StaffForce> updateList;
	
	public List<StaffForce> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<StaffForce> insertList) {
		this.insertList = insertList;
	}

	public List<StaffForce> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<StaffForce> deleteList) {
		this.deleteList = deleteList;
	}

	public List<StaffForce> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<StaffForce> updateList) {
		this.updateList = updateList;
	}

}
