package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentStaffReportCommitBean extends BaseModel implements Serializable{
	
	public List<IncidentStaffReport> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<IncidentStaffReport> insertList) {
		this.insertList = insertList;
	}

	public List<IncidentStaffReport> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<IncidentStaffReport> deleteList) {
		this.deleteList = deleteList;
	}

	public List<IncidentStaffReport> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<IncidentStaffReport> updateList) {
		this.updateList = updateList;
	}

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<IncidentStaffReport> insertList;

	@JsonProperty("deleteList")
	private List<IncidentStaffReport> deleteList;

	@JsonProperty("updateList")
	private List<IncidentStaffReport> updateList;

}
