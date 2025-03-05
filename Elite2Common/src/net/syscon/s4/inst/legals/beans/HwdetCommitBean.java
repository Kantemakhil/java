package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class HwdetCommitBean extends BaseModel implements Serializable  {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<HoldWarrentDetainer> insertList;
	
	@JsonProperty("updateList")
	private List<HoldWarrentDetainer> updateList;
	
	@JsonProperty("deleteList")
	private List<HoldWarrentDetainer> deleteList;
	
	
	
	public List<HoldWarrentDetainer> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<HoldWarrentDetainer> insertList) {
		this.insertList = insertList;
	}

	public List<HoldWarrentDetainer> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<HoldWarrentDetainer> updateList) {
		this.updateList = updateList;
	}

	public List<HoldWarrentDetainer> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<HoldWarrentDetainer> deleteList) {
		this.deleteList = deleteList;
	}
	
	
	
}


