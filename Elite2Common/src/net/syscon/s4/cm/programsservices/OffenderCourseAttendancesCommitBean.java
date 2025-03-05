package net.syscon.s4.cm.programsservices;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

public class OffenderCourseAttendancesCommitBean extends BaseModel{
	@JsonProperty("insertList")
	private List<OffenderCourseAttendance> insertList;
	@JsonProperty("deleteList")
	private List<OffenderCourseAttendance> deleteList;
	@JsonProperty("updateList")
	private List<OffenderCourseAttendance> updateList;
	@JsonProperty("courseSchedules")
	private CourseSchedules courseSchedules;
	public List<OffenderCourseAttendance> getInsertList() {
		return insertList;
	}
	public CourseSchedules getCourseSchedules() {
		return courseSchedules;
	}
	public void setCourseSchedules(CourseSchedules courseSchedules) {
		this.courseSchedules = courseSchedules;
	}
	public void setInsertList(List<OffenderCourseAttendance> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderCourseAttendance> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderCourseAttendance> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderCourseAttendance> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderCourseAttendance> updateList) {
		this.updateList = updateList;
	}



}
