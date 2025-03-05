package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class HoCodesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<HoCodes> insertList;

	@JsonProperty("deleteList")
	private List<HoCodes> deleteList;

	@JsonProperty("updateList")
	private List<HoCodes> updateList;
	
	@JsonProperty("reportInsertList")
	private List<HoCodes> reportInsertList;

	public List<HoCodes> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<HoCodes> insertList) {
		this.insertList = insertList;
	}

	public List<HoCodes> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<HoCodes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<HoCodes> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<HoCodes> updateList) {
		this.updateList = updateList;
	}

	public List<HoCodes> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<HoCodes> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
}
