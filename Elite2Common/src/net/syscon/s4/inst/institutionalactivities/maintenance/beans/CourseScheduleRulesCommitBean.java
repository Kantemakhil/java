package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.CourseActivities;

@XmlRootElement
public class CourseScheduleRulesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<CourseScheduleRules> insertList;
	private List<CourseScheduleRules> deleteList;
	private List<CourseScheduleRules> updateList;
	private List<CourseActivities> actUpdate;
	public List<CourseScheduleRules> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<CourseScheduleRules> insertList) {
		this.insertList = insertList;
	}
	public List<CourseScheduleRules> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<CourseScheduleRules> deleteList) {
		this.deleteList = deleteList;
	}
	public List<CourseScheduleRules> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<CourseScheduleRules> updateList) {
		this.updateList = updateList;
	}
	public List<CourseActivities> getActUpdate() {
		return actUpdate;
	}
	public void setActUpdate(final List<CourseActivities> actUpdate) {
		this.actUpdate = actUpdate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
