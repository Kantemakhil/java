package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderAssessmentsCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderAssessments> insertList;

	@JsonProperty("deleteList")
	private List<OffenderAssessments> deleteList;

	@JsonProperty("updateList")
	private List<OffenderAssessments> updateList;

	/**
	 * Creates new OffenderAssessmentsCommitBean class Object
	 */
	public OffenderAssessmentsCommitBean() {
		// OffenderAssessmentsCommitBean
	}

	public void setInsertList(final List<OffenderAssessments> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderAssessments> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderAssessments> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderAssessments> getInsertList() {
		return insertList;
	}

	public List<OffenderAssessments> getUpdateList() {
		return updateList;
	}

	public List<OffenderAssessments> getDeleteList() {
		return deleteList;
	}

}
