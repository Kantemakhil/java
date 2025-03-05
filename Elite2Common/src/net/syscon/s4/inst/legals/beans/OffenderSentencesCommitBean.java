package net.syscon.s4.inst.legals.beans;

import java.util.List;

public class OffenderSentencesCommitBean {

	private List<OffenderSentences> insertList;
	private List<OffenderSentences> deleteList;
	private List<OffenderSentences> updateList;
	public List<OffenderSentences> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderSentences> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderSentences> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderSentences> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderSentences> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderSentences> updateList) {
		this.updateList = updateList;
	}
}
