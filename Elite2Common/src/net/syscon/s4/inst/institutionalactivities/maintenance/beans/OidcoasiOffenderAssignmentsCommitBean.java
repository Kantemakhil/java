package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OidcoasiOffenderAssignmentsCommitBean extends BaseBean
 * 
 */
@XmlRootElement
public class OidcoasiOffenderAssignmentsCommitBean extends BaseModel {
	private List<OidcoasiOffenderAssignments> insertList;
	private List<OidcoasiOffenderAssignments> deleteList;
	private List<OidcoasiOffenderAssignments> updateList;

	public void setInsertList(final List<OidcoasiOffenderAssignments> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OidcoasiOffenderAssignments> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OidcoasiOffenderAssignments> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OidcoasiOffenderAssignments> getInsertList() {
		return insertList;
	}

	public List<OidcoasiOffenderAssignments> getUpdateList() {
		return updateList;
	}

	public List<OidcoasiOffenderAssignments> getDeleteList() {
		return deleteList;
	}

}
