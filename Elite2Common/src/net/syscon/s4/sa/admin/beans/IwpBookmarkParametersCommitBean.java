package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class IwpBookmarkParametersCommitBean extends BaseModel  {
	private static final long serialVersionUID = 1L;
	private List<IwpBookmarkParameters> insertList;
	private List<IwpBookmarkParameters> deleteList;
	private List<IwpBookmarkParameters> updateList;
	public List<IwpBookmarkParameters> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<IwpBookmarkParameters> insertList) {
		this.insertList = insertList;
	}
	public List<IwpBookmarkParameters> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<IwpBookmarkParameters> deleteList) {
		this.deleteList = deleteList;
	}
	public List<IwpBookmarkParameters> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<IwpBookmarkParameters> updateList) {
		this.updateList = updateList;
	}

}
