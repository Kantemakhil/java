package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class StgRelationshipsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<StgRelationships> insertList;
	private List<StgRelationships> deleteList;
	private List<StgRelationships> updateList;

	public void setInsertList(final List<StgRelationships> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StgRelationships> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StgRelationships> deleteList){
		this.deleteList = deleteList;
	}

	public List<StgRelationships> getInsertList(){
		return insertList;
	}

	public List<StgRelationships> getUpdateList(){
		return updateList;
	}

	public List<StgRelationships> getDeleteList(){
		return deleteList;
	}

	
	
}
