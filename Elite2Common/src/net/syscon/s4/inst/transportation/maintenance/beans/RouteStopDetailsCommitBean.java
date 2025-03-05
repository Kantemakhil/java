package net.syscon.s4.inst.transportation.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class RouteStopDetailsCommitBean extends BaseBean
 * @author Vrnda Software Technologies 
 * @version 1.0 
 */
public class RouteStopDetailsCommitBean extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<RouteStopDetails> insertList;
	private List<RouteStopDetails> deleteList;
	private List<RouteStopDetails> updateList;

	public void setInsertList(List<RouteStopDetails> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<RouteStopDetails> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<RouteStopDetails> deleteList){
		this.deleteList = deleteList;
	}

	public List<RouteStopDetails> getInsertList(){
		return insertList;
	}

	public List<RouteStopDetails> getUpdateList(){
		return updateList;
	}

	public List<RouteStopDetails> getDeleteList(){
		return deleteList;
	}

}
