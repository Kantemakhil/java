package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.inst.legals.beans.Holds;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class HoldsCommitBean extends BaseModel implements Serializable  {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Holds> insertList;
	
	@JsonProperty("updateList")
	private List<Holds> updateList;
	
	@JsonProperty("deleteList")
	private List<Holds> deleteList;
	

	public List<Holds> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Holds> insertList) {
		this.insertList = insertList;
	}

	public List<Holds> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Holds> updateList) {
		this.updateList = updateList;
	}

	public List<Holds> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Holds> deleteList) {
		this.deleteList = deleteList;
	}
	
}


