package net.syscon.s4.inst.visitsmanagement.beans;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


public class OffenderPersonRestrictsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderPersonRestricts> insertList;
	@JsonProperty("deleteList ")
	private List<OffenderPersonRestricts> deleteList;
	@JsonProperty("updateList")
	private List<OffenderPersonRestricts> updateList;

	public void setInsertList(List<OffenderPersonRestricts> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderPersonRestricts> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderPersonRestricts> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderPersonRestricts> getInsertList(){
		return insertList;
	}

	public List<OffenderPersonRestricts> getUpdateList(){
		return updateList;
	}

	public List<OffenderPersonRestricts> getDeleteList(){
		return deleteList;
	}

}
