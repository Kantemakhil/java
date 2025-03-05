package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;

public class CaseloadAdmAlertProfilesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<CaseloadAdmAlertProfiles> insertList;
	private List<CaseloadAdmAlertProfiles> deleteList;
	private List<CaseloadAdmAlertProfiles> updateList;

	public List<CaseloadAdmAlertProfiles> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<CaseloadAdmAlertProfiles> insertList) {
		this.insertList = insertList;
	}

	public List<CaseloadAdmAlertProfiles> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<CaseloadAdmAlertProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CaseloadAdmAlertProfiles> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<CaseloadAdmAlertProfiles> updateList) {
		this.updateList = updateList;
	}

}
