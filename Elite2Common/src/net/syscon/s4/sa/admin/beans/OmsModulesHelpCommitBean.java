package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsModulesHelp;

public class OmsModulesHelpCommitBean {
	
	private static final long serialVersionUID = 1L;
	private List<OmsModulesHelp> insertList;
	private List<OmsModulesHelp> deleteList;
	private List<OmsModulesHelp> updateList;
	private String createUserId;
	public List<OmsModulesHelp> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OmsModulesHelp> insertList) {
		this.insertList = insertList;
	}
	public List<OmsModulesHelp> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OmsModulesHelp> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OmsModulesHelp> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OmsModulesHelp> updateList) {
		this.updateList = updateList;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	
}
