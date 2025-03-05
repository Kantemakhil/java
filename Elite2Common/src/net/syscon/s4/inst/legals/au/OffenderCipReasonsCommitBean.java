package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCipReasonsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderCipReasons> insertList;
	@JsonProperty("deleteList")
	private List<OffenderCipReasons> deleteList;
	@JsonProperty("updateList")
	private List<OffenderCipReasons> updateList;

	public void setInsertList(List<OffenderCipReasons> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderCipReasons> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderCipReasons> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderCipReasons> getInsertList() {
		return insertList;
	}

	public List<OffenderCipReasons> getUpdateList() {
		return updateList;
	}

	public List<OffenderCipReasons> getDeleteList() {
		return deleteList;
	}

}
