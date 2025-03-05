package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AdjustCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Adjustments> insertList;
	
	@JsonProperty("updateList")
	private List<Adjustments> updateList;
	
	@JsonProperty("deleteList")
	private List<Adjustments> deleteList;
	
	@JsonProperty("beforeUpdatedList")
	private List<Adjustments> beforeUpdatedList;

	public List<Adjustments> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Adjustments> insertList) {
		this.insertList = insertList;
	}

	public List<Adjustments> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Adjustments> updateList) {
		this.updateList = updateList;
	}

	public List<Adjustments> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Adjustments> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Adjustments> getBeforeUpdatedList() {
		return beforeUpdatedList;
	}

	public void setBeforeUpdatedList(List<Adjustments> beforeUpdatedList) {
		this.beforeUpdatedList = beforeUpdatedList;
	}
}
