package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;

public class InternalScheduleReasonsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<InternalScheduleReasons> insertList;

	@JsonProperty("deleteList")
	private List<InternalScheduleReasons> deleteList;

	@JsonProperty("updateList")
	private List<InternalScheduleReasons> updateList;

	/**
	 * @return the insertList
	 */
	public List<InternalScheduleReasons> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(final List<InternalScheduleReasons> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<InternalScheduleReasons> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(final List<InternalScheduleReasons> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<InternalScheduleReasons> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(final List<InternalScheduleReasons> updateList) {
		this.updateList = updateList;
	}


	
}
