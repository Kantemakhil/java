package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class HealthRecordDetailsCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<HealthRecordDetails> insertList;

	@JsonProperty("deleteList")
	private List<HealthRecordDetails> deleteList;

	@JsonProperty("updateList")
	private List<HealthRecordDetails> updateList;

	public List<HealthRecordDetails> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<HealthRecordDetails> insertList) {
		this.insertList = insertList;
	}

	public List<HealthRecordDetails> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<HealthRecordDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<HealthRecordDetails> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<HealthRecordDetails> updateList) {
		this.updateList = updateList;
	}
	
	

}
