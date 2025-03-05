package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class IdentifierCommitBean extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CaseIdentifiers> insertList;
	
	@JsonProperty("updateList")
	private List<CaseIdentifiers> updateList;
	
	@JsonProperty("deleteList")
	private List<CaseIdentifiers> deleteList;
	
	@JsonProperty("beforeUpdatedList")
	private List<CaseIdentifiers> beforeUpdatedList;

	public List<CaseIdentifiers> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<CaseIdentifiers> insertList) {
		this.insertList = insertList;
	}

	public List<CaseIdentifiers> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<CaseIdentifiers> updateList) {
		this.updateList = updateList;
	}

	public List<CaseIdentifiers> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<CaseIdentifiers> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CaseIdentifiers> getBeforeUpdatedList() {
		return beforeUpdatedList;
	}

	public void setBeforeUpdatedList(List<CaseIdentifiers> beforeUpdatedList) {
		this.beforeUpdatedList = beforeUpdatedList;
	}
}
