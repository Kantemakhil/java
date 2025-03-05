package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderBookingEventCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderBookingEvent> insertList;

	@JsonProperty("deleteList")
	private List<OffenderBookingEvent> deleteList;

	@JsonProperty("updateList")
	private List<OffenderBookingEvent> updateList;
	
	@JsonProperty("reportInsertList")
	private List<OffenderResidence> reportInsertList;

	/**
	 * Creates new OffenderBookingEventCommitBean class Object
	 */
	public OffenderBookingEventCommitBean() {
		// TODO: OffenderBookingEventCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderBookingEvent> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<OffenderBookingEvent> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderBookingEvent> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<OffenderBookingEvent> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderBookingEvent> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<OffenderBookingEvent> updateList) {
		this.updateList = updateList;
	}

	public List<OffenderResidence> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<OffenderResidence> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}

}
