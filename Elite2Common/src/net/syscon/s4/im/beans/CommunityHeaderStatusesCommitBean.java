package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommunityHeaderStatusesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<CommunityHeaderStatuses> insertList;
	private List<CommunityHeaderStatuses> deleteList;
	private List<CommunityHeaderStatuses> updateList;

	public void setInsertList(final List<CommunityHeaderStatuses> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CommunityHeaderStatuses> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CommunityHeaderStatuses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CommunityHeaderStatuses> getInsertList() {
		return insertList;
	}

	public List<CommunityHeaderStatuses> getUpdateList() {
		return updateList;
	}

	public List<CommunityHeaderStatuses> getDeleteList() {
		return deleteList;
	}

}
