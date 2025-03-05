package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.Areas;

public class AreasCommitBean extends BaseModel implements Serializable{ 
	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Areas> insertList;

	@JsonProperty("deleteList")
	private List<Areas> deleteList;

	@JsonProperty("updateList")
	private List<Areas> updateList;
	
	@JsonProperty("reportInsertList")
	private List<Areas> reportInsertList;

	public List<Areas> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<Areas> insertList) {
		this.insertList = insertList;
	}

	public List<Areas> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<Areas> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Areas> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<Areas> updateList) {
		this.updateList = updateList;
	}

	public List<Areas> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(final List<Areas> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
	
}
