package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ArrestRelatedOffencesCommitBean extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<ArrestRelatedOffences> insertList;
	@JsonProperty("deleteList")
	private List<ArrestRelatedOffences> deleteList;
	@JsonProperty("updateList")
	private List<ArrestRelatedOffences> updateList;
	/**
	 * @return the insertList
	 */
	public List<ArrestRelatedOffences> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<ArrestRelatedOffences> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<ArrestRelatedOffences> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<ArrestRelatedOffences> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<ArrestRelatedOffences> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<ArrestRelatedOffences> updateList) {
		this.updateList = updateList;
	}

}
