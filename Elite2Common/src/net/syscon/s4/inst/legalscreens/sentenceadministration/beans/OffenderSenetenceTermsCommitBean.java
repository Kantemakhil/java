package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderSenetenceTermsCommitBean {

	@JsonProperty("insertList")
	private List<OffenderSenetenceTerms> insertList;

	@JsonProperty("deleteList")
	private List<OffenderSenetenceTerms> deleteList;

	@JsonProperty("updateList")
	private List<OffenderSenetenceTerms> updateList;

	public List<OffenderSenetenceTerms> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderSenetenceTerms> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderSenetenceTerms> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderSenetenceTerms> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderSenetenceTerms> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderSenetenceTerms> updateList) {
		this.updateList = updateList;
	}
	
	
}
