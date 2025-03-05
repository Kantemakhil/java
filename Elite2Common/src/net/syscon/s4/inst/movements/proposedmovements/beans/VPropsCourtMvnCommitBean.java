package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VPropsCourtMvnCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<VPropsCourtMvn> insertList;
	private List<VPropsCourtMvn> deleteList;
	private List<VPropsCourtMvn> updateList;
	
	public List<VPropsCourtMvn> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VPropsCourtMvn> insertList) {
		this.insertList = insertList;
	}
	public List<VPropsCourtMvn> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VPropsCourtMvn> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VPropsCourtMvn> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VPropsCourtMvn> updateList) {
		this.updateList = updateList;
	}
	

}
