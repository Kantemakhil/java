package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VOffenderCourseEventsCommitBean extends BaseBean
 * 
 */
@XmlRootElement
public class VOffenderCourseEventsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VOffenderCourseEvents> insertList;
	private List<VOffenderCourseEvents> deleteList;
	private List<VOffenderCourseEvents> updateList;

	public void setInsertList(final List<VOffenderCourseEvents> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VOffenderCourseEvents> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VOffenderCourseEvents> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VOffenderCourseEvents> getInsertList() {
		return insertList;
	}

	public List<VOffenderCourseEvents> getUpdateList() {
		return updateList;
	}

	public List<VOffenderCourseEvents> getDeleteList() {
		return deleteList;
	}

}
