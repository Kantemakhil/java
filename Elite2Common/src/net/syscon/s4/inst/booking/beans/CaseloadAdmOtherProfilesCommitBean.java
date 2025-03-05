package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;

public class CaseloadAdmOtherProfilesCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CaseloadAdmOtherProfiles> insertList;
	private List<CaseloadAdmOtherProfiles> deleteList;
	private List<CaseloadAdmOtherProfiles> updateList;

	public List<CaseloadAdmOtherProfiles> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<CaseloadAdmOtherProfiles> insertList) {
		this.insertList = insertList;
	}

	public List<CaseloadAdmOtherProfiles> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<CaseloadAdmOtherProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CaseloadAdmOtherProfiles> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<CaseloadAdmOtherProfiles> updateList) {
		this.updateList = updateList;
	}

}
