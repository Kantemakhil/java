package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VExternalMovesCommitBean extends  BaseModel implements Serializable{
	private List<VExternalMoves> insertList;
	private List<VExternalMoves> deleteList;
	private List<VExternalMoves> updateList;
	
	
	
	
	

	public void setInsertList(List<VExternalMoves> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VExternalMoves> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VExternalMoves> deleteList){
		this.deleteList = deleteList;
	}

	public List<VExternalMoves> getInsertList(){
		return insertList;
	}

	public List<VExternalMoves> getUpdateList(){
		return updateList;
	}

	public List<VExternalMoves> getDeleteList(){
		return deleteList;
	}

	
	
	
	
}
