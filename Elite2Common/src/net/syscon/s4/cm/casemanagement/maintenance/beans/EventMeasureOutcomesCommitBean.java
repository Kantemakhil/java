package net.syscon.s4.cm.casemanagement.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class EventMeasureOutcomesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<EventMeasureOutcomes> insertList;
	@JsonProperty("deleteList")
	private List<EventMeasureOutcomes> deleteList;
	@JsonProperty("updateList")
	private List<EventMeasureOutcomes> updateList;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<EventMeasureOutcomes> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<EventMeasureOutcomes> insertList) {
		this.insertList = insertList;
	}
	public List<EventMeasureOutcomes> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<EventMeasureOutcomes> deleteList) {
		this.deleteList = deleteList;
	}
	public List<EventMeasureOutcomes> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<EventMeasureOutcomes> updateList) {
		this.updateList = updateList;
	}
	
	
}
