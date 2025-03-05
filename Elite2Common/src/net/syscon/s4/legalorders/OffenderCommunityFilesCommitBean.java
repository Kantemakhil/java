package net.syscon.s4.legalorders;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCommunityFilesCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderCommunityFiles> insertList;
	private List<OffenderCommunityFiles> deleteList;
	private List<OffenderCommunityFiles> updateList;

	public List<OffenderCommunityFiles> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderCommunityFiles> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderCommunityFiles> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderCommunityFiles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderCommunityFiles> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderCommunityFiles> updateList) {
		this.updateList = updateList;
	}

}
