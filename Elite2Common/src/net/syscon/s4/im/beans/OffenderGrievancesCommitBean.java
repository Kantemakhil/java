package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderGrievancesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderGrievances> insertList;
	@JsonProperty("deleteList")
	private List<OffenderGrievances> deleteList;
	@JsonProperty("updateList")
	private List<OffenderGrievances> updateList;
	@JsonProperty("offenderGrievanceTxnsList")
	private List<OffenderGrievanceTxns> offenderGrievanceTxnsList;

	public void setInsertList(List<OffenderGrievances> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderGrievances> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderGrievances> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the offenderGrievanceTxnsList
	 */
	public List<OffenderGrievanceTxns> getOffenderGrievanceTxnsList() {
		return offenderGrievanceTxnsList;
	}

	/**
	 * @param offenderGrievanceTxnsList
	 *            the offenderGrievanceTxnsList to set
	 */
	public void setOffenderGrievanceTxnsList(List<OffenderGrievanceTxns> offenderGrievanceTxnsList) {
		this.offenderGrievanceTxnsList = offenderGrievanceTxnsList;
	}

	public List<OffenderGrievances> getInsertList() {
		return insertList;
	}

	public List<OffenderGrievances> getUpdateList() {
		return updateList;
	}

	public List<OffenderGrievances> getDeleteList() {
		return deleteList;
	}

}
