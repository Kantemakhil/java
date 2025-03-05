package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cm.programsservices.CourseScheduleStaff;
import net.syscon.s4.common.beans.BaseModel;

public class CourseScheduleStaffsCommitBean  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<CourseScheduleStaff> insertList;
	
	@JsonProperty("deleteList")
	private List<CourseScheduleStaff> deleteList;
	
	@JsonProperty("updateList")
	private List<CourseScheduleStaff> updateList;
	
	public List<CourseScheduleStaff> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<CourseScheduleStaff> insertList) {
		this.insertList = insertList;
	}
	public List<CourseScheduleStaff> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<CourseScheduleStaff> deleteList) {
		this.deleteList = deleteList;
	}
	public List<CourseScheduleStaff> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<CourseScheduleStaff> updateList) {
		this.updateList = updateList;             
	}
	
	
}
