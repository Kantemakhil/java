package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyIncidentPartiesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyIncidentParties> insertList;

	@JsonProperty("deleteList")
	private List<AgencyIncidentParties> deleteList;

	@JsonProperty("updateList")
	private List<AgencyIncidentParties> updateList;

	/**
	 * Creates new AgencyIncidentCharges class Object
	 */
	public AgencyIncidentPartiesCommitBean() {
		// AgencyIncidentPartiesCommitBean
	}

	public void setInsertList(final List<AgencyIncidentParties> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgencyIncidentParties> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgencyIncidentParties> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgencyIncidentParties> getInsertList() {
		return insertList;
	}

	public List<AgencyIncidentParties> getUpdateList() {
		return updateList;
	}

	public List<AgencyIncidentParties> getDeleteList() {
		return deleteList;
	}

}
