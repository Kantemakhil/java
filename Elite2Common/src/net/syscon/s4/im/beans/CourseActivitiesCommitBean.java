package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseActivitiesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<CourseActivities> insertList;
	private List<CourseActivities> deleteList;
	private List<CourseActivities> updateList;

	public void setInsertList(final List<CourseActivities> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CourseActivities> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CourseActivities> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CourseActivities> getInsertList() {
		return insertList;
	}

	public List<CourseActivities> getUpdateList() {
		return updateList;
	}

	public List<CourseActivities> getDeleteList() {
		return deleteList;
	}

}
