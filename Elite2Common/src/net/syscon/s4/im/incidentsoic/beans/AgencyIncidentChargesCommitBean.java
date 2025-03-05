package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyIncidentChargesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyIncidentCharges> insertList;

	@JsonProperty("deleteList")
	private List<AgencyIncidentCharges> deleteList;

	@JsonProperty("updateList")
	private List<AgencyIncidentCharges> updateList;

	@JsonProperty("insertOffenderInvList")
	private List<AgencyIncidentParties> insertOffenderInvList;

	@JsonProperty("updateOffenderInvList")
	private List<AgencyIncidentParties> updateOffenderInvList;

	@JsonProperty("insertRepairList")
	private List<AgencyIncidentRepairs> insertRepairList;

	@JsonProperty("updateRepairList")
	private List<AgencyIncidentRepairs> updateRepairList;

	@JsonProperty("insertStaffPartiesList")
	private List<AgencyIncidentParties> insertStaffPartiesList;

	/**
	 * Creates new AgencyIncidentChargesCommitBean class Object
	 */
	public AgencyIncidentChargesCommitBean() {
		// AgencyIncidentChargesCommitBean
	}

	public void setInsertList(final List<AgencyIncidentCharges> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgencyIncidentCharges> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgencyIncidentCharges> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgencyIncidentCharges> getInsertList() {
		return insertList;
	}

	public List<AgencyIncidentCharges> getUpdateList() {
		return updateList;
	}

	public List<AgencyIncidentCharges> getDeleteList() {
		return deleteList;
	}

	/**
	 * @return the insertOffenderInvList
	 */
	public List<AgencyIncidentParties> getInsertOffenderInvList() {
		return insertOffenderInvList;
	}

	/**
	 * @param insertOffenderInvList
	 *            the insertOffenderInvList to set
	 */
	public void setInsertOffenderInvList(List<AgencyIncidentParties> insertOffenderInvList) {
		this.insertOffenderInvList = insertOffenderInvList;
	}

	/**
	 * @return the updateOffenderInvList
	 */
	public List<AgencyIncidentParties> getUpdateOffenderInvList() {
		return updateOffenderInvList;
	}

	/**
	 * @param updateOffenderInvList
	 *            the updateOffenderInvList to set
	 */
	public void setUpdateOffenderInvList(List<AgencyIncidentParties> updateOffenderInvList) {
		this.updateOffenderInvList = updateOffenderInvList;
	}

	/**
	 * @return the insertRepairList
	 */
	public List<AgencyIncidentRepairs> getInsertRepairList() {
		return insertRepairList;
	}

	/**
	 * @param insertRepairList
	 *            the insertRepairList to set
	 */
	public void setInsertRepairList(List<AgencyIncidentRepairs> insertRepairList) {
		this.insertRepairList = insertRepairList;
	}

	/**
	 * @return the updateRepairList
	 */
	public List<AgencyIncidentRepairs> getUpdateRepairList() {
		return updateRepairList;
	}

	/**
	 * @param updateRepairList
	 *            the updateRepairList to set
	 */
	public void setUpdateRepairList(List<AgencyIncidentRepairs> updateRepairList) {
		this.updateRepairList = updateRepairList;
	}

	/**
	 * @return the insertStaffPartiesList
	 */
	public List<AgencyIncidentParties> getInsertStaffPartiesList() {
		return insertStaffPartiesList;
	}

	/**
	 * @param insertStaffPartiesList
	 *            the insertStaffPartiesList to set
	 */
	public void setInsertStaffPartiesList(List<AgencyIncidentParties> insertStaffPartiesList) {
		this.insertStaffPartiesList = insertStaffPartiesList;
	}

}
