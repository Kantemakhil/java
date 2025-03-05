package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AdjustmentDetailsCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AdjustmentDetails> insertList;

	@JsonProperty("updateList")
	private List<AdjustmentDetails> updateList;
	
	@JsonProperty("deleteList")
	private List<AdjustmentDetails> deleteList;
	
	@JsonProperty("selectedAdjustmentRecord")
	private SentenceAdjustment selectedAdjustmentRecord;

	public List<AdjustmentDetails> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<AdjustmentDetails> insertList) {
		this.insertList = insertList;
	}

	public List<AdjustmentDetails> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<AdjustmentDetails> updateList) {
		this.updateList = updateList;
	}

	public SentenceAdjustment getSelectedAdjustmentRecord() {
		return selectedAdjustmentRecord;
	}

	public void setSelectedAdjustmentRecord(SentenceAdjustment selectedAdjustmentRecord) {
		this.selectedAdjustmentRecord = selectedAdjustmentRecord;
	}

	public List<AdjustmentDetails> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<AdjustmentDetails> deleteList) {
		this.deleteList = deleteList;
	}
	
	
}
