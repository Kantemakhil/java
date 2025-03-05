package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class CourseSchedulesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CourseSchedules> insertList;
	private List<CourseSchedules> deleteList;
	private List<CourseSchedules> updateList;

	public List<CourseSchedules> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<CourseSchedules> insertList) {
		this.insertList = insertList;
	}

	public List<CourseSchedules> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<CourseSchedules> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CourseSchedules> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<CourseSchedules> updateList) {
		this.updateList = updateList;
	}

}
