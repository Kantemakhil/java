package net.syscon.s4.inst.offenderissuestracking.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderGrievStaffsCommitBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderGrievStaffs> insertList;

	@JsonProperty("deleteList")
	private List<OffenderGrievStaffs> deleteList;

	@JsonProperty("updateList")
	private List<OffenderGrievStaffs> updateList;

	public List<OffenderGrievStaffs> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderGrievStaffs> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderGrievStaffs> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderGrievStaffs> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderGrievStaffs> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderGrievStaffs> updateList) {
		this.updateList = updateList;
	}

}
