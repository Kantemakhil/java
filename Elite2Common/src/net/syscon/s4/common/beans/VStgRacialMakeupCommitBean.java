package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VStgRacialMakeupCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<VStgRacialMakeup> insertList;
	@JsonProperty("deleteList")
	private List<VStgRacialMakeup> deleteList;
	@JsonProperty("updateList")
	private List<VStgRacialMakeup> updateList;
	public VStgRacialMakeupCommitBean() {
//		VStgRacialMakeupCommitBean
	}
	/**
	 * @return the insertList
	 */
	public List<VStgRacialMakeup> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<VStgRacialMakeup> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<VStgRacialMakeup> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<VStgRacialMakeup> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<VStgRacialMakeup> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<VStgRacialMakeup> updateList) {
		this.updateList = updateList;
	}

}
