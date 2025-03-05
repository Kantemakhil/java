package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SentenceAdjustmentCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SentenceAdjustment> insertList;

	@JsonProperty("updateList")
	private List<SentenceAdjustment> updateList;

	@JsonProperty("deleteList")
	private List<SentenceAdjustment> deleteList;

	/**
	 * Creates new SentenceAdjustmentCommitBean class Object
	 */
	public SentenceAdjustmentCommitBean() {
		// SentenceAdjustmentCommitBean
	}

	public void setInsertList(final List<SentenceAdjustment> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SentenceAdjustment> updateList) {
		this.updateList = updateList;
	}

	public List<SentenceAdjustment> getInsertList() {
		return insertList;
	}

	public List<SentenceAdjustment> getUpdateList() {
		return updateList;
	}

	public void setDeleteList(final List<SentenceAdjustment> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SentenceAdjustment> getDeleteList() {
		return deleteList;
	}

}
