package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourtReportChargesCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<CourtReportCharges> insertList;
	@JsonProperty("deleteList")
	private List<CourtReportCharges> deleteList;
	@JsonProperty("updateList")
	private List<CourtReportCharges> updateList;
	
	public List<CourtReportCharges> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<CourtReportCharges> insertList) {
		this.insertList = insertList;
	}
	public List<CourtReportCharges> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<CourtReportCharges> deleteList) {
		this.deleteList = deleteList;
	}
	public List<CourtReportCharges> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<CourtReportCharges> updateList) {
		this.updateList = updateList;
	}
	
	
}
