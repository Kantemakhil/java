package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderBookingsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderBookings> insertList;

	@JsonProperty("deleteList")
	private List<OffenderBookings> deleteList;

	@JsonProperty("updateList")
	private List<OffenderBookings> updateList;

	
	
	@JsonProperty("offdetUpdateList")
	private List<TeamMembers> offdetUpdateList;

	public List<TeamMembers> getOffdetUpdateList() {
		return offdetUpdateList;
	}

	public void setOffdetUpdateList(List<TeamMembers> offdetUpdateList) {
		this.offdetUpdateList = offdetUpdateList;
	}
	
	
	/**
	 * Creates new OffenderBookingsCommitBean class Object
	 */
	public OffenderBookingsCommitBean() {
		// TODO: OffenderBookingsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderBookings> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<OffenderBookings> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderBookings> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<OffenderBookings> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderBookings> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<OffenderBookings> updateList) {
		this.updateList = updateList;
	}

}
