package net.syscon.s4.inst.legals.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentenceAdjustmentCommitBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderSentenceAdjustment> insertList;

	@JsonProperty("deleteList")
	private List<OffenderSentenceAdjustment> deleteList;

	@JsonProperty("updateList")
	private List<OffenderSentenceAdjustment> updateList;
	
	@JsonProperty("calcReason")
	private String calcReason;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	public List<OffenderSentenceAdjustment> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderSentenceAdjustment> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderSentenceAdjustment> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderSentenceAdjustment> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderSentenceAdjustment> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderSentenceAdjustment> updateList) {
		this.updateList = updateList;
	}

	public String getCalcReason() {
		return calcReason;
	}

	public void setCalcReason(String calcReason) {
		this.calcReason = calcReason;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	
	



}
