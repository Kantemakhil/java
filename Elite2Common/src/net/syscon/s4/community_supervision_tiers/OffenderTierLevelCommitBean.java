package net.syscon.s4.community_supervision_tiers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderTierLevelCommitBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderTierLevel> insertList;

	@JsonProperty("deleteList")
	private List<OffenderTierLevel> deleteList;

	@JsonProperty("updateList")
	private List<OffenderTierLevel> updateList;

	public List<OffenderTierLevel> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<OffenderTierLevel> insertList) {
		this.insertList = insertList;
	}

	public List<OffenderTierLevel> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<OffenderTierLevel> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderTierLevel> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<OffenderTierLevel> updateList) {
		this.updateList = updateList;
	}
	
}
