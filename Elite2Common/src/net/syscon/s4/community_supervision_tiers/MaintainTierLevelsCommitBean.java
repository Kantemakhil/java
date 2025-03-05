package net.syscon.s4.community_supervision_tiers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class MaintainTierLevelsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<MaintainTierLevels> insertList;

	@JsonProperty("deleteList")
	private List<MaintainTierLevels> deleteList;

	@JsonProperty("updateList")
	private List<MaintainTierLevels> updateList;
	
	@JsonProperty("insertTierDefEvents")
	private List<MaintainTierDefaultEvents> insertTierDefEvents;
	
	@JsonProperty("updateTierDefEvents")
	private List<MaintainTierDefaultEvents> updateTierDefEvents;
	
	@JsonProperty("deleteTierDefEvents")
	private List<MaintainTierDefaultEvents> deleteTierDefEvents;

	public List<MaintainTierLevels> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<MaintainTierLevels> insertList) {
		this.insertList = insertList;
	}

	public List<MaintainTierLevels> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<MaintainTierLevels> deleteList) {
		this.deleteList = deleteList;
	}

	public List<MaintainTierLevels> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<MaintainTierLevels> updateList) {
		this.updateList = updateList;
	}

	public List<MaintainTierDefaultEvents> getInsertTierDefEvents() {
		return insertTierDefEvents;
	}

	public void setInsertTierDefEvents(List<MaintainTierDefaultEvents> insertTierDefEvents) {
		this.insertTierDefEvents = insertTierDefEvents;
	}

	public List<MaintainTierDefaultEvents> getUpdateTierDefEvents() {
		return updateTierDefEvents;
	}

	public void setUpdateTierDefEvents(List<MaintainTierDefaultEvents> updateTierDefEvents) {
		this.updateTierDefEvents = updateTierDefEvents;
	}

	public List<MaintainTierDefaultEvents> getDeleteTierDefEvents() {
		return deleteTierDefEvents;
	}

	public void setDeleteTierDefEvents(List<MaintainTierDefaultEvents> deleteTierDefEvents) {
		this.deleteTierDefEvents = deleteTierDefEvents;
	}
	
	
	

}
