package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCipCausesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderCipCauses> insertList;
	@JsonProperty("deleteList")
	private List<OffenderCipCauses> deleteList;
	@JsonProperty("updateList")
	private List<OffenderCipCauses> updateList;

	public void setInsertList(List<OffenderCipCauses> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderCipCauses> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderCipCauses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderCipCauses> getInsertList() {
		return insertList;
	}

	public List<OffenderCipCauses> getUpdateList() {
		return updateList;
	}

	public List<OffenderCipCauses> getDeleteList() {
		return deleteList;
	}

}
