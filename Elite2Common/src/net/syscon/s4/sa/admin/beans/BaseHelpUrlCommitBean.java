package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.im.beans.BaseHelpUrl;

public class BaseHelpUrlCommitBean {
	
	private static final long serialVersionUID = 1L;
	private List<BaseHelpUrl> insertList;
	private List<BaseHelpUrl> deleteList;
	private List<BaseHelpUrl> updateList;
	public List<BaseHelpUrl> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<BaseHelpUrl> insertList) {
		this.insertList = insertList;
	}
	public List<BaseHelpUrl> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<BaseHelpUrl> deleteList) {
		this.deleteList = deleteList;
	}
	public List<BaseHelpUrl> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<BaseHelpUrl> updateList) {
		this.updateList = updateList;
	}

	
}
