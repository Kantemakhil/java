package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CourtReportCommitBean extends BaseModel implements Serializable  {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CourtReport> insertList;
	
	@JsonProperty("updateList")
	private List<CourtReport> updateList;
	
	@JsonProperty("deleteList")
	private List<CourtReport> deleteList;
	
	@JsonProperty("selectedCourtReport")
	private CourtReport selectedCourtReport;
	
	@JsonProperty("courtReportInsertedRow")
	private CourtReport courtReportInsertedRow;

	public CourtReport getCourtReportInsertedRow() {
		return courtReportInsertedRow;
	}

	public void setCourtReportInsertedRow(CourtReport courtReportInsertedRow) {
		this.courtReportInsertedRow = courtReportInsertedRow;
	}

	public CourtReport getSelectedCourtReport() {
		return selectedCourtReport;
	}

	public void setSelectedCourtReport(CourtReport selectedCourtReport) {
		this.selectedCourtReport = selectedCourtReport;
	}

	public List<CourtReport> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<CourtReport> insertList) {
		this.insertList = insertList;
	}

	public List<CourtReport> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<CourtReport> updateList) {
		this.updateList = updateList;
	}

	public List<CourtReport> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<CourtReport> deleteList) {
		this.deleteList = deleteList;
	}
	
	
	
}


