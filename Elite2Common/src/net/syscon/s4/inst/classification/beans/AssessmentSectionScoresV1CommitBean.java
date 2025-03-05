package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AssessmentSectionScoresV1CommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AssessmentSectionScoresV1> insertList;

	@JsonProperty("deleteList")
	private List<AssessmentSectionScoresV1> deleteList;

	/**
	 * @return the insertList
	 */
	public List<AssessmentSectionScoresV1> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<AssessmentSectionScoresV1> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<AssessmentSectionScoresV1> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<AssessmentSectionScoresV1> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<AssessmentSectionScoresV1> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<AssessmentSectionScoresV1> updateList) {
		this.updateList = updateList;
	}

	@JsonProperty("updateList")
	private List<AssessmentSectionScoresV1> updateList;

}
