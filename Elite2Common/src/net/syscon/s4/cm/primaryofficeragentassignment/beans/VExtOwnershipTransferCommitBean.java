package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VExtOwnershipTransferCommitBean extends BaseModel {
	@JsonProperty("insertList")
	private List<VExtOwnershipTransfer> insertList;
	@JsonProperty("deleteList")
	private List<VExtOwnershipTransfer> deleteList;
	@JsonProperty("updateList")
	private List<VExtOwnershipTransfer> updateList;

	/**
	 * Creates new VExtOwnershipTransferCommitBean class Object
	 */
	public VExtOwnershipTransferCommitBean() {
		// VExtOwnershipTransferCommitBean
	}

	public void setInsertList(List<VExtOwnershipTransfer> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<VExtOwnershipTransfer> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<VExtOwnershipTransfer> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VExtOwnershipTransfer> getInsertList() {
		return insertList;
	}

	public List<VExtOwnershipTransfer> getUpdateList() {
		return updateList;
	}

	public List<VExtOwnershipTransfer> getDeleteList() {
		return deleteList;
	}

}
