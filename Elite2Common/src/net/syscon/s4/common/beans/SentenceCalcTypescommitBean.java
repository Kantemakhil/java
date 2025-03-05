package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceCalcTypescommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SentenceCalcTypes> insertList;

	@JsonProperty("deleteList")
	private List<SentenceCalcTypes> deleteList;

	@JsonProperty("updateList")
	private List<SentenceCalcTypes> updateList;
	
	@JsonProperty("reportInsertList")
	private List<SentenceCalcTypes> reportInsertList;

	/**
	 * Creates new OffenderBookingEventCommitBean class Object
	 */
	public SentenceCalcTypescommitBean() {
		// TODO: OffenderBookingEventCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<SentenceCalcTypes> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<SentenceCalcTypes> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<SentenceCalcTypes> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<SentenceCalcTypes> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<SentenceCalcTypes> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<SentenceCalcTypes> updateList) {
		this.updateList = updateList;
	}

	public List<SentenceCalcTypes> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<SentenceCalcTypes> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}
}
