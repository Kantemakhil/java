package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderPrgObligationsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderPrgObligations> insertList;
	@JsonProperty("deleteList")
	private List<OffenderPrgObligations> deleteList;
	@JsonProperty("updateList")
	private List<OffenderPrgObligations> updateList;

	/**
	 * Creates new OffenderPrgObligationsCommitBean class Object
	 */
	public OffenderPrgObligationsCommitBean() {
		// OffenderPrgObligationsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderPrgObligations> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(List<OffenderPrgObligations> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderPrgObligations> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(List<OffenderPrgObligations> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderPrgObligations> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<OffenderPrgObligations> updateList) {
		this.updateList = updateList;
	}

}
