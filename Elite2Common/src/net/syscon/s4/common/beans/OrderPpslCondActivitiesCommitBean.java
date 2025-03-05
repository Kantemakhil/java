package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPpslCondActivitiesCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OrderPpslCondActivities> insertList;
	@JsonProperty("deleteList")
	private List<OrderPpslCondActivities> deleteList;
	@JsonProperty("updateList")
	private List<OrderPpslCondActivities> updateList;
	public List<OrderPpslCondActivities> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OrderPpslCondActivities> insertList) {
		this.insertList = insertList;
	}
	public List<OrderPpslCondActivities> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OrderPpslCondActivities> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OrderPpslCondActivities> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OrderPpslCondActivities> updateList) {
		this.updateList = updateList;
	}
	
	
}
