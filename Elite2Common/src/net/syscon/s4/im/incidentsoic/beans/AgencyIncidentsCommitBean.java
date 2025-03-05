package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyIncidentsCommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyIncidents> insertList;

	@JsonProperty("deleteList")
	private List<AgencyIncidents> deleteList;

	@JsonProperty("updateList")
	private List<AgencyIncidents> updateList;

	
	/**
	 * Creates new AgencyIncidentChargesCommitBean class Object
	 */
	public AgencyIncidentsCommitBean() {
		// AgencyIncidentsCommitBean
	}
	/**
	 * @return the insertList
	 */
	public List<AgencyIncidents> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<AgencyIncidents> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<AgencyIncidents> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<AgencyIncidents> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<AgencyIncidents> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<AgencyIncidents> updateList) {
		this.updateList = updateList;
	}
}
