package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class OffenderSamplesCommitBean
 *
 */
public class OffenderSamplesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<OffenderSamples> insertList;
	private List<OffenderSamples> deleteList;
	private List<OffenderSamples> updateList;

	public void setInsertList(final List<OffenderSamples> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderSamples> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderSamples> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderSamples> getInsertList() {
		return insertList;
	}

	public List<OffenderSamples> getUpdateList() {
		return updateList;
	}

	public List<OffenderSamples> getDeleteList() {
		return deleteList;
	}

}
