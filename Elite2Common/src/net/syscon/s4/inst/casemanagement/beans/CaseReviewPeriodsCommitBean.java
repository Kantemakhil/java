package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class CaseReviewPeriodsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CaseReviewPeriods> insertList;
	private List<CaseReviewPeriods> deleteList;
	private List<CaseReviewPeriods> updateList;

	public List<CaseReviewPeriods> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<CaseReviewPeriods> insertList) {
		this.insertList = insertList;
	}

	public List<CaseReviewPeriods> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<CaseReviewPeriods> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CaseReviewPeriods> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<CaseReviewPeriods> updateList) {
		this.updateList = updateList;
	}

}
