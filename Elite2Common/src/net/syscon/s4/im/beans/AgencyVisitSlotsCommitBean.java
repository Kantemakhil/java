package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AgencyVisitSlotsCommitBean extends BaseModel{
	@JsonProperty("insertList")
	private List<AgencyVisitSlots> insertList;

	@JsonProperty("deleteList")
	private List<AgencyVisitSlots> deleteList;

	@JsonProperty("updateList")
	private List<AgencyVisitSlots> updateList;
	
	@JsonProperty("reportInsertList")
	private List<AgencyVisitSlots> reportInsertList;
	public AgencyVisitSlotsCommitBean() {
		// TODO: OffenderBookingEventCommitBean
	}
	public List<AgencyVisitSlots> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<AgencyVisitSlots> insertList) {
		this.insertList = insertList;
	}
	public List<AgencyVisitSlots> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<AgencyVisitSlots> deleteList) {
		this.deleteList = deleteList;
	}
	public List<AgencyVisitSlots> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<AgencyVisitSlots> updateList) {
		this.updateList = updateList;
	}
	public List<AgencyVisitSlots> getReportInsertList() {
		return reportInsertList;
	}
	public void setReportInsertList(List<AgencyVisitSlots> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
}
