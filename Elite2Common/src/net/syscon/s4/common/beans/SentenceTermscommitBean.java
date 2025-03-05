package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceTermscommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SentenceTerms> insertList;

	@JsonProperty("deleteList")
	private List<SentenceTerms> deleteList;

	@JsonProperty("updateList")
	private List<SentenceTerms> updateList;
	
	@JsonProperty("reportInsertList")
	private List<SentenceTerms> reportInsertList;

	/**
	 * Creates new OffenderBookingEventCommitBean class Object
	 */
	public SentenceTermscommitBean() {
		// TODO: OffenderBookingEventCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<SentenceTerms> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<SentenceTerms> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<SentenceTerms> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<SentenceTerms> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<SentenceTerms> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<SentenceTerms> updateList) {
		this.updateList = updateList;
	}

	public List<SentenceTerms> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<SentenceTerms> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
}
