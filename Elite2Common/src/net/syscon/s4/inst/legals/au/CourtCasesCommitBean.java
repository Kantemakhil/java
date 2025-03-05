package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.beans.CourtCases;

public class CourtCasesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<CourtCases> insertList;
	@JsonProperty("deleteList ")
	private List<CourtCases> deleteList;
	@JsonProperty("updateList")
	private List<CourtCases> updateList;
	@JsonProperty("sealFlag")
	private String sealFlag;

	public void setInsertList(final List<CourtCases> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CourtCases> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CourtCases> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CourtCases> getInsertList() {
		return insertList;
	}

	public List<CourtCases> getUpdateList() {
		return updateList;
	}

	public List<CourtCases> getDeleteList() {
		return deleteList;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
}
