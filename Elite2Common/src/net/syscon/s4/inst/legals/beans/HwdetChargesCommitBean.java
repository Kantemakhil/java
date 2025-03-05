package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class HwdetChargesCommitBean extends BaseModel implements Serializable  {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Charges> insertList;
	
	@JsonProperty("updateList")
	private List<Charges> updateList;
	
	@JsonProperty("deleteList")
	private List<Charges> deleteList;
	
	
	
	public List<Charges> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Charges> insertList) {
		this.insertList = insertList;
	}

	public List<Charges> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Charges> updateList) {
		this.updateList = updateList;
	}

	public List<Charges> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Charges> deleteList) {
		this.deleteList = deleteList;
	}
	
	
	
}


