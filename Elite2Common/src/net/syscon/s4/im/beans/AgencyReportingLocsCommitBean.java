package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AgencyReportingLocsCommitBean extends BaseModel implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyReportingLocs> insertList;

	@JsonProperty("deleteList")
	private List<AgencyReportingLocs> deleteList;

	@JsonProperty("updateList")
	private List<AgencyReportingLocs> updateList;
	
	@JsonProperty("reportInsertList")
	private List<AgencyReportingLocs> reportInsertList;
	
	public AgencyReportingLocsCommitBean() {
		//  AgencyReportingLocsCommitBean
	}

	public List<AgencyReportingLocs> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<AgencyReportingLocs> insertList) {
		this.insertList = insertList;
	}

	public List<AgencyReportingLocs> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<AgencyReportingLocs> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgencyReportingLocs> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<AgencyReportingLocs> updateList) {
		this.updateList = updateList;
	}

	public List<AgencyReportingLocs> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<AgencyReportingLocs> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
	
}
