package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ArrestNumbersCommitBean extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<ArrestNumbers> insertList;
	@JsonProperty("deleteList")
	private List<ArrestNumbers> deleteList;
	@JsonProperty("updateList")
	private List<ArrestNumbers> updateList;
	/**
	 * @return the insertList
	 */
	public List<ArrestNumbers> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<ArrestNumbers> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<ArrestNumbers> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<ArrestNumbers> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<ArrestNumbers> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<ArrestNumbers> updateList) {
		this.updateList = updateList;
	}
	

}
