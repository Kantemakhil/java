package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderBailConditionscommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderBailConditions> insertList;
	@JsonProperty("deleteList")
	private List<OffenderBailConditions> deleteList;
	@JsonProperty("updateList")
	private List<OffenderBailConditions> updateList;
	public List<OffenderBailConditions> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderBailConditions> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderBailConditions> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderBailConditions> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderBailConditions> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderBailConditions> updateList) {
		this.updateList = updateList;
	}

	
}
