package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class programsPayCompensationCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<programsPayCompensationBean> insertList;

	@JsonProperty("deleteList")
	private List<programsPayCompensationBean> deleteList;

	@JsonProperty("updateList")
	private List<programsPayCompensationBean> updateList;

	public List<programsPayCompensationBean> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<programsPayCompensationBean> insertList) {
		this.insertList = insertList;
	}

	public List<programsPayCompensationBean> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<programsPayCompensationBean> deleteList) {
		this.deleteList = deleteList;
	}

	public List<programsPayCompensationBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<programsPayCompensationBean> updateList) {
		this.updateList = updateList;
	}

}
