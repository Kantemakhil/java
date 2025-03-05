package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class HoldsWarantsHistoryCommitBean  extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<HoldsWarantsHistory> insertList;
	
	@JsonProperty("updateList")
	private List<HoldsWarantsHistory> updateList;
	
	@JsonProperty("deleteList")
	private List<HoldsWarantsHistory> deleteList;
	
	
	
	public List<HoldsWarantsHistory> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<HoldsWarantsHistory> insertList) {
		this.insertList = insertList;
	}

	public List<HoldsWarantsHistory> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<HoldsWarantsHistory> updateList) {
		this.updateList = updateList;
	}

	public List<HoldsWarantsHistory> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<HoldsWarantsHistory> deleteList) {
		this.deleteList = deleteList;
	}
	
	
}
