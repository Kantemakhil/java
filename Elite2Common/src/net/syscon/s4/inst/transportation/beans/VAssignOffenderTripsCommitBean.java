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
public class VAssignOffenderTripsCommitBean extends BaseModel{

	private List<VAssignOffenderTrips> insertList;
	private List<VAssignOffenderTrips> deleteList;
	private List<VAssignOffenderTrips> updateList;

	public void setInsertList(List<VAssignOffenderTrips> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VAssignOffenderTrips> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VAssignOffenderTrips> deleteList){
		this.deleteList = deleteList;
	}

	public List<VAssignOffenderTrips> getInsertList(){
		return insertList;
	}

	public List<VAssignOffenderTrips> getUpdateList(){
		return updateList;
	}

	public List<VAssignOffenderTrips> getDeleteList(){
		return deleteList;
	}

}
