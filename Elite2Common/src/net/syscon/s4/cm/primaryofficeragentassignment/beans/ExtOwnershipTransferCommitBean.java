package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ExtOwnershipTransferCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<ExtOwnershipTransfer> insertList;

	@JsonProperty("deleteList")
	private List<ExtOwnershipTransfer> deleteList;

	@JsonProperty("updateList")
	private List<ExtOwnershipTransfer> updateList;

	/**
	 * Creates new OffenderAlertsCommitBean class Object
	 */
	public ExtOwnershipTransferCommitBean() {
		// OffenderAlertsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<ExtOwnershipTransfer> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<ExtOwnershipTransfer> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<ExtOwnershipTransfer> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<ExtOwnershipTransfer> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<ExtOwnershipTransfer> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<ExtOwnershipTransfer> updateList) {
		this.updateList = updateList;
	}

}
