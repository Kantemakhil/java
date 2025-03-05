package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class OffenderImprisonStatusesCommitBean
 *
 */
public class OffenderImprisonStatusesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<OffenderImprisonStatuses> insertList;
	private List<OffenderImprisonStatuses> deleteList;
	private List<OffenderImprisonStatuses> updateList;

	public void setInsertList(final List<OffenderImprisonStatuses> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderImprisonStatuses> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderImprisonStatuses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderImprisonStatuses> getInsertList() {
		return insertList;
	}

	public List<OffenderImprisonStatuses> getUpdateList() {
		return updateList;
	}

	public List<OffenderImprisonStatuses> getDeleteList() {
		return deleteList;
	}

}
