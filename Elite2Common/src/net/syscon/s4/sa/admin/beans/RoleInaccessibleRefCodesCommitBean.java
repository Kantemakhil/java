package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class RoleInaccessibleRefCodesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<RoleInaccessibleRefCodes> insertList;
	private List<RoleInaccessibleRefCodes> deleteList;
	private List<RoleInaccessibleRefCodes> updateList;
	public List<RoleInaccessibleRefCodes> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<RoleInaccessibleRefCodes> insertList) {
		this.insertList = insertList;
	}
	public List<RoleInaccessibleRefCodes> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<RoleInaccessibleRefCodes> deleteList) {
		this.deleteList = deleteList;
	}
	public List<RoleInaccessibleRefCodes> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<RoleInaccessibleRefCodes> updateList) {
		this.updateList = updateList;
	}
	
	
}
