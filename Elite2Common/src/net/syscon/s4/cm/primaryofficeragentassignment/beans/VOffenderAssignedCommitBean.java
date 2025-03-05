package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 * @Class VOffenderAssignedCommitBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")

public class VOffenderAssignedCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VOffenderAssigned> insertList;

	@JsonProperty("deleteList")
	private List<VOffenderAssigned> deleteList;

	@JsonProperty("updateList")
	private List<VOffenderAssigned> updateList;

	/**
	 * Creates new OffenderAlertsCommitBean class Object
	 */
	public VOffenderAssignedCommitBean() {
		// OffenderAlertsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<VOffenderAssigned> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<VOffenderAssigned> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<VOffenderAssigned> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<VOffenderAssigned> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<VOffenderAssigned> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<VOffenderAssigned> updateList) {
		this.updateList = updateList;
	}

}
