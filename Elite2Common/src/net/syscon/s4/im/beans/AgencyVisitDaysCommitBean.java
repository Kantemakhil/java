package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AgencyVisitDaysCommitBean extends BaseModel implements  Serializable{
	@JsonProperty("insertList")
	private List<AgencyVisitDays> insertList;

	@JsonProperty("deleteList")
	private List<AgencyVisitDays> deleteList;

	@JsonProperty("updateList")
	private List<AgencyVisitDays> updateList;
	
	@JsonProperty("reportInsertList")
	private List<AgencyVisitDays> reportInsertList;
	
	public AgencyVisitDaysCommitBean() {
		// TODO: OffenderBookingEventCommitBean
	}

	public List<AgencyVisitDays> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<AgencyVisitDays> insertList) {
		this.insertList = insertList;
	}

	public List<AgencyVisitDays> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<AgencyVisitDays> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgencyVisitDays> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<AgencyVisitDays> updateList) {
		this.updateList = updateList;
	}

	public List<AgencyVisitDays> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<AgencyVisitDays> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
	
}
