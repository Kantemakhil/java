package net.syscon.s4.cf.deductions.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class FeeOverrideDetailsCommitBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<FeeOverrideDetails> insertList;
	@JsonProperty("deleteList")
	private List<FeeOverrideDetails> deleteList;
	@JsonProperty("updateList")
	private List<FeeOverrideDetails> updateList;
	
	public List<FeeOverrideDetails> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<FeeOverrideDetails> insertList) {
		this.insertList = insertList;
	}
	public List<FeeOverrideDetails> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<FeeOverrideDetails> deleteList) {
		this.deleteList = deleteList;
	}
	public List<FeeOverrideDetails> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<FeeOverrideDetails> updateList) {
		this.updateList = updateList;
	}


}
