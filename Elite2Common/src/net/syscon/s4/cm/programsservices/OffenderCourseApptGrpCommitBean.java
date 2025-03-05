package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.OffenderProgramProfiles;

public class OffenderCourseApptGrpCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OffenderCourseApptGrp> insertList;
	private List<OffenderCourseApptGrp> deleteList;
	private List<OffenderCourseApptGrp> updateList;
	private List<OffenderCourseApptRule> offschInsertList;
	private List<OffenderCourseApptRule> offschDeleteList;
	private List<OffenderCourseApptRule> offschUpdateList;
	private List<OffenderProgramProfiles> updateOffPrgList;

	public List<OffenderProgramProfiles> getUpdateOffPrgList() {
		return updateOffPrgList;
	}

	public void setUpdateOffPrgList(List<OffenderProgramProfiles> updateOffPrgList) {
		this.updateOffPrgList = updateOffPrgList;
	}

	public List<OffenderCourseApptRule> getOffschInsertList() {
		return offschInsertList;
	}

	public void setOffschInsertList(List<OffenderCourseApptRule> offschInsertList) {
		this.offschInsertList = offschInsertList;
	}

	public List<OffenderCourseApptRule> getOffschDeleteList() {
		return offschDeleteList;
	}

	public void setOffschDeleteList(List<OffenderCourseApptRule> offschDeleteList) {
		this.offschDeleteList = offschDeleteList;
	}

	public List<OffenderCourseApptRule> getOffschUpdateList() {
		return offschUpdateList;
	}

	public void setOffschUpdateList(List<OffenderCourseApptRule> offschUpdateList) {
		this.offschUpdateList = offschUpdateList;
	}

	public List<OffenderCourseApptGrp> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderCourseApptGrp> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderCourseApptGrp> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderCourseApptGrp> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderCourseApptGrp> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderCourseApptGrp> updateList) {
		this.updateList = updateList;
	}

}
