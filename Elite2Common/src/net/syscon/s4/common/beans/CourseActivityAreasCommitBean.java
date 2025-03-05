package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class CourseActivityAreasCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<CourseActivityAreas> insertList;
	@JsonProperty("deleteList")
	private List<CourseActivityAreas> deleteList;
	@JsonProperty("updateList")
	private List<CourseActivityAreas> updateList;
	public List<CourseActivityAreas> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<CourseActivityAreas> insertList) {
		this.insertList = insertList;
	}
	public List<CourseActivityAreas> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<CourseActivityAreas> deleteList) {
		this.deleteList = deleteList;
	}
	public List<CourseActivityAreas> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<CourseActivityAreas> updateList) {
		this.updateList = updateList;
	}



}
