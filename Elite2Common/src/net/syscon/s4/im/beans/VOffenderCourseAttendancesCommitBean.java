package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderCourseAttendancesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VOffenderCourseAttendances> insertList;

	@JsonProperty("deleteList")
	private List<VOffenderCourseAttendances> deleteList;

	@JsonProperty("updateList")
	private List<VOffenderCourseAttendances> updateList;

	/**
	 * @return the insertList
	 */
	public List<VOffenderCourseAttendances> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<VOffenderCourseAttendances> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<VOffenderCourseAttendances> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<VOffenderCourseAttendances> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<VOffenderCourseAttendances> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<VOffenderCourseAttendances> updateList) {
		this.updateList = updateList;
	}

}
