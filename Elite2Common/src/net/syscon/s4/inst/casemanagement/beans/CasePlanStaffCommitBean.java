package net.syscon.s4.inst.casemanagement.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class CasePlanStaffCommitBean extends BaseModel {

	private List<CasePlanStaff> insertList;
	private List<CasePlanStaff> updateList;
	private List<CasePlanStaff> deleteList;

	public List<CasePlanStaff> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<CasePlanStaff> insertList) {
		this.insertList = insertList;
	}

	public List<CasePlanStaff> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<CasePlanStaff> updateList) {
		this.updateList = updateList;
	}

	public List<CasePlanStaff> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<CasePlanStaff> deleteList) {
		this.deleteList = deleteList;
	}

	@Override
	public String toString() {
		return "CasePlanStaffDetailsCommitBean [insertList=" + insertList + ", updateList=" + updateList
				+ ", deleteList=" + deleteList + "]";
	}

}
