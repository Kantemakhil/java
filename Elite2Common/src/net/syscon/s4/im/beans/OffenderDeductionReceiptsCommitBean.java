package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderDeductionReceiptsCommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderDeductionReceipts> insertList;
	@JsonProperty("deleteList")
	private List<OffenderDeductionReceipts> deleteList;
	@JsonProperty("updateList")
	private List<OffenderDeductionReceipts> updateList;
	@JsonProperty("offenderGrievanceTxnsList")
	private List<OffenderGrievanceTxns> offenderGrievanceTxnsList;

	public OffenderDeductionReceiptsCommitBean() {
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderDeductionReceipts> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<OffenderDeductionReceipts> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderDeductionReceipts> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<OffenderDeductionReceipts> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderDeductionReceipts> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<OffenderDeductionReceipts> updateList) {
		this.updateList = updateList;
	}

	/**
	 * @return the offenderGrievanceTxnsList
	 */
	public List<OffenderGrievanceTxns> getOffenderGrievanceTxnsList() {
		return offenderGrievanceTxnsList;
	}

	/**
	 * @param offenderGrievanceTxnsList the offenderGrievanceTxnsList to set
	 */
	public void setOffenderGrievanceTxnsList(List<OffenderGrievanceTxns> offenderGrievanceTxnsList) {
		this.offenderGrievanceTxnsList = offenderGrievanceTxnsList;
	}
	
	

}
