package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffEquipmentCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<StaffEquipment> insertList;

	@JsonProperty("deleteList")
	private List<StaffEquipment> deleteList;

	@JsonProperty("updateList")
	private List<StaffEquipment> updateList;
	
	public List<StaffEquipment> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<StaffEquipment> insertList) {
		this.insertList = insertList;
	}

	public List<StaffEquipment> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<StaffEquipment> deleteList) {
		this.deleteList = deleteList;
	}

	public List<StaffEquipment> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<StaffEquipment> updateList) {
		this.updateList = updateList;
	}

}
