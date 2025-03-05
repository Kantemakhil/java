package net.syscon.s4.inst.transportation.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class ScheduledTripAssignmentsCommitBean extends BaseBean
 * @author Vrnda Software Technologies 
 * @version 1.0 
 */
public class ScheduledTripAssignmentsCommitBean extends BaseModel implements Serializable{
	private List<ScheduledTripAssignments> insertList;
	private List<ScheduledTripAssignments> deleteList;
	private List<ScheduledTripAssignments> updateList;

	public void setInsertList(List<ScheduledTripAssignments> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<ScheduledTripAssignments> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<ScheduledTripAssignments> deleteList){
		this.deleteList = deleteList;
	}

	public List<ScheduledTripAssignments> getInsertList(){
		return insertList;
	}

	public List<ScheduledTripAssignments> getUpdateList(){
		return updateList;
	}

	public List<ScheduledTripAssignments> getDeleteList(){
		return deleteList;
	}

}
