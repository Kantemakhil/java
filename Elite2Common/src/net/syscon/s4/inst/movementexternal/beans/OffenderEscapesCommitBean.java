package net.syscon.s4.inst.movementexternal.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class OffenderEscapesCommitBean
 *
 */
public class OffenderEscapesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderEscapes> insertList;

	@JsonProperty("deleteList")
	private List<OffenderEscapes> deleteList;

	@JsonProperty("updateList")
	private List<OffenderEscapes> updateList;

	/**
	 * @return the insertList
	 */
	public List<OffenderEscapes> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(final List<OffenderEscapes> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderEscapes> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(final List<OffenderEscapes> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderEscapes> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(final List<OffenderEscapes> updateList) {
		this.updateList = updateList;
	}



}
