package net.syscon.s4.common.beans;

import java.util.List;

public class AgyIntLocProfilesCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AgyIntLocProfiles> insertList;
	private List<AgyIntLocProfiles> deleteList;
	private List<AgyIntLocProfiles> updateList;

	public void setInsertList(final List<AgyIntLocProfiles> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgyIntLocProfiles> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgyIntLocProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgyIntLocProfiles> getInsertList() {
		return insertList;
	}

	public List<AgyIntLocProfiles> getUpdateList() {
		return updateList;
	}

	public List<AgyIntLocProfiles> getDeleteList() {
		return deleteList;
	}

}
