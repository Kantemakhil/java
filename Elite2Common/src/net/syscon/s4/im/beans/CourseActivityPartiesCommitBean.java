package net.syscon.s4.im.beans;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@JsonIgnoreProperties(ignoreUnknown = true)

public class CourseActivityPartiesCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CourseActivityParties> insertList;

	@JsonProperty("deleteList")
	private List<CourseActivityParties> deleteList;

	@JsonProperty("updateList")
	private List<CourseActivityParties> updateList;

	public List<CourseActivityParties> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<CourseActivityParties> insertList) {
		this.insertList = insertList;
	}

	public List<CourseActivityParties> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<CourseActivityParties> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CourseActivityParties> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<CourseActivityParties> updateList) {
		this.updateList = updateList;
	}
	
}

