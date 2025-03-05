package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class programsPayCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<programsPayBean> insertList;

	@JsonProperty("deleteList")
	private List<programsPayBean> deleteList;

	@JsonProperty("updateList")
	private List<programsPayBean> updateList;

	public List<programsPayBean> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<programsPayBean> insertList) {
		this.insertList = insertList;
	}

	public List<programsPayBean> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<programsPayBean> deleteList) {
		this.deleteList = deleteList;
	}

	public List<programsPayBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<programsPayBean> updateList) {
		this.updateList = updateList;
	}

}
