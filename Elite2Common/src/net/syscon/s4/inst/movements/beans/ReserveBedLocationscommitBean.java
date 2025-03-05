package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ReserveBedLocationscommitBean extends BaseModel implements Serializable{

	@JsonProperty("insertList")
	private List<ReserveBedLocations> insertList;

	@JsonProperty("deleteList")
	private List<ReserveBedLocations> deleteList;

	@JsonProperty("updateList")
	private List<ReserveBedLocations> updateList;

	/**
	 *
	 * @return
	 */
	public List<ReserveBedLocations> getInsertList() {
		return insertList;
	}

	/**
	 *
	 * @param insertList
	 */
	public void setInsertList(List<ReserveBedLocations> insertList) {
		this.insertList = insertList;
	}

	/**
	 *
	 * @return
	 */
	public List<ReserveBedLocations> getDeleteList() {
		return deleteList;
	}

	/**
	 *
	 * @param deleteList
	 */
	public void setDeleteList(List<ReserveBedLocations> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 *
	 * @return
	 */
	public List<ReserveBedLocations> getUpdateList() {
		return updateList;
	}

	/**
	 *
	 * @param updateList
	 */
	public void setUpdateList(List<ReserveBedLocations> updateList) {
		this.updateList = updateList;
	}

}