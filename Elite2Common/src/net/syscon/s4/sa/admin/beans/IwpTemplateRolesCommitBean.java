package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class IwpTemplateRolesCommitBean extends BaseModel  {
	private static final long serialVersionUID = 1L;
	private List<IwpTemplateRoles> insertList;
	private List<IwpTemplateRoles> deleteList;
	private List<IwpTemplateRoles> updateList;
	public List<IwpTemplateRoles> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<IwpTemplateRoles> insertList) {
		this.insertList = insertList;
	}
	public List<IwpTemplateRoles> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<IwpTemplateRoles> deleteList) {
		this.deleteList = deleteList;
	}
	public List<IwpTemplateRoles> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<IwpTemplateRoles> updateList) {
		this.updateList = updateList;
	}

}
