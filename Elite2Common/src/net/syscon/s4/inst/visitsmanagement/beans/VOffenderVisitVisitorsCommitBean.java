package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VOffenderVisitVisitorsCommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VOffenderVisitVisitors> insertList;
	
	@JsonProperty("updateList")
	private List<VOffenderVisitVisitors> updateList;
	
	@JsonProperty("deleteList")
	private List<VOffenderVisitVisitors> deleteList;
	
	public List<VOffenderVisitVisitors> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<VOffenderVisitVisitors> insertList) {
		this.insertList = insertList;
	}

	public List<VOffenderVisitVisitors> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<VOffenderVisitVisitors> updateList) {
		this.updateList = updateList;
	}

	public List<VOffenderVisitVisitors> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<VOffenderVisitVisitors> deleteList) {
		this.deleteList = deleteList;
	}
}
