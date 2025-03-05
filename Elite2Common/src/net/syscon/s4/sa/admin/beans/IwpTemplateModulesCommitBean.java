package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.IwpTemplateModules;

public class IwpTemplateModulesCommitBean extends BaseModel  {
	private static final long serialVersionUID = 1L;
	private List<IwpTemplateModules> insertList;
	private List<IwpTemplateModules> deleteList;
	private List<IwpTemplateModules> updateList;
	public List<IwpTemplateModules> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<IwpTemplateModules> insertList) {
		this.insertList = insertList;
	}
	public List<IwpTemplateModules> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<IwpTemplateModules> deleteList) {
		this.deleteList = deleteList;
	}
	public List<IwpTemplateModules> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<IwpTemplateModules> updateList) {
		this.updateList = updateList;
	}

}
