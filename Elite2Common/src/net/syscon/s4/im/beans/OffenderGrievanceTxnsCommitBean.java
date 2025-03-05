package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderGrievanceTxnsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<OffenderGrievanceTxns> insertList;
	private List<OffenderGrievanceTxns> deleteList;
	private List<OffenderGrievanceTxns> updateList;

	public void setInsertList(List<OffenderGrievanceTxns> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderGrievanceTxns> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderGrievanceTxns> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderGrievanceTxns> getInsertList() {
		return insertList;
	}

	public List<OffenderGrievanceTxns> getUpdateList() {
		return updateList;
	}

	public List<OffenderGrievanceTxns> getDeleteList() {
		return deleteList;
	}

}
