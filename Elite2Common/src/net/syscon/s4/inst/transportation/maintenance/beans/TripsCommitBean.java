package net.syscon.s4.inst.transportation.maintenance.beans;
import java.util.List;


import net.syscon.s4.common.beans.BaseModel;
/**
 * Class TripsCommitBean extends BaseBean
 * @author Vrnda Software Technologies 
 * @version 1.0 
 */
public class TripsCommitBean extends BaseModel{
	private List<Trips> insertList;
	private List<Trips> deleteList;
	private List<Trips> updateList;

	public void setInsertList(List<Trips> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<Trips> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<Trips> deleteList){
		this.deleteList = deleteList;
	}

	public List<Trips> getInsertList(){
		return insertList;
	}

	public List<Trips> getUpdateList(){
		return updateList;
	}

	public List<Trips> getDeleteList(){
		return deleteList;
	}

}
