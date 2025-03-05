package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

public class OffenderCourseSkillsCommitBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderCourseSkills> insertList;
	private List<OffenderCourseSkills> deleteList;
	private List<OffenderCourseSkills> updateList;
	
	private List<VOffenderCourseEvents> eventUpdateList;

	public List<OffenderCourseSkills> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<OffenderCourseSkills> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderCourseSkills> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<OffenderCourseSkills> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderCourseSkills> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<OffenderCourseSkills> updateList) {
		this.updateList = updateList;
	}

	public List<VOffenderCourseEvents> getEventUpdateList() {
		return eventUpdateList;
	}

	public void setEventUpdateList(List<VOffenderCourseEvents> eventUpdateList) {
		this.eventUpdateList = eventUpdateList;
	}
	
	

}
