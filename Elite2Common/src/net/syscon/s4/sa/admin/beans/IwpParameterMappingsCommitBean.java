package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class IwpParameterMappingsCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<IwpParameterMappings> insertList;
	private List<IwpParameterMappings> deleteList;
	private List<IwpParameterMappings> updateList;

	public List<IwpParameterMappings> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<IwpParameterMappings> insertList) {
		this.insertList = insertList;
	}

	public List<IwpParameterMappings> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<IwpParameterMappings> deleteList) {
		this.deleteList = deleteList;
	}

	public List<IwpParameterMappings> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<IwpParameterMappings> updateList) {
		this.updateList = updateList;
	}

}
