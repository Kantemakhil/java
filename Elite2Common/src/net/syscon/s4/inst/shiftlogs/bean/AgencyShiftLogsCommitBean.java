package net.syscon.s4.inst.shiftlogs.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class AgencyShiftLogsCommitBean
 *
 */
public class AgencyShiftLogsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyShiftLogs> insertList;

	@JsonProperty("deleteList")
	private List<AgencyShiftLogs> deleteList;

	@JsonProperty("updateList")
	private List<AgencyShiftLogs> updateList;

	/**
	 * @return the insertList
	 */
	public List<AgencyShiftLogs> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(final List<AgencyShiftLogs> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<AgencyShiftLogs> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(final List<AgencyShiftLogs> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<AgencyShiftLogs> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(final List<AgencyShiftLogs> updateList) {
		this.updateList = updateList;
	}



}
