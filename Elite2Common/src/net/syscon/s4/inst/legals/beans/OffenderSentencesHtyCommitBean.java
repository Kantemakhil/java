package net.syscon.s4.inst.legals.beans;

import java.util.List;

public class OffenderSentencesHtyCommitBean {
	
	private List<OffenderSentencesHty> insertList;
	private List<OffenderSentencesHty> deleteList;
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
