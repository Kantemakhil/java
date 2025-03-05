package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignificantIncidentCommitBean extends BaseModel implements Serializable {


	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SignificantIncident> insertList;

	@JsonProperty("deleteList")
	private List<SignificantIncident> deleteList;

	@JsonProperty("updateList")
	private List<SignificantIncident> updateList;

	@JsonProperty("insertOffenderInvList")
	private List<SignificantIncident> insertOffenderInvList;

	@JsonProperty("updateOffenderInvList")
	private List<SignificantIncident> updateOffenderInvList;

	@JsonProperty("insertRepairList")
	private List<SignificantIncident> insertRepairList;

	@JsonProperty("updateRepairList")
	private List<SignificantIncident> updateRepairList;

	@JsonProperty("insertStaffPartiesList")
	private List<SignificantIncident> insertStaffPartiesList;

	/**
	 * Creates new AgencyIncidentChargesCommitBean class Object
	 */
	public SignificantIncidentCommitBean() {
		// AgencyIncidentChargesCommitBean
	}

	public void setInsertList(final List<SignificantIncident> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SignificantIncident> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SignificantIncident> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SignificantIncident> getInsertList() {
		return insertList;
	}

	public List<SignificantIncident> getUpdateList() {
		return updateList;
	}

	public List<SignificantIncident> getDeleteList() {
		return deleteList;
	}

	/**
	 * @return the insertOffenderInvList
	 */
	public List<SignificantIncident> getInsertOffenderInvList() {
		return insertOffenderInvList;
	}

	/**
	 * @param insertOffenderInvList
	 *            the insertOffenderInvList to set
	 */
	public void setInsertOffenderInvList(List<SignificantIncident> insertOffenderInvList) {
		this.insertOffenderInvList = insertOffenderInvList;
	}

	/**
	 * @return the updateOffenderInvList
	 */
	public List<SignificantIncident> getUpdateOffenderInvList() {
		return updateOffenderInvList;
	}

	/**
	 * @param updateOffenderInvList
	 *            the updateOffenderInvList to set
	 */
	public void setUpdateOffenderInvList(List<SignificantIncident> updateOffenderInvList) {
		this.updateOffenderInvList = updateOffenderInvList;
	}

	/**
	 * @return the insertRepairList
	 */
	public List<SignificantIncident> getInsertRepairList() {
		return insertRepairList;
	}

	/**
	 * @param insertRepairList
	 *            the insertRepairList to set
	 */
	public void setInsertRepairList(List<SignificantIncident> insertRepairList) {
		this.insertRepairList = insertRepairList;
	}

	/**
	 * @return the updateRepairList
	 */
	public List<SignificantIncident> getUpdateRepairList() {
		return updateRepairList;
	}

	/**
	 * @param updateRepairList
	 *            the updateRepairList to set
	 */
	public void setUpdateRepairList(List<SignificantIncident> updateRepairList) {
		this.updateRepairList = updateRepairList;
	}

	/**
	 * @return the insertStaffPartiesList
	 */
	public List<SignificantIncident> getInsertStaffPartiesList() {
		return insertStaffPartiesList;
	}

	/**
	 * @param insertStaffPartiesList
	 *            the insertStaffPartiesList to set
	 */
	public void setInsertStaffPartiesList(List<SignificantIncident> insertStaffPartiesList) {
		this.insertStaffPartiesList = insertStaffPartiesList;
	}



}
