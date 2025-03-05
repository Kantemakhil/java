package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyIncidentRepairsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyIncidentRepairs> insertList;

	@JsonProperty("deleteList")
	private List<AgencyIncidentRepairs> deleteList;

	@JsonProperty("updateList")
	private List<AgencyIncidentRepairs> updateList;

	/**
	 * Creates new AgencyIncidentRepairsCommitBean class Object
	 */
	public AgencyIncidentRepairsCommitBean() {
		// AgencyIncidentRepairsCommitBean
	}

	public void setInsertList(final List<AgencyIncidentRepairs> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgencyIncidentRepairs> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgencyIncidentRepairs> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgencyIncidentRepairs> getInsertList() {
		return insertList;
	}

	public List<AgencyIncidentRepairs> getUpdateList() {
		return updateList;
	}

	public List<AgencyIncidentRepairs> getDeleteList() {
		return deleteList;
	}

}
