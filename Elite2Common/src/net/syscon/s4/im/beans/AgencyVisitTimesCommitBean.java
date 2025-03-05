package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AgencyVisitTimesCommitBean extends BaseModel{
	@JsonProperty("insertList")
	private List<AgencyVisitTimes> insertList;

	@JsonProperty("deleteList")
	private List<AgencyVisitTimes> deleteList;

	@JsonProperty("updateList")
	private List<AgencyVisitTimes> updateList;
	
	@JsonProperty("reportInsertList")
	private List<AgencyVisitTimes> reportInsertList;
	public AgencyVisitTimesCommitBean() {
		// TODO: OffenderBookingEventCommitBean
	}
	public List<AgencyVisitTimes> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<AgencyVisitTimes> insertList) {
		this.insertList = insertList;
	}
	public List<AgencyVisitTimes> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<AgencyVisitTimes> deleteList) {
		this.deleteList = deleteList;
	}
	public List<AgencyVisitTimes> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<AgencyVisitTimes> updateList) {
		this.updateList = updateList;
	}
	public List<AgencyVisitTimes> getReportInsertList() {
		return reportInsertList;
	}
	public void setReportInsertList(List<AgencyVisitTimes> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
}
