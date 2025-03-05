package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * 
 * class WorkFlowLogsCommitBean
 *
 */
public class WorkFlowLogsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<WorkFlowLogs> insertList;
	private List<WorkFlowLogs> deleteList;
	private List<WorkFlowLogs> updateList;
	private CasePlans updateNextReviewDate;

	public CasePlans getUpdateNextReviewDate() {
		return updateNextReviewDate;
	}

	public void setUpdateNextReviewDate(CasePlans updateNextReviewDate) {
		this.updateNextReviewDate = updateNextReviewDate;
	}

	/**
	 * @return the insertList
	 */
	public List<WorkFlowLogs> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<WorkFlowLogs> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<WorkFlowLogs> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<WorkFlowLogs> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<WorkFlowLogs> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<WorkFlowLogs> updateList) {
		this.updateList = updateList;
	}

	@Override
	public String toString() {
		return "WorkFlowLogsCommitBean [insertList=" + insertList + ", deleteList=" + deleteList + ", updateList="
				+ updateList + ", updateNextReviewDate=" + updateNextReviewDate + "]";
	}

}
