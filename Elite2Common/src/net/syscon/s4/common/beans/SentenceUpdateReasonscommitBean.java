package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceUpdateReasonscommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SentenceUpdateReasons> insertList;

	@JsonProperty("deleteList")
	private List<SentenceUpdateReasons> deleteList;

	@JsonProperty("updateList")
	private List<SentenceUpdateReasons> updateList;
	
	@JsonProperty("reportInsertList")
	private List<SentenceUpdateReasons> reportInsertList;

	/**
	 * Creates new OffenderBookingEventCommitBean class Object
	 */
	public SentenceUpdateReasonscommitBean() {
	}

	/**
	 * @return the insertList
	 */
	public List<SentenceUpdateReasons> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<SentenceUpdateReasons> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<SentenceUpdateReasons> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<SentenceUpdateReasons> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<SentenceUpdateReasons> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<SentenceUpdateReasons> updateList) {
		this.updateList = updateList;
	}

	public List<SentenceUpdateReasons> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<SentenceUpdateReasons> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
}
