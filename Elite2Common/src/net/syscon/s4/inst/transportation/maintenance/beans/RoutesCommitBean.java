package net.syscon.s4.inst.transportation.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class RoutesCommitBean extends BaseBean
 * @author Vrnda Software Technologies 
 * @version 1.0 
 */
public class RoutesCommitBean extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<Routes> insertList;
	
	@JsonProperty("deleteList")
	private List<Routes> deleteList;
	
	@JsonProperty("updateList")
	private List<Routes> updateList;

	public void setInsertList(List<Routes> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<Routes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<Routes> deleteList){
		this.deleteList = deleteList;
	}

	public List<Routes> getInsertList(){
		return insertList;
	}

	public List<Routes> getUpdateList(){
		return updateList;
	}

	public List<Routes> getDeleteList(){
		return deleteList;
	}

}
