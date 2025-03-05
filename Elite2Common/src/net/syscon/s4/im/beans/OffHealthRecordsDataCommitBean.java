package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffHealthRecordsDataCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffHealthRecordsData> insertList;

	@JsonProperty("deleteList")
	private List<OffHealthRecordsData> deleteList;

	@JsonProperty("updateList")
	private List<OffHealthRecordsData> updateList;

	public List<OffHealthRecordsData> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffHealthRecordsData> insertList) {
		this.insertList = insertList;
	}

	public List<OffHealthRecordsData> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffHealthRecordsData> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffHealthRecordsData> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffHealthRecordsData> updateList) {
		this.updateList = updateList;
	}
	
	



}
