package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderSentencesHtyCommitBean {

	@JsonProperty("insertList")
	private List<OffenderSentencesHty> insertList;

	@JsonProperty("deleteList")
	private List<OffenderSentencesHty> deleteList;

	@JsonProperty("updateList")
	private List<OffenderSentencesHty> updateList;

	public List<OffenderSentencesHty> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderSentencesHty> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderSentencesHty> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderSentencesHty> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderSentencesHty> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderSentencesHty> updateList) {
		this.updateList = updateList;
	}
	
	
}
