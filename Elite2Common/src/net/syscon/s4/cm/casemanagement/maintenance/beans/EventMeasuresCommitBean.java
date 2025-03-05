package net.syscon.s4.cm.casemanagement.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class EventMeasuresCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<EventMeasures> insertList;
	@JsonProperty("deleteList")
	private List<EventMeasures> deleteList;
	@JsonProperty("updateList")
	private List<EventMeasures> updateList;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<EventMeasures> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<EventMeasures> insertList) {
		this.insertList = insertList;
	}
	public List<EventMeasures> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<EventMeasures> deleteList) {
		this.deleteList = deleteList;
	}
	public List<EventMeasures> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<EventMeasures> updateList) {
		this.updateList = updateList;
	}
	
	
}
