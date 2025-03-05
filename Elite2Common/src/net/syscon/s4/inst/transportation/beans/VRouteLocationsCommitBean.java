/**
 * 
 */
package net.syscon.s4.inst.transportation.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * @author Gettha T
 *
 */
public class VRouteLocationsCommitBean extends BaseModel {

	private List<VRouteLocations> insertList;
	private List<VRouteLocations> deleteList;
	private List<VRouteLocations> updateList;

	public void setInsertList(List<VRouteLocations> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VRouteLocations> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VRouteLocations> deleteList){
		this.deleteList = deleteList;
	}

	public List<VRouteLocations> getInsertList(){
		return insertList;
	}

	public List<VRouteLocations> getUpdateList(){
		return updateList;
	}

	public List<VRouteLocations> getDeleteList(){
		return deleteList;
	}

}


