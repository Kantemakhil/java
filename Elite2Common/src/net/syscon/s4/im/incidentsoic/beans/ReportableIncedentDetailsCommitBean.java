package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ReportableIncedentDetailsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<ReportableIncedentDetails> insertList;

	@JsonProperty("deleteList")
	private List<ReportableIncedentDetails> deleteList;
	
	@JsonProperty("updateList")
	private List<ReportableIncedentDetails> updateList;

	public List<ReportableIncedentDetails> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<ReportableIncedentDetails> insertList) {
		this.insertList = insertList;
	}

	public List<ReportableIncedentDetails> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<ReportableIncedentDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ReportableIncedentDetails> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<ReportableIncedentDetails> updateList) {
		this.updateList = updateList;
	}

	
}
