package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderIndSchedulesCommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderIndSchedules> insertList;

	@JsonProperty("deleteList")
	private List<OffenderIndSchedules> deleteList;

	@JsonProperty("updateList")
	private List<OffenderIndSchedules> updateList;
	
	@JsonProperty("agyUpdateList")
	private List<OffenderIndSchedules> agyUpdateList;

	/**
	 * @return the insertList
	 */
	public List<OffenderIndSchedules> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	                the insertList to set
	 */
	
	public void setInsertList(final List<OffenderIndSchedules> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderIndSchedules> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	                the deleteList to set
	 */
	
	public void setDeleteList(final List<OffenderIndSchedules> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderIndSchedules> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	                the updateList to set
	 */
	
	public void setUpdateList(final List<OffenderIndSchedules> updateList) {
		this.updateList = updateList;
	}
	

	public List<OffenderIndSchedules> getAgyUpdateList() {
		return agyUpdateList;
	}
	

	public void setAgyUpdateList(List<OffenderIndSchedules> agyUpdateList) {
		this.agyUpdateList = agyUpdateList;
	}



}
