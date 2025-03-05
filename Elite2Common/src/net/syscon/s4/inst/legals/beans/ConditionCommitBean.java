package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ConditionCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Condition> insertList;
	
	@JsonProperty("updateList")
	private List<Condition> updateList;
	
	@JsonProperty("deleteList")
	private List<Condition> deleteList;
	
	@JsonProperty("beforeUpdatedList")
	private List<Condition> beforeUpdatedList;

	public List<Condition> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Condition> insertList) {
		this.insertList = insertList;
	}

	public List<Condition> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Condition> updateList) {
		this.updateList = updateList;
	}

	public List<Condition> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Condition> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Condition> getBeforeUpdatedList() {
		return beforeUpdatedList;
	}

	public void setBeforeUpdatedList(List<Condition> beforeUpdatedList) {
		this.beforeUpdatedList = beforeUpdatedList;
	}
}
