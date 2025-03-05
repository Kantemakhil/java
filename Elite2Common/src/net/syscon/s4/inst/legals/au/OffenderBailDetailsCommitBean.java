package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;

public class OffenderBailDetailsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderBailDetails> insertList;
	@JsonProperty("deleteList")
	private List<OffenderBailDetails> deleteList;
	@JsonProperty("updateList")
	private List<OffenderBailDetails> updateList;
	public List<OffenderBailDetails> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderBailDetails> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderBailDetails> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderBailDetails> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderBailDetails> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderBailDetails> updateList) {
		this.updateList = updateList;
	}
	
	

}
