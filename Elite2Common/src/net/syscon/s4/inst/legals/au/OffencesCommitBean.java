package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;

public class OffencesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffensesOutcome> insertList;
	@JsonProperty("deleteList ")
	private List<OffensesOutcome> deleteList;
	@JsonProperty("updateList")
	private List<OffensesOutcome> updateList;
	@JsonProperty("sealFlag")
	private String sealFlag;

	public void setInsertList(final List<OffensesOutcome> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffensesOutcome> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffensesOutcome> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffensesOutcome> getInsertList() {
		return insertList;
	}

	public List<OffensesOutcome> getUpdateList() {
		return updateList;
	}

	public List<OffensesOutcome> getDeleteList() {
		return deleteList;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
