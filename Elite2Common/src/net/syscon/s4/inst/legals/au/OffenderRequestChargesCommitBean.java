package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.legalorders.OffenderRequestCharges;

public class OffenderRequestChargesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderRequestCharges> insertList;
	@JsonProperty("deleteList ")
	private List<OffenderRequestCharges> deleteList;
	@JsonProperty("updateList")
	private List<OffenderRequestCharges> updateList;

	public void setInsertList(List<OffenderRequestCharges> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderRequestCharges> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderRequestCharges> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderRequestCharges> getInsertList() {
		return insertList;
	}

	public List<OffenderRequestCharges> getUpdateList() {
		return updateList;
	}

	public List<OffenderRequestCharges> getDeleteList() {
		return deleteList;
	}

}
