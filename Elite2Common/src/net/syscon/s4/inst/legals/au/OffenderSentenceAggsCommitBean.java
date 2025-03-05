package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentenceAggsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderSentenceAggs> insertList;
	@JsonProperty("deleteList ")
	private List<OffenderSentenceAggs> deleteList;
	@JsonProperty("updateList")
	private List<OffenderSentenceAggs> updateList;

	public void setInsertList(List<OffenderSentenceAggs> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderSentenceAggs> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderSentenceAggs> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderSentenceAggs> getInsertList() {
		return insertList;
	}

	public List<OffenderSentenceAggs> getUpdateList() {
		return updateList;
	}

	public List<OffenderSentenceAggs> getDeleteList() {
		return deleteList;
	}

}
