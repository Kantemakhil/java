package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceCustodyStatusCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SentenceCustodyStatus> insertList;

	@JsonProperty("deleteList")
	private List<SentenceCustodyStatus> deleteList;

	@JsonProperty("updateList")
	private List<SentenceCustodyStatus> updateList;
	
	@JsonProperty("reportInsertList")
	private List<SentenceCustodyStatus> reportInsertList;

	public List<SentenceCustodyStatus> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<SentenceCustodyStatus> insertList) {
		this.insertList = insertList;
	}

	public List<SentenceCustodyStatus> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<SentenceCustodyStatus> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SentenceCustodyStatus> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<SentenceCustodyStatus> updateList) {
		this.updateList = updateList;
	}

	public List<SentenceCustodyStatus> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<SentenceCustodyStatus> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}

}
