package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class LegalUpdateReasonsCommitBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<LegalUpdateReasons> insertList;

	@JsonProperty("deleteList")
	private List<LegalUpdateReasons> deleteList;

	@JsonProperty("updateList")
	private List<LegalUpdateReasons> updateList;
	
	@JsonProperty("reportInsertList")
	private List<LegalUpdateReasons> reportInsertList;

	public List<LegalUpdateReasons> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<LegalUpdateReasons> insertList) {
		this.insertList = insertList;
	}

	public List<LegalUpdateReasons> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<LegalUpdateReasons> deleteList) {
		this.deleteList = deleteList;
	}

	public List<LegalUpdateReasons> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<LegalUpdateReasons> updateList) {
		this.updateList = updateList;
	}

	public List<LegalUpdateReasons> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<LegalUpdateReasons> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
}
