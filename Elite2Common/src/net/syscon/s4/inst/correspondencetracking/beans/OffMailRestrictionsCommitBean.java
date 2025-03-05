package net.syscon.s4.inst.correspondencetracking.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffMailRestrictionsCommitBean extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffMailRestrictions> insertList;
	@JsonProperty("deleteList")
	private List<OffMailRestrictions> deleteList;
	@JsonProperty("updateList")
	private List<OffMailRestrictions> updateList;
	
	public List<OffMailRestrictions> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffMailRestrictions> insertList) {
		this.insertList = insertList;
	}
	public List<OffMailRestrictions> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffMailRestrictions> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffMailRestrictions> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffMailRestrictions> updateList) {
		this.updateList = updateList;
	}
	

}
