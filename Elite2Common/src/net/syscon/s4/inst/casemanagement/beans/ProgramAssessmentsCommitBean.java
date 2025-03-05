package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class ProgramAssessmentsCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private List<ProgramAssessments> insertList;
	private List<ProgramAssessments> deleteList;
	private List<ProgramAssessments> updateList;

	/**
	 * @return the insertList
	 */
	public List<ProgramAssessments> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<ProgramAssessments> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<ProgramAssessments> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<ProgramAssessments> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<ProgramAssessments> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<ProgramAssessments> updateList) {
		this.updateList = updateList;
	}




}
