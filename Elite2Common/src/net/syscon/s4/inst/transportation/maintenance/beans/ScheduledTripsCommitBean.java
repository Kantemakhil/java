package net.syscon.s4.inst.transportation.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class ScheduledTripsCommitBean extends BaseBean
 * @author Vrnda Software Technologies 
 * @version 1.0 
 */
public class ScheduledTripsCommitBean extends BaseModel implements Serializable{
	private List<ScheduledTrips> insertList;
	private List<ScheduledTrips> deleteList;
	private List<ScheduledTrips> updateList;

	public void setInsertList(List<ScheduledTrips> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<ScheduledTrips> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<ScheduledTrips> deleteList){
		this.deleteList = deleteList;
	}

	public List<ScheduledTrips> getInsertList(){
		return insertList;
	}

	public List<ScheduledTrips> getUpdateList(){
		return updateList;
	}

	public List<ScheduledTrips> getDeleteList(){
		return deleteList;
	}

}
