package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VRouteLocationsCommitBean extends BaseModel implements Serializable {
	@JsonProperty("insertList")
	private List<VRouteLocations> insertList;
	@JsonProperty("updateList")
	private List<VRouteLocations> updateList;
	@JsonProperty("deleteList")
	private List<VRouteLocations> deleteList;

	public List<VRouteLocations> getInsertList() {
		return this.insertList;
	}

	public void setInsertList(final List<VRouteLocations> insertList) {
		this.insertList = insertList;
	}

	public List<VRouteLocations> getUpdateList() {
		return this.updateList;
	}

	public void setUpdateList(final List<VRouteLocations> updateList) {
		this.updateList = updateList;
	}

	public List<VRouteLocations> getDeleteList() {
		return this.deleteList;
	}

	public void setDeleteList(final List<VRouteLocations> deleteList) {
		this.deleteList = deleteList;
	}

}